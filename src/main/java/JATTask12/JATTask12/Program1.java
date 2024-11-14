package JATTask12.JATTask12;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Program1 {
	
	// Database credentials
    static final String DB_URL = "jdbc:mysql://localhost:3306/employee"; // Replace with your database name
    static final String USER = "root"; // Replace with your username
    static final String PASS = "password goes here"; // Replace with your password

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			
		 // Step 2: JDBC Connection
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Connect to the database
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected to the database!");

            // Step 3: Insert data into the table
            String sql = "INSERT INTO emp (empcode, empname, empage, esalary) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);

            // Sample data to insert
            Object[][] employees = {
                {101, "Jenny", 25, 10000},
                {102, "Jacky", 30, 20000},
                {103, "Joe", 20, 40000},
                {104, "John", 40, 80000},
                {105, "Shameer", 25, 90000}
            };

            // Loop through the sample data and insert each record
            for (Object[] emp : employees) {
                pstmt.setInt(1, (int) emp[0]); // empcode
                pstmt.setString(2, (String) emp[1]); // empname
                pstmt.setInt(3, (int) emp[2]); // empage
                pstmt.setInt(4, (int) emp[3]); // esalary

                // Execute the insert statement
                pstmt.addBatch(); // Add to batch
            }

            // Execute batch insert
            int[] rowsInserted = pstmt.executeBatch();
            System.out.println("Inserted " + rowsInserted.length + " records successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
	}
}



