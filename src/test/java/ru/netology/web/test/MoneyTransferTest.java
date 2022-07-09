package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromOneCardToAnother() {
        var loginPage = new LoginPage();//создаем новый объект
        var authInfo = DataHelper.getAuthInfo();//создаем переменную authInfo и инициализируем ее,
        // вызывая метод getAuthInfo
        var verificationPage = loginPage.validLogin(authInfo);//
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        dashBoardPage.transferPage(DataHelper.cards[0], DataHelper.cards[1], "100");
    }

    @Test
    void shouldTransferMoneyFromFirstCardToFirst() {
        var loginPage = new LoginPage();//создаем новый объект
        var authInfo = DataHelper.getAuthInfo();//создаем переменную authInfo и инициализируем ее,
        // вызывая метод getAuthInfo
        var verificationPage = loginPage.validLogin(authInfo);//
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        dashBoardPage.transferPage(DataHelper.cards[1], DataHelper.cards[1], "100");
    }

    @Test
    void shouldNullTransferMoneyFromOneCardToAnother() {
        var loginPage = new LoginPage();//создаем новый объект
        var authInfo = DataHelper.getAuthInfo();//создаем переменную authInfo и инициализируем ее,
        // вызывая метод getAuthInfo
        var verificationPage = loginPage.validLogin(authInfo);//
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        dashBoardPage.transferPage(DataHelper.cards[0], DataHelper.cards[1], "0");
        var balance = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());
        assertEquals(9900, balance);
    }

    @Test
    void shouldTransferMoneyFromOneCardToAnotherMoreBalance() {
        var loginPage = new LoginPage();//создаем новый объект
        var authInfo = DataHelper.getAuthInfo();//создаем переменную authInfo и инициализируем ее,
        // вызывая метод getAuthInfo
        var verificationPage = loginPage.validLogin(authInfo);//
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashBoardPage = verificationPage.validVerify(verificationCode);

        dashBoardPage.transferPage(DataHelper.cards[0], DataHelper.cards[1], "11000");
    }
}
