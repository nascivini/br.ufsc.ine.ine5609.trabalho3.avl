package br.ufsc.ine.ine5609.trabalho3.avl;

/**
 *
 * @author vinicius.nascimento
 */
public class Node {
    private int root;
    private Node leftChild;
    private Node rightChild;
    private int balance;
    
    public Node(){
        this.leftChild = null;
        this.rightChild = null;
    }
    
    public Node(int element){
        this.root = element;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }
    
    public void setBalance(int newBalance){
        this.balance = newBalance;
    }
   
}
