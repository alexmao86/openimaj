package com.github.alexmao86.openimaj;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyAuthenticator extends Authenticator {

    private String userName, password;

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password.toCharArray());
    }

    public ProxyAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}