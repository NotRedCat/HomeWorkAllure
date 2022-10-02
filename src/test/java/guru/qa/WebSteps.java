package guru.qa;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу {startPage}")
    public void openMainPage(String startPage) {
        open(startPage);
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Кликаем по ссылке репозитория {repo}")

    public void clickOnRepositoryLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Открывает таб Issues")
    public void openIssuesTab() {
        $("#issues-tab").click();

    }

    @Step("Ищем Issue с номером #{issue}")
    public void shouldIssueWithNumber(int issue) {
        $(withText("#" + issue)).should(Condition.exist);

    }
}
