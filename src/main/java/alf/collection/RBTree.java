package alf.collection;

import java.util.*;

/**
 * Red -Black tree struct, make values' hash as key
 * @param <V>
 */
public class RBTree<V> {

    public RBTree() {
        NA = new RBNode<>(Integer.MIN_VALUE,null,null);
        NA.color = RBColor.BLACK;
        NA.left = NA.right = NA;

        root = NA;
    }
    public void Insert(V... values)
    {
        for (V item: values) {
            Insert(item);
        }
    }
    public void Clear()
    {
        if(root!=NA)
            Clear(root);
    }
    private  void Clear(RBNode node)
    {
        if(node.left!=NA)
            Clear(node.left);
        if(node.right!=NA)
            Clear(node.right);
        if(node.left==NA&& node.right==NA)
        {
            if(node.parent == NA)
                root = NA;
            else if(node.parent.left == node)
                node.parent.left = NA;
            else if(node.parent.right == node)
                node.parent.right = NA;
        }
    }
    public void Insert(V value){
        int key=value.hashCode();
        RBNode<V> node = new RBNode<V>(key,value, NA);
        if(root == NA){
            root=node;
            root.color = RBColor.BLACK;
            return;
        }
        RBNode y =NA;
        RBNode<V> x = root;
        while (x != NA) {
            y = x;
            if (key < x.key)
                x = x.left;
            else if (key > x.key)
                x = x.right;
            else
                break;
        }
        node.parent = y;

        if (key < y.key) {
            y.left = node;
        } else if (key > y.key) {
            y.right = node;
        } else {
            node.parent = y.parent;
            node.left = y.left;
            node.right = y.right;
            if (y.parent != NA) {
                if (y.parent.left == y)
                    y.parent.left = node;
                else if (y.parent.right == y)
                    y.parent.right = node;
            }
        }

        InertFixUp(node);
    }

    /**
     * condition: both current node and parent node are RED
     * Case1: parent and uncle node are RED, then set grandpa node RED; parent and uncle node BLACK.  z=z.parent
     * case2: currentnode =grandpa node->left->left; make grandpa RED,mak parent node Black . RotateRight with parent node, z = z.parent.parent;
     * case3: currentnode =grandpa node->right->right; make grandpa RED,mak parent node Black . RotateLeft with parent node, z = z.parent.parent;
     * case4: currentnode =grandpa node->left->right; RotateLeft with parent node,make grandpa Black,mak parent of grandpa node Red . RotateRight with parent of grandpa node, z=z.parent
     * case5: currentnode =grandpa node->right->left; RotateRight with parent node,make grandpa Black,mak parent of grandpa node Red .RotateLeft with parent of grandpa node, z=z.parent
     * @param z
     */
    private void InertFixUp(RBNode<V> z) {
        RBNode y=NA;
        while (z.color ==RBColor.RED && z.parent.color == RBColor.RED){
            if(z.parent == z.parent.parent.left){
                y = z.parent.parent.right;
                if(y!=NA&&y.color == RBColor.RED){
                    z.parent.color=RBColor.BLACK;
                    y.color =RBColor.BLACK;
                    z.parent.parent.color = RBColor.RED;
                    z = z.parent.parent;
                }
                else if( z == z.parent.right){
                    z=z.parent;
                    RotateLeft(z);
                    z.parent.color = RBColor.BLACK;
                    z.parent.parent.color = RBColor.RED;
                    RotateRight(z.parent.parent);
                }
                else {//left-left
                    z.parent.color = RBColor.BLACK;
                    z.parent.parent.color = RBColor.RED;
                    RotateRight(z.parent.parent);
                    z=z.parent;
                }
            }
            else if(z.parent == z.parent.parent.right){
                y = z.parent.parent.left;
                if(y !=NA && y.color == RBColor.RED){
                    z.parent.color=RBColor.BLACK;
                    y.color =RBColor.BLACK;
                    z.parent.parent.color = RBColor.RED;
                    z = z.parent.parent;
                }
                else if( z == z.parent.left){
                    z=z.parent;
                    RotateRight(z);
                    z.parent.color = RBColor.BLACK;
                    z.parent.parent.color = RBColor.RED;
                    RotateLeft(z.parent.parent);
                }
                else {
                    z.parent.color = RBColor.BLACK;
                    z.parent.parent.color = RBColor.RED;
                    RotateLeft(z.parent.parent);
                    z=z.parent;
                }
            }
        }

        root.color = RBColor.BLACK;
    }


