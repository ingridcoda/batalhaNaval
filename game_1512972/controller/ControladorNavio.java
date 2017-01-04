package controller;


import java.awt.event.*;

import view.Navio;

/* Classe de Controle do tipo Navio */
public class ControladorNavio {
	public static Navio navioClicado;

	/* Verificação de último navio clicado */
	public static Navio verificaUltimoSelecionado(MouseEvent mouse){
		return (Navio) mouse.getSource();
	}	
	
}
