
import java.io.BufferedReader;
import java.io.FileReader;

public class HashSearch {

	static int HASH_LIST_SIZE = 0;
	
	
	static class Customer {
		String rfc;
		String name, address;
		int index;
		
		public Customer(String rfc, String name, String address, int index) {
			this.rfc     = rfc;
			this.name    = name;
			this.address = address;
			this.index   = index;
		}
	}
	
	static class CustomerNode {
		Customer customer;
		CustomerNode next;
		
		CustomerNode(Customer customer) {
			this.customer = customer;
			this.next = null;
		}
	}
	
	static CustomerNode[] createCustomerHashList(Customer[] customers) {
		CustomerNode[] hashList = new CustomerNode[HASH_LIST_SIZE];
		for(Customer c : customers) {
			int h = hashcode(c.rfc);
			CustomerNode nodeToAdd   = new CustomerNode(c);
			CustomerNode currentNode = hashList[h];
			if(currentNode == null) {
				hashList[h] = nodeToAdd;
			} else {
				while(currentNode.next != null) currentNode = currentNode.next;
				currentNode.next = nodeToAdd;
			}
		}		
		return hashList;
	}
	
	static Customer[] readCustomers(String filename) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine().trim();
		final int COUNT = Integer.parseInt(line);
		Customer[] customers = new Customer[COUNT];
		for(int i = 0; i < COUNT; i ++) {
			line = br.readLine();
			String[] rowData = line.split("\t");
			customers[i] = new Customer(rowData[1].trim(), rowData[0].trim(), rowData[2].trim(), i);
		}
		br.close();
		return customers;
	}
	
	static int getValue(char c) {
//		0 .. 9 -->   0 .. 9
//		A .. Z -->  10 .. 35
		if(Character.isDigit(c)) return c - '0'; 
		return c - 'A' + 10;
	}

	static int hashcode(String rfc) {
		int h = getValue(rfc.charAt(0));
		for(int i = 1; i < rfc.length(); i ++) {
			h = (h * 36 + getValue(rfc.charAt(i))) % HASH_LIST_SIZE;
		}
		return h;
	}
	
	public static void main(String[] args) throws Exception {
		Customer[] customers = readCustomers("Clientes.txt");
		HASH_LIST_SIZE = customers.length;
//		System.out.println(HASH_LIST_SIZE);
		System.out.println(hashcode(customers[0].rfc));
//		System.out.println(hashcode(customers[1].rfc));
//		System.out.println(hashcode(customers[2].rfc));
//		System.out.println(hashcode(customers[3].rfc));
		CustomerNode[] hashList = createCustomerHashList(customers);
		System.out.println(hashList.length);
		
		System.out.println(hashList[383].customer.rfc);
	}
	
	
}
