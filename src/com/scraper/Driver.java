package com.scraper;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Driver extends Controller
{
	static WebDriver driver = new ChromeDriver();
	static Boolean loggedIn = false;
	
	public static void startDriver()
	{
		//System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		driver.get("http://finance.yahoo.com");
		//driver.manage().window().maximize();
		login();
	}
	
	public static void login()
	{
		//StartDriver();
		WebDriverWait waitLogin = new WebDriverWait(driver, 20);
		
		waitLogin.until(presenceOfElementLocated(By.id("header-signin-link")));

		WebElement loginButton = driver.findElement(By.id("header-signin-link"));
        loginButton.click();
        
        WebDriverWait waitUsername = new WebDriverWait(driver, 20);
        waitUsername.until(presenceOfElementLocated(By.id("login-username")));
        
		driver.findElement(By.id("login-username")).sendKeys("meshberge" + Keys.ENTER);
		driver.findElement(By.id("login-username")).sendKeys("meshberge" + Keys.ENTER);
		
		WebDriverWait waitPassword = new WebDriverWait(driver, 20);
		waitLogin.until(presenceOfElementLocated(By.id("login-passwd")));
		
		driver.findElement(By.id("login-passwd")).sendKeys("987612345" + Keys.ENTER);
		//loggedIn = true;
		
		navigateToPortfolioPage();
	}

	public static void navigateToPortfolioPage()
	{
		driver.get("https://finance.yahoo.com/portfolio/p_1/view/v1");
		viewStockPortfolio();
	}
	
	public static void viewStockPortfolio()
	{
		WebDriverWait waitData = new WebDriverWait(driver, 20);
		waitData.until(presenceOfElementLocated(By.xpath("//tr")));
		
		WebElement stockTable = driver.findElement(By.xpath("//tbody"));
		List<WebElement> rows = stockTable.findElements(By.xpath("//tr"));
		
		for(WebElement row: rows)
		{
			List<WebElement> columns = row.findElements(By.xpath("//td"));
			
			for(int column = 0; column < columns.size(); column++)
			{
				String data = columns.get(column).getText();
				System.out.println(data);
			}
			
			//String symbol = cells.toString();
		}
		
		
	}
}
