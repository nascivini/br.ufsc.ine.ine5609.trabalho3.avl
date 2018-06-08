package br.ufsc.ine.ine5609.trabalho3.avl;

/**
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * INE5609 - Estruturas de Dados 2018/1
 * @author vinicius.nascimento 17103176
 * @author marco.geremias 17103974
 */
public class Nodo {
        private int dado;
	private int numBalanceamento;
        private Nodo filhoEsquerda;
	private Nodo filhoDireita;
	private Nodo nodoPai;
        
        /**
         * Método construdor da classe Nodo. 
         * @param dado Valor da dado (valor bruto do dado), passado no momento da criação do nodo.
         */
	public Nodo(int dado) {
		setFilhoEsquerda(setDireita(setPai(null)));
		setNumBalanceamento(0);
		setDado(dado);
	}
        
        /**
         * Método implementado para auxiliar no 'print' da árvore AVL.
         * @return String com o dado bruto do nodo
         */
	public String toString() {
		return Integer.toString(getDado());
	}

	public int getDado() {
		return dado;
	}

	public void setDado(int dado) {
		this.dado = dado;
	}

	public int getNumBalanceamento() {
		return numBalanceamento;
	}

	public void setNumBalanceamento(int numBalanceamento) {
		this.numBalanceamento = numBalanceamento;
	}

	public Nodo getNodoPai() {
		return nodoPai;
	}

	public Nodo setPai(Nodo pai) {
		this.nodoPai = pai;
		return pai;
	}

	public Nodo getFilhoDireita() {
		return filhoDireita;
	}

	public Nodo setDireita(Nodo direita) {
		this.filhoDireita = direita;
		return direita;
	}

	public Nodo getFilhoEsquerda() {
		return filhoEsquerda;
	}

	public void setFilhoEsquerda(Nodo filhoEsquerda) {
		this.filhoEsquerda = filhoEsquerda;
	}
        
}