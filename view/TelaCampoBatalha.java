package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import controller.ControladorMatriz;
import controller.ControladorSalvamento;
import model.Jogador;
import model.Matriz;

/*Classe de Visualiza磯 da Fase de Ataques*/
@SuppressWarnings("serial")
public class TelaCampoBatalha extends Frame implements ActionListener, Observer{
	private static TelaCampoBatalha instancia = null;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	public static JButton btnSave;
	public static Jogador jogVez;
	public static Jogador jogOponente;
	public static Tabuleiro t1, t2;
	
	/*Construtor da Classe de Visualização*/
	public TelaCampoBatalha(Jogador jogVez, Jogador jogOponente){	
		super();
		
		TelaCampoBatalha.jogVez = jogVez;
		TelaCampoBatalha.jogOponente = jogOponente;
		this.setBackground(Color.WHITE);
		this.painel.setBounds(0, 0, 800, 600);
		
		t1 = new Tabuleiro(TelaCampoBatalha.jogVez, 30, 150);
		this.add(t1);
		t1.setEnabled(false);
		t2 = new Tabuleiro(TelaCampoBatalha.jogOponente, 450, 150);
		this.add(t2);
		t2.setEnabled(true);

		lblTitle = new JLabel("BATALHA NAVAL");
		lblTitle.setBounds(160, -250, 800, 600);
		lblTitle.setFont(fonteTitulo);
		this.painel.add(lblTitle);
		
		lbl1 = new JLabel(TelaCampoBatalha.jogVez.nome);
		lbl1.setBounds(160, 60, 100, 140);
		this.painel.add(lbl1);
		
		lbl2 = new JLabel(TelaCampoBatalha.jogOponente.nome);
		lbl2.setBounds(580, 60, 100, 140);
		this.painel.add(lbl2);
		
		lbl3 = new JLabel("Vez do Jogador "+jogVez.nome);
		lbl3.setBounds(310, 500, 200, 100);
		this.painel.add(lbl3);
		
		btnSave = new JButton("Salvar Jogo");
		btnSave.setBounds(345, 500, 110, 30);
		this.painel.add(btnSave);
		
		this.painel.setLayout(null);
		this.painel.setVisible(true);
		this.getContentPane().add(painel);	
		this.setLayout(null);
		this.setVisible(true);	
		
	}
	
	/*Singleton*/
	public static synchronized TelaCampoBatalha getInstance(Jogador j1, Jogador j2){
		if(instancia == null){
			instancia = new TelaCampoBatalha(j1, j2);
		} 
		return instancia;
	}
	
	/*Alternar Vez de Jogadores*/
	public void alternaJogadorVez(){
		Jogador aux = jogVez;
		jogVez = jogOponente;
		jogOponente = aux;
		repaint();
	}
	
	/*Alternar Tabuleiros*/
	public void alternaTabuleiros(){
		if(t1.isEnabled() && t1.getMatrizControle() == jogOponente.matriz){
			t1.setEnabled(false);
			t2.setEnabled(true);
		} else if (t2.isEnabled() && t2.getMatrizControle() == jogOponente.matriz){
			t2.setEnabled(false);
			t1.setEnabled(true);
		}
		alternaJogadorVez();
		repaint();
	}
	
	/*Tratamento de Evento de Bot�o*/
	@Override
	public void actionPerformed(ActionEvent e) {
		new ControladorSalvamento(this).actionPerformed(e);
	}

	/*Observer*/
	@Override
	public void update(Observable o, Object arg) {
		if(o.hasChanged()){
			 TelaCampoBatalha.jogOponente.matriz = (Matriz[][]) arg;
			 this.repaint();
		}		
		this.repaint();
	}

	
	
}