package view;

import java.awt.*;
import javax.swing.*;

/*Classe de Visualização Geral*/
@SuppressWarnings("serial")
public class Frame extends JFrame{
	protected JPanel painel = new JPanel();
	private static Frame instancia = null;
	
	/*Construtor da Classe de Visualização*/
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
	
	/*Singleton*/
	public static synchronized Frame getInstance(){
		if(instancia == null){
			instancia = new Frame();
		} 
		return instancia;
	}
}
