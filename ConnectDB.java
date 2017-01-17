package database;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import models.*;
public class ConnectDB {
	public static void main(String[] args) throws ParseException {
		SAXloader saXloader = new SAXloader();
//		String JDBC_DRIVER = "com.sql.jdbc.Driver";
//		String DB_URL = "jdbc:mysql://localhost/bookstore";
//		String username = "root";
//		String password = "Green16";
////		UserDTO user = new UserDTO("root", "Kk132459");
//		Connection connection = null;
//		
//		try {
//			connection = DriverManager.getConnection(DB_URL, username, password);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		MYSQLAccess mysqlAccess = new MYSQLAccess();
//		mysqlAccess.init();
		
//		UsersDAO usersTable = new UsersDAO(user.getConnection());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date parsed = format.parse("19910612");
		java.sql.Date sqldate = new java.sql.Date(parsed.getTime());
//		int userID = usersTable
//		java.sql.Date date = new java.sql.Date(100);
//		usersTable.insertRecord("diwei", "freshdvn", "123", "freshdvn@163.com", sqldate, "mmm", "123456789", true);
//		PrivilegesDAO privilegesTable = new PrivilegesDAO(user.getConnection());
//		privilegesTable.insertPriviledge(userID);
		
//		ItemsDAO itemsDAO = new ItemsDAO(user.getConnection());
//		ArrayList<ItemDTO> itemDTOs;
//		itemDTOs = itemsDAO.findByPublication("Pattern Matching in Trees and Nets.");
//		ArrayList<String> authors = new ArrayList<String>();
//		authors.add("chen");
//		authors.add("dd");
//		itemsDAO.insertAuthoredByTable(101, authors);
//		itemsDAO.getItemID("Parallel Integer Sorting and Simulation Amongst CRCW Models.");
//		System.out.println(itemsDAO.getItemID("Parallel Integer Sorting and Simulation Amongst CRCW Models."));
//		itemsDAO.setItemPaused(101, true);
//		System.out.println(itemsDAO.isPaused(101));
		String venue = "kk";
//		Blob blob = null;
//		itemsDAO.insertItemsTable(1, "diwei", "diwei", "1991", venue, 20);
//		itemsDAO.insertRecord(1, "diwei", "diwei", authors, "1991", venue, 20, "diwei", blob);
//		itemDTOs = itemsDAO.findAll();
//		ItemDTO itemDTO = new ItemDTO();
//		itemDTOs = itemsDAO.findByPrice(-1);
		
//		CartItemsDAO cartItemsDAO = new CartItemsDAO(user.getConnection());
//		cartItemsDAO.insertRecord(1, 10);
//		ArrayList<CartItemDTO> cartItemDTOs = new ArrayList<CartItemDTO>();
//		cartItemDTOs = cartItemsDAO.findCartItemsByUserID(1);
//		System.out.println(cartItemDTOs.size());
		
		
		
//		OrderedItemsDAO orderedItemsDAO = new OrderedItemsDAO(user.getConnection());
//		ArrayList<OrderedItemDTO> orderedItemDTOs = new ArrayList<OrderedItemDTO>();
//		Timestamp addedTime = new Timestamp(100);
//		Timestamp changedTime = new Timestamp(200);
//
//		orderedItemsDAO.insertRecord(addedTime, changedTime, 1, 1, 101);
//		orderedItemDTOs = orderedItemsDAO.findAll();
//		System.out.println("size " + orderedItemDTOs.size());
		
//		PageVisitedDAO pageVisitedDAO = new PageVisitedDAO(user.getConnection());
//		pageVisitedDAO.insertRecord("www.qq.com", addedTime, changedTime, 1);
//		ArrayList<PageVisitedDTO> pageVisitedDTOs = new ArrayList<PageVisitedDTO>();
//		pageVisitedDTOs = pageVisitedDAO.selectUserID(1);
//		System.out.println(pageVisitedDTOs.size());
		
//		ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>();
//		UsersDAO usersDAO = new UsersDAO();
//		userDTOs = usersDAO.findAll();
//		usersDAO.setUserEmailAddress("diwei", "@gmail");
//		usersDAO.setUserYearofBirth("diwei", sqldate);
//		usersDAO.setUserAddress("diwei", "mk road");
//		usersDAO.setUserCreditCardNumber("diwei", "000000");
//		usersDAO.setUserConfirmed("diwei", true);
//		usersDAO.setUserBanned("diwei", true);
//		UserDTO userDTO = usersDAO.getUserInfo("diwei");
//		int id = usersDAO.getUserID("diwei");
//		System.out.println(userDTO.getPassword());
		
//		StatesDAO statesDAO = new StatesDAO(user.getConnection());
//		statesDAO.insertRecord("isInCart");
//		StateDTO stateDTO = statesDAO.getState("isInCart");
//		System.out.println(stateDTO.getState());
		
		
//		boolean bl = itemsDAO.insertVenueInTable(1, "mk");
//		itemsDAO.insertAuthoredByTable(1, authors);
//		System.out.println("kk " + itemDTOs.size());
		
//		usersTable.setUserConfirmed("diwei", true);
//		usersTable.setUserBanned("diwei", true);
//		System.out.println(usersTable.isLogin("diwei", "123"));
	}
}
