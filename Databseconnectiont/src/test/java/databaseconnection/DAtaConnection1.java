package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAtaConnection1 {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://DESKTOP-7IA5N30;databaseName=BPS;encrypt=true;trustServerCertificate=true;";
        String user = "sa";
        String password = "1234";
        Connection con = null;

        try {
            System.out.println("Creating Connection.");
            con = DriverManager.getConnection(connectionUrl, user, password);
            System.out.println("Connection created.");

            // Define the SQL query to retrieve the top 1000 rows from the Aadhar table
            String query = "SELECT TOP (1000) [AadharId], [FirstName], [LastName], [MobileNumber], [BioMetric], " +
                           "[Gender], [Status], [Address], [Pincode], [CreatedDate], [LastModifiedDate], [LastModifiedBy] " +
                           "FROM [BPS].[dbo].[Aadhar]";

            // Create a Statement object to execute the query
            Statement stmt = con.createStatement();

            // Execute the query and obtain the result set
            ResultSet rs = stmt.executeQuery(query);

            // Process and display the result set
            while (rs.next()) {
                // Retrieve data by column name
                int aadharId = rs.getInt("AadharId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String mobileNumber = rs.getString("MobileNumber");
                String bioMetric = rs.getString("BioMetric");
                String gender = rs.getString("Gender");
                String status = rs.getString("Status");
                String address = rs.getString("Address");
                String pincode = rs.getString("Pincode");
                String createdDate = rs.getString("CreatedDate");
                String lastModifiedDate = rs.getString("LastModifiedDate");
                String lastModifiedBy = rs.getString("LastModifiedBy");

                // Display the data in the console
                System.out.println("AadharId: " + aadharId + ", FirstName: " + firstName + ", LastName: " + lastName +
                                   ", MobileNumber: " + mobileNumber + ", BioMetric: " + bioMetric + ", Gender: " + gender +
                                   ", Status: " + status + ", Address: " + address + ", Pincode: " + pincode +
                                   ", CreatedDate: " + createdDate + ", LastModifiedDate: " + lastModifiedDate +
                                   ", LastModifiedBy: " + lastModifiedBy);
            }

            // Close the ResultSet and Statement
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
