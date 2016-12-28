package model;

import java.util.*;

import facade.Facade;
import view.*;

@SuppressWarnings("unused")
/* Classe de Negócio para Lógica do Jogo */
public class Logica extends Observable{
	private static Matriz[][] matrizControleJogadorVez;
	private static int i, j;	
	private boolean afundou = false;
	private boolean acertou = false;
	public boolean posicionou = false;
	public boolean terminou = false;
	public ArrayList<Observer> listaObserver = new ArrayList<Observer>();
	public static String nomeVencedor;

	/* Construtor da Classe de Negócio */
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

	/* Posicionamento de Embarcação */
	public void posicionarArma(Navio navio, Matriz[][] matrizControleJogadorVez, int coordX, int coordY){
		/* atribui à variável local de matriz a matriz fornecida como parâmetro */
		Logica.matrizControleJogadorVez = matrizControleJogadorVez;

		/* chama método de verificação de posição da matriz a qual as coordenadas x e y correspondem */
		verificaPosMatriz(Logica.matrizControleJogadorVez, coordX, coordY);

		/*apenas controle meu*/
		System.out.println("Inx i no pos: "+i);
		System.out.println("Inx j no pos: "+j);

		/* chama método de verificação de posição válida */
		if(verificaValidadePosicionamento(i,j) == true){

			/* verifica se é submarino */
			if(navio.getTipo() == 1){	

				/* atribui à posição da matriz o tipo da embarcação e notifica Observers */
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				this.setChanged();
				navio.isPositioned = true;
				System.out.println("Posicionou submarino");
				notifyObservers(Logica.matrizControleJogadorVez);

				/* verifica se é destroyer */
			} else if (navio.getTipo() == 2){

				/* verifica se não está rotacionado e se todas as casas a serem ocupadas são válidas */
				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou destroyer não rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

					/* verifica se está rotacionado e se todas as casas a serem ocupadas são válidas */
				} else if(navio.isRotate == true && verificaValidadePosicionamento(i,j+1) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou destroyer rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);
				}

				/* verifica se é hidroavião */
			} else if(navio.getTipo() == 3){

				/* verifica se não está rotacionado e se todas as casas a serem ocupadas são válidas */
				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j-1) == true && verificaValidadePosicionamento(i+2,j) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j-1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou hidroavião não rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

