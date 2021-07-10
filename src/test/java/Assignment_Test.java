import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;

public class Assignment_Test {
    public static WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        String userDir = System.getProperty("user.dir");

        System.setProperty("webdriver.chrome.driver", userDir+"\\chromedriver.exe");
        driver = new ChromeDriver(options);
    }

    @AfterTest
    public void afterTest(){
        //Closes all driver session
        driver.quit();
    }

    @Test
    public void facebookPostStatusTest() {
        String url = "https://www.facebook.com/";
        //Facebook Test Account
        String userName = "open_xhbplqq_user@tfbnw.net";
        String passWord = "test@123";

        driver.get(url);
        driver.manage().window().maximize();

        WebElement emailField = driver.findElement(By.xpath("//*[@name='email']"));
        emailField.sendKeys(userName);

        WebElement passwordField = driver.findElement(By.xpath("//*[@name='pass']"));
        passwordField.sendKeys(passWord);

        WebElement loginButton = driver.findElement(By.xpath("//*[@name='login']"));
        loginButton.click();

        //Waiting for the page to load completely
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement statusMsgClk = driver.findElement(By.xpath("//*[contains(text(),'on your mind')]"));
        statusMsgClk.click();

        //Waiting for the pop up to open for posting status.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='notranslate _5rpu']")));

        //Facebook is not allowing to post same status message more than once.
        Random rand = new Random();
        int n = rand.nextInt(50);
        n += 1;

        WebElement statusMsg = driver.findElement(By.xpath("//*[@class='notranslate _5rpu']"));
        statusMsg.sendKeys("Hello World + " + n + " ");

        WebElement postBtn = driver.findElement(By.xpath("//*[@aria-label='Post']"));
        postBtn.click();
    }

    @Test
    public void walletHubTest(){
        String urlCreateAccount = "https://wallethub.com/join/light";
        String urlInsurance = "http://wallethub.com/profile/test_insurance_company/";

        //Test Email Account
        String email = "xikewek740@eyeremind.com";
        String passWord = "Password@123";

        driver.get(urlCreateAccount);
        driver.manage().window().maximize();//

        WebElement creditScoreUncheck = driver.findElement(By.xpath("//*[@id='get-my-report']"));
        creditScoreUncheck.click();

        WebElement emailField = driver.findElement(By.xpath("//*[@placeholder='Email Address']"));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.xpath("//*[@placeholder='Password']"));
        passwordField.sendKeys(passWord);

        WebElement passwordField1 = driver.findElement(By.xpath("//*[@placeholder='Confirm Password']"));
        passwordField1.sendKeys(passWord);

        WebElement loginButton = driver.findElement(By.xpath("//*[@type='button']//*[contains(text(),'Join')]"));
        loginButton.click();

        //Waiting for the page to load completely
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        //Waiting for password to enter in the field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='password']")));

        WebElement passwordFld = driver.findElement(By.xpath("//*[@placeholder='Password']"));
        passwordFld.sendKeys(passWord);

        WebElement loginBtn = driver.findElement(By.xpath("//*[@type='button']//*[contains(text(),'Login')]"));
        loginBtn.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Navigating to Test Insurance Page
        driver.get(urlInsurance);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        //Mouse Hovering to 4th star and viewing the field in the middle of the page.
        Actions builder = new Actions(driver);
        WebElement fourthStar = driver.findElement(By.xpath("(//*[@class='rvs-svg']//*[@class='rating-box-wrapper']//*[@class='rvs-star-svg'])[4]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fourthStar);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-300)");

        builder.moveToElement(fourthStar).perform();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fourthStar.click();

        //Waiting for dropdown selection field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='wrev-user-input-box input-field progress-indicator-container']//*[@class='textarea wrev-user-input validate']")));

        WebElement selectButton = driver.findElement(By.xpath("//*[@class='dropdown second']//*[@class='dropdown-placeholder']"));
        selectButton.click();

        WebElement selectDropDown = driver.findElement(By.xpath("//*[@class='dropdown-list ng-enter-element']//*[contains(text(),'Health Insurance')]"));
        selectDropDown.click();

        WebElement writeReview = driver.findElement(By.xpath("//*[@class='wrev-user-input-box input-field progress-indicator-container']//*[@class='textarea wrev-user-input validate']"));
        writeReview.sendKeys("This is a Test Review...This is a Test Review...This is a Test Review...This is a Test Review...This is a Test Review...This is a Test Review...This is a Test Review...This is a Test Review...This is a Test Review...");

        WebElement submitButton = driver.findElement(By.xpath("//*[contains(@class,'sbn-action') and contains(text(),'Submit')]"));
        submitButton.click();

        //Waiting for dropdown selection field
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Awesome!')]")));

        driver.get("https://wallethub.com/profile/");
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='inst-rating rvs-plain rvs-svg']")).isDisplayed(),"Review is visible");

        System.out.println("");//
    }
}
