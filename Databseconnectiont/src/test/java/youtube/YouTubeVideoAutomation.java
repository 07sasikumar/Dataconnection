package youtube;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
public class YouTubeVideoAutomation {
    public static void main(String[] args) throws Exception {
    
    	FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.youtube.com");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("learn automation videos");
		Thread.sleep(2000);
		driver.findElement(By.id("search-icon-legacy")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@title='Selenium Tutorial Videos - Automation Testing Tool']")).click();
		driver.findElement(By.xpath("//span[@class='style-scope ytd-playlist-renderer']")).click();
		//System.out.println("sasi");
		Thread.sleep(5000);
		File dec = new File("./screenshort/youtube(homepage).png");
		// tack screen shot for the currect page 
	    TakesScreenshot ts = (TakesScreenshot) driver;
		File firstSrc = ts.getScreenshotAs(OutputType.FILE);
		// using file utiles 
		FileUtils.copyFile(firstSrc, dec);

    }
}
