//Name: Archanaa R Sathyanarayana CWID: A20354423 Name: Mallika KejriwalCWID: A2036504 Name : Srishti Negi CWID : A20351640
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
public class modifySchedule {
	public static final String DBURL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String DBUSER = "mkejriw1";
	public static final String DBPASS = "13nov1989";	
	
	static public void main(String[] args) throws IOException{
		modifySchedule stm = new modifySchedule();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome Manager... Please enter your choice to proceed...");
		System.out.println("1. Insert new schedule for an employee");
		System.out.println("2. Update existing schedule of an employee");
		int choice = Integer.parseInt(br.readLine());
		if (choice == 1) {
			stm.addSchedule();
		} else if(choice == 2) {
			stm.updateSchedule();
		} else {
			System.out.println("Invalid choice please try again");
		}		
	}

	private void addSchedule() {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;
	PreparedStatement preparedStatement3 = null;
	String  staffname = " ",day= " ", staff_id = "";
	String  shift_from = " ",shift_to = " ", role="", theatreid="";
	java.util.Date from, to;
	Object sfrom, sto;
	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");    
	
    try {
	  //Register driver manager
	  DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	
	  //connect to database 
	  connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	  // get inputs from the userID
	  
	  System.out.println("Enter the name of staff for adding his/her schedule");
	  staffname = br.readLine();
	
	  if(staffname.equals("")) {
		  System.out.println("Please enter a name to continue");
		  staffname = br.readLine();
	  }
	
	  String sql = "select STAFF_ID, THEATRE_ID, ROLE from STAFF where STAFFNAME =?";
	  
	  preparedStatement = connection.prepareStatement(sql);
	  preparedStatement.setString(1, staffname);	
	  
	  ResultSet rs = preparedStatement.executeQuery();
	  if(rs.next()) {
		  staff_id = rs.getString(1);		  
		  theatreid = rs.getString(2);
		  role = rs.getString(3);
		  
		  String sql2 = "select * from STAFF_SCHEDULE where STAFF_ID =?";
    	  preparedStatement3 = connection.prepareStatement(sql2);
    	  preparedStatement3.setString(1, staff_id);
    	  ResultSet rs1 = preparedStatement3.executeQuery();
    	  if(rs1.next()){
    		  System.out.println("The staff name you entered already has a schedule.. You can only update the schedule");
    	  } else {
    		  System.out.println("Please enter the day of work");
    		  day = br.readLine();
    		
    		  if(day.equals("")) {
    			  System.out.println("Please enter a valid day to continue");
    			  day = br.readLine();
    		  }
    		  
    		  System.out.println("Please enter shift start time (HH:MI:SS) 24hr format");
    		  shift_from = br.readLine();    		  
    		
    		  if(shift_from.equals("")) {
    			  System.out.println("Please enter a valid start time to continue");
    			  shift_from = br.readLine();
    		  }
    		  from = sdf.parse(shift_from);
    		  
    		  sfrom = new java.sql.Timestamp(from.getTime());    		  
    		  
    		  System.out.println("Please enter the shift end time (HH:MI:SS) 24hr format");
    		  shift_to = br.readLine(); 
    		
    		  if(shift_to.equals("")) {
    			  System.out.println("Please enter a valid end time to continue");
    			  shift_to = br.readLine();
    		  }
    		  
    		  to = sdf.parse(shift_to);	  
    		  sto = new java.sql.Timestamp(to.getTime());    		  
    		  
    		  boolean duplicate = checkForDuplicate(theatreid, role, day, shift_from, shift_to);
    		  if(duplicate) {
    			  System.out.println("Oops!! There is already an employee working on the same day, timeslot, location and for the same role");
    		  } else {
    			  String sql1 = "insert into staff_schedule values (?, ?, ?, ?)";
      			  preparedStatement2 = connection.prepareStatement(sql1);
      		      preparedStatement2.setString(1, staff_id);
      			  preparedStatement2.setString(2, day);
      			  preparedStatement2.setObject(3, sfrom);
      			  preparedStatement2.setObject(4, sto);
      			
      		   	  int intoSchedule = preparedStatement2.executeUpdate();
      		   	  if(intoSchedule > 0)
      			    System.out.println("Schedule for "+staffname+" has been added succesfully");
      		   	  else
      		   		  System.out.println("Something went wrong.. Please try again");
    		  } 		  
    		  
    	  }
		  
	  }	
	  
	}		catch (SQLException se) {
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
	
	private void updateSchedule() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		String  staffname = " ",day= " ", staff_id = "";
		String  shift_from = " ",shift_to = " ",role= "",theatreid= "";
		java.util.Date from, to;
		Object sfrom, sto;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");    
		
	    try {	
	    	  //Register driver manager
	    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    		
	    	  //connect to database 
	    	    connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	    	  // get inputs from the userID
	    		  
	    	  System.out.println("Enter the name of staff for updating his schedule");
	    	  staffname = br.readLine();
	    	  
	    	  if(staffname.equals("")) {
	    		  System.out.println("Please enter a name to continue");
	    		  staffname = br.readLine();
	    	  }
	    	  
	    	  String sql = "select STAFF_ID, THEATRE_ID, ROLE from STAFF where STAFFNAME =?";
	    	  preparedStatement = connection.prepareStatement(sql);
	    	  preparedStatement.setString(1, staffname);	
	    	
	    	  ResultSet rs = preparedStatement.executeQuery();
	    	  if(rs.next()) {	    		  
	    		  staff_id = rs.getString(1);		  
	    		  theatreid = rs.getString(2);
	    		  role = rs.getString(3);
	    		  
	    		  String sql2 = "select STAFF_ID from STAFF_SCHEDULE where STAFF_ID =?";
		    	  preparedStatement2 = connection.prepareStatement(sql2);
		    	  preparedStatement2.setString(1, staff_id);
		    	  
		    	  ResultSet rs1 = preparedStatement2.executeQuery();
		    	  if(rs1.next()) {
		    		  
		    		  System.out.println("Please enter the day of work");
		    		  day = br.readLine();
		    		
		    		  if(day.equals("")) {
		    			  System.out.println("Please enter a valid day to continue");
		    			  day = br.readLine();
		    		  }
		    		  
		    		  System.out.println("Please enter shift start time (HH:MI:SS) 24hr format");
		    		  shift_from = br.readLine();
		    		
		    		  if(shift_from.equals("")) {
		    			  System.out.println("Please enter a valid start time to continue");
		    			  shift_from = br.readLine();
		    		  }
		    		  from = sdf.parse(shift_from);
		    		  
		    		  sfrom = new java.sql.Timestamp(from.getTime());		    		  
		    		  
		    		  System.out.println("Please enter the shift end time (HH:MI:SS) 24hr format");
		    		  shift_to = br.readLine(); 
		    		
		    		  if(shift_to.equals("")) {
		    			  System.out.println("Please enter a valid end time to continue");
		    			  shift_to = br.readLine();
		    		  }
		    		  
		    		  to = sdf.parse(shift_to);	  
		    		  sto = new java.sql.Timestamp(to.getTime());		    		  
		    		  
		    		  boolean duplicate = checkForDuplicate(theatreid, role, day, shift_from, shift_to);
		    		  if(duplicate) {
		    			  System.out.println("Oops!! There is already an employee working on the same day, timeslot, location and for the same role");
		    		  } else {
		    			  String sqlupdate = "update STAFF_SCHEDULE set SHIFT_DAY=?, SHIFT_FROM=?, SHIFT_TO=? where STAFF_ID=?";
				  			preparedStatement3 = connection.prepareStatement(sqlupdate);
				  			preparedStatement3.setString(1, day);
				  			preparedStatement3.setObject(2, sfrom);
				  			preparedStatement3.setObject(3, sto);
				  			preparedStatement3.setString(4, staff_id);
				  			
				  			int intoSchedule = preparedStatement3.executeUpdate();
				  			if(intoSchedule > 0)
				  			       System.out.println("Schedule for "+staffname+" has been updated successfully");
				  			else
				  				System.out.println("Something went wrong.. Please try again");
		    		  }		    		

		    	  } else {
		    		  System.out.println("The current staff name you entered has no schedule currently.. Please add a schedule first");
		    	  }
	    	  }	else {
	    		  System.out.println("The current staff name you entered does not match our records.. Please enter the correct name");
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
                if (preparedStatement2 != null)
                {
                    preparedStatement2.close();
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

	private boolean checkForDuplicate(String theatreid, String role, String day, String shift_from, String shift_to) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {	
	    	  //Register driver manager
	    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    		
	    	  //connect to database 
	    	    connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	    	    String sql = "Select TO_CHAR(SHIFT_FROM, 'HH24:MI:SS'), TO_CHAR(SHIFT_TO, 'HH24:MI:SS'), SHIFT_DAY, STAFF.STAFFNAME from STAFF_SCHEDULE, STAFF"+
	    	                  " where ((STAFF.THEATRE_ID = ? AND SHIFT_DAY = ? AND STAFF.STAFF_ID = STAFF_SCHEDULE.STAFF_ID AND STAFF.ROLE = ?) AND ("+
                              " (TO_CHAR(SHIFT_FROM, 'HH24:MI:SS') <= ? AND TO_CHAR(SHIFT_TO, 'HH24:MI:SS') >= ?)"+
                               " OR(TO_CHAR(SHIFT_FROM, 'HH24:MI:SS') >= ? AND TO_CHAR(SHIFT_TO, 'HH24:MI:SS') <= ?)"+
                               " OR(TO_CHAR(SHIFT_FROM, 'HH24:MI:SS') >= ? AND TO_CHAR(SHIFT_TO, 'HH24:MI:SS') >= ? AND TO_CHAR(SHIFT_FROM, 'HH24:MI:SS') <= ?)"+
                               " OR(TO_CHAR(SHIFT_FROM, 'HH24:MI:SS') <= ? AND TO_CHAR(SHIFT_TO, 'HH24:MI:SS') <= ? AND TO_CHAR(SHIFT_TO, 'HH24:MI:SS') >= ?))"+
                               " )";
	    	    preparedStatement = connection.prepareStatement(sql);
		    	preparedStatement.setString(1, theatreid);	
		    	preparedStatement.setString(2, day);
		    	preparedStatement.setString(3, role);
		    	preparedStatement.setString(4, shift_from);
		    	preparedStatement.setString(5, shift_to);
		    	preparedStatement.setString(6, shift_from);
		    	preparedStatement.setString(7, shift_to);
		    	preparedStatement.setString(8, shift_from);
		    	preparedStatement.setString(9, shift_to);
		    	preparedStatement.setString(10, shift_to);
		    	preparedStatement.setString(11, shift_from);
		    	preparedStatement.setString(12, shift_to);
		    	preparedStatement.setString(13, shift_from);
		    	
		    	ResultSet rs = preparedStatement.executeQuery();
		    	
		    	if(rs.next()) 
		    		result = true;
		    	
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
		return result;
	}
}
