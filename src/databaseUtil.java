import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

/**
 * <h1>This is the class of database<\h1>
 * 
 * @author momo, tim, catherine, sopia
 * @version 1.0
 * @since 2019-05-31
 */
public class databaseUtil {
	// MySQL
	static Connection connect = null;
	static Statement stmt = null;
	static ResultSet results = null;

	/**
	 * build connection to MySQL database(user:root, password:root)
	 * 
	 */
	public static void buildConnection() {
		// build connection to MySQL
		try {
			System.out.print("Connecting to MySQL...");

			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost/?user=root&password=root");
			stmt = connect.createStatement();
			stmt.execute("USE `hotelList`;");
			System.out.println("finish!");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	/**
	 * read SQL Script to build tables
	 * 
	 */
	public static void initDatabase() {
		// build database
		BufferedReader fin = null;
		try {
			System.out.print("Building database...");

			fin = new BufferedReader(new FileReader("buildTable.sql"));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = fin.readLine()) != null) {
				sb.append(line + "\n");
			}
			fin.close();

			String[] cmds = sb.toString().split(";");
			for (int i = 0; i < cmds.length; i++) {
				if (!cmds[i].trim().equals("")) {
					stmt.execute(cmds[i]);
				}
			}

			System.out.println("finish!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * insert a user to table 'Users' by given User object
	 * 
	 * @param newUser new User to be insert
	 * @return True if insert successfully, False instead.
	 */
	public static boolean insertUser(User newUser) {
		String cmd = "INSERT INTO Users"
						+ "(UID, password)" 
						+ "VALUES"
						+ "(\'" + newUser.getUserID() + "\', \'" + newUser.getPassword() + "\');";

		
		try {
			stmt.execute(cmd);
		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * get the certain User by given UserID
	 * @param UID User's ID
	 * @return User with UID
	 */
	public static User getUser(String UID) {
		String cmd = "SELECT * FROM Users WHERE UID=\'" + UID + "\';";
		try {
			results = stmt.executeQuery(cmd);
			if (results.next()) {
				return new User(results.getString("UID"), results.getString("password"));
			} else {
				System.out.println("No such User!!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new User();
	}
	

	/**
	 * insert a Order to table 'Orders' by given Order object
	 * @param newOrder The order to be insert
	 * @return True if insert successfully, False instead
	 */
	public static boolean insertOrder(Order newOrder) {
		String SR = "", DR = "", QR = "";
		for (Integer num : newOrder.getSnum())
			SR = SR + num.toString() + ":";
		for (Integer num : newOrder.getDnum())
			DR = DR + num.toString() + ":";
		for (Integer num : newOrder.getQnum())
			QR = QR + num.toString() + ":";
		
		String cmd = "INSERT INTO Orders"
						+ "(OrderID, UID, HotelID, SingleRoom, DoubleRoom, QuadRoom, CheckIn, CheckOut)" 
						+ "VALUES("
						+ newOrder.getID() + ", " 
						+ "\'" + newOrder.getUserID() + "\'" + ", "
						+ newOrder.getHotelID() + ", "
						+ "\'" + SR + "\'" + ", "
						+ "\'" + DR + "\'" + ", "
						+ "\'" + QR + "\'" + ", "
						+ "\'" + newOrder.getCheckInDate().replace('/', '-') + "\'" + ", "
						+ "\'" + newOrder.getCheckOutDate().replace('/', '-') + "\'" + ");";
		try {
			if (getOrderByOrderID(newOrder.getID()) != null) {
				stmt.execute("DELETE FROM Orders WHERE OrderID=" + newOrder.getID() + ";");
			}
			stmt.execute(cmd);
		} catch (SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * To check if String s is an integer
	 * @param s String to be checked
	 * @return True if s is a integer, False instead
	 */
	private static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex) {
	        return false;
	    }
	}
	
	/**
	 * get the certain OrderID by given OrderID
	 * @param OrderID order ID
	 * @return Order with OrderID
	 */
	public static Order getOrderByOrderID(int OrderID) {
		String cmd = "SELECT * FROM Orders WHERE OrderID=" + OrderID + ";";
		try {
			results = stmt.executeQuery(cmd);
			
			if (results.next()) {
				ArrayList<Integer> SRoom = new ArrayList<Integer>();
				ArrayList<Integer> DRoom = new ArrayList<Integer>();
				ArrayList<Integer> QRoom = new ArrayList<Integer>();
				String SR = results.getString("SingleRoom"), DR = results.getString("DoubleRoom"), QR = results.getString("QuadRoom");
				for (String num : SR.split(":")) {
					if (!isInt(num)) break;
					SRoom.add(Integer.valueOf(num));
				}
				for (String num : DR.split(":")) {
					if (!isInt(num)) break;
					DRoom.add(Integer.valueOf(num));
				}
				for (String num : QR.split(":")) {
					if (!isInt(num)) break;
					QRoom.add(Integer.valueOf(num));
				}
				return new Order(results.getInt("OrderID"), 
								 results.getString("UID"), 
								 results.getInt("HotelID"), 
								 results.getDate("CheckIn").toString().replace('-', '/'),
								 results.getDate("CheckOut").toString().replace('-', '/'),
								 SRoom, 
								 DRoom, 
								 QRoom);
			} else {
				System.out.println("No such Order!!");
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * get the Order list by given UserID
	 * @param UID user ID
	 * @return Order list with UID
	 */
	public static Order[] getOrderByUserID(String UID) {
		String cmd = "SELECT * FROM Orders WHERE UID=\'" + UID + "\';";
		System.out.println(cmd);

		try {
			int len;
			results = stmt.executeQuery(cmd);
			results.last();
			len = results.getRow();
			results.first();
			
			if (len == 0) {
				System.out.println("No such Order!!");
				return null;
			}
			
			Order[] retList = new Order[len];
			int index = 0;
			do {
				ArrayList<Integer> SRoom = new ArrayList<Integer>();
				ArrayList<Integer> DRoom = new ArrayList<Integer>();
				ArrayList<Integer> QRoom = new ArrayList<Integer>();
				String SR = results.getString("SingleRoom"), DR = results.getString("DoubleRoom"), QR = results.getString("QuadRoom");
				for (String num : SR.split(":")) {
					if (num == "") break;
					SRoom.add(Integer.valueOf(num));
				}
				for (String num : DR.split(":")) {
					if (num == "") break;
					DRoom.add(Integer.valueOf(num));
				}
				for (String num : QR.split(":")) {
					if (num == "") break;
					QRoom.add(Integer.valueOf(num));
				}
				retList[index++] = new Order(results.getInt("OrderID"), 
											 results.getString("UID"), 
											 results.getInt("HotelID"), 
											 results.getDate("CheckIn").toString().replace('-', '/'),
											 results.getDate("CheckOut").toString().replace('-', '/'),
											 SRoom, 
											 DRoom, 
											 QRoom);
			} while(results.next());

			return retList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * Get the certain Order list by given HotelID
	 * @param HotelID hotel ID
	 * @return Order list with HotelID
	 */
	public static Order[] getOrderByHotelID(int HotelID) {
		String cmd = "SELECT * FROM Orders WHERE HotelID=" + HotelID + ";";
		try {
			int len;
			results = stmt.executeQuery(cmd);
			results.last();
			len = results.getRow();
			results.first();
			
			if (len == 0) {
				System.out.println("No such Order!!");
				return null;
			}
			
			Order[] retList = new Order[len];
			int index = 0;
			do {
				ArrayList<Integer> SRoom = new ArrayList<Integer>();
				ArrayList<Integer> DRoom = new ArrayList<Integer>();
				ArrayList<Integer> QRoom = new ArrayList<Integer>();
				String SR = results.getString("SingleRoom"), DR = results.getString("DoubleRoom"), QR = results.getString("QuadRoom");
				for (String num : SR.split(":")) {
					if (num == "") break;
					SRoom.add(Integer.valueOf(num));
				}
				for (String num : DR.split(":")) {
					if (num == "") break;
					DRoom.add(Integer.valueOf(num));
				}
				for (String num : QR.split(":")) {
					if (num == "") break;
					QRoom.add(Integer.valueOf(num));
				}
				retList[index++] = new Order(results.getInt("OrderID"), 
											 results.getString("UID"), 
											 results.getInt("HotelID"), 
											 results.getDate("CheckIn").toString().replace('-', '/'),
											 results.getDate("CheckOut").toString().replace('-', '/'),
											 SRoom, 
											 DRoom, 
											 QRoom);
			} while(results.next());
			return retList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	/**
	 * Delete the certain Order with OrderID
	 * @param OrderID order ID
	 */
	public static void deleteOrder(int OrderID) {
		try {
			stmt.execute("DELETE FROM Orders WHERE OrderID=" + OrderID + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the next available Order ID
	 * @return next available Order ID
	 */
	public static int getNewOrderID() {	
		try {
			results = stmt.executeQuery("SELECT * FROM Orders ORDER BY OrderID DESC;");
			if (!results.next())	return 0;
			int lastID = results.getInt("OrderID");
			return lastID + 1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
}
