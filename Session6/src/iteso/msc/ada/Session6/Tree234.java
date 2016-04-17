package iteso.msc.ada.Session6;

import java.util.ArrayList;


public class Tree234 {

	static public class Node234 {
		private ArrayList<Integer> values = new ArrayList<Integer>(3);
		private ArrayList<Integer> indexes = new ArrayList<Integer>(3);
		private ArrayList<Node234> children = new ArrayList<Node234>(4);
		Node234 parent = null;
		
		public Node234(int value, int index_in_array) {
			values.add(value);
			indexes.add(value);
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
			return children.size() + 1;
		}
		
		public boolean isLeaf() {
			return children.isEmpty();
		}
		
		public int insert(int value, int index) {
			for(int i = 0; i < values.size(); i++) {
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
	
	public Node234 createTree234(int[] array) {
		Node234 root = new Node234(array[0], 0);
		return root;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
	}
}
