package br.ufsc.ine.ine5609.trabalho3.avl;

/**
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * INE5609 - Estruturas de Dados 2018/1
 * @author vinicius.nascimento 17103176
 * @author marco.geremias 17103974
 */
public class main {
    /**
     * Inicia a aplicação, instanciando o controlador e a tela.
     * @param args Argumentos para startar a aplicação.
     */
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        Screen tela = new Screen(controlador);
        tela.setVisible(true);
    }
    
}
