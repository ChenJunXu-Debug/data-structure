/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BinaryHeap
 * Author:   ASUS
 * Date:     2019/10/10 11:15
 * Description: 二叉堆
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈二叉堆〉
 *
 * @author cjx
 * @create 2019/10/10
 * @since 1.0.0
 */
public class BinaryHeap<T extends Comparable<? super T>> {
    private T [] array;//底层数组
    private int currentSize;//数组大小
    private static final int DEFAULT_CAPACITY = 10;//默认容量
   //构造函数
    public BinaryHeap(){
        array = (T[])new Comparable[DEFAULT_CAPACITY];
    }
    public BinaryHeap(int capacity){
        array = (T[])new Comparable[capacity];
    }
    public BinaryHeap(T[] items){
        currentSize = items.length;
        array = (T[])new Comparable[(currentSize+2)*11/10];
        for(int i=0; i<items.length; i++){
            array[i+1] = items[i];
        }
        buildHeap();
    }
    private void buildHeap(){
        //有子节点的节点都进行下滤
        for(int i = currentSize/2; i>0; i--){
            percolateDown(i);
        }
    }
    public boolean isEmpty(){
        return currentSize == 0;
    }
    public T findMin(){
        return array[1];
    }
    public void insert(T x){
        //插入第一个节点
        if(currentSize == 0){
            array[1] = x;
            currentSize++;
            return ;
        }
        if(currentSize == array.length-1){
            enlargeArray(array.length*2);//容量增加一倍
        }
        //往上移
        int hole = ++currentSize;//当前插入元素的下标
        //比父节点小就往上移 父节点覆盖当前节点
        for(array[0]=x; x.compareTo(array[hole/2])<0; hole /=2){
            array[hole] = array[hole/2];
        }
        //再把父节点换成要插入的节点
        array[hole] = x;
    }
    public T deleteMin() throws Exception {
        if(isEmpty()){
            throw new Exception("BinaryHeap容器为空");
        }
        T minItem = findMin();
        //把最末的元素放在下标1的位置 集合大小-1
        array[1] = array[currentSize--];
        percolateDown(1);
        return minItem;
    }
    private void percolateDown(int hole){
        int child;
        T tmp = array[hole];//存储根元素
        for(; hole*2 <= currentSize; hole = child){
            child = hole*2;
            //不是单子节点并且左子节点比右子节点大
            if(child!=currentSize&&array[child].compareTo(array[child+1])>0){
                child++;
            }
            //比根元素小就往上移
            if(array[child].compareTo(tmp)<0){
                array[hole] = array[child];
            }else{
                break;
            }
        }
        //最后把上移那个元素换成根元素
        array[hole] = tmp;

    }
    private void enlargeArray(int newLen){
        T[] newArr = (T[])new Comparable[newLen];
        for(int i=1; i<array.length; i++){
            newArr[i] = array[i];
        }
        array = newArr;
    }
    public void printHeap(){
        for(int i=1; i<currentSize+1; i++){
            System.out.println(array[i]);
        }
    }
//    public static void main(String[] args) {
//        int[] arr = new int[]{1, 2, 3};
//        System.out.println(arr[2]);
//        int[] newArr = new int[5];
//        System.arraycopy(arr, 0, newArr, 0, 3);
//        System.out.println(arr[2]);
//        System.out.println(newArr[3]);
//    }
}
