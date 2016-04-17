package iteso.msc.ada.Session7;

import java.util.ArrayList;


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

		Node234 getParent() {
			return parent;
		}
		
		void addChild(Node234 child) {
			children.add(child);
			child.parent = this;
		}
		
		void addChildren(Node234 ch1, Node234 ch2, int index) {
			children.set(index, ch1);
			children.add(index + 1, ch2);
			ch1.parent = this;
			ch2.parent = this;			
		}
		
	}
	
	static Node234 createTree234(int[] array) {
		Node234 root = new Node234(array[0], 0);
		for(int i = 1; i < array.length; i ++) {
			Node234 current = root;
			boolean added = false;
			do {
				if(current.getType() == 4) {
					Node234 left  = new Node234(current.getValue(0), current.getIndex(0));
					Node234 right = new Node234(current.getValue(2), current.getIndex(2));
					if(!current.isLeaf()) {
						left.addChild(current.getChild(0));
						left.addChild(current.getChild(1));
						right.addChild(current.getChild(2));
						right.addChild(current.getChild(3));
					}
					if(current.getParent() == null) {  // current is root
						root = new Node234(current.getValue(1), current.getIndex(1));
						root.addChild(left);
						root.addChild(right);
						current = root;
					} else {   // current is not root
						Node234 parent = current.getParent();
						int index = parent.insert(current.getValue(1), current.getIndex(1));
						parent.addChildren(left, right, index);
						current = parent;
					}					
				}
				else if(current.isLeaf()) {
					current.insert(array[i], i);
					added = true;
				} else {
					if(current.getType() == 2) {
						if(array[i] < current.getValue(0)) current = current.getChild(0);
						else current = current.getChild(1);
					} else {  // type = 3
						if(array[i] < current.getValue(0)) current = current.getChild(0);
						else if(array[i] < current.getValue(1)) current = current.getChild(1);
						else current = current.getChild(2);
					}					
				}
			} while(!added);
		}		
		return root;
	}
	
	static void print(Node234 node, int spaces) {
		for(int s = 0; s < spaces; s ++) System.out.print(' ');
		System.out.println(node.values);
		if(node.isLeaf()) return;
		if(node.getType() == 2) {
			print(node.getChild(0), spaces + 1);
			print(node.getChild(1), spaces + 1);
		} else {  // type = 3
			print(node.getChild(0), spaces + 1);
			print(node.getChild(1), spaces + 1);
			print(node.getChild(2), spaces + 1);			
		}
	}
	
	static int calls = 0;
	
	static int indexOf(Node234 node, int value) {
		calls ++;
		int i = node.values.indexOf(value);
		if(i >= 0) return node.getIndex(i);
		
		if(node.isLeaf()) return -1;
		
		if(node.getType() == 2) {
			if(value < node.getValue(0)) return indexOf(node.getChild(0), value);
			return indexOf(node.getChild(1), value);
		}
		
//		type = 3
		if(value < node.getValue(0)) return indexOf(node.getChild(0), value);
		if(value < node.getValue(1)) return indexOf(node.getChild(1), value);
		return indexOf(node.getChild(2), value);			
	}
	
	public static void main(String[] args) {
		int array[] = {3, 1, 5, 6, 2, 0, 7, 4}; // {1, 2, 3, 4, 5, 6, 7, 8, 9};
//		int aPasar = 1 + (int) (27 * Math.random());
//		System.out.println(aPasar);
		int N = 1024 * 1024;
		int bigArray[] = Utils.createIntArray(N, -N, N);
		Node234 root = createTree234(bigArray);
//		print(root, 0);
		System.out.println(indexOf(root, N + 1));
		System.out.println(calls);
	}
}
