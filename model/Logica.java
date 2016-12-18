package model;

import java.util.*;

import facade.Facade;
import view.*;

/*Classe de Negócio para Lógica do Jogo*/
public class Logica extends Observable{
	private static Matriz[][] matrizControleJogadorVez;
	private static int i, j;
	private boolean afundou = false;
	private boolean acertou = false;
	public boolean terminou = false;
	public ArrayList<Observer> listaObserver = new ArrayList<Observer>();
	//private ListIterator<Observer> it;
	public static String nomeVencedor;

	/*Construtor da Classe de Negócio*/
	public Logica(){
		Logica.i = 0;
		Logica.j = 0;
		matrizControleJogadorVez = new Matriz[15][15];
		matrizControleJogadorVez = Matriz.inicializaMatriz(matrizControleJogadorVez);
	}

	/*Observer*/
	public void notifyObservers(Object obj){
		if(this.hasChanged()){
			for(Observer obs: listaObserver){
				obs.update(this, obj);
			}
		}
	}

	public void posicionarArma(Navio navio, Matriz[][] matrizControleJogadorVez, int coordX, int coordY){
		Logica.matrizControleJogadorVez = matrizControleJogadorVez;
		verificaPosMatriz(Logica.matrizControleJogadorVez, coordX, coordY);
		System.out.println("Inx i no pos: "+i);
		System.out.println("Inx j no pos: "+j);
		if(verificaValidadePosicionamento(i,j) == true){
			
			if(navio.getTipo() == 1){	
				
				Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
				this.setChanged();
				notifyObservers(Logica.matrizControleJogadorVez);

			} else if (navio.getTipo() == 2){

				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				} else if(navio.isRotate == true && verificaValidadePosicionamento(i,j+1) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);
				}

			} else if(navio.getTipo() == 3){

				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j-1) == true && verificaValidadePosicionamento(i+2,j) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j-1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				} else if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j+1) == true && verificaValidadePosicionamento(i+2,j) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j+1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				}

			} else if(navio.getTipo() == 4){

				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j) == true && verificaValidadePosicionamento(i+2,j) == true && verificaValidadePosicionamento(i+3,j) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+3][j].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				} else if(navio.isRotate == false && verificaValidadePosicionamento(i,j+1) == true && verificaValidadePosicionamento(i,j+2) == true && verificaValidadePosicionamento(i,j+3) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+3].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				}

			} else {

				if(navio.isRotate == false && verificaValidadePosicionamento(i+1,j) == true && verificaValidadePosicionamento(i+2,j) == true && verificaValidadePosicionamento(i+3,j) == true && verificaValidadePosicionamento(i+4,j) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+1][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+2][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+3][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i+4][j].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				} else if(navio.isRotate == false && verificaValidadePosicionamento(i,j+1) == true && verificaValidadePosicionamento(i,j+2) == true && verificaValidadePosicionamento(i,j+3) == true && verificaValidadePosicionamento(i,j+4) == true){

					Logica.matrizControleJogadorVez[i][j].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+1].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+2].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+3].tipoArma = navio.getTipo();
					Logica.matrizControleJogadorVez[i][j+4].tipoArma = navio.getTipo();
					this.setChanged();
					notifyObservers(Logica.matrizControleJogadorVez);

				}
			}
		} 
		
		i = 0;
		j = 0;
	}

	/*Verificação de Posicionamento dentro da Matriz*/
	private void verificaPosMatriz(Matriz[][] matrizControleJogadorVez, int coordX, int coordY){
		int x,y;
		//i = ((coordY*20)/305)-1;
		//j = ((coordX*20)/305)-1;
		for(x = 10; x < 315; x += 20 ){
			if(x < coordX){
				i++;
			} else {
				break;
			}
		}
		for(y = 10; y < 315; y += 20){
			if(y < coordY){
				j++;
			} else {
				break;
			}
		}
		i-=1;
		j-=1;
		System.out.println("Valor de i result: "+i);
		System.out.println("Valor de j result: "+j);
	}

	/*Verificação de posição válida*/
	private boolean verificaValidadePosicionamento(int i, int j){ ///////////REVER CONDI��ES... TA INCOMPLETO!!!
		i = Logica.j;
		j = Logica.i;
		if(i > 0 && i < 14 && j > 0 && j < 14){
			if(Logica.matrizControleJogadorVez[i-1][j-1].tipoArma == 0){
				i = Logica.j;
				j = Logica.i;
				if(Logica.matrizControleJogadorVez[i-1][j].tipoArma == 0){
					i = Logica.j;
					j = Logica.i;
					if(Logica.matrizControleJogadorVez[i-1][j+1].tipoArma == 0){
						i = Logica.j;
						j = Logica.i;
						if(Logica.matrizControleJogadorVez[i][j-1].tipoArma == 0){
							i = Logica.j;
							j = Logica.i;
							if(Logica.matrizControleJogadorVez[i][j+1].tipoArma == 0){
								i = Logica.j;
								j = Logica.i;
								if(Logica.matrizControleJogadorVez[i+1][j-1].tipoArma == 0){
									i = Logica.j;
									j = Logica.i;
									if(Logica.matrizControleJogadorVez[i+1][j].tipoArma == 0){
										i = Logica.j;
										j = Logica.i;
										if(Logica.matrizControleJogadorVez[i+1][j+1].tipoArma == 0){
											i = Logica.j;
											j = Logica.i;
											if(i+4 < 15 && j+4 < 15){
												i = Logica.j;
												j = Logica.i;
												if(i-1 >= 0 && j-1 >= 0){
													i = Logica.j;
													j = Logica.i;
													return true;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return false;		
	}

	/*Verificação de Redondezas para tratar afundamento de arma*/
	private void verificaArredoresDestroyer(Matriz[][] matrizControleJogadorVez){
		if(matrizControleJogadorVez[i][j+1].tipoArma == 2 || matrizControleJogadorVez[i][j-1].tipoArma == 2 
				|| matrizControleJogadorVez[i+1][j].tipoArma == 2 || matrizControleJogadorVez[i-1][j].tipoArma == 2){
			matrizControleJogadorVez[i][j].tipoArma = -1;
		} else {
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;
		}		
	}

	private void verificaArredoresHidroaviao(Matriz[][] matrizControleJogadorVez){
		if(matrizControleJogadorVez[i-1][j+1].tipoArma == 3 || matrizControleJogadorVez[i][j+2].tipoArma == 3
				|| matrizControleJogadorVez[i+1][j-1].tipoArma == 3 || matrizControleJogadorVez[i+1][j+1].tipoArma == 3
				|| matrizControleJogadorVez[i-1][j-1].tipoArma == 3 || matrizControleJogadorVez[i][j-2].tipoArma == 3
				|| matrizControleJogadorVez[i+1][j+1].tipoArma == 3 || matrizControleJogadorVez[i+2][j].tipoArma == 3
				|| matrizControleJogadorVez[i-2][j].tipoArma == 3){
			matrizControleJogadorVez[i][j].tipoArma = -1;
		} else {
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;
		}	
	}


	private void verificaArredoresCruzador(Matriz[][] matrizControleJogadorVez){
		if(matrizControleJogadorVez[i][j+1].tipoArma == 4 || matrizControleJogadorVez[i][j+2].tipoArma == 4 || matrizControleJogadorVez[i][j+3].tipoArma == 4 
				|| matrizControleJogadorVez[i][j-1].tipoArma == 4 || matrizControleJogadorVez[i][j-2].tipoArma == 4 || matrizControleJogadorVez[i][j-3].tipoArma == 4
				|| matrizControleJogadorVez[i-1][j].tipoArma == 4 || matrizControleJogadorVez[i-2][j].tipoArma == 4 || matrizControleJogadorVez[i-3][j].tipoArma == 4
				|| matrizControleJogadorVez[i+1][j].tipoArma == 4 || matrizControleJogadorVez[i+2][j].tipoArma == 4 || matrizControleJogadorVez[i+3][j].tipoArma == 4){
			matrizControleJogadorVez[i][j].tipoArma = -1;
		} else {
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;
		}	
	}

	private void verificaArredoresCouracado(Matriz[][] matrizControleJogadorVez){		
		if(matrizControleJogadorVez[i][j+1].tipoArma == 5 || matrizControleJogadorVez[i][j+2].tipoArma == 5 || matrizControleJogadorVez[i][j+3].tipoArma == 5 || matrizControleJogadorVez[i][j+4].tipoArma == 5 
				|| matrizControleJogadorVez[i][j-1].tipoArma == 5 || matrizControleJogadorVez[i][j-2].tipoArma == 5 || matrizControleJogadorVez[i][j-3].tipoArma == 5 || matrizControleJogadorVez[i][j-4].tipoArma == 5
				|| matrizControleJogadorVez[i-1][j].tipoArma == 5 || matrizControleJogadorVez[i-2][j].tipoArma == 5 || matrizControleJogadorVez[i-3][j].tipoArma == 5 || matrizControleJogadorVez[i-4][j].tipoArma == 5
				|| matrizControleJogadorVez[i+1][j].tipoArma == 5 || matrizControleJogadorVez[i+2][j].tipoArma == 5 || matrizControleJogadorVez[i+3][j].tipoArma == 5 || matrizControleJogadorVez[i+4][j].tipoArma == 5){
			matrizControleJogadorVez[i][j].tipoArma = -1;
		} else {
			matrizControleJogadorVez[i][j].tipoArma = 0;
			afundou = true;
		}	
	}

	/*Verificação de tipo de arma*/
	private int verificaArma(Matriz[][] matrizControleJogadorVez, int coordX, int coordY){	
		verificaPosMatriz(matrizControleJogadorVez, coordX, coordY);
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
		return 0;
	}

	/*Lógica de Ataque*/
	public boolean atirar(Matriz[][] matriz, int coordX, int coordY){
		int tipoArma;
		Logica.matrizControleJogadorVez = matriz;		
		tipoArma = verificaArma(Logica.matrizControleJogadorVez, coordX, coordY);
		if(tipoArma != 0){
			if(tipoArma == 1){
				afundou = true;
				setStatus(coordX, coordY);
			} else if(tipoArma == 2){
				acertou = true;
				setStatus(coordX, coordY);
				verificaArredoresDestroyer(Logica.matrizControleJogadorVez);
			} else if(tipoArma == 3){
				acertou = true;
				setStatus(coordX, coordY);
				verificaArredoresHidroaviao(Logica.matrizControleJogadorVez);
			} else if(tipoArma == 4){
				acertou = true;
				setStatus(coordX, coordY);
				verificaArredoresCruzador(Logica.matrizControleJogadorVez);
			} else if(tipoArma == 5){
				acertou = true;
				setStatus(coordX, coordY);
				verificaArredoresCouracado(Logica.matrizControleJogadorVez);
			} else {			
				if(afundou){
					setStatus(coordX, coordY);
					matrizControleJogadorVez[i][j].tipoArma = 0;
				}		
			}
		}
		this.setChanged();
		notifyObservers(Logica.matrizControleJogadorVez);
		return acertou;
	}

	public void setStatus(int x, int y){
		if(acertou){
			Logica.matrizControleJogadorVez[i][j].tipoArma = -1;
			Logica.matrizControleJogadorVez[i][j].statusAcerto = true;	
			this.setChanged();
			notifyObservers(Logica.matrizControleJogadorVez);
		}
	}

	public boolean verificaFinalJogo(){
		if(Facade.j1.numEmbarcacoes == 0){
			nomeVencedor = TelaJogadores.getNomeJogador1();
			terminou = true;
		} else if(Facade.j2.numEmbarcacoes == 0){
			nomeVencedor = TelaJogadores.getNomeJogador2();
			terminou = true;
		}			
		return terminou;
	}





}
