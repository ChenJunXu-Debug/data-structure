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
 * 〈插入排序〉
 *
 * @author ASUS
 * @create 2019/10/19
 * @since 1.0.0
 */
public class InserttionSort {
    public static<T extends Comparable<? super T>> void sort(T[] arr){
        int j;
        for (int i=1; i<arr.length; i++){
            if(arr[i].compareTo(arr[i - 1]) < 0){
                T tmp = arr[i];
                for (j = i; j > 0 && tmp.compareTo(arr[j - 1]) < 0; j--){
                    arr[j] = arr [j -1 ];
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
