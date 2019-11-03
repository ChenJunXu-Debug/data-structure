/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestLeftistHeap
 * Author:   ASUS
 * Date:     2019/10/16 10:28
 * Description: 左式数测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈左式树测试〉
 *
 * @author ASUS
 * @create 2019/10/16
 * @since 1.0.0
 */
public class TestLeftistHeap {
    public static void main(String[] args) throws Exception {
        LeftistHeap<Integer> heap = new LeftistHeap<>();
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
//        heap.printHeap();
        LeftistHeap<Integer> heap2 = new LeftistHeap<>();
        heap2.insert(6);
        heap2.insert(7);
        heap2.insert(8);
        heap.merge(heap2);
        heap.printHeap();
    }
}
