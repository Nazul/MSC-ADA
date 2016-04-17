//**********************************************************
// ITESO - Maestría en Sistemas Computacionales
// Análisis y Diseño de Algoritmos
// Tarea 5, A. Justicia laboral (Justicia.java)
//
// Erick González (705070)
// Mario Contreras (705080)
//
//*********************************************************
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
/*
Sample Input
2
3
1000 2
850 0
950 0
5
150 3
120 0
130 1
80 0
155 0

Sample Output
JUSTICIA

155
120
150
80
130
*/

class Justicia {
	// Auxiliary class - Represents an employee and contains an array of employees (direct reports)
	static class Employee {
		int salary;
		int subCount;
		Employee[] subordinates = null;
		Employee(int s, int sc) {
			salary = s;
			subCount = sc;
			if (sc > 0) subordinates = new Employee[sc];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// How many test cases
		int T = sc.nextInt();
		
		// For each test case
		for(int t = 0; t < T; t++) {
			Queue<Employee> managers = new ArrayDeque<Employee>();
			// How many employees
			int N = sc.nextInt();
			Employee[] employees = new Employee[N];
			// For each employee (nodes)
			Employee currentManager = null;
			int i = 0, j = 0;
			for(int n = 0; n < N; n++) {
				// Get salary and how many subordinates
				int s = sc.nextInt();
				int c = sc.nextInt();
				// Create the new employee and add it to the array
				employees[n] = new Employee(s, c);
				// If this employee is a manager, add it to managers queue
				if(c > 0) managers.add(employees[n]);
				// For each level, we are going to add subordinates as needed
				if(i == j) {
					// All previous managers have their subordinates, select a new manager to work with
					currentManager = managers.remove();
					j = currentManager.subCount;
					i = 0;
				}
				if(currentManager != employees[n]) {
					// Add a subordinate (except when currentManager == root)
					currentManager.subordinates[i++] = employees[n];
				}
			}
			// Ok, we have all the data, now we have search for justice!
			if(justice(employees[0])) {
				// Everything is Ok
				System.out.println("JUSTICIA");
			}
			else {
				// Swap salaries until we have reach justice
				while(!justice(employees[0]));
				// print employees salaries
				for(Employee e : employees) {System.out.println(e.salary);}
			}
			// Line break for each test case
			System.out.println();
		}
//		System.out.println();
		sc.close();
	}

	// Recursive. Check if current subordinates earn more than the current employee
	// If so, there is no justice and salary is swapped, but that might lead to injustice again.
	static boolean justice(Employee e) {
		boolean retval = true;
		if(e == null) return true;
		if(e.subordinates == null) return true;
		for(Employee s : e.subordinates) {
			if(s.salary > e.salary) {
				retval = false;
				int temp = s.salary;
				s.salary = e.salary;
				e.salary = temp;
			}
			retval = retval && justice(s);
		}
		return retval;
	}
}

//EOF
