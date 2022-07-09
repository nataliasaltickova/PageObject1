package ru.netology.web.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {//вложенный класс AuthInfo с двумя переменными
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {//метод getAuthInfo с типом возвращаемого значения AuthInfo
        return new AuthInfo("vasya", "qwerty123");//возвращает новый объект класса AuthInfo
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {//класс VerificationCode с одной переменной
        String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class Card {//класс Card  с тремя переменными
        String number;
        String balance;
        public String id;
    }

    public static Card[] cards = {new Card("5559_0000_0000_0001", "10_000", "92df3f1c-a033-48e6-8390-206f6b1f56c0"),
            new Card("5559_0000_0000_0002", "10_000", "0f3f5c2a-249e-4c3d-8287-09f7a039391d")};

}