					/* verifica se está rotacionado e se todas as casas a serem ocupadas são válidas */
				} else if(navio.isRotate == true && verificaValidadePosicionamento(i+1,j+1) == true && verificaValidadePosicionamento(i+2,j) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j+1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou hidroavião rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

				}

				/* verifica se é cruzador */
			} else if(navio.getTipo() == 4){

				/* verifica se não está rotacionado e se todas as casas a serem ocupadas são válidas */
				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j) == true && verificaValidadePosicionamento(i+2,j) == true && verificaValidadePosicionamento(i+3,j) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+3][j].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou cruzador não rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

					/* verifica se está rotacionado e se todas as casas a serem ocupadas são válidas */
				} else if(navio.isRotate == true && verificaValidadePosicionamento(i,j+1) == true && verificaValidadePosicionamento(i,j+2) == true && verificaValidadePosicionamento(i,j+3) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+3].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou cruzador rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

				}

				/* se entrar aqui, é couraçado */
			} else {

				/* verifica se não está rotacionado e se todas as casas a serem ocupadas são válidas */
				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j) == true && verificaValidadePosicionamento(i+2,j) == true && verificaValidadePosicionamento(i+3,j) == true && verificaValidadePosicionamento(i+4,j) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+3][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+4][j].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou couraçado não rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

					/* verifica se está rotacionado e se todas as casas a serem ocupadas são válidas */
				} else if(navio.isRotate == true && verificaValidadePosicionamento(i,j+1) == true && verificaValidadePosicionamento(i,j+2) == true && verificaValidadePosicionamento(i,j+3) == true && verificaValidadePosicionamento(i,j+4) == true){

					/* atribui às posições da matriz o tipo da embarcação e notifica Observers */
					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+3].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+4].tipoArma = navio.getTipo();
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou couraçado rotacionado");
					notifyObservers(Logica.matrizControleJogadorVez);

				}

			}

		} 

		/* atribui zero às variáveis de posicionamento i e j */
		i = 0;
		j = 0;
		
		for(int i = 0; i < 15; i++){
			for (int j = 0; j < 15; j++){
				System.out.print(matrizControleJogadorVez[j][i].tipoArma+ " ");
			}
			System.out.println("");
		}
		

	}

	/* Verificação de Posicionamento dentro da Matriz ----> Método escrito pelo Lucas Debatin */
	private void verificaPosMatriz(Matriz[][] matrizControleJogadorVez, int coordX, int coordY){

		/* percorre tabuleiro horizontalmente*/
		for(int x = 10; x < 315; x += 20 ){

			/* se o valor de x é menor que a coordenada x clicada */
			if(x < coordX){

				/* incremento minha variável de posicionamento i em um */
				i++;

				/* senão, encontrei meu i */	
			} else {
				/* saio do looping */
				break;

			}

		}

		/* percorre tabuleiro verticalmente*/
		for(int y = 10; y < 315; y += 20){

			/* se o valor de y é menor que a coordenada y clicada */
			if(y < coordY){

				/* incremento minha variável de posicionamento j em um */
				j++;

				/* senão, encontrei meu j */		
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

	/* Verificação de posição válida */
	private boolean verificaValidadePosicionamento(int i, int j){ ///////////REVER CONDIÇÕES... TA INCOMPLETO, MAS NÃO SEI POR QUÊ!!!

		/* atribui os valores das minhas variáveis globais de posicionamento i 
		 * e j para as variáveis locais de posicionamento i e j por segurança */
		i = Logica.j;
		j = Logica.i;

		/*verifica se i e j estão dentro do limite da matriz */
		if(i > 0 && i < 14 && j > 0 && j < 14){

			/* verifica se arredores estão vazios, se sim, mantém i e j como válidos e retorna true */
			if((Logica.matrizControleJogadorVez[i-1][j-1].tipoArma + Logica.matrizControleJogadorVez[i-1][j].tipoArma + 
				Logica.matrizControleJogadorVez[i-1][j+1].tipoArma + Logica.matrizControleJogadorVez[i][j-1].tipoArma + 	
				Logica.matrizControleJogadorVez[i][j+1].tipoArma + Logica.matrizControleJogadorVez[i+1][j-1].tipoArma +
				Logica.matrizControleJogadorVez[i+1][j].tipoArma + Logica.matrizControleJogadorVez[i+1][j+1].tipoArma == 0)
				&& (i+4 < 15 && j+4 < 15) && (i-1 >= 0 && j-1 >= 0)){

				i = Logica.j;
				j = Logica.i;
				return true;
			}
		}
		/* senão, retorna false */
		return false;	

	}

	/* Verificação de Redondezas para tratar afundamento de arma */
	private void verificaArredoresDestroyer(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um destroyer */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 2 || matrizControleJogadorVez[i][j-1].tipoArma == 2 
		|| matrizControleJogadorVez[i+1][j].tipoArma == 2 || matrizControleJogadorVez[i-1][j].tipoArma == 2){

			/* marca como acerto apenas, isto é, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variável afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	

	}


	private void verificaArredoresHidroaviao(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um hidroavião */
		if(matrizControleJogadorVez[i-1][j+1].tipoArma == 3 || matrizControleJogadorVez[i][j+2].tipoArma == 3
		|| matrizControleJogadorVez[i+1][j-1].tipoArma == 3 || matrizControleJogadorVez[i+1][j+1].tipoArma == 3
		|| matrizControleJogadorVez[i-1][j-1].tipoArma == 3 || matrizControleJogadorVez[i][j-2].tipoArma == 3
		|| matrizControleJogadorVez[i+1][j+1].tipoArma == 3 || matrizControleJogadorVez[i+2][j].tipoArma == 3
		|| matrizControleJogadorVez[i-2][j].tipoArma == 3){

			/* marca como acerto apenas, isto é, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variável afundou recebe true */
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

			/* marca como acerto apenas, isto é, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variável afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	
	}

	private void verificaArredoresCouracado(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um couraçado */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 5 || matrizControleJogadorVez[i][j+2].tipoArma == 5 || matrizControleJogadorVez[i][j+3].tipoArma == 5 || matrizControleJogadorVez[i][j+4].tipoArma == 5 
		|| matrizControleJogadorVez[i][j-1].tipoArma == 5 || matrizControleJogadorVez[i][j-2].tipoArma == 5 || matrizControleJogadorVez[i][j-3].tipoArma == 5 || matrizControleJogadorVez[i][j-4].tipoArma == 5
		|| matrizControleJogadorVez[i-1][j].tipoArma == 5 || matrizControleJogadorVez[i-2][j].tipoArma == 5 || matrizControleJogadorVez[i-3][j].tipoArma == 5 || matrizControleJogadorVez[i-4][j].tipoArma == 5
		|| matrizControleJogadorVez[i+1][j].tipoArma == 5 || matrizControleJogadorVez[i+2][j].tipoArma == 5 || matrizControleJogadorVez[i+3][j].tipoArma == 5 || matrizControleJogadorVez[i+4][j].tipoArma == 5){

			/* marca como acerto apenas, isto é, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variável afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	

	}

	/*Verificação de tipo de arma*/
	private int verificaArma(Matriz[][] matrizControleJogadorVez, int coordX, int coordY){

		/* chama método de verificação de posicionamento das coordenadas x e y na matriz */
		verificaPosMatriz(matrizControleJogadorVez, coordX, coordY);

		/* verifica se há algum tipo da arma na posicão i,j da matriz e retorna o tipo */
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

		/* se não houver arma no local, retorna zero */
		return 0;
	}

	/*Lógica de Ataque*/
	public boolean atirar(Matriz[][] matriz, int coordX, int coordY){
		int tipoArma;
		Logica.matrizControleJogadorVez = matriz;

		/* chama método de verificação de tipo de embarcação posicionada nas 
		 * coordenadas x e y da matriz e atribui retorno à variàvel tipoArma */
		tipoArma = verificaArma(Logica.matrizControleJogadorVez, coordX, coordY);

		/* verifica se existe alguma embarcação no local */
		if(tipoArma != 0){

			/* verifica se é um submarino */
			if(tipoArma == 1){

				/*marca como clicado e afundado e chama método para alterar status de acerto */
				afundou = true;				
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;

				/* verifica se é um destroyer */	
			} else if(tipoArma == 2){

				/*marca como clicado e acertado e chama método para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresDestroyer(Logica.matrizControleJogadorVez);

				/* verifica se é um hidroavião */		
			} else if(tipoArma == 3){

				/*marca como clicado e acertado e chama método para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresHidroaviao(Logica.matrizControleJogadorVez);

				/* verifica se é um cruzador */	
			} else if(tipoArma == 4){

				/*marca como clicado e acertado e chama método para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresCruzador(Logica.matrizControleJogadorVez);

				/* verifica se é um couraçado */		
			} else if(tipoArma == 5){

				/*marca como clicado e acertado e chama método para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(coordX, coordY);
				matrizControleJogadorVez[i][j].foiClicado = true;
				verificaArredoresCouracado(Logica.matrizControleJogadorVez);

			} 

		}

		/* notifico Observers e retorno valor da variável acertou */
		this.setChanged();
		notifyObservers(Logica.matrizControleJogadorVez);
		return acertou;

	}

	/* Verificação de Status de Acerto */
	public void setStatus(int x, int y){    ///////////////////////////// ACHO QUE ESTÁ SENDO "INÚTIL E REDUNDANTE"... REVER!

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

	/* Verificação de Final de Jogo */
	public boolean verificaFinalJogo(){

		/* verifica se o numero de embarcações do primeiro jogador é zero */
		if(Facade.j1.numEmbarcacoes == 0){

			/* atribuo o nome do segundo jogador à variável nomeJogador e variável terminou recebe true */
			nomeVencedor = TelaJogadores.getNomeJogador2();
			terminou = true;

			/* verifica se o numero de embarcações do segundo jogador é zero */	
		} else if(Facade.j2.numEmbarcacoes == 0){

			/* atribuo o nome do primeiro jogador à variável nomeJogador e variável terminou recebe true */
			nomeVencedor = TelaJogadores.getNomeJogador1();
			terminou = true;

		}	

		/* retorna valor da variável terminou */
		return terminou;
	}

}
