package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    //у класса LoginPage метод validLogin с типом возвращаемого значения VerificationPage с аргументами authInfo из класса AuthInfo,вложенного в класс DataHelper
    public VerificationPage validLogin(DataHelper.AuthInfo authInfo) {
        $("[data-test-id= login] input").setValue(authInfo.getLogin());
        $("[data-test-id= password] input").setValue(authInfo.getPassword());
        $("[data-test-id= action-login]").click();
        return new VerificationPage();//возвращает новый объект VerificationPage

    }
}
