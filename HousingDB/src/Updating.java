import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Updating {
    private Scanner scanString = new Scanner(System.in);
    private Scanner scanNum = new Scanner(System.in);
    private Connect c = new Connect();
    private Connection con = c.getConnection();

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
            while (!scanNum.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("In order to update your housing record please select what you would like to update.");
                System.out.println("1- Update Record Address");
                System.out.println("2- Update Record City");
                System.out.println("3- Update Record Zipcode");
                System.out.println("4- Update Record size");
                System.out.println("5- Update Record number of bedrooms");
                System.out.println("6- Update Record number of bathrooms");
                System.out.println("7- Update Record the price");
                System.out.println("8- Return to the main menu");
                scanNum.next();
            }

            int whatToUpdate = scanNum.nextInt();
            if (whatToUpdate == 1) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the address please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update your address to.");
                    String address = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Address=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setString(1, address);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if (x > 0) {
                        System.out.println("The address has been updated to " + address);

                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1, "UPDATE forsale SET Address=" + address + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                        p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3, LoginOrRegister.primary_keys);
                        p2.executeUpdate();
                        con.commit();

                    } else {
                        System.out.println("Update unsuccessful something went wrong.");
                    }


                } catch (SQLException e) {
                    con.rollback();
                }
            } else if (whatToUpdate == 2) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the city please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the city to.");
                    String city = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET City=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setString(1, city);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The city has been updated to " + city);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forsale SET City=" + city + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    e.printStackTrace();
                    con.rollback();
                }


            } else if (whatToUpdate == 3) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the zipcode please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the zipcode to.");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update the zipcode to.");
                        scanNum.next();
                    }
                    int zip = scanNum.nextInt();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET ZipCode=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setInt(1, zip);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The zipcode has been updated to " + zip);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forsale SET ZipCode=" + zip + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 4) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the size please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the size to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update the size to.");
                        scanNum.next();
                    }
                    double size = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Size=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, size);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The size has been updated to " + size);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forsale SET Size=" + size + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 5) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the  number of bedrooms please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the number of bedrooms to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update the number of bedrooms to.");
                        scanNum.next();
                    }
                    double bed = scanNum.nextInt();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Bedrooms=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, bed);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The number of bedrooms has been updated to " + bed);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forsale SET Bedrooms=" + bed + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 6) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the number of bathrooms please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the number of bathrooms to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update the number of bathrooms to.");
                        scanNum.next();
                    }
                    double bath = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Bathrooms=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, bath);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The number of bathrooms has been updated to " + bath);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forsale SET Bathrooms=" + bath + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 7) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the price please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update your price to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update your price to.");
                        scanNum.next();
                    }
                    double price = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forsale SET Price=? WHERE UserID=? AND ForSaleID=?");
                    ps.clearParameters();
                    ps.setDouble(1, price);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The price has been updated to " + price);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forsale SET Price=" + price + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 8) {
                theSystem s = new theSystem();
                s.run();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateForRent() throws InterruptedException {
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
            while (!scanNum.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("In order to update your housing record please select what you would like to update.");
                System.out.println("1- Update Record Address");
                System.out.println("2- Update Record City");
                System.out.println("3- Update Record Zipcode");
                System.out.println("4- Update Record size");
                System.out.println("5- Update Record number of bedrooms");
                System.out.println("6- Update Record number of bathrooms");
                System.out.println("7- Update Record the price");
                System.out.println("8- Return to the main menu");
                scanNum.next();
            }

            int whatToUpdate = scanNum.nextInt();
            if (whatToUpdate == 1) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the address please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update your address to.");
                    String address = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET Address=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setString(1, address);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The address has been updated to " + address);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET Address=" + address + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    con.rollback();
                }
            } else if (whatToUpdate == 2) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the city please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the city to.");
                    String city = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET City=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setString(1, city);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The city has been updated to " + city);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET City=" + city + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }


            } else if (whatToUpdate == 3) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the zipcode please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the zipcode to.");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update the zipcode to.");
                        scanNum.next();
                    }
                    int zip = scanNum.nextInt();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET ZipCode=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setInt(1, zip);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The zipcode has been updated to " + zip);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET ZipCode=" + zip + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 4) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the size please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the size to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update size to.");
                        scanNum.next();
                    }
                    double size = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET Size=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setDouble(1, size);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The size has been updated to " + size);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET Size=" + size + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 5) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the  number of bedrooms please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the number of bedrooms to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update the number of bedrooms to.");
                        scanNum.next();
                    }
                    int bed = scanNum.nextInt();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET Bedrooms=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setInt(1, bed);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The number of bedrooms has been updated to " + bed);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET Bedrooms=" + bed + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 6) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the number of bathrooms please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update the number of bathrooms to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to the number of bathrooms to.");
                        scanNum.next();
                    }
                    double bath = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET Bathrooms=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setDouble(1, bath);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The number of bathrooms has been updated to " + bath);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET Bathrooms=" + bath + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 7) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("We need your house ID in order to update the price please enter it below");
                    while (!scanNum.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID in order to update");
                        scanNum.next();
                    }
                    int updateID = scanNum.nextInt();
                    System.out.println("Please enter what you would like to update your price to.");
                    while (!scanNum.hasNextDouble()) {
                        System.out.println("That's not a number!");
                        System.out.println("Please enter what you would like to update your price to.");
                        scanNum.next();
                    }
                    double price = scanNum.nextDouble();
                    PreparedStatement ps = con.prepareStatement("UPDATE forrent SET Price=? WHERE UserID=? AND ForRentID=?");
                    ps.clearParameters();
                    ps.setDouble(1, price);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    ps.setInt(3, updateID);
                    int x = ps.executeUpdate();
                    con.commit();

                    if(x>0){
                        System.out.println("The price has been updated to " + price);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE forrent SET Price=" + price + " WHERE UserID=" + LoginOrRegister.primary_keys + " AND ForSaleID=" + updateID);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();


                } catch (SQLException e) {
                    con.rollback();
                }

            } else if (whatToUpdate == 8) {
                theSystem s = new theSystem();
                s.run();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateProfile() throws InterruptedException {
        try {
            System.out.println("In order to update your profile record please select what you would like to update.");
            System.out.println("1- Update Username");
            System.out.println("2- Update Password");
            System.out.println("3- Update First Name");
            System.out.println("4- Update Last Name");
            System.out.println("5- Update Phone number");
            System.out.println("6- Update email address");
            System.out.println("7- Return to the main menu");
            while (!scanNum.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("In order to update your profile record please select what you would like to update.");
                System.out.println("1- Update Username");
                System.out.println("2- Update Password");
                System.out.println("3- Update First Name");
                System.out.println("4- Update Last Name");
                System.out.println("5- Update Phone number");
                System.out.println("6- Update email address");
                System.out.println("7- Return to the main menu");
                scanNum.next();
            }

            int whatToUpdate = scanNum.nextInt();
            if (whatToUpdate == 1) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("Please enter what you would like to update your username to.");
                    String username = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET Username=? WHERE UserID=?");
                    ps.clearParameters();
                    ps.setString(1, username);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    int x = ps.executeUpdate();
                    if(x>0){
                        System.out.println("Your username has been updated to " + username);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    con.commit();
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE users SET Username=" + username + " WHERE UserID=" + LoginOrRegister.primary_keys);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    System.out.println("This username already exists please try another username.");
                    updateProfile();
                    try {
                        con.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (whatToUpdate == 2) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("Please enter what you would like to update your password to.");
                    String pass = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET Password=? WHERE UserID=?");
                    ps.clearParameters();
                    ps.setInt(1, pass.hashCode());
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    int x = ps.executeUpdate();
                    if(x>0){
                        System.out.println("Your password has been updated to " + pass);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    con.commit();
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE users SET Password=" + pass.hashCode() + " WHERE UserID=" + LoginOrRegister.primary_keys);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (whatToUpdate == 3) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("Please enter what you would like to update your First Name to.");
                    String fname = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET FirstName=? WHERE UserID=?");
                    ps.clearParameters();
                    ps.setString(1, fname);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    int x = ps.executeUpdate();
                    if(x>0){
                        System.out.println("Your First name has been updated to " + fname);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    con.commit();
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE users SET FirstName=" + fname + " WHERE UserID=" + LoginOrRegister.primary_keys);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (whatToUpdate == 4) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("Please enter what you would like to update your Last Name to.");
                    String lname = scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET LastName=? WHERE UserID=?");
                    ps.clearParameters();
                    ps.setString(1, lname);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    int x = ps.executeUpdate();
                    if(x>0){
                        System.out.println("Your last name has been updated to " + lname);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    con.commit();
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE users SET LastName=" + lname + " WHERE UserID=" + LoginOrRegister.primary_keys);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            } else if (whatToUpdate == 5) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("Please enter what you would like to update your Phone number to.");
                    long phone = scanNum.nextLong();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET Phone=? WHERE UserID=?");
                    ps.clearParameters();
                    ps.setLong(1, phone);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    int x = ps.executeUpdate();
                    if(x>0){
                        System.out.println("Your phone number has been updated to " + phone);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    con.commit();
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE users SET Phone=" + phone + " WHERE UserID=" + LoginOrRegister.primary_keys);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }else if (whatToUpdate == 6) {
                try {
                    con.setAutoCommit(false);
                    System.out.println("Please enter what you would like to update your email address to.");
                    String email=scanString.nextLine();
                    PreparedStatement ps = con.prepareStatement("UPDATE users SET Email=? WHERE UserID=?");
                    ps.clearParameters();
                    ps.setString(1, email);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    int x = ps.executeUpdate();
                    if(x>0){
                        System.out.println("Your email has been updated to " + email);

                    }else{
                        System.out.println("Updating unsuccessful");
                    }
                    con.commit();
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1, "UPDATE users SET Email=" + email + " WHERE UserID=" + LoginOrRegister.primary_keys);
                    p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3, LoginOrRegister.primary_keys);
                    p2.executeUpdate();
                    con.commit();

                } catch (SQLException e) {
                    try {
                        con.rollback();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }else if (whatToUpdate == 7) {
                theSystem s = new theSystem();
                s.run();
            }

        } catch (Exception r) {
        }


    }
}
