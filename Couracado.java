package view;

import java.awt.*;

@SuppressWarnings("serial")
public class Couracado extends Navio{
	private int tipo = 5;
	private Color cor;
	
	public Couracado(Color cor, int x, int y){
		super(x, y, 5);
		this.cor = cor;
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		this.setSize(100, 20);
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

