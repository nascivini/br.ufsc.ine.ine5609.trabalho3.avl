package br.ufsc.ine.ine5609.trabalho3.avl;

import java.util.ArrayList;

/**
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * INE5609 - Estruturas de Dados 2018/1
 * @author vinicius.nascimento 17103176
 * @author marco.geremias 17103974
 */
public class AVLTree {

    private Nodo raizPrincipal;
    
    /**
     * Construtor. Instancia uma árvore AVL padrão sem dados.
     */
    public AVLTree() {
        this.raizPrincipal = null;
    }
    
     /**
     * Realiza uma caminhada in-order na árvore e retorna um ArrayList com todos os nós da árvore ordenados.
     * Ordena os nodos recursivamente com o apoio do método 'filho' inorder(com parâmetros)
     * @return ArrayList<Nodo> com todos os nós da árvore ordenados.
     * Utilizado especificamente na listagem ou na 'impressão' da árvore.
     */
    final protected ArrayList<Nodo> inorder() {
        ArrayList<Nodo> ret = new ArrayList<>();
        inorder(raizPrincipal, ret);
        return ret;
    }
    
    /**
     * Método de apoio para a recursividade da caminhada em ordem.
     * @param nodo Nodo raiz atual.
     * @param lista Lista atual. (será atualizada na recursão)
     */
    final protected void inorder(Nodo nodo, ArrayList<Nodo> lista) {
        if (nodo == null) {
            return;
        }
        inorder(nodo.getFilhoEsquerda(), lista);
        lista.add(nodo);
        inorder(nodo.getFilhoDireita(), lista);
    }
    
    /**
     * Método padrão para inserção de dados na árvore. Utiliza os métodos subsequentes da classe para inserir corretamente seguindo os padrões AVL.
     * @param dado Recebe como parâmetro um número inteiro a ser inserido.
     */
    public void inserir(int dado) {
        Nodo n = new Nodo(dado);
        inserirAVL(this.raizPrincipal, n);
    }
    
    /**
     * Método usado para realizar a inserção na lista de forma balanceada.
     * Recebe como parâmetro o nodo a ser inserido e outro nodo a ser comparado (recursivamente)
     * Utiliza as especializações de métodos da classe para verificar o balanceamento e rotacionar os dados na árvore se necessário.
     * @param aSerComparado Nodo a comparar na inserção.
     * @param aInserir Nodo a ser inserido na árvore.
     */
    private void inserirAVL(Nodo aSerComparado, Nodo aInserir) {

        if (aSerComparado == null) {
            this.raizPrincipal = aInserir;

        } 
        else {
            if (aInserir.getDado() < aSerComparado.getDado()) {
                if (aSerComparado.getFilhoEsquerda() == null) {
                    aSerComparado.setFilhoEsquerda(aInserir);
                    aInserir.setPai(aSerComparado);
                    verificarBalanceamento(aSerComparado);
                } 
                else {
                    inserirAVL(aSerComparado.getFilhoEsquerda(), aInserir);
                }
            } 
            else if (aInserir.getDado() > aSerComparado.getDado()) {
                if (aSerComparado.getFilhoDireita() == null) {
                    aSerComparado.setDireita(aInserir);
                    aInserir.setPai(aSerComparado);
                    verificarBalanceamento(aSerComparado);
                } else {
                    inserirAVL(aSerComparado.getFilhoDireita(), aInserir);
                }
            } 
            else {
                // O nó já existe
            }
        }
    }
    
