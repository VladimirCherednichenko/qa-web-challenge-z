package pages;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.util.List;

public class Grid_PageObject {
	
	WebDriver driver;
	
	public Grid_PageObject(WebDriver driver) {
		this.driver = driver;
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
	}
	public void navigateToDefaultGrid() {
		driver.manage().window().maximize();
		Path path = Paths.get("task.html");
		String url = path.toUri().toString();
		driver.get(url);
		System.out.println("Page is opened.");
		
		//Thread.sleep(2000);
		//driver.quit();
	}
	
	public void validateIconWithRowAndColumnNumber(int rowNumber, int columnNumber, Boolean expectedColorRed) {
		WebElement icon = driver.findElement(this.getIconWithRowAndColumnNumber(rowNumber, columnNumber));
		String actualIconColor = icon.getCssValue("color");
		String expectedColor;
		System.out.println("starting validation of icon color");
		System.out.println("actual color for icon with rowNumber " + rowNumber + " columnNumber " + columnNumber + " is " + actualIconColor);
		if (expectedColorRed) 
		{
			expectedColor = "rgba(255, 27, 58, 1)";
			System.out.println("expected color is red or " + expectedColor);
			
		} else {
			expectedColor = "rgba(199, 199, 199, 1)";
			System.out.println("expected color is " + expectedColor);
		}
		
		Assert.assertTrue(actualIconColor.contains(expectedColor));
	}
	
	public Boolean iconIsGrey(WebElement icon) {
		
		String actualIconColor = icon.getCssValue("color");
		String expectedColor;
		System.out.println("starting validation of icon color");
		expectedColor = "rgba(199, 199, 199, 1)";
		System.out.println("expected color is grey or " + expectedColor);
		return actualIconColor.contains(expectedColor);
	}
	
	public void validateGridHaveOnlyInactiveIcons() {
		 List<WebElement> icons = driver.findElements(By.xpath("/html/body//div[@class='mainGrid']//div[@class='icon']"));
		 for(WebElement icon:icons) 
		 {
			 Assert.assertTrue(iconIsGrey(icon));
		 }
	}
	
	public void clickOnIconWithRowAndColumnNumber(int rowNumber, int columnNumber) {
		driver.findElement(this.getIconWithRowAndColumnNumber(rowNumber, columnNumber)).click(); 
	}
	
	public By getIconWithRowAndColumnNumber(int rowNumber, int columnNumber) {
		return By.xpath("/html/body//div[@class='mainGrid']/div[@class='row'][" + rowNumber +"]/div[@class='icon'][" + columnNumber + "]");
	}
	
	public void validateGridSize(int expectedGridSize) {
		int actualNumberIconsOnPage = driver.findElements(By.xpath("/html/body//div[@class='mainGrid']//div[@class='icon']")).size();
		int expectedNumnerIconsOnPage = expectedGridSize * expectedGridSize;
		System.out.println("starting validation of grid size");
		System.out.println("actual number of icons in grid: " + actualNumberIconsOnPage + " expected number of icons in grid:: " + expectedNumnerIconsOnPage);
		Assert.assertTrue(actualNumberIconsOnPage == expectedNumnerIconsOnPage);
		int actualNumberIconsInRow = driver.findElements(By.xpath("/html/body//div[@class='mainGrid']/div[@class='row'][1]//div[@class='icon']")).size();
		System.out.println("actual number of icons in row: " + actualNumberIconsInRow + " expected number of icons in row:: " + expectedGridSize);
		Assert.assertTrue(actualNumberIconsInRow == expectedGridSize);
	}
	
	public void fillOuterPerimetr(int gridSize) { 
		for (int n = 1; (n <= gridSize); n++)
		{
			clickOnIconWithRowAndColumnNumber(1, n);
			
			if (n!=1) {
				clickOnIconWithRowAndColumnNumber(n, gridSize);
			} 
			if (n!=gridSize) {
				clickOnIconWithRowAndColumnNumber(gridSize, n);
			}
			if (n!=1 && n!=gridSize) {
				clickOnIconWithRowAndColumnNumber(n, 1);
			}
			
		}
	}
	
}
