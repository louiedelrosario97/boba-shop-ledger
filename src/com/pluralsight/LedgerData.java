
// This is where we will manage all of our transactions. Reading files, entering new data, etc.

package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;

public class LedgerData
{
    private void loadTransactions() // There is no initial data, no need to build temporary array list.
    {
        try
        {
            FileReader fileReader   = new FileReader("transaction_history.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] columns = line.split("\\|");


                }










    }







}