    /**
     * Método utilizado APENAS por meio do método inserirAVL.
     * Verifica o balanceamento do nodo passado como parâmetro.
     * @param atual Nodo a ter o balanceamento verificado.
     */
    private void verificarBalanceamento(Nodo atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getNumBalanceamento();
        if (balanceamento == -2) {
            if (calculaAltura(atual.getFilhoEsquerda().getFilhoEsquerda()) >= calculaAltura(atual.getFilhoEsquerda().getFilhoDireita())) {
                atual = rotacaoDireita(atual);
            } 
            else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }
        } 
        else if (balanceamento == 2) {
            if (calculaAltura(atual.getFilhoDireita().getFilhoDireita()) >= calculaAltura(atual.getFilhoDireita().getFilhoEsquerda())) {
                atual = rotacaoEsquerda(atual);
            } 
            else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }
        if (atual.getNodoPai() != null) {
            verificarBalanceamento(atual.getNodoPai());
        } 
        else {
            this.raizPrincipal = atual;
        }
    }
    
    /**
     * Seguindo a mesma lógica da inserção, é o método que starta a exclusão do elemento na árvore.
     * @param dado Valor a ser removido. 
     */
    public void remover(int dado) {
        removerAVL(this.raizPrincipal, dado);
    }

    /**
     * Remove o elemento passado como parâmetro com base na raiz passada inicialmente também como parâmetro.
     * Realiza a exclusão de forma recursiva.
     * Ao encontrar o valor a ser excluído, utiliza o método removerNodoEncontrado para excluí-lo efetivamente e rebalancear a árvore se necessário.
     * @param atual Nodo atual (a raiz principal inicialmente)
     * @param dado (valor a ser inserido)
     */
    private void removerAVL(Nodo atual, int dado) {
        if (atual == null) {
            return;
        } 
        else {
            if (atual.getDado() > dado) {
                removerAVL(atual.getFilhoEsquerda(), dado);
            } 
            else if (atual.getDado() < dado) {
                removerAVL(atual.getFilhoDireita(), dado);
            } 
            else if (atual.getDado() == dado) {
                removerNodoEncontrado(atual);
            }
        }
    }
    
    /**
     * Remove o nodo passado como parâmetro da árvore.
     * Balanceia a árvore novamente se necessário utilizando os métodos especializados.
     * @param aRemover Nodo a ser removido.
     */
    private void removerNodoEncontrado(Nodo aRemover) {
        Nodo r;
        if (aRemover.getFilhoEsquerda() == null || aRemover.getFilhoDireita() == null) {
            if (aRemover.getNodoPai() == null) {
                this.raizPrincipal = null;
                aRemover = null;
                return;
            }
            r = aRemover;
        } 
        else {
            r = sucessor(aRemover);
            aRemover.setDado(r.getDado());
        }
        Nodo p;
        if (r.getFilhoEsquerda() != null) {
            p = r.getFilhoEsquerda();
        } 
        else {
            p = r.getFilhoDireita();
        }
        if (p != null) {
            p.setPai(r.getNodoPai());
        }
        if (r.getNodoPai() == null) {
            this.raizPrincipal = p;
        } 
        else {
            if (r == r.getNodoPai().getFilhoEsquerda()) {
                r.getNodoPai().setFilhoEsquerda(p);
            } else {
                r.getNodoPai().setDireita(p);
            }
            verificarBalanceamento(r.getNodoPai());
        }
        r = null;
    }

    /**
     * Rotaciona o nodo à esquerda, manipulando os apontamentos.
     * @param nodo Nodo base a ser rotacionado.
     * @return Retorna o nodo 'corrigido'.
     */
    private Nodo rotacaoEsquerda(Nodo nodo) {
        Nodo direita = nodo.getFilhoDireita();
        direita.setPai(nodo.getNodoPai());
        nodo.setDireita(direita.getFilhoEsquerda());
        if (nodo.getFilhoDireita() != null) {
            nodo.getFilhoDireita().setPai(nodo);
        }
        direita.setFilhoEsquerda(nodo);
        nodo.setPai(direita);
        if (direita.getNodoPai() != null) {
            if (direita.getNodoPai().getFilhoDireita() == nodo) {
                direita.getNodoPai().setDireita(direita);
            } else if (direita.getNodoPai().getFilhoEsquerda() == nodo) {
                direita.getNodoPai().setFilhoEsquerda(direita);
            }
        }
        setBalanceamento(nodo);
        setBalanceamento(direita);
        return direita;
    }

    
    /**
     * Rotaciona o nodo à direita, manipulando os apontamentos.
     * @param nodo Nodo base a ser rotacionado.
     * @return Retorna o nodo 'corrigido'.
     */
    private Nodo rotacaoDireita(Nodo nodo) {
        Nodo esquerda = nodo.getFilhoEsquerda();
        esquerda.setPai(nodo.getNodoPai());
        nodo.setFilhoEsquerda(esquerda.getFilhoDireita());
        if (nodo.getFilhoEsquerda() != null) {
            nodo.getFilhoEsquerda().setPai(nodo);
        }
        esquerda.setDireita(nodo);
        nodo.setPai(esquerda);
        if (esquerda.getNodoPai() != null) {
            if (esquerda.getNodoPai().getFilhoDireita() == nodo) {
                esquerda.getNodoPai().setDireita(esquerda);

            } 
            else if (esquerda.getNodoPai().getFilhoEsquerda() == nodo) {
                esquerda.getNodoPai().setFilhoEsquerda(esquerda);
            }
        }
        setBalanceamento(nodo);
        setBalanceamento(esquerda);
        return esquerda;
    }
    
    /**
     * Rotaciona o nodo duplamente. Inicialmente à esquerda, e posteriormente à direita.
     * @param nodo Nodo a ser rotacionado.
     * @return Nodo 'corrigido'.
     */
    private Nodo duplaRotacaoEsquerdaDireita(Nodo nodo) {
        nodo.setFilhoEsquerda(rotacaoEsquerda(nodo.getFilhoEsquerda()));
        return rotacaoDireita(nodo);
    }

    
    /**
     * Rotaciona o nodo duplamente. Inicialmente à direita, e posteriormente à esquerda.
     * @param nodo Nodo a ser rotacionado.
     * @return Nodo 'corrigido'.
     */
    private Nodo duplaRotacaoDireitaEsquerda(Nodo inicial) {
        inicial.setDireita(rotacaoDireita(inicial.getFilhoDireita()));
        return rotacaoEsquerda(inicial);
    }
    
    /**
     * Substitui o nodo recebido (vindo da exclusão) por seu substituto após encontrá-lo, depois também de calcular seu sucessor na iteração.
     * @param aSerSubstituido
     * @return Retorna o novo nodo inserido no local do nodo passado por parâmetro.
     */
    public Nodo sucessor(Nodo aSerSubstituido) {
        if (aSerSubstituido.getFilhoDireita() != null) {
            Nodo nodo1 = aSerSubstituido.getFilhoDireita();
            while (nodo1.getFilhoEsquerda() != null) {
                nodo1 = nodo1.getFilhoEsquerda();
            }
            return nodo1;
        } else {
            Nodo nodo2 = aSerSubstituido.getNodoPai();
            while (nodo2 != null && aSerSubstituido == nodo2.getFilhoDireita()) {
                aSerSubstituido = nodo2;
                nodo2 = aSerSubstituido.getNodoPai();
            }
            return nodo2;
        }
    }

    /**
     * Calcula a 'calculaAltura' do nodo passado por parâmetro (calculaAltura da direita - calculaAltura da esquerda)
     * @param atual Nodo para o cálculo.
     * @return 'calculaAltura': (Altura da direita - Altura da esquerda)
     */
    private int calculaAltura(Nodo atual) {
        if (atual == null) {
            return -1;
        }
        if (atual.getFilhoEsquerda() == null && atual.getFilhoDireita() == null) {
            return 0;
        } 
        else if (atual.getFilhoEsquerda() == null) {
            return 1 + calculaAltura(atual.getFilhoDireita());
        } 
        else if (atual.getFilhoDireita() == null) {
            return 1 + calculaAltura(atual.getFilhoEsquerda());
        } 
        else {
            return 1 + Math.max(calculaAltura(atual.getFilhoEsquerda()), calculaAltura(atual.getFilhoDireita()));
        }
    }
    
    /**
     * Atualiza o balanceamento dos nodos de acordo com o cálculo feito pelo método calculaAltura.
     * @param no 
     */
    private void setBalanceamento(Nodo no) {
        no.setNumBalanceamento(calculaAltura(no.getFilhoDireita()) - calculaAltura(no.getFilhoEsquerda()));
    }

}

