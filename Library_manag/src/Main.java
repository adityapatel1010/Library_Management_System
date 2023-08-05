import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Database d=new Database();
        d.connectDatabase();
        login l=new login();
        l.createlogin();
    }
}