/* Sample program. Template. */
import java.util.Scanner;


public class Abc {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		for(int n = 0; n < N; n++) {
			int i1 = sc.nextInt();
			int i2 = sc.nextInt();
			int i3 = sc.nextInt();
			String s = sc.next();

			int A, B, C;
			// Find out A
			if(i1 < i2 && i1 < i3) A = i1;
			else if(i2 < i1 && i2 < i3) A = i2;
			else A = i3;
			// Find out B
			if(i1 < i2 && i1 > i3) B = i1;
			else if(i2 < i1 && i2 > i3) B = i2;
			else B = i3;
			// Find out C
			if(i1 > i2 && i1 > i3) C = i1;
			else if(i2 > i1 && i2 > i3) C = i2;
			else C = i3;
			
			for(int i = 0; i < 3; i ++) {
				char c = s.charAt(i);
				switch(c) {
					case 'A': System.out.print(A + " "); break;
					case 'B': System.out.print(B + " "); break;
					default : System.out.print(C + " ");
				}
			}
			System.out.println();
		}
		sc.close();
	}
}

//EOF
