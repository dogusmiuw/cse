package Algorithms.AmortizedAnalysis;

import java.util.Random;

public class ArrayCopy {

    static int[] array = new int[1];
    static int addedCount = 0;
    static int copyCount = 0;


    public static void main(String[] args) {

        //  Amortized Analysis

        for (int i = 0; i < 1000000; i++) {
            array = add(array, new Random().nextInt(100));
        }


        System.out.println("Added count: " + addedCount);
        System.out.println("Copy count: " + copyCount);


        /*

        Amortized analysis is a method used to show that the average transaction cost is small even
        if a single transaction in a series of transactions is expensive.

        Although we take the averages, probability is not taken into account.
        Amortized analysis ensures the average worst-case performance of each transaction.


        There are three types of amortized analysis:

        1. Aggregate Analysis
        2. Accounting Analysis
        3. Potential Analysis


        1 - > Aggregate Analysis:

            Although Aggregate Analysis is simple, it lacks the precision of the other two methods.
            Calculation and potential methods allow assigning a certain amortized cost to each transaction.
            The aggregate method, where the total running time for a sequence of operations is analyzed.


        2 - > Accounting Analysis:

            Accounting Analysis is the most common method of amortized analysis.
            The accounting (or banker's) method, where we impose an extra charge on inexpensive operations and use it to pay for expensive operations later on.
            For this method, we think about amortized cost as being a “charge” that we assign to each operation.
            Each time we encounter an operation, we try to pay for it using this “charge”.
            If the operation actually costs less than our “charge”, we stash the change in a bank account. If the operation actually costs more than our “charge”, we can dip into our bank account to cover the cost.
            The idea is that we want to save enough money during our “cheaper” operations to pay for any “expensive” operations we might encounter later on.


        3 - > Potential Analysis:

            The potential method, in which we derive a potential function characterizing the amount of extra work we can do in each step.
            This potential either increases or decreases with each successive operation, but cannot be negative.




         */

        // Time Analysis

        /*

       Expensive operation

           Expensive operations are those that take a long time to complete.
           2,4,8,16,32 .... (2^n) While adding the elements that will come to the indexes,
           because the copying process is performed,
           n copying operations are performed as load and 1 addition operation is performed.
           In total, n +1 transactions are made
           O(n) = n + 1 = n


        Normal operation

            3,5,6,7,9,10,11,12 .... while adding future elements to the indexes,
            only 1 insertion operation is performed because there is no copying operation.
            In total, 1 transactions are made
            O(n) = 1

        In this way, the average time complexity of the algorithm is O(1).
        Because the number of operations performed is constant.
        The number of operations performed is not affected by the number of elements in the array.


        Calculations:

        Consider that there are n additions. The worst time to make an addition is Θ(n).
        So the worst time for n additions is n . Θ(n) = Θ(n^2). (Wrong)

        FALSE! In fact, the worst-case time required for n additions is just Θ(n) << Θ(n^2).


        Total Cost => Cost(Normal) + Cost(Expensive)
        Cost of each operation => ( Cost(Normal) + Cost(Expensive) ) / n

        Total Cost => Normal + Expensive
        Expensive Cost =>  2 ^ n     T(n) = T(n/2) + Θ(n).  T(n) = Θ(n)

        Total Normal Cost => 1 * n
        Total Expensive Cost => Θ(n)

        Total Cost => n + Θ(n)
        ******* Total Cost => Θ(n)  (True)

        Cost of each operation => Θ(n) / n = Θ(1)  (True)

         */

    }


    public static int[] add(int[] array, int element) {

        if (addedCount == array.length) {
            int[] newArray = new int[array.length * 2];
            copy(array, newArray);
            array = newArray;
        }

        array[addedCount] = element;
        addedCount++;

        return array;

    }

    public static void copy(int[] array, int[] newArray) {

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
            copyCount++;
        }

    }

}
