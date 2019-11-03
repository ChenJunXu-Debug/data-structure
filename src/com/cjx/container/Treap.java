/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TreapNode
 * Author:   ASUS
 * Date:     2019/10/25 10:29
 * Description: treap树 tree和heap的结合
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈treap树 tree和heap的结合〉
 *
 * @author ASUS
 * @create 2019/10/25
 * @since 1.0.0
 */
public class Treap<T extends Comparable<? super T>> {
    private TreapNode<T> root;
    private TreapNode<T> nullNode;
    private int size;//元素个数

    public int getSize(){
        return size;
    }
    public Treap(){
        nullNode = new TreapNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        root = nullNode;
        nullNode.priority = Integer.MAX_VALUE;
    }

    public void insert (T x) {
        root = insert( x, root );
        size++;
    }

    public TreapNode<T> insert( T x, TreapNode<T> t) {
        //插入根节点
        if( t == nullNode ){
            return new TreapNode<>( x, nullNode, nullNode );
        }
        int compareResult = x.compareTo(t.element);
        if( compareResult < 0 ) {
            //先递归的根据大小插入到对应位置
            t.left = insert( x, t.left );
            //再递归的根据优先级进行旋转
            if ( t.left.priority < t.priority ){
                t = rotateWithLeftChild( t );
            }
        }else if ( compareResult > 0) {
            //先递归的根据大小插入到对应位置
            t.right = insert( x, t.right );
            //再递归的根据优先级进行旋转
            if ( t.right.priority < t.priority ){
                t = rotateWithRightChild( t );
            }
        }
        return t;
        /*TreapNode<T> node = root;
        TreapNode<T> parent = null;
        List<Boolean> rotateList = new ArrayList<>();
        while ( node != nullNode ) {
            parent = node;
            if ( x.compareTo(node.element) < 0 ){
                if ( node.left.priority < node.priority ){
                    rotateList.add(true);//true表示旋转左儿子
                }
                node = node.left;
            }else if ( x.compareTo(node.element) > 0 ){
                if ( node.right.priority < node.priority ){
                    rotateList.add(false);//false表示旋转右儿子
                }
                node = node.right;
            }else {
                break ;
            }
        }
        node = new TreapNode<>( x, nullNode, nullNode );
        for ( int i = rotateList.size(); i >= 0; i-- ){
            if ( rotateList.get( i -1) ){
                rotateWithLeftChild(node);
            }else{
                rotateWithRightChild(node);
            }
        }*/
    }
    //右儿子 左旋
    private TreapNode<T> rotateWithRightChild(TreapNode<T> t) {
        TreapNode<T> rightChild = t.right;
        t.right = rightChild.left;
        rightChild.left = t;
        return rightChild;
    }
    //左儿子 右旋
    private TreapNode<T> rotateWithLeftChild(TreapNode<T> t) {
        TreapNode<T> leftChild = t.left;
        t.left = leftChild.right;
        leftChild.right = t;
        return leftChild;
    }

    public void remove( T x) {
        root = remove( x, root);
    }

    private TreapNode<T> remove ( T x, TreapNode<T> t ) {
        //不是空树
        if( t != nullNode ) {
            int compareResult = x.compareTo(t.element);
            if( compareResult < 0 ) {
                t.left = remove( x, t.left );
            }else if ( compareResult > 0) {
                t.right = remove( x, t.right);
            }else {//找到节点
                if( t.left.priority < t.right.priority ) {
                    t = rotateWithLeftChild( t );
                }else {
                    t = rotateWithRightChild( t );
                }
                if ( t != nullNode ) {
                    t = remove( x, t);
                }else {//t为叶子节点 经过旋转右儿子 t的数据就到了左儿子上面
                    t.left = nullNode;
                    size--;
                }
            }
        }
        return t;
    }

    public boolean contain( T x ) {
        TreapNode<T> node = root;
        if( node != nullNode ) {
            int compareResult = x.compareTo(node.element);
            if( compareResult < 0 ) {
                node = node.left;
            }else if ( compareResult > 0) {
                node = node.right;
            }else {//找到节点
                return true;
            }
        }
        return false;
    }

    public void printTree(){
        printTree(root, 0);
    }

    private void printTree(TreapNode<T> r, int deep) {
        if( r != nullNode ) {
            String s = "";
            for(int i = 0; i < deep; i++){
                s += "=";
            }
            System.out.println(r.element + s);
            printTree(r.left, deep + 1);
            printTree(r.right, deep + 1);
        }
    }
    //查找x需要几次
    public int count (T x){
        int count = 0;
        TreapNode<T> findNode = root;
        while(findNode != nullNode){
            count++;
            if(x.compareTo(findNode.element) < 0){
                findNode = findNode.left;
            }else if (x.compareTo(findNode.element) > 0){
                findNode = findNode.right;
            }else{
                return count;
            }
        }
        return 0 ;
    }

    private static class TreapNode<T>{
        T element;
        TreapNode<T> left;
        TreapNode<T> right;
        int priority;//优先级
        private static Random randomObj = new Random();

        public TreapNode(T element, TreapNode<T> left, TreapNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
            priority = randomObj.nextInt(Integer.MAX_VALUE - 1);
        }

        public TreapNode(T element) {
            this(element, null, null);
        }
    }
}
