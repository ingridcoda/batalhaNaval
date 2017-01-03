package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;

import controller.ControladorNavio;

@SuppressWarnings("serial")
public class Navio extends JPanel implements MouseListener{
	private int tipo;
	private static boolean pendente;
	public boolean isSelected = false;
	public boolean isRotate = false;
	public boolean isPositioned = false;
	private int direcao = 1;
	private int width = 20;
	private int height = 20;
	private Color cor = Color.BLACK;

	public Navio(int x, int y, int tipo){
		this.tipo = tipo;
		this.setLocation(x, y);

		//addMouseListener(this); já foi feito na subclasse
	}

	public void setCor(Color cor) {
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
	
	public static void setSemPendencia(){
		pendente = false;
	}
	
	public int getDirecao(){
		return this.direcao;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if(tipo == 1){
			this.cor = Color.ORANGE;
		} else if(tipo == 2){
			this.cor = Color.YELLOW;
		} else if(tipo == 3){
			this.cor = Color.RED;
		} else if(tipo == 4){
			this.cor = Color.GREEN;
		} else if(tipo == 5){
			this.cor = Color.CYAN;
		}
		if(isPositioned == false){
			if(tipo != 3){
				this.width = setTam(tipo);
				if(isRotate == false){			
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
				
				if(direcao == 2){
					g2d.setColor(cor);
					//g2d.draw(new Rectangle2D.Double(0, 0, width, height));
					g2d.fill(new Rectangle2D.Double(20, 0, width+1, height+1));
					//g2d.draw(new Rectangle2D.Double(20, 20, width, height));
					g2d.fill(new Rectangle2D.Double(0, 20, width, height));
					//g2d.draw(new Rectangle2D.Double(0, 40, width, height));
					g2d.fill(new Rectangle2D.Double(20, 40, width, height));
				}
				else
				if(direcao == 3){
					g2d.setColor(cor);
					//g2d.draw(new Rectangle2D.Double(0, 20, width, height));
					g2d.fill(new Rectangle2D.Double(0, 0, width+1, height+1));
					//g2d.draw(new Rectangle2D.Double(20, 0, width, height));
					g2d.fill(new Rectangle2D.Double(40, 0, width, height));
					//g2d.draw(new Rectangle2D.Double(40, 20, width, height));
					g2d.fill(new Rectangle2D.Double(20, 20, width, height));
				}
				else
				if(direcao == 4){
					g2d.setColor(cor);
					//g2d.draw(new Rectangle2D.Double(0, 0, width, height));
					g2d.fill(new Rectangle2D.Double(0, 0, width+1, height+1));
					//g2d.draw(new Rectangle2D.Double(20, 20, width, height));
					g2d.fill(new Rectangle2D.Double(20, 20, width, height));
					//g2d.draw(new Rectangle2D.Double(0, 40, width, height));
					g2d.fill(new Rectangle2D.Double(0, 40, width, height));
				}
				else{
					g2d.setColor(cor);
					//g2d.draw(new Rectangle2D.Double(0, 20, width, height));
					g2d.fill(new Rectangle2D.Double(20, 0, width, height));
					//g2d.draw(new Rectangle2D.Double(20, 0, width, height));
					g2d.fill(new Rectangle2D.Double(0, 20, width, height));
					//g2d.draw(new Rectangle2D.Double(40, 20, width, height));
					g2d.fill(new Rectangle2D.Double(40, 20, width, height));
				}
			}
		}
	}

		@Override
		public void mouseClicked(MouseEvent mouse) {		

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent mouse) {
			System.out.println("Pendente? " + pendente);
			if(SwingUtilities.isLeftMouseButton(mouse)){
				if(!pendente){ //só seleciona se não tiver nenhum pendente
					this.isSelected = !isSelected;
					pendente = !pendente;
					ControladorNavio.navioClicado = this;
				}
				else
				if(isSelected){ //há pendentes, então só desseleciona se  
					this.isSelected = !isSelected;
					pendente = !pendente;
					ControladorNavio.navioClicado = null;
				}
			}
			
			if(SwingUtilities.isRightMouseButton(mouse) && isSelected){
				this.isRotate = !isRotate;
				if(tipo != 3){
					if(isRotate == true){
						this.setSize(height, width);
					} else {
						this.setSize(width, height);
					}
				} 
				else {
					if(direcao == 1){ //então vai pra 2
						direcao = 2;
						this.setSize(60, 60);
					}
					else
					if(direcao == 2){
						direcao = 3;
						this.setSize(60, 40);
					}
					else
					if(direcao == 3){
						direcao = 4;
						this.setSize(60, 60);
					}
					else{
						direcao = 1;
						this.setSize(60, 40);
					}
				}
				
				this.repaint();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {


		}

	}
