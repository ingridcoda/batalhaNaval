package model;

import java.util.Observable;
import java.util.Observer;

/* Classe de Negócio para criação do tipo Jogador */
public class Jogador implements Observer{
	public String nome;
	public int numEmbarcacoes = 15;
	public Matriz[][] matriz;
	
	/* Construtor da Classe de Negócio */
	public Jogador(String nomeJogador){
		this.nome = nomeJogador;
		numEmbarcacoes = 15;
		this.matriz = Matriz.inicializaMatriz(matriz);				
	}
	
	/* Getters e Setters */
	public String getNome() {
		return nome;
	}

	/* Observer */
	@Override
	public void update(Observable o, Object obj) {
		matriz = (Matriz[][]) obj;		
	}
	
}