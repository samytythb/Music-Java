package Models;

public class Dao {
    private static  String DB_URl="jdbc:sqlserver://localhost:1433; databaseName=Music; encrypt=true; trustServerCertificate=true";
    private static String DB_USER="leduc";
    private static  String DB_PASSWORD="leduc2821";

    public static String getDB_URl() {
        return DB_URl;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
}
