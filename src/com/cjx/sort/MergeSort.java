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
 * 〈归并排序〉
 *
 * @author ASUS
 * @create 2019/10/19
 * @since 1.0.0
 */
public class MergeSort {

    public static<T extends Comparable<? super T>> void sort(T[] arr){
        T[] tmpArr = (T[]) new Comparable[arr.length];
        mergeSort(arr, tmpArr, 0, arr.length-1);
    }

    private static<T extends Comparable<? super T>> void mergeSort(T[] arr, T[]tmpArr, int left, int right) {
        if(left < right){
            int center = (left + right)/2;
            mergeSort(arr, tmpArr, left, center);
            mergeSort(arr, tmpArr, center + 1, right);
            merge(arr, tmpArr, left, center + 1, right);
        }
    }

    private static<T extends Comparable<? super T>> void merge(T[] arr, T[] tmpArr, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numEle = rightEnd - leftPos + 1;
        while(leftPos <= leftEnd&&rightPos <= rightEnd){
            //添加到临时数组 临时数组下标+1 自身下标+1
            if(arr[leftPos].compareTo(arr[rightPos]) <= 0){
                tmpArr[tmpPos++] = arr[leftPos++];
            }else{
                tmpArr[tmpPos++] = arr[rightPos++];
            }
        }
        //剩余左边的数组
        while(leftPos <= leftEnd){
            tmpArr[tmpPos++] = arr[leftPos++];
        }
        //剩余右边的数组
        while(rightPos <= rightEnd){
            tmpArr[tmpPos++] = arr[rightPos++];
        }
        for(int i = 0; i < numEle; i++, rightEnd--){
            arr[rightEnd] = tmpArr[rightEnd];
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
