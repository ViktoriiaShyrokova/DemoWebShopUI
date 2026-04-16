package de.demoshop.data;

import de.demoshop.model.User;

public class UserData {

    public static final String NAME = "Jack";
    public static final String LASTNAME = "Sparrow";
    public static final String EMAIL = "jack@sparrow.com";
    public static final String PASSWORD = "Password1!";

    public static User userWithNotExistedInDbEmail() {
        return new User()
                .setEmail("jack@fake.com")
                .setPassword("Password1!");
    }
    public static User userWithWrongPassword() {
        return new User()
                .setEmail("jack@sparrow.com")
                .setPassword("Password1!fake");
    }

}
