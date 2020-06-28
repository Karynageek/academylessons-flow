package org.academy.end.to.end.tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import org.academy.TestConfigurations;
import org.academy.api.requests.IssueRequests;
import org.academy.utils.web.AbstractWebDriver;
import org.academy.web.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.academy.utils.web.AbstractWebDriver.webDriver;

@Slf4j
public class IssueTest extends AbstractWebDriver {
    private BasePage basePage;
    private LoginPage loginPage;
    private RepositoryPage repositoryPage;
    private IssuesPage issuesPage;
    private NewIssuePage newIssuePage;
    private IssueRequests issueRequests = new IssueRequests();

    public IssueTest() {
        super();
    }

    @BeforeMethod(onlyForGroups = "NewIssue")
    public void precondition() {
        loginPage = new LoginPage(webDriver, true);
        basePage = loginPage.login();
        log.info("Logged in");
    }

    @Test(groups = "NewIssue")
    public void createIssue() {
        repositoryPage = basePage.clickOnShowMoreLink();
        repositoryPage = basePage.goToAcademyLessonsFlowRepositoryLink();
        issuesPage = repositoryPage.clickOnIssueLink();
        newIssuePage = issuesPage.clickOnNewIssue()
                .fillTheTitle("test")
                .clickOnSubmitNewIssueButton();
    }

    @Test(groups = "ListIssue")
    public void getIssueRequests() {
        issueRequests.getIssuesRequest(TestConfigurations.getApiToken(), 200);
        Assert.assertEquals("test", issueRequests.getIssuesTitleRequest());
    }
}
