import java.util.Date;

class Account {
    private int id;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;

    public Account() {
        this(0, 0, 0);
    }

    public Account(int id, double balance, double annualInterestRate) {
        this.id = id;
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public String getDateCreated() {
        return dateCreated.toString();
    }

    public double getMonthlyInterestRate() {
        return annualInterestRate / 12;
    }

    public double getMonthlyInterest() {
        return balance * (getMonthlyInterestRate() / 100);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Error: Insufficient funds.");
        } else {
            balance -= amount;
        }
    }

    public void deposit(double amount) {
        if (amount < 0) {
            System.out.println("Error: Deposit amount must be positive.");
        } else {
            balance += amount;
        }
    }

    public String toString() {
        return "\nAccount ID: " + id + "\nDate created: " + getDateCreated()
            + "\nBalance: $" + String.format("%.2f", balance) + 
            "\nMonthly interest: $" + String.format("%.2f", getMonthlyInterest());
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(int id, double balance) {
        super(id, balance, 0);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= 0) {
            super.withdraw(amount);
        } else {
            System.out.println("Error! Savings account overdrawn transaction rejected.");
        }
    }
}

class CheckingAccount extends Account {
    private double overdraftLimit;

    public CheckingAccount() {
        super();
        this.overdraftLimit = -20;
    }

    public CheckingAccount(int id, double balance, double overdraftLimit) {
        super(id, balance, 0);
        this.overdraftLimit = overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= overdraftLimit) {
            super.withdraw(amount);
        } else {
            System.out.println("Error! Amount exceeds overdraft limit.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nOverdraft limit: $" + String.format("%.2f", overdraftLimit);
    }
}

public class Exercise_11_03 {
    public static void main(String[] args) {
        Account account = new Account(1122, 20000, 4.5);
        SavingsAccount savings = new SavingsAccount(1001, 20000);
        CheckingAccount checking = new CheckingAccount(1004, 20000, -20);
        account.setAnnualInterestRate(4.5);
        savings.setAnnualInterestRate(4.5);
        checking.setAnnualInterestRate(4.5);
        account.withdraw(2500);
        savings.withdraw(2500);
        checking.withdraw(2500);
        account.deposit(3000);
        savings.deposit(3000);
        checking.deposit(3000);
        System.out.println(account.toString());
        System.out.println(savings.toString());
        System.out.println(checking.toString());
    }
}
