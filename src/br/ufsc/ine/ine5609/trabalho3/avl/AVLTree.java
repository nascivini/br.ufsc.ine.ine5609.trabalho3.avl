package br.ufsc.ine.ine5609.trabalho3.avl;

/**
 *
 * @author vinicius.nascimento
 */
public class AVLTree {
    private Node mainRoot;
    
    public AVLTree(){
        this.mainRoot = null;
    }

    public Node getMainRoot() {
        return mainRoot;
    }

    public void setMainRoot(Node mainRoot) {
        this.mainRoot = mainRoot;
    }
    
    public boolean insertOnTree(int newElement){
        if(this.mainRoot == null){
            Node newRoot = new Node(newElement);
            this.mainRoot = newRoot;
            return true;
        }
        return false;
    }
    
    public void rotateLeft(){
    
    }
    
    public void rotateRight(){
    
    }
    
    public void rotateDoubleRight(){
    
    }
    
    public void rotateDoubleLeft(){
    
    }
    
    public void removeFromTree(int element){
        
    }
    
    public void makeBalance(){
        
    }
    
    private int heightOfNode(Node actual){ 
        if(actual == null){
            return -1;
        }
        if(actual.getLeftChild() == null && actual.getRightChild() == null){
            return 0;
        }
        else if(actual.getLeftChild() == null){
            return 1 + heightOfNode(actual.getRightChild());
        }
        else if(actual.getRightChild() == null){
            return 1 + heightOfNode(actual.getLeftChild());
        }
        else{
            return 1 + Math.max(heightOfNode(actual.getLeftChild()), heightOfNode(actual.getRightChild()));
        }
    }
    
    private void updateBalance(Node node){
        node.setBalance(heightOfNode(node.getRightChild())- heightOfNode(node.getLeftChild()));
    }
}
