package com.cjx.container;

public class TestBinarySearchTree {

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
//		tree.insert(4);
//		tree.insert(2);
//		tree.insert(1);
//		tree.insert(3);
//		tree.insert(6);
//		tree.insert(5);
//		tree.insert(7);
		int i = 1;
		while(i < 8) {
			tree.insert(i++);
		}
		tree.printTree();
	}
}
