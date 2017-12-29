package AVL;

public class Tree {
	
	TreeNode root;
	public Tree(int value) {
		this.root = new TreeNode(value);
	}
	
	public Tree(int[] input) {
		for(int i = 0; i < input.length; i++) {
			insert(input[i]);
		}
	}
	
	
	public void insert(int data) {
		insert(data, this.root);
	}
	
	public void insert(int data, TreeNode current) {
		if(this.root == null) {
			this.root = new TreeNode(data);
		}else {
			if(data <= current.data && current.left == null) {
				current.left = new TreeNode(data);
			}else if(data <= current.data) {
				insert(data, current.left);
			}else if(data > current.data && current.right == null) {
				current.right = new TreeNode(data);
			}else {
				insert(data, current.right);
			}
		}
	}
	
	public int height() {
		return height(this.root);
	}
	
	public int height(TreeNode current) {
		if(current == null || (current.left == null && current.right == null)) {
			return 0;
		}
		return Math.max(height(current.left), height(current.right)) + 1;
	}
	public void draw() {
		draw(1);
	}
	
	
	public void draw(int spacing) {
		int[] array = makeArray();
		int height = height();
		String space = "";
		for(int i = 0; i < spacing*2 - 1; i++) {
			space += " ";
		}
		String start = "";
		for(int i  = 0; i < spacing; i++) {
			start += " ";
		}
		for(int i = 0; i <= height; i++) {
			String startingSpace = "";
			for(int j = 1; j <= Math.pow(2, height - i) - 1; j++) {
				startingSpace += start;
			}
			System.out.print(startingSpace);
			String middle = middleSpacing(i, height, space);
			for(int j = (int)Math.pow(2, i) - 1; j < (int)Math.pow(2, i + 1) -1 ; j++) {
				System.out.print(array[j] + middle);
			}
			System.out.println();
			int verticalCount = (int)(Math.pow(2, height - i) - 1)*(spacing) - (int)(Math.pow(2, height - i - 1) - 1)*(spacing) - 1;
			if(verticalCount < 0) {
				verticalCount = 0;
			}
			for(int j = 1; j <= verticalCount; j++) {
				// print intitial spaces
				for(int k = 0; k < (int)(Math.pow(2, height - i) - 1)*(spacing) - j; k++) {
					System.out.print(" ");
				}
				// forward slash and backward slash loop
				for(int k = 0; k < (int)Math.pow(2, i); k++) {
					System.out.print("/");
					for(int l = 0; l < 2*j - 1; l++) {
						System.out.print(" ");
					}
					System.out.print("\\");
					for(int l = 0; l < middle.length() - 2*(j); l++) {
						System.out.print(" ");
					}	
				}
				System.out.println();
			}
		}
	}
	
	public void AVLInsert(int num) {
		this.root = AVLInsert(num, this.root);
	}
	
	public TreeNode AVLInsert(int num, TreeNode current) {
		if(current == null) {
			return new TreeNode(num);
		}

		if(current.data < num) {
			
			current.right = AVLInsert(num, current.right);
			if(current.right == null) {
				current.rightHeight = 0;
			}else {
				current.rightHeight = height(current.right) + 1;
			}
			if(current.left == null) {
				current.leftHeight = 0;
			}else {
				current.leftHeight = height(current.left) + 1;
			}

			if(Math.abs(current.rightHeight - current.leftHeight) >= 2) {
				if(current.right.right == null) {
					current.right.rightHeight = 0;
				}else {
					current.right.rightHeight = height(current.right.right) + 1;
				}
				if(current.right.left == null) {
					current.right.leftHeight = 0;
				}else {
					current.right.leftHeight = height(current.right.left) + 1;
				}
				if(current.right.rightHeight < current.right.leftHeight) {
					current.right = rotateRight(current.right);
				}
				return rotateLeft(current);
			}
		}else {
			
			current.left = AVLInsert(num, current.left);
			if(current.right == null) {
				current.rightHeight = 0;
			}else {
				current.rightHeight = height(current.right) + 1;
			}
			if(current.left == null) {
				current.leftHeight = 0;
			}else {
				current.leftHeight = height(current.left) + 1;
			}
			if(Math.abs(current.leftHeight - current.rightHeight) >= 2) {
				
				if(current.left.left == null) {
					current.left.leftHeight = 0;
				}else {
					current.left.leftHeight = height(current.left.left) + 1;
				}
				if(current.left.right == null) {
					current.left.rightHeight = 0;
				}else {
					current.left.rightHeight = height(current.left.right) + 1;
				}
				if(current.left.leftHeight < current.left.rightHeight) {
					current.left = rotateLeft(current.left);
				}
				return rotateRight(current);
			}
		}
		return current;
	}
	
	public String middleSpacing(int level, int height, String spacing) {
		String result = spacing;
		for(int i = 0; i < height - level; i++) {
			result += result + " ";
		}
		return result;
	}
	
	
	public void delete(int n) {
		this.root = delete1(n, this.root);
	}
	
	public TreeNode rotateRight(TreeNode node) {
		TreeNode left = node.left;
		node.left = left.right;
		left.right = node;
		return left;
	}
	
	public TreeNode rotateLeft(TreeNode node) {
		TreeNode right = node.right;
		node.right = right.left;
		right.left = node;
		return right;
	}
	
	public TreeNode delete1(int value, TreeNode current) {
		if(current == null) {
			return null;
		}
		if(current.data < value) {
			current.right = delete1(value, current.right);
		}else if(current.data > value) {
			current.left = delete1(value, current.left);
		}else {
			if(current.left == null && current.right == null) {
				return null;
			}else if(current.left == null) {
				return current.right;
			}else if(current.right == null) {
				return current.left;
			}else {
				TreeNode Right = current.right;
				Right.right = delete1(Right.data, Right);
				Right.left = current.left;
				return Right;
			}
		}
		return current;
	}
	
	public int[] makeArray() {
		int height = height();
		int[] array = new int[(int)(Math.pow(2, height + 1) - 1)];
	    makeArray(array, this.root, 0);
	    return array;
	}
	
	public void makeArray(int[] array, TreeNode current, int index) {
		if(current != null) {
			array[index] = current.data;
			TreeNode leftChild = current.left;
			TreeNode rightChild = current.right;
			makeArray(array, leftChild, 2*index + 1);
			makeArray(array, rightChild, 2*index + 2);
		}
	}
	
public static class TreeNode{
	public int data;
	public TreeNode left;
	public TreeNode right;
	public int leftHeight;
	public int rightHeight;
	
	public TreeNode(int data, TreeNode left, TreeNode right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	public TreeNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
}

}
