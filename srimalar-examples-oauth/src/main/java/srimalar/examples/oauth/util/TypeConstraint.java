package srimalar.examples.oauth.util;

import org.springframework.orm.jpa.vendor.Database;

public abstract class TypeConstraint {
    public static Database asDatabase(String name) {
        if(name == null) {
            return Database.DEFAULT;
        }
        String databaseName = name.trim().toUpperCase();
        return switch (databaseName) {
            case "SQL_SERVER" -> Database.SQL_SERVER;
            case "H2" -> Database.H2;
            case "POSTGRESQL" -> Database.POSTGRESQL;
            case "HSQL" -> Database.HSQL;
            case "DB2" -> Database.DB2;
            case "MYSQL" -> Database.MYSQL;
            case "ORACLE" -> Database.ORACLE;
            case "SYBASE" -> Database.SYBASE;
            case "DERBY" -> Database.DERBY;
            case "INFORMIX" -> Database.INFORMIX;
            case "HANA" -> Database.HANA;
            default -> Database.DEFAULT;
        };
    }
}
