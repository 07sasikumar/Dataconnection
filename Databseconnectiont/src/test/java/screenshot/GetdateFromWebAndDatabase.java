package screenshot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GetdateFromWebAndDatabase {
	public static void main(String[] arg) throws InterruptedException {
		FirefoxDriver driver = new FirefoxDriver();
		// maximize the browser
		driver.manage().window().maximize();
		// Lanch the delivery fox url
		driver.get("https://deliveryforx.trymydemo.com/admin-dashboard");
		// Login the web page
		WebElement loginbutton = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
		loginbutton.click();

		WebElement datepo = driver.findElement(By.xpath("//INPUT[@id='startDatePicker']/self::INPUT"));
		datepo.click();
		WebElement yearsel = driver.findElement(By.xpath("//INPUT[@class='numInput cur-year']/self::INPUT"));
		yearsel.clear();
		yearsel.sendKeys("2023" + Keys.ENTER);
		WebElement datesel = driver.findElement(By.xpath("//SPAN[@class='flatpickr-day'][text()='8']/self::SPAN"));
		datesel.click();
		WebElement searchbutt = driver.findElement(By.xpath("//button[@id=\"newConsigneeReset\"]"));
		searchbutt.click();
		
		// Print the value
		WebElement totalOrders = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[4]/div/p/span[1]"));
		String totalOrdersValue = totalOrders.getText();
		System.out.println("Total Orders value is: " + totalOrdersValue);

		// Select the value for "New Orders" (144)
		WebElement newOrders = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[2]/div/p/span[1]"));
		String newOrdersValue = newOrders.getText();
		System.out.println("New Orders value is: " + newOrdersValue);

		// Select the value for "Accepted Orders" (9)
		WebElement acceptedOrders = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[3]/div/p/span[1]"));
		String acceptedOrdersValue = acceptedOrders.getText();
		System.out.println("Accepted Orders value is: " + acceptedOrdersValue);

		WebElement Dispatched = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[4]/div/p/span[1]"));
		String Dispatched_OrdersValues = Dispatched.getText();
		System.out.println("Dispatched Orders: " + Dispatched_OrdersValues);

		WebElement Prepareing = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[5]/div/p/span[1]"));
		String Prepareingvalus = Prepareing.getText();
		System.out.println("Prepareing for delivery: " + Prepareingvalus);

		WebElement Started = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[6]/div/p/span[1]"));
		String Startedvalus = Started.getText();
		System.out.println("Started: " + Startedvalus);

//		Arrived
		WebElement Arrived = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[7]/div/p/span[1]"));
		String Arrivedvalus = Arrived.getText();
		System.out.println("Arrived: " + Arrivedvalus);

//		Delivered Orders
		WebElement Delivered = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[8]/div/p/span[1]"));
		String Deliveredvalus = Delivered.getText();
		System.out.println("Delivered order: " + Deliveredvalus);

//		Failed Orders
		WebElement Failed = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[9]/div/p/span[1]"));
		String Failedvalus = Failed.getText();
		System.out.println("Failed Orders: " + Failedvalus);

//		Rejected Orders
		WebElement Rejected = driver.findElement(
				By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[10]/div/p/span[1]"));
		String Rejectedvalus = Rejected.getText();
		System.out.println("Rejected Orders: " + Rejectedvalus);


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
                int newOrders1 = rs.getInt("New");
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
                	    "New: " + newOrders1,
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

		// give the wait time the web page
		Thread.sleep(1000);
		// close the browser
		// driver.close();
	}

}

