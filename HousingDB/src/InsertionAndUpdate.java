import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertionAndUpdate{
   private Scanner scanString=new Scanner(System.in);
   private Scanner scanNum=new Scanner(System.in);
   private Connect c=new Connect();
   private Connection con=c.getConnection();


    public void insertForSale() throws SQLException{
        try {
            con.setAutoCommit(false);
            System.out.println("Please fill out the following fields in order to list your house for sale.");
            System.out.println("");
            System.out.println("Please enter the address of the home you wish to list.");
            String address=scanString.nextLine();
            System.out.println("Please enter the city where your house is located in.");
            String city=scanString.nextLine();
            System.out.println("Please enter your 5 digit zipcode of your home.");
            long zip=scanNum.nextLong();
            System.out.println("Please enter the size of your home in square feet.");
            double size=scanNum.nextDouble();
            System.out.println("Please enter the number of bedrooms in your home.");
            int bedroom=scanNum.nextInt();
            System.out.println("Please enter the number of bathrooms in your home.");
            double bathroom=scanNum.nextDouble();
            System.out.println("Finally, please enter the price you wish to list your home at.");
            double price=scanNum.nextDouble();
            PreparedStatement addForSale=con.prepareStatement("INSERT INTO forsale(UserID, Address,City, ZipCode, Size, Bedrooms, Bathrooms, Price) VALUES (?,?,?,?,?,?,?,?)");
            addForSale.clearParameters();
            addForSale.setInt(1,LoginOrRegister.primary_keys);
            addForSale.setString(2, address);
            addForSale.setString(3,city);
            addForSale.setLong(4, zip);
            addForSale.setDouble(5, size);
            addForSale.setInt(6, bedroom);
            addForSale.setDouble(7, bathroom);
            addForSale.setDouble(8,price);
            addForSale.executeUpdate();
            con.commit();
            System.out.println("Your house has been successfully added to the for sale listings!");


         }catch (SQLException e){
            con.rollback();

        }



    }
    public void insertForRent() throws SQLException {
        try {
            con.setAutoCommit(false);
            System.out.println("Please fill out the following fields in order to list your house for rent.");
            System.out.println("");
            System.out.println("Please enter the address of the home you wish to list.");
            String address=scanString.nextLine();
            System.out.println("Please enter the city where your house is located in.");
            String city=scanString.nextLine();
            System.out.println("Please enter your 5 digit zipcode of your home.");
            long zip=scanNum.nextLong();
            System.out.println("Please enter the size of your home in square feet.");
            double size=scanNum.nextDouble();
            System.out.println("Please enter the number of bedrooms in your home.");
            int bedroom=scanNum.nextInt();
            System.out.println("Please enter the number of bathrooms in your home.");
            double bathroom=scanNum.nextDouble();
            System.out.println("Finally, please enter the price you wish to list your home at.");
            double price=scanNum.nextDouble();
            PreparedStatement addForSale=con.prepareStatement("INSERT INTO forrent(UserID, Address,City, ZipCode, Size, Bedrooms, Bathrooms, Price) VALUES (?,?,?,?,?,?,?,?)");
            addForSale.clearParameters();
            addForSale.setInt(1,LoginOrRegister.primary_keys);
            addForSale.setString(2, address);
            addForSale.setString(3,city);
            addForSale.setLong(4, zip);
            addForSale.setDouble(5, size);
            addForSale.setInt(6, bedroom);
            addForSale.setDouble(7, bathroom);
            addForSale.setDouble(8,price);
            addForSale.executeUpdate();
            con.commit();
            System.out.println("Your house has been successfully added to the for rent listings!");


        }catch (SQLException e){
            con.rollback();
        }



    }
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
            }else if(whatToUpdate==2){
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

            }else if(whatToUpdate==3){
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

            }else if(whatToUpdate==4){
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

            }else if(whatToUpdate==5){
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

            }else if(whatToUpdate==6){
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

            }else if(whatToUpdate==7){
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

            }else if(whatToUpdate==8){
                theSystem s=new theSystem();
                s.run();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }








}
