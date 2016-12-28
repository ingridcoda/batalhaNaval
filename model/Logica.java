package model;

import java.util.*;

import facade.Facade;
import view.*;

@SuppressWarnings("unused")
/* Classe de Neg�cio para L�gica do Jogo */
public class Logica extends Observable{
	private static Matriz[][] matrizControleJogadorVez;
	private static int i, j;	
	private boolean afundou = false;
	private boolean acertou = false;
	public boolean posicionou = false;
	public boolean terminou = false;
	public ArrayList<Observer> listaObserver = new ArrayList<Observer>();
	public static String nomeVencedor;

	/* Construtor da Classe de Neg�cio */
	public Logica(){
		Logica.i = 0;
		Logica.j = 0;
		matrizControleJogadorVez = new Matriz[15][15];
		matrizControleJogadorVez = Matriz.inicializaMatriz(matrizControleJogadorVez);
	}

	/* Observer */
	public void notifyObservers(Object obj){
		if(this.hasChanged()){
			for(Observer obs: listaObserver){
				obs.update(this, obj);
			}
		}
	}

	/* Posicionamento de Embarca��o */
	public void posicionarArma(Navio navio, Matriz[][] matrizControleJogadorVez, int coordX, int coordY){
		/* atribui � vari�vel local de matriz a matriz fornecida como par�metro */
		Logica.matrizControleJogadorVez = matrizControleJogadorVez;

		/* chama m�todo de verifica��o de posi��o da matriz a qual as coordenadas x e y correspondem */
		verificaPosMatriz(Logica.matrizControleJogadorVez, coordX, coordY);

		/*apenas controle meu*/
		System.out.println("Inx i no pos: "+i);
		System.out.println("Inx j no pos: "+j);


		/* verifica se � submarino */
		if(navio.getTipo() == 1 && verificaValidadePosicionamentoSubmarino(i,j) == true){	

			/* atribui � posi��o da matriz o tipo da embarca��o e notifica Observers */
			Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
			this.setChanged();
			navio.isPositioned = true;
			System.out.println("Posicionou submarino");
			notifyObservers(Logica.matrizControleJogadorVez);

			/* verifica se � destroyer */
		} else if (navio.getTipo() == 2 && verificaValidadePosicionamentoDestroyer(i, j, navio.isRotate) == true){

			/* verifica se n�o est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			if(navio.isRotate == false){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou destroyer n�o rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

				/* verifica se est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			} else if(navio.isRotate == true){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou destroyer rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);
			}

			/* verifica se � hidroavi�o */
		} else if(navio.getTipo() == 3 && verificaValidadePosicionamentoHidroaviao(i, j, navio.isRotate) == true){

			/* verifica se n�o est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			if(navio.isRotate == false){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+1][j-1].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou hidroavi�o n�o rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

				/* verifica se est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			} else if(navio.isRotate == true){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+1][j+1].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou hidroavi�o rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

			}

			/* verifica se � cruzador */
		} else if(navio.getTipo() == 4 && verificaValidadePosicionamentoCruzador(i, j, navio.isRotate) == true){

			/* verifica se n�o est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			if(navio.isRotate == false){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+3][j].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou cruzador n�o rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

				/* verifica se est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			} else if(navio.isRotate == true){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+3].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou cruzador rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

			}

			/* se entrar aqui, � coura�ado */
		} else if(navio.getTipo() == 5 && verificaValidadePosicionamentoCouracado(i, j, navio.isRotate) == true){

			/* verifica se n�o est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			if(navio.isRotate == false){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+3][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i+4][j].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou coura�ado n�o rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

				/* verifica se est� rotacionado e se todas as casas a serem ocupadas s�o v�lidas */
			} else if(navio.isRotate == true){

				/* atribui �s posi��es da matriz o tipo da embarca��o e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+3].tipoArma = navio.getTipo();
				Logica.matrizControleJogadorVez[i][j+4].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou coura�ado rotacionado");
				notifyObservers(Logica.matrizControleJogadorVez);

			}

		}

		/* atribui zero �s vari�veis de posicionamento i e j */
		i = 0;
		j = 0;
		
		for(int i = 0; i < 15; i++){
			for (int j = 0; j < 15; j++){
				System.out.print(matrizControleJogadorVez[j][i].tipoArma+ " ");
			}
			System.out.println("");
		}
		

	}

	/* Verifica��o de Posicionamento dentro da Matriz ----> M�todo escrito pelo Lucas Debatin */
	private void verificaPosMatriz(Matriz[][] matrizControleJogadorVez, int coordX, int coordY){

		/* percorre tabuleiro horizontalmente*/
		for(int x = 10; x < 315; x += 20 ){

			/* se o valor de x � menor que a coordenada x clicada */
			if(x < coordX){

				/* incremento minha vari�vel de posicionamento i em um */
				i++;

				/* sen�o, encontrei meu i */	
			} else {
				/* saio do looping */
				break;

			}

		}

		/* percorre tabuleiro verticalmente*/
		for(int y = 10; y < 315; y += 20){

			/* se o valor de y � menor que a coordenada y clicada */
			if(y < coordY){

				/* incremento minha vari�vel de posicionamento j em um */
				j++;

				/* sen�o, encontrei meu j */		
			} else {
				/* saio do looping */
				break;

			}

		}

		/* decremento 1 em i e j para posicionar no local correto */
		i-=1;
		j-=1;

		/* apenas controle meu */
		System.out.println("Valor de i result: "+i);
		System.out.println("Valor de j result: "+j);

	}	
	
	/* Verifica��o de posi��o v�lida */
	private boolean verificaValidadePosicionamentoSubmarino(int i, int j){  ////////OK!!!! TESTA TODAS AS BORDAS DO SUBMARINO E SEU PRÓPRIO POSICIONAMENTO

		/* atribui os valores das minhas vari�veis globais de posicionamento i 
		 * e j para as vari�veis locais de posicionamento i e j por seguran�a */
		i = Logica.j;
		j = Logica.i;

		/*verifica se i e j est�o dentro do limite da matriz */
		if(i > 0 && i < 14 && j > 0 && j < 14){

			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			}
		}
		/* sen�o, retorna false */
		return false;	

	}
		
