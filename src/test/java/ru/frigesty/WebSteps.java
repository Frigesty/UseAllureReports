package ru.frigesty;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("");
    }

    @Step("Кликаем по кнопке Сообщество")
    public void clickOnPageCommunity() {
        $(".text-menu").$(byText("Сообщество")).click();
    }

    @Step("Ищем пользователя {user} в поиске друзей")
    public void lookingForUserInFriendSearch(String user) {
        $(".SearchField-input").sendKeys(user);
        $(".SearchField-input").submit();
    }

    @Step("Кликаем по пользователю {user}")
    public void clickOnUser(String user) {
        $(".UserItemList").$(byText(user)).click();
    }

    @Step("Кликаем по кнопке Перестал")
    public void clickOnTheStopButton() {
        $(".Tabs-container").$(byText("Перестал")).click();
    }

    @Step("Провереяем что в блоке с сериалами с надписью Перестал присутствует сериал {content}")
    public void checkThatInTheBlockWithSeriesWithTheInscriptionStoppedThereIsASeries(String content) {
        $(".UserShowsBlock").shouldHave(text(content));
    }
}