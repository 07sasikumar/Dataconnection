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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenshotDeliveryforX {
	public static void main(String[] ScreenShotDateandTime) throws IOException {

		System.out.println("process is started");
		DateFormat dateformet = new SimpleDateFormat("dd.mm.yyyy_HH.ss");

		// get current date
		Date date = new Date();
		String date1 = dateformet.format(date);
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://deliveryforx.trymydemo.com/admin-login");// Lanch
		File login = new File("./DeliveryForX/LoginScreenshort" + date1 + ".png");
		TakesScreenshot log = (TakesScreenshot) driver;
		File firstSrc = log.getScreenshotAs(OutputType.FILE);
		// using file tiles
		FileUtils.copyFile(firstSrc, login);
		System.out.println("Takes Screenshot Login page Sucessfully");
		System.out.println("File Name :" + "LoginScreenshort" + date1);
		WebElement login1 = driver.findElement(By.xpath("//input[@type=\"submit\"]"));
		login1.click();

		// DateFormat dateformet = new SimpleDateFormat("dd.mm.yyyy_HH.ss");

		// System.out.println(date1);
		// set file location
		File dec = new File("./screenshort/Deliverforx(homepage)" + date1 + ".png");

		TakesScreenshot ts = (TakesScreenshot) driver;

		File firstSrc1 = ts.getScreenshotAs(OutputType.FILE);
		// using file tiles
		FileUtils.copyFile(firstSrc1, dec);
		// Program successfully completed
		System.out.println("Takes Screenshot Sucessfully");
		// print the file Name
		System.out.println("File Name :" + "Deliverforx(homepage)" + date1);
		// Close the browser
		driver.close();

	}
}
