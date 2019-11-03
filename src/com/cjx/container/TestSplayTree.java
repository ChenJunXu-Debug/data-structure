/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestSplayTree
 * Author:   ASUS
 * Date:     2019/10/23 14:13
 * Description: 测试伸展树
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈测试伸展树〉
 *
 * @author ASUS
 * @create 2019/10/23
 * @since 1.0.0
 */
public class TestSplayTree {
    public static void main(String[] args) {
        SplayTree<Integer> tree = new SplayTree<>();
        for(int i = 0; i < 100; i++){
            tree.insert(i);
        }
        tree.splay(40);
//        tree.splay(50);
//        tree.splay(60);
//        tree.printTree();
//        System.out.println(tree.splay(100));
//        System.out.println(tree.contain(2));
        tree.remove(0);
        tree.remove(99);
//        tree.printTree();
        System.out.println(tree.findMin());
        System.out.println(tree.findMax());
    }
}
