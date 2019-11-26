package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This POJO handles the Page Objects of the Registration Page
 * All the page objects locators are defined here
 */
public class Register {

    private WebDriver driver;

    /**
     * Input Locators
     */
    private By mobileNumber = By.id("field-Member_CLI");
    private By emailId = By.id("field-Member_Email");
    private By password = By.id("field-MemberPassword");
    private By TenC = By.name("terms");

    /**
     * Submit Button Locator
     */
    private By next = By.name("labyrinth_UserDetails_next");

    /**
     * Warning Notification Locators
     */
    private By pageWarning = By.xpath("/html/body/div[5]/ul/li[2]/div/ul/ul");
    private By mobileWarning = By.xpath("/html/body/div[5]/ul/li[2]/div/form/fieldset[2]/ol/li[1]/div/div/span");

    private By emailWarning = By.xpath("/html/body/div[5]/ul/li[2]/div/form/fieldset[2]/ol/li[3]/div/div/span");
    private By pwdWarning = By.xpath("/html/body/div[5]/ul/li[2]/div/form/fieldset[2]/ol/li[6]/div/div/span");

    /**
     *
     * Specific Warning Locators
     */
    private By mobileTooSmall = By.xpath("//span[@class='error']");
    private By mobileInvalid = By.xpath("//*[@id=\"climsgbox\"]");
    private By emailInvalid = By.xpath("//span[.='Email address is invalid']");
    private By invalidEmail = By.xpath("//*[@id=\"emailmsgbox\"]");
    private By pwdFormat = By.xpath("//span[.='The password does not meet the correct format.']");

    public Register(WebDriver driver) {
        this.driver = driver;
    }

    /**
     *
     * Getters ...
     */
    public String getMobileTooSmall() {
        return driver.findElement(this.mobileTooSmall).getText();
    }

    public String getMobileInvalid() {
        return driver.findElement(this.mobileInvalid).getText();
    }

    public String getEmailInvalid() {
        return driver.findElement(this.emailInvalid).getText();
    }

    public String getInvalidEmailLocator() {
        return driver.findElement(this.invalidEmail).getText();
    }

    public String getPwdFormat() {
        return driver.findElement(this.pwdFormat).getText();
    }

    public By getNext() {
        return next;
    }

    public String getPwdWarning() {
        return driver.findElement(this.pwdWarning).getText();
    }
    public String getEmailWarning() {
        return driver.findElement(this.emailWarning).getText();
    }
    public String getMobileWarning() {
        return driver.findElement(this.mobileWarning).getText();
    }
    public String getPageWarning() {
        return driver.findElement(this.pageWarning).getText();
    }

    /**
     *
     * Setters ...
     */

    public void setMobileNumber(String s) {
        driver.findElement(this.mobileNumber).sendKeys(s);
        driver.findElement(this.mobileNumber).sendKeys(Keys.ENTER);
    }

    public void setEmailId(String s) {
        driver.findElement(this.emailId).sendKeys(s);
        driver.findElement(this.emailId).sendKeys(Keys.ENTER);
    }

    public void setPassword(String s) {
        driver.findElement(this.password).sendKeys(s);
        driver.findElement(this.password).sendKeys(Keys.ENTER);
    }

    public void selectTenC() {
        driver.findElement(this.TenC).click();
    }

    public void submit() {
        WebElement element = driver.findElement(this.next);
        element.click();
    }

}
