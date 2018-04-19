import java.sql.SQLException;
import java.util.Scanner;

public class theSystem {
    private Selection s=new Selection();
    private LoginOrRegister l=new LoginOrRegister();
    private Insertion i=new Insertion();
    private Updating u=new Updating();
    private Deletion d=new Deletion();
    Scanner scanInt=new Scanner(System.in);
    Scanner scanString=new Scanner(System.in);


    public void start() throws InterruptedException{
        System.out.println("WELCOME TO THE HOUSING HELPER");
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.print(".");Thread.sleep(30);System.out.print(".");Thread.sleep(30);
        System.out.println("");
        System.out.println("1- Login");
        System.out.println("2- Register");
        System.out.println("3- Exit");

        System.out.println("Please enter 1 to Login or 2 to Register 3 to Exit the system");
        while (!scanInt.hasNextInt()) {
            System.out.println("Not a valid input");
            System.out.println("Please enter 1 to Login or 2 to Register 3 to Exit the system");
            scanInt.next(); // this is important!
        }
        int logOrreg=scanInt.nextInt();
        if (logOrreg==1){
            l.login();

        }else if(logOrreg==2){
            l.register();
        }else if(logOrreg==3){
            System.out.println("Thank you for using Housing Helper goodbye!");
            System.exit(0);
        }else{
            System.exit(0);
        }
    }

    public void run() throws InterruptedException {
        int goAgain=1;
        do {
            try {
                s.getConnection();
                System.out.println("WELCOME TO THE HOUSING HELPER DATABASE");
                System.out.println("What are you interested in?");
                System.out.println("1- Houses for sale?");
                System.out.println("2- Houses for rent?");
                System.out.println("3- Finding an agent to help you with purchasing or renting a home?");
                System.out.println("4- Logout");
                System.out.println("Please select between options 1-4 by entering the number corresponding to the option.");
                while (!scanInt.hasNextInt()) {
                    System.out.println("Not a valid input");
                    System.out.println("1- Houses for sale?");
                    System.out.println("2- Houses for rent?");
                    System.out.println("3- Finding an agent to help you with purchasing or renting a home?");
                    System.out.println("4- Logout");
                    System.out.println("Please select between options 1-4 by entering the number corresponding to the option.");                    scanInt.next(); // this is important!
                }

                int option = scanInt.nextInt();
                if (option == 1) {
                    System.out.println("Welcome to houses for sale!");
                    //options to choose from
                    System.out.println("1- View houses for sale.");
                    System.out.println("2- Add your property to the database.");
                    System.out.println("3- Remove your property from the database.");
                    System.out.println("4- Update your property in the database");
                    System.out.println("5- View the average house price for sale in a particular city");
                    System.out.println("6- Find users who have both a property for sale and for rent");
                    System.out.println("7- View houses between a certain price range.");


                    //view house above certain price
                    //view house below certain price
                    //view house between a certain price
                    //address city agent and agent phone join
                    System.out.println("8-Return to main menu");
                    while (!scanInt.hasNextInt()) {
                        System.out.println("Not a valid input");
                        System.out.println("1- View houses for sale.");
                        System.out.println("2- Add your property to the database.");
                        System.out.println("3- Remove your property from the database.");
                        System.out.println("4- Update your property in the database");
                        System.out.println("5- View the average house price for sale in a particular city");
                        System.out.println("6- Find users who have both a property for sale and for rent");
                        System.out.println("7- View houses between a certain price range.");
                        System.out.println("8-Return to main menu");
                        scanInt.next(); // this is important!
                    }

                    int forSaleChoice = scanInt.nextInt();
                    if (forSaleChoice == 1) {
                        s.selectForSale();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    } else if (forSaleChoice == 2) {
                        i.insertForSale();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    } else if (forSaleChoice == 3) {
                        d.deleteForSale();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    } else if (forSaleChoice == 4) {

                        u.updateForSale();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }else if(forSaleChoice==5){
                        s.avgPriceForSalePerCity();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();
                    }else if(forSaleChoice==6){
                        s.userForSaleForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }else if(forSaleChoice==7){
                        s.betweenPriceRangeSale();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }


                } else if (option == 2) {
                    System.out.println("Welcome to houses for rent!");
                    //options to choose from
                    System.out.println("1- View houses for rent.");
                    System.out.println("2- Add your property to the database.");
                    System.out.println("3- Remove your property from the database.");
                    System.out.println("4- Update your property in the database");
                    System.out.println("5- View the average house price for rent in a particular city");
                    System.out.println("6- Find users who have both a property for rent and for sale");
                    System.out.println("7- View houses between a certain price range.");
                    //address city agent and agent phone join
                    System.out.println("8-Return to main menu");
                    while (!scanInt.hasNextInt()) {
                        System.out.println("Not a valid input");
                        System.out.println("1- View houses for rent.");
                        System.out.println("2- Add your property to the database.");
                        System.out.println("3- Remove your property from the database.");
                        System.out.println("4- Update your property in the database");
                        System.out.println("5- View the average house price for rent in a particular city");
                        System.out.println("6- Find users who have both a property for rent and for sale");
                        System.out.println("7- View houses between a certain price range.");
                        scanInt.next(); // this is important!
                    }

                    int forRentChoice = scanInt.nextInt();
                    if (forRentChoice == 1) {
                        s.selectForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    } else if (forRentChoice == 2) {
                        i.insertForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    } else if (forRentChoice == 3) {
                        d.deleteForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    } else if (forRentChoice == 4) {
                        u.updateForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }else if(forRentChoice==5){
                        s.avgPriceForRentPerCity();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();
                    }else if(forRentChoice==6){
                        s.userForSaleForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();
                    }else if(forRentChoice==7){
                        s.betweenPriceRangeRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();
                    }


                } else if (option == 3) {
                    //agent table
                    System.out.println("We have so many agents available what do you need from them?");
                    System.out.println("1- View all agents available.");
                    System.out.println("2- Find agents in your area.");
                    System.out.println("3- Find the address and city of houses for sale with the corresponding agent information ");
                    System.out.println("4- Find the address and city of houses for sale with the corresponding agent information ");

                    System.out.println("8-Return to main menu");
                    while (!scanInt.hasNextInt()) {
                        System.out.println("Not a valid input");
                        System.out.println("1- View all agents available.");
                        System.out.println("2- Find agents in your area.");
                        System.out.println("3- Find the address and city of houses for sale with the corresponding agent information ");
                        System.out.println("4- Find the address and city of houses for sale with the corresponding agent information ");

                        System.out.println("8-Return to main menu");
                        scanInt.next(); // this is important!
                    }
                    int agentChoice = scanInt.nextInt();
                    if (agentChoice == 1) {
                        s.viewAgents();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }else if(agentChoice==2){
                        s.viewAgentsByCity();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }else if(agentChoice==3){
                        s.AgentsAndAddressForSale();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();

                    }else if(agentChoice==4){
                        s.AgentsAndAddressForRent();
                        System.out.println("Back to the main menu?");
                        System.out.println("1-Yes, 2-No");
                        while (!scanInt.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Back to the main menu?");
                            System.out.println("1-Yes, 2-No");
                            scanInt.next(); // this is important!
                        }
                        goAgain = scanInt.nextInt();
                    }



                } else if(option==4) {
                    System.out.println("You have been logged out");
                    start();

                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }while (goAgain==1);




    }
}
