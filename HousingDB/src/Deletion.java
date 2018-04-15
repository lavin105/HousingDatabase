import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            System.out.println("You will need your home's for sale ID if you do not know it please enter 1");
            System.out.println("If you do know the ID enter 2 to continue");
            int dontKnow = scanInt.nextInt();
            if (dontKnow == 1) {
                select.selectForSale();

            } else if (dontKnow == 2) {
                con.setAutoCommit(false);
                System.out.println("Please enter the houses for sale ID");
                int saleID = scanInt.nextInt();
                PreparedStatement ps = con.prepareStatement("DELETE FROM forsale WHERE ForSaleID=? AND UserID=?");
                ps.setInt(1, saleID);
                ps.setInt(2, LoginOrRegister.primary_keys);
                ps.executeUpdate();
                con.commit();
                System.out.println("Record officially deleted from our records.");


            }
        }catch (SQLException e){
            con.rollback();
        }
    }
    public void deleteForRent() throws SQLException {
        try {
            System.out.println("In order to remove a house for sale please enter the following information.");
            System.out.println("You will need your home's for rent ID if you do not know it please enter 1");
            System.out.println("If you do know the ID enter 2 to continue");
            int dontKnow = scanInt.nextInt();
            if (dontKnow == 1) {
                select.selectForSale();

            } else if (dontKnow == 2) {
                con.setAutoCommit(false);
                System.out.println("Please enter the houses for rent ID");
                int saleID = scanInt.nextInt();
                PreparedStatement ps = con.prepareStatement("DELETE FROM forrent WHERE ForRentID=? AND UserID=?");
                ps.setInt(1, saleID);
                ps.setInt(2, LoginOrRegister.primary_keys);
                ps.executeUpdate();
                con.commit();
                System.out.println("Record officially deleted from our records.");


            }
        }catch (SQLException e){
            con.rollback();
        }
    }


}
