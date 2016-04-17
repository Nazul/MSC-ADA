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
	
	static Customer search(String rfc, CustomerNode[] hashList) {
		int h = hashcode(rfc);
		CustomerNode node = hashList[h];
		while(node != null) {
			if(rfc.equals(node.customer.rfc)) return node.customer;
			node = node.next;
		}
		return null;
	}
	
	static Customer search(String rfc, Customer[] customers) {
		for(Customer c : customers) {
			if(rfc.equals(c.rfc)) return c;
		}
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		Customer[] customers = readCustomers("Clientes.txt");
		HASH_LIST_SIZE = customers.length;
		CustomerNode[] hashList = createCustomerHashList(customers);
		
		final int M = 1_000_000;
		String[] lookupRFCs = new String[M];
		
		for (int i = 0; i < M; i++) {
			int rndIndex = (int)(HASH_LIST_SIZE * Math.random());
			lookupRFCs[i] = customers[rndIndex].rfc;
		}
		
		// M búsquedas por hash
		long start = System.currentTimeMillis();
		for(int i = 0; i < M; i++) {
			search(lookupRFCs[i], hashList);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		// M búsquedas secuencial
		start = System.currentTimeMillis();
		for(int i = 0; i < M; i++) {
			search(lookupRFCs[i], customers);
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
}
