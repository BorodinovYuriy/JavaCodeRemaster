package org.example.pages.demoblaze;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BeforeLogin  extends BasePage{
    private String username;
    private String password;
    private final By singUpButton;
    private final By singUpUsernameField;
    private final By singUpPasswordField;
    private final By singUpConfirmButton;
    private final By loginButton;
    private final By loginUsername;
    private final By loginPassword;
    private final By loginConfirmButton;
    private final By confirmLogin;

    public BeforeLogin(WebDriver webDriver) {
        super(webDriver);
        this.username = faker.name().username();
        this.password = faker.internet().password();
        this.singUpButton = By.xpath("//a[@id='signin2']/ancestor::li[@class='nav-item']");
        this.singUpUsernameField = By.xpath("//input[@type='text' and @id='sign-username']");
        this.singUpPasswordField = By.xpath("//input[@type='password' and @id='sign-password']");
        this.singUpConfirmButton = By.xpath("//button[text()='Sign up' and @onclick='register()']");
        this.loginButton = By.xpath("//a[@id='login2']/ancestor::li[@class='nav-item']");
        this.loginUsername = By.xpath("//input[@type='text' and @id='loginusername']");
        this.loginPassword = By.xpath("//input[@type='password' and @id='loginpassword']");
        this.loginConfirmButton = By.xpath("//button[text()='Log in' and @onclick='logIn()']");
        this.confirmLogin = By.xpath("//a[@id='nameofuser']");
    }
    public BeforeLogin openPage() {
        super.openPage();
        return this;
    }
    public BeforeLogin waitConfirmLogin(){
        waitAndGetWebElement(confirmLogin);
        return this;
    }
}
