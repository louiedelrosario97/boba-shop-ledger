package com.pluralsight;

public class Transaction {

    // Data Members
    String date;
    String time;
    String description;
    String vendor;
    double amount;

    // Getters
    public double getAmount() { return amount; }
    public String getVendor() { return vendor; }
    public String getDescription() { return description; }
    public String getTime() { return time; }
    public String getDate() { return date; }

    // Setters
    public void setAmount(double amount) { this.amount = amount; }
    public void setVendor(String vendor) { this.vendor = vendor; }
    public void setDescription(String description) { this.description = description; }
    public void setTime(String time) { this.time = time; }
    public void setDate(String date) { this.date = date; }

    // Constructor
    public Transaction(String date, String time, String description, String vendor, double amount)
    {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return String.format("%-10s | %-11s | %-30s | %-20s | $%.2f", date, time, description, vendor, amount);
    }
}
