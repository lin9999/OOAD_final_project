import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Reserve {
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
	public static ArrayList<ArrayList<Integer>> ReserveRoom(int HotelID, String UserID, long start, long end, int sn, int dn, int qn) {
		Hotel hotel = BookingSystem.getHotelList()[HotelID];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		ArrayList<ArrayList<Integer>> RoomNumbers = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 3; i++)
			RoomNumbers.add(new ArrayList<Integer> ());
		
		if (sn > 0) { // single room
			int booked = 0;
			for (int i = 0; i < singleroom.length; i++) {
				if (RoomChecker.CheckRoomIsAvailable(singleroom[i], start, end)) {
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
				if (RoomChecker.CheckRoomIsAvailable(doubleroom[i], start, end)) {
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
				if (RoomChecker.CheckRoomIsAvailable(quadroom[i], start, end)) {
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
	public static Order BookHotel(User user, String CID, String COD, int HotelID, int sn, int dn, int qn) {
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = RoomChecker.CountDaysBetween(sdf.format(Now), CID);
		long end = RoomChecker.CountDaysBetween(sdf.format(Now), COD);

		if (RoomChecker.CheckAllRooms(HotelID, start, end, sn, dn, qn)) {
			ArrayList<ArrayList<Integer>> re = Reserve.ReserveRoom(HotelID, user.getUserID(), start, end, sn, dn, qn);
			Order nOrder = new Order(databaseUtil.getNewOrderID(), user.getUserID(), HotelID, CID, COD, re.get(0), re.get(1), re.get(2));
			databaseUtil.insertOrder(nOrder);
			return nOrder;
		}
		return null;
	}
}
