//Name: Archanaa R Sathyanarayana CWID: A20354423 Name: Mallika KejriwalCWID: A2036504 Name : Srishti Negi CWID : A20351640
import java.io.*;
import java.sql.*;
public class buyTicket {
	public static final String DBURL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String DBUSER = "mkejriw1";
	public static final String DBPASS = "13nov1989";	
	
	static public void main(String[] args) throws IOException{
		buyTicket tckt = new buyTicket();		
		tckt.bookTicket();
	}	
	
	private void bookTicket() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		Connection connection = null;
		PreparedStatement preparedStatementuser = null;
		PreparedStatement preparedStatementmovie = null;
		PreparedStatement preparedStatementbalance = null;
		PreparedStatement preparedStatementupdate = null;
		String userID = "", movie = "";
		int memberid,numtckts = 0;
		float balance = 0.0f, price = 0.0f, tcktcost = 0.0f;
		
		try {
			  //Register driver manager
			  DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			  //connect to database 
			  connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			  // get inputs from the userID
			  
			  System.out.println("Welcome member... Please enter userID to book tickets");
			  userID = br.readLine();
			  
			  String sqluser = "Select MEMBER_ID from MEMBER where USER_ID = ?";
			  preparedStatementuser = connection.prepareStatement(sqluser);
	    	  preparedStatementuser.setString(1, userID);	
	    	  
	    	  ResultSet rsuser = preparedStatementuser.executeQuery();
	    	  if(rsuser.next()) {	    		  
	    		  memberid = rsuser.getInt(1);
	    		  System.out.println("Please enter the movie for which you want to book ticktes");
	    		  movie = br.readLine();
	    		  System.out.println("Please enter the no. of tickets you want to book");
	    		  numtckts = Integer.parseInt(br.readLine());
	    		  
	    		  String sqlmovie = "Select MOVIE.MOVIE_ID, PRICE from MOVIE, MOVIE_SCHEDULE where (TITLE = ? AND MOVIE.MOVIE_ID = MOVIE_SCHEDULE.MOVIE_ID)";
	    		  preparedStatementmovie = connection.prepareStatement(sqlmovie);
		    	  preparedStatementmovie.setString(1, movie);
		    	  
		    	  ResultSet rsmovie = preparedStatementmovie.executeQuery();
		    	  
		    	  if(rsmovie.next()) {
		    		  price = rsmovie.getFloat(2);
		    		  String sqlbalance = "Select BALANCE from CREDIT_CARD where MEMBER_ID = ?";
		    		  preparedStatementbalance = connection.prepareStatement(sqlbalance);
		    		  preparedStatementbalance.setInt(1, memberid);
		    		  
		    		  ResultSet rsbalance = preparedStatementbalance.executeQuery();
		    		  if(rsbalance.next()) {
		    			  balance = rsbalance.getFloat(1);
		    			  tcktcost = price * numtckts;
		    			  System.out.println("Your current card balance: "+balance);
		    			  if(balance >= tcktcost) {
		    				  balance = balance - tcktcost;		    				  
		    				  String sqlupdate = "Update CREDIT_CARD set BALANCE = ? where MEMBER_ID = ?";
		    				  preparedStatementupdate = connection.prepareStatement(sqlupdate);
				    		  preparedStatementupdate.setFloat(1, balance);
				    		  preparedStatementupdate.setInt(2, memberid);
				    		  
				    		  preparedStatementupdate.executeUpdate();
				    		  System.out.println("Congratulations!! Your tickets have been booked successfully");	
				    		  System.out.println("You ticket cost: "+tcktcost+" Card balance after this transaction: "+balance);
		    				  
		    			  } else {
		    				  System.out.println("Oops!! You do not have sufficient balance to make this transaction");
		    			  }
		    		  }
		    	  } else {
		    		  System.out.println("Sorry.. The movie name "+movie+" you entered is not screened in our theatre currently");
		    	  }	    		  
	    		  
	    	  } else {
	    		  System.out.println("The userID you provided does not match our records..");
	    	  }
		}  catch (SQLException se) {
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
		                if (preparedStatementuser != null)
		                {
		                    preparedStatementuser.close();
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
}
		
		
