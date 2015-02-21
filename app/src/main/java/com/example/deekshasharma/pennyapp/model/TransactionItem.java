package com.example.deekshasharma.pennyapp.model;

public class TransactionItem {

    private int dateIcon;
    private String transactionName;
    private String amount;
    private int transactionIcon;

    public TransactionItem(int dateIcon, String transactionName, String amount, int transactionIcon)
    {
        this.dateIcon = dateIcon;
        this.transactionName = transactionName;
        this.amount = amount;
        this.transactionIcon = transactionIcon;
    }

    public int getDateIcon() {
        return dateIcon;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getAmount() {
        return amount;
    }

    public int getTransactionIcon() {
        return transactionIcon;
    }
}
