package database;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.print.DocFlavor.STRING;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tomcat.jdbc.pool.DataSource;


public class MYSQLAccess {
	
	
	private DataSource dataSource;
	private Connection connection;
	private PreparedStatement pSItems = null;
	private PreparedStatement pSAuthoredBy = null;
	private PreparedStatement pSAuthors = null;
	private PreparedStatement pSVenueIn = null;
	private PreparedStatement pSVenues = null;
	private PreparedStatement pSEntityStore = null;
	private PreparedStatement pSGraphStore = null;
	private Statement statement = null;
	
	private SAXHandler handler = null;
	private int sizeOfEntryList = 0;
	
	private String insertItems = "insert into Items "
												+ "(PublishType, Title, yearPublished, Booktitle, Pages, Journal, Vol, Num, Month, URL, EE, cdrom, cite, Publisher, Note, CrossRef, ISBN, Series, School, Chapter, Price, IsPaused, VenueName, VenueID) values "
												+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String insertAuthoredBy = "insert into AuthoredBy"
//												+ "(AuthoredByID, AuthorId, ItemID) values "
//												+ "(?,?,?)";
			+ "( AuthorId, ItemID) values "
			+ "(?,?)";
	private String insertAuthors = "insert into Authors"
												+ "(AuthorID, AuthorName) values "
												+ "(?,?)";
	private String insertVenueIn = "insert into VenueIn"
												+ "(VenueInID, VenueID, ItemId) values "
												+ "(?,?,?)";
	private String insertVenues = "insert into Venues"
												+ "(VenueID, VenueName) values "
												+ "(?,?)";
	private String insertEntityStore = "insert into EntityStore(Subject, Predicate, Object) values(?,?,?)";
	private String insertGraphStore = "insert into GraphStore(Subject, Object) values(?,?)";
	
