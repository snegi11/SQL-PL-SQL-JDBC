//Name: Archanaa R Sathyanarayana CWID: A20354423 Name: Mallika KejriwalCWID: A2036504 Name : Srishti Negi CWID : A20351640
//import java.util.*;
import java.io.*;
import java.sql.*;
public class registerMember {
	public static final String DBURL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String DBUSER = "mkejriw1";
	public static final String DBPASS = "13nov1989";
	
	static public void main(String[] args) {
		registerMember mem = new registerMember();
		mem.addMemberInfo();
	}
	
	private void addMemberInfo() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		String name = "", userID = "", password = "", phone = "", mail = "", status ="";
        String cardNo = "", expDate = "", cardType = "";
        int creditpts=0, customerID=1001;	

        try {
			//Register driver manager
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			//connect to database
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			
			// get inputs from the userID
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Welcome guest!! Enter your name to begin your enrollment");
			name = br.readLine();
			if(name.equals("")) {
				System.out.println("Please enter a name to continue");
				name = br.readLine();
			}
			System.out.println("Please enter a User ID");
			userID = br.readLine();
			if(userID.equals("")) {
				System.out.println("Please enter a valid userID to continue");
				userID = br.readLine();
			}
			
			
			boolean result = checkUserID(userID);			
			
			while(result) {
				System.out.println("This userID is already taken by another user.. Please give a different value");
				userID = br.readLine();
				result = checkUserID(userID);
			}
			System.out.println("Please enter a password");
			password = br.readLine();
			if(password.equals("")) {
				System.out.println("Please enter a valid password to continue");
				password = br.readLine();
			}
			System.out.println("Please enter your Phone number");
			phone = br.readLine();
			if (phone.equals("")) {
				System.out.println("Please enter a valid phone number  to continue");
				phone = br.readLine();
			}
			System.out.println("Please enter your Email ID");
			mail = br.readLine();
			if (mail.equals("")) {
				System.out.println("Please enter a valid mail to continue");
				mail = br.readLine();
			}
			System.out.println("Please enter your Credit Card Number");
			cardNo = br.readLine();
			if(cardNo.equals("")) {
				System.out.println("Please enter a valid card number  to continue");
				cardNo = br.readLine();
			}
			System.out.println("Please enter your Card Type");
			cardType = br.readLine();
			if(cardType.equals("")) {
				System.out.println("Please enter a valid cardType  to continue");
				cardType = br.readLine();
			}
			System.out.println("Please enter your Card Expiry date");
			expDate = br.readLine();
			if(expDate.equals("")) {
				System.out.println("Please enter a valid expDate  to continue");
				expDate = br.readLine();
			}
			
			status = "Standard";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("Select MAX(customer_id) as maxid from customer");
			if(rs.next()) {
				customerID = rs.getInt("maxid");
				customerID = customerID+1;				
			}
			
			//sql to insert in Customer
			String sql1 = "insert into customer values (?, ?, ?, ?)";
			
			//sql to insert in Member
			String sql2 = "insert into MEMBER values (?, ?, ?, ?, ?)";
			
			//sql to insert in Credit_card
			String sql3 = "insert into credit_card values (?, ?, ?, ?, ?)";
			
			//insert into Customer
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setInt(1, customerID);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, phone);
			preparedStatement.setString(4, mail);
			
			
			preparedStatement.executeUpdate();			
			
			//insert into Member
			
			preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setString(1, userID);
			preparedStatement2.setString(2, password);
			preparedStatement2.setInt(3, creditpts);
			preparedStatement2.setString(4, status);
			preparedStatement2.setInt(5, customerID);			
			
			preparedStatement2.executeUpdate();			
			
			//insert into Credit_card
			preparedStatement3 = connection.prepareStatement(sql3);
			preparedStatement3.setInt(1, customerID);
			preparedStatement3.setString(2, cardNo);
			preparedStatement3.setString(3, expDate);
			preparedStatement3.setString(4, cardType);
			preparedStatement3.setFloat(5, 500);
			
			preparedStatement3.executeUpdate();
			System.out.println("Congratulations!! You have been successfully registered as our member.. Enjoy your association with us!");
			System.out.println("Your Customer id is: "+customerID);
        } catch (SQLException se) {
            /*
             * Handle errors for JDBC
             */
            se.printStackTrace();
        } 
          catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
            try
            {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (preparedStatement2 != null)
                {
                    preparedStatement2.close();
                }
                if (preparedStatement3 != null)
                {
                    preparedStatement3.close();
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }		
	    }
    }
	
	private boolean checkUserID(String userID) {
		boolean found = false;
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
		try {
			
			//Register driver manager
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			//connect to database
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			
			String sql = "Select count(*) from MEMBER where user_id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userID);			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				int rows = rs.getInt(1);
				if(rows > 0)
				   found = true;
			}
			
		} catch (SQLException se) {
            /*
             * Handle errors for JDBC
             */
            se.printStackTrace();
        } 
		  catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * finally block used to close resources
             */
            try
            {
                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }
            try
            {
                if (connection != null)
                {
                    connection.close();
                }
            }
            catch (SQLException sqlException)
            {
                sqlException.printStackTrace();
            }		
	    
        }	
		return found;
	}
}