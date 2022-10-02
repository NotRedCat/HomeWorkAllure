package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepTest {
    private static final String START_PAGE = "https://github.com";
    private static final String REPOSITORY = "google/googletest";
    private static final int ISSUE = 4026;

    @Test
    public void searchIssueTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(START_PAGE);
        $(".header-search-input").setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).shouldHave(Condition.exist);
    }

    @Test
    public void testLambdaTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную стрфницу", () -> {
            open(START_PAGE);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие issue с номером #" + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    void issueTabWebStepsTest() {
        WebSteps steps = new WebSteps();
        steps.openMainPage(START_PAGE);
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldIssueWithNumber(ISSUE);
    }

}