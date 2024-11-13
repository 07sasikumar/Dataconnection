package screenshot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dataconnect {
    public static void main(String[] args) {
        String connectionUrl = "jdbc:sqlserver://DESKTOP-7IA5N30;databaseName=Deliveryforx;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "1234";

        try {
            System.out.println("Creating the connection...");
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connection established successfully!");

            String query = "SELECT [Total], [New], [Accepted], [Dispatched], [Prepareing], [Started], [Arrived], [Delivered], [Failed], [Rejected] FROM [Deliveryforx].[dbo].[admin]";
            System.out.println("Executing query" );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Print the result set
            while (rs.next()) {
                int total = rs.getInt("Total");
                int newOrders = rs.getInt("New");
                int accepted = rs.getInt("Accepted");
                int dispatched = rs.getInt("Dispatched");
                int preparing = rs.getInt("Prepareing");
                int started = rs.getInt("Started");
                int arrived = rs.getInt("Arrived");
                int delivered = rs.getInt("Delivered");
                int failed = rs.getInt("Failed");
                int rejected = rs.getInt("Rejected");

                String output = String.join("\n",
                	    "Total: " + total,
                	    "New: " + newOrders,
                	    "Accepted: " + accepted,
                	    "Dispatched: " + dispatched,
                	    "Preparing: " + preparing,
                	    "Started: " + started,
                	    "Arrived: " + arrived,
                	    "Delivered: " + delivered,
                	    "Failed: " + failed,
                	    "Rejected: " + rejected
                	);
                	System.out.print(output);

            }

            // Close resources
            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
