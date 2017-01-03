package model;

/* Classe de Negócio para criação do tipo Matriz */
public class Matriz {
	public int tipoArma;

	/* Construtor da Classe de Negócio */
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

	/* Inicialização da Matriz */
	public static Matriz[][] criaMatrizInicializada(){

		/* inicializa matriz */
		Matriz[][] matriz = new Matriz[15][15];

		/* inicializa cada posição da matriz */
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				matriz[i][j] = new Matriz(0);
				matriz[i][j].tipoArma = 0;
			}
		}

		/* retorna matriz inicializada */
		return matriz;

	}

	/* Atribuição de matrizes */
	public static Matriz[][] atribuiMatriz(Matriz[][] matriz1, Matriz[][] matriz2){ 
		/* FIZ ESSE MÉTODO PORQUE ESTAVA DANDO EXCEÇÃO (NULLPOINTER 
		 * EXCEPTION) QUANDO EU ATRIBUIA DIRETO, ACHO QUE AGORA NÃO 
		 * É MAIS NECESSÁRIO, ESTOU MANTENDO AQUI APENAS POR PRECAUÇÃO.*/

		/* matriz 1 recebe matriz 2 e é retornada */
		matriz1 = matriz2;
		return matriz1;

	}

}
