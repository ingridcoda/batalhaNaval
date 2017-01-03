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
	private static int quantidadeTabuleirosProntos; //NOVO
	private static boolean tabuleirosProntos;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JButton btnReady;
	private JLabel lblTitle;
	private JLabel lbl1;
	private static int qtdEmbarcacoesADistribuir = 15;
	public static Jogador jogVez; //modificado
	public Tabuleiro t;
	public static int qtdNavio1 = 4;
	public static int qtdNavio2 = 3;
	public static int qtdNavio3 = 5;
	public static int qtdNavio4 = 2;
	public static int qtdNavio5 = 1;

	/* Construtor da Classe de Visualização */
	private TelaEmbarcacoes(int idJogador){
		super();
		
		jogVez = Facade.getJogador(idJogador);
		this.t = new Tabuleiro(jogVez, 450, 150, 'p');

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
	
	public static TelaEmbarcacoes getInstance(int idInstancia){
		if(idInstancia==1){
			if(instancia1 == null)
				instancia1 = new TelaEmbarcacoes(1);

			return instancia1;
		}
		else{
			if(instancia2 == null)
				instancia2 = new TelaEmbarcacoes(2);

			return instancia2;
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
			this.add(new Hidroaviao(Color.RED, x, y));
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
			this.add(new Submarino(Color.ORANGE, x, y));
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
			this.add(new Destroyer(Color.YELLOW, x, y));
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
			this.add(new Cruzador(Color.GREEN, x, y));
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
			this.add(new Couracado(Color.CYAN, x, y));
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
			this.setEnabled(false);
			this.dispose();
			Facade.posicionarArmasJogador(2);

		} else if (e.getSource() == btnReady && tabuleirosProntos == true){

			/* seta a tela de posicionamento de embarcações como 
			 * invisível, a encerra e chama método de início de fase 
			 * de ataques, que está definido na classe de fachada */
			this.setVisible(false);
			this.setEnabled(false);
			this.dispose();
			Facade.iniciarAtaques();

		} else {
			System.out.println("NÃO ERA PRA ACONTECER!!!");
			/* finaliza o jogo */
			//System.exit(1);

		}

	}
	
	public void repintarTela(){
		this.t.repaint();
		this.painel.repaint();
		this.repaint();
	}

	/* Observer */
	@Override
	public void update(Observable o, Object obj) {

		/* verifica se observado mudou em algo */
		if(o.hasChanged()){
			System.out.println("Observador foi alertado de mudanças! Faltam "+qtdEmbarcacoesADistribuir+" navios!");
			/* matriz do jogador recebe matriz atualizada e quantidade 
			 * de embarcações a distribuir é decrementado em um */
			jogVez.matriz = (Matriz[][]) obj;
			qtdEmbarcacoesADistribuir--;
			Navio.setSemPendencia();
			
			if(qtdEmbarcacoesADistribuir==0){
				System.out.println("Jogador completou a distribuição de seus navios!");
				btnReady.setEnabled(true);
				quantidadeTabuleirosProntos++;
				qtdEmbarcacoesADistribuir=15;
			}
			
			if(quantidadeTabuleirosProntos==2){
				System.out.println("Pronto para iniciar jogo!");
				tabuleirosProntos = true;
			}
		}
		else
			System.out.println("Update Observer Nada Mudou!");
		/* redesenha elementos da tela atualizados */
		repintarTela();

	}

}
