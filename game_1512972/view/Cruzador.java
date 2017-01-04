package view;

import java.awt.*;

@SuppressWarnings("serial")
public class Cruzador extends Navio{
	private int tipo = 4;
	private Color cor;
	
	public Cruzador(Color cor, int x, int y){
		super(x, y, 4);
		this.cor = cor;
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		this.setSize(80, 20);
		this.setVisible(true);
	}


	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
	}

}

