import java.util.Scanner;

public class Engine {
    private Selection s=new Selection();
    private LoginOrRegister l=new LoginOrRegister();
    private Insertion i=new Insertion();
    Scanner scanInt=new Scanner(System.in);
    Scanner scanString=new Scanner(System.in);

    public void run(){
            System.out.println("WELCOME TO THE HOUSING HELPER");
            System.out.println("1- Login");
            System.out.println("2- Register");
            System.out.println("Please enter 1 to Login or 2 to Register");
            int logOrreg=scanInt.nextInt();
            if (logOrreg==1){
                l.login();

            }else if(logOrreg==2){
                l.register();
            }


        int goAgain=1;
        do {
            s.getConnection();
            System.out.println("WELCOME TO THE HOUSING HELPER DATABASE");
            System.out.println("What are you interested in?");
            System.out.println("1- Houses for sale?");
            System.out.println("2- Houses for rent?");
            System.out.println("3- Finding an agent to help you with purchasing or renting a home?");
            System.out.println("Please select between options 1-3 by entering the number corresponding to the option.");
            int option=scanInt.nextInt();
            if(option==1){
                System.out.println("Welcome to houses for sale!");
                //options to choose from
                System.out.println("1- View houses for sale.");
                System.out.println("2- Add your property to the database.");
                System.out.println("3- Remove your property from the database.");
                System.out.println("4- Update your property in the database");
                //view the cheapest house
                //view avg house price in a city

                int forSaleChoice=scanInt.nextInt();
                if(forSaleChoice==1){
                    s.selectForSale();
                    System.out.println("Back to the main menu?");
                    System.out.println("1-Yes, 2-No");
                    goAgain=scanInt.nextInt();

                }else  if(forSaleChoice==2){
                    i.insertForSale();
                    System.out.println("Back to the main menu?");
                    System.out.println("1-Yes, 2-No");
                    goAgain=scanInt.nextInt();

                }else  if(forSaleChoice==3){

                }else  if(forSaleChoice==4){

                }//add more clauses



            }else if(option==2){
                System.out.println("Welcome to houses for rent!");
                //options to choose from
                System.out.println("1- View houses for rent.");
                System.out.println("2- Add your property to the database.");
                System.out.println("3- Remove your property from the database.");
                System.out.println("4- Update your property in the database");
                //view the cheapest house
                //view avg house price in a city
                int forRentChoice=scanInt.nextInt();
                if(forRentChoice==1){
                    s.selectForRent();
                    System.out.println("Back to the main menu?");
                    System.out.println("1-Yes, 2-No");
                    goAgain=scanInt.nextInt();

                }else  if(forRentChoice==2){
                    i.insertForRent();
                    System.out.println("Back to the main menu?");
                    System.out.println("1-Yes, 2-No");
                    goAgain=scanInt.nextInt();

                }else  if(forRentChoice==3){

                }else  if(forRentChoice==4){

                }//add more clauses


            }else  if(option==3){
                //agent table
                System.out.println("We have so many agents available what do you need from them?");
                System.out.println("1- View all agents available.");
                System.out.println("2- Find agents in your area.");


            }else{

            }




















//            System.out.println("Please enter 1 in order to view the tables within the database");
//            System.out.println("Please enter 2 in order to view the records within a table in the Housing Database");
//
//            int choice=scanInt.nextInt();
//            if(choice==1){
//                s.showTables();
//                System.out.println("Back to the main menu?");
//                System.out.println("1-Yes, 2-No");
//                goAgain=scanInt.nextInt();
//
//
//            }
//            else if(choice==2){
//                s.showTables();
//                System.out.println("Please select the table in which you would like to see records");
//                System.out.println("1 for ?, 2 for ?, 3 for ?");
//                int selection=scanInt.nextInt();
//                if(selection==1){
//                    s.selectForRent();
//                    System.out.println("Back to the main menu?");
//                    System.out.println("1-Yes, 2-No");
//                    goAgain=scanInt.nextInt();
//                }else if(selection==2){
//                    s.selectForSale();
//                    System.out.println("Back to the main menu?");
//                    System.out.println("1-Yes, 2-No");
//                    goAgain=scanInt.nextInt();
//                }else if(selection==3){
//                    System.out.println("Back to the main menu?");
//                    System.out.println("1-Yes, 2-No");
//                    goAgain=scanInt.nextInt();
//                }
//
//
//            }else{
//                System.exit(54);
//            }
        }while (goAgain==1);




    }
}
