package databaseconnection;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class PDFDownload {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");  // Update with your path

        // Configure Chrome preferences for PDF download
        String downloadFilePath = "C:/Downloads";  // Set your desired download location here
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadFilePath);
        prefs.put("plugins.always_open_pdf_externally", true);  // Disables the PDF viewer, so PDFs are downloaded

        // Set Chrome options with preferences
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        // Initialize WebDriver with Chrome options
        WebDriver driver = new ChromeDriver(options);

        try {
            // Navigate to the URL where PDF can be downloaded
            driver.get("https://example.com/sample.pdf");

            // Additional code to handle download triggers, if required
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
