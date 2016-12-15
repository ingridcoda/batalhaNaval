package view;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Destroyer extends Navio implements ActionListener, MouseListener{
	private int tipo = 2;
	private Color cor;

	public Destroyer(Color cor, int x, int y){
		super(x, y, 2);
		this.cor = cor;
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		this.setSize(40, 20);
		//setFocusable(true);
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
	@Override
	public void mouseClicked(MouseEvent mouse) {
		super.mouseClicked(mouse);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
