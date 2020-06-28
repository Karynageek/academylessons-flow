package org.academy.web.pages;

import lombok.extern.slf4j.Slf4j;
import org.academy.TestConfigurations;
import org.academy.web.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Slf4j
public class IssuesPage extends AbstractPage {
    public IssuesPage(WebDriver webDriver, boolean navigateToPage, String navigateToPageUrl) {
        super(webDriver, navigateToPage, navigateToPageUrl);
    }
    public IssuesPage(WebDriver webDriver) {
        super(webDriver, false, "");
    }

    @FindBy(id = "issue_45_alhonchar_academylessons_link")
    private WebElement issue21420;

    @FindBy(xpath ="//span[contains(text(),'New issue')]")
    private WebElement issueLink;

    public Issue21420Page clickOnIssue21420() {
        issue21420.click();
        return new Issue21420Page(webDriver, true, TestConfigurations.getIssue21420Url());
    }
    public NewIssuePage clickOnNewIssue() {
        issueLink.click();
        return new NewIssuePage(webDriver);
    }
}

