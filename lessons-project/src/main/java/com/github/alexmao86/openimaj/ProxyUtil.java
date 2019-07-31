package com.github.alexmao86.openimaj;

import java.net.Authenticator;

public class ProxyUtil {
	private ProxyUtil() {
		
	}
	public static void apply() {
		System.setProperty("http.proxyHost", "rb-proxy-de.bosch.com");
        System.setProperty("http.proxyPort", "8080");
        
    	String username = "mna1szh";//System.getProperty("proxy.authentication.username");
        String password = "Alexmao1986*";//System.getProperty("proxy.authentication.password");

        if (username != null && !username.equals("")) 
            Authenticator.setDefault(new ProxyAuthenticator(username, password));
	}
}