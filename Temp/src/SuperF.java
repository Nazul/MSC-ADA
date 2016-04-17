
public class SuperF {

	static long superf(int x, int y, int z) {
		if(x <= 0 || y <= 0 || z <= 0)
			return 0;
		else
			return 1 + (superf(x - 2, y - 1, z - 1) + superf(x - 1,  y - 2, z - 1) + superf(x - 1, y - 1, z - 2)) % 57413;
	}
	
	public static void main(String[] args) {
		// "Classic" recursion
		System.out.println(superf(2, 2, 3));
		System.out.println(superf(5, 6, 7));
		System.out.println(superf(10, 9, 8));
		// Quite slow
		//System.out.println(superf(30, 29, 28));
		//System.out.println(superf(95, 96, 97));
	}
}
