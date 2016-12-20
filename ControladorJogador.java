package controller;

import model.Jogador;
import view.TelaJogadores;

public class ControladorJogador {

	public static Jogador jogadorVez;
	
	public void alternaJogadores(){
		if(jogadorVez.getNome() == TelaJogadores.getNomeJogador1()){
			//jogadorVez.setNome(TelaJogadores.getNomeJogador2());
		} else {
			//jogadorVez.setNome(TelaJogadores.getNomeJogador1());
		}
	}
	
}
