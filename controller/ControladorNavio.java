package controller;

import java.awt.event.*;
import view.Navio;

/* Classe de Controle do tipo Navio */
public class ControladorNavio implements MouseListener{
	public static Navio navioClicado;

	/* Verificação de último navio clicado */
	public static Navio verificaUltimoSelecionado(MouseEvent mouse){
		return (Navio) mouse.getSource();
	}

	/* Tratamentos de Eventos de Mouse */
	@Override
	public void mouseClicked(MouseEvent mouse) {
		
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
				
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		int x = mouse.getX();
        int y = mouse.getY();
        System.out.println("Navio x: "+ x +" Navio y: "+ y); 
        navioClicado = verificaUltimoSelecionado(mouse);
		
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
				
	}
	
	
}
