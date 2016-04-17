
public class Seis {
	static int getValue(char c) {
		// Java source file must be saved as UTF-8 (if '<smileyFace>'... are going to be used)
		// Common mono-spaced fonts (Courier, Courier New, Consolas) don't have the correct character symbol
		// But as long as the character is stored correctly, no issues.
		//       Smiley Face - 0x263A
		//         Copyright - 0x00A9
		// Small/Lower Delta - 0x03B4
		// {6, #, A, , f, @, 3, ©, }
		int[] syms = {'6', '#', 'A', 0x263A, 'f', '@', '3', 0x00A9, 0x03B4};
		for (int i = 0; i < syms.length; i++) {
			if(c == syms[i]) return i + 1;
		}
		return -1;
	}

	static int hashcode(String serviceCode) {
		int HASH_LIST_SIZE = 500;
		int h = getValue(serviceCode.charAt(serviceCode.length() - 1));
		for(int i = serviceCode.length() - 2; i >= 0; i --) {
			h = (h * 9 + getValue(serviceCode.charAt(i))) % HASH_LIST_SIZE;
		}
		return h;
	}
	
	public static void main(String[] args) {
		char[] arr = {'#', 'f', '6', '@', 0x03B4};
		String serviceCode = new String(arr);
		
		System.out.println("Code: " + serviceCode + "; hascode: " + hashcode(serviceCode));
		
		int[] syms = {'6', '#', 'A', 0x263A, 'f', '@', '3', 0x00A9, 0x03B4};
		for (int i = 0; i < 9; i++) {
			System.out.println("Character: " + (char)syms[i] + "; hashcode: " + hashcode(Character.toString((char)syms[i])));
		}
	}
}
