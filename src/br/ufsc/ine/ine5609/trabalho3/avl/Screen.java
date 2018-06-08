package br.ufsc.ine.ine5609.trabalho3.avl;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Universidade Federal de Santa Catarina - UFSC
 * Departamento de Informática e Estatística - INE
 * INE5609 - Estruturas de Dados 2018/1
 * @author vinicius.nascimento 17103176
 * @author marco.geremias 17103974
 */
public class Screen extends JFrame {
    private Controlador controlador;
    private JButton inserir, listar, limpar, sair, excluir;
    private JTextField dadosInserir;
    private JLabel arvoreListada, descricaoTela;
  
    public Screen(Controlador controlador){
        this.createComponents();
        this.controlador = controlador;
    }
    
    private void createComponents(){
        this.descricaoTela = new JLabel("Insira os dados na caixa de texto para excl. ou ins.");
        this.inserir = new JButton("Inserir Dados");
        this.listar = new JButton("Listar");
        this.limpar = new JButton("Limpar");
        this.sair = new JButton("Sair");
        this.excluir = new JButton("Excluir");
       
        this.dadosInserir = new JTextField();
        this.arvoreListada = new JLabel();
        
        Dimension dimensaoBotoes = new Dimension(125, 40);
        Dimension dimensaoTextos = new Dimension(150,30);
        Dimension dimensaoLabel = new Dimension(150,30);
        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());
        
        GerenciadorBotoes gerenciador = new GerenciadorBotoes();
        GridBagConstraints c = new GridBagConstraints();
        
        
        this.inserir.addActionListener(gerenciador);
        this.listar.addActionListener(gerenciador);
        this.sair.addActionListener(gerenciador);
        this.limpar.addActionListener(gerenciador);
        this.excluir.addActionListener(gerenciador);
        
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(0,0,90,0);
        container.add(this.descricaoTela, c);
        
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(0,10,0,0);
        inserir.setPreferredSize(dimensaoBotoes);
        container.add(this.inserir, c);
        
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.WEST;  
        excluir.setPreferredSize(dimensaoBotoes);
        c.insets = new Insets(10,10,10,10);
        container.add(this.excluir, c);
        
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;     
        c.insets = new Insets(10,10,10,10);
        listar.setPreferredSize(dimensaoBotoes);
        container.add(this.listar, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.CENTER;  
        limpar.setPreferredSize(dimensaoBotoes);
        c.insets = new Insets(10,10,10,10);
        container.add(this.limpar, c);
        
        c.gridx = 0;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;  
        sair.setPreferredSize(dimensaoBotoes);
        c.insets = new Insets(10,10,10,10);
        container.add(this.sair, c);
        
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.EAST;
        dadosInserir.setPreferredSize(dimensaoTextos);
        c.insets = new Insets(10,10,10,10);
        container.add(this.dadosInserir, c);
        
        c.gridx = 1;
        c.gridy = 1;
        c.anchor = GridBagConstraints.WEST;     
        c.insets = new Insets(10,10,10,10);
        arvoreListada.setPreferredSize(dimensaoLabel);
        container.add(this.arvoreListada, c);
        
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public void validaNumero(JTextField Numero) { 
	long valor; 
	if (Numero.getText().length() != 0){ 
		try { 
			valor = Long.parseLong(Numero.getText()); 
		}catch(NumberFormatException ex){ 
			JOptionPane.showMessageDialog(null, "Esse Campo só aceita números" ,"Atenção",JOptionPane.ERROR_MESSAGE);
                        Numero.grabFocus(); 
                        Numero.setText("");
		} 
	} 
} 

    private class GerenciadorBotoes implements ActionListener {

        public GerenciadorBotoes() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            arvoreListada.setText("");
            if(e.getSource() == inserir){
                validaNumero(dadosInserir);
                controlador.getArvore().inserir(Integer.parseInt((dadosInserir.getText())));
                JOptionPane.showMessageDialog(null, "Elemento " + dadosInserir.getText() + " inserido!","Informação", JOptionPane.INFORMATION_MESSAGE);
                dadosInserir.setText("");
            }
            else if(e.getSource() == listar){
                ArrayList<Nodo> teste = controlador.getArvore().inorder();
                String texto = "Árvore: " + arvoreListada.getText();
                for(Nodo atual : teste){
                    texto = texto.concat(Integer.toString(atual.getDado()) + ", ");
                }
                arvoreListada.setText(texto);
            }
            else if(e.getSource() == excluir){
                validaNumero(dadosInserir);
                controlador.getArvore().remover(Integer.parseInt((dadosInserir.getText())));
                JOptionPane.showMessageDialog(null, "Elemento " + dadosInserir.getText() + " excluído!","Informação", JOptionPane.INFORMATION_MESSAGE);
                dadosInserir.setText("");
            }
            else if(e.getSource() == limpar){
                arvoreListada.setText("");
                dadosInserir.setText("");
            }
            else if(e.getSource() == sair){
                System.exit(0);    
            }
        }
    }
    }




