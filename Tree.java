import java.util.*;

public class Tree {

    static class Node{
        int data ;
        Node left ;
        Node right ;

        Node(int data) {
            this.data = data ;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree{
        static int idx  = -1 ;
        public static Node buildTree(int nodes []){
            idx ++;
            if(nodes[idx] == -1){
            return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left =  buildTree(nodes);
            newNode.right =  buildTree(nodes);
            return  newNode;
        }
    }


    //preorder Treversal
    public static void  preorder(Node root) {

        if(root == null){
            // System.out.print("-1" + " ");
            return;
        }
        System.out.print(root.data+ " ");
        preorder(root.left);
        preorder(root.right);
    }
    
    ///Inorder Treersal...
    public static void inorder(Node root) {
        if(root == null){
            return;
        }

         inorder(root.left);
         System.out.print(root.data + " ");
         inorder(root.right );
    }


    //Post order Treversal...
    public static void Postorder(Node root){
        if(root == null){
            return ;
        }
        Postorder(root.left);
        Postorder(root.right);
        System.out.print(root.data + " ");
    }


    //Level Order Trevesal this is DFs treversal
    public static void levelOrder(Node root) {
        if(root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()) {
            Node curr = q.remove();
            if(curr == null) {
                System.out.println();
                //queue empty
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(curr.data+" ");
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }


    //number of nodes in tree
    public static int countOfNode(Node root) {

        if(root == null){
            return 0 ;
        }
        int leftNodes = countOfNode(root.left);
        int rightNodes = countOfNode(root.right);

        return leftNodes + rightNodes + 1 ;
    }

    //sum of datas in tree
    public static int countOfSum(Node root) {

        if(root == null) {
            return 0 ;
        }
        int leftSum = countOfSum(root.left);
        int rightSum = countOfSum(root.right);

        return leftSum + rightSum + root.data ;
    }

    //height  of the tree..
    public static int height(Node root){
        if(root == null) {
            return 0 ;
        }
         int leftHeight = height(root.left);
         int rightHeight = height(root.right);

         int myHeight = Math.max(leftHeight , rightHeight) + 1;
         return myHeight ;
    }



    //Diameter.. O(n^2) approach 1
    public  static int diameter(Node root){

        if(root == null){
            return 0 ;
        }
         int daim1 = diameter(root.left);
         int daim2 = diameter(root.right);
         int daim3 =    height(root.left) +  height(root.right) + 1;
         return Math.max(daim3 , Math.max(daim1, daim2 ));
    }

    //approach 2 for Diameter ...
    static class TreeInfo{
            int ht , diam;
            TreeInfo(int ht , int diam){
                this.ht = ht ;
                this.diam = diam ;  
            }
    }

    public static TreeInfo diameter2(Node root){

        if(root  == null){
            return new TreeInfo(0,0);
        }
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);
        int myHeight = Math.max(left.ht , right.ht) + 1;
        int diam1 = left.diam ;
        int diam2 = right.diam ;
        int diam3 = left.ht + right.ht + 1 ;
        int myDiam = Math.max(Math.max(diam1, diam2),diam3);
        TreeInfo myInfo = new TreeInfo(myHeight, myDiam);
        return myInfo ;
    }
    public static void main(String args[]){ 

        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1} ;
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        // levelOrder(root);
        // System.out.println(countOfSum(root));
        // System.out.println(height(root));
        System.out.println(diameter2(root).diam);
    }
}
// pre post and In are BFs treversals except Level it is DFs 
// Time compleity is O(n).. of all treversal