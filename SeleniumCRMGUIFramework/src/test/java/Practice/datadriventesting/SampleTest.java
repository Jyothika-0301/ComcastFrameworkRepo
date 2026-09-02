package Practice.datadriventesting;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.jdbc.Driver;

public class SampleTest {
	public static void main(String[] args) throws Throwable {
		
		String expectedProjectName ="FB_01";
				boolean flag = false;

		// 1. load / register the database driver
		Driver driverRef = new Driver();               // Driver is class tht is the implimentation of Driver interface
		DriverManager.registerDriver(driverRef);

		// 2. connect to database
		Connection con = DriverManager.getConnection("jdbc:mysql://49.249.29.4:3307/ninza_hrm", "root@%", "root");
		System.out.println("==========Done============");
		
		// 3. create Sql statement
		 Statement stat= con.createStatement();
		 
		 
		// 4. execute select query and get result
		  ResultSet relset=  stat.executeQuery("select * from project");
		  while(relset.next()) {
			  String actProjectNameString=relset.getString(4);
			  if(expectedProjectName.equals(actProjectNameString)) {
				  flag=true;
			  System.out.println(expectedProjectName+"is available==Pass");
		  }
		  }
		  
		  if(flag==false) {
			  System.out.println(expectedProjectName+"is not available==FAIL");
		  }
		// 5. close the connection
		  con.close();

	}
}
