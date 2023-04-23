package com.tochka.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

@Feature("Проверка сотрудников")
public class EmployeeVerificationTest extends TestBase{

    private String code = "0000";
    private String errorText = "Мы не нашли этот код: возможно, вы ошиблись при вводе. " +
            "Пожалуйста, попросите у звонящего новый код и попробуйте ещё раз.";

    @DisplayName("Ввод некорректного кода подтверждения")
    @Test
    void employeeVerificationTest(){
        step("Открыть страницу проверки сотрудника", () -> {
            Selenide.open("call-check");
        });
        step("Ввести некорретный код подтверждения", () -> {
            $("#code").sendKeys(code);
        });
        step("Нажать кнопку Подключить", () -> {
            $(byXpath("//*[text()=\"Проверить сотрудника\"]")).click();
        });
        step("Проверить, что появилась ошибка", () -> {
            $$(".code-result").last().shouldBe(Condition.visible).shouldHave(text(errorText));
        });
    }
}
