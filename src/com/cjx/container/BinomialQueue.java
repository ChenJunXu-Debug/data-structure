/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BinomialQueue
 * Author:   ASUS
 * Date:     2019/10/16 10:55
 * Description: 二项队列
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈二项队列〉
 *
 * @author ASUS
 * @create 2019/10/16
 * @since 1.0.0
 */
public class BinomialQueue<T extends Comparable<? super T>> {
    private int currentSize;//当前队列的存放项的总数
    private Node<T> [] trees;//容器（数组+链表）
    private static final int DEFAULT_TREES = 1;//默认数组长度
    //容量 2^trees.length - 1
    public boolean isEmpty(){
        return currentSize == 0;
    }
    private int capacity(){
        return (1 << trees.length) - 1;
    }
    public BinomialQueue() {
        trees = new Node[DEFAULT_TREES];
    }
    //只有一个节点的构造函数
    public BinomialQueue(T x) {
        this();
        trees[0] = new Node<>(x);
        currentSize = 1;
    }
    private Node<T> combineTrees(Node<T>t1, Node<T>t2){
        if(t1.element.compareTo(t2.element) > 0){
            return combineTrees(t2, t1);
        }
        t2.nextSibling = t1.leftChild;
        t1.leftChild = t2;
        return t1;
    }
    //合并另一个二项队列
    public void merge(BinomialQueue<T> bq){
        if(this == bq){
            return;
        }
        currentSize += bq.currentSize;
        //最末的两项合并 扩容
        if(currentSize > capacity()){
            int maxLen = Math.max(trees.length, bq.trees.length);
            expandTrees(maxLen + 1);
        }
        Node<T> carry = null;//合并当前下标两节点（扩大一倍，后移一位）之后的新节点
        for(int i=0, j=1; j<=currentSize; i++, j *= 2){
            Node<T> t1 = trees[i];
            Node<T> t2 = bq.trees.length > i?bq.trees[i]: null;
            int whichCase = t1 == null?0:1;
            whichCase += t2 == null?0:2;
            whichCase += carry == null?0:4;
            switch (whichCase){
                case 0://没有节点
                case 1://本树的节点
                    break;
                case 2://bq的节点
                    //把bq的节点copy过来
                    trees[i] = t2;
                    break;
                case 4://只有合并后的节点（前一位置两节点合并得到的）
                    trees[i] = carry;
                    carry = null;
                    break;
                case 3://本树和bq都有这个节点 合并
                    carry = combineTrees(t1, t2);
                    trees[i] = null;
                    break;
                case 5://本树节点和carry 合并
                    carry = combineTrees(carry, t1);
                    trees[i] = null;
                    break;
                case 6://bq的节点和carry 合并
                    carry = combineTrees(carry, t2);
                    break;
                case 7://两树的节点和carry 先赋值 再合并
                    trees[i] = carry;
                    carry = combineTrees(t1, t2);
                    break;
            }
        }
        for(Node<T> node: bq.trees){
            node = null;
        }
        bq.currentSize = 0;
    }
    //插入一个节点
    public void insert(T x){
        merge(new BinomialQueue<>(x));
    }
    public int findMinIndex() throws Exception {
        if(isEmpty()){
            throw new Exception("findMinIndex之空队列");
        }
        T min = null;
        int minIndex = 0;
        //找不为null的节点初始化最小值
        for(int i=0; i < trees.length; i++){
            if(trees[i] != null){
                min = trees[i].element;
                minIndex = i;
                break;
            }
        }
        //再逐个比较
//        int reStart = minIndex + 1;
        for(int j = minIndex + 1; j < trees.length; j++){
            if(trees[j] != null && trees[j].element.compareTo(min)<0){
                min = trees[j].element;
                minIndex = j;
            }
        }
        return minIndex;
    }
    public T findMin() throws Exception {
        return trees[findMinIndex()].element;
    }
    public T deleteMin() throws Exception {
        if(isEmpty()){
            throw new Exception("deleteMin之空队列");
        }
        int minIndex = findMinIndex();
        T minEle = trees[minIndex].element;
        //被删除的队列 拆分成一个二项队列
        Node<T> deletedTree = trees[minIndex].leftChild;
        BinomialQueue<T> delQ = new BinomialQueue<>();
        delQ.expandTrees(minIndex + 1);
        delQ.currentSize = (1<<minIndex) - 1;
        for(int i=minIndex-1; i>=0; i--){
            delQ.trees[i] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            delQ.trees[i].nextSibling = null;
        }
        //当前二项队列的处理
        trees[minIndex] = null;
        currentSize -= 1<<(minIndex-1);
        merge(delQ);
        return minEle;
    }
    private void expandTrees(int len){
        Node<T> [] newTrees = new Node[len];
        for(int i=0; i<trees.length; i++){
            newTrees[i] = trees[i];
        }
        trees = newTrees;
    }
    public void printQ(){
        for(Node<T> node: trees){
            if(node != null){
                printQ(node);
            }
        }
    }
    private void printQ(Node<T> node){
        System.out.println(node.element);
        if(node.leftChild != null){
            printQ(node.leftChild);
        }
        if(node.nextSibling != null){
            printQ(node.nextSibling);
        }
    }
    private static class Node<T>{
        T element;//节点的值
        Node<T>leftChild;//左儿子
        Node<T>nextSibling;//右兄弟节点

        public Node(T element, Node<T> leftChild, Node<T> nextSibling) {
            this.element = element;
            this.leftChild = leftChild;
            this.nextSibling = nextSibling;
        }

        public Node(T element) {
            this(element, null, null);
        }
    }
}
