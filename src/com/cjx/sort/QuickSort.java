/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: QuickSort
 * Author:   ASUS
 * Date:     2019/10/22 9:42
 * Description: 快速排序
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.sort;

/**
 * 〈一句话功能简述〉<br> 
 * 〈快速排序〉
 *
 * @author ASUS
 * @create 2019/10/22
 * @since 1.0.0
 */
public class QuickSort {
    public static<T extends Comparable<? super T>> void sort(T[] arr){
        sort(arr, 0, arr.length - 1);
    }

    private static<T extends Comparable<? super T>> void sort(T[] arr, int left, int right) {
        if(right - left > 5){
            T pivot = median(arr, left, right);//选择枢纽元
            int i = left, j = right -1;//两个游标
            for( ; ; ){
                //左游标所在下标的元素比枢纽元小 一直往右移
                while(arr[++i].compareTo(pivot) < 0){}
                //右游标所在下标的元素比枢纽元大 一直往左移
                while(arr[--j].compareTo(pivot) > 0){}
                //两个游标重合时说明走完了 否则需要交换元素再进行移动
                if(i < j){
                    swapReference(arr, i, j);
                }else{
                    break;
                }
            }
            swapReference(arr, i, right - 1);//交换枢纽元和i位置
            sort(arr, left, i-1);
            sort(arr, i+1, right);
        }else{
            //要排序的数组小于等于5时 做插入排序
            insertionSort(arr, left, right);
        }
    }

    private static<T extends Comparable<? super T>> void insertionSort(T[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++){
            int j;
            if(arr[i].compareTo(arr[i - 1]) < 0){
                T tmp = arr[i];
                for(j = i; j > left&&arr[i].compareTo(arr[i - 1]) < 0; j--){
                    arr[j] = arr[j-1];
                }
                arr[j] = tmp;
            }
        }
    }

    private static <T extends Comparable<? super T>> T median(T[] arr, int left, int right) {
        int center = (left + right)/2;
        //交换位置
        if(arr[center].compareTo(arr[left]) < 0){
            swapReference(arr, left, center);
        }
        if(arr[right].compareTo(arr[left]) < 0){
            swapReference(arr, right, left);
        }
        if(arr[right].compareTo(arr[center]) < 0){
            swapReference(arr, right, center);
        }
        swapReference(arr, center, right - 1);
        return arr[right - 1];
    }

    private static<T extends Comparable<? super T>> void swapReference(T[] arr, int t1, int t2) {
        T tmp = arr[t1];
        arr[t1] = arr[t2];
        arr[t2] = tmp;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[100];
        for(int i = 100, j = 0; i > 0; i--, j++){
            arr[j] = i;
        }
        sort(arr);
        for(Integer i: arr){
            System.out.print(i + " ");
        }
    }
}
