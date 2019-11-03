/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestBinomialQueue
 * Author:   ASUS
 * Date:     2019/10/16 11:08
 * Description: 二项队列测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈二项队列测试〉
 *
 * @author ASUS
 * @create 2019/10/16
 * @since 1.0.0
 */
public class TestBinomialQueue {
    public static void main(String[] args) throws Exception {
        BinomialQueue<Integer> q = new BinomialQueue<>();
        for(int i = 1; i < 8; i++)
        q.insert(i);
//        q.printQ();
        q.deleteMin();
        q.printQ();
    }
}
