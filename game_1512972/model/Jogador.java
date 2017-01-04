package model;

import java.util.Observable;
import java.util.Observer;

/* Classe de Negocio para criacao do tipo Jogador */
public class Jogador implements Observer{
	public String nome;
	public int numEmbarcacoes = 38; //numero total de quadradinhos
	public Matriz[][] matriz;
	
	/* Construtor da Classe de Negocio */
	public Jogador(String nomeJogador){
		this.nome = nomeJogador;
		this.matriz = Matriz.criaMatrizInicializada();
	}
	
	/* Getters e Setters */
	public String getNome() {
		return nome;
	}
	
	public void setNumEmbarcacoes(int n){
		numEmbarcacoes = n;
	}

	/* Observer */
	@Override
	public void update(Observable o, Object obj) {
		matriz = (Matriz[][]) obj;		
	}
	
}