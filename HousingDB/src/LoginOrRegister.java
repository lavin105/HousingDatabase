import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
public class LoginOrRegister {
    private Scanner scan=new Scanner(System.in);
    private Scanner scanNum=new Scanner(System.in);

    private Connect c=new Connect();
     private Connection con=c.getConnection();
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
                System.out.println("UserID: "+primary_keys);
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
            System.out.println("Please enter your administrator password to login.");
            String pass=scan.nextLine();
            CallableStatement ps=con.prepareCall("CALL adminLogin(?,?)");
            ps.setString(1,username);
            ps.setInt(2,pass.hashCode());

            ResultSet rs=ps.executeQuery();
            if (rs.next()) {
                primary_keys = rs.getInt(1);
                System.out.println("AdminID: "+primary_keys);
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
                scanNum.next();
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

            System.out.println("UserID: "+primary_keys);
            CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
            p2.setString(1,"INSERT INTO users(Username,Password, FirstName, LastName, Phone, Email) VALUES ("+username+","+ pass.hashCode()+","+fname+","+lname+","+num+","+email+")");
            p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();

        }catch (Exception e){
            System.out.println("This username already exists please try another username.");
            register();

        }



    }




}