	/* Verifica��o de posi��o v�lida */
	private boolean verificaValidadePosicionamentoDestroyer(int i, int j, boolean rotacionado){  

		/* atribui os valores das minhas vari�veis globais de posicionamento i 
		 * e j para as vari�veis locais de posicionamento i e j por seguran�a */
		i = Logica.j;
		j = Logica.i;

		/*verifica se i e j est�o dentro do limite da matriz */
		if(i > 0 && i < 14 && j > 0 && j < 14 && i+1 < 15 && (rotacionado == false)){

			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+2][j-1].tipoArma + Logica.matrizControleJogadorVez[i+2][j].tipoArma + Logica.matrizControleJogadorVez[i+2][j+1].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		} else if (i > 0 && i < 14 && j > 0 && j < 14 && j+1 < 15 && (rotacionado == true)) {
			
			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+2].tipoArma + Logica.matrizControleJogadorVez[i][j+2].tipoArma + Logica.matrizControleJogadorVez[i+1][j+2].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		}
		/* sen�o, retorna false */
		return false;	

	}
		
	/* Verifica��o de posi��o v�lida */
	private boolean verificaValidadePosicionamentoHidroaviao(int i, int j, boolean rotacionado){  

		/* atribui os valores das minhas vari�veis globais de posicionamento i 
		 * e j para as vari�veis locais de posicionamento i e j por seguran�a */
		i = Logica.j;
		j = Logica.i;

		/*verifica se i e j est�o dentro do limite da matriz */
		if(i > 0 && i < 14 && j > 0 && j < 14 && i+2 < 15 && j-1 < 15 && j-1 > 0 && (rotacionado == false)){

			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+2][j-1].tipoArma + Logica.matrizControleJogadorVez[i+2][j].tipoArma + Logica.matrizControleJogadorVez[i+2][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+3][j-1].tipoArma + Logica.matrizControleJogadorVez[i+3][j].tipoArma + Logica.matrizControleJogadorVez[i+3][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i][j-2].tipoArma + Logica.matrizControleJogadorVez[i+1][j-2].tipoArma + Logica.matrizControleJogadorVez[i+2][j-2].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		} else if (i > 0 && i < 14 && j > 0 && j < 14 && i+1 < 15 && j+2 < 15 && (rotacionado == true)) {
			
			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+2].tipoArma + Logica.matrizControleJogadorVez[i][j+2].tipoArma + Logica.matrizControleJogadorVez[i+1][j+2].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+3].tipoArma + Logica.matrizControleJogadorVez[i][j+3].tipoArma + Logica.matrizControleJogadorVez[i+1][j+3].tipoArma + 
			   Logica.matrizControleJogadorVez[i+2][j].tipoArma + Logica.matrizControleJogadorVez[i+2][j+1].tipoArma + Logica.matrizControleJogadorVez[i+2][j+2].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		}
		/* sen�o, retorna false */
		return false;	

	}
	
	/* Verifica��o de posi��o v�lida */
	private boolean verificaValidadePosicionamentoCruzador(int i, int j, boolean rotacionado){  

		/* atribui os valores das minhas vari�veis globais de posicionamento i 
		 * e j para as vari�veis locais de posicionamento i e j por seguran�a */
		i = Logica.j;
		j = Logica.i;

		/*verifica se i e j est�o dentro do limite da matriz */
		if(i > 0 && i < 14 && j > 0 && j < 14 && i+3 < 15 && (rotacionado == false)){

			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+2][j-1].tipoArma + Logica.matrizControleJogadorVez[i+2][j].tipoArma + Logica.matrizControleJogadorVez[i+2][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+3][j-1].tipoArma + Logica.matrizControleJogadorVez[i+3][j].tipoArma + Logica.matrizControleJogadorVez[i+3][j+1].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		} else if (i > 0 && i < 14 && j > 0 && j < 14 && j+3 < 15 && (rotacionado == true)) {
			
			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+2].tipoArma + Logica.matrizControleJogadorVez[i][j+2].tipoArma + Logica.matrizControleJogadorVez[i+1][j+2].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+3].tipoArma + Logica.matrizControleJogadorVez[i][j+3].tipoArma + Logica.matrizControleJogadorVez[i+1][j+3].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		}
		/* sen�o, retorna false */
		return false;	

	}	
	
	/* Verifica��o de posi��o v�lida */
	private boolean verificaValidadePosicionamentoCouracado(int i, int j, boolean rotacionado){  

		/* atribui os valores das minhas vari�veis globais de posicionamento i 
		 * e j para as vari�veis locais de posicionamento i e j por seguran�a */
		i = Logica.j;
		j = Logica.i;

		/*verifica se i e j est�o dentro do limite da matriz */
		if(i > 0 && i < 14 && j > 0 && j < 14 && i+4 < 15 && (rotacionado == false)){

			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+2][j-1].tipoArma + Logica.matrizControleJogadorVez[i+2][j].tipoArma + Logica.matrizControleJogadorVez[i+2][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+3][j-1].tipoArma + Logica.matrizControleJogadorVez[i+3][j].tipoArma + Logica.matrizControleJogadorVez[i+3][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i+4][j-1].tipoArma + Logica.matrizControleJogadorVez[i+4][j].tipoArma + Logica.matrizControleJogadorVez[i+4][j+1].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		} else if (i > 0 && i < 14 && j > 0 && j < 14 && j+4 < 15 && (rotacionado == true)) {
			
			/* verifica se arredores est�o vazios, se sim, mant�m i e j como v�lidos e retorna true */
			if(Logica.matrizControleJogadorVez[i][j].tipoArma + Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + Logica.matrizControleJogadorVez[i][j+1].tipoArma + 
			   Logica.matrizControleJogadorVez[i+1][j-1].tipoArma + Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+2].tipoArma + Logica.matrizControleJogadorVez[i][j+2].tipoArma + Logica.matrizControleJogadorVez[i+1][j+2].tipoArma +
			   Logica.matrizControleJogadorVez[i-1][j+3].tipoArma + Logica.matrizControleJogadorVez[i][j+3].tipoArma + Logica.matrizControleJogadorVez[i+1][j+3].tipoArma + 
			   Logica.matrizControleJogadorVez[i-1][j+4].tipoArma + Logica.matrizControleJogadorVez[i][j+4].tipoArma + Logica.matrizControleJogadorVez[i+1][j+4].tipoArma == 0){

				i = Logica.j;
				j = Logica.i;
				return true;
			} 
			
		}
		/* sen�o, retorna false */
		return false;	

	}
	
	/* Verifica��o de Redondezas para tratar afundamento de arma */
	private void verificaArredoresDestroyer(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um destroyer */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 2 || matrizControleJogadorVez[i][j-1].tipoArma == 2 
		|| matrizControleJogadorVez[i+1][j].tipoArma == 2 || matrizControleJogadorVez[i-1][j].tipoArma == 2){

			/* marca como acerto apenas, isto �, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a vari�vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	

	}


	private void verificaArredoresHidroaviao(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um hidroavi�o */
		if(matrizControleJogadorVez[i-1][j+1].tipoArma == 3 || matrizControleJogadorVez[i][j+2].tipoArma == 3
		|| matrizControleJogadorVez[i+1][j-1].tipoArma == 3 || matrizControleJogadorVez[i+1][j+1].tipoArma == 3
		|| matrizControleJogadorVez[i-1][j-1].tipoArma == 3 || matrizControleJogadorVez[i][j-2].tipoArma == 3
		|| matrizControleJogadorVez[i+1][j+1].tipoArma == 3 || matrizControleJogadorVez[i+2][j].tipoArma == 3
		|| matrizControleJogadorVez[i-2][j].tipoArma == 3){

			/* marca como acerto apenas, isto �, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a vari�vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;
		}	
	}


	private void verificaArredoresCruzador(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um cruzador */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 4 || matrizControleJogadorVez[i][j+2].tipoArma == 4 || matrizControleJogadorVez[i][j+3].tipoArma == 4 
		|| matrizControleJogadorVez[i][j-1].tipoArma == 4 || matrizControleJogadorVez[i][j-2].tipoArma == 4 || matrizControleJogadorVez[i][j-3].tipoArma == 4
		|| matrizControleJogadorVez[i-1][j].tipoArma == 4 || matrizControleJogadorVez[i-2][j].tipoArma == 4 || matrizControleJogadorVez[i-3][j].tipoArma == 4
		|| matrizControleJogadorVez[i+1][j].tipoArma == 4 || matrizControleJogadorVez[i+2][j].tipoArma == 4 || matrizControleJogadorVez[i+3][j].tipoArma == 4){

			/* marca como acerto apenas, isto �, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a vari�vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	
	}

	private void verificaArredoresCouracado(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um coura�ado */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 5 || matrizControleJogadorVez[i][j+2].tipoArma == 5 || matrizControleJogadorVez[i][j+3].tipoArma == 5 || matrizControleJogadorVez[i][j+4].tipoArma == 5 
		|| matrizControleJogadorVez[i][j-1].tipoArma == 5 || matrizControleJogadorVez[i][j-2].tipoArma == 5 || matrizControleJogadorVez[i][j-3].tipoArma == 5 || matrizControleJogadorVez[i][j-4].tipoArma == 5
		|| matrizControleJogadorVez[i-1][j].tipoArma == 5 || matrizControleJogadorVez[i-2][j].tipoArma == 5 || matrizControleJogadorVez[i-3][j].tipoArma == 5 || matrizControleJogadorVez[i-4][j].tipoArma == 5
		|| matrizControleJogadorVez[i+1][j].tipoArma == 5 || matrizControleJogadorVez[i+2][j].tipoArma == 5 || matrizControleJogadorVez[i+3][j].tipoArma == 5 || matrizControleJogadorVez[i+4][j].tipoArma == 5){

			/* marca como acerto apenas, isto �, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a vari�vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	

	}

	/*Verifica��o de tipo de arma*/
	private int verificaArma(Matriz[][] matrizControleJogadorVez, int coordX, int coordY){

		/* chama m�todo de verifica��o de posicionamento das coordenadas x e y na matriz */
		verificaPosMatriz(matrizControleJogadorVez, coordX, coordY);

		/* verifica se h� algum tipo da arma na posic�o i,j da matriz e retorna o tipo */
		if(matrizControleJogadorVez[i][j].tipoArma == 1){
			return 1;
		} else if(matrizControleJogadorVez[i][j].tipoArma == 2){
			return 2;
		} else if(matrizControleJogadorVez[i][j].tipoArma == 3){
			return 3;
		} else if(matrizControleJogadorVez[i][j].tipoArma == 4){
			return 4;
		} else if(matrizControleJogadorVez[i][j].tipoArma == 5){
			return 5;
		}

		/* se n�o houver arma no local, retorna zero */
		return 0;
	}

	/*L�gica de Ataque*/
	public boolean atirar(Matriz[][] matriz, int coordX, int coordY){
		int tipoArma;
		Logica.matrizControleJogadorVez = matriz;

		/* chama m�todo de verifica��o de tipo de embarca��o posicionada nas 
		 * coordenadas x e y da matriz e atribui retorno � vari�vel tipoArma */
		tipoArma = verificaArma(Logica.matrizControleJogadorVez, coordX, coordY);

		/* verifica se existe alguma embarca��o no local */
		if(tipoArma != 0){

			/* verifica se � um submarino */
			if(tipoArma == 1){

				/*marca como clicado e afundado e chama m�todo para alterar status de acerto */
				afundou = true;				
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;

				/* verifica se � um destroyer */	
			} else if(tipoArma == 2){

				/*marca como clicado e acertado e chama m�todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresDestroyer(Logica.matrizControleJogadorVez);

				/* verifica se � um hidroavi�o */		
			} else if(tipoArma == 3){

				/*marca como clicado e acertado e chama m�todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresHidroaviao(Logica.matrizControleJogadorVez);

				/* verifica se � um cruzador */	
			} else if(tipoArma == 4){

				/*marca como clicado e acertado e chama m�todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresCruzador(Logica.matrizControleJogadorVez);

				/* verifica se � um coura�ado */		
			} else if(tipoArma == 5){

				/*marca como clicado e acertado e chama m�todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresCouracado(Logica.matrizControleJogadorVez);

			} 

		}

		/* notifico Observers e retorno valor da vari�vel acertou */
		this.setChanged();
		notifyObservers(Logica.matrizControleJogadorVez);
		return acertou;

	}

	/* Verifica��o de Status de Acerto */
	public void setStatus(int x, int y){    ///////////////////////////// ACHO QUE EST� SENDO "IN�TIL E REDUNDANTE"... REVER!

		/* verifica se acertou */
		if(acertou){

			/* marco como clicado e acertado e notifico Observers */
			Logica.matrizControleJogadorVez[i][j].tipoArma = 0;
			Logica.matrizControleJogadorVez[i][j].statusAcerto = true;	
			Logica.matrizControleJogadorVez[i][j].foiClicado = true;
			this.setChanged();
			notifyObservers(Logica.matrizControleJogadorVez);

		}

	}

	/* Verifica��o de Final de Jogo */
	public boolean verificaFinalJogo(){

		/* verifica se o numero de embarca��es do primeiro jogador � zero */
		if(Facade.j1.numEmbarcacoes == 0){

			/* atribuo o nome do segundo jogador � vari�vel nomeJogador e vari�vel terminou recebe true */
			nomeVencedor = TelaJogadores.getNomeJogador2();
			terminou = true;

			/* verifica se o numero de embarca��es do segundo jogador � zero */	
		} else if(Facade.j2.numEmbarcacoes == 0){

			/* atribuo o nome do primeiro jogador � vari�vel nomeJogador e vari�vel terminou recebe true */
			nomeVencedor = TelaJogadores.getNomeJogador1();
			terminou = true;

		}	

		/* retorna valor da vari�vel terminou */
		return terminou;
	}

}
