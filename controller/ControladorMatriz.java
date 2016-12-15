package controller;

import java.awt.event.*;

import facade.Facade;
import model.Matriz;
import view.TelaEmbarcacoes;

/*Classe de Controle do tipo Matriz*/
public class ControladorMatriz implements MouseListener{
	private Matriz[][] matrizVez;
	
	/*Construtor da Classe de Controle*/
	public ControladorMatriz(Matriz[][] matrizControleJogadorVez, MouseEvent mouse){
		this.matrizVez = matrizControleJogadorVez;
		//mouseClicked(mouse);
	}
	
	/*Tratamentos de Eventos de Mouse*/
	@Override
	public void mouseClicked(MouseEvent mouse) {
		int x = mouse.getX();
        int y = mouse.getY();
        System.out.println("Tabuleiro x: "+ x +" Tabuleiro y: "+ y); 
		if(TelaEmbarcacoes.getInstance1().isVisible()){			
			Facade.escolherPosicaoArma(matrizVez, mouse.getX(), mouse.getY());
		} else if(TelaEmbarcacoes.getInstance2().isVisible()){
			Facade.escolherPosicaoArma(matrizVez, mouse.getX(), mouse.getY());
		} else {
			Facade.atacar(matrizVez, mouse.getX(), mouse.getY());
		}
		//mouse.getComponent().repaint();
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
}
