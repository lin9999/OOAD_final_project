import java.io.InputStreamReader;
import java.io.Reader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * This is the main class for running a Hotel Booking System.
 * 
 * @author B06505017, B06505032, B06505054, B06902023
 * @version 1.0
 * @since 2019-05-31
 */
public class BookingSystem {
	private static Hotel[] HotelList;
	public static User user;
	
	public static Hotel[] getHotelList() {
		return HotelList;
	}
	
	/**
	 * This method reads the hotel list.
	 */
	public static void ReadHotelList()  {
		try (Reader reader = new InputStreamReader(BookingSystem.class.getResourceAsStream("HotelList.json"), "big5")) {
			Gson gson = new GsonBuilder().create();
			HotelList = gson.fromJson(reader, Hotel[].class);
			for (Hotel h : HotelList)
				h.init();
//			for (Hotel h : HotelList)
//				System.out.println(h);
		} catch (Exception e) {
			System.out.println("cannot find the file.");
		}
	}
	
	public static void main(String[] args) {
		
		databaseUtil.buildConnection();
//		databaseUtil.initDatabase();
		ReadHotelList();
		HotelPreference program = new HotelPreference();
		program.setVisible(true);
		
	}
}
