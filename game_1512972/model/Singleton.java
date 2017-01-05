package model;

import java.util.*;

import controller.ControladorNavio;
import facade.Facade;
import view.*;

@SuppressWarnings("unused")
/* Classe de Negï¿½cio para Lï¿½gica do Jogo */
public class Singleton extends Observable{
	private static Singleton instancia; //novo
	private static Matriz[][] matrizControleJogadorVez;
	private static int i, j;	
	private boolean afundou = false;
	private boolean acertou = false;
	public boolean posicionou = false;
	public boolean terminou = false;
	public ArrayList<Observer> listaObserver = new ArrayList<Observer>();
	public static String nomeVencedor;

	/* Construtor da Classe de Negï¿½cio */
	private Singleton(){ //modificado
		//Logica.i = 0; //nï¿½o precisa pq int jï¿½ ï¿½ inicializado com 0 por default
		//Logica.j = 0;
		//matrizControleJogadorVez = new Matriz[15][15]; tava sendo instanciada duas vezes, aqui e o inicializa
		//matrizControleJogadorVez = Matriz.criaMatrizInicializada(); //retorna a matriz jï¿½ instanciada e inicializada
	}
	
	public static Singleton getInstance(){ //novo
		if(instancia == null)
			instancia = new Singleton();
		return instancia;
	}

	/* Observer */
	public void notifyObservers(Object obj){
		//if(this.hasChanged()){
		for(Observer obs: listaObserver){
			obs.update(this, obj);
		}
		//}
	}
	
	//verifica apenas as celulas vizinhas, 
	//se vizinho estiver fora do tabuleiro então retorna true,
	//se vizinho estiver dentro, mas já tiver uma arma lá então retorna false,
	//se não, retorna true;
	private boolean verificaCelula(int x, int y){
		System.out.println("SINGLETON (verificando o redor): x="+x+" y="+y);
		
		if(x<0 || x>14 || y<0 || y>14)
			return true;
		
		if(matrizControleJogadorVez[x][y].tipoArma>0){
			System.out.println("CELULA OU ADJACENCIAS OCUPADAS");
			return false;
		}
		
		return true;
	}
	
	private boolean verificaCelulas(int x, int y, int a, int b, int c, int d){
		int u, v;
		boolean ret = true;
		
		for(u=-1; u<2 && ret; u++)
			for(v=-1; v<2 && ret; v++)
				ret = verificaCelula(x+u, y+v);
		
		for(u=-1; u<2 && ret; u++)
			for(v=-1; v<2 && ret; v++)
				ret = verificaCelula((x+a)+u, (y+b)+v);
		
		for(u=-1; u<2 && ret; u++)
			for(v=-1; v<2 && ret; v++)
				ret = verificaCelula((x+c)+u, (y+d)+v);
		
		return ret;
	}

	private boolean verificaHidroaviao(int x, int y, int dir){
		boolean ret = true;
		
		if(dir == 1){
			if(x<1 || y>12){
				System.out.println("DEBUG: x="+(x<1)+" y="+(y>12));
				return false;
			}
		}
		else
		if(dir == 2){
			if(x>12 || y<1){
				System.out.println("DEBUG: x="+(x>12)+" y="+(y<1));
				return false;
			}
		}
		else
		if(dir == 3){
			if(x>13 || y>12){
				System.out.println("DEBUG: x="+(x>13)+" y="+(y>12));
				return false;
			}	
		}
		else
		if(dir == 4){
			if(x>12 || y>13){
				System.out.println("DEBUG: x="+(x>12)+" y="+(y>13));
				return false;
			}
		}
		
		switch(dir){
			case 1:
				ret = verificaCelulas(x, y, -1, 1, 0, 2);
				break;
			case 2:
				ret = verificaCelulas(x, y, 1, -1, 2, 0);
				break;
			case 3:
				ret = verificaCelulas(x, y, 1, 1, 0, 2);
				break;
			case 4:
				ret = verificaCelulas(x, y, 1, 1, 2, 0);
				break;
		}
		
		return ret;
	}
	
	private boolean verificaOutrosNavios(int x, int y, int fimU, int fimV){
		int u, v;
		boolean ret = true;
		
		if(x>(16-fimU) || y>(16-fimV)){
			System.out.println("DEBUG: x="+(x>(16-fimU))+" y="+(y>(16-fimV)));
			return false;
		}
		
		for(u=-1; u<fimU && ret; u++)
			for(v=-1; v<fimV && ret; v++)
				ret = verificaCelula(x+u, y+v);
			
		return ret;
	}
	
