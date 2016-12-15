package controller;

import java.awt.event.*;
import view.Navio;

public class ControladorNavio implements MouseListener{
	public static Navio navioClicado;

	
	public static Navio verificaUltimoSelecionado(MouseEvent mouse){
		return (Navio) mouse.getSource();
	}

	@Override
	public void mouseClicked(MouseEvent mouse) {
		int x = mouse.getX();
        int y = mouse.getY();
        System.out.println("Navio x: "+ x +" Navio y: "+ y); 
        navioClicado = verificaUltimoSelecionado(mouse);
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		// TODO Auto-generated method stub
		
	}
	
	
}
