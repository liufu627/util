package alf.collection;

import org.junit.Test;

public class RBTreeTest {

    RBTree<Integer> rbTree=new RBTree<>();

    @Test
    public void insert() {


        rbTree.Clear();
        int[] arr={2,1,7,11,8,14,15,5,4};

        for (int i:arr) {
            rbTree.Insert(i);
            System.out.println("\n");
            rbTree.Print();
        }

        /**
         *
         2,2,BLACK
         1,1,BLACK
         8,8,RED
         5,5,BLACK
         4,4,RED
         7,7,RED
         14,14,BLACK
         11,11,RED
         15,15,RED
         */

        rbTree.Clear();
        System.out.println("\n");
        rbTree.Insert(3,4,5,6,7,28,27,30,31);
        rbTree.Print();
        /**
         *
         6,6,BLACK
         4,4,RED
         3,3,BLACK
         5,5,BLACK
         27,27,RED
         7,7,BLACK
         30,30,BLACK
         28,28,RED
         31,31,RED
         */

        System.out.println("\n");
        rbTree.Clear();
        rbTree.Insert(31,30,27,28,7,6,5,4,3);
        rbTree.Print();
        /**
         *
         27,27,BLACK
         6,6,RED
         4,4,BLACK
         3,3,RED
         5,5,RED
         7,7,BLACK
         30,30,RED
         28,28,BLACK
         31,31,BLACK
         */


    }

    @Test
    public void delete() {
    }

    @Test
    public void print() {
    }
}