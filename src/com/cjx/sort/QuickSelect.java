/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: QuickSelect
 * Author:   ASUS
 * Date:     2019/10/22 10:47
 * Description: 快速选择
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.sort;

/**
 * 〈一句话功能简述〉<br> 
 * 〈快速选择〉
 *
 * @author ASUS
 * @create 2019/10/22
 * @since 1.0.0
 */
public class QuickSelect {
    //选择数组中第k小的元素
    public static<T extends Comparable<? super T>> T select(T[] arr, int k){
        select(arr, 0, arr.length - 1, k);
        return arr[k-1];
    }

    private static<T extends Comparable<? super T>> void select(T[] arr, int left, int right, int k) {
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
            if(k <= i){
                select(arr, left, i - 1, k);
            }else if(k > i + 1){
                select(arr, i + 1, right, k);
            }
            //arr[i]是第i+1个最小数 k = i+1时 arr[i]是第k个最小数 ar[k-1]=arr[i]
            //排好序也是在arr[k-1]位置
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
        System.out.println(select(arr, 33));
    }
}
