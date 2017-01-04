package model;

import java.util.*;

import facade.Facade;
import view.*;

@SuppressWarnings("unused")
/* Classe de Negocio para Logica do Jogo */
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

	/* Construtor da Classe de Negocio */
	private Singleton(){ 
		
	}
	
	/* Singleton */
	public static Singleton getInstance(){ 
		if(instancia == null){
			instancia = new Singleton();
		}
		return instancia;
	}

	/* Observer */
	public void notifyObservers(Object obj){
		for(Observer obs: listaObserver){
			obs.update(this, obj);
		}
	}
	
	/* verifica apenas as celulas vizinhas, se vizinho estiver fora do 
	 * tabuleiro então retorna true, se vizinho estiver dentro, mas já 
	 * tiver uma arma lá então retorna false, se não, retorna true */
	private boolean verificaCelula(int x, int y){
		
		System.out.println("SINGLETON (verificando o redor): x = " +x+ " y = " +y);
		
		if(x < 0 || x > 14 || y < 0 || y > 14)
			return true;
		
		if(matrizControleJogadorVez[x][y].tipoArma > 0){
			
			System.out.println("CELULA OU ADJACENCIAS OCUPADAS");
			return false;
			
		}
		
		return true;
		
	}
	
	private boolean verificaCelulas(int x, int y, int a, int b, int c, int d){
		int u, v;
		boolean ret = true;
		
		for(u =- 1; u < 2 && ret; u++){
			for(v =- 1; v < 2 && ret; v++){
				ret = verificaCelula(x + u, y + v);
			}
		}
		
		for(u =- 1; u < 2 && ret; u++){
			for(v =- 1; v < 2 && ret; v++){
				ret = verificaCelula((x + a) + u, (y + b) + v);
			}
		}
		
		for(u =- 1; u < 2 && ret; u++){
			for(v =- 1; v < 2 && ret; v++){
				ret = verificaCelula((x + c) + u, (y + d) + v);
			}
		}
		
		return ret;
		
	}

	private boolean verificaHidroaviao(int x, int y, int dir){
		boolean ret = true;
		
		if(dir == 1){
			if(x < 1 || y > 12){
				System.out.println("DEBUG: x="+(x < 1)+" y="+(y > 12));
				return false;
			}
		}
		else
		if(dir == 2){
			if(x > 12 || y < 1){
				System.out.println("DEBUG: x="+(x > 12)+" y="+(y < 1));
				return false;
			}
		}
		else
		if(dir == 3){
			if(x > 13 || y > 12){
				System.out.println("DEBUG: x="+(x > 13)+" y="+(y > 12));
				return false;
			}	
		}
		else
		if(dir == 4){
			if(x > 12 || y > 13){
				System.out.println("DEBUG: x="+(x > 12)+" y="+(y > 13));
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
		
		if(x > (16 - fimU) || y > (16 - fimV)){
			System.out.println("DEBUG: x="+(x > (16 - fimU))+" y="+(y > (16 - fimV)));
			return false;
		}
		
		for(u =- 1; u < fimU && ret; u++)
			for(v =- 1; v < fimV && ret; v++)
				ret = verificaCelula(x+u, y+v);
			
		return ret;
	}
	
	/* Verificacao de posicao valida */
	private boolean verificaValidadePosicionamento(Navio nav, int x, int y){ 
		boolean ret = true;
		boolean rot = nav.isRotate;
		int u, v;

		switch(nav.getTipo()){
		
			case 1: 
				ret = verificaOutrosNavios(x, y, 2, 2);
				break;
				
			case 2: 
				if(rot){
					ret = verificaOutrosNavios(x, y, 3, 2);
				} else {
					ret = verificaOutrosNavios(x, y, 2, 3);
				}
				break;
				
			case 3: 
				ret = verificaHidroaviao(x, y, nav.getDirecao());
				break;
				
			case 4:
				if(rot){
					ret = verificaOutrosNavios(x, y, 5, 2);
				} else {
					ret = verificaOutrosNavios(x, y, 2, 5);
				}
				break;
				
			case 5:
				if(rot){
					ret = verificaOutrosNavios(x, y, 6, 2);
				} else {
					ret = verificaOutrosNavios(x, y, 2, 6);
				}
				break;
				
			default:
				ret = false;
		}
		
		return ret;
	}

	/* Posicionamento de Embarcacao */
	public void posicionarArma(Navio navio, Matriz[][] matriz, int coordX, int coordY){
		int tipo;
		boolean posicaoEhValida;
		
		/* atribui a variavel local de matriz a matriz fornecida como parametro */
		Singleton.matrizControleJogadorVez = matriz;

		/* chama metodo de verificacao de posicao da matriz a qual as coordenadas x e y correspondem */
		setCoordenada(coordX, coordY);

		/* se posicao escolhida for valida, posiciona arma e notifica Observers */
		tipo = navio.getTipo();
		posicaoEhValida = verificaValidadePosicionamento(navio, i,j);
		System.out.println("SINGLETON (teste de posicionamento): Posição "+i+","+j+" valida para posicionar? "+posicaoEhValida);
		
		if(posicaoEhValida){
			switch(tipo){
				case 1: 
					matriz[i][j].tipoArma = tipo;
					this.setChanged();
					navio.isPositioned = true;
					System.out.println("Posicionou submarino");
					notifyObservers(matriz);
					break;
					
				case 2: 
					if(navio.isRotate == false){
						
						for(int x = 0; x < 2 ; x++){
							matriz[i][j+x].tipoArma = tipo;
						}
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou destroyer nao rotacionado");
						notifyObservers(matriz);
						
					} else if(navio.isRotate == true){
						
						for(int x = 0; x < 2 ; x++){
							matriz[i+x][j].tipoArma = tipo;
						}
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou destroyer rotacionado");
						notifyObservers(matriz);
					}
					break;
					
				case 3: 
					if(navio.getDirecao() == 1){
						
						matriz[i][j].tipoArma = tipo;
						matriz[i-1][j+1].tipoArma = tipo;
						matriz[i][j+2].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao rotacionado sentido anti-horario");
						notifyObservers(matriz);
						
					} else if(navio.getDirecao() == 2){
						
						matriz[i][j].tipoArma = tipo;
						matriz[i+1][j-1].tipoArma = tipo;
						matriz[i+2][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao nao rotacionado");
						notifyObservers(matriz);
						
					}  else if(navio.getDirecao() == 3){
						
						matriz[i][j].tipoArma = tipo;
						matriz[i+1][j+1].tipoArma = tipo;
						matriz[i][j+2].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao rotacionado sentido horario");
						notifyObservers(matriz);
						
					} else if(navio.getDirecao() == 4){
						
						matriz[i][j].tipoArma = tipo;
						matriz[i+1][j+1].tipoArma = tipo;
						matriz[i+2][j].tipoArma = tipo;
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou hidroaviao rotacionado para baixo");
						notifyObservers(matriz);
						
					}
					break;
					
				case 4: 
					if(navio.isRotate == false){
						
						for(int x = 0; x < 4 ; x++){
							matriz[i][j+x].tipoArma = tipo;
						}
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou cruzador nï¿½o rotacionado");
						notifyObservers(matriz);
						
					} else {
						
						for(int x = 0; x < 4 ; x++){
							matriz[i+x][j].tipoArma = tipo;
						}
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou cruzador rotacionado");
						notifyObservers(matriz);
					}
					break;
					
				case 5: 
					if(navio.isRotate == false){
						
						for(int x = 0; x < 5 ; x++){
							matriz[i][j+x].tipoArma = tipo;
						}
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou couraï¿½ado nï¿½o rotacionado");
						notifyObservers(matriz);
						
					} else{
						
						for(int x = 0; x < 5 ; x++){
							matriz[i+x][j].tipoArma = tipo;
						}
						this.setChanged();
						navio.isPositioned = true;
						System.out.println("Posicionou couraï¿½ado rotacionado");
						notifyObservers(matriz);
						
					}
					break;
					
			}
			
		}

		/* atribui zero as variaveis de posicionamento i e j */
		i = 0;
		j = 0;

		for(int x = 0; x < 15; x++){
			for (int y = 0; y < 15; y++){
				System.out.print(matriz[x][y].tipoArma+ " ");
			}
			System.out.println("");
		}


	}

	/* Verificacao de Posicionamento dentro da Matriz */
	private void setCoordenada(int coordX, int coordY){
		j = (coordX-10)/20;
		i = (coordY-10)/20;
	}	

	/*Verificacao de tipo de arma*/
	private int verificaArma(Matriz[][] matriz, int coordX, int coordY){

		/* chama metodo de verificacao de posicionamento das coordenadas x e y na matriz */
		setCoordenada(coordX, coordY);

		/* verifica se ha algum tipo da arma na posicao i,j da matriz e retorna o tipo */
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

		/* se nao houver arma no local, retorna zero */
		return 0;
	}

	/*Logica de Ataque*/
	public boolean atirar(Matriz[][] matriz, int coordX, int coordY){
		int tipoArma;

		/* chama metodo de verificacao de tipo de embarcacao posicionada nas 
		 * coordenadas x e y da matriz e atribui retorno a variavel tipoArma */
		tipoArma = verificaArma(matriz, coordX, coordY);
		
		System.out.println("O tiro acertou isso->"+tipoArma);
		/* verifica se existe alguma embarcacao no local */
		if(tipoArma != 0){

			/* verifica se eh um submarino */
			if(tipoArma == 1){

				/*marca como clicado e afundado e chama metodo para alterar status de acerto */
				afundou = true;	
				acertou = true;
				setStatus(matriz);

				/* verifica se eh um destroyer */	
			} else if(tipoArma == 2){

				/*marca como clicado e acertado e chama metodo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);

				/* verifica se eh um hidroaviao */		
			} else if(tipoArma == 3){

				/*marca como clicado e acertado e chama metodo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);
				
				/* verifica se eh um cruzador */	
			} else if(tipoArma == 4){

				/*marca como clicado e acertado e chama metodo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);

				/* verifica se eh um couracado */		
			} else if(tipoArma == 5){

				/*marca como clicado e acertado e chama metodo para alterar status de acerto e verificar arredores */
				acertou = true;
				setStatus(matriz);
				
			} 
		}
		else{
			acertou = false;
			setStatus(matriz);
		}

		/* notifico Observers e retorno valor da variavel acertou */
		this.setChanged();
		notifyObservers(matriz); 
		return acertou;

	}

	/* Verificacao de Status de Acerto */
	public void setStatus(Matriz[][] matriz){

		/* verifica se acertou */
		if(acertou){
			
			System.out.println("SINGLETON: setstatus()-> "+TelaCampoBatalha.jogVez.getNome()+" ACERTOU!");
			/* marco como clicado e acertado e notifico Observers */
			matriz[i][j].tipoArma = -2;
			
		} else {
			
			System.out.println("SINGLETON: setstatus()-> "+TelaCampoBatalha.jogVez.getNome()+" ERROU!");
			/* marco como clicado e errado e notifico Observers */
			matriz[i][j].tipoArma = -1; 
		}
		
	}
	
}
