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
 * 〈希尔排序〉
 *
 * @author ASUS
 * @create 2019/10/19
 * @since 1.0.0
 */
public class ShellSort {
    public static<T extends Comparable<? super T>> void sort(T[] arr){
        int j;
        //gap步长
        for (int gap = arr.length/2; gap > 0 ; gap /= 2){
            //步长下插入排序
            for(int i = gap; i < arr.length; i++){
                T tmp = arr[i];
                for(j = i; j >= gap && tmp.compareTo(arr[j - gap]) < 0; j -= gap){
                    arr[j] = arr[j - gap];
                }
                arr[j] = tmp;
            }
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
