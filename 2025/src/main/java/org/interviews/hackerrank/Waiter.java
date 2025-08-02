package org.interviews.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Waiter {

  /*
   * Complete the 'waiter' function below.
   *
   * The function is expected to return an INTEGER_ARRAY.
   * The function accepts following parameters:
   *  1. INTEGER_ARRAY number
   *  2. INTEGER q
   */

  public static List<Integer> waiter(List<Integer> number, int q) {
    List<Integer> primes = getPrimes(q);
    List<Integer> answer = new ArrayList<>();
    Stack<Integer> firstStack = new Stack<>();
    Stack<Integer> secondStack = new Stack<>();
    for(Integer plate: number){
      firstStack.push(plate);
    }

    for(int prime: primes){
      Stack<Integer> tempStack = new Stack<>();
      while(!firstStack.isEmpty()) {
        if(firstStack.peek()%prime == 0){
          secondStack.push(firstStack.pop());
        } else{
          tempStack.push(firstStack.pop());
        }
      }
      while(!secondStack.isEmpty()){
        answer.add(secondStack.pop());
      }
      firstStack = tempStack;
    }
    while(!firstStack.isEmpty()){
      answer.add(firstStack.pop());
    }
    return answer;
  }

  public static List<Integer> getPrimes(int q){
    List<Integer> primes = new ArrayList<>();
    int num = 2; // Start checking for primes from 2

    while (primes.size() < q) {
      boolean isPrime = true;
      // Check for divisibility from 2 up to the square root of num
      for (int i = 2; i * i <= num; i++) {
        if (num % i == 0) {
          isPrime = false;
          break; // Not a prime, no need to check further
        }
      }
      if (isPrime) {
        primes.add(num); // Add the prime number to the list
      }
      num++; // Move to the next number to check
    }
    return primes;
  }



  public static void main(String[] args) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int q = Integer.parseInt(firstMultipleInput[1]);

    String[] numberTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    List<Integer> number = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      int numberItem = Integer.parseInt(numberTemp[i]);
      number.add(numberItem);
    }

    List<Integer> result = waiter(number, q);
    System.out.println(result.toString());

//    for (int i = 0; i < result.size(); i++) {
//      bufferedWriter.write(String.valueOf(result.get(i)));
//
//      if (i != result.size() - 1) {
//        bufferedWriter.write("\n");
//      }
//    }
//
//    bufferedWriter.newLine();

    bufferedReader.close();
//    bufferedWriter.close();
  }
}
