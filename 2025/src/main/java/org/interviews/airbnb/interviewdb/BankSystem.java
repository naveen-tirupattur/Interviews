package org.interviews.airbnb.interviewdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Balance {
  long timestamp;
  int balance;

  public Balance(long timestamp, int balance) {
    this.timestamp = timestamp;
    this.balance = balance;
  }
}

class Account {
  int id;
  int currentBalance;
  List<Balance> balanceList;

  public Account(int id) {
    this.id = id;
    this.currentBalance = 0;
    this.balanceList = new ArrayList<>();
  }
}

public class BankSystem {

  Map<Integer, Account> accountMap = new HashMap<>();

  public void deposit(int id, long timestamp, int amount) {
    if (amount <= 0) throw new IllegalArgumentException("Please specify valid deposit amount");
    Account a = accountMap.getOrDefault(id, new Account(id));
    a.currentBalance += amount;
    a.balanceList.add(new Balance(timestamp, a.currentBalance));
    accountMap.put(id, a);
  }

  public boolean withdraw(int id, long timestamp, int amount) {
    if (!accountMap.containsKey(id)) return false;
    if (amount <= 0)
      throw new IllegalArgumentException("Withdrawal of this amount is not supported");
    Account a = accountMap.get(id);
    if (a.currentBalance - amount < 0) return false;
    a.currentBalance -= amount;
    a.balanceList.add(new Balance(timestamp, a.currentBalance));
    return true;
  }

  public int check(int id) {
    if (!accountMap.containsKey(id)) throw new UnsupportedOperationException("Account doesn't exist");
    Account a = accountMap.get(id);
    return a.currentBalance;
  }

  public int balance(int id, long startTime, long endTime) {
    if (!accountMap.containsKey(id)) throw new UnsupportedOperationException("Account doesn't exist");
    Account a = accountMap.get(id);
    return find(a.balanceList, startTime, endTime);
  }

  public int find(List<Balance> balanceList, long startTime, long endTime) {
    if (balanceList.isEmpty() || endTime < balanceList.get(0).timestamp) return 0;
    int i = findIndex(balanceList, startTime);
    int j = findIndex(balanceList, endTime) - 1;

    if (i > j || i >= balanceList.size()) return 0;

    int startBalance = (i > 0) ? balanceList.get(i - 1).balance : 0;
    int endBalance = balanceList.get(j).balance;
    return endBalance - startBalance;
  }

  public int findIndex(List<Balance> balanceList, long time) {
    int low = 0, high = balanceList.size() - 1;
    int ans = balanceList.size();
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (balanceList.get(mid).timestamp > time) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }
    return ans;
  }

  // Example usage
  public static void main(String[] args) {
    BankSystem bank = new BankSystem();

    bank.deposit(1, 10, 100);
    bank.deposit(1, 20, 200);
    bank.withdraw(1, 30, 50);

    System.out.println(bank.check(1));             // 250
    System.out.println(bank.balance(1, 0, 20));    // 300 (10,20)
    System.out.println(bank.balance(1, 10, 30));   // 150 (20,30)
    System.out.println(bank.balance(1, -5, 30));   // 250 (all)
  }

}
