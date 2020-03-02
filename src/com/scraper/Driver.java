package com.scraper;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Driver
{
	static WebDriver driver = new ChromeDriver();
	static Boolean loggedIn = false;
	
	public static void StartDriver()
	{
		//WebDriver driver = new ChromeDriver();
		driver.get("http://finance.yahoo.com");
		driver.manage().window().maximize();
	}
	
	public static void login()
	{
		StartDriver();
		WebDriverWait waitLogin = new WebDriverWait(driver, 10);
		
		waitLogin.until(presenceOfElementLocated(By.id("header-signin-link")));

		WebElement loginButton = driver.findElement(By.id("header-signin-link"));
        loginButton.click();
        
        //WebDriverWait waitUsername = new WebDriverWait(driver, 10);
        //waitUsername.until(presenceofElementLocated(By.id("login-username")));
        
		driver.findElement(By.id("login-username")).sendKeys("meshberge" + Keys.ENTER);
		
		WebDriverWait waitPassword = new WebDriverWait(driver, 10);
		waitLogin.until(presenceOfElementLocated(By.id("login-passwd")));
		
		driver.findElement(By.id("login-passwd")).sendKeys("987612345" + Keys.ENTER);
		loggedIn = true;
		
		navigateToPortfolioPage();
	}

	public static void navigateToPortfolioPage()
	{
		driver.get("https://finance.yahoo.com/portfolio/p_1/view/v1");
		viewStockPortfolio();
	}
	
	public static void viewStockPortfolio()
	{
		WebDriverWait waitData = new WebDriverWait(driver, 10);
		waitData.until(presenceOfElementLocated(By.xpath("//tr")));
		
		WebElement stockTable = driver.findElement(By.xpath("//tbody"));
	}
}
