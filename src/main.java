import java.util.*;
import java.io.*;
import org.json.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOExceptionWithCause;

import com.google.gson.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This is the class of the main class for running a Hotel Booking System.
 * 
 * @author B06505017, B06505032, B06505054, B06902023
 * @version 1.0
 * @since 2019-05-31
 */
public class main {
	public static Hotel[] HotelList;
	public static User user;

	/**
	 * This is the main method to build the connection with database, read the hotel list, and set the intitialized GUI.
	 * 
	 * @param args Unused.
	 */
	public static void main(String[] args) {
		databaseUtil.buildConnection();
//		databaseUtil.initDatabase();
		ReadHotelList();
		HotelPreference program = new HotelPreference();
		program.setVisible(true);
	}

	/**
	 * This method reads the hotel list.
	 */
	public static void ReadHotelList()  {
		try (Reader reader = new InputStreamReader(main.class.getResourceAsStream("HotelList.json"), "big5")) {
			Gson gson = new GsonBuilder().create();
			HotelList = gson.fromJson(reader, Hotel[].class);
			for (Hotel h : HotelList)
				h.init();
			for (Hotel h : HotelList)
				System.out.println(h);
		} catch (Exception e) {
			System.out.println("cannot find the file.");
		}
	}
	
	/**
	 * This method produce randomly return a String with assigned length.
	 * 
	 * @param length the assigned length.
	 * @return String the random String.
	 */
	public static String getRandomString(int length) {
		String str = "abcdefghigklmnopkrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(62);// 0~61
			sf.append(str.charAt(number));
		}
		return sf.toString();
	}

	/**
	 * This method checks whether the User can be signed in.
	 * 
	 * @param UserID the current user's ID
	 * @param Password the current user's password
	 * @return int 1 if the user can be signed, 0 if user's ID is unknown, -1 if the password is wrong
	 */
	public static int SignInCheck(String UserID, String Password) { 
		user = databaseUtil.getUser(UserID);
		if (user == null) return 0;
		else if (!Password.equals(user.getPassword())) return -1;
		return 1;
	}

	/**
	 * This method checks whether the current user's ID doesn't exist.
	 * 
	 * @param UserID the current user's ID
	 * @return boolean true if user's ID doesn't exist.
	 */
	public static boolean SignUpCheck(String UserID) {
		return databaseUtil.getUser(UserID) == null;
	}

	/**
	 * This methods counts the days between the given two dates. 
	 * If the second date is ahead of the first date, it returns negative numbers.
	 * 
	 * @param D1 is the first date
	 * @param D2 is the second date
	 * @return long the days between the two dates
	 */
	public static long CountDaysBetween(String D1, String D2) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		final LocalDate firstDate = LocalDate.parse(D1, formatter);
		final LocalDate secondDate = LocalDate.parse(D2, formatter);
		final long days = ChronoUnit.DAYS.between(firstDate, secondDate);
		// System.out.println("Days between: " + days);
		return days;
	}

	/**
	 * This method checks if the given room is available in the given period.
	 * 
	 * @param room the given room
	 * @param Start is the number of (start date - today)
	 * @param end is the number of (end date - today)
	 * @return boolean true if the room is available
	 */
	public static boolean CheckRoomIsAvailable(Room room, long Start, long end) {
		boolean[] DIO = room.getDateIsOccupied();
		for (int i = (int) Start; i < end; i++)
			if (DIO[i] == true) 
				return false;
		return true;
	}

	/**
	 * This method checks if all the rooms in the hotel of the current order are available for booking.
	 * It calls the CheckRoomIsAvailable() to check the rooms one by one.
	 * 
	 * @param HotelID the current hotel
	 * @param start is the number of (start date - today)
	 * @param end is the number of (end date - today)
	 * @param sn is the demand number of single room
	 * @param dn is the demand number of double room
	 * @param qn is the demand number of quad room
	 * @return boolean true if all the rooms are available
	 */
	public static boolean CheckAllRooms(int HotelID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();

		if (sn > 0) {
			if (singleroom == null)
				return false;
			int ok = 0;
			for (Room sr : singleroom)
				if (CheckRoomIsAvailable(sr, start, end))
					ok++;
			if (ok < sn)
				return false;
		}
		if (dn > 0) {
			int ok = 0;
			for (Room dr : doubleroom)
				if (CheckRoomIsAvailable(dr, start, end))
					ok++;
			if (ok < dn)
				return false;
		}
		if (qn > 0) {
			int ok = 0;
			for (Room qr : quadroom)
				if (CheckRoomIsAvailable(qr, start, end))
					ok++;
			if (ok < qn)
				return false;
		}
		return true;
	}

	/**
	 * This method runs the given reserve operation.
	 * 
	 * @param HotelID is the given Hotel
	 * @param UserID is the current user's ID
	 * @param start is the number of (start date - today)
	 * @param end is the number of (end date - today)
	 * @param sn is the demand number of single room
	 * @param dn is the demand number of double room
	 * @param qn is the demand number of quad room
	 * @return ArrayList the list of the reserved room numbers
	 */
	public static ArrayList<ArrayList<Integer>> Reserve(int HotelID, String UserID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = HotelList[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		ArrayList<ArrayList<Integer>> RoomNumbers = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 3; i++)
			RoomNumbers.add(new ArrayList<Integer> ());
		
		if (sn > 0) { // single room
			int booked = 0;
			for (int i = 0; i < singleroom.length; i++) {
				if (CheckRoomIsAvailable(singleroom[i], start, end)) {
					for (int t = (int)start; t < end; t++)
						singleroom[i].setDateIsOccupied(t);
					RoomNumbers.get(0).add(i);
					booked++;
				}
				if (booked == sn)
					break;
			}
		}
		if (dn > 0) { // double room
			int booked = 0;
			for (int i = 0; i < doubleroom.length; i++) {
				if (CheckRoomIsAvailable(doubleroom[i], start, end)) {
					for (int t = (int)start; t < end; t++)
						doubleroom[i].setDateIsOccupied(t);
					RoomNumbers.get(1).add(i);
					booked++;
				}
				if (booked == dn)
					break;
			}
		}
		if (qn > 0) { // quad room
			int booked = 0;
			for (int i = 0; i < quadroom.length; i++) {
				if (CheckRoomIsAvailable(quadroom[i], start, end)) {
					for (int t = (int) start; t < end; t++)
						quadroom[i].setDateIsOccupied(t);
					RoomNumbers.get(2).add(i);
					booked++;
				}
				if (booked == qn)
					break;
			}
		}
		return RoomNumbers;
	}

	/**
	 * This method runs the given Search operation.
	 * 
	 * @param CID is the check-in date
	 * @param COD is the check-out date
	 * @param p is the demand number of people 
	 * @param n is the demand number of rooms
	 * @return ArrayList the list of the available hotel rooms
	 */
	public static ArrayList<AvailableHotelRoom> SearchAvailableHotels(String CID, String COD, int p, int n) {
		ArrayList<AvailableHotelRoom> AHR = new ArrayList<AvailableHotelRoom>();

		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), CID);
		long end = CountDaysBetween(sdf.format(Now), COD);

		for (Hotel hotel : HotelList) {
			Room[] singleroom = hotel.getSingleRooms();
			Room[] doubleroom = hotel.getDoubleRooms();
			Room[] quadroom = hotel.getQuadRooms();

			int available_sr = 0;
			if (singleroom != null) 
				for (Room sr : singleroom)
					if (CheckRoomIsAvailable(sr, start, end))
						available_sr++;
	
			int available_dr = 0;
			if (doubleroom != null) 
				for (Room dr : doubleroom)
					if (CheckRoomIsAvailable(dr, start, end))
						available_dr++;
			
			int available_qr = 0;
			if (quadroom != null) {
				for (Room qr : quadroom)
					if (CheckRoomIsAvailable(qr, start, end))
						available_qr++;
			}
			/*
			 * solve 1*x + 2*y + 4*z >= p x >= 0, y >= 0, z >= 0, x + y + z = n
			 */
			for (int x = 0; x <= Math.min(n, available_sr); x++)
				for (int y = 0; y <= Math.min(n, available_dr); y++)
					for (int z = 0; z <= Math.min(n, available_qr); z++)
						if (x + y + z == n && 1 * x + 2 * y + 4 * z >= p)
							AHR.add(new AvailableHotelRoom(hotel.getID(), hotel.getStar(), hotel.getLocality(),
									hotel.getAddress(), x, y, z));
		}
		return AHR;
	}

	/**
	 * This method runs the given Reserve operation. 
	 * It first call the CheckAllRooms() and produce an Order of the given demand.
	 * 
	 * @param CID is the check-in date
	 * @param COD is the check-out date
	 * @param HotelID the current hotel's ID
	 * @param sn is the demand number of single rooms
	 * @param dn is the demand number of double rooms
	 * @param qn is the demand number of quad rooms
	 * @return Order based on the given demand
	 */
	public static Order BookHotel(String CID, String COD, int HotelID, int sn, int dn, int qn) {
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), CID);
		long end = CountDaysBetween(sdf.format(Now), COD);

		if (CheckAllRooms(HotelID, start, end, sn, dn, qn)) {
			ArrayList<ArrayList<Integer>> re = Reserve(HotelID, user.getUserID(), start, end, sn, dn, qn);
			Order nOrder = new Order(databaseUtil.getNewOrderID(), user.getUserID(), HotelID, CID, COD, re.get(0), re.get(1), re.get(2));
			databaseUtil.insertOrder(nOrder);
			return nOrder;
		}
		return null;
	}

	/**
	 * This method checks if the given order's ID exists.
	 * 
	 * @param OrderID is the given order's ID
	 * @return boolean true if the order's ID exists.
	 */
	public static Order CheckOrder(int OrderID) {
		return databaseUtil.getOrderByOrderID(OrderID);
	}
	
	/**
	 * This method selects the matched hotels and rooms from the list AvailableHotelRoom based on the given hotel's star.
	 * 
	 * @param AHR is the AvailableHotelRoom list
	 * @param Star is the given hotel's star
	 * @return ArrayList the new AvailableHotelRoom list
	 */
	public static ArrayList<AvailableHotelRoom> SearchByStar(ArrayList<AvailableHotelRoom> AHR, int Star) {
		ArrayList<AvailableHotelRoom> nAHR = new ArrayList<AvailableHotelRoom>();
		for (AvailableHotelRoom ahr : AHR)
			if (ahr.getHotelStar() == Star)
				nAHR.add(ahr);
		return nAHR;
	}

	/**
	 * This method counts the cost of the given AvailableHotelRoom.
	 * 
	 * @param x is the given room
	 * @return int the summation price 
	 */
	public static int CountSumPrice(AvailableHotelRoom x) {
		return HotelList[x.getHotelID()].getSingleRoomPrice() * x.getSingle() 
			 + HotelList[x.getHotelID()].getDoubleRoomPrice() * x.getDouble()
		     + HotelList[x.getHotelID()].getQuadRoomPrice() * x.getQuad();
	}

	/**
	 * This method sorts the given list AvailableHotelRoom by price.
	 * When option is 1, sort in descending order. When option is 2, sort in ascending order.
	 * 
	 * @param AHR is the given list
	 * @param op is the option
	 * @return ArrayList the sorted list
	 */
	public static ArrayList<AvailableHotelRoom> SortByPrice(ArrayList<AvailableHotelRoom> AHR, int op) {
		Collections.sort(AHR, new Comparator<AvailableHotelRoom>() {
			public int compare(AvailableHotelRoom a, AvailableHotelRoom b) {
				return (op == 1 ? (CountSumPrice(a) - CountSumPrice(b)) : (CountSumPrice(b) - CountSumPrice(a)));
			}
		});
		return AHR;
	}

	/**
	 * This method runs the given Cancel Order operation.
	 * 
	 * @param OrderID is the given order's ID
	 */
	public static void CancelOrder(int OrderID) {
		databaseUtil.deleteOrder(OrderID);
	}
	
	/**
	 * This method runs the Change Rooms operation.
	 * 
	 * @param OrderID is the given order's ID
	 * @param nsn is the modified number of single rooms
	 * @param ndn is the modified number of double rooms
	 * @param nqn is the modified number of quad rooms
	 * @return Order the modified order
	 */
	public static Order ChangeRooms(int OrderID, int nsn, int ndn, int nqn) { 
		Order order = databaseUtil.getOrderByOrderID(OrderID);
		Hotel hotel = HotelList[order.getHotelID()];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), order.getCheckInDate());
		long end = CountDaysBetween(sdf.format(Now), order.getCheckOutDate());
		
		int sn = order.getSnum().size();
		ArrayList<Integer> Snum = order.getSnum();
		if (nsn < sn) {
			for (int i = sn-1; i >= nsn; i--) {
				for (int t = (int)start; t < end; t++)
					singleroom[Snum.get(i)].setDateIsNotOccupied(t);
				Snum.remove(i);
				Snum.trimToSize();
			}
		} 
		int dn = order.getDnum().size();
		ArrayList<Integer> Dnum = order.getDnum();
		if (ndn < dn) {
			for (int i = dn-1; i >= ndn; i--) {
				for (int t = (int)start; t < end; t++) 
					doubleroom[Dnum.get(i)].setDateIsNotOccupied(t);
				Dnum.remove(i);
				Dnum.trimToSize();
			}
		} 
		int qn = order.getQnum().size();
		ArrayList<Integer> Qnum = order.getQnum();
		if (nqn < qn) {
			for (int i = qn-1; i >= nqn; i--) {
				for (int t = (int)start; t < end; t++) 
					quadroom[Qnum.get(i)].setDateIsNotOccupied(t);
				Qnum.remove(i);
				Qnum.trimToSize();
			}
		}
		Order newOrder = new Order(OrderID, order.getUserID(), order.getHotelID(), order.getCheckInDate(), order.getCheckOutDate(), 
				Snum, Dnum, Qnum);
		databaseUtil.insertOrder(newOrder);
		return newOrder;
	}
	
	/**
	 * This method checks whether the revised date is valid.
	 * Valid means that the revised date period must be lagrget thean 0, and smaller than the original date period.
	 * 
	 * @param OrderID is the given order's ID
	 * @param nCID is the revised check-in date
	 * @param nCOD is the revised check-out date
	 * @return boolean true if the revised is valid
	 */
	public static boolean CheckDateforReviseDate(int OrderID, String nCID, String nCOD) {
		Order order = databaseUtil.getOrderByOrderID(OrderID);
		long Days = CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate());
		long D = CountDaysBetween(nCID, nCOD);
		
		return D > 0 && D < Days && CountDaysBetween(order.getCheckInDate(), nCID) >= 0;
	}

	/**
	 * This method runs the Revise Date operation. 
	 * 
	 * @param OrderID is the given order's ID
	 * @param nCID is the new check-in date
	 * @param nCOD is the new check-out date
	 * @return Order the modified order
	 */
	public static Order ModifyDate(int OrderID, String nCID, String nCOD) { 
		Order order = databaseUtil.getOrderByOrderID(OrderID);
		Hotel hotel = HotelList[order.getHotelID()];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = CountDaysBetween(sdf.format(Now), order.getCheckInDate());
		long end = CountDaysBetween(sdf.format(Now), order.getCheckOutDate());
		
		long nstart = CountDaysBetween(sdf.format(Now), nCID);
		long nend = CountDaysBetween(sdf.format(Now), nCOD);
		
		ArrayList<Integer> Snum = order.getSnum();
		if (Snum.size() > 0) {
			for (int i = 0; i < Snum.size(); i++) 
				for (int t = (int)start; t < end; t++) 
					if (nstart <= t && t < nend) 
						singleroom[Snum.get(i)].setDateIsNotOccupied(t);
		}
		ArrayList<Integer> Dnum = order.getDnum();
		if (Dnum.size() > 0) {
			for (int i = 0; i < Dnum.size(); i++) 
				for (int t = (int)start; t < end; t++) 
					if (nstart <= t && t < nend) 
						doubleroom[Dnum.get(i)].setDateIsNotOccupied(t);
		}
		ArrayList<Integer> Qnum = order.getQnum();
		if (Qnum.size() > 0) {
			for (int i = 0; i < Qnum.size(); i++) 
				for (int t = (int)start; t < end; t++) 
					if (nstart <= t && t < nend) 
						quadroom[Qnum.get(i)].setDateIsNotOccupied(t);
		}
		Order newOrder = new Order(OrderID, order.getUserID(), order.getHotelID(), nCID, nCOD, 
				Snum, Dnum, Qnum);
		databaseUtil.insertOrder(newOrder);
		return newOrder;
	}
}
