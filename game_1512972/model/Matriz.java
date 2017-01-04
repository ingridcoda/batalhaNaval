package model;

/* Classe de Negocio para criação do tipo Matriz */
public class Matriz {
	public int tipoArma;

	/* Construtor da Classe de Negocio */
	public Matriz(int arma){
		this.tipoArma = arma;
	}

	/* Getters e Setters */
	public int getTipoArma() {
		return tipoArma;
	}

	public void setTipoArma(int tipo) {
		this.tipoArma = tipo;		
	}

	/* Inicializacao da Matriz */
	public static Matriz[][] criaMatrizInicializada(){

		/* inicializa matriz */
		Matriz[][] matriz = new Matriz[15][15];

		/* inicializa cada posicao da matriz */
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				matriz[i][j] = new Matriz(0);
				matriz[i][j].tipoArma = 0;
			}
		}

		/* retorna matriz inicializada */
		return matriz;

	}

}
