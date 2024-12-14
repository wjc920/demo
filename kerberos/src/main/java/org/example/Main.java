package org.example;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.security.krb5.realm", "HADOOP.COM");
        System.setProperty("java.security.krb5.kdc", "wslhost");
        LoginContext lc = new LoginContext("SampleClient",
                new Subject(),
                null,
                new CustomConfiguration("hadoop@HADOOP.COM", "D:/hadoop.keytab"));
        lc.login();
        System.out.println(lc.getSubject().toString());
    }
}