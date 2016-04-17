package iteso.msc.ada.Homework1;

public class Utils {
	// Basic string comparison. Assumes a and b are not null
	public static boolean StringEquals(String a, String b) {
		if (a.length() != b.length())
			return false;
		for (int i = 0; i < a.length(); i++)
			if (a.charAt(i) != b.charAt(i))
				return false;
		return true;
	}
	
	// Bubble sort of an array of ints
	// Reference: http://mathbits.com/MathBits/Java/arrays/Bubble.htm
	public static int[] BubbleSort(int[] num) {
	    int j;
	    boolean flag = true;
	    int temp;

	    while (flag) {
	    	flag= false;
			for(j=0; j < num.length -1; j++) {
				if (num[j] < num[j+1]) {
					temp = num[ j ];
					num[j] = num[j + 1];
					num[j + 1] = temp;
					flag = true;
				} 
			} 
	    }
	    return num;
	}
	
	// Get median from an unsorted array of ints
	// Reference: http://stackoverflow.com/questions/4191687/how-to-calculate-mean-median-mode-and-range-from-a-set-of-numbers
	public static float Median(int[] values) {
		int[] sortedValues = BubbleSort(values);
		int middle = sortedValues.length/2;
		
	    if (sortedValues.length % 2 == 1) {
	        return sortedValues[middle];
	    } else {
	        return (sortedValues[middle - 1] + sortedValues[middle]) / 2;
	    }
	}
	
	// Determinate whether or not N is prime
	// Reference: http://introcs.cs.princeton.edu/java/13flow/Prime.java.html
	public static boolean Prime(int N) {
		if (N < 2)
			return false;

		// try all possible factors i of N
		// if if N has a factor, then it has one less than or equal to sqrt(N),
		// so for efficiency we only need to check i <= sqrt(N) or equivalently i*i <= N
		for (long i = 2; i*i <= N; i++)
		    // if i divides evenly into N, N is not prime, so break out of loop
		    if (N % i == 0)
		        return false;

		return true;
	}

	// Based on Euclid.java
    public static int div3(int x, int numDivs) {
        if (x == 1)
        	return numDivs;
        else if (x == 2)
        	return ++numDivs;
        else
        	return div3(x / 3, ++numDivs);
    }
    
    // List all pairs (a, b) from 1 to N where:
    // cos(a) * sin(b) <= b / 2 * a
    public static void Pairs(int N) {
    	for (double a = 1; a <= N; a++)
    		for (double b = 1; b <= N; b++)
    			if ((Math.cos(a) * Math.sin(b)) <= (b / (2 * a)))
    				System.out.println("(" + a + ", " + b + ")");
    }
}
