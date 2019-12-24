import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Search {
	/**
	 * This method runs the given Search operation.
	 * 
	 * @param CID is the check-in date
	 * @param COD is the check-out date
	 * @param p is the demand number of people 
	 * @param n is the demand number of rooms
	 * @return ArrayList the list of the available hotel rooms
	 */
	public static ArrayList<AvailableHotelRoom> SearchAvailableHotels(Hotel[] HotelList, String CID, String COD, int p, int n) {
		ArrayList<AvailableHotelRoom> AHR = new ArrayList<AvailableHotelRoom>();

		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = RoomChecker.CountDaysBetween(sdf.format(Now), CID);
		long end = RoomChecker.CountDaysBetween(sdf.format(Now), COD);

		for (Hotel hotel : HotelList) {
			Room[] singleroom = hotel.getSingleRooms();
			Room[] doubleroom = hotel.getDoubleRooms();
			Room[] quadroom = hotel.getQuadRooms();

			int available_sr = 0;
			if (singleroom != null) 
				for (Room sr : singleroom)
					if (RoomChecker.CheckRoomIsAvailable(sr, start, end))
						available_sr++;
	
			int available_dr = 0;
			if (doubleroom != null) 
				for (Room dr : doubleroom)
					if (RoomChecker.CheckRoomIsAvailable(dr, start, end))
						available_dr++;
			
			int available_qr = 0;
			if (quadroom != null) {
				for (Room qr : quadroom)
					if (RoomChecker.CheckRoomIsAvailable(qr, start, end))
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
	public static int CountSumPrice(Hotel[] HotelList, AvailableHotelRoom x) {
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
				return (op == 1 ? (CountSumPrice(BookingSystem.getHotelList(), a) - CountSumPrice(BookingSystem.getHotelList(), b)) : (CountSumPrice(BookingSystem.getHotelList(), b) - CountSumPrice(BookingSystem.getHotelList(),a)));
			}
		});
		return AHR;
	}
}