    public void Delete(V value)
    {
        int key=value.hashCode();
        RBNode<V> foundNode = root;
        while(foundNode!= NA)
        {
            if(key<foundNode.key)
            {
                if(foundNode.left == NA)
                    break;
                else {
                    foundNode = foundNode.left;
                }
            }else if(key>foundNode.key){
                if(key>foundNode.key)
                {
                    if(foundNode.right == NA)
                        break;
                    else
                        foundNode = foundNode.right;
                }
            }else {
                RBNode<V> parentNode = foundNode.parent;
                if (parentNode.left == foundNode) {
                    parentNode.left = foundNode.right;
                    if (foundNode.left == NA) break;

                    RBNode tmpNode = foundNode.right;
                    MoveToLeftest(tmpNode,foundNode.left);

                } else if (parentNode.right == foundNode) {
                    parentNode.right = foundNode.left;
                    if (foundNode.left == NA) break;

                    RBNode tmpNode = foundNode.left;


                }

                break;
            }
        }
        //todo: delete a not exted node.

        //UpdateLevel();
        Balance();
    }

    public void  Balance()
    {
    }


    public void Print()
    {
        Print(root);
    }
    private void Print(RBNode node){
        System.out.printf("%d,%s,%s\n",node.key,node.value,node.color);

        if(node.left != NA)
            Print(node.left);
        if(node.right != NA)
            Print(node.right);
    }
    public enum RBColor{
        RED(0),BLACK(1);
        private int value;
        private RBColor(int value)
        {
            this.value = value;
        }
    }

    public class RBNode<V> implements Comparable{
        private RBNode<V> left;
        private RBNode<V> right;
        private RBNode<V> parent;

        private int key;
        private V value;
        private RBColor color;

        public RBNode(int key, V value,RBNode<V> defaultLinkNode) {
            this.key = key;
            this.value = value;
            this.left = defaultLinkNode;
            this.right =defaultLinkNode;
            this.parent = defaultLinkNode;
            this.color = RBColor.RED;
        }

        @Override
        public int hashCode() {
            return key;
        }

        @Override
        public String toString() {
            return  Integer.toString(key);
        }

        @Override
        public int compareTo(Object o) {
            return this.key - o.hashCode();
        }
    }

    /**
     * null node
     */
    final RBNode<V> NA;
    private RBNode<V> root;


