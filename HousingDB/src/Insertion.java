import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insertion{
   private Scanner scanString=new Scanner(System.in);
   private Scanner scanNum=new Scanner(System.in);
   private Connect c=new Connect();
   private Connection con=c.getConnection();

    public void insertForSale(){
        try {
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
            System.out.println("Your house has been successfully added to the for sale listings!");


         }catch (SQLException e){

        }



    }
    public void insertForRent(){
        try {
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
            System.out.println("Your house has been successfully added to the for rent listings!");


        }catch (SQLException e){

        }



    }








}
