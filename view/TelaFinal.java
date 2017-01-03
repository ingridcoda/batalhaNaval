package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import facade.Facade;

/* Classe de Visualização Final */
@SuppressWarnings("serial")
public class TelaFinal extends Frame implements ActionListener{
	private static TelaFinal instancia;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lblText;
	public static JButton btnQuitGame;

	/* Construtor da Classe de Visualização */
	private TelaFinal(String nomeVencedor){
		super();

		this.setBackground(Color.WHITE);

		lblTitle = new JLabel("BATALHA NAVAL");
		lblTitle.setBounds(160, -250, 800, 600);
		lblTitle.setFont(fonteTitulo);
		this.painel.setBounds(0, 0, 800, 600);
		this.painel.add(lblTitle);

		lblText = new JLabel("Parabéns, jogador "+nomeVencedor+"!!! Você venceu!!!");
		lblText.setBounds(280, -120, 800, 600);
		this.painel.add(lblText);

		btnQuitGame = new JButton("Fechar Jogo");
		btnQuitGame.setBounds(330, 250, 120, 40);
		this.painel.add(btnQuitGame);
		btnQuitGame.addActionListener(this);

		this.painel.setLayout(null);
		this.painel.setVisible(true);
		this.painel.setBackground(Color.WHITE);
		this.getContentPane().add(painel);

		this.setVisible(true);
	}

	/* Singleton */
	public static void getInstance(String nomeVencedor){
		if(instancia == null){
			instancia = new TelaFinal(nomeVencedor);
		}
	}

	/* Tratamento de Evento de Mouse */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* finaliza o jogo */
		System.exit(1);

	}

}


