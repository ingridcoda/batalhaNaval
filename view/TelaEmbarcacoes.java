package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import facade.Facade;
import model.Jogador;
import model.Matriz;
import controller.*;

/*Classe de Visualização da Fase de Posicionamento de Armas*/
@SuppressWarnings("serial")
public class TelaEmbarcacoes extends Frame implements ActionListener, Observer{
	private static TelaEmbarcacoes instancia1  = null;
	private static TelaEmbarcacoes instancia2  = null;
	private boolean tabuleirosProntos = false;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JButton btnReady;
	private JLabel lblTitle;
	private JLabel lbl1;
	private int numEmbarcacoesADistribuir = 30;
	public static Jogador jog1 = new Jogador("");
	public static Jogador jog2 = new Jogador("");
	public static Jogador jogVez = new Jogador("");
	public Tabuleiro t;
	public static int numNavio1 = 4;
	public static int numNavio2 = 3;
	public static int numNavio3 = 5;
	public static int numNavio4 = 2;
	public static int numNavio5 = 1;
	
	/*Construtor da Classe de Visualização*/
	public TelaEmbarcacoes(Jogador j){
		super();
		TelaEmbarcacoes.jogVez = j;
		this.t = new Tabuleiro(jogVez, 450, 150);
		
		getContentPane().add(painel);
		this.add(t);
		addHidroaviao();
		addSubmarino();
		addDestroyer();
		addCruzador();
		addCouracado();
		
		
		this.setBackground(Color.WHITE);
		
		lblTitle = new JLabel("BATALHA NAVAL");
		lblTitle.setBounds(160, -250, 800, 600);
		lblTitle.setFont(fonteTitulo);
		this.painel.setBounds(0, 0, 800, 600);
		this.painel.add(lblTitle);
		
		lbl1 = new JLabel("Jogador "+jogVez.getNome()+", distribua suas embarcações pelo tabuleiro:");
		lbl1.setBounds(240, 50, 500, 140);
		this.painel.add(lbl1);
		
		btnReady = new JButton("Pronto");
		btnReady.setBounds(350, 500, 100, 30);
		this.painel.add(btnReady);
		btnReady.addActionListener(this);
		//btnReady.setEnabled(false);
		
		getContentPane().add(painel);
		this.painel.setLayout(null);
		this.painel.setVisible(true);
		
		this.setVisible(true);
		
		
	}	

	/*Singleton*/
	public static synchronized TelaEmbarcacoes getInstance1(){
		if(instancia1 == null){
			instancia1 = new TelaEmbarcacoes(jogVez);
		} 
		return instancia1;
	}
	
	public static synchronized TelaEmbarcacoes getInstance2(){
		if(instancia2 == null){
			instancia2 = new TelaEmbarcacoes(jogVez);
		} 
		return instancia2;
	}
	
	/*Getters e Setters*/
	public Matriz[][] getMatrizControle() {
		return jogVez.matriz;
	}
	
	/*VerificaÃ§Ã£o de Tabuleiro Pronto*/
	public void verificaPronto(){
		if(this.numEmbarcacoesADistribuir == 0){
			tabuleirosProntos = true;
			btnReady.setEnabled(true);
		}
	}
	
	/*Adicionar Navios*/
	public void addHidroaviao(){
		int i = 0;
		int x = 30;
		int y = 150;
		while(i < numNavio3){
			this.add(new Hidroaviao(Color.BLACK, x, y));
			x += 80;
			i++;
		}
	}
	
	public void addSubmarino(){
		int i = 0;
		int x = 30;
		int y = 230;
		while(i < numNavio1){
			this.add(new Submarino(Color.BLACK, x, y));
			x += 100;
			i++;
		}
	}
	
	
	public void addDestroyer(){
		int i = 0;
		int x = 30;
		int y = 300;
		while(i < numNavio2){
			this.add(new Destroyer(Color.BLACK, x, y));
			x += 110;
			i++;
		}
	}
	
	public void addCruzador(){
		int i = 0;
		int x = 30;
		int y = 370;
		while(i < numNavio4){
			this.add(new Cruzador(Color.BLACK, x, y));
			x += 120;
			i++;
		}
	}
	
	
	public void addCouracado(){
		int i = 0;
		int x = 30;
		int y = 440;
		while(i < numNavio5){
			this.add(new Couracado(Color.BLACK, x, y));
			i++;
		}
	}

	/*Tratamento de Evento de BotÃ£o*/
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource() == btnReady && tabuleirosProntos == false){
			this.setVisible(false);
			this.dispose();
			//Facade.posicionarArmasJogador2(getNomeJogador2());
		//} else if (e.getSource() == btnReady && tabuleirosProntos == true){
			//this.setVisible(false);
			Facade.iniciarAtaques();
		} else {
			System.exit(1);
		}
	}
	
	/*Observer*/
	@Override
	public void update(Observable o, Object obj) {
		if(o.hasChanged()){
			jogVez.matriz = (Matriz[][]) obj;
			this.numEmbarcacoesADistribuir--;
		}
		this.t.repaint();
		this.painel.repaint();
		this.repaint();
	}

}
