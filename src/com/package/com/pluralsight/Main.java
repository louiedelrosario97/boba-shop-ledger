package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Main
{
   static Scanner scanner = new Scanner(System.in);
   static LedgerManagement ledger = new LedgerManagement();

    public static void main(String[] args)
    {
        homeScreen();
    }

   static void homeScreen()
   {   String choice;
       boolean runningLoop = true; // Variable used to control the loop via "Close App"
       while(runningLoop)
       {
           System.out.println();
           System.out.println(" ACCOUNTING LEDGER");
           System.out.println("-------------------");
           System.out.println();
           System.out.println("D) Add Deposit");
           System.out.println("P) Add Payment");
           System.out.println("L) Open Ledger");
           System.out.println("X) Close App");
           System.out.print("Choose option: ");
           choice = scanner.nextLine().toUpperCase().strip();
           System.out.println();

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

    // deposit() use LocalDate/Time.now
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
        }                                                                       // TODO: Add code for inputs from the user that are not double values

        String date = LocalDate.now().toString();
        String time = LocalTime.now().toString();

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println("Deposit added successfully!");

    }

    // payment()
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
        }                                                                        // TODO: Add code for inputs from the user that are not double values

        String date = LocalDate.now().toString();
        String time = LocalDate.now().toString();

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println("Payment added successfully!");
    }

    // ledgerScreen()
    public static void ledgerScreen()
    {   String choice;
        boolean runningLoop = true;
        while (runningLoop)
        {
            System.out.println("-- LEDGER --");
            System.out.println("A) Display All");
            System.out.println("D) Display Deposit History");
            System.out.println("P) Display Payment History");
            System.out.println("H) Back to Home Screen");
            System.out.println("X) Close App");
            System.out.println("Choose option: ");
            choice = scanner.nextLine().toUpperCase().strip();
            System.out.println();

            switch (choice)
            {
                case "A": displayTransactions(); break;
                case "D": displayTransactions(); break;
                case "P": displayTransactions(); break;
                case "R": reportsScreen(); break;
                case "H": homeScreen(); break;
                case "X": runningLoop = false; break;
                default: System.out.println("Invalid option, silly billy.");
            }

        }
    }




















}
