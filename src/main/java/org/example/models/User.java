package org.example.models;

import lombok.*;

import lombok.extern.jackson.Jacksonized;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements Serializable {

    private boolean isAdmin;
    private String login;
    private transient String password;
    private String passwordHash;

    public User(String userLogin, String userPassword) {
        login = userLogin;
        String salt = BCrypt.gensalt();
        password = userPassword;
        passwordHash = BCrypt.hashpw(userPassword, salt);
        isAdmin = false;
    }
    @Override
    public boolean equals(Object user) {
        User otherUser = (User) user;
        return Objects.equals(login, otherUser.login) &&
                BCrypt.checkpw(password, otherUser.passwordHash);
    }
}
