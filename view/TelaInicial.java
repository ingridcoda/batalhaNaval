package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import facade.Facade;
import model.Jogador;
import model.Matriz;

public class TelaInicial extends Frame implements ActionListener{
	private static TelaInicial instancia = null;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lblText;
	public static JButton btnNewGame;
	public static JButton btnLoadGame;
	
	/*Construtor da Classe de Visualização*/
	public TelaInicial(){
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

	/*Singleton*/
	public static synchronized TelaInicial getInstance(){
		if(instancia == null){
			instancia = new TelaInicial();
		} 
		return instancia;
	}
	
	/*Tratamento de Evento de Botão*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewGame){
			this.setVisible(false);
			this.dispose();
			Facade.novoJogo();
		} else if (e.getSource() == btnLoadGame){
			this.setVisible(false);
			this.dispose();
			Jogador j1 = new Jogador("");
			Jogador j2 = new Jogador("");
			Facade.carregarJogo(TelaCampoBatalha.getInstance(j1, j2));
		} else {
			System.exit(1);
		}
		
	}
}
