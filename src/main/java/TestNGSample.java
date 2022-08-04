/*package cbt.selenium.testng;
/*
 * Run from the xml suit file
 */

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;

public class TestNGSample {

    private WebDriver driver;

    @BeforeClass
    @org.testng.annotations.Parameters(value={"os", "osVersion", "browser", "browserVersion"})
    public void setUp(String os,String osVersion,String browser,String browserVersion) throws Exception {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("resolution", "2560x1920");
        capability.setCapability("platform", os);
        capability.setCapability("osVersion", osVersion);
        capability.setCapability("browserName", browser);
        capability.setCapability("version", browserVersion);
        capability.setCapability("name", "TestNG-Parallel");
        capability.setCapability("bitbar_apiKey", "");
        var URL = "https://us-west-desktop-hub.bitbar.com/wd/hub";
        driver = new RemoteWebDriver(new URL(URL),capability);
    }

    @Test
    public void testSimple() throws Exception {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://bearstore-testsite.smartbear.com/");
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Page title is: " + driver.getTitle());
        Assert.assertEquals("Shop", driver.getTitle());

        // add items to cart and verify
        // click Sports
        System.out.println("Adding a Football to our cart");
        System.out.println("Clicking Sports");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Show products in category Sports']")));
        WebElement sportsIcon = driver.findElement(By.xpath("//img[@title='Show products in category Sports']"));
        sportsIcon.click();
        // click Soccer
        System.out.println("Clicking Soccer");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Show products in category Soccer']")));
        WebElement soccerIcon = driver.findElement(By.xpath("//img[@title='Show products in category Soccer']"));
        soccerIcon.click();
        // click Nike Strike Football
        System.out.println("Clicking Nike Strike Football");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@title='Show details for Nike Strike Football']")));
        WebElement nikeIcon = driver.findElement(By.xpath("//img[@title='Show details for Nike Strike Football']"));
        nikeIcon.click();
        // click add to cart
        System.out.println("Clicking add to cart");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(., 'Add to cart')]")));
        WebElement cartButton = driver.findElement(By.xpath("//a[contains(., 'Add to cart')]"));
        cartButton.click();
        // go to cart
        System.out.println("Going to cart");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(), 'Go to cart')]")));
        WebElement goToCartButton = driver.findElement(By.xpath("//a[contains(text(), 'Go to cart')]"));
        goToCartButton.click();
        // remove item from cart
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Removing item from cart");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='cart-items']//div//a/i[contains(@class, 'fa')]")));
        WebElement removeButton = driver.findElement(By.xpath("//div[@id='cart-items']//div//a/i[contains(@class, 'fa')]"));
        removeButton.click();
        // fill out the contact us form
        driver.get("https://bearstore-testsite.smartbear.com/contactus");
        // First, find the Contact Us button and click it.
        System.out.println("Filling out the Contact Form and Submitting");
        // click name
        System.out.println("Entering Name");
        WebElement nameElement = driver.findElement(By.xpath("//input[@id=(//label[.='Your name']/@for)]"));
        nameElement.clear();
        nameElement.sendKeys("Joe Smith");
        //click email
        System.out.println("Entering Email");
        WebElement emailElement = driver.findElement(By.xpath("//form/div[2]//input"));
        emailElement.clear();
        emailElement.sendKeys("joe@joe.com");
        // click enquiry
        System.out.println("Entering Enquiry");
        WebElement enquiryElement = driver.findElement(By.xpath("//textarea[@id=(//label[.='Enquiry']/@for)]"));
        enquiryElement.clear();
        enquiryElement.sendKeys("Hello!");
        // click on the Submit button.
        System.out.println("Clicking submit");
        WebElement submitButton = driver.findElement(By.xpath("//button[@name='send-email']"));
        submitButton.click();

        // lets login
        driver.get("https://bearstore-testsite.smartbear.com/");
        TimeUnit.SECONDS.sleep(3);
        // click login
        System.out.println("Clicking Login");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[.='Log in']")));
        WebElement loginButton = driver.findElement(By.xpath("//span[.='Log in']"));
        loginButton.click();
        // enter username
        System.out.println("Entering Username");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#UsernameOrEmail")));
        WebElement usernameElement = driver.findElement(By.cssSelector("#UsernameOrEmail"));
        usernameElement.sendKeys("npaisley");
        // enter password
        System.out.println("Entering Password");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#Password")));
        WebElement passwordElement = driver.findElement(By.cssSelector("#Password"));
        passwordElement.sendKeys("smartbear");
        // click login
        System.out.println("Clicking Login");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='content-center']//button")));
        WebElement loginElement = driver.findElement(By.xpath("//div[@id='content-center']//button"));
        loginElement.click();

    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}