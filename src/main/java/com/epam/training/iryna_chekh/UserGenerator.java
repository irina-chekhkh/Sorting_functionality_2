package com.epam.training.iryna_chekh;

import com.epam.training.iryna_chekh.user.User;

public class UserGenerator {
    public static final String USER_NAME = "user.name";
    public static final String USER_PASSWORD = "user.password";

    public static User createUser() {
        String userName = ResourcesDataReader.getData(USER_NAME);
        String password = ResourcesDataReader.getData(USER_PASSWORD);
        return new User(userName, password);
    }
}
