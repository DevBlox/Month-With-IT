package com.tieto.it2014.domain.util;

import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class Hash {

    private static final Logger LOGGER = Logger.getLogger(Hash.class);

    private Hash() {
    }

    public static String sha256(String base) {
        String result = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            result = hexString.toString();
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return result;
    }
}
