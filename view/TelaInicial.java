package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import facade.Facade;

/* Classe de Visualiza��o Inicial */
@SuppressWarnings("serial")
public class TelaInicial extends Frame implements ActionListener{
	private static TelaInicial instancia;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lblText;
	public static JButton btnNewGame;
	public static JButton btnLoadGame;
	
	/* Construtor da Classe de Visualiza��o */
	private TelaInicial(){
		super();
		
		this.setBackground(Color.WHITE);
		
		lblTitle = new JLabel("BATALHA NAVAL");
		lblTitle.setBounds(160, -250, 800, 600);
		lblTitle.setFont(fonteTitulo);
		this.painel.setBounds(0, 0, 800, 600);
		this.painel.add(lblTitle);
		
		lblText = new JLabel("Escolha entre iniciar novo jogo ou carregar jogo salvo:");
		lblText.setBounds(240, -120, 800, 600);
		this.painel.add(lblText);
		
		btnNewGame = new JButton("Novo Jogo");
		btnNewGame.setBounds(260, 250, 120, 40);
		this.painel.add(btnNewGame);
		btnNewGame.addActionListener(this);
		
		btnLoadGame = new JButton("Carregar Jogo");
		btnLoadGame.setBounds(410, 250, 120, 40);
		this.painel.add(btnLoadGame);
		btnLoadGame.addActionListener(this);
		
		this.painel.setLayout(null);
		this.painel.setVisible(true);
		this.painel.setBackground(Color.WHITE);
		this.getContentPane().add(painel);
		
		this.setVisible(true);
		
	}

	public static void mostrarTela(){
		if(instancia == null){
			instancia = new TelaInicial();
		}
	}
	
	/* Tratamento de Evento de Bot�o */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* verifica qual bot�o foi clicado */
		if(e.getSource() == btnNewGame){
			
			/* seta visibilidade da tela inicial como falsa, a encerra 
			 * e chama m�todo novo jogo, definido na classe da fachada */
			this.setVisible(false);
			this.dispose();
			Facade.novoJogo();
			
		} else if (e.getSource() == btnLoadGame){
			
			/* seta visibilidade da tela inicial como falsa, a encerra, cria duas inst�ncias de jogadores
			 * com nomes vazios e chama m�todo de carregamento de jogo, definido na classe de fachada, 
			 * passando uma inst�ncia da tela de campo de batalha e os jogadores criados*/
			this.setVisible(false);
			this.dispose();
			Facade.carregarJogo();
			
		} else {
			
			/* finaliza o jogo */
			System.exit(1);
			
		}
		
	}
}
