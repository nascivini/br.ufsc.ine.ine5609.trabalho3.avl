package br.ufsc.ine.ine5609.trabalho3.avl;

/**
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * INE5609 - Estruturas de Dados 2018/1
 * @author vinicius.nascimento 17103176
 * @author marco.geremias 17103974
 */
public class Controlador {
    ArvoreAVL arvore;
    
    Controlador(){
        this.arvore = new ArvoreAVL();
    }

    public ArvoreAVL getArvore() {
        return arvore;
    }

    public void setArvore(ArvoreAVL arvore) {
        this.arvore = arvore;
    }
    
}
