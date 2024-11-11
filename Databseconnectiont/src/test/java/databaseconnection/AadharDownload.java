package databaseconnection;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.firefox.FirefoxDriver;

public class AadharDownload {
    public static void main (String[] download) throws InterruptedException {
        FirefoxDriver driver = new FirefoxDriver();
        
        driver.get("https://myaadhaar.uidai.gov.in/genricDownloadAadhaar/en");
        Thread.sleep(3000);
                try {
            // File path for the file to read
            String filePath1 = "D:\\data bask\\Compar\\dataget.txt";
            
            // Reading the file and storing the lines in a List
            List<String> fileContent = Files.readAllLines(Paths.get(filePath1));
            
            // Print the content of the file
            System.out.println("Contents of the file:");
            fileContent.forEach(System.out::println);  // Print each line

            // You can compare records or perform other logic here
            
        } catch (IOException e) {
            e.printStackTrace();
           
        } finally {
            driver.quit(); // Don't forget to close the browser
        }
    }
}
