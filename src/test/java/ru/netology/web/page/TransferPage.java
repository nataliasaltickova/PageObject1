package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.Keys;
import ru.netology.web.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.Keys.SHIFT;

public class TransferPage {

    public void transferMoney(DataHelper.Card from, String money) {
        //метод transferPage без возвр.значения
        $("span[data-test-id= amount] input").click();
        $("span[data-test-id= amount] input").sendKeys(Keys.chord(SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("span[data-test-id= amount] input").setValue(money);
        $("[data-test-id=\"from\"] input").click();
        $("[data-test-id=\"from\"] input").sendKeys(Keys.END,Keys.chord(SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=\"from\"] input").setValue(from.getNumber());
        $("[data-test-id= action-transfer]").click();
    }
}
