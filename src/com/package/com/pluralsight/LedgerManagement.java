
// This is where we will manage all of our transactions. Reading files, entering new data, etc.
// ---------------------------------------------------------------------------------------------------
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

    private void loadTransactions()
    {           // Plug in file/buff reader, if statement that includes "no data" possibility
        try
        {
            FileReader fr = new FileReader("transaction_history.csv");
            BufferedReader br = new BufferedReader(fr);

            String line; // Variable used in while loop
            while ((line = br.readLine()) != null)
            {
                String[] category = line.split("\\|");
                if (category.length == 5)
                {
                    String date = category[0];
                    String time = category[1];
                    String description = category[2];
                    String vendor = category[3];
                    double amount = Double.parseDouble(category[4]);

                    transactions.add(new Transaction(date, time, description, vendor, amount));
                }
            }
            br.close();
        } catch (IOException e)
        {
            System.out.println("No transaction data available.");
        }
    }

    public void addTransaction(Transaction newTransaction) // Use BufferedWriter
    {
        transactions.add(newTransaction);
        try
        {       // .get in order of the .csv file
            BufferedWriter writer = new BufferedWriter(new FileWriter("transaction_history.csv", true)); // "true" = append mode, so we don't overwrite the file.
            writer.write(newTransaction.getDate()+ "|" + newTransaction.getTime() + "|" + newTransaction.getDescription() + "|" + newTransaction.getVendor() + "|" + newTransaction.getAmount() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not update file.");
        }
    }
}
