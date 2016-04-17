/******************************************************************************
 *  Reads two arguments p and q and computes the greatest
 *  common divisor of p and q using Euclid's algorithm.
 *
 *  Based on: http://introcs.cs.princeton.edu/java/23recursion/Euclid.java.html
 *
 *  Remarks
 *  -----------
 *    - may return the negative of the gcd if p is negative
 ******************************************************************************/
package iteso.msc.ada.Homework1;


public class Euclid {

    // Recursive implementation
    public static long gcd(long p, long q) {
        if (q == 0) {
        	return p;
        }
        else {
        	Homework1.c++;
        	return gcd(q, p % q);
        }
    }
}
