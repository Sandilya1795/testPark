package pages.stepsDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.testng.Assert;
import pages.Register;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This is the main Test Class : Also referred as Step Definitions class,
 * where all the BDD Steps defined in the Cucumber Feature are implemented here.
 */

public class RegistrationPageStepDefinitions {
    private WebDriver driver;
    private Register register;
    private Random random = new Random();

    @Given("^Setup to start browser and the Registration Page$")
    public void setup() {
        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile firefoxProfile = profile.getProfile("default");
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);
        firefoxProfile.setPreference("security.insecure_field_warning.contextual.enabled", false);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(firefoxProfile);

        driver = new FirefoxDriver(options);
        register = new Register(driver);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://myRingGoTest:W4t3Rf4lls@myrgo-preprod.ctt.co.uk/register");
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(),"RingGo Cashless Parking Solution: Register for a RingGo account");
    }

    @Given("^I open Registration Page and enter my details$")
    public void iEnterMyRegistrationDetails() {
        register.setMobileNumber("06" + random.nextInt(90000000));
        register.setEmailId("testPark"+ random.nextInt(900)+"@gmail.com");
        register.setPassword("Zupp@ijs"+ random.nextInt(100));
        register.selectTenC();
    }

    @When("^I submit my registration details$")
    public void iSubmitMyRegistrationDetails() {
        register.submit();
    }

    @Then("^I am registered Successfully$")
    public void iAmRegisteredSuccessfully() {
        Assert.assertEquals(driver.getTitle(), "RingGo Cashless Parking Solution: Register for a RingGo account");
    }

    @Given("^I submit Registration page without entering mandatory details$")
    public void iSubmitWithoutEnteringMandatoryDetails() {
        register.submit();
    }

    @Then("^I am rendered with Page Warnings$")
    public void iAmRenderedWithPageWarnings() {
        Assert.assertEquals(register.getPageWarning(),"There is an error in the form. Please check for details below.");
        Assert.assertEquals(register.getMobileWarning(),"Mobile Number is required");
        Assert.assertEquals(register.getEmailWarning(),"Email address is required");
        Assert.assertEquals(register.getPwdWarning(),"Password is required");
    }

    @When("^I enter invalid (.*) then I am triggered with specific (.*) message$")
    public void iEnterInvalidDetails(String mobile, String expectedMobErr) throws InterruptedException {
        register.setMobileNumber(mobile);
        Thread.sleep(5000);
        register.submit();
        if (mobile.equals("0649123") || mobile.equals("064abc")) {
            Assert.assertEquals(register.getMobileTooSmall(),expectedMobErr);
        } else {
            Thread.sleep(2000);
            Assert.assertEquals(register.getMobileInvalid(),expectedMobErr);
        }

    }

    @Then("^I am triggered with specific Email Error as (.*) and Password Error message as (.*)$")
    public void iAmTriggeredWithRelevantErr(String expectedEmailErr, String expectedPwdErr) throws InterruptedException {
        if (expectedEmailErr.equals("Email is invalid")) {
            Assert.assertEquals(register.getInvalidEmailLocator(),"Email is invalid");
        } else {
            Assert.assertEquals(register.getEmailInvalid(),expectedEmailErr);
        }
        Assert.assertEquals(register.getPwdFormat(), expectedPwdErr);
    }

    @When("^I enter invalid (.*) and (.*)$")
    public void iEnterInvalidEmailIdAndPassword(String invalidEmail, String invalidPwd) throws InterruptedException {
        register.setEmailId(invalidEmail);
        Thread.sleep(5000);
        register.setPassword(invalidPwd);
        register.submit();
    }

    @After
    public void tearDown() {
        this.driver.close();
    }
}
