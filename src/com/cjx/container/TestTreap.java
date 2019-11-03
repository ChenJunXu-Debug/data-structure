/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestTreap
 * Author:   ASUS
 * Date:     2019/10/25 10:35
 * Description: 测试treap树
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

import java.util.Random;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试treap树〉
 *
 * @author ASUS
 * @create 2019/10/25
 * @since 1.0.0
 */
public class TestTreap {
    public static void main(String[] args) {
        Treap<Integer> tree = new Treap<>();
        for (int i = 1; i < 100; i++){
            tree.insert(i);
        }
        System.out.println(tree.getSize());
        tree.remove(34);
        System.out.println(tree.getSize());
        System.out.println(tree.contain(34));
        tree.remove(54);
        System.out.println(tree.getSize());
        System.out.println(tree.contain(54));
        tree.printTree();
        //计算平均查找次数
//        int totalCount = 0;
//        for (int i = 1; i < 100; i++){
//            totalCount += tree.count(i);
//        }
//        System.out.println(totalCount*1.0/99);
    }
}
