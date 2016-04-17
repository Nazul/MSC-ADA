/******************************************************************************
 *  Computes first N Fibonacci numbers.
 *
 *  WARNING:  this program is spectacularly inefficient and is meant
 *            to illustrate a performance bug, e.g., set N = 45.
 *
 * Based on: http://introcs.cs.princeton.edu/java/23recursion/Fibonacci.java.html
 * 
 *   Remarks
 *   -------
 *    - The 93rd Fibonacci number would overflow a long, but this
 *      will take so long to compute with this function that we
 *      don't bother to check for overflow.
 ******************************************************************************/
package iteso.msc.ada.Homework1;


public class Fibonacci {
    public static long fib(long n) {
        if (n <= 1) return n;
        else return fib(n-1) + fib(n-2);
    }
}
