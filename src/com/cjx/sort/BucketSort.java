/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BucketSort
 * Author:   ASUS
 * Date:     2019/10/22 11:31
 * Description: 桶排序
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.sort;

/**
 * 〈一句话功能简述〉<br> 
 * 〈桶排序〉
 *
 * @author ASUS
 * @create 2019/10/22
 * @since 1.0.0
 */
public class BucketSort {
    //numLen表示最大数字的位数
    public static void sort(int[] arr, int numLen){
        final int BUCKETS = 10;//0-9 10个桶
        int n = arr.length;//元素个数
        int[] buffer = new int[n];//相同容量的缓冲容器
        int[] in = arr;
        int[] out = buffer;
        //从个位往高位排
        for(int pos = 1; pos <= numLen; pos++){
            int[] count = new int[BUCKETS + 1];
            //记录每个桶中元素的个数
            for (int i = 0; i < n; i++){
                count[numAt(in[i], pos) + 1]++;
            }
            //记录前面的桶的所有元素个数之和 count[1]表示0和1号桶中的所有元素个数之和
            for (int b = 1; b <= BUCKETS; b++){
                count[b] += count[b - 1];
            }
            //根据桶下标排序
            for(int i = 0; i < n; i++){
                out[count[numAt(in[i], pos)]++] = in[i];
            }
            //交换in out数组
            int[]tmp = in;
            in = out;//in存放排好序的数组
            out = tmp;
        }
        //如果上面的操作是偶数次的 in指向arr 否则out指向arr
        if(numLen % 2 == 1){
            for(int i = 0; i < n; i++){
                out[i] = in[i];
            }
        }
    }

    private static int numAt(int i, int pos) {
        return i/(int)(Math.pow(10, pos - 1))%10;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{22, 211, 221, 322, 5, 4, 3, 2, 1, 11, 43, 21};
        sort(arr, 3);
        for (int i: arr){
            System.out.println(i);
        }
    }
}
