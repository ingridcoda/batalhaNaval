package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

import controller.*;
//import model.Matriz;
import model.Jogador;

@SuppressWarnings("serial")
public class Tabuleiro extends JPanel implements MouseListener{
	//private int numEmbarcacoesADistribuir;
	private Jogador jogador;
	public char tipoTela;   /////// tipo 'a' é ataque e tipo 'p' é posicionamento.

	public Tabuleiro(Jogador j, int x, int y, char tipoTela){
		jogador = j;

		this.setSize(315, 315);
		this.setBackground(Color.WHITE);
		this.setLocation(x, y);
		this.setEnabled(false);
		this.tipoTela = tipoTela;

		addMouseListener(this);
	}

	/* Desenhar tabuleiro */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int x, y, i, j;

		for(i=0, y=10; i<15; i++, y+=20){
			for(j=0, x=10; j<15; j++, x+=20){
				g2d.draw(new Rectangle2D.Double(x, y, 20, 20));	

				// trata caso de posiï¿½ï¿½o que jï¿½ foi acertada /
				if(jogador.matriz[i][j].getTipoArma() == -1){
					g2d.setColor(Color.RED);
					g2d.fill(new Rectangle2D.Double(x, y, 20, 20));
					g2d.setColor(Color.BLACK);

					/* trata caso de tela de posicionamento */	
				} else if(jogador.matriz[i][j].getTipoArma() > 0 && tipoTela == 'p'){
					g2d.setColor(Color.BLACK);
					g2d.fill(new Rectangle2D.Double(x, y, 20, 20));

					/* trata caso de clicar no mar */
				} else if(jogador.matriz[i][j].getTipoArma() == -2){
					g2d.setColor(Color.BLUE);
					g2d.fill(new Rectangle2D.Double(x, y, 20, 20));
					g2d.setColor(Color.BLACK);			

				}

			}

		}

		String[] dados = new String[15];
		dados[0] = "1";  dados[5] = "6";  dados[10] = "11";
		dados[1] = "2";  dados[6] = "7";  dados[11] = "12";
		dados[2] = "3";  dados[7] = "8";  dados[12] = "13";
		dados[3] = "4";  dados[8] = "9";  dados[13] = "14";
		dados[4] = "5";  dados[9] = "10"; dados[14] = "15";
		y = 9; 
		x = 18;

		for(i = 0; i < 15; i++){
			g2d.drawString(dados[i], x, y);
			if(i == 8){
				x += 15;
			} else {
				x += 20;
			}
		} 

		dados[0] = "A";  dados[5] = "F";  dados[10] = "K";
		dados[1] = "B";  dados[6] = "G";  dados[11] = "L";
		dados[2] = "C";  dados[7] = "H";  dados[12] = "M";
		dados[3] = "D";  dados[8] = "I";  dados[13] = "N";
		dados[4] = "E";  dados[9] = "J";  dados[14] = "O";
		x = 0; 
		y = 25;

		for(i = 0; i < 15; i++){
			g2d.drawString(dados[i], x, y);
			y += 20;
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
		
		if(this.isEnabled() == true){
			new ControladorMatriz(jogador.matriz, mouse).mousePressed(mouse);
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
