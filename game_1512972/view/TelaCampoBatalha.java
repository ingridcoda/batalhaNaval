package view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import controller.ControladorSalvamento;
import model.Jogador;
import model.Matriz;

/* Classe de Visualiza��o da Fase de Ataques */
@SuppressWarnings("serial")
public class TelaCampoBatalha extends Frame implements ActionListener, Observer{
	private static TelaCampoBatalha instancia = null;
	private Font fonteTitulo = new Font("Monospaced", Font.BOLD, 60);
	private JLabel lblTitle;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	public static JButton btnSave;
	public static Jogador jogVez, jogOponente;
	public static Tabuleiro t1, t2;
	private JPanel painel;

	/* Construtor da Classe de Visualiza��o */
	private TelaCampoBatalha(Jogador jogVez, Jogador jogOponente){	
		super();
		this.painel = new JPanel();

		TelaCampoBatalha.jogVez = jogVez;
		TelaCampoBatalha.jogOponente = jogOponente;
		
		
		//DEBUG JOGADORES REFERENCIADOS
		System.out.println("TELACAMPOBATALHA:");
		System.out.println(jogVez.getNome());
		for(int x = 0; x < 15; x++){
			for (int y = 0; y < 15; y++){
				System.out.print(jogVez.matriz[x][y].tipoArma + " ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println(jogOponente.getNome());
		for(int x = 0; x < 15; x++){
			for (int y = 0; y < 15; y++){
				System.out.print(jogOponente.matriz[x][y].tipoArma + " ");
			}
			System.out.println("");
		}
		//FIM DEBUG
		
		
		
		this.setBackground(Color.WHITE);
		this.painel.setBackground(Color.WHITE);
		this.painel.setBounds(0, 0, 800, 600);

		t1 = new Tabuleiro(1, 30, 150, 'a');
		t2 = new Tabuleiro(2, 450, 150, 'a');

		t1.setEnabled(false); //come�a como desabilitado
		this.add(t1);
		t2.setEnabled(true); //come�a como habilitado
		this.add(t2);
		
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
		btnSave.addActionListener(this);
		this.painel.add(btnSave);

		this.painel.setLayout(null);
		this.painel.setVisible(true);
		this.getContentPane().add(painel);	
		this.setLayout(null);
		this.setVisible(true);	
		
		System.out.println("TELACAMPOBATALHA INSTANCIADO!");
		System.out.println("TAB1 HABILITADO? "+t1.isEnabled());
		System.out.println("TAB2 HABILITADO? "+t2.isEnabled());
	}
	
	public static TelaCampoBatalha getInstance(Jogador j1, Jogador j2){
		if(instancia == null){
			instancia = new TelaCampoBatalha(j1, j2);
		} 
		return instancia;
	}
	
	public static boolean estaInstanciado(){
		if(instancia != null)
			return true;
		return false;
	}

	/* Alternar Vez de Jogadores */
	public void alternaJogadorVez(){

		/* atribui jogador da vez ao jogador oponente, atribui 
		 * jogador oponente ao jogador da vez e redesenha tela */
		lbl3.setText("Vez do Jogador "+jogOponente.nome);
		Jogador aux = jogVez;
		jogVez = jogOponente;
		jogOponente = aux;

	}

	/* Alternar Tabuleiros */
	public void alternaTabuleiros(){
		t2.setEnabled(t1.isEnabled());
		//t2.setVisible(t1.isVisible());

		t1.setEnabled(!t1.isEnabled());
		//t1.setVisible(!t1.isVisible());
		
		/*chama m�todo de alternar jogadores definido nesta classe e redesenha tela */
		alternaJogadorVez();
		
		repaint();
		
		System.out.println("TAB1 HABILITADO? "+t1.isEnabled());
		System.out.println("TAB2 HABILITADO? "+t2.isEnabled());

	}

	/* Tratamento de Evento de Bot�o */
	@Override
	public void actionPerformed(ActionEvent e) {

		/* chama m�todo de tratamento de evento de bot�o da classe de controle de salvamento,
		 * passando evento de clique de bot�o ocorrido na tela de campo de batalha*/
		new ControladorSalvamento(this).actionPerformed(e);

	}

	/* Observer */
	@Override
	public void update(Observable o, Object arg) {

		/* verifica se observado mudou em algo */
		if(o.hasChanged()){
			System.out.println("TELACAMPOBATALHA: O observador foi notificado de uma modifica��o!");
			/* matriz do jogador recebe matriz atualizada e tela � redesenhada */
			TelaCampoBatalha.jogOponente.matriz = (Matriz[][]) arg;
			
			/* redesenha elementos da tela atualizados */
			TelaCampoBatalha.t1.repaint();
			TelaCampoBatalha.t2.repaint();
			this.painel.repaint();
			this.repaint();	
			lbl3.repaint();
		}
	}


}