/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InserttionSort
 * Author:   ASUS
 * Date:     2019/10/19 11:42
 * Description: 插入排序
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.sort;

/**
 * 〈一句话功能简述〉<br> 
 * 〈堆排序〉
 *
 * @author ASUS
 * @create 2019/10/19
 * @since 1.0.0
 */
public class HeapSort {
    private static int leftChild(int i){
        return 2*i + 1;
    }

    private static<T extends Comparable<? super T>> void precDown(T[] arr, int i, int n){
        int child;
        T tmp;
        for (tmp = arr[i]; leftChild(i) < n; i = child){
            child = leftChild(i);
            //不是最末的元素（单节点） 并且左儿子小于右儿子
            if(child != n-1&&arr[child].compareTo(arr[child + 1]) < 0){
                child++;
            }
            if (tmp.compareTo(arr[child]) < 0){
                arr[i] = arr[child];
            }else{
                break;
            }
        }
        arr[i] = tmp;
    }

    public static<T extends Comparable<? super T>> void sort(T[] arr){
        //构建堆
        for(int i = arr.length/2 - 1; i >= 0; i--){
            precDown(arr, i, arr.length);
        }
        T tmp;
        for(int i = arr.length - 1; i > 0; i--){
            //交换最大的（0位置）和最末的
            tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            //i每次-1 保证不影响到后面排好序的元素
            precDown(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 3, 5, 8, 7, 2, 6, 4};
        sort(arr);
        for(Integer i: arr){
            System.out.print(i);
        }
    }
}
