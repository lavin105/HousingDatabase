import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Deletion {
    private Scanner scanString=new Scanner(System.in);
    private Scanner scanInt=new Scanner(System.in);
    Selection select=new Selection();
    Connect c =new Connect();
    Connection con=c.getConnection();

    public void deleteForSale() throws SQLException {
        try {
            System.out.println("In order to remove a house for sale please enter the following information.");
                con.setAutoCommit(false);
                System.out.println("Please enter the houses for sale ID");
                while (!scanInt.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID of the house you wish to delete.");
                    scanInt.next();
                }
                int saleID = scanInt.nextInt();
            PreparedStatement p=con.prepareStatement("INSERT INTO DeletedHouseForSale SELECT * FROM forsale WHERE ForSaleID=? AND UserID=?");
            p.setInt(1,saleID);
            p.setInt(2, LoginOrRegister.primary_keys);
            p.executeUpdate();
            con.commit();

            PreparedStatement pz = con.prepareStatement("INSERT INTO logs VALUES(?,?,?)");
            pz.setString(1,"INSERT INTO DeletedHouseForSale SELECT * FROM forsale WHERE ForSaleID="+saleID+" AND UserID="+LoginOrRegister.primary_keys);
            pz.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            pz.setInt(3,LoginOrRegister.primary_keys);pz.executeUpdate();
            con.commit();

            PreparedStatement ps = con.prepareStatement("DELETE FROM forsale WHERE ForSaleID=? AND UserID=?");
                ps.setInt(1, saleID);
                ps.setInt(2, LoginOrRegister.primary_keys);
                ps.executeUpdate();
                con.commit();

            System.out.println("Record officially deleted from our records.");

                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"DELETE FROM forsale WHERE ForSaleID= "+saleID+" AND UserID= "+ LoginOrRegister.primary_keys);
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                con.commit();




        }catch (SQLException e){
            con.rollback();
        }
    }
    public void deleteForRent() throws SQLException {
        try {
            System.out.println("In order to remove a house for sale please enter the following information.");
                con.setAutoCommit(false);
                System.out.println("Please enter the houses for rent ID");
                while (!scanInt.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID of the house you wish to delete.");
                    scanInt.next();
                }
                int saleID = scanInt.nextInt();
                PreparedStatement p=con.prepareStatement("INSERT INTO DeletedHouseForRent SELECT * FROM forrent WHERE ForRentID=? AND UserID=?");
                p.setInt(1,saleID);
                p.setInt(2, LoginOrRegister.primary_keys);
                p.executeUpdate();
            con.commit();

            PreparedStatement pz = con.prepareStatement("INSERT INTO logs VALUES(?,?,?)");
            pz.setString(1,"INSERT INTO DeletedHouseForRent SELECT * FROM forrent WHERE ForSaleID="+saleID+" AND UserID="+LoginOrRegister.primary_keys);
            pz.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            pz.setInt(3,LoginOrRegister.primary_keys);pz.executeUpdate();
            con.commit();

            PreparedStatement ps = con.prepareStatement("DELETE FROM forrent WHERE ForRentID=? AND UserID=?");
                ps.setInt(1, saleID);
                ps.setInt(2, LoginOrRegister.primary_keys);
                ps.executeUpdate();
            con.commit();

            System.out.println("Record officially deleted from our records.");
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"DELETE FROM forrent WHERE ForSaleID= "+saleID+" AND UserID= "+ LoginOrRegister.primary_keys);
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                con.commit();




        }catch (SQLException e){
            con.rollback();
        }
    }


}
