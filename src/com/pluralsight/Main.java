package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main
{
   static Scanner scanner = new Scanner(System.in);
   static LedgerManager ledger = new LedgerManager();

   static void main(String[] args) { homeScreen(); }

    // ---------------------------------------- homeScreen() ----------------------------------------------------------
   static void homeScreen()
   {   String choice;
       boolean runningLoop = true; // Variable used to control the loop via "Close App"
       while(runningLoop)
       {
           System.out.println("  ___       _         _____         ");
           System.out.println(" | _ ) ___ | |__  __ |_   _|___  __ ");
           System.out.println(" | _ \\/ _ \\| '_ \\/ _` || |/ -_) / _`|");
           System.out.println(" |___/\\___/|_.__/\\__,_||_|\\___|\\__,_|");
           System.out.println("       Boba Tea Shop Accounting v1.0       ");
           System.out.println("=========================================");

           System.out.println("S) Record Sale");
           System.out.println("E) Record Expense");
           System.out.println("L) Open Ledger");
           System.out.println("X) Close App");
           System.out.print("Choose option: ");
           choice = scanner.nextLine().toUpperCase().strip();

           switch (choice)
           {
               case "S": sale();              break;
               case "E": expense();           break;
               case "L": ledgerScreen();      break;
               case "X": runningLoop = false; break;
               default:
                   System.out.println();
                   System.out.println("Invalid option. Please try again.");
           }
       }
       System.out.println();
       System.out.println("Boba Tea Ledger shutting down...");
   }
    // ---------------------------------------- ledgerScreen() --------------------------------------------------------
    public static void ledgerScreen()
    {
        String choice;
        boolean runningLoop = true;
        while (runningLoop)
        {
            System.out.println();
            System.out.println("---- BOBA TEA LEDGER ----");
            System.out.println("A) Display All");
            System.out.println("S) Display Sale History");
            System.out.println("E) Display Expense History");
            System.out.println("R) Report Search");
            System.out.println("H) (Back) to Home Screen");
            System.out.println("X) Close App");
            System.out.print("Choose option: ");
            choice = scanner.nextLine().toUpperCase().strip();

            switch (choice)
            {
                case "A": ledger.displayAll();      break;
                case "S": ledger.displaySales();    break;
                case "E": ledger.displayExpenses(); break;
                case "R": reportsScreen();          break;
                case "H": homeScreen();             break;
                case "X": runningLoop = false;      break;

                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
            }
        }
        System.out.println();
        System.out.println("Boba Tea Ledger shutting down...");
    }
    // --------------------------------------------- reportsScreen() --------------------------------------------------
    public static void reportsScreen()
    {
        String choice;
        boolean runningLoop = true;
        while(runningLoop)
        {
            System.out.println();
            System.out.println("---- REPORTS ----");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Customer/Vendor");
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
                    System.out.print("Customer/Vendor Name: ");
                    String vendorName = scanner.nextLine();
                    ledger.searchByVendor(vendorName);
                                                  break;
                case "0": ledgerScreen();         break;
                case "H": homeScreen();           break;
                case "X": runningLoop = false;    break;

                default:
                    System.out.println();
                    System.out.println("Invalid entry. Please try again.");
            }
        }
        System.out.println();
        System.out.println("Boba Tea Ledger shutting down...");
    }
    // ---------------------------------------- sale() ----------------------------------------------------------------
    public static void sale()
    {
        System.out.println();
        System.out.println("NEW SALE:");
        System.out.println("-------------------");
        // Receive 'date' user input. Try-catch used to ensure correct date format is inputted
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); // (04/30/2026 format) Moved out of try-catch. Now in scope of 'Transaction newTransaction'
        LocalDate saleDate = null;
        boolean dateInput = false;
        while (!dateInput) // LOGIC: By default the user input is false. If user enters a valid value for the DateTimeFormatter, then dateInput = true, ending the loop.
        {
            System.out.print("Date (MM/DD/YYYY): ");
            try
            {
                String date = scanner.nextLine();
                saleDate = LocalDate.parse(date, dtFormatter);
                dateInput = true;
            }

            catch (Exception e)
            {
                System.out.println();
                System.out.println("Invalid entry. Please enter date in (MM/DD/YYYY) format.");
            }
        }
        // Receive time user input
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a")); // "hh:mm:ss a" creates 12-hour AM/PM format
        // Receive description user input
        System.out.print("Item Sold: ");
        String description = scanner.nextLine();
        // Receive customer/vendor user input
        System.out.print("Customer: ");
        String vendor = scanner.nextLine();
        // Receive amount user input. Try-catch used to ensure number value is inputted AND if-else used to ensure positive number is inputted.
        double amount = 0;
        boolean amountInput = false;
        while (!amountInput)
        {
            System.out.print("Sale Amount: $");
            try
            {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0)
                {
                    System.out.println();
                    System.out.println("Sale must be a positive value, please try again.");
                }
                else { amountInput = true; }
            }

            catch (NumberFormatException e)
            {
                System.out.println();
                System.out.println("Invalid entry. Please enter only number values.");
            }
        }
        // Creates a Transaction object using all the info we collected. saleDate.format() converts the LocalDate back to a String in our argument format
        Transaction newTransaction = new Transaction(saleDate.format(dtFormatter), time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println();
        System.out.println("Sale added successfully! Returning to Homescreen...");
    }
    // ---------------------------------------- expense() -------------------------------------------------------------
    public static void expense()
    {
        System.out.println();
        System.out.println("EXPENSE INFO:");
        System.out.println("-------------------");

        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate paymentDate = null;
        boolean dateInput = false;
        while (!dateInput) // LOGIC: same as sale()
        {
            System.out.print("Date (MM/DD/YYYY): ");
            try
            {
                String date = scanner.nextLine();
                paymentDate = LocalDate.parse(date, dtFormatter);
                dateInput = true;
            }

            catch (Exception e)
            {
                System.out.println();
                System.out.println("Invalid entry. Please enter date in (MM/DD/YYYY) format.");
            }
        }

        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        double amount = 0;
        boolean amountInput = false;
        while (!amountInput)
        {
            System.out.print("Expense Amount: $");
            try
            {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount > 0)
                {
                    System.out.println();
                    System.out.println("Expense must be a negative value, please try again.");
                }
                else { amountInput = true; }
            }

            catch (NumberFormatException e)
            {
                System.out.println();
                System.out.println("Invalid entry. Please enter only number values.");
            }
        }

        Transaction newTransaction = new Transaction(paymentDate.format(dtFormatter), time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println();
        System.out.println("Expense added successfully! Returning to Homescreen...");
    }
}
