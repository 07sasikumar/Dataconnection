package databaseconnection;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AadharDownload {
	public static void main(String[] download) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		System.out.println("Start time");
		driver.get("https://myaadhaar.uidai.gov.in/genricDownloadAadhaar/en");
		Thread.sleep(1000*8);

		try {
			// File path for the file to read
			String filePath1 = "D:\\data bask\\Compar\\dataget.txt";

			// Reading the file and storing the lines in a List
			List<String> fileContent = Files.readAllLines(Paths.get(filePath1));

			// Concatenating the content of the file into a single String
			String a = fileContent.stream().collect(Collectors.joining("\n"));

			// Print the content of the file
			System.out.println("Contents of the file:");
			System.out.println(a);
			System.out.println("End time");

			// Enter Aadhar number and CAPTCHA using the file content
			WebElement aadharNum = driver.findElement(By.xpath("//INPUT[@id='']/self::INPUT")); // Update the ID
			aadharNum.sendKeys("777727769901");

			WebElement captcha = driver.findElement(By.xpath("//input[@name=\"captcha\"]")); // Update the ID
			captcha.sendKeys(a);
			
			WebElement sendotp = driver.findElement(By.xpath("//BUTTON[@type='button'][text()='Send OTP']/self::BUTTON"));
			sendotp.click();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Ensure the browser is closed
			//driver.quit();
		}
	}
}
