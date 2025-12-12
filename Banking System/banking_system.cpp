#include <bits/stdc++.h>
using namespace std;

class InvalidAmountException : public runtime_error
{
public:
    InvalidAmountException(const string &msg) : runtime_error(msg) {}
};

class InsufficientFundsException : public runtime_error
{
public:
    InsufficientFundsException(const string &msg) : runtime_error(msg) {}
};

class BankAccount
{
private:
    string name;
    int accountNumber;
    double balance;

public:
    BankAccount(string n, int accNo, double bal = 0.0)
        : name(n), accountNumber(accNo), balance(bal) {}

    void deposit(double amount)
    {
        if (amount <= 0)
        {
            throw InvalidAmountException("Deposit amount must be positive!");
        }
        balance += amount;
        cout << "Deposited ₹" << amount << " successfully.\n";
    }

    void withdraw(double amount)
    {
        if (amount <= 0)
        {
            throw InvalidAmountException("Withdrawal amount must be positive!");
        }
        if (amount > balance)
        {
            throw InsufficientFundsException("Insufficient balance!");
        }
        balance -= amount;
        cout << "Withdrawn ₹" << amount << " successfully.\n";
    }

    void display() const
    {
        cout << "\n--- Account Details ---\n";
        cout << "Account Holder: " << name << endl;
        cout << "Account Number: " << accountNumber << endl;
        cout << "Current Balance: ₹" << balance << endl;
        cout << "------------------------\n";
    }

    double getBalance() const { return balance; }
    int getAccountNumber() const { return accountNumber; }
    string getAccountHolderName() const { return name; }
};

// Composition: Customer has-a BankAccount
class Customer
{
private:
    string customerName;
    int customerId;
    BankAccount account;

public:
    Customer(const string &cname, int cid, int accNo, double initialBalance = 0.0)
        : customerName(cname), customerId(cid), account(cname, accNo, initialBalance) {}

    void deposit(double amount)
    {
        account.deposit(amount);
    }

    void withdraw(double amount)
    {
        account.withdraw(amount);
    }

    void displayCustomer() const
    {
        cout << "\n=== Customer Info ===\n";
        cout << "Customer Name: " << customerName << endl;
        cout << "Customer ID  : " << customerId << endl;
        account.display();
    }

    int getCustomerId() const { return customerId; }
    string getCustomerName() const { return customerName; }
    int getAccountNumber() const { return account.getAccountNumber(); }
};

int main()
{
    string name;
    int accNo;
    int custId;
    double initialBalance;

    cout << "Enter customer name: ";
    getline(cin, name);
    cout << "Enter customer ID: ";
    cin >> custId;
    cout << "Enter account number: ";
    cin >> accNo;
    cout << "Enter initial balance: ";
    cin >> initialBalance;

    Customer customer(name, custId, accNo, initialBalance);

    int choice;
    double amount;

    do
    {
        cout << "\n===== BANK MANAGEMENT SYSTEM =====";
        cout << "\n1. Deposit Money";
        cout << "\n2. Withdraw Money";
        cout << "\n3. Display Account Details";
        cout << "\n4. Exit";
        cout << "\nEnter your choice: ";
        cin >> choice;

        try
        {
            switch (choice)
            {
            case 1:
                cout << "Enter amount to deposit: ";
                cin >> amount;
                customer.deposit(amount);
                break;

            case 2:
                cout << "Enter amount to withdraw: ";
                cin >> amount;
                customer.withdraw(amount);
                break;

            case 3:
                customer.displayCustomer();
                break;

            case 4:
                cout << "Exiting\n";
                break;

            default:
                cout << "Invalid choice! Try again.\n";
            }
        }
        catch (const runtime_error &e)
        {
            cerr << "Error: " << e.what() << endl;
        }

    } while (choice != 4);

    return 0;
}
