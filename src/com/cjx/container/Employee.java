/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Employee
 * Author:   ASUS
 * Date:     2019/10/12 11:17
 * Description: 员工类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

import java.util.Objects;

/**
 * 〈一句话功能简述〉<br> 
 * 〈员工类〉
 *
 * @author ASUS
 * @create 2019/10/12
 * @since 1.0.0
 */
public class Employee {
    private String name;
    private double salary;
    private int empNum;

    public Employee(String name, double salary, int empNum) {
        this.name = name;
        this.salary = salary;
        this.empNum = empNum;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Employee && ((Employee) o).name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", empNum=" + empNum +
                '}';
    }
}
