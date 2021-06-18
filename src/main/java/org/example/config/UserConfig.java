package org.example.config;

public class UserConfig {
    private enum UserType {
        TRANSLATOR,
        CUSTOMER
    }
    public static UserType userType = null;

    public static UserType setCustomer() {
        return userType = UserType.CUSTOMER;
    }
    public static UserType setTranslator() {
        return userType = UserType.TRANSLATOR;
    }
    public static boolean isCustomer() {
        return userType == UserType.CUSTOMER;
    }
}