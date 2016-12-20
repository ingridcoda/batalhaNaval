package main;

import facade.Facade;
import view.TelaInicial;

/* Classe Principal para iniciar jogo */
public class Main {

	public static void main(String[] args) {

		/* inicializa a fachada */
		Facade.inicializaFacade();

		/* instancia a tela inicial */
		TelaInicial.getInstance();

	}

}
