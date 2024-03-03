package org.example.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

@Getter
@Setter(AccessLevel.PRIVATE)
public class User {
    private boolean isAdmin;
    private String login;
    private String passwordHash;

    public User(String userLogin, String userPassword) {
        login = userLogin;
        String salt = BCrypt.gensalt();
        passwordHash = BCrypt.hashpw(userPassword, salt);
    }
}
