package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import facade.*;

/*Classe de Visualização da Fase de Criação de Jogadores*/
@SuppressWarnings("serial")
public class TelaJogadores extends Frame implements ActionListener{
	private static TelaJogadores instancia = null;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lblText;
	private JLabel lblNome1;
	private JLabel lblNome2;
	public static JTextField txtfNome1 = null;
	public static JTextField txtfNome2 = null;
	public static JButton btnStart;
	public static JButton btnCancel;
	
	/*Construtor da Classe de Visualização*/
	public TelaJogadores(){
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
	
	/*Singleton*/
	public static synchronized TelaJogadores getInstance(){
		if(instancia == null){
			instancia = new TelaJogadores();
		} 
		return instancia;
	}
	
	/*Getters e Setters*/
	public static String getNomeJogador1(){
		return txtfNome1.getText();
	}
	
	public static String getNomeJogador2(){
		return txtfNome2.getText();
	}
	
	public static String alteraNomeJogador(String nomeJogador){
		if(nomeJogador.equals(getNomeJogador1())){
			nomeJogador = getNomeJogador2();
		} else {
			nomeJogador = getNomeJogador1();	
		}
		return nomeJogador;
	}
	
	/*Tratamento de Evento de Botão*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart){	
			this.setVisible(false);
			this.dispose();
			Facade.criarJogadores(getNomeJogador1(), getNomeJogador2());
			Facade.posicionarArmasJogador1(Facade.j1.getNome());
		} else if(e.getSource() == btnCancel){
			System.exit(1);
		}
	}
}
