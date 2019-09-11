package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Double.parseDouble;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import static java.lang.Double.parseDouble;
import static org.assertj.core.api.Assertions.*;
import static org.openqa.selenium.By.partialLinkText;

import java.util.Random;
import java.util.concurrent.TimeUnit;

    public class HW {

        protected static WebDriver driver;


        @BeforeMethod

        public void openBrowser(){

            System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

            driver = new ChromeDriver();
            //Maximise the browser window screen
            driver.manage().window().fullscreen();
            //implicity wait for driver object
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            //open website

            driver.get("https://demo.nopcommerce.com/");}

        @AfterMethod()
        public void closeBrowser () {
            //browser close
            driver.quit();
        }

        @Test
        public static void userShouldBeAbleToLogInSuccessfully(){

            driver.findElement(By.xpath("//a[@class='ico-register']")).click();

            //First Name
            driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("vijay");
            //Last name
            driver.findElement(By.id("LastName")).sendKeys("chabra");

            //random email creation
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(100000);

            //Email
            driver.findElement(By.xpath("//input[@id=\"Email\"] ")).sendKeys("chabra"+ randomInt+ "@gmail.com");



            //Password Entry
            driver.findElement(By.xpath("//input[@id=\"Password\"]")).sendKeys("12Danda");

            //Confirm Password
            driver.findElement(By.xpath("//input[@id=\"ConfirmPassword\"]")).sendKeys("12Danda");

            //Click Register button
            driver.findElement(By.name("register-button")).click();

//Registration successful (Test Pass or Fail)

            String expectedMSG = "Your registration completed";
            String actualMSG = driver.findElement(By.xpath("//div[@class=\"result\"]")).getText();

            org.testng.Assert.assertEquals(actualMSG, expectedMSG);

            //Click on Continue
            driver.findElement(By.name("register-continue")).click();
        }

        @Test
        public  void userShouldBeAbleToReferAProductToFriendByEmail () throws InterruptedException {
            driver.findElement(By.xpath("//a[@class='ico-register']")).click();

            //First Name
            driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("vijay");
            //Last name
            driver.findElement(By.id("LastName")).sendKeys("chabra");

            //random email creation
            Random randomGenerator = new Random();
            int randomInt = randomGenerator.nextInt(100000);

            //Email
            driver.findElement(By.xpath("//input[@id=\"Email\"] ")).sendKeys("12Danda"+ randomInt+ "@gmail.com");

            System.out.println("rsahay"+ randomInt+ "@gmail.com");

            //Password Entry
            driver.findElement(By.xpath("//input[@id=\"Password\"]")).sendKeys("12Danda");

            //Confirm Password
            driver.findElement(By.xpath("//input[@id=\"ConfirmPassword\"]")).sendKeys("Champa12");

            //Click Register button
            driver.findElement(By.name("register-button")).click();


            //Click on Continue
            driver.findElement(By.name("register-continue")).click();

            //click on macbook
            driver.findElement(By.xpath("//div/a[@title=\"Show details for Apple MacBook Pro 13-inch\"]")).click();

            Thread.sleep(2000);

            //Email a friend
            driver.findElement(By.xpath("//div[@class='email-a-friend']")).click();
            Thread.sleep(2000);

            //Enter Friends email
            driver.findElement(By.xpath("//input[@class='friend-email'] ")).sendKeys("rsahay600@gmail.com");
            Thread.sleep(3000);


            //Enter your email
            // driver.findElement(By.xpath("//input[@class='your-email']")).sendKeys("nehal.dama27@gmail.com");

            //Personal Message
            driver.findElement(By.xpath(" //textarea[@placeholder='Enter personal message (optional).'] ")).sendKeys("Hey check this out a very good deal I found buy this has great features");

            Thread.sleep(3000);
            //Click send email
            driver.findElement(By.name("send-email")).click();

            Thread.sleep(5000);

            //Test Pass or Fail

            String expectM = "Your message has been sent.";
            String actualM = driver.findElement(By.xpath("//*[@class=\"result\"]")).getText();

            org.testng.Assert.assertEquals(actualM, expectM);

        }

        @Test
        public void userShouldBeNavigateCameraAndPhoto() throws InterruptedException {

            //click on Electronics category
            driver.findElement(By.linkText("Electronics")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//img[@alt=\"Picture for category Camera & photo\"]")).click();

            Thread.sleep(4000);

            String actualM1 = driver.findElement(By.xpath("//h1")).getText();
            String expectedM1 = "Camera & photo";

            org.testng.Assert.assertEquals(actualM1, expectedM1);


        }

        @Test

        public void userShouldBeAbleToFilterJewelleryByPriceRange() {


            //Select Jewellery category
            driver.findElement(partialLinkText("Jewelry")).click();

            //Select Price Range of 700-3000
            driver.findElement(By.xpath("//a[contains(@href,'700-3000')]")).click();

            //compare

            String actualM2 = driver.findElement(By.xpath("//span[@class=\"price actual-price\"]")).getText();
            String expectedM2 = "$700.00 - $3,000.00";
            String s[] = expectedM2.split("-");
            String s4 = s[1].replaceAll(" ", "").replace("$", "").replace("," , "");
            String s3 = s[0].replace("$", "");
            //Convert String to Double
            double p1 = parseDouble(s3);
            double p2 = parseDouble(s4);
            //Converting Actual value to double
            double AM2 = parseDouble(actualM2.replace("$", "").replace(",", ""));

            // Assert.assertTrue(AM2,EM2);

            assertThat(AM2).isBetween(p1, p2);
        }


        @Test
        public void userShouldBeAbleToAdd2ItemsToTheBasket() throws InterruptedException {

            //Go to book category
            driver.findElement(By.linkText("Books")).click();

            //Add first book to cart
            driver.findElement(By.xpath("//input[@type='button' and contains(@onclick, '38/1/1')]")).click();

            Thread.sleep(2000);

            //Add second book to cart
            driver.findElement(By.xpath("//input[@type='button' and contains(@onclick, '37/1/1')]")).click();

            Thread.sleep(7000);

            //Click on shopping cart
            driver.findElement(By.className("ico-cart")).click();



            //Check if test was successful or failed
            String actualu1 = driver.findElement(By.xpath("//span[@class='sku-number' and contains(text(), 'FIRST_PRP')]")).getText();
            String expectedu1 = "FIRST_PRP";
            org.testng.Assert.assertEquals(actualu1, expectedu1);

            String actualb2 = driver.findElement(By.xpath("//span[@class='sku-number' and contains(text(), 'FR_451_RB')]")).getText();
            String expectedb2 = "FR_451_RB";
            Assert.assertEquals(actualb2, expectedb2);

        }

    }


