package screenshot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gmail {

	public static void main(String[] args) throws InterruptedException {
		
		FirefoxDriver driver = new FirefoxDriver();
		
//		driver.get("https://www.google.com/");
//		WebElement gmail = driver.findElement(By.xpath("//A[@class='gb_W'][text()='Gmail']/self::A"));
//		gmail.click();
//		WebElement sign = driver.findElement(By.xpath("//SPAN[@class='button__label'][text()='Sign in']/self::SPAN"));
//	    sign.click();
		driver.get("https://accounts.google.com/v3/signin/identifier?continue=https%3A%2F%2Fmail"
				+ ".google.com%2Fmail%2F&ifkv=AcMMx-cwuC5S-8RGqeCoEHawWyMvjU-tuncSVdN80a136yfZNWapA9sTocES28"
				+ "FWy5V87pTJ3Y6t&rip=1&sacu=1&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&dsh="
				+ "S574294081%3A1731328342085280&ddm=1");
	    WebElement username = driver.findElement(By.xpath("//INPUT[@id='identifierId']/self::INPUT"));
	   username.sendKeys("sanjair.16civil@kongu.edu");
	WebElement nextbutton = driver.findElement(By.xpath("//SPAN[@jsname='V67aGc'][text()='Next']/self::SPAN"));
	nextbutton.click();
	Thread.sleep(3000);
	WebElement password = driver.findElement(By.xpath("//INPUT[@type='password']/self::INPUT"));
	password.sendKeys("Sanja*@2299");
	Thread.sleep(3000);
	WebElement passnext =driver.findElement(By.xpath("//SPAN[@jsname='V67aGc'][text()='Next']/self::SPAN"));
	passnext.click();
	Thread.sleep(10000);
	WebElement comp =driver.findElement(By.xpath("//DIV[@class='T-I T-I-KE L3'][text()='Compose']/self::DIV"));
	comp.click();
	WebElement comp1 =driver.findElement(By.xpath("//DIV[@class='T-I T-I-KE L3'][text()='Compose']/self::DIV"));
	comp1.click();
	Thread.sleep(10000);
	WebElement comto =driver.findElement(By.xpath("//INPUT[@id=':sy']/self::INPUT"));
	comto.click();
//	WebElement compcc =driver.findElement(By.xpath("//DIV[@class='T-I T-I-KE L3'][text()='Compose']/self::DIV"));
//	compcc.click();
//	WebElement compsent =driver.findElement(By.xpath("//DIV[@class='T-I T-I-KE L3'][text()='Compose']/self::DIV"));
//	compsent.click();
	//driver.close();    
	}

}

