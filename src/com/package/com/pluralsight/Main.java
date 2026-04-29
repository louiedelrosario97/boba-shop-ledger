package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Main
{
   static Scanner scanner = new Scanner(System.in);
   static LedgerManagement ledger = new LedgerManagement();

    static void main(String[] args)
    {
        homeScreen();
    }
    // ---------------------------------------- homeScreen() -----------------------------------------------------------
   static void homeScreen()
   {   String choice;
       boolean runningLoop = true; // Variable used to control the loop via "Close App"
       while(runningLoop)
       {
           System.out.println();
           System.out.println(" LEDGER HOMESCREEN ");
           System.out.println("-------------------");
           System.out.println("D) Add Deposit");
           System.out.println("P) Add Payment");
           System.out.println("L) Open Ledger");
           System.out.println("X) Close App");
           System.out.print("Choose option: ");
           choice = scanner.nextLine().toUpperCase().strip();


           switch (choice)
           {
               case "D": deposit();   break;
               case "P": payment();  break;
               case "L": ledgerScreen(); break;
               case "X": runningLoop = false; break;
               default:  System.out.println("Invalid option, silly billy.");
           }
       }
       System.out.println("Sayonara!");
   }
    // ---------------------------------------- ledgerScreen() --------------------------------------------------------
    public static void ledgerScreen()
    {
        String choice;
        boolean runningLoop = true;
        while (runningLoop)
        {
            System.out.println("---- LEDGER ----");
            System.out.println("A) Display All");
            System.out.println("D) Display Deposit History");
            System.out.println("P) Display Payment History");
            System.out.println("R) Report Search");
            System.out.println("H) (Back) to Home Screen");
            System.out.println("X) Close App");
            System.out.println("Choose option: ");
            choice = scanner.nextLine().toUpperCase().strip();
            System.out.println();

            switch (choice)
            {
                case "A": ledger.displayAll();      break;
                case "D": ledger.displayDeposits(); break;
                case "P": ledger.listPayments(); break;
                case "R": reportsScreen();          break;
                case "H": homeScreen();             break;
                case "X": runningLoop = false;      break;

                default: System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Sayonara!");
    }
    // --------------------------------------------- reportsScreen() --------------------------------------------------
    public static void reportsScreen()
    {
        String choice;
        boolean runningLoop = true;
        while(runningLoop)
        {
            System.out.println("---- REPORTS ----");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("0) (Back) to Ledger");
            System.out.println("H) (Back) to Home Screen");
            System.out.println("X) Close App");
            System.out.println("Select report type: ");
            choice = scanner.nextLine().toUpperCase().strip();

            switch(choice)
            {
                case "1": ledger.monthToDate();  break;
                case "2": ledger.previousMonth();break;
                case "3": ledger.yearToDate();   break;
                case "4": ledger.previousYear(); break;
                case "5": ledger.searchByVendor; break;
                case "0": ledgerScreen();        break;
                case "H": homeScreen();          break;
                case "X": runningLoop = false;   break;

                default: System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println("Sayonara!");
    }
    // ---------------------------------------- deposit() -------------------------------------------------------------
    public static void deposit()
    {
        System.out.println("DEPOSIT INFO:");
        System.out.println("-------------------");
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Deposit Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        while (amount < 0)
        {
            System.out.println("Must be a positive value, please try again.");
            System.out.print("Amount: " );
            amount = Double.parseDouble(scanner.nextLine());
        }                                                                                                                // TODO: Add code for inputs from the user that are not double values

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        ledger.addTransaction(newTransaction);
        System.out.println();
        System.out.println("Deposit added successfully!");
    }
    // ---------------------------------------- payment() -------------------------------------------------------------
    public static void payment()
    {
        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        while (amount > 0)
        {
            System.out.println("Must be a negative value, please try again.");
            System.out.print("Amount: ");
            amount = Double.parseDouble(scanner.nextLine());
        }                                                                                                                // TODO: Add code for inputs from the user that are not double values

        String date = LocalDate.now().toString();
        String time = LocalDate.now().toString();

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println("Payment added successfully!");
    }
}
