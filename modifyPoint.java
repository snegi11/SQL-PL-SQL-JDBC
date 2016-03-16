//Name: Archanaa R Sathyanarayana CWID: A20354423 Name: Mallika KejriwalCWID: A2036504 Name : Srishti Negi CWID : A20351640
import java.io.*;
import java.sql.*;
public class modifyPoint {
	public static final String DBURL = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl";
	public static final String DBUSER = "mkejriw1";
	public static final String DBPASS = "13nov1989";
	
	static public void main(String[] args) {
		modifyPoint mem = new modifyPoint();
		mem.addInfo(); 
	}
	private void addInfo() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		PreparedStatement preparedStatement4 = null;
	    String name= "",status="";
	    int customer_id = 0, credit_points=0,member_id=0 ;
	
	    try {
			//Register driver manager
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			//connect to database
			connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			
			// get inputs from the userID
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Welcome owner!! Enter the name of the member");
			name = br.readLine();
			if(name.equals("")) {
				System.out.println("Please enter a member name to continue");
				name = br.readLine();
			}
			
			
		
			String sql = "SELECT CUSTOMER_ID,count(*) from customer where name = ? group by CUSTOMER_ID";

            /*
             * Execute the query
             */
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next())
            {
                customer_id = rs.getInt(1);
                
                String  sql1 = "SELECT credit_points,membership_status,member_id from member where member_id=?";
                preparedStatement2 = connection.prepareStatement(sql1);
                preparedStatement2.setInt(1, customer_id);
                ResultSet rs2 = preparedStatement2.executeQuery();
               
         
	         
            if(rs2.next())
            {
            	
            	status =rs2.getString("membership_status");
            	credit_points = rs2.getInt("credit_points");
            	member_id = rs2.getInt("member_id");
            	String  sql3 = "SELECT CREDIT_POINT from discounts where activity=?";
            	preparedStatement3 = connection.prepareStatement(sql3);
            	preparedStatement3.setString(1, "review");
                ResultSet rs3 = preparedStatement3.executeQuery();
                System.out.println("The membership_status is:" + status);
            	System.out.println("The credit points are:" + credit_points);
            if(rs3.next()){
            	
            	int currpoints= rs3.getInt("CREDIT_POINT");
            	if (status.equals("standard")){
            	currpoints =1*currpoints;	
            	}
            	else if(status.equals("silver")){
            		currpoints =2*currpoints;	
            	}
            	else if(status.equals("gold")){
            		currpoints =5*currpoints;	
            	}
            	else if(status.equals("platinum")){
            		currpoints =10*currpoints;	
            	}
            credit_points = credit_points + currpoints;
            if (credit_points>=600){
            	status = "Platinum";	
            	}
            	else if(credit_points>=400){
                	status = "Gold";
            	}
            	else if(credit_points>=200){
                	status = "Silver";
            	}
            	else status = "Standard";
            	}
              String sql4 = "update member set credit_points=? ,membership_status=? where member_id=?";
              preparedStatement4 = connection.prepareStatement(sql4);
            preparedStatement4.setInt(1,credit_points);
            preparedStatement4.setString(2,status);
            preparedStatement4.setInt(3,customer_id);
            int updated = preparedStatement4.executeUpdate();
            System.out.println("New membership status:"+status);
            System.out.println("New credit point is:"+credit_points);
            
            System.out.println("Number of rows updated="+updated);
            }
            else
            {
            	System.out.println("The name you entered is not a member");
            	}
            }
            
            else{
            	System.out.println("The name you entered does not match our rewards");
            }}
            catch (SQLException se) {
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
	}
}
    		
    	
    
            
            
           