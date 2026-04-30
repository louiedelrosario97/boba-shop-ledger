
// This is where we will manage all of our transactions. Reading files, entering new data, etc.
// --------------------------------------------------------------------------------------------------------------------
package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
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
    {           // Plug in file/buff reader, if statement that includes "no data" possibility
        try
        {
            FileReader fReader = new FileReader("transaction_history.csv");
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
        } catch (IOException e)
        {
            System.out.println("No transaction data available.");
        }
    }
// ----------------------------------------- addTransaction() ---------------------------------------------------------
    public void addTransaction(Transaction t)
    {
        transactions.add(t);
        try
        {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter("transaction_history.csv", true));  // "true" = append mode, so we don't overwrite the file.
            bWriter.write(t.getDate()+ "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount() + "\n");
            bWriter.close();
        } catch (IOException e) {
            System.out.println("Could not update file.");
        }
    }
// ----------------------------------------- printHeader() ---------------------------------------------------------
    private void printHeader()
    {
        System.out.println(" Date | Time | Description | Vendor | Amount ");
        System.out.println("-------------------------------------------");
    }
// --------------------------------------------- displayAll() ---------------------------------------------------------
    public void displayAll()
    {
        printHeader();
        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            System.out.println(transactions.get(i));
        }
    }
// --------------------------------------------- displayDeposits() ----------------------------------------------------
    public void displayDeposits()
    {
        printHeader();
        for (Transaction t : transactions)
        {
            System.out.println(t.getAmount() > 0);
        }
    }
// --------------------------------------------- displayPayments() ----------------------------------------------------
    public void displayPayments()
    {
        printHeader();
        for (Transaction t : transactions)
        {
            if (t.getAmount() < 0)
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- monthToDate() ----------------------------------------------------
    public void monthToDate()
    {
        printHeader();
        LocalDate today = LocalDate.now();

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            LocalDate date = LocalDate.parse(t.getDate());

            if (date.getYear() == today.getYear() &&
                    date.getMonthValue() == today.getMonthValue())
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- previousMonth() ----------------------------------------------------
    public void previousMonth()
    {
        printHeader();
        LocalDate prevMonth = LocalDate.now().minusMonths(1);

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            LocalDate date = LocalDate.parse(t.getDate());

            if (date.getYear() == prevMonth.getYear() &&
                    date.getMonthValue() == prevMonth.getMonthValue())
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- yearToDate() ----------------------------------------------------
    public void yearToDate()
    {
        printHeader();
        int currentYear = LocalDate.now().getYear();

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            LocalDate date = LocalDate.parse(t.getDate());

            if (date.getYear() == currentYear)
            {
                System.out.println(t);
            }
        }
    }
// --------------------------------------------- previousYear() -------------------------------------------------------
    public void previousYear()
    {
        printHeader();
        int previousYear = LocalDate.now().getYear() - 1;

        for (int i = transactions.size() - 1; i >= 0; i--)
        {
            Transaction t = transactions.get(i);
            LocalDate date = LocalDate.parse(t.getDate());

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

