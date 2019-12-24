import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RoomChecker {
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
		Hotel hotel = BookingSystem.getHotelList()[HotelID];
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
}
