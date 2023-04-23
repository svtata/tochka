package com.tochka.tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Feature("Форма заявки на комплаэнс-ассистента")
public class ComplianceAssistantTests extends TestBase {

    String toggle = "common-toggle-large crs-pointer d-flex";
    SelenideElement title = $("#request-form-compliance h2");

    @DisplayName("Проверка тоггла Стать клиентом")
    @Test
    void checkDefaultToggleTest(){
        step("Открыть страницу Бесплатный комплаенс-ассистент", () -> {
            Selenide.open("compliance-assistant/");
        });
        Selenide.sleep(20000);
        step("Проверить тоггл по умолчанию", () -> {
            $("#first").parent().shouldHave(attribute("class", toggle + " left"));
        });
        step("Проверить, что отображается корректный заголовок", () -> {
            $(title).shouldHave(text("Стать клиентом"));
        });
    }

    @DisplayName("Проверка тоггла Я уже клиент")
    @Test
    void checkSwitchToggleTest(){
        step("Открыть страницу Бесплатный комплаенс-ассистент", () -> {
            Selenide.open("compliance-assistant/");
        });
        Selenide.sleep(20000);
        step("Переключить на тогл Я уже клиент", () -> {
            $("#second").click();
        });
        step("Проверить, что тоггл переключен", () -> {
            $("#first").parent().shouldHave(attribute("class", toggle + " right"));
        });
        step("Проверить, что отображается корректный заголовок", () -> {
            $(title).shouldHave(text("Консультация комплаенс-ассистента"));
        });
    }
}
