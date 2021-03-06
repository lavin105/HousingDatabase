import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Selection extends Connect {
    Scanner scan=new Scanner(System.in);
    Scanner scanString=new Scanner(System.in);
    //format string for te majority of tables
    String format = "\u2503%1$-18s\u2503%2$-18s\u2503%3$-30s\u2503%4$-18s\u2503%5$-18s\u2503%6$-18s\u2503%7$-18s\u2503%8$-18s\u2503%9$-18s\u2503\n";

    //method to select a home for sale
    //a house can be selected with or without a filter on a particular attribute
    //a home can be filtered by all of its criteria: ID, address, city, zip, bedrooms, bathrooms, size, and price
    //each query can be exported to a csv
    //every if and else if are different filters on the house for sale
    public void selectForSale() {
        StringBuilder s=new StringBuilder();
        System.out.println("Would you like to filter your selection?");
        System.out.println("1 Yes, 2 No");
        int filter=scan.nextInt();
        if(filter==2) {

            try {
                PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale");
                //timer commented out used for testing purposes
                //long startTime = System.currentTimeMillis();
                ResultSet r = p.executeQuery();
                //long endTime = System.currentTimeMillis();
                //System.out.println("That took " + (endTime - startTime) + " milliseconds");
                ResultSetMetaData rm = r.getMetaData();

                String col1 = rm.getColumnName(1);
                String col2 = rm.getColumnName(2);
                String col3 = rm.getColumnName(3);
                String col4 = rm.getColumnName(4);
                String col5 = rm.getColumnName(5);
                String col6 = rm.getColumnName(6);
                String col7 = rm.getColumnName(7);
                String col8= rm.getColumnName(8);
                String col9=rm.getColumnName(9);
                if(!r.isBeforeFirst()){
                    System.out.println("No record exists");


                }else{
                    System.out.println("Displaying all houses for sale");
                    System.out.println("");
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                   //appending attributes to a string builder for the sake of exporting to csv
                    s.append(col1);
                    s.append(',');
                    s.append(col2);
                    s.append(',');
                    s.append(col3);
                    s.append(',');
                    s.append(col4);
                    s.append(',');
                    s.append(col5);
                    s.append(',');
                    s.append(col6);
                    s.append(',');
                    s.append(col7);
                    s.append(',');
                    s.append(col8);
                    s.append(',');
                    s.append(col9);
                    s.append('\n');

                    while (r.next()) {
                        System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                        System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                        s.append(r.getInt(1));
                        s.append(',');
                        s.append(r.getInt(2));
                        s.append(',');
                        s.append(r.getString(3));
                        s.append(',');
                        s.append(r.getString(4));
                        s.append(',');
                        s.append(r.getInt(5));
                        s.append(',');
                        s.append(r.getDouble(6));
                        s.append(',');
                        s.append(r.getDouble(7));
                        s.append(',');
                        s.append(r.getDouble(8));
                        s.append(',');
                        s.append(r.getDouble(9));
                        s.append('\n');

                    }

                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1,"SELECT * FROM ForSale ");
                    p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();


                    System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next();
                    }
                    int csv=scan.nextInt();
                    if(csv==1){
                        System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                        String _file=scanString.nextLine();
                        try {
                            PrintWriter pw = new PrintWriter(new File(_file));
                            pw.write(s.toString());
                            pw.close();
                        }catch (IOException e){
                            System.out.println("Unable to create file");
                        }

                        System.out.println("Exported");
                    }else{
                        System.out.println("Not exported");
                    }

                }




            } catch (SQLException r) {
                    System.out.println(r.getMessage());

            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }else if(filter==1){

            System.out.println("What would you like to filter faculty by?");
            System.out.println("1-ID, 2-Address, 3-City, 4-Zip Code, 5-Size, 6-# of Bedrooms, 7-# of Bathrooms, 8-Price");
            int filter2=scan.nextInt();
            if(filter2==1){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE ForSaleID=?");
                    System.out.println("Enter the ID to filter by.");

                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next();
                    }
                    int id=scan.nextInt();
                    p.setInt(1,id);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()){
                        System.out.println("No house for sale with ID "+ id);


                    }else{
                        System.out.println("Displaying all houses for sale with the ForSaleId "+id);
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where ForSaleID="+id);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));

                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();

                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }



                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==2){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Address LIKE ?");
                    System.out.println("Enter the Address to filter by.");
                    String add=scanString.nextLine();
                    p.setString(1,add+'%');
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale with an address of " + add);

                    }else{
                        System.out.println("Displaying all houses for sale with the address "+add);
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where Address LIKE "+add);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));

                        p2.setInt(3,LoginOrRegister.primary_keys);
                        p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==3){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE City=?");
                    System.out.println("Enter the City to filter by.");
                    String city=scanString.nextLine();
                    p.setString(1,city);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale with the city "+city );

                    }else{
                        System.out.println("Displaying all houses for sale with the city "+city);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where City="+city);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);

                        p2.setInt(3,LoginOrRegister.primary_keys);
                        p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==4){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE ZipCode=?");
                    System.out.println("Enter the ZipCode to filter by.");

                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a popper zipcode!");
                        System.out.println("Enter the Zipcode to filter by.");
                        scan.next();
                    }


                    int zcode=scan.nextInt();
                    p.setInt(1,zcode);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale with the zipcode "+ zcode);

                    }else {
                        System.out.println("Displaying all houses for sale with the zipcode "+zcode);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where ZipCode="+zcode);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==5){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Size=?");
                    System.out.println("Enter the Size (sqft) to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper size!");
                        System.out.println("Enter the size to filter by.");
                        scan.next();
                    }
                    double sz=scan.nextDouble();
                    p.setDouble(1,sz);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale is "+sz +" square feet");


                    }else{
                        System.out.println("Displaying all houses for sale with the size "+sz+" square feet");

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where Size="+sz);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }

                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==6){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Bedrooms=?");
                    System.out.println("Enter the number of Bedrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper number of bedrooms!");
                        System.out.println("Enter the number of bedrooms to filter by.");
                        scan.next();
                    }
                    double bed=scan.nextDouble();
                    p.setDouble(1,bed);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale has "+ bed + " bedrooms");


                    }else{
                        System.out.println("Displaying all houses for sale with "+bed+" bedrooms");

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where Bed="+bed);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==7){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Bathrooms=?");
                    System.out.println("Enter the number of Bathrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper number of bathrooms!");
                        System.out.println("Enter the number of bathrooms to filter by.");
                        scan.next();
                    }
                    double bath=scan.nextDouble();
                    p.setDouble(1,bath);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale has "+ bath + " bathrooms");


                    }else{
                        System.out.println("Displaying all houses for sale with "+bath+" bathrooms");

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where Bath="+bath);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }


                    }


                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==8){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Price=?");
                    System.out.println("Enter the Price to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper price!");
                        System.out.println("Enter the price to filter by.");
                        scan.next();
                    }
                    double price=scan.nextDouble();
                    p.setDouble(1,price);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale has a price of "+ price + " dollars");

                    }else {
                        System.out.println("Displaying all houses for sale with a price of "+ price);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForSale Where Price="+price);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }


                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else {
                System.out.println("Not a valid option please choose again");
                selectForSale();
            }







        }else {
            System.out.println("Not a valid option please choose again");
            selectForSale();
        }
    }
    //selecting houses for rent behaves identical to selecting homes for sale
    //see the selectForSale method for details about the method
    public void selectForRent() {
        StringBuilder s=new StringBuilder();
        System.out.println("Would you like to filter your selection?");
        System.out.println("1 Yes, 2 No");
        int filter=scan.nextInt();
        if(filter==2) {

            try {
                PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent");
                ResultSet r = p.executeQuery();
                ResultSetMetaData rm = r.getMetaData();

                String col1 = rm.getColumnName(1);
                String col2 = rm.getColumnName(2);
                String col3 = rm.getColumnName(3);
                String col4 = rm.getColumnName(4);
                String col5 = rm.getColumnName(5);
                String col6 = rm.getColumnName(6);
                String col7 = rm.getColumnName(7);
                String col8= rm.getColumnName(8);
                String col9=rm.getColumnName(9);

                if(!r.isBeforeFirst()){
                    System.out.println("No record exists");

                }else{
                    System.out.println("Displaying all houses for rent");

                    System.out.println("");
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    s.append(col1);
                    s.append(',');
                    s.append(col2);
                    s.append(',');
                    s.append(col3);
                    s.append(',');
                    s.append(col4);
                    s.append(',');
                    s.append(col5);
                    s.append(',');
                    s.append(col6);
                    s.append(',');
                    s.append(col7);
                    s.append(',');
                    s.append(col8);
                    s.append(',');
                    s.append(col9);
                    s.append('\n');
                    while (r.next()) {
                        System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                        System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                        s.append(r.getInt(1));
                        s.append(',');
                        s.append(r.getInt(2));
                        s.append(',');
                        s.append(r.getString(3));
                        s.append(',');
                        s.append(r.getString(4));
                        s.append(',');
                        s.append(r.getInt(5));
                        s.append(',');
                        s.append(r.getDouble(6));
                        s.append(',');
                        s.append(r.getDouble(7));
                        s.append(',');
                        s.append(r.getDouble(8));
                        s.append(',');
                        s.append(r.getDouble(9));
                        s.append('\n');
                    }
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1,"SELECT * FROM ForRent");
                    p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                    System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next();
                    }
                    int csv=scan.nextInt();
                    if(csv==1){
                        System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                        String _file=scanString.nextLine();
                        try {
                            PrintWriter pw = new PrintWriter(new File(_file));
                            pw.write(s.toString());
                            pw.close();
                        }catch (IOException e){
                            System.out.println("Unable to create file");
                        }

                        System.out.println("Exported");
                    }else{
                        System.out.println("Not exported");
                    }

                }






            } catch (SQLException r) {
                    r.printStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if(filter==1){

            System.out.println("What would you like to filter  by?");
            System.out.println("1-ID, 2-Address, 3-City, 4-Zip Code, 5-Size, 6-# of Bedrooms, 7-# of Bathrooms, 8-Price");
            int filter2=scan.nextInt();
            if(filter2==1){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE ForRentID=?");
                    System.out.println("Enter the ID to filter by.");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next();
                    }
                    int id=scan.nextInt();
                    p.setInt(1,id);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent with the ID "+id);

                    }else{
                        System.out.println("Displaying all houses for rent with a ForRentID of "+id);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE ForRentID="+id);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==2){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Address LIKE ?");
                    System.out.println("Enter the Address to filter by.");
                    String add=scanString.nextLine();
                    p.setString(1,add+'%');
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house exists with the address "+ add);

                    }else{
                        System.out.println("Displaying all houses for rent with the address "+add);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE Address LIKE "+add);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }


                    }



                } catch (SQLException r) {
                r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==3){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE City=?");
                    System.out.println("Enter the City to filter by.");
                    String city=scanString.nextLine();
                    p.setString(1,city);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent exists with the city "+city);

                    }else{
                        System.out.println("Displaying all houses for rent with the city "+city);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE City="+city);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==4){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE ZipCode=?");
                    System.out.println("Enter the ZipCode to filter by.");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a proper zipcode!");
                        System.out.println("Enter the zipcode to filter by.");
                        scan.next();
                    }
                    int zcode=scan.nextInt();
                    p.setInt(1,zcode);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent has the zipcode "+zcode);

                    }else{
                        System.out.println("Displaying all houses for rent with the zipcode "+zcode);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE ZipCode="+zcode);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==5){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Size=?");
                    System.out.println("Enter the Size (sqft) to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper size!");
                        System.out.println("Enter the size to filter by.");
                        scan.next();
                    }
                    double sz=scan.nextDouble();
                    p.setDouble(1,sz);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent exists that is "+sz+" square feet");

                    }else{
                        System.out.println("Displaying all houses for rent with the size "+sz+" square feet");

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE Size="+sz);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }



                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==6){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Bedrooms=?");
                    System.out.println("Enter the number of Bedrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper number of bedrooms!");
                        System.out.println("Enter the number of bedrooms to filter by.");
                        scan.next();
                    }
                    double bed=scan.nextDouble();
                    p.setDouble(1,bed);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No hose for rent has "+bed+" bedrooms");

                    }else{
                        System.out.println("Displaying all houses for rent with"+bed+" bedrooms");

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE Bedrooms="+bed);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==7){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Bathrooms=?");
                    System.out.println("Enter the number of Bathrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper number of bathrooms!");
                        System.out.println("Enter the number of bathrooms to filter by.");
                        scan.next();
                    }
                    double bath=scan.nextDouble();
                    p.setDouble(1,bath);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent has "+bath+" bathrooms");

                    }else{
                        System.out.println("Displaying all houses for rent with"+bath+" bathrooms");

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE Bathrooms="+bath);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==8){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Price=?");
                    System.out.println("Enter the Price to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper price!");
                        System.out.println("Enter the price to filter by.");
                        scan.next();
                    }
                    double price=scan.nextDouble();
                    p.setDouble(1,price);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house with the price "+price+" exists");

                    }else{
                        System.out.println("Displaying all houses for rent with the price $"+price);

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                        p2.setString(1,"SELECT * FROM ForRent WHERE Price="+price);
                        p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                        p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next();
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else {
                System.out.println("Not a valid option please choose again");
                selectForRent();
            }







        }else {
            System.out.println("Not a valid option please choose again");
            selectForRent();
        }
    }
    //this method uses an aggregate function in it to display the average price of a home for sale based on a city of the users choice
    //it returns a table with the city's name and the average price for that particular city
    public void avgPriceForSalePerCity(){
        StringBuilder s=new StringBuilder();

        try {
            System.out.println("Please enter the city in which you would like to see the average price of houses for sale.");
            String city = scanString.nextLine();
            PreparedStatement p = con.prepareStatement("SELECT City, ROUND(AVG(Price),2) as AverageHousePrice FROM forsale WHERE City=? GROUP BY City");
            p.setString(1,city);
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String format ="\u2503%1$-25s\u2503%2$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No house exists with the city "+city);

            }else{
                System.out.println("Displaying the average house prices for sale in the city "+city);

                System.out.println("");
                System.out.format(format, "+++++++++++++++++++++++++", "++++++++++++++++++++");
                System.out.format(format, col1, col2);
                System.out.format(format, "+++++++++++++++++++++++++", "++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),"$"+r.getDouble(2));
                    System.out.format(format, "-------------------------", "--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getDouble(2));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT City, AVG(Price) as AverageHousePrice FROM forsale WHERE City="+city+" GROUP BY City");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }





        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    //this method uses an aggregate function in it to display the average price of a home for rent based on a city of the users choice
    //it returns a table with the city's name and the average price for that particular city
    public void avgPriceForRentPerCity(){
        StringBuilder s=new StringBuilder();

        try {
            System.out.println("Please enter the city in which you would like to see the average price of houses for rent.");
            String city = scanString.nextLine();
            PreparedStatement p = con.prepareStatement("SELECT City, ROUND(AVG(Price),2) as AverageHousePrice FROM forrent WHERE City=? GROUP BY City");
            p.setString(1,city);
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String format ="\u2503%1$-25s\u2503%2$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No record exists with the city "+city);

            }else{
                System.out.println("Displaying the average house prices for rent in the city "+city);

                System.out.println("");
                System.out.format(format, "+++++++++++++++++++++++++", "++++++++++++++++++++");
                System.out.format(format, col1, col2);
                System.out.format(format, "+++++++++++++++++++++++++", "++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),"$"+r.getDouble(2));
                    System.out.format(format, "-------------------------", "--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getDouble(2));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT City, AVG(Price) as AverageHousePrice FROM forrent WHERE City="+city+" GROUP BY City");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //this method involves a query with a join over 3 tables and it displays the users with a house for sale and for rent and their information

    public void userForSaleForRent(){
        StringBuilder s=new StringBuilder();

        try {
            System.out.println("Displaying users with a house for sale and for rent");
            //this query is changed into a view
            PreparedStatement p = con.prepareStatement("SELECT * FROM SellsAndRents");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-30s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No record exists");

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++++++++++++");
                System.out.format(format, col1, col2,col3,col4);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2),r.getLong(3),r.getString(4));
                    System.out.format(format, "--------------------", "--------------------","--------------------","------------------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM SellsAndRents");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    //method to view all the real estate agents within the databse
    public void viewAgents(){
        StringBuilder s=new StringBuilder();

        try{
            //query uses a view as well
            PreparedStatement p = con.prepareStatement("SELECT * FROM allagents");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3=rm.getColumnName(3);
            String col4=rm.getColumnName(4);
            String col5=rm.getColumnName(5);
            String col6=rm.getColumnName(6);


            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-25s\u2503%5$-20s\u2503%6$-25s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("Records do not exists");

            }else{
                System.out.println("Displaying all agents");

                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5), r.getString(6));
                    System.out.format(format, "--------------------", "--------------------","--------------------","-------------------------","--------------------","-------------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getString(6));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM allagents");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    //this method displays homes for sale between a particular price range which is helpful for people on a budget
    public void betweenPriceRangeSale(){
        StringBuilder s=new StringBuilder();

        try{
            System.out.println("In order to find houses between a certain range we need a min and a max");
            System.out.println("What is the minimum price you are searching for?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper minimum price!");
                System.out.println("What is the minimum price you are searching for?");
                scan.next();
            }
            double min=scan.nextDouble();
            System.out.println("What is the maximum price you are searching?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper maximum price!");
                System.out.println("What is the maximum price you are searching for?\"");
                scan.next();
            }
            double max=scan.nextDouble();
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Price BETWEEN ? AND ?");
            p.setDouble(1, min);
            p.setDouble(2,max);
            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);



            if(!r.isBeforeFirst()){
                System.out.println("No house for sale between the prices of "+min+ " and "+max);

            }else{
                System.out.println("Displaying houses for sale between the prices of "+min+ " and "+max);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));

                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM ForSale WHERE Price BETWEEN "+min+" AND "+max);
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        }catch (SQLException e){
        e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //this method displays homes for rent between a particular price range which is helpful for people on a budget
    public void betweenPriceRangeRent(){
        StringBuilder s=new StringBuilder();

        try{
            System.out.println("In order to find houses between a certain range we need a min and a max");
            System.out.println("What is the minimum price you are searching for?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper minimum price!");
                System.out.println("What is the minimum price you are searching for?");
                scan.next();
            }
            double min=scan.nextDouble();
            System.out.println("What is the maximum price you are searching?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper maximum price!");
                System.out.println("What is the maximum price you are searching for?\"");
                scan.next();
            }
            double max=scan.nextDouble();
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Price BETWEEN ? AND ?");
            p.setDouble(1, min);
            p.setDouble(2,max);
            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);



            if(!r.isBeforeFirst()){
                System.out.println("No house for rent between "+min+" and "+max);

            }else{
                System.out.println("Displaying houses for rent between the prices of "+min+ " and "+max);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));

                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM ForRent WHERE Price BETWEEN "+min+" AND "+max);
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //this method filter agents based on a city
    //the query also is converted into a view
    public void viewAgentsByCity(){
        StringBuilder s=new StringBuilder();

        try{
            System.out.println("Please enter the city in which you like to filter agents by.");
            String city=scanString.nextLine();
        PreparedStatement p = con.prepareStatement("SELECT * FROM agentbycity WHERE CityJurisdiction=?");
            p.setString(1, city);
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3=rm.getColumnName(3);
            String col4=rm.getColumnName(4);
            String col5=rm.getColumnName(5);
            String col6=rm.getColumnName(6);


            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-25s\u2503%5$-20s\u2503%6$-25s\u2503\n";


            if(!r.isBeforeFirst()){
                System.out.println("No agent exists with jurisdiction over the city "+city);

            }else{
                System.out.println("Displaying all agent with jurisdiction over the city "+city);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++","++++++++++++++++++++","+++++++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getString(2), r.getString(3),r.getString(4),r.getString(5), r.getString(6));
                    System.out.format(format, "--------------------", "--------------------","--------------------","-------------------------","--------------------","-------------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getString(6));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM agentbycity WHERE CityJurisdiction="+city);
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    //method displays homes for sale with te corresponding agent and their agent
    //the query contains a join across 3 tables
    //also contains a view

    public void AgentsAndAddressForSale(){
        StringBuilder s=new StringBuilder();

        try{
        PreparedStatement p = con.prepareStatement("SELECT * FROM agentaddressforsale");
            //timer commented out used for testing purposes

            //long startTime = System.currentTimeMillis();
            ResultSet r = p.executeQuery();
            //long endTime = System.currentTimeMillis();
            //System.out.println("That took " + (endTime - startTime) + " milliseconds");
        ResultSetMetaData rm = r.getMetaData();
        String col1 = rm.getColumnName(1);
        String col2 = rm.getColumnName(2);
        String col3=rm.getColumnName(3);
        String col4=rm.getColumnName(4);
        String col5=rm.getColumnName(5);
        String col6=rm.getColumnName(6);
        String col7=rm.getColumnName(7);



        String format ="\u2503%1$-30s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503%5$-20s\u2503%6$-20s\u2503%7$-30s\u2503\n";

            if(!r.isBeforeFirst()){
                System.out.println("Record does not exist");

            }else{
                System.out.println("Displaying agent information with the corresponding hosues for sale that they manage");
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6, col7);
                System.out.format(format, "++++++++++++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2), r.getInt(3),r.getString(4),r.getString(5), r.getString(6), r.getString(7));
                    System.out.format(format, "------------------------------", "--------------------","--------------------","--------------------","--------------------","--------------------","------------------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getInt(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getString(6));
                    s.append(',');
                    s.append(r.getString(7));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM agentaddressforsale");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }


    }catch (SQLException e){
        e.printStackTrace();
    }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //method displays homes for rent with te corresponding agent and their agent
    //the query contains a join across 3 tables
    //also contains a view
    public void AgentsAndAddressForRent(){
        StringBuilder s=new StringBuilder();

        try{
            PreparedStatement p = con.prepareStatement("SELECT * FROM agentaddressforrent");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3=rm.getColumnName(3);
            String col4=rm.getColumnName(4);
            String col5=rm.getColumnName(5);
            String col6=rm.getColumnName(6);
            String col7=rm.getColumnName(7);



            String format ="\u2503%1$-30s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503%5$-20s\u2503%6$-20s\u2503%7$-30s\u2503\n";


            if(!r.isBeforeFirst()){
                System.out.println("Record does not exist");

            }else{
                System.out.println("Displaying agent information with the corresponding hosues for rent that they manage");

                System.out.println("");
                System.out.format(format, "++++++++++++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6, col7);
                System.out.format(format, "++++++++++++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2), r.getInt(3),r.getString(4),r.getString(5), r.getString(6), r.getString(7));
                    System.out.format(format, "------------------------------", "--------------------","--------------------","--------------------","--------------------","--------------------","------------------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getInt(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getString(6));
                    s.append(',');
                    s.append(r.getString(7));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM agentaddressforrent");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    //method tht diplays the houses for sale above the average price of the city that they are in
    //this method contains a query with a subquery
    public void aboveAvgPriceForSale(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE City=? AND Price>(SELECT AVG(Price) FROM ForSale WHERE City=?)");
            System.out.println("Enter the City to find the houses greater than the average price");
            String city=scanString.nextLine();
            p.setString(1,city);
            p.setString(2,city);

            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);

            if(!r.isBeforeFirst()){
                System.out.println("No house for sale exists with this criteria");

            }else{
                System.out.println("Displaying all houses for sale above the average prce in the city "+city);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()) {
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM ForSale WHERE City="+city+" AND Price>(SELECT AVG(Price) FROM ForSale WHERE City="+city+")");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        } catch (SQLException r) {
            r.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //method tht diplays the houses for sale below the average price of the city that they are in
    //this method contains a query with a subquery
    public void belowAvgPriceForSale(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE City=? AND Price<(SELECT AVG(Price) FROM ForSale WHERE City=?)");
            System.out.println("Enter the City to find the houses less than the average price");
            String city=scanString.nextLine();
            p.setString(1,city);
            p.setString(2,city);

            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);

            if(!r.isBeforeFirst()){
                System.out.println("No house for sale exists with this criteria");

            }else{
                System.out.println("Displaying all houses for sale below the average prce in the city "+city);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()) {
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM ForSale WHERE City="+city+" AND Price<(SELECT AVG(Price) FROM ForSale WHERE City="+city+")");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        } catch (SQLException r) {
            r.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //method tht diplays the houses for rent above the average price of the city that they are in
    //this method contains a query with a subquery
    public void aboveAvgPriceForRent(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE City=? AND Price>(SELECT AVG(Price) FROM ForRent WHERE City=?)");
            System.out.println("Enter the City to find the houses greater than the average price");
            String city=scanString.nextLine();
            p.setString(1,city);
            p.setString(2,city);

            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);

            if(!r.isBeforeFirst()){
                System.out.println("No house for rent exists with this criteria");

            }else{
                System.out.println("Displaying all houses for rent above the average prce in the city "+city);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()) {
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM ForRent WHERE City="+city+" AND Price>(SELECT AVG(Price) FROM ForSale WHERE City="+city+")");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        } catch (SQLException r) {
            r.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //method tht diplays the houses for sale below the average price of the city that they are in
    //this method contains a query with a subquery
    public void belowAvgPriceForRent(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE City=? AND Price<(SELECT AVG(Price) FROM ForRent WHERE City=?)");
            System.out.println("Enter the City to find the houses less than the average price");
            String city=scanString.nextLine();
            p.setString(1,city);
            p.setString(2,city);

            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);

            if(!r.isBeforeFirst()){
                System.out.println("No house for rent exists with this criteria");

            }else{
                System.out.println("Displaying all houses for rent below the average prce in the city "+city);

                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()) {
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), "$"+r.getDouble(9));
                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1,"SELECT * FROM ForRent WHERE City="+city+" AND Price<(SELECT AVG(Price) FROM ForSale WHERE City="+city+")");
                p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        } catch (SQLException r) {
            r.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //this method is for displaying the number of users within the system
    //method is intended for administrator purposes
    public void viewNumberUsers(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT COUNT(*) AS NumberOfUsers FROM Users");

            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String format = "\u2503%1$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No users exist");

            }else{
                System.out.println("Displaying the number of users in the system.");
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++");
                System.out.format(format, col1);
                System.out.format(format, "++++++++++++++++++++");
                s.append(col1);
                s.append('\n');
                while (r.next()) {
                    System.out.format(format, r.getInt(1));
                    System.out.format(format, "--------------------");
                    s.append(r.getInt(1));
                    s.append('\n');
                }

                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        } catch (SQLException r) {
            r.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //method that returns all of the uses within the database and their corresponding user information
    //method is intended for administrator use only
    public void viewAllUsers(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM Users");

            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String format = "\u2503%1$-18s\u2503%2$-18s\u2503%3$-18s\u2503%4$-18s\u2503%5$-18s\u2503%6$-18s\u2503%7$-30s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No users exist");

            }else{
                System.out.println("Displaying all users in the system.");
                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append('\n');
                while (r.next()) {
                    System.out.format(format, r.getInt(1),r.getString(2), r.getInt(3), r.getString(4), r.getString(5), r.getLong(6), r.getString(7));
                    System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getInt(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getLong(6));
                    s.append(',');
                    s.append(r.getString(7));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        } catch (SQLException r) {
            r.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    //method for viewing all the logs generated for the system
    //intended for administrator use only
    //displas the query made, the date/time stamp, as well as the userID of who performed the query
    public void viewLogs(){
        StringBuilder s=new StringBuilder();

        try {
            PreparedStatement p = con.prepareStatement("SELECT * FROM logs");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);

            String format ="\u2503%1$-170s\u2503%2$-20s\u2503%3$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No record exists");

            }else{
                System.out.println("Displaying the HosuingHelper Database Logs.");

                System.out.println("");
                System.out.format(format, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++", "++++++++++++++++++++", "++++++++++++++++++++");
                System.out.format(format, col1, col2,col3);
                System.out.format(format, "++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++", "++++++++++++++++++++", "++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2),r.getInt(3));
                    System.out.format(format, "--------------------------------------------------------------------------------------------------------------------------------------------------------------------------", "--------------------", "--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getInt(3));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next();
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to. Please put .csv at the end of your file's name");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }





        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
