package srimalar.sample.oauth.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TypeParser {
    private static final Logger logger = LoggerFactory.getLogger(TypeParser.class);
    public static int asInt(String value) {
        int result  = 0;
        if(value != null) {
            try {
                result = Integer.parseInt(value);
            } catch (NumberFormatException ex) {
                logger.error("Integer parser error : " + ex.getMessage());
            }
        }
        return result;
    }

    public static long asLong(String value) {
        long result  = 0L;
        if(value != null) {
            try {
                result = Long.parseLong(value);
            } catch (NumberFormatException ex) {
                logger.error("Long parser error : " + ex.getMessage());
            }
        }
        return result;
    }

    public static boolean asBoolean(String value) {
        return value != null && "true".equalsIgnoreCase(value.trim());
    }
}
