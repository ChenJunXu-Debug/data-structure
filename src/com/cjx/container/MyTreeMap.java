package com.cjx.container;

import java.util.Comparator;

/**
 * AVL树
 * @param <T>
 */
public class MyTreeMap<T> {
	private AvlNode<T> root;
	private Comparator<? super T> cmp;
	private static final int ALLOWED_IMBALANCE = 1;
	public MyTreeMap() {
		this(null);
	}
	public MyTreeMap(Comparator<? super T> c) {
		root = null; cmp = c;
	}
	private int myCompare(T lch, T rch) {
		if(cmp !=null) {
			return cmp.compare(lch, rch);
		}else {
			return ((Comparable<T>)lch).compareTo(rch);
		}
	}
	public void makeEmpty() {
		root = null;
	}
	public boolean isEmpty() {
		return root == null;
	}
	public boolean contains(T x) {
		return contains(root, x);
	}

	private boolean contains(AvlNode<T> r, T x) {
		if(r == null) {
			return false;
		}
		int i = myCompare(x, r.element);
		if(i < 0) {
			return contains(r.left, x);
		}else if(i > 0) {
			return contains(r.right, x);
		}else {
			return true;//找到节点
		}
	}

	public T findMin() throws Exception {
		if(isEmpty()) {
			throw new Exception("找最小值之空树异常");
		}
		return findMin(root).element;
	}

	private AvlNode<T> findMin(AvlNode<T> t) {
		AvlNode<T> min = t;
		while(t.left != null) {
			min = t.left;
		}
		return min;
	}
	public T findMax() throws Exception {
		if(isEmpty()) {
			throw new Exception("找最大值之空树异常");
		}
		return findMax(root).element;
	}

	private AvlNode<T> findMax(AvlNode<T> t) {
		AvlNode<T> max = t;
		while(t.right != null) {
			max = t.right;
		}
		return max;
	}

	public void insert(T x) {
		root = insert(x, root);
	}

	private AvlNode<T> insert(T x, AvlNode<T> r) {
		//插入节点
		if(r == null) {
			return new AvlNode(x, null, null);
		}
		int i = myCompare(x, r.element);
		//循环调用 插入节点 并逐级平衡子节点
		if(i < 0) {
			r.left = insert(x, r.left);
		}else if(i > 0) {
			r.right = insert(x, r.right);
		}else {}
		//给整树平衡 并返回整棵树
		System.out.println("="+r.element);
		System.out.println("=="+root.element);
		return balance(r);
	}

	private AvlNode<T> balance(AvlNode<T> r) {
		if(r == null) {
			return r;
		}
		if(height(r.left) - height(r.right)>ALLOWED_IMBALANCE) {
			if(height(r.left.left) >= height(r.left.right)) {
				r = rotateWithLeftChild(r);
			}else {
				r = doubleWithLeftChild(r);
			}
		}
		if(height(r.right) - height(r.left)>ALLOWED_IMBALANCE) {
			if(height(r.right.right) >= height(r.right.left)) {
				r = rotateWithRightChild(r);
			}else {
				r = doubleWithRightChild(r);
			}
		}
		r.height = Math.max(height(r.left), height(r.right)) + 1;
		return r;
	}
	//单旋转
	private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
		AvlNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
		k1.height = Math.max(height(k1.left), k2.height) + 1;
		return k1;
	}
	private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
//		System.out.println(k1.element);
		AvlNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
		k2.height = Math.max(height(k2.right), k1.height) + 1;
		return k2;
	}
	//双旋转
	private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
		k3.left = rotateWithRightChild(k3.left);
		return rotateWithLeftChild(k3);
	}
	private AvlNode<T> doubleWithRightChild(AvlNode<T> k3) {
		k3.right = rotateWithLeftChild(k3.right);
		return rotateWithRightChild(k3);
	}
	private int height(AvlNode<T> r) {
		if(r == null) {
			return -1;
		}else {
			return 1 + Math.max(height(r.left), height(r.right));
		}
	}
	public void printTree() {
		if(isEmpty()) {
			System.out.println("空树");
		}else {
			printTree(root);
		}
	}

	private void printTree(AvlNode<T> r) {
		if(r != null) {
			System.out.println(r.element);
			printTree(r.left);
			printTree(r.right);
		}
	}

	private static class AvlNode<T>{
		T element;//数据
		AvlNode left;//左儿子
		AvlNode right;//右儿子
		int height;//高度
		public AvlNode(T element) {
			this(element, null, null);
		}
		public AvlNode(T element, AvlNode left, AvlNode right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}
		private int height(AvlNode t) {
			return t==null?-1:t.height;
		}
	}
}
