package com.tieto.it2014.domain.util;

import com.tieto.it2014.domain.DomainException;
import java.security.MessageDigest;
import org.apache.log4j.Logger;

public class Hash {

    private static final Logger logger = Logger.getLogger(Hash.class);

    private Hash() {
    }

    public static String sha256(String base) {
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

            return hexString.toString();
        } catch (Exception ex) {
            throw new DomainException(ex);
        }
    }

}
