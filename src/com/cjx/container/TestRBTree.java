/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestRBTree
 * Author:   ASUS
 * Date:     2019/10/24 21:49
 * Description: 红黑树测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈红黑树测试〉
 *
 * @author ASUS
 * @create 2019/10/24
 * @since 1.0.0
 */
public class TestRBTree {
    public static void main(String[] args) {
        RedBlackTree<Integer> tree = new RedBlackTree<>();
//        for (int i = 1; i < 100; i++){
//            tree.insert(i);
//        }
        for (int i = 99; i > 50; i--){
            tree.insert(i);
        }
        for (int i = 1; i < 51; i++){
            tree.insert(i);
        }
        //计算平均查找次数
        int totalCount = 0;
        for (int i = 1; i < 100; i++){
            totalCount += tree.count(i);
        }
        System.out.println(totalCount*1.0/99);
//        System.out.println(tree.contain(55));
//        tree.remove(55);
//        System.out.println(tree.contain(55));
//        tree.printTree();

    }
}
