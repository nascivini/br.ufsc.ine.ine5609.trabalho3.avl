package br.ufsc.ine.ine5609.trabalho3.avl;

/**
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * INE5609 - Estruturas de Dados 2018/1
 * @author vinicius.nascimento 17103176
 * @author marco.geremias 17103974
 */
public class Controlador {
    AVLTree arvore;
    
    Controlador(){
        this.arvore = new AVLTree();
    }

    public AVLTree getArvore() {
        return arvore;
    }

    public void setArvore(AVLTree arvore) {
        this.arvore = arvore;
    }
    
}
