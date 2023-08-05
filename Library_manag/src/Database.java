import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Database {
    static String url="jdbc:mysql://localhost:3306/lib";
    static String user="root";
    static String pass="adi1010";
    static int size;
    static Connection con;

    void connectDatabase(){
        try {
            con=DriverManager.getConnection(url,user,pass);
            System.out.println("Connected to database");
        } catch (SQLException e) {
            System.out.println("Error connecting to database");
            throw new RuntimeException(e);
        }
    }

    void addBook(String book,int quan){
        String rec=new String("insert into books values(?,?)");
        PreparedStatement s= null;
        try {
            s = con.prepareStatement(rec);
            s.setString(1,book);
            s.setInt(2,quan);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateBook(String book,int quan){
        String rec=new String("select*from books where name='"+book+"'");
        try {
            Statement s=con.createStatement();
            ResultSet ans=s.executeQuery(rec);
            if(ans.next()){
                String new_rec=new String("update books set quantity="+quan+" where name='"+book+"'");
                s.executeUpdate(new_rec);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean checkBook(String book){
        String rec=new String("select quantity from books where name=(?)");
        try {
            PreparedStatement s=con.prepareStatement(rec);
            s.setString(1,book);
            ResultSet ans=s.executeQuery();
            if(ans.next()) return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    int viewBook(String book){
        String rec=new String("select quantity from books where name=(?)");
        try {
            PreparedStatement s=con.prepareStatement(rec);
            s.setString(1,book);
            ResultSet ans=s.executeQuery();
            ans.next();
            return ans.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ResultSet fetchBook() {

        String rec=new String("select count(*) from books");
        Statement s= null;
        try {
            s = con.createStatement();
            ResultSet ans =s.executeQuery(rec);
            ans.next();
            size=ans.getInt(1);
            rec=new String("select *from books");
            s=con.createStatement();
            ans=s.executeQuery(rec);
            return ans;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean login(String id,String pass,String type){
        String rec=new String ("select* from login where id='"+id+"'"+"and pass='"+pass+"'"+"and type='"+type+"'");
        try {
            Statement s=con.createStatement();
            ResultSet ans= s.executeQuery(rec);
            if(ans.next()){
                return true;
            }
            else return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    boolean login(String id,String type){
        String rec=new String ("select* from login where id='"+id+"' "+"and type='"+type+"'");
        try {
            Statement s=con.createStatement();
            ResultSet ans= s.executeQuery(rec);
            if(ans.next()) return true;
            else  return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void login_create(String id,String pass,String type){
        String rec=new String("insert into login values (?,?,?)");
        try {
            PreparedStatement s=con.prepareStatement(rec);
            s.setString(1,id);
            s.setString(2,pass);
            s.setString(3,type);
            s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    ResultSet issue(){
        try {
            Statement s=con.createStatement();
            ResultSet ans=s.executeQuery("select count(*) from issue");
            ans.next();
            size=ans.getInt(1);
            String rec=new String("select*from issue");
            s=con.createStatement();
            return s.executeQuery(rec);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ResultSet issueUnique(){
        try {
            Statement s=con.createStatement();
            ResultSet ans=s.executeQuery("select count(distinct id) from issue");
            ans.next();
            size=ans.getInt(1);
            String rec=new String("select distinct id from issue");
            s=con.createStatement();
            return s.executeQuery(rec);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ResultSet issue(String id){
        try {
            PreparedStatement s=con.prepareStatement("select count(*) from issue where id=(?)");
            s.setString(1,id);
            ResultSet ans=s.executeQuery();
            ans.next();
            size=ans.getInt(1);
            s=con.prepareStatement("select name,issuedate from issue where id=(?)");
            s.setString(1,id);
            return s.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    int issueAdd(String id,String book,int q){
        String rec=new String("select *from issue where id='"+id+"' and name='"+book+"'");
        try {
            Statement p =con.createStatement();
            ResultSet ans=p.executeQuery(rec);
            if(ans.next()) return -1;
            rec=new String("select *from books where name='"+book+"'");
            ans=p.executeQuery(rec);
            ans.next();
            q=ans.getInt(2);
            if(q==0)    return -2;
            PreparedStatement s=con.prepareStatement("insert into issue values(?,?,?)");
            s.setString(1,id);
            s.setString(2,book);
            Date date=new Date();
            SimpleDateFormat d=new SimpleDateFormat("yyyy/MM/dd");
            String str=d.format(date);
            s.setString(3,str);
            s.executeUpdate();
            s=con.prepareStatement("update books set quantity=(?) where name=(?)");
            s.setString(2,book);
            s.setInt(1,q-1);
            return s.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void issueRemove(String id,String book){
        String rec=new String ("select*from issue where id='"+id+"' and name='"+book+"'");
        try {
            Statement s=con.createStatement();
            if(s.execute(rec)){
                rec=new String("delete from issue where id='"+id+"' and name ='"+book+"'");
                s.executeUpdate(rec);
                rec="select quantity from books where name='"+book+"'";
                ResultSet ans=s.executeQuery(rec);
                ans.next();
                updateBook(book,ans.getInt(1)+1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
