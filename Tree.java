package AVL;

public class Tree {
	
	TreeNode root;
	public Tree(int value) {
		this.root = new TreeNode(value);
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
	

	
	public String middleSpacing(int level, int height, String spacing) {
		String result = spacing;
		for(int i = 0; i < height - level; i++) {
			result += result + " ";
		}
		return result;
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
