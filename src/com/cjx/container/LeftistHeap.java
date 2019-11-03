/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LeftistHeap
 * Author:   ASUS
 * Date:     2019/10/14 13:22
 * Description: 左式堆
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈左式堆〉
 *
 * @author ASUS
 * @create 2019/10/14
 * @since 1.0.0
 */
public class LeftistHeap<T extends Comparable<? super T>> {
    private Node<T> root;//根节点
    public LeftistHeap(){
        root = null;
    }
    public boolean isEmpty(){
        return root == null;
    }
    public void makeEmpty(){
        root = null;
    }
    public void merge(LeftistHeap<T> rhs){
        if(this == rhs){
            return;
        }
        //合并两树的根节点并返回合并后的根节点就相当于合并了两棵树
        root = merge(root, rhs.root);
        rhs.root = null;
    }
    private Node<T> merge(Node<T>h1, Node<T>h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        if(h1.element.compareTo(h2.element)<0){
            return merge1(h1, h2);
        }else{
            return merge1(h2, h1);
        }
    }

    /**
     * @param h1 数据小的节点
     * @param h2 数据大的节点
     * @return 合并后的节点
     */
    private Node<T> merge1(Node<T>h1, Node<T>h2){
        //直接加在左子节点
        if(h1.left == null){
            h1.left = h2;
        }else{
            //加在右子节点合并
            h1.right = merge(h1.right, h2);
            //右子树比左子树高 交换左右子树
            if(h1.left.npl < h1.right.npl){
                Node<T>tmp = h1.left;
                h1.left = h1.right;
                h1.right = tmp;
            }
            h1.npl = h1.right.npl + 1;
        }
        return h1;
    }
    //插入一个节点
    public void insert(T x){
        root = merge(new Node<T>(x), root);
    }
    public T findMin(){
        return root.element;
    }
    //删除最小节点（root最小） 在合并左右子树
    public T deleteMin() throws Exception {
        if(isEmpty()){
            throw new Exception("deleteMin之空树");
        }
        T element = root.element;
        root = merge(root.left, root.right);
        return element;
    }
    public void printHeap() throws Exception {
        if(root == null){
            throw new Exception("printHeap之空树");
        }
        printHeap(root);
    }
    private void printHeap(Node<T> r){
        System.out.println(r.element + "==" + r.npl);
        if(r.left != null){
            printHeap(r.left);
        }
        if(r.right != null){
            printHeap(r.right);
        }
    }
    private static class Node<T>{
        T element;//节点数据
        Node<T>left;//左儿子
        Node<T>right;//右儿子
        int npl;//当前节点到不具有两个儿子的节点的最短距离

        public Node(T element, Node<T> left, Node<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            npl = 0;
        }
        public Node(T ele){
            this(ele, null, null);
        }
    }
}
