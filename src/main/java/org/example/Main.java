package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.naukri.com/nlogin/login");

        //login
        driver.findElement(new By.ById("usernameField")).sendKeys("****");
        WebElement passwordField = driver.findElement(new By.ById("passwordField"));
        passwordField.sendKeys("****");
        passwordField.sendKeys(Keys.ENTER);

        pause(2);
        //open resume headline
        driver.get("https://www.naukri.com/mnjuser/profile?id=&orgn=homepage");
        WebElement resumeHeadline = driver.findElement(By.className("resumeHeadline"));
        resumeHeadline.findElement(By.className("edit")).click();
        String resumeHeadlineTxt = driver.findElement(new By.ById("resumeHeadlineTxt")).getText();

        //update space in resume headline
        Boolean isSpacePresent = isTrailingSpacePresent(resumeHeadlineTxt);
        if (isSpacePresent)
            driver.findElement(new By.ById("resumeHeadlineTxt")).sendKeys(Keys.BACK_SPACE);
        else
            driver.findElement(new By.ById("resumeHeadlineTxt")).sendKeys(Keys.SPACE);
        WebElement resumeHeadlineForm = driver.findElement(new By.ByName("resumeHeadlineForm"));
        resumeHeadlineForm.findElement(By.className("waves-effect")).click();

        driver.close();
    }

    private static void pause(int timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds*1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isTrailingSpacePresent(String resumeHeadlineTxt) {
        int length = resumeHeadlineTxt.length();
        if(resumeHeadlineTxt.charAt(length - 1) == ' ')
            return true;
        return false;
    }

    private static String toggleSpaceAtString(String resumeHeadlineTxt) {
        int length = resumeHeadlineTxt.length();
        if(resumeHeadlineTxt.charAt(length - 1) == ' ')
            return resumeHeadlineTxt.trim();
        return resumeHeadlineTxt + " ";
    }
}