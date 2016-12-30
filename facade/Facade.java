package facade;
import controller.*;
import model.*;
import view.*;

/* Classe de Fachada */
public class Facade {
	private static Logica l;
	private static boolean acertou;
	public static Jogador j1;
	public static Jogador j2;

	/* Inicializador da Classe Fachada */
	public static void inicializaFacade(){

		/* inicializa uma inst�ncia de l�gica a ser utilizada para o jogo */
		l = new Logica();

	}

	/* Iniciar Novo Jogo */
	public static void novoJogo(){

		/* cria uma instancia da tela de novo jogo */
		TelaJogadores.getInstance();

	}

	/* Carregar Jogo Salvo */
	public static void carregarJogo(TelaCampoBatalha tela){

		/* chama fun��o de carregamento de jogo passando uma tela de campo de 
		 * batalha vazia a ser preenchida com os dados salvos anteriormente */
		Salvamento.carregarJogo(tela);

	}

	/* Cria��o de Jogadores */
	public static void criarJogadores(String nomeJogador1, String nomeJogador2){

		/* inicializa duas inst�ncias de jogador
		 * com os nomes fornecidos por par�metro */
		j1 = new Jogador(nomeJogador1);
		j2 = new Jogador(nomeJogador2);

	}	

	/* Posicionamento de Armas */
	public static void posicionarArmasJogador1(String nomeJogador1){

		/* cria uma instancia da tela de posicionamento de embarca��es 
		 * para o primeiro jogador e a adiciona � lista de Observers */
		TelaEmbarcacoes.getInstance1();	
		l.listaObserver.add(TelaEmbarcacoes.getInstance1());

	}

	public static void posicionarArmasJogador2(String nomeJogador2){

		/* cria uma instancia da tela de posicionamento de embarca��es 
		 * para o segundo jogador e a adiciona � lista de Observers */
		TelaEmbarcacoes.getInstance2();	
		l.listaObserver.add(TelaEmbarcacoes.getInstance2());
		TelaEmbarcacoes.verificaPronto();

	}

	public static void escolherPosicaoArma(Matriz[][] matriz, int x, int y){

		/* chama m�todo de posicionamento de embarca��es definido na classe l�gica,
		 * passando o �ltimo navio clicado, a matriz e as coordenadas x e y clicadas */
		if(ControladorNavio.navioClicado != null && ControladorNavio.navioClicado.isPositioned == false){
			l.posicionarArma(ControladorNavio.navioClicado, matriz, x, y);
			ControladorNavio.navioClicado = null;
		}
		
	}

	/* Fase de Ataques */
	public static void iniciarAtaques(){

		/* cria uma inst�ncia da tela de campo de batalha, passando os 
		 * jogadores como par�metro e a adiciona � lista de Observers */
		TelaCampoBatalha.getInstance(j1, j2);
		l.listaObserver.add(TelaCampoBatalha.getInstance(j1, j2));

	}

	/* Ataques */
	public static void atacar(Matriz[][] matriz, int x, int y){
		
		/* repeti��o garante que cada jogador ataque tr�s vezes 
		 * seguidas e depois a vez de atacar passe para o oponente */
		for(int i = 0; i < 3; i++){
			
			/* vari�vel acertou recebe retorno booleano de acerto fornecido 
			 * pela chamada do m�todo atirar definido na classe l�gica, onde 
			 * � passada a matriz e as coordenadas x e y clicadas */
			acertou = l.atirar(matriz, x, y);
			
			/* se acertou o tiro, redesenha tela de campo de batalha atual e o 
			 * n�mero de embarca��es do jogador oponente � decrementado em um */ 
			if(acertou){
				TelaCampoBatalha.getInstance(j1, j2).repaint();
				j2.numEmbarcacoes--;
			}
			
		}
		
		
		
		/* verifica se terminou o jogo ou se o jogo prossegue */
		if(l.terminou){
		
			/* cria uma inst�ncia da tela final, passando o nome do vencedor */
			TelaFinal.getInstance(Logica.nomeVencedor);			
			
		} else {
		
			/* chama m�todo de alternar os tabuleiros */
			TelaCampoBatalha.getInstance(j1, j2).alternaTabuleiros();
			
		}
		
	}

	/* Salvamento do Jogo */
	public static void salvarJogo(TelaCampoBatalha tela){
		
		/* chama m�todo de salvamento de jogo, passando 
		 * tela de campo de batalha com os dados a serem
		 * armazenados no arquivo de salvamento */
		Salvamento.salvarJogo(tela);
		
	}

}

