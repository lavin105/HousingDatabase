import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class LoginOrRegister {
    Scanner scan=new Scanner(System.in);
     Connect c=new Connect();
     Connection con=c.getConnection();
     public static int primary_keys;



    public void login(){

        try {
            System.out.println("Please enter your username to login.");
            String username=scan.nextLine();
            System.out.println("Please enter your password to login.");
            String pass=scan.nextLine();
            PreparedStatement ps=con.prepareStatement("SELECT DISTINCT UserID FROM users WHERE username=? AND Password=? ");
            ps.setString(1,username);
            ps.setInt(2,pass.hashCode());

            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                primary_keys = rs.getInt(1);
                System.out.println(primary_keys);
            }else{
                System.out.println("Incorrect password please try again");
                login();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void register(){
        try {
            System.out.println("Please enter your desired username");
            String username = scan.nextLine();
            System.out.println("Please enter your desired password");
            String pass = scan.nextLine();
            System.out.println("Please enter your first name.");
            String fname = scan.nextLine();
            System.out.println("Please enter your last name");
            String lname = scan.nextLine();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(Username,Password, FirstName, LastName) VALUES (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.clearParameters();
            ps.setString(1, username);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, fname);
            ps.setString(4, lname);
            ps.executeUpdate();
            ResultSet rs_key = ps.getGeneratedKeys();
            if (rs_key.next()) {
                primary_keys = rs_key.getInt(1);
            }

            System.out.println(primary_keys);

        }catch (Exception e){
            System.out.println("This username already exists please try another username.");
            register();

        }



    }




}
