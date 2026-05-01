
// This is where we will manage all of our transactions. Reading files, entering new data, etc.
// --------------------------------------------------------------------------------------------------------------------
package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LedgerManager
{
   static private final ArrayList<Transaction> transactions = new ArrayList<>();

    public LedgerManager()
    {
        loadTransactions();
    }

// ----------------------------------------- loadTransactions() --------------------------------------------------------
    private void loadTransactions()
    {
        try
        {
            FileReader fReader = new FileReader("bobashop_transactions.csv");
            BufferedReader bReader = new BufferedReader(fReader);

            String line = bReader.readLine();
            while (line != null)
            {
                String[] cols = line.split("\\|");
                String date = cols[0];
                String time = cols[1];
                String description = cols[2];
                String vendor = cols[3];
                double amount = Double.parseDouble(cols[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

                line = bReader.readLine();
            }
            bReader.close();
        } catch (IOException _) {}
    }
// ----------------------------------------- addTransaction() ---------------------------------------------------------
    public void addTransaction(Transaction t)
    {
        transactions.add(t);
        try
        {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("bobashop_transactions.csv", true));  // "true" = append mode, so we don't overwrite the file.
            bWriter.write(t.getDate()+ "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount() + "\n");
            bWriter.close();
        }
        catch (IOException e) { System.out.println("Could not update file."); }
    }
// ----------------------------------------- printHeader() ---------------------------------------------------------
    private void printHeader()
    {
        System.out.printf("%-10s | %-11s | %-30s | %-20s | %s%n", "Date", "Time", "Description", "Customer/Vendor", "Amount");
        System.out.println("------------------------------------------------------------------------------------------------");
    }
// --------------------------------------------- displayAll() ---------------------------------------------------------
    public void displayAll()
    {
        System.out.println();
        System.out.println("   Now Displaying All Transactions...");
        System.out.println();
        printHeader();
        for (int i = transactions.size() - 1; i >= 0; i--) // Loops through entire array list, and goes backwards through the list so newest transactions appear first.
        {
            System.out.println(transactions.get(i));
        }
    }
// --------------------------------------------- displaySales() -------------------------------------------------------
    public void displaySales()
    {
        System.out.println();
        System.out.println("   Now Displaying All Sales...");
        System.out.println();
        printHeader();
        for (Transaction t : transactions)
        {
            if (t.getAmount() > 0)    // All elements in 'transactions' that has a positive 'amount' value will display
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- displayExpenses() ----------------------------------------------------
    public void displayExpenses()
    {
        System.out.println();
        System.out.println("   Now Displaying All Expenses...");
        System.out.println();
        printHeader();
        for (Transaction t : transactions)
        {
            if (t.getAmount() < 0) // All elements in 'transactions' that has a negative 'amount' value will display
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- monthToDate() --------------------------------------------------------
    public void monthToDate()
    {
        System.out.println();
        System.out.println("   Now Displaying Month To Date Transactions...");
        System.out.println();
        printHeader();
        LocalDate now = LocalDate.now();

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(t.getDate(), dtFormatter);
            // if-statement filters elements that have the same year and month as 'now'
            if (date.getYear() == now.getYear() && date.getMonthValue() == now.getMonthValue())
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- previousMonth() ------------------------------------------------------
    public void previousMonth()
    {
        System.out.println();
        System.out.println("   Now Displaying Previous Month Transactions...");
        System.out.println();
        printHeader();
        LocalDate prevMonth = LocalDate.now().minusMonths(1); // Gets today's date then subtracts one month to get last month's date.

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(t.getDate(), dtFormatter);

            if (date.getYear() == prevMonth.getYear() && date.getMonthValue() == prevMonth.getMonthValue())
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- yearToDate() ---------------------------------------------------------
    public void yearToDate()
    {
        System.out.println();
        System.out.println("   Now Displaying Year To Date Transactions...");
        System.out.println();
        printHeader();
        int currentYear = LocalDate.now().getYear();

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(t.getDate(), dtFormatter);

            if (date.getYear() == currentYear)
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- previousYear() -------------------------------------------------------
    public void previousYear()
    {
        System.out.println();
        System.out.println("   Now Displaying Previous Year Transactions...");
        System.out.println();
        printHeader();
        int previousYear = LocalDate.now().getYear() - 1;

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate date = LocalDate.parse(t.getDate(), dtFormatter);

            if (date.getYear() == previousYear)
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- searchByVendor() -------------------------------------------------------
    public void searchByVendor(String vendorName)
    {
        printHeader();
        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);

            if (t.getVendor().toLowerCase().contains(vendorName.toLowerCase()))
            {
                System.out.println(t);
            }
        }
    }
}

