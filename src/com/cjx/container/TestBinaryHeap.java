/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestBinaryHeap
 * Author:   ASUS
 * Date:     2019/10/10 13:30
 * Description: 测试二叉堆
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试二叉堆〉
 *
 * @author ASUS
 * @create 2019/10/10
 * @since 1.0.0
 */
public class TestBinaryHeap {

    public static void main(String[] args) throws Exception {
//        Integer[] arr = new Integer[]{7, 6, 5, 4, 3, 2, 1};
//        System.out.println(arr.length);
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.insert(7);
        heap.insert(6);
        heap.insert(5);
        heap.insert(4);
        heap.insert(3);
        heap.insert(2);
        heap.insert(1);
//        heap.printHeap();
        heap.deleteMin();
        heap.printHeap();
        heap.deleteMin();
        heap.printHeap();
    }
}
