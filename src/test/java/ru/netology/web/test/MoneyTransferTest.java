package ru.netology.web.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    DashBoardPage dashBoardPage;

    @BeforeEach
    public void setUp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        var loginPage = new LoginPage();//создаем новый объект
        var authInfo = DataHelper.getAuthInfo();//создаем переменную authInfo и инициализируем ее,
        // вызывая метод getAuthInfo
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashBoardPage = verificationPage.validVerify(verificationCode);
    }

    @AfterEach
    public void setDown() {
        var balance = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        if (balance > 10000) {
            var difference = balance - 10000;
            var transferPade = dashBoardPage.transferPage(DataHelper.cards[1]);
            transferPade.transferMoney(DataHelper.cards[0], String.valueOf(difference));

        } else {
            var difference = 10000 - balance;
            var transferPade = dashBoardPage.transferPage(DataHelper.cards[0]);
            transferPade.transferMoney(DataHelper.cards[1], String.valueOf(difference));
        }

        /*var balance0 = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        var balance1 = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());
        assertEquals(10000,balance0);
        assertEquals(10000,balance1);*/
    }

    @Test
    void shouldTransferMoneyFromSecondCardToFirst() {
        var transferPage = dashBoardPage.transferPage(DataHelper.cards[0]);
        transferPage.transferMoney(DataHelper.cards[1], "100");
        var balance0 = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        var balance1 = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());

        assertEquals(10100, balance0);
        assertEquals(9900, balance1);
    }

    @Test
    void shouldTransferMoneyFromOneCardToSecond() {
        var transferPage = dashBoardPage.transferPage(DataHelper.cards[1]);
        transferPage.transferMoney(DataHelper.cards[0], "200");
        var balance0 = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        var balance1 = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());

        assertEquals(9800, balance0);
        assertEquals(10200, balance1);
    }

    @Test
    void shouldNullTransferMoneyFromOneCardToAnother() {
        var transferPage = dashBoardPage.transferPage(DataHelper.cards[0]);
        transferPage.transferMoney(DataHelper.cards[1], "0");
        var balance0 = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        var balance1 = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());

        assertEquals(10000, balance0);
        assertEquals(10000, balance1);

    }

    @Test
    void shouldNegativeTransferMoneyFromOneCardToAnother() {
        var transferPage = dashBoardPage.transferPage(DataHelper.cards[0]);
        transferPage.transferMoney(DataHelper.cards[1], "-1");
        var balance0 = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        var balance1 = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());

        assertEquals(10001, balance0);//ожидаем, что минус валидация не пропустит и перевод будет на 1 руб.
        assertEquals(9999, balance1);

    }

    @Test
    void shouldTransferMoneyFromOneCardToAnotherMoreBalance() {
        var transferPage = dashBoardPage.transferPage(DataHelper.cards[0]);
        transferPage.transferMoney(DataHelper.cards[1], "11000");
        var balance0 = dashBoardPage.getCardBalance(DataHelper.cards[0].getId());
        var balance1 = dashBoardPage.getCardBalance(DataHelper.cards[1].getId());

        assertEquals(10000, balance1);
        assertEquals(10000, balance0);
    }
}




