package model;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import controller.ControladorNavio;

public class Navio extends JPanel implements MouseListener{
	private int x;
	private int y;
	private int tipo;
	public boolean isSelected = false;
	public boolean isRotate = false;
	private int width = 20;
	private int height = 20;
	private Color cor = Color.BLACK;
	
	public Navio(int x, int y, int tipo){
		this.x = x;
		this.y = y;
		this.tipo = tipo;
		this.setLocation(x, y);
	}
	
	private void setCor(Color cor) {
		this.cor = cor;		
	}
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int setTam (int tipo){
		return 20*tipo;
	}
	
	public void desenharNavio(){
		switch (this.tipo){
		
		case 1: new Submarino(Color.BLACK, x, y);
				break;
		
		case 2: new Destroyer(Color.BLACK, x, y);
				break;		
				
		case 3: new Hidroaviao(Color.BLACK, x, y);
				break;	
			
		case 4: new Cruzador(Color.BLACK, x, y);
				break;	
				
		case 5: new Couracado(Color.BLACK, x, y);
				break;	
				
		}
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(tipo != 3){
			this.width = setTam(tipo);
			if(!isRotate){			
				g2d.draw(new Rectangle2D.Double(0, 0, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(0, 0, width, height));
			} else {
				g2d.draw(new Rectangle2D.Double(0, 0, height, width));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(0, 0, height, width));
			}
		} else if(tipo == 3){
			this.width = setTam(1);
			if(!isRotate){
				g2d.draw(new Rectangle2D.Double(0, 20, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(0, 20, width, height));
				g2d.draw(new Rectangle2D.Double(20, 0, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(20, 0, width, height));
				g2d.draw(new Rectangle2D.Double(40, 20, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(40, 20, width, height));
			} else {
				g2d.draw(new Rectangle2D.Double(0, 0, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(0, 0, width, height));
				g2d.draw(new Rectangle2D.Double(20, 20, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(20, 20, width, height));
				g2d.draw(new Rectangle2D.Double(0, 40, width, height));
				g2d.setColor(cor);
				g2d.fill(new Rectangle2D.Double(0, 40, width, height));
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
		new ControladorNavio();
		new ControladorNavio().mouseClicked(mouse);
		if(SwingUtilities.isLeftMouseButton(mouse)){
			this.isSelected = !isSelected;
			if(this.isSelected){
				this.setCor(Color.RED);
				this.repaint();
			} else {
				this.setCor(Color.BLACK);
				this.repaint();
			}
		}		
		if(SwingUtilities.isRightMouseButton(mouse) && isSelected){
			this.isRotate = !isRotate;
			if(tipo != 3){
				if(isRotate){
					this.setSize(height, width);
				} else {
					this.setSize(width, height);
				}
				this.repaint();
			} else {
				if(isRotate){
					this.setSize(60, 60);
				} else {
					this.setSize(60, 40);
				}
				this.repaint();
			}
		}
		
	}

	


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
