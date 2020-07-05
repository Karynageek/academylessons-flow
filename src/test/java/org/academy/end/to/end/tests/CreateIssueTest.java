package org.academy.end.to.end.tests;

import lombok.extern.slf4j.Slf4j;

import org.academy.TestConfigurations;
import org.academy.api.requests.IssueRequests;
import org.academy.utils.web.AbstractWebDriver;
import org.academy.web.pages.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class CreateIssueTest extends AbstractWebDriver {
    private BasePage basePage;
    private LoginPage loginPage;
    private RepositoryPage repositoryPage;
    private IssuesPage issuesPage;
    private NewIssuePage newIssuePage;
    private IssueRequests issueRequests = new IssueRequests();
    private String generatedString;

    public CreateIssueTest() {
        super();
    }

    @BeforeMethod(onlyForGroups = "NewIssue")
    public void precondition() {
        loginPage = new LoginPage(webDriver, true);
        basePage = loginPage.login();
        log.info("Logged in");
    }

    @Test(groups = {"regression","NewIssue"})
    public void createIssue() {
        repositoryPage = basePage.clickOnShowMoreLink();
        repositoryPage = basePage.goToAcademyLessonsFlowRepositoryLink();
        issuesPage = repositoryPage.clickOnIssueLink();
        generatedString = RandomStringUtils.random(10, true, false);
        newIssuePage = issuesPage.clickOnNewIssue()
                .fillTheTitle(generatedString)
                .clickOnSubmitNewIssueButton();
    }

    @Test(groups = {"regression","ListIssue"})
    public void getIssueRequests() {
        issueRequests.getIssuesRequest(TestConfigurations.getApiToken(), 200);
        Assert.assertEquals(generatedString, issueRequests.getIssuesTitleRequest());
        log.info("Title issue " + generatedString +" matches "+ issueRequests.getIssuesTitleRequest());
    }
}
