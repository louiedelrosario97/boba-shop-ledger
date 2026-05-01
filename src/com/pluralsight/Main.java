package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main
{
   static Scanner scanner = new Scanner(System.in);
   static LedgerManager ledger = new LedgerManager();

   static void main(String[] args)
    {
        homeScreen();
    }

    // ---------------------------------------- homeScreen() ----------------------------------------------------------
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
               case "D": deposit();           break;
               case "P": payment();           break;
               case "L": ledgerScreen();      break;
               case "X": runningLoop = false; break;
               default:
                   System.out.println();
                   System.out.println("Invalid option. Please try again.");
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
            System.out.println();
            System.out.println("---- LEDGER ----");
            System.out.println("A) Display All");
            System.out.println("D) Display Deposit History");
            System.out.println("P) Display Payment History");
            System.out.println("R) Report Search");
            System.out.println("H) (Back) to Home Screen");
            System.out.println("X) Close App");
            System.out.println("Choose option: ");
            choice = scanner.nextLine().toUpperCase().strip();

            switch (choice)
            {
                case "A": ledger.displayAll();      break;
                case "D": ledger.displayDeposits(); break;
                case "P": ledger.displayPayments(); break;
                case "R": reportsScreen();          break;
                case "H": homeScreen();             break;
                case "X": runningLoop = false;      break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println();
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
                case "1": ledger.monthToDate();   break;
                case "2": ledger.previousMonth(); break;
                case "3": ledger.yearToDate();    break;
                case "4": ledger.previousYear();  break;
                case "5":
                    System.out.print("Vendor Name: ");
                    String vendorName = scanner.nextLine();
                    ledger.searchByVendor(vendorName);
                                                  break;
                case "0": ledgerScreen();         break;
                case "H": homeScreen();           break;
                case "X": runningLoop = false;    break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println();
        System.out.println("Sayonara!");
    }
    // ---------------------------------------- deposit() -------------------------------------------------------------
    public static void deposit()
    {
        System.out.println();
        System.out.println("DEPOSIT INFO:");
        System.out.println("-------------------");

        LocalDate depositDate = "";
        boolean dateInput = false;
        while (!dateInput)
        {
            System.out.print("Date (MM/DD/YYYY): ");
            try
            {
                String date = scanner.nextLine();                                                          // Logic: By default the user input is false.
                DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");                 // If user enters a valid value for the DateTimeFormatter,
                depositDate = LocalDate.parse(date, dtFormatter);                                          // then dateInput = true, ending the loop.
                dateInput = true;
            }

            catch (Exception e)
            {
                System.out.println();
                System.out.println("Invalid date format. Please enter date in (MM/DD/YYYY) format.");
            }
        }

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")); // "hh:mm:ss a" creates 12-hour AM/PM format

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        double amount = 0;
        boolean amountInput = false;
        while (!amountInput)
        {
            System.out.print("Deposit Amount: $");
            try
            {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0)
                {
                    System.out.println();
                    System.out.println("Deposit must be a positive value, please try again.");
                }
                else { amountInput = true; }
            }

            catch (NumberFormatException e)
            {
                System.out.println();
                System.out.println("Invalid amount. Please enter only number values.");
            }
        }

        Transaction newTransaction = new Transaction(transactionDate.format(dtFormatter), time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println();
        System.out.println("Deposit added successfully! Returning to Homescreen...");
    }
    // ---------------------------------------- payment() -------------------------------------------------------------
    public static void payment()
    {
        System.out.println();
        System.out.println("PAYMENT INFO:");
        System.out.println("-------------------");

        System.out.print("Date (MM/DD/YYYY): ");
        String date = scanner.nextLine();
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate transactionDate = LocalDate.parse(date, dtFormatter);

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        double amount = 0;
        boolean amountInput = false;
        while (!amountInput)
        {
            System.out.print("Payment Amount: $");
            try
            {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount > 0)
                {
                    System.out.println();
                    System.out.println("Payment must be a negative value, please try again.");
                }
                else { amountInput = true; }
            }

            catch (NumberFormatException e)
            {
                System.out.println();
                System.out.println("Invalid amount. Please enter only number values.");
            }
        }

        Transaction newTransaction = new Transaction(transactionDate.format(dtFormatter), time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println();
        System.out.println("Payment added successfully! Returning to Homescreen...");
    }
}
