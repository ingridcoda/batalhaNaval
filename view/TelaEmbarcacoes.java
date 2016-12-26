package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import facade.Facade;
import model.Jogador;
import model.Matriz;

/* Classe de Visualização da Fase de Posicionamento de Armas */
@SuppressWarnings("serial")
public class TelaEmbarcacoes extends Frame implements ActionListener, Observer{
	private static TelaEmbarcacoes instancia1  = null;
	private static TelaEmbarcacoes instancia2  = null;
	public static boolean tabuleirosProntos = false;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private static JButton btnReady;
	private JLabel lblTitle;
	private JLabel lbl1;
	private static int qtdEmbarcacoesADistribuir = 30;
	public static Jogador jog1 = new Jogador("");
	public static Jogador jog2 = new Jogador("");
	public static Jogador jogVez = new Jogador("");
	public Tabuleiro t;
	public static int qtdNavio1 = 4;
	public static int qtdNavio2 = 3;
	public static int qtdNavio3 = 5;
	public static int qtdNavio4 = 2;
	public static int qtdNavio5 = 1;

	/* Construtor da Classe de Visualização */
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

		lbl1 = new JLabel("Jogador "+jogVez.nome+", distribua suas embarcações pelo tabuleiro:");
		lbl1.setBounds(240, 50, 500, 140);
		this.painel.add(lbl1);

		btnReady = new JButton("Pronto");
		btnReady.setBounds(350, 500, 100, 30);
		this.painel.add(btnReady);
		btnReady.addActionListener(this);
		btnReady.setEnabled(false);

		getContentPane().add(painel);
		this.painel.setLayout(null);
		this.painel.setVisible(true);

		this.setVisible(true);


	}	

	/* Singleton */
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

	/* Getters e Setters */
	public Matriz[][] getMatrizControle() {
		return jogVez.matriz;
	}

	/* Verificação de Tabuleiro Pronto */
	public static void verificaPronto(){

		/* verifica se número de embarcações a distribuir é zero*/
		if(qtdEmbarcacoesADistribuir == 0){

			/* seta a variável tabuleirosProntos como true e habilita o botão "Pronto" */
			tabuleirosProntos = true;
			btnReady.setEnabled(true);

		}

	}

	/* Adicionar Navios */
	public void addHidroaviao(){
		int i = 0;
		int x = 30;
		int y = 150;

		/* adiciona hidroaviões enquanto o número de adicionados 
		 * for menor que a quantidade total que deve existir */
		while(i < qtdNavio3){
			this.add(new Hidroaviao(Color.BLACK, x, y));
			x += 80;
			i++;
		}

	}

	public void addSubmarino(){
		int i = 0;
		int x = 30;
		int y = 230;

		/* adiciona submarinos enquanto o número de adicionados 
		 * for menor que a quantidade total que deve existir */
		while(i < qtdNavio1){
			this.add(new Submarino(Color.BLACK, x, y));
			x += 100;
			i++;
		}

	}


	public void addDestroyer(){
		int i = 0;
		int x = 30;
		int y = 300;

		/* adiciona destroyers enquanto o número de adicionados 
		 * for menor que a quantidade total que deve existir */
		while(i < qtdNavio2){
			this.add(new Destroyer(Color.BLACK, x, y));
			x += 110;
			i++;
		}

	}

	public void addCruzador(){
		int i = 0;
		int x = 30;
		int y = 370;

		/* adiciona cruzadores enquanto o número de adicionados 
		 * for menor que a quantidade total que deve existir */
		while(i < qtdNavio4){
			this.add(new Cruzador(Color.BLACK, x, y));
			x += 120;
			i++;
		}

	}


	public void addCouracado(){
		int i = 0;
		int x = 30;
		int y = 440;

		/* adiciona couraçados enquanto o número de adicionados 
		 * for menor que a quantidade total que deve existir */
		while(i < qtdNavio5){
			this.add(new Couracado(Color.BLACK, x, y));
			i++;
		}

	}

	/* Tratamento de Evento de Botão */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* verifica se botão foi clicado e tabuleiro está pronto */
		if(e.getSource() == btnReady && tabuleirosProntos == false){

			/* seta a tela de posicionamento de embarcações como invisível, 
			 * a encerra e chama método de posicionamento das embarcações 
			 * do segundo jogador, que está definido na classe de fachada */
			this.setVisible(false);
			this.dispose();
			Facade.posicionarArmasJogador2(jog2.nome);

		} else if (e.getSource() == btnReady && tabuleirosProntos == true){

			/* seta a tela de posicionamento de embarcações como 
			 * invisível, a encerra e chama método de início de fase 
			 * de ataques, que está definido na classe de fachada */
			this.setVisible(false);
			Facade.iniciarAtaques();

		} else {

			/* finaliza o jogo */
			System.exit(1);

		}

	}

	/* Observer */
	@Override
	public void update(Observable o, Object obj) {

		/* verifica se observado mudou em algo */
		if(o.hasChanged()){

			/* matriz do jogador recebe matriz atualizada e quantidade 
			 * de embarcações a distribuir é decrementado em um */
			jogVez.matriz = (Matriz[][]) obj;
			qtdEmbarcacoesADistribuir--;

		}

		/* redesenha elementos da tela atualizados */
		this.t.repaint();
		this.painel.repaint();
		this.repaint();

	}

}
