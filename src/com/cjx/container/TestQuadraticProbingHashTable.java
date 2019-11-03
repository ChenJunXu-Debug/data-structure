/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestQuadraticProbingHashTable
 * Author:   ASUS
 * Date:     2019/10/12 13:25
 * Description: 平方探测哈希表测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈平方探测哈希表测试〉
 *
 * @author ASUS
 * @create 2019/10/12
 * @since 1.0.0
 */
public class TestQuadraticProbingHashTable {
    public static void main(String[] args) {
        QuadraticProbingHashTable<Employee>set = new QuadraticProbingHashTable();
        set.insert(new Employee("张三", 3000.0, 1));
        set.insert(new Employee("李四", 3000.0, 2));
        set.insert(new Employee("王五", 3000.0, 3));
        set.insert(new Employee("张三", 3000.0, 4));
        set.println();
        System.out.println(set.getSize());
        set.remove(new Employee("李四", 3000.0, 2));
        set.println();
        System.out.println(set.getSize());
    }
}
