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

public class GetDataInWebPage {
    public static void main(String[] arg) throws InterruptedException {
        FirefoxDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://deliveryforx.trymydemo.com/admin-dashboard");

        WebElement loginbutton = driver.findElement(By.xpath("//input[@type='submit']"));
        loginbutton.click();

        WebElement datepo = driver.findElement(By.xpath("//INPUT[@id='startDatePicker']/self::INPUT"));
        datepo.click();
        WebElement yearsel = driver.findElement(By.xpath("//INPUT[@class='numInput cur-year']/self::INPUT"));
        yearsel.clear();
        yearsel.sendKeys("2023" + Keys.ENTER);
        WebElement datesel = driver.findElement(By.xpath("//SPAN[@class='flatpickr-day'][text()='8']/self::SPAN"));
        datesel.click();
        WebElement searchbutt = driver.findElement(By.xpath("//button[@id='newConsigneeReset']"));
        searchbutt.click();

        // Retrieve values from webpage
        String totalOrdersValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[4]/div/p/span[1]")).getText();
        String newOrdersValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[2]/div/p/span[1]")).getText();
        String acceptedOrdersValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[3]/div/p/span[1]")).getText();
        String dispatchedOrdersValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[4]/div/p/span[1]")).getText();
        String preparingValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[5]/div/p/span[1]")).getText();
        String startedValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[6]/div/p/span[1]")).getText();
        String arrivedValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[7]/div/p/span[1]")).getText();
        String deliveredValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[8]/div/p/span[1]")).getText();
        String failedValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[9]/div/p/span[1]")).getText();
        String rejectedValue = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]/div/div/div[2]/div/div[10]/div/p/span[1]")).getText();

        String connectionUrl = "jdbc:sqlserver://DESKTOP-7IA5N30;databaseName=Deliveryforx;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "1234";

        try {
            Connection connection = DriverManager.getConnection(connectionUrl, username, password);
            String query = "SELECT [Total], [New], [Accepted], [Dispatched], [Prepareing], [Started], [Arrived], [Delivered], [Failed], [Rejected] FROM [Deliveryforx].[dbo].[admin]";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                // Retrieve values from the database
                String dbTotal = String.valueOf(rs.getInt("Total"));
                String dbNew = String.valueOf(rs.getInt("New"));
                String dbAccepted = String.valueOf(rs.getInt("Accepted"));
                String dbDispatched = String.valueOf(rs.getInt("Dispatched"));
                String dbPreparing = String.valueOf(rs.getInt("Prepareing"));
                String dbStarted = String.valueOf(rs.getInt("Started"));
                String dbArrived = String.valueOf(rs.getInt("Arrived"));
                String dbDelivered = String.valueOf(rs.getInt("Delivered"));
                String dbFailed = String.valueOf(rs.getInt("Failed"));
                String dbRejected = String.valueOf(rs.getInt("Rejected"));

                // Compare values and display matched and unmatched results
                System.out.println("\n--- Comparison Results ---");
                
                compareValues("Total Orders", totalOrdersValue, dbTotal);
                compareValues("New Orders", newOrdersValue, dbNew);
                compareValues("Accepted Orders", acceptedOrdersValue, dbAccepted);
                compareValues("Dispatched Orders", dispatchedOrdersValue, dbDispatched);
                compareValues("Preparing", preparingValue, dbPreparing);
                compareValues("Started", startedValue, dbStarted);
                compareValues("Arrived", arrivedValue, dbArrived);
                compareValues("Delivered", deliveredValue, dbDelivered);
                compareValues("Failed", failedValue, dbFailed);
                compareValues("Rejected", rejectedValue, dbRejected);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }

        // Give the wait time the web page
        Thread.sleep(1000);
        // Close the browser
        driver.close();
    }

    // Method to compare and print matched/unmatched values
    public static void compareValues(String label, String webValue, String dbValue) {
        if (webValue.equals(dbValue)) {
            System.out.println(label + " matched: Web = " + webValue + ", DB = " + dbValue);
        } else {
            System.out.println(label + " unmatched: Web = " + webValue + ", DB = " + dbValue);
        }
    }
}
