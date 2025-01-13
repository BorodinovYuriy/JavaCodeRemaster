package org.example.pages.demoblaze;

import org.example.helpers.Data;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BeforeLogin  extends BasePage{
    private final String username;
    private final String password;

    @FindBy(xpath = "//a[@id='signin2']/ancestor::li[@class='nav-item']")
    private WebElement singUpButton;
    @FindBy(xpath = "//input[@type='text' and @id='sign-username']")
    private WebElement singUpUsernameField;
    @FindBy(xpath = "//input[@type='password' and @id='sign-password']")
    private WebElement singUpPasswordField;
    @FindBy(xpath = "//button[text()='Sign up' and @onclick='register()']")
    private WebElement singUpConfirmButton;

    @FindBy(xpath = "//a[@id='login2']/ancestor::li[@class='nav-item']")
    private WebElement loginButton;
    @FindBy(xpath = "//input[@type='text' and @id='loginusername']")
    private WebElement loginUsername;
    @FindBy(xpath = "//input[@type='password' and @id='loginpassword']")
    private WebElement loginPassword;
    @FindBy(xpath = "//button[text()='Log in' and @onclick='logIn()']")
    private WebElement loginConfirmButton;


    public BeforeLogin(WebDriver webDriver) {
        super(webDriver);
        this.username = Data.makeUsername();
        this.password = Data.makePassword();
        PageFactory.initElements(webDriver, this);
    }
    public void openPage() {
        super.openPage();
    }
    public boolean singUp(){
        wait.until(ExpectedConditions.visibilityOf(singUpButton)).click();
        wait.until(ExpectedConditions.visibilityOf(singUpUsernameField)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(singUpPasswordField)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(singUpConfirmButton)).click();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String massage = alert.getText();
            if(massage.contains("Sign up successful.")){alert.accept();}
            return massage.contains("Sign up successful.");
    }
    public boolean logIn(){
        //sleepSec(1);//todo дождаться исчезновения alert
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
        wait.until(ExpectedConditions.visibilityOf(loginUsername)).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(loginPassword)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOf(loginConfirmButton)).click();
        sleepSec(1);
        return waitConfirm();
    }


}
