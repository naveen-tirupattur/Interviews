package org.interviews.airbnb.interviewdb;

import java.time.LocalDate;
import java.util.*;

class Payment {
  String id;
  String method;
  LocalDate date;
  int amount;       // original amount
  int remaining;    // remaining amount available for refunds

  public Payment(String id, String method, String date, int amount) {
    this.id = id;
    this.method = method;
    this.date = LocalDate.parse(date);
    this.amount = amount;
    this.remaining = amount;
  }
}

class RefundAllocation {
  int refundRequestId;
  String paymentId;
  String method;
  int refundAmount;

  public RefundAllocation(int refundRequestId, String paymentId, String method, int refundAmount) {
    this.refundRequestId = refundRequestId;
    this.paymentId = paymentId;
    this.method = method;
    this.refundAmount = refundAmount;
  }

  @Override
  public String toString() {
    return "RefundReq#" + refundRequestId +
      " -> Payment " + paymentId +
      " (" + method + "), $" + refundAmount;
  }
}

public class RefundAllocator {

  private static final Map<String, Integer> PRIORITY = new HashMap<>();

  static {
    PRIORITY.put("CREDIT", 1);
    PRIORITY.put("CREDIT_CARD", 2);
    PRIORITY.put("PAYPAL", 3);
  }

  public static List<RefundAllocation> allocateMultipleRefunds(List<Payment> payments, List<Integer> refunds) {
    // Sort payments once by priority and date
    payments.sort((a, b) -> {
      int priA = PRIORITY.get(a.method);
      int priB = PRIORITY.get(b.method);
      if (priA != priB) return Integer.compare(priA, priB);
      return b.date.compareTo(a.date); // most recent first
    });

    List<RefundAllocation> allAllocations = new ArrayList<>();

    int refundRequestId = 1;
    for (int refundAmount : refunds) {
      int remaining = refundAmount;

      for (Payment p : payments) {
        if (remaining == 0) break;
        if (p.remaining == 0) continue; // already fully refunded

        int refund = Math.min(p.remaining, remaining);
        p.remaining -= refund;
        remaining -= refund;

        allAllocations.add(new RefundAllocation(refundRequestId, p.id, p.method, refund));
      }

      refundRequestId++;
    }

    return allAllocations;
  }

  // Demo
  public static void main(String[] args) {
    List<Payment> payments = Arrays.asList(
      new Payment("P1", "CREDIT", "2023-01-10", 40),
      new Payment("P2", "PAYPAL", "2023-01-15", 60),
      new Payment("P3", "CREDIT_CARD", "2023-01-20", 100)
    );

    List<Integer> refunds = Arrays.asList(50, 80, 30);

    List<RefundAllocation> result = allocateMultipleRefunds(payments, refunds);

    for (RefundAllocation r : result) {
      System.out.println(r);
    }
  }
}
