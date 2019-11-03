/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RedBlackTree
 * Author:   ASUS
 * Date:     2019/10/24 10:40
 * Description: 红黑树
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.cjx.container;

/**
 * 〈一句话功能简述〉<br> 
 * 〈红黑树〉
 *
 * @author ASUS
 * @create 2019/10/24
 * @since 1.0.0
 */
public class RedBlackTree<T extends Comparable<? super T>> {
    private RBTNode<T> root;//根节点

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      px                              px
     *     /                               /
     *    x                               y
     *   /  \      --(左旋)-.            / \                #
     *  lx   y                         x  ry
     *     /   \                      /  \
     *    ly   ry                    lx  ly
     *
     *
     */
    private void leftRotate(RBTNode<T> x){
        RBTNode<T> y = x.right;
        x.right = y.left;
        if(y.left != null){
            y.left.parent = x;
        }
        y.parent = x.parent;
        //x是空节点
        if(x.parent == null){
            root = y;
        }else{
            //x是左子节点
            if(x.parent.left == x){
                x.parent.left = y;
            }else{
                //x是右子节点
                x.parent.right = y;
            }
        }
        y.left = x;
        x.parent = y;
    }

    /*
     * 对红黑树的节点(y)进行右旋转
     *
     * 右旋示意图(对节点y进行左旋)：
     *            py                               py
     *           /                                /
     *          y                                x
     *         /  \      --(右旋)-.             /  \
     *        x   ry                          lx    y
     *       / \                                   / \
     *      lx  rx                                rx  ry
     *
     */
    private void rightRotate(RBTNode<T> y) {
        // 设置x是当前节点的左孩子。
        RBTNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        // 将 “y的父亲” 设为 “x的父亲”
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;            // 如果 “y的父亲” 是空节点，则将x设为根节点
        } else {
            if (y == y.parent.right)
                y.parent.right = x;    // 如果 y是它父节点的右孩子，则将x设为“y的父节点的右孩子”
            else
                y.parent.left = x;    // (y是它父节点的左孩子) 将x设为“x的父节点的左孩子”
        }
        x.right = y;
        y.parent = x;
    }
    //插入节点
    public void insert(T x){
        insert(new RBTNode<>(x, null, null, BLACK, null));
    }

    private void insert(RBTNode<T> node){
        int cmp;
        RBTNode<T> y = null;
        RBTNode<T> x = this.root;

        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null) {
            y = x;//记录插入节点最后在哪个节点下面 也就是父节点
            cmp = node.element.compareTo(x.element);
            if (cmp < 0)
                x = x.left;
            else if (cmp == 0)
                return ;//插入的节点存在
            else
                x = x.right;
        }
        //建立双向联系
        node.parent = y;
        if (y!=null) {
            cmp = node.element.compareTo(y.element);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        } else {
            this.root = node;//红黑树为空树时
        }

        // 2. 设置节点的颜色为红色
        node.color = RED;

        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }
    //插入节点 红黑树失去平衡后 进行修正
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent, gparent;

        // 若“父节点存在，并且父节点的颜色是红色” 父为黑色则不用做什么
        while (((parent = node.parent) != null) && parent.color == RED) {
            gparent = parent.parent;

            //若“父节点”是“祖父节点的左孩子”
            if (parent == gparent.left) {
                // Case 1条件：叔叔节点是红色
                RBTNode<T> uncle = gparent.right;
                if ((uncle!=null) && uncle.color == RED) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;//把祖父节点当作当前节点继续进行往上平衡
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是右孩子
                if (parent.right == node) {
                    RBTNode<T> tmp;
                    leftRotate(parent);//先左旋(和case3一样) 再右旋
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是左孩子。
                parent.color = BLACK;
                gparent.color = RED;
                rightRotate(gparent);
            } else {    //若“z的父节点”是“z的祖父节点的右孩子”
                // Case 1条件：叔叔节点是红色
                RBTNode<T> uncle = gparent.left;
                if ((uncle != null) && uncle.color == RED) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                // Case 2条件：叔叔是黑色，且当前节点是左孩子
                if (parent.left == node) {
                    RBTNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                // Case 3条件：叔叔是黑色，且当前节点是右孩子。
                parent.color = BLACK;
                gparent.color = RED;
                leftRotate(gparent);
            }
        }

        // 将根节点设为黑色
        root.color = BLACK;
    }

    //删除节点
    public void remove(T x){
        RBTNode<T> node = find(x);
        if(node != null){
            remove(node);
        }
    }
    //查找x需要几次
    public int count (T x){
        int count = 0;
        RBTNode<T> findNode = root;
        while(findNode != null){
            count++;
            if(x.compareTo(findNode.element) < 0){
                findNode = findNode.left;
            }else if (x.compareTo(findNode.element) > 0){
                findNode = findNode.right;
            }else{
                return count;
            }
        }
        return 0 ;
    }

    private RBTNode<T> find(T x) {
        RBTNode<T> findNode = root;
        while(findNode != null){
            if(x.compareTo(findNode.element) < 0){
                findNode = findNode.left;
            }else if (x.compareTo(findNode.element) > 0){
                findNode = findNode.right;
            }else{
                return findNode;
            }
        }
        return null ;
    }

    public boolean contain(T x){
        return find(x) != null;
    }

    private void remove(RBTNode<T> node){
        RBTNode<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ( (node.left!=null) && (node.right!=null) ) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBTNode<T> replace = node;

            // 获取后继节点(右子树中最小节点)
            replace = replace.right;
            while (replace.left != null)
                replace = replace.left;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (node.parent != null) {
                if (node.parent.left == node)
                    node.parent.left = replace;
                else
                    node.parent.right = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.root = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.right;
            parent = replace.parent;
            // 保存"取代节点"的颜色
            color = replace.color;

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
//                parent = replace;//好像没什么用
            } else {
                // child不为空 取代节点的儿子和父节点双向联系
                if (child != null)
                    child.parent = parent;
                parent.left = child;
                //没有儿子就不用处理儿子 和被删节点的右儿子建立双向联系
                replace.right = node.right;
                node.right.parent = replace;
            }
            //取代被删节点 和被删节点的父节点和左子节点双向联系
            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;
            //如果取代节点是黑色 需要平衡
            if (color == BLACK)
                removeFixUp(child, parent);
            //否则不用做什么
            node = null;
            return ;
        }

        //一个或者没有儿子
        if (node.left !=null) {
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child != null)
            child.parent = parent;

        // "node节点"不是根节点 被删节点的儿子节点和父节点双向链接
        if (parent != null) {
            if (parent.left == node)
                parent.left = child;
            else
                parent.right = child;
        } else {
            this.root = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }
    //删除的平衡 node为在取代节点位置上的取代节点的儿子节点
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;//兄弟节点

        while ((node == null || node.color == BLACK) && (node != this.root)) {
            //左儿子
            if (parent.left == node) {
                other = parent.right;
                if (other.color == RED) {
                    // Case 1: x的兄弟w是红色的 则父节点一定是黑色的 再转化成case 2,3,4
                    other.color = BLACK;
                    parent.color = RED;
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left==null || other.left.color == BLACK) &&
                        (other.right==null || other.right.color == BLACK)) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    other.color = RED;
                    node = parent;//往上平衡 此时这个节点的树少一个黑节点
                    parent = node.parent;
                } else {

                    if (other.right == null || other.right.color == BLACK) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.left.color = BLACK;
                        other.color = RED;
                        rightRotate(other);
                        other = parent.right;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.right.color = BLACK;
                    leftRotate(parent);
                    node = this.root;//已经平衡 跳出循环
                    break;
                }
            } else {

                other = parent.left;
                if (other.color == RED) {
                    // Case 1: x的兄弟w是红色的
                    other.color = BLACK;
                    parent.color = RED;
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left==null || other.left.color == BLACK) &&
                        (other.right==null || other.right.color == BLACK)) {
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    other.color = RED;
                    node = parent;
                    parent = node.parent;
                } else {

                    if (other.left == null || other.left.color == BLACK) {
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.right.color = BLACK;
                        other.color = RED;
                        leftRotate(other);
                        other = parent.left;
                    }

                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.left.color = BLACK;
                    rightRotate(parent);
                    node = this.root;
                    break;
                }
            }
        }

        if (node != null)
            node.color = BLACK;
    }

    public void printTree(){
        printTree(root, 0);
    }

    private void printTree(RBTNode<T> r, int deep) {
        if(r != null){
            String s = "";
            for(int i = 0; i < deep; i++){
                s += "=";
            }
            System.out.println(r.element + s);
            printTree(r.left, deep + 1);
            printTree(r.right, deep + 1);
        }
    }

    //内部节点类
    private static class RBTNode<T> {
        T element;//数据
        RBTNode<T> left;//左儿子
        RBTNode<T> right;//右儿子
        boolean color;
        RBTNode<T> parent;

        public RBTNode(T element, RBTNode<T> left, RBTNode<T> right, boolean color, RBTNode<T> parent) {
            this.element = element;
            this.left = left;
            this.right = right;
            this.color = color;
            this.parent = parent;
        }
    }

    private static final boolean BLACK = true;
    private static final boolean RED = false;
}
