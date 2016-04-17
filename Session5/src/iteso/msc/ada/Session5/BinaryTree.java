package iteso.msc.ada.Session5;


public class BinaryTree {
	public class Node {
		int value;
		int index;
		Node left = null;
		Node right = null;
		
		public Node(int value, int index) {
			this.value = value;
			this.index = index;
		}
	}
	
	Node root = null;
	
	/// ToDo: Terminar la implementación
	Node createTree(int[] array) {
		root = null;
		
		for(int i = 0; i < array.length; i++) {
			Node n = new Node(array[i], i);

			if(root == null)
				root = n;
			else {
				Node currentNode = root;
				do {
					if (n.value < currentNode.value && currentNode.left == null) {
						currentNode.left = n;
						currentNode = null;
					}
					else if (currentNode.right == null) {
						currentNode.right = n;
						currentNode = null;
					}
					//else if ()
				} while (true);
			}
		}
		return root;
	}
}
