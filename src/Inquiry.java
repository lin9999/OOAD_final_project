import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Inquiry {
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
		Hotel hotel = BookingSystem.getHotelList()[order.getHotelID()];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = RoomChecker.CountDaysBetween(sdf.format(Now), order.getCheckInDate());
		long end = RoomChecker.CountDaysBetween(sdf.format(Now), order.getCheckOutDate());
		
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
		long Days = RoomChecker.CountDaysBetween(order.getCheckInDate(), order.getCheckOutDate());
		long D = RoomChecker.CountDaysBetween(nCID, nCOD);
		
		return D > 0 && D <= Days && RoomChecker.CountDaysBetween(order.getCheckInDate(), nCID) >= 0;
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
		Hotel hotel = BookingSystem.getHotelList()[order.getHotelID()];
		Room[] singleroom = hotel.getSingleRooms();
		Room[] doubleroom = hotel.getDoubleRooms();
		Room[] quadroom = hotel.getQuadRooms();
		
		Date Now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		long start = RoomChecker.CountDaysBetween(sdf.format(Now), order.getCheckInDate());
		long end = RoomChecker.CountDaysBetween(sdf.format(Now), order.getCheckOutDate());
		
		long nstart = RoomChecker.CountDaysBetween(sdf.format(Now), nCID);
		long nend = RoomChecker.CountDaysBetween(sdf.format(Now), nCOD);
		
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
