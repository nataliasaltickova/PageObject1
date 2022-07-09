package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashBoardPage;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {//класс VerificationPage с двумя
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }//??

    public DashBoardPage validVerify(DataHelper.VerificationCode verificationCode) {
        //метод validVerify с типом возв.значения DashBoardPage и переменной verificationCode
        //из класса VerificationCode, вложенного в класс DataHelper
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashBoardPage();//возвращает новый объект DashBoardPage
    }
}
