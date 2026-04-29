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
       boolean runningLoop = true; // Variable used to control the loop via "Exit"
       while(runningLoop) {
           System.out.println();
           System.out.println(" ACCOUNTING LEDGER");
           System.out.println("-------------------");
           System.out.println();
           System.out.println("D) Add Deposit");
           System.out.println("P) Add Payment");
           System.out.println("L) Open Ledger");
           System.out.println("E) Exit");
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

        String date = LocalDate.now().toString();
        String time = LocalDate.now().toString();

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        ledger.addTransaction(newTransaction);

        System.out.println("Payment added successfully!");
    }

    // ledgerScreen()
    public static void ledgerScreen()
    {


    }




















}
