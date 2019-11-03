package com.cjx.container;

public class TestMyTreeMap {

	public static void main(String[] args) {
		MyTreeMap<Integer>map = new MyTreeMap<>();
		for(int i=1; i<6; i++) {
			map.insert(i);
		}
//		System.out.println(map.root.element);
//		System.out.println(map.root.left.element);
//		System.out.println(map.root.right.element);
//		System.out.println(map.root.left.left.element);
//		System.out.println(map.root.left.right.element);
//		System.out.println(map.root.right.left.element);
//		System.out.println(map.root.right.right.element);
//		map.printTree();
	}

}
