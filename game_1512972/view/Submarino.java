package view;
import java.awt.*;

@SuppressWarnings("serial")
public class Submarino extends Navio{
	private int tipo = 1;
	private Color cor;
	
	public Submarino(Color cor, int x, int y){
		super(x, y, 1);
		this.cor = cor;
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		this.setSize(20, 20);
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
