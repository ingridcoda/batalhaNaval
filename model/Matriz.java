package model;

/* Classe de Neg�cio para cria��o do tipo Matriz */
public class Matriz {
	public int tipoArma;
	public boolean statusAcerto;
	public boolean foiClicado;

	/* Construtor da Classe de Neg�cio */
	public Matriz(int arma, boolean statusAcerto){
		this.tipoArma = arma;
		this.statusAcerto = statusAcerto;
	}

	/* Getters e Setters */
	public int getTipoArma() {
		return tipoArma;
	}

	public void setTipoArma(int tipo) {
		this.tipoArma = tipo;		
	}

	public static boolean getStatusAcerto(Matriz[][] matriz, int i, int j){
		return matriz[i][j].statusAcerto;
	}

	/* Inicializa��o da Matriz */
	public static Matriz[][] inicializaMatriz(Matriz[][] matriz){

		/* inicializa matriz */
		matriz = new Matriz[15][15];

		/* inicializa cada posi��o da matriz */
		for(int i = 0; i < 15; i++){
			for(int j = 0; j < 15; j++){
				matriz[i][j] = new Matriz(0, false);
				matriz[i][j].tipoArma = 0;
				matriz[i][j].statusAcerto = false;
				matriz[i][j].foiClicado = false;
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
