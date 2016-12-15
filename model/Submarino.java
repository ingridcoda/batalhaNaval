package model;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import controller.*;

@SuppressWarnings("serial")
public class Submarino extends Navio implements ActionListener, MouseListener{
	private int tipo = 1;
	private Color cor;
	private boolean isRotate = false;
	private boolean isSelected = false;
	
	public Submarino(Color cor, int x, int y){
		super(x, y, 1);
		this.cor = cor;
		this.addMouseListener(this);
		this.setBackground(Color.WHITE);
		this.setSize(20, 20);
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
		/*passar para controller depois*/
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {	
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