    private  void RotateLeft(RBNode<V> foundNode){
        RBNode x= foundNode;
        RBNode y = foundNode.right;

        x.right = y.left;
        if(y.left !=NA)
            y.left.parent = x;

        y.parent = x.parent;

        if(x.parent == NA){
            root = y;
        }
        else if(x.parent.left == x) {
            x.parent.left = y;
        }else if(x.parent.right == x){
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private  void RotateRight(RBNode<V> foundNode){
        RBNode x= foundNode;
        RBNode y = foundNode.left;

        x.left = y.right;
        if(y.right !=NA)
            y.right.parent = x;

        y.parent = x.parent;

        if(x.parent == NA){
            root = y;
        }
        else if(x.parent.left == x) {
            x.parent.left = y;
        }else if(x.parent.right == x){
            x.parent.right = y;
        }
        y.right = x;
        x.parent = y;
    }

    //TurnLeft(node);
    private RBNode InCreasingFor3Node(RBNode node) {
        if (node == NA)
            return NA;

        if (node.left == NA && (node.right != NA && node.right.left == NA) && (node.right.right != NA && node.right.right.left == NA)) {
            return node;
        }

        RBNode left = node.left;
        if (left != NA) {
            left = InCreasingFor3Node(node.left);
            if (left != NA)
                return left;
        }

        RBNode right = node.right;
        if (right != NA) {
            right = InCreasingFor3Node(node.right);
            if (right != NA)
                return right;
        }
        return NA;
    }

    private RBNode DeCreasingFor3Node(RBNode node) {
        if (node == NA)
            return NA;

        if (node.right == NA && (node.left != NA && node.left.right == NA) && (node.left.left != NA && node.left.left.right == NA)) {
            return node;
        }


        RBNode left = node.left;
        if (left != NA) {
            left = DeCreasingFor3Node(node.left);
            if(left !=NA)
                return left;
        }

        RBNode right = node.right;
        if (right != NA) {
            right = DeCreasingFor3Node(node.right);
            if(right !=NA)
                return right;
        }

        return  NA;
    }
    private void CheckRedColor()
    {

    }
    private void CheckRedColor(RBNode<V> parentNode,RBNode<V> node)
    {
        if(parentNode.color == RBColor.RED && parentNode.color == node.color)
        {
            if(parentNode.left == node && parentNode.right == NA ||
                    parentNode.right == node && parentNode.left == NA)
            {
                parentNode.color = RBColor.BLACK;
                if(parentNode.parent!= NA)
                    CheckRedColor(parentNode.parent,parentNode);
            }
        }
        if( node.color != RBColor.RED && parentNode.color == node.color)
        {
            RBNode<V> sibNode = parentNode.left;
            if( parentNode.left == node )
                sibNode = parentNode.right;

            if(node.color != sibNode.color&& (sibNode.left== NA || sibNode.right == NA))
            {
                sibNode.color = RBColor.RED;
                SetChildrenColor(sibNode);
            }
        }
    }

    private  void UpdateLeafNode()
    {
//        if(leftLeafNodes==null)
//            leftLeafNodes = new RBNode[]
//        if(leftLeafNodes.size()>0)
//            leftLeafNodes.clear();
//
//        if(rightLeafNodes==null)
//            rightLeafNodes = new ArrayList<>();
//        if(rightLeafNodes.size()>0)
//            rightLeafNodes.clear();
//
//        if(root!= NA && root.left !=NA)
//            UpdateLeafNode(root,leftLeafNodes);
//
//        if(root!= NA && root.right !=NA)
//            UpdateLeafNode(root,rightLeafNodes);
//
//
//        List<Integer> leftLevels=new ArrayList<>();
//        List<Integer> rightLevels=new ArrayList<>();
//
//        UpdateLevel(leftLeafNodes,leftLevels);
//        UpdateLevel(rightLeafNodes,rightLevels);
    }


    private  void UpdateLeafNode(RBNode node, List<RBNode<V>> leafNodes)
    {
        if(node!=NA && (node.left == NA || node.right == NA)) {
            leafNodes.add(node);
        }

        if(node.right != NA)
            UpdateLeafNode(node.left,leafNodes);

        if(node.right != NA)
            UpdateLeafNode(node.right,leafNodes);
    }


    private boolean IsLeftRoot(RBNode node)
    {
        while(node!= NA){
            if(node == root.left)
                return true;
            if(node == root.right || node == root)
                return  false;
            return IsLeftRoot(node.parent);
        }
        return  false;
    }
    private  void SetChildrenColor(RBNode<V> node){
        if(node.left != NA) {
            node.left.color = node.color == RBColor.RED ? RBColor.BLACK : RBColor.RED;
            SetChildrenColor(node.left);
        }
        if(node.right != NA) {
            node.right.color = node.color == RBColor.RED ? RBColor.BLACK : RBColor.RED;
            SetChildrenColor(node.right);
        }

    }

    private  void MoveToLeftest(RBNode<V> tmpNode,RBNode<V> movedNode){
        while (tmpNode != NA) {
            if (tmpNode.left != NA)
                tmpNode = tmpNode.left;
            else
                break;
        }
        if (tmpNode != NA && tmpNode.left == NA) {
            tmpNode.left = movedNode;
            movedNode.parent =tmpNode;
        }
    }

    private  void MoveToRightest(RBNode<V> tmpNode,RBNode<V> movedNode) {
        while (tmpNode != NA) {
            if (tmpNode.right != NA)
                tmpNode = tmpNode.right;
            else
                break;
        }
        if (tmpNode != NA && tmpNode.right == NA) {
            tmpNode.right = movedNode;
            movedNode.parent = tmpNode;
        }
    }

}
