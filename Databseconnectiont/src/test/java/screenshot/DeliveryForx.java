package screenshot;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DeliveryForx {
	public static void main(String[] screenshort) throws IOException {
		FirefoxDriver driver = new FirefoxDriver();
// open the browser 
		driver.get("https://deliveryforx.trymydemo.com/admin-login");
// get the location foe user name and password 
		WebElement username = driver.findElement(By.xpath("//*[@id=\"adm_email\"]"));
		WebElement pass = driver.findElement(By.xpath("//*[@id=\"adm_password\"]"));
// clear the username and password 
		username.clear();
		pass.clear();
// enter the user name and password 
		username.sendKeys("admin@mailinator.com");
		pass.sendKeys("123456");
// get the location of login page 
		WebElement login = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/div/form/input[3]"));
// login page 
		login.click();
//set the file location 
		File dec = new File("./screenshort/Deliverforx(homepage).png");
// tack screen shot for the currect page 
		TakesScreenshot ts = (TakesScreenshot) driver;

		File firstSrc = ts.getScreenshotAs(OutputType.FILE);
// using file utiles 
		FileUtils.copyFile(firstSrc, dec);

// page close 
		//driver.close();
	}
}
