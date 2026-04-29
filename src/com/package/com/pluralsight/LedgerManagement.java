
// This is where we will manage all of our transactions. Reading files, entering new data, etc.
// --------------------------------------------------------------------------------------------------------------------
package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class LedgerManagement
{
   static private final ArrayList<Transaction> transactions = new ArrayList<>();

    public LedgerManagement()
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
// --------------------------------------------- listAll() ArrayList -----------------------------------------------
    public ArrayList<Transaction> displayAll() { return transactions; }
// --------------------------------------------- listDeposits() ArrayList ------------------------------------------
    public ArrayList<Transaction> listDeposits()
    {
        ArrayList<Transaction> allDeposits = new ArrayList<>();

        for (Transaction t : transactions)
        {
            if (t.getAmount() > 0)
            {
                allDeposits.add(t);
            }
        }
    return allDeposits;
    }
// --------------------------------------------- listPayments() ArrayList ------------------------------------------
    public ArrayList<Transaction> listPayments()
    {
        ArrayList<Transaction> allPayments = new ArrayList<>();

        for (Transaction t : transactions)
        {
            if (t.getAmount() < 0)
            {
                allPayments.add(t);
            }
        }
    return allPayments;
    }

}
