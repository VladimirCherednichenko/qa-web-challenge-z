package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class WrongNumberAlert_PageObject {
	Alert alert;
	WebDriver driver;
	public WrongNumberAlert_PageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void validateText() {
		String expectedText = "Not a good size!";
		alert = driver.switchTo().alert();
		String actualAlertText = alert.getText();
		
		System.out.println("starting validation of alert msg");
		System.out.println("actual alert txt " + actualAlertText);
		System.out.println("expected alert txt " + expectedText);
		
		Assert.assertTrue(actualAlertText.contains(expectedText));
		
	}

	public void pressOk() {
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
}
