import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class LoginOrRegister {
    Scanner scan=new Scanner(System.in);
    Scanner scanNum=new Scanner(System.in);

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
    public void Adminlogin(){

        try {
            System.out.println("Please enter your admin username to login.");
            String username=scan.nextLine();
            System.out.println("Please enter your  administrator password to login.");
            String pass=scan.nextLine();
            PreparedStatement ps=con.prepareStatement("SELECT DISTINCT AdminID FROM administrator WHERE AdminUsername=? AND AdminPassword=? ");
            ps.setString(1,username);
            ps.setInt(2,pass.hashCode());

            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                primary_keys = rs.getInt(1);
                System.out.println(primary_keys);
            }else{
                System.out.println("Incorrect password please try again");
                Adminlogin();
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
            System.out.println("Please enter your phone number");
            while (!scanNum.hasNextLong()) {
                System.out.println("That's not a number!");
                System.out.println("Please enter your phone number");
                scanNum.next(); // this is important!
            }
            long num = scanNum.nextLong();
            System.out.println("Please enter your email address");
            String email = scan.nextLine();
            PreparedStatement ps = con.prepareStatement("INSERT INTO users(Username,Password, FirstName, LastName, Phone, Email) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.clearParameters();
            ps.setString(1, username);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, fname);
            ps.setString(4, lname);
            ps.setLong(5, num);
            ps.setString(6, email);

            ps.executeUpdate();
            ResultSet rs_key = ps.getGeneratedKeys();
            if (rs_key.next()) {
                primary_keys = rs_key.getInt(1);
            }

            System.out.println(primary_keys);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("This username already exists please try another username.");
            register();

        }



    }




}
