import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Updating {
    private Scanner scanString=new Scanner(System.in);
    private Scanner scanNum=new Scanner(System.in);
    private Connect c=new Connect();
    private Connection con=c.getConnection();
    public void updateForSale() throws InterruptedException {
        try {
            System.out.println("In order to update your housing record please select what you would like to update.");
            System.out.println("1- Update Record Address");
            System.out.println("2- Update Record City");
            System.out.println("3- Update Record Zipcode");
            System.out.println("4- Update Record size");
            System.out.println("5- Update Record number of bedrooms");
            System.out.println("6- Update Record number of bathrooms");
            System.out.println("7- Update Record the price");
            System.out.println("8- Return to the main menu");

            int whatToUpdate = scanNum.nextInt();
            if (whatToUpdate == 1) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the address please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update your address to.");
                    String address = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Address=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setString(1, address);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The address has been updated to "+ address);
                }catch (SQLException e){
                    con.rollback();
                }
            }else if(whatToUpdate==2){
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the city please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the city to.");
                    String city = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET City=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setString(1, city);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3,updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The city has been updated to "+ city);

                }catch (SQLException e){
                    con.rollback();
                }


            }else if(whatToUpdate==3){
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the zipcode please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the zipcode to.");
                    long zip = scanNum.nextLong();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET ZipCode=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setLong(1, zip);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The zipcode has been updated to "+ zip);

                }catch (SQLException e){
                    con.rollback();
                }

            }else if(whatToUpdate==4){
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the size please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the size to.");
                    double size = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Size=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, size);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The size has been updated to "+ size);

                }catch(SQLException e){
                    con.rollback();
                }

            }else if(whatToUpdate==5){
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the  number of bedrooms please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the number of bedrooms to.");
                    int bed = scanNum.nextInt();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Bedrooms=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setInt(1, bed);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The number of bedrooms has been updated to "+ bed);

                }catch (SQLException e){
                    con.rollback();
                }

            }else if(whatToUpdate==6){
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the number of bathrooms please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the number of bathrooms to.");
                    double bath = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Bathrooms=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, bath);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The number of bathrooms has been updated to "+ bath);

                }catch (SQLException e){
                    con.rollback();
                }

            }else if(whatToUpdate==7){
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the price please enter it below");
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update your price to.");
                    double price = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Price=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, price);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    ps.executeUpdate();
                    con.commit();
                    System.out.println("The price has been updated to "+ price);

                }catch (SQLException e){
                    con.rollback();
                }

            }else if(whatToUpdate==8){
                theSystem s=new theSystem();
                s.run();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
