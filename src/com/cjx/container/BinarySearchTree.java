package com.cjx.container;

/**
 * 二叉查找树
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
	//根节点
	private BinaryNode<T> root;
	//清空整棵树
	public void makeEmpty() {
		root = null;
	}
	//是否为空树
	public boolean isEmpty() {
		return root == null;
	}
	//是否包含值为x的节点
	public boolean contains(T x) {
		return contains(x, root);//从根节点开始找
	}
	//节点r下是否包含x节点
	private boolean contains(T x, BinaryNode<T> r) {
		if(r == null) {
			return false;//是空树就不用找了
		}
		//和当前节点做比较
		int compareResult = x.compareTo(r.element);
		//<就往左子树找
		if(compareResult < 0) {
			return contains(x, r.left);//递归调用，把左子节点当作根节点
			//>就往右子树找
		}else if(compareResult > 0) {
			return contains(x, r.right);//递归调用，把右子节点当作根节点
		}else {
			return true;//找到x节点
		}
	}
	//找值最小的节点
	public T findMin() throws Exception {
		if(isEmpty()) {
			throw new Exception("findMin之空树");
		}
		return finMin(root).element;
	}
	private BinaryNode<T> finMin(BinaryNode<T> r) {
		if(r == null) {
			return null;
		}
		//往左子节点找
		while(r.left != null) {
			r = r.left;
		}
		return r;
	}
	//找值最大的节点
	public T findMax() throws Exception {
		if(isEmpty()) {
			throw new Exception("findMax之空树");
		}
		return findMax(root).element;
	}
	private BinaryNode<T> findMax(BinaryNode<T> r) {
		if(r == null) {
			return null;
		}
		//往左子节点找
		while(r.right != null) {
			r = r.right;
		}
		return r;
	}
	//插入一个节点
	public void insert(T x) {
		root = insert(x, root);
	}
	//有返回值是为了方便递归调用
	private BinaryNode<T> insert(T x, BinaryNode<T> r) {
		if(r == null) {
			return new BinaryNode<>(x, null, null);
		}
		int compareResult = x.compareTo(r.element);
		//<就往左子树找 递归调用，直到合适的位置没有其他节点就插入
		if(compareResult < 0) {
			r.left = insert(x, r.left);//
			//>就往右子树找
		}else if(compareResult > 0) {
			r.right = insert(x, r.right);
		}else {
			;//有重复节点 什么也不用做
		}
		return r;
	}
	//移除节点
	public void remove(T x) {
		root = remove(x, root);
	}
	//移除节点（需要找一个合适的子节点代替当前节点）
	private BinaryNode<T> remove(T x, BinaryNode<T> r) {
		if(r == null) {
			return r;//没找到要移除的项 什么也不用做
		}
		int compareResult = x.compareTo(r.element);
		//<就往左子树找 递归调用，直到合适的位置没有其他节点就插入
		if(compareResult < 0) {
			r.left = remove(x, r.left);//
			//>就往右子树找
		}else if(compareResult > 0) {
			r.right = remove(x, r.right);
			//找到节点 并且这个节点有两个子节点
		}else if(r.left != null&&r.right != null){
			//找到右子树中最小的节点，把它的值赋给当前节点
			//这样当前节点就比右子树所有节点小，比左子树所有节点大
			//或者找左子树中最大的节点也行
			r.element = finMin(r.right).element;
			//把上一步找到的节点再删除 这样就把要删除的节点删除了
			r.right = remove(r.element, r.right);
			//找到节点 并且这个节点有一个子节点
		}else {
			//把子节点取代当前节点 要删的节点就被删除了
			r = (r.left != null)?r.left:r.right;
		}
		return r;
	}
	//打印
	public void printTree() {
		if(isEmpty()) {
			System.out.println("空树");
		}else {
			printTree(root, 0);
		}
	}
	//前序遍历 当前=>左子节点=>右子节点
	private void printTree(BinaryNode<T> r, int depth) {
		if(r != null) {
			for(int i=0; i<depth; i++) {
				System.out.print("-");
			}
			System.out.println(r.element);
			depth++;
			printTree(r.left, depth);
			printTree(r.right, depth);
		}
	}
	//内部节点类
	private static class BinaryNode<T>{
		private T element;//节点的值
		private BinaryNode<T> left;//左子节点
		private BinaryNode<T> right;//右子节点
		//构造方法
		private BinaryNode(T ele){
			this(ele, null, null);
		}
		private BinaryNode(T ele, BinaryNode<T> lf, BinaryNode<T> rt) {
			element = ele; left = lf; right = rt;
		}
	}
}