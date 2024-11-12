package screenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenshotDeliveryforX {
	public static void main(String[] ScreenShotDateandTime) throws IOException {

		FirefoxDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://deliveryforx.trymydemo.com/admin-login");// Lanch
		WebElement login = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
		login.click();

		DateFormat dateformet = new SimpleDateFormat("dd.mm.yyyy_HH.ss");

		// get current date
		Date date = new Date();

		String date1 = dateformet.format(date);

		System.out.println(date1);
		// set file location
		File dec =new File ("./screenshort/Deliverforx(homepage)"+date1+".png");
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File firstSrc = ts.getScreenshotAs(OutputType.FILE);
		// using file utiles 
				FileUtils.copyFile(firstSrc, dec);

	}
}
