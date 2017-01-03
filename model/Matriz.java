package model;

/* Classe de Neg�cio para cria��o do tipo Matriz */
public class Matriz {
	public int tipoArma;

	/* Construtor da Classe de Neg�cio */
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

	/* Inicializa��o da Matriz */
	public static Matriz[][] criaMatrizInicializada(){

		/* inicializa matriz */
		Matriz[][] matriz = new Matriz[15][15];

		/* inicializa cada posi��o da matriz */
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				matriz[i][j] = new Matriz(0);
				matriz[i][j].tipoArma = 0;
			}
		}

		/* retorna matriz inicializada */
		return matriz;

	}

	/* Atribui��o de matrizes */
	public static Matriz[][] atribuiMatriz(Matriz[][] matriz1, Matriz[][] matriz2){ 
		/* FIZ ESSE M�TODO PORQUE ESTAVA DANDO EXCE��O (NULLPOINTER 
		 * EXCEPTION) QUANDO EU ATRIBUIA DIRETO, ACHO QUE AGORA N�O 
		 * � MAIS NECESS�RIO, ESTOU MANTENDO AQUI APENAS POR PRECAU��O.*/

		/* matriz 1 recebe matriz 2 e � retornada */
		matriz1 = matriz2;
		return matriz1;

	}

}
