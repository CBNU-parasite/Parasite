package org.example;

public class WrongFixedPasswordGenerator implements PasswordGenerator {


    @Override
    public String generatePassword() {
        return "ab"; // 8글자
    }
}