	/* Verificaï¿½ï¿½o de posiï¿½ï¿½o vï¿½lida */
	private boolean verificaValidadePosicionamento2(Navio nav, int x, int y){ 
		boolean ret = true;
		boolean rot = nav.isRotate;
		int u, v;

		switch(nav.getTipo()){
			case 1: // x
				ret = verificaOutrosNavios(x, y, 2, 2);
				break;
			case 2: // xx
				if(rot)
					ret = verificaOutrosNavios(x, y, 3, 2);
				else
					ret = verificaOutrosNavios(x, y, 2, 3);
				break;
			case 3: // x
					//x x
				ret = verificaHidroaviao(x, y, nav.getDirecao());
				break;
			case 4:
				if(rot)
					ret = verificaOutrosNavios(x, y, 5, 2);
				else
					ret = verificaOutrosNavios(x, y, 2, 5);
				break;
			case 5:
				if(rot)
					ret = verificaOutrosNavios(x, y, 6, 2);
				else
					ret = verificaOutrosNavios(x, y, 2, 6);
				break;
			default:
				ret = false;
		}
		
		return ret;
	}

	/* Posicionamento de Embarcaï¿½ï¿½o */
	public void posicionarArma(Navio navio, Matriz[][] matriz, int coordX, int coordY){
		int tipo;
		boolean posicaoEhValida;
		
		/* atribui ï¿½ variï¿½vel local de matriz a matriz fornecida como parï¿½metro */
		Singleton.matrizControleJogadorVez = matriz;

		/* chama mï¿½todo de verificaï¿½ï¿½o de posiï¿½ï¿½o da matriz a qual as coordenadas x e y correspondem */
		setCoordenada(coordX, coordY);

		/* verifica se ï¿½ submarino */
		tipo = navio.getTipo();
		posicaoEhValida = verificaValidadePosicionamento2(navio, i,j);
		System.out.println("SINGLETON (teste de posicionamento): Posição "+i+","+j+" valida para posicionar? "+posicaoEhValida);
		
		if(posicaoEhValida){
			switch(tipo){
				case 1: //submarino
					/* atribui ï¿½ posiï¿½ï¿½o da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
					matriz[i][j].tipoArma = tipo;
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou submarino");
					notifyObservers(matriz);
					break;
					
				case 2: //destroyer
					if(navio.isRotate == false){
						/* atribui ï¿½s posiï¿½ï¿½es da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
						for(int x=0; x<2 ; x++)
							matriz[i][j+x].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou destroyer nï¿½o rotacionado");
						notifyObservers(matriz);
					}	
					/* verifica se estï¿½ rotacionado e se todas as casas a serem ocupadas sï¿½o vï¿½lidas */
					else if(navio.isRotate == true){
						/* atribui ï¿½s posiï¿½ï¿½es da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
						for(int x=0; x<2 ; x++)
							matriz[i+x][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou destroyer rotacionado");
						notifyObservers(matriz);
					}
					break;
					
				case 3: //hidroaviao
					/* verifica se nao esta rotacionado e se todas as casas a serem ocupadas sao validas */
					if(navio.getDirecao() == 1){
						/* atribui as posicoes da matriz o tipo da embarcacao e notifica Observers */
						matriz[i][j].tipoArma = tipo;
						matriz[i-1][j+1].tipoArma = tipo;
						matriz[i][j+2].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao rotacionado sentido anti-horario");
						notifyObservers(matriz);
						/* verifica se esta rotacionado e se todas as casas a serem ocupadas sao validas */
					} else if(navio.getDirecao() == 2){
						/* atribui as posicoes da matriz o tipo da embarcacao e notifica Observers */
						matriz[i][j].tipoArma = tipo;
						matriz[i+1][j-1].tipoArma = tipo;
						matriz[i+2][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao nao rotacionado");
						notifyObservers(matriz);
					}  else if(navio.getDirecao() == 3){
						/* atribui as posicoes da matriz o tipo da embarcacao e notifica Observers */
						matriz[i][j].tipoArma = tipo;
						matriz[i+1][j+1].tipoArma = tipo;
						matriz[i][j+2].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao rotacionado sentido horario");
						notifyObservers(matriz);
					} else if(navio.getDirecao() == 4){
						/* atribui as posicoes da matriz o tipo da embarcacao e notifica Observers */
						matriz[i][j].tipoArma = tipo;
						matriz[i+1][j+1].tipoArma = tipo;
						matriz[i+2][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao rotacionado para baixo");
						notifyObservers(matriz);
					}
					break;
					
				case 4: //cruzador
					if(navio.isRotate == false){
						/* atribui ï¿½s posiï¿½ï¿½es da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
						for(int x=0; x<4 ; x++)
							matriz[i][j+x].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou cruzador nï¿½o rotacionado");
						notifyObservers(matriz);
						/* verifica se estï¿½ rotacionado e se todas as casas a serem ocupadas sï¿½o vï¿½lidas */
					}
					else{
						/* atribui ï¿½s posiï¿½ï¿½es da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
						for(int x=0; x<4 ; x++)
							matriz[i+x][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou cruzador rotacionado");
						notifyObservers(matriz);
					}
					break;
					
				case 5: //couraçado
					if(navio.isRotate == false){
						/* atribui ï¿½s posiï¿½ï¿½es da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
						for(int x=0; x<5 ; x++)
							matriz[i][j+x].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou couraï¿½ado nï¿½o rotacionado");
						notifyObservers(matriz);
						/* verifica se estï¿½ rotacionado e se todas as casas a serem ocupadas sï¿½o vï¿½lidas */
					} 
					else{
						/* atribui ï¿½s posiï¿½ï¿½es da matriz o tipo da embarcaï¿½ï¿½o e notifica Observers */
						for(int x=0; x<5 ; x++)
							matriz[i+x][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou couraï¿½ado rotacionado");
						notifyObservers(matriz);
					}
					break;
			}
		}

		/* atribui zero ï¿½s variï¿½veis de posicionamento i e j */
		i = 0;
		j = 0;

		for(int x = 0; x < 15; x++){
			for (int y = 0; y < 15; y++){
				System.out.print(matriz[x][y].tipoArma+ " ");
			}
			System.out.println("");
		}


	}

	/* Verificaï¿½ï¿½o de Posicionamento dentro da Matriz ----> Mï¿½todo escrito pelo Lucas Debatin */
	private void setCoordenada(int coordX, int coordY){
		j = (coordX-10)/20;
		i = (coordY-10)/20;
	}	

	/* Verificaï¿½ï¿½o de Redondezas para tratar afundamento de arma */
	private void verificaArredoresDestroyer(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um destroyer */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 2 || matrizControleJogadorVez[i][j-1].tipoArma == 2 
				|| matrizControleJogadorVez[i+1][j].tipoArma == 2 || matrizControleJogadorVez[i-1][j].tipoArma == 2){

			/* marca como acerto apenas, isto ï¿½, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variï¿½vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	

	}


	private void verificaArredoresHidroaviao(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um hidroaviï¿½o */
		if(matrizControleJogadorVez[i-1][j+1].tipoArma == 3 || matrizControleJogadorVez[i][j+2].tipoArma == 3
				|| matrizControleJogadorVez[i+1][j-1].tipoArma == 3 || matrizControleJogadorVez[i+1][j+1].tipoArma == 3
				|| matrizControleJogadorVez[i-1][j-1].tipoArma == 3 || matrizControleJogadorVez[i][j-2].tipoArma == 3
				|| matrizControleJogadorVez[i+1][j+1].tipoArma == 3 || matrizControleJogadorVez[i+2][j].tipoArma == 3
				|| matrizControleJogadorVez[i-2][j].tipoArma == 3){

			/* marca como acerto apenas, isto ï¿½, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variï¿½vel afundou recebe true */
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

			/* marca como acerto apenas, isto ï¿½, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variï¿½vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	
	}

	private void verificaArredoresCouracado(Matriz[][] matrizControleJogadorVez){

		/* verifica se nos arredores do local do tiro dado existe parte de um couraï¿½ado */
		if(matrizControleJogadorVez[i][j+1].tipoArma == 5 || matrizControleJogadorVez[i][j+2].tipoArma == 5 || matrizControleJogadorVez[i][j+3].tipoArma == 5 || matrizControleJogadorVez[i][j+4].tipoArma == 5 
				|| matrizControleJogadorVez[i][j-1].tipoArma == 5 || matrizControleJogadorVez[i][j-2].tipoArma == 5 || matrizControleJogadorVez[i][j-3].tipoArma == 5 || matrizControleJogadorVez[i][j-4].tipoArma == 5
				|| matrizControleJogadorVez[i-1][j].tipoArma == 5 || matrizControleJogadorVez[i-2][j].tipoArma == 5 || matrizControleJogadorVez[i-3][j].tipoArma == 5 || matrizControleJogadorVez[i-4][j].tipoArma == 5
				|| matrizControleJogadorVez[i+1][j].tipoArma == 5 || matrizControleJogadorVez[i+2][j].tipoArma == 5 || matrizControleJogadorVez[i+3][j].tipoArma == 5 || matrizControleJogadorVez[i+4][j].tipoArma == 5){

			/* marca como acerto apenas, isto ï¿½, apenas atribui zero ao local do tiro */
			matrizControleJogadorVez[i][j].tipoArma = 0;

		} else {

			/* atribui zero ao local do tiro e a variï¿½vel afundou recebe true */
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;

		}	

	}

	/*Verificaï¿½ï¿½o de tipo de arma*/
	private int verificaArma(Matriz[][] matriz, int coordX, int coordY){

		/* chama mï¿½todo de verificaï¿½ï¿½o de posicionamento das coordenadas x e y na matriz */
		setCoordenada(coordX, coordY);

		/* verifica se hï¿½ algum tipo da arma na posicï¿½o i,j da matriz e retorna o tipo */
		if(matriz[i][j].tipoArma == 1){
			return 1;
		} else if(matriz[i][j].tipoArma == 2){
			return 2;
		} else if(matriz[i][j].tipoArma == 3){
			return 3;
		} else if(matriz[i][j].tipoArma == 4){
			return 4;
		} else if(matriz[i][j].tipoArma == 5){
			return 5;
		}

		/* se nï¿½o houver arma no local, retorna zero */
		return 0;
	}

	/*Lï¿½gica de Ataque*/
	public boolean atirar(Matriz[][] matriz, int coordX, int coordY){
		int tipoArma;

		/* chama mï¿½todo de verificaï¿½ï¿½o de tipo de embarcaï¿½ï¿½o posicionada nas 
		 * coordenadas x e y da matriz e atribui retorno ï¿½ variï¿½vel tipoArma */
		tipoArma = verificaArma(matriz, coordX, coordY);
		
		System.out.println("O tiro acertou isso->"+tipoArma);
		/* verifica se existe alguma embarcaï¿½ï¿½o no local */
		if(tipoArma != 0){

			/* verifica se ï¿½ um submarino */
			if(tipoArma == 1){

				/*marca como clicado e afundado e chama mï¿½todo para alterar status de acerto */
				afundou = true;	
				acertou = true;
				setStatus(matriz);
				//matrizControleJogadorVez[i][j].foiClicado = true;

				/* verifica se ï¿½ um destroyer */	
			} else if(tipoArma == 2){

				/*marca como clicado e acertado e chama mï¿½todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);
				//matrizControleJogadorVez[i][j].foiClicado = true;
				//verificaArredoresDestroyer(matriz);

				/* verifica se ï¿½ um hidroaviï¿½o */		
			} else if(tipoArma == 3){

				/*marca como clicado e acertado e chama mï¿½todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);
				//matrizControleJogadorVez[i][j].foiClicado = true;
				//verificaArredoresHidroaviao(matriz);

				/* verifica se ï¿½ um cruzador */	
			} else if(tipoArma == 4){

				/*marca como clicado e acertado e chama mï¿½todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);
				//matrizControleJogadorVez[i][j].foiClicado = true;
				//verificaArredoresCruzador(matriz);

				/* verifica se ï¿½ um couraï¿½ado */		
			} else if(tipoArma == 5){

				/*marca como clicado e acertado e chama mï¿½todo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);
				//matrizControleJogadorVez[i][j].foiClicado = true;
				//verificaArredoresCouracado(matriz);

			} 
		}
		else{
			acertou = false;
			setStatus(matriz);
		}

		/* notifico Observers e retorno valor da variï¿½vel acertou */
		this.setChanged();
		notifyObservers(matriz); //telacampobatalha
		return acertou;

	}

	/* Verificaï¿½ï¿½o de Status de Acerto */
	public void setStatus(Matriz[][] matriz){

		/* verifica se acertou */
		if(acertou){
			System.out.println("SINGLETON: setstatus()-> "+TelaCampoBatalha.jogVez.getNome()+" ACERTOU!");
			/* marco como clicado e acertado e notifico Observers */
			matriz[i][j].tipoArma = -2;
		}
		else{
			System.out.println("SINGLETON: setstatus()-> "+TelaCampoBatalha.jogVez.getNome()+" ERROU!");
			matriz[i][j].tipoArma = -1; //agua
		}
	}
}
