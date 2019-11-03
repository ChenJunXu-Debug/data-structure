/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: SplayTree
 * Author:   ASUS
 * Date:     2019/10/23 10:15
 * Description: 自顶向下的伸展树
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈自顶向下的伸展树〉
 *
 * @author ASUS
 * @create 2019/10/23
 * @since 1.0.0
 */
public class SplayTree<T extends Comparable<? super T>> {
    //根节点
    private BinaryNode<T> root;
    //所有叶子节点的子节点都是nullNode
    private BinaryNode<T> nullNode;
    //左右树的父节点
    private BinaryNode<T> header = new BinaryNode<>(null);
    private BinaryNode<T> newNode = null;

    public SplayTree(){
        nullNode = new BinaryNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        root = nullNode;
    }

    public T splay(T x){
        if (root == nullNode){
            return null;
        }
        root = splay(x, root);
        return root.element;
    }

    public void printTree(){
        printTree(root, 0);
    }

    private void printTree(BinaryNode<T> r, int deep) {
        if(r != nullNode){
            String s = "";
            for(int i = 0; i < deep; i++){
                s += "=";
            }
            System.out.println(r.element + s);
            printTree(r.left, deep + 1);
            printTree(r.right, deep + 1);
        }
    }

    private BinaryNode<T> splay(T x, BinaryNode<T> t){
        BinaryNode<T> leftTreeMax, rightTreeMin;
        header.left = header.right = nullNode;
        leftTreeMax = rightTreeMin = header;
        nullNode.element = x;
        for( ; ; ){
            if(x.compareTo(t.element) < 0){
                if(x.compareTo(t.left.element) < 0){
                    t = rotateWithLeftChild(t);
                }
                //无此节点 跳出
                if(t.left == nullNode){
                    break ;
                }
                rightTreeMin.left = t;
                rightTreeMin = t;
                t = t.left;
            }else if(x.compareTo(t.element) > 0){
                if(x.compareTo(t.right.element) > 0){
                    t = rotateWithRightChild(t);
                }
                if( t.right == nullNode){
                    break ;
                }
                leftTreeMax.right = t;
                leftTreeMax = t;
                t = t.right;
            }else{
                break ;//找到节点
            }
        }
        //合并中树 左右树
        leftTreeMax.right = t.left;
        rightTreeMin.left = t.right;
        t.left = header.right;
        t.right = header.left;
        return t;
    }

    public void makeEmpty(){
        root = nullNode;
        nullNode.element = null;
    }

    public boolean isEmpty(){
        return root == nullNode;
    }

    public T findMin(){
        if (root == nullNode){
            return null;
        }
        while(root.left != nullNode){
            root = rotateWithLeftChild(root);
        }
        return root.element;
    }

    public T findMax(){
        if (root == nullNode){
            return null;
        }
        while(root.right != nullNode){
            root = rotateWithRightChild(root);
        }
        return root.element;
    }

    public void remove(T x){
        BinaryNode<T> newTree;
        if(!contain(x)){
            return ;
        }
        //如果包含 则上一步已经把x转移到根节点
        //没有左儿子则用右儿子直接取代
        if(root.left == nullNode){
            newTree = root.right;
        }else{
            //把左子树根据x伸展 则左子树中最大节点成为根节点 且这个节点不会有右儿子
            //再把右子树连接到这个节点的右儿子处
           newTree = root.left;
           newTree = splay(x, newTree);
           newTree.right = root.right;
        }
        root = newTree;
    }

    public boolean contain(T x) {
        return splay(x).compareTo(x) == 0;

    }

    private BinaryNode<T> rotateWithRightChild(BinaryNode<T> t) {
        BinaryNode<T> rightC = t.right;
        t.right = rightC.left;
        rightC.left = t;
        return rightC;
    }

    public void insert(T x){
        if(newNode == null){
            newNode = new BinaryNode<>(null);
        }
        newNode.element = x;//初始化插入节点
        //根节点为null
        if(root == nullNode){
            newNode.left = newNode.right = nullNode;
            root = newNode;
        }
        else{
            root = splay(x, root);
            if(x.compareTo(root.element) < 0){
                newNode.left = root.left;
                newNode.right = root;
                root.left = nullNode;
                root = newNode;
            }else if(x.compareTo(root.element) > 0){
                newNode.right = root.right;
                newNode.left = root;
                root.right = nullNode;
                root = newNode;
            }else{
                return ;//插入的节点存在
            }
        }
        newNode = null;
    }

    private BinaryNode<T> rotateWithLeftChild(BinaryNode<T> t) {
        BinaryNode<T> leftC = t.left;
        t.left = leftC.right;
        leftC.right = t;
        return leftC;
    }

    //内部节点类
    private static class BinaryNode<T>{
        T element;//数据
        BinaryNode<T> left;//左儿子
        BinaryNode<T> right;//右儿子

        BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        BinaryNode(T ele){
            this(ele, null, null);
        }
    }
}