	public void init() {
		try {
			// register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// set up connection to MYSQL
			
			try{
				Context initContext = new InitialContext();
				Context envContext = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/TestDB");
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			System.out.println("Connected database successfully...");
			
			connection = dataSource.getConnection();
			
			pSItems = connection.prepareStatement(insertItems);
			pSAuthoredBy = connection.prepareStatement(insertAuthoredBy);
			pSAuthors = connection.prepareStatement(insertAuthors);
			pSVenueIn = connection.prepareStatement(insertVenueIn);
			pSVenues = connection.prepareStatement(insertVenues);
			statement = connection.createStatement();
			
			pSEntityStore = connection.prepareStatement(insertEntityStore);
			pSEntityStore = connection.prepareStatement(insertEntityStore);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		// parse xml data to MYSQL
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
//			InputStream xmlInput = new FileInputStream("/Users/diwei/Desktop/comp9321/assignment2/dblp.xml");
			InputStream xmlInput = new FileInputStream("/home/nw/cs9321/ass/ass1_extra/ass1_resources/dblp.xml");
			
			SAXParser saxParser = factory.newSAXParser();
			handler = new SAXHandler();
			saxParser.parse(xmlInput, handler);	
			sizeOfEntryList = handler.getEntryList().size();
			System.out.println("++" + sizeOfEntryList);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		int itemID = 1;
		int authoredByID = 1;
		int authorID = 1;
		int venueInID = 1;
		int venueID = 1;
		
		for (Entry entry : handler.getEntryList()) {
			String type = entry.getPubType();
			String publication = entry.getPublication();
			String date = entry.getPubDate();
			String venue = entry.getVenue();
			ArrayList<String> authorsList = entry.getAuthor();
			
			try {
				pSItems.setInt(1, itemID);
				pSItems.setString(2, type);
				pSItems.setString(3, publication);
				pSItems.setString(4, date);
				pSItems.execute();
				
				//
				String subject = "I" + Integer.toString(itemID);
				EntityStoreDAO entityStoreDAO = new EntityStoreDAO();
				entityStoreDAO.insertRecord(subject, "Type", type);
				entityStoreDAO.insertRecord(subject, "Publication", publication);
				entityStoreDAO.insertRecord(subject, "PubDate", date);
				entityStoreDAO.insertRecord(subject, "Price", Integer.toString(-1));
				entityStoreDAO.insertRecord(subject, "IsPaused", Boolean.toString(false));
				entityStoreDAO.insertRecord(subject, "UserID", "NULL");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			// set up AuthoredBy table and Authors table
			for (int i = 0; i < authorsList.size(); ++i) {
				String author = authorsList.get(i);
				String selectAuthors = "select AuthorID, AuthorName from Authors";
				
				try{
					// avoid duplicates
					ResultSet resultSet = statement.executeQuery(selectAuthors);
					int flag = 1;
					while (resultSet.next()) {
						int resultAuthorID = resultSet.getInt("AuthorID");
						String resultAuthorName = resultSet.getString("AuthorName");
						if (author.equals(resultAuthorName)) {
							// no need to insert new author into table Authors
							pSAuthoredBy.setInt(1, authoredByID);
							pSAuthoredBy.setInt(2, resultAuthorID);
							pSAuthoredBy.setInt(3, itemID);
							pSAuthoredBy.execute();
							
							String subject = "I" + Integer.toString(itemID);
							String object = "A" + Integer.toString(authorID);
							GraphStoreDAO graphStoreDAO = new GraphStoreDAO();
							graphStoreDAO.insertRecord(subject, object);
							
							++authoredByID;
							flag = 0;
							break;
						}
					}
					if (flag == 1) {
						pSAuthors.setInt(1, authorID);
						pSAuthors.setString(2, author);
						pSAuthors.execute();
						
						pSAuthoredBy.setInt(1, authoredByID);
						pSAuthoredBy.setInt(2, authorID);
						pSAuthoredBy.setInt(3, itemID);
						pSAuthoredBy.execute();
						
						String subject = "A" + authorID;
						EntityStoreDAO entityStoreDAO = new EntityStoreDAO();
						entityStoreDAO.insertRecord(subject, "AuthorName", author);
						
						String subject2 = "I" + Integer.toString(itemID);
						String object = "A" + Integer.toString(authorID);
						GraphStoreDAO graphStoreDAO = new GraphStoreDAO();
						graphStoreDAO.insertRecord(subject2, object);
						
						++authorID;
						++authoredByID;
					}
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			// set up VenueIn table and Venues table
			String selectVenues = "select VenueID, VenueName from Venues";
			try {
				ResultSet resultSet = statement.executeQuery(selectVenues);
				int flag = 1;
				while (resultSet.next()) {
					int resultVenueID = resultSet.getInt("VenueID");
					String resultVenueName = resultSet.getString("VenueName");
//					System.out.println("111");
//					System.out.println("venue: \n" + venue);
//					System.out.println("resultVenue: \n" + resultVenueName);
					if (venue.equals(resultVenueName)) {
						// no need to insert current venue name
						
//						System.out.println("222");
						pSVenueIn.setInt(1, venueInID);
						pSVenueIn.setInt(2, resultVenueID);
						pSVenueIn.setInt(3, itemID);
						pSVenueIn.execute();
						
						String subject = "I" + Integer.toString(itemID);
						String object = "V" + Integer.toString(venueID);
						GraphStoreDAO graphStoreDAO = new GraphStoreDAO();
						graphStoreDAO.insertRecord(subject, object);
						
						venueInID++;
						flag = 0;
						break;
					}
				}
				if (flag == 1) {
					pSVenues.setInt(1, venueID);
					pSVenues.setString(2, venue);
					pSVenues.execute();
					
					pSVenueIn.setInt(1, venueInID);
					pSVenueIn.setInt(2, venueID);
					pSVenueIn.setInt(3, itemID);
					pSVenueIn.execute();
					
					String subject = "V" + venueID;
					EntityStoreDAO entityStoreDAO = new EntityStoreDAO();
					entityStoreDAO.insertRecord(subject, "VenueName", venue);
					
					String subject2 = "I" + Integer.toString(itemID);
					String object = "V" + Integer.toString(venueID);
					GraphStoreDAO graphStoreDAO = new GraphStoreDAO();
					graphStoreDAO.insertRecord(subject2, object);
					
					++venueID;
					++venueInID;
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			if (itemID == 1000) break;
			itemID++;
		}
	}

	
}
