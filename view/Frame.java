package view;

import java.awt.*;
import javax.swing.*;

/* Classe de Visualiza��o Geral */
@SuppressWarnings("serial")
public class Frame extends JFrame{
	protected JPanel painel = new JPanel();
	
	/* Construtor da Classe de Visualiza��o */
	public Frame(){	
		this.setSize(800, 600);
		setLayout(null);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Batalha Naval");		
		this.painel.setBackground(Color.WHITE);
		this.painel.setVisible(true);
		this.getContentPane().add(painel);
	}
}
