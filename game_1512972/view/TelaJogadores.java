package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import facade.*;

/* Classe de Visualiza��o da Fase de Cria��o de Jogadores */
@SuppressWarnings("serial")
public class TelaJogadores extends Frame implements ActionListener{
	private static TelaJogadores instancia;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lblText;
	private JLabel lblNome1;
	private JLabel lblNome2;
	public static JTextField txtfNome1 = null;
	public static JTextField txtfNome2 = null;
	public static JButton btnStart;
	public static JButton btnCancel;
	
	/* Construtor da Classe de Visualiza��o */
	private TelaJogadores(){
		super();
		
		lblTitle = new JLabel("BATALHA NAVAL");
		lblTitle.setBounds(160, -250, 800, 600);
		lblTitle.setFont(fonteTitulo);
		this.painel.setBounds(0, 0, 800, 600);
		this.painel.add(lblTitle);
		
		lblText = new JLabel("Digite os nomes dos jogadores:");
		lblText.setBounds(295, 100, 200, 50);
		this.painel.add(lblText);
		
		lblNome1 = new JLabel("Jogador 1:");
		lblNome1.setBounds(250, 150, 100, 50);
		this.painel.add(lblNome1);
		
		txtfNome1 = new JTextField();
		txtfNome1.setBounds(320, 160, 200, 30);
		this.painel.add(txtfNome1);
			
		lblNome2 = new JLabel("Jogador 2:");
		lblNome2.setBounds(250, 200, 100, 50);
		this.painel.add(lblNome2);
		
		txtfNome2 = new JTextField();
		txtfNome2.setBounds(320, 210, 200, 30);
		this.painel.add(txtfNome2);
		
		btnStart = new JButton("Iniciar");
		btnStart.setBounds(280, 300, 100, 40);
		this.painel.add(btnStart);
		btnStart.addActionListener(this);
			
		btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(390, 300, 100, 40);
		this.painel.add(btnCancel);
		btnCancel.addActionListener(this);
		
		this.painel.setLayout(null);
		this.painel.setVisible(true);
		this.painel.setBackground(Color.WHITE);
		this.getContentPane().add(painel);	
		
		this.setLayout(null);
		this.setVisible(true);			
	}
	
	public static void mostrarTela(){
		if(instancia == null){
			instancia = new TelaJogadores();
		}
	}
	
	/* Getters e Setters */
	public static String getNomeJogador(int idJogador){
		if(idJogador==1)
			return txtfNome1.getText();
		else
			return txtfNome2.getText();
	}
	
	/* Alternar Nome do Jogador */
	public static String alteraNomeJogador(String nomeJogador){
		
		/*verifica nome do jogador dado por par�metro*/
		if(nomeJogador.equals(getNomeJogador(1))){
			
			/* atribui nome do segundo jogador ao nome dado por par�metro */
			nomeJogador = getNomeJogador(2);
			
		} else {
			
			/* atribui nome do primeiro jogador ao nome dado por par�metro */
			nomeJogador = getNomeJogador(1);	
			
		}
		
		/* retorna nome do jogador */
		return nomeJogador;
	}
	
	/* Tratamento de Evento de Bot�o */
	@Override
	public void actionPerformed(ActionEvent e) {
		/* verifica bot�o clicado*/
		if(e.getSource() == btnStart){
			
			/* seta visibilidade da tela de novo jogo como falsa, a encerra e chama
			 * m�todos de cria��o de jogadores e de cria��o da tela de posicionamento 
			 * de embarca��es, definidos na classe da fachada, passando nome de jogador */
			System.out.println("FOI AQUI?");
			this.setVisible(false);
			this.dispose();
			Facade.getFacadeInstance().criarJogadores(getNomeJogador(1), getNomeJogador(2));
			Facade.getFacadeInstance().posicionarArmasJogador(1);
			
		} else if(e.getSource() == btnCancel){
			
			/* finaliza o jogo */
			System.exit(1);
		}
	}
}
