package model;

/*Classe de Neg󣩯 para criação do tipo Matriz*/
public class Matriz {
	protected int tipoArma;
	public boolean statusAcerto;

	/*Construtor da Classe de Negócio*/
	public Matriz(int arma, boolean statusAcerto){
		this.tipoArma = arma;
		this.statusAcerto = statusAcerto;
	}

	/*Getters e Setters*/
	public int getTipoArma() {
		return tipoArma;
	}

	
	public static Matriz[][] inicializaMatriz(Matriz[][] matriz){
		matriz = new Matriz[15][15];
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				matriz[i][j] = new Matriz(0, false);
				matriz[i][j].tipoArma = 0;
				matriz[i][j].statusAcerto = false;
			}
		}
		return matriz;
	}
	
	public static Matriz[][] atribuiMatriz(Matriz[][] matriz1, Matriz[][] matriz2){
		matriz1 = matriz2;
		return matriz1;
	}
	
	public void setTipoArma(int tipo) {
		this.tipoArma = tipo;		
	}
	
	public static boolean getStatusAcerto(Matriz[][] matriz, int i, int j){
		return matriz[i][j].statusAcerto;
	}
}
