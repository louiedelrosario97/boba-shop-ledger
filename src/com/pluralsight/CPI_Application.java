package com.pluralsight;

import java.util.Scanner;

//
public class CPI_Application
{
   static Scanner scanner = new Scanner(System.in);

   static void homeScreen()
   {
       while(true) {
           System.out.println();
           System.out.println("ACCOUNTING LEDGER");
           System.out.println("---------------------");
           System.out.println();
           System.out.println("D) Add Deposit");
           System.out.println("P) Add Payment");
           System.out.println("L) Ledger Display");
           System.out.println("E) Exit");
           System.out.print("Choose option: ");
           String choice = scanner.nextLine().toUpperCase().strip();
           System.out.println();

           switch (choice) {
               case "D":
                   deposit();
                   break;
               case "P":
                   payment();
                   break;
               case "L":
                   ledgerScreen();
                   break;
               case "E":
                   System.out.println("Goodbye!");
                   break;
               default:
                   System.out.println("Invalid response, try again: ");
                   break;
           }
       }

       public static void deposit() { // Need to

   }






















}
