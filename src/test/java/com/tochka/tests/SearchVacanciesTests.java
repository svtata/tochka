package com.tochka.tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

@Feature("Вакансии точки")
public class SearchVacanciesTests extends TestBase{

    private String vacancyName = "маркетолог";
    private String city = "Екатеринбург";

    @DisplayName("Поиск вакансий по названию")
    @Test
    void searchVacancyNameTest(){
        step("Открыть страницу с вакансиями", () -> {
            Selenide.open("hr/vacancies/");
        });
        Selenide.sleep(20000);
        step("Ввести в поиск искомое значение", () -> {
            $("[placeholder='Найти вакансию']").sendKeys(vacancyName);
        });
        step("Проверить, что заголовок содержит искомое значение", () -> {
            $("[class^=jobs_jobsListItemName]").shouldHave(text(vacancyName));
        });
    }

    @DisplayName("Поиск вакансий по локации")
    @Test
    void searchVacancyTest(){
        step("Открыть страницу с вакансиями", () -> {
            Selenide.open("hr/vacancies/");
        });
        Selenide.sleep(20000);
        step("Развернуть выпадающий список", () -> {
            $("#city").click();
        });
        step("Выбрать в выпадающем списке город", () -> {
            $("[class^=input-dropdown_item]").$(byText(city)).click();
        });
        step("Проверить, что вакансия содержит выбранный город", () -> {
            $("[class^=jobs_item]").shouldHave(text(city));
        });
    }

}
