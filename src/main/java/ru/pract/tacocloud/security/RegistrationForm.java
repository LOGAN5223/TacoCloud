package ru.pract.tacocloud.security;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.pract.tacocloud.entity.UserTable;

@Data
public class RegistrationForm {
    private final String username;
    private final String password;
    private final String fullname;
    private final String street;
    private final String city;
    private final String state;
    private final String zip;
    private final String phone;
    private final String role;

    public UserTable toUser(BCryptPasswordEncoder passwordEncoder){
        return new UserTable(
                username, passwordEncoder.encode(password),
                fullname, street, city, state, zip, phone, "USER"
        );
    }
}
