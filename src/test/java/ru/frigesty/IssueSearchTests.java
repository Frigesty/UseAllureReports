package ru.frigesty;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.frigesty.pages.WebSteps;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@Feature("Сериал во вкладке")
@Story("Отоброжение сериала в блоке Перестал")
@Owner("Frigesty")
@Severity(SeverityLevel.CRITICAL)
@Link(value = "testing", url = "https://myshows.me/")
public class IssueSearchTests {

    private final WebSteps steps = new WebSteps();

    private static final String USER = "MGW",
                             CONTENT = "Сабрина - маленькая ведьма",
                           SITE_NAME = "https://myshows.me/";

    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    public void clearSearchTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(SITE_NAME);
        $(".text-menu").$(byText("Сообщество")).click();
        $(".SearchField-input").sendKeys(USER);
        $(".SearchField-input").submit();
        $(".UserItemList").$(byText(USER)).click();
        $(".Tabs-container").$(byText("Перестал")).click();
        $(".UserShowsBlock").shouldHave(text(CONTENT));
    }

    @Test
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    public void lambdaStepTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open(SITE_NAME);
        });

        step("Кликаем по кнопке Сообщество", () -> {
            $(".text-menu").$(byText("Сообщество")).click();
        });

        step("Ищем пользователя " + USER + " в поиске друзей", () -> {
            $(".SearchField-input").sendKeys(USER);
            $(".SearchField-input").submit();
        });

        step("Кликаем по пользователю " + USER, () -> {
            $(".UserItemList").$(byText(USER)).click();
        });

        step("Кликаем по кнопке Перестал", () -> {
            $(".Tabs-container").$(byText("Перестал")).click();
        });

        step("Провереяем что в блоке с сериалами с надписью Перестал присутствует сериал " + CONTENT , () -> {
            $(".UserShowsBlock").shouldHave(text(CONTENT));
        });
    }

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    public void testWebSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        steps.openMainPage(SITE_NAME);
        steps.clickOnPageCommunity();
        steps.lookingForUserInFriendSearch(USER);
        steps.clickOnUser(USER);
        steps.clickOnTheStopButton();
        steps.checkThatInTheBlockWithSeriesWithTheInscriptionStoppedThereIsASeries(CONTENT);
    }
}