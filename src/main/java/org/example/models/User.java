package org.example.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter(AccessLevel.PRIVATE)
public class User implements Serializable {
    private boolean isAdmin;
    private String login;
    private String passwordHash;

    public User(String userLogin, String userPassword) {
        login = userLogin;
        String salt = BCrypt.gensalt();
        passwordHash = BCrypt.hashpw(userPassword, salt);
        isAdmin = false;
    }

    public User(String userLogin, String userPassword, String passsword) {
        login = userLogin;
        String salt = BCrypt.gensalt();
        passwordHash = BCrypt.hashpw(userPassword, salt);
        isAdmin = true;
    }

    @Override
    public boolean equals(Object user) {
        User otherUser = (User) user;
        return this.isAdmin == otherUser.isAdmin &&
                Objects.equals(this.login, ((User) user).login) &&
                Objects.equals(this.passwordHash, ((User) user).passwordHash);
    }
}
