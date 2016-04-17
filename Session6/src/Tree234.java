import java.util.ArrayList;
import java.util.LinkedList;

public class Tree234 {

	static class Node234 {
		private ArrayList<Integer> values = new ArrayList<Integer>(3);
		private ArrayList<Integer> indexes = new ArrayList<Integer>(3);
		private ArrayList<Node234> children = new ArrayList<Node234>(4);
		private Node234 parent;
		
		public Node234(int value, int index_in_array) {
			values.add(value);
			indexes.add(index_in_array);
			parent = null;
		}
		
		public int getValue(int i) {
			return values.get(i);
		}
		
		public int getIndex(int i) {
			return indexes.get(i);
		}
	
		public Node234 getChild(int i) {
			return children.get(i);
		}
		
		public int getType() {
			return values.size() + 1;
		}
		
		public boolean isLeaf() {
			return children.isEmpty();
		}
	
//		Insertar valor en una hoja
		public int insert(int value, int index) {
			for(int i = 0; i < values.size(); i ++) {
				if(value < values.get(i)) {
					values.add(i, value);
					indexes.add(i, index);
					return i;
				}
			}			

			values.add(value);
			indexes.add(index);
			return values.size() - 1;
		}

	}
	
	Node234 createTree234(int[] array) {
		return null;
	}
	
	public static void main(String[] args) {
		int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9};
//		int aPasar = 1 + (int) (27 * Math.random());
//		System.out.println(aPasar);
		
	}
}
