package org.example;

import javax.security.auth.login.AppConfigurationEntry;
import java.util.HashMap;
import java.util.Map;

class CustomConfiguration
        extends javax.security.auth.login.Configuration {

    private static final Map<String, String> BASIC_JAAS_OPTIONS =
            new HashMap<String, String>();
    private static final Map<String, String> KEYTAB_KERBEROS_OPTIONS =
            new HashMap<String, String>();
    private static final AppConfigurationEntry KEYTAB_KERBEROS_LOGIN =
            new AppConfigurationEntry("com.sun.security.auth.module.Krb5LoginModule",
                    AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
                    KEYTAB_KERBEROS_OPTIONS);
    private static final AppConfigurationEntry[] KEYTAB_KERBEROS_CONF =
            new AppConfigurationEntry[]{KEYTAB_KERBEROS_LOGIN};

    static {
        KEYTAB_KERBEROS_OPTIONS.put("doNotPrompt", "true");
        KEYTAB_KERBEROS_OPTIONS.put("useKeyTab", "true");
        KEYTAB_KERBEROS_OPTIONS.put("storeKey", "true");
        KEYTAB_KERBEROS_OPTIONS.put("refreshKrb5Config", "true");
        KEYTAB_KERBEROS_OPTIONS.putAll(BASIC_JAAS_OPTIONS);
    }

    private String keytabPrincipal;
    private String keytabFile;

    public CustomConfiguration(String keytabPrincipal, String keytabFile) {
        this.keytabPrincipal = keytabPrincipal;
        this.keytabFile = keytabFile;
    }

    private CustomConfiguration() {
    }

    public String getKeytabFile() {
        return keytabFile;
    }

    public String getKeytabPrincipal() {
        return keytabPrincipal;
    }

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String appName) {
        KEYTAB_KERBEROS_OPTIONS.put("keyTab", keytabFile);
        KEYTAB_KERBEROS_OPTIONS.put("principal", keytabPrincipal);
        return KEYTAB_KERBEROS_CONF;
    }
}