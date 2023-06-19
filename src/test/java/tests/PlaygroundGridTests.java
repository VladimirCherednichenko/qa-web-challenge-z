package tests;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.GridSizePrompt_PageObject;
import pages.Grid_PageObject;
import pages.WrongNumberAlert_PageObject; 
public class PlaygroundGridTests {
	Grid_PageObject grid;
	GridSizePrompt_PageObject prompt;
	WrongNumberAlert_PageObject alert;
	WebDriver driver;
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		driver = new ChromeDriver();
		grid = new Grid_PageObject(driver);
		prompt = new GridSizePrompt_PageObject(driver);
		alert = new WrongNumberAlert_PageObject(driver);
		
	}
	
	@AfterClass
	public void teardown() {
		
		sleep(5000);
		driver.quit();
	}
	

	@Test 
	public void test1() {
		grid.navigateToDefaultGrid();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.validateGridSize(4);
		grid.clickOnIconWithRowAndColumnNumber(1, 1);
		grid.validateIconWithRowAndColumnNumber(1, 1, true);
		grid.clickOnIconWithRowAndColumnNumber(1, 1);
		grid.validateIconWithRowAndColumnNumber(1, 2, false);

	}
	
	@Test 
	public void test2() {
		for(int gridSize = 3; gridSize < 10; gridSize++) 
		{	
			grid.navigateToDefaultGrid();
			grid.validateGridHaveOnlyInactiveIcons();
			grid.fillOuterPerimetr(4);
			prompt.setGridSizeNumber(gridSize);
			prompt.validateText();
			prompt.pressOk();
			grid.validateGridHaveOnlyInactiveIcons();
			grid.validateGridSize(gridSize);
		}	
	}
	
	@Test 
	public void test3() {
		grid.navigateToDefaultGrid();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.fillOuterPerimetr(4);
		prompt.pressCancel();
		grid.validateGridHaveOnlyInactiveIcons();
	}
	
	@Test 
	public void test4() {
		grid.navigateToDefaultGrid();
		grid.clickOnIconWithRowAndColumnNumber(2, 2);
		grid.fillOuterPerimetr(4);
		grid.clickOnIconWithRowAndColumnNumber(2, 2);
		int newGridSize = 5;
		prompt.setGridSizeNumber(newGridSize);
		prompt.pressOk();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.validateGridSize(newGridSize);
	}

	@Test 
	public void test5() {
		grid.navigateToDefaultGrid();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.fillOuterPerimetr(4);
		for(int gridSize = 3; gridSize < 10; gridSize++) 
		{
			prompt.setGridSizeNumber(gridSize);
			prompt.validateText();
			prompt.pressOk();
			grid.validateGridHaveOnlyInactiveIcons();
			grid.validateGridSize(gridSize);
			grid.fillOuterPerimetr(gridSize);
		}
		prompt.setGridSizeNumber(4);
		prompt.pressOk();
	}
	
	@Test 
	public void test6() {
		
		grid.navigateToDefaultGrid();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.fillOuterPerimetr(4);
		prompt.setGridSizeNumber(10);
		prompt.pressOk();
		alert.validateText();
		alert.pressOk();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.validateGridSize(4);
		grid.navigateToDefaultGrid();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.fillOuterPerimetr(4);
		prompt.setGridSizeNumber(2);
		prompt.pressOk();
		alert.validateText();
		alert.pressOk();
		grid.validateGridHaveOnlyInactiveIcons();
		grid.validateGridSize(4);
		
	}

	
	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
