package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class GridSizePrompt_PageObject {
	Alert alert;
	WebDriver driver;
	public GridSizePrompt_PageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void validateText() {
		String expectedText = "Done! Ready for the next try? Give me a size [3-9]:";
		alert = driver.switchTo().alert();
		String actualPromt = alert.getText();
		Assert.assertTrue(actualPromt.contains(expectedText));
	}
	
	public void setGridSizeNumber(int gridSizeNumber) {
		alert = driver.switchTo().alert();
		alert.sendKeys(Integer.toString(gridSizeNumber));
	}
	
	public void pressOk() {
		alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public void pressCancel() {
		alert = driver.switchTo().alert();
		alert.dismiss();
	}
	
	

}
