package facade;
import controller.*;
import model.*;
import view.*;

/* Classe de Fachada */
public class Facade {
	private static Singleton instancia;
	private static boolean acertou;
	private static int tiros = 3;
	public static Jogador j1;
	public static Jogador j2;
	@SuppressWarnings("unused")
	private static ControladorNavio controladorNavio;

	/* Inicializador da Classe Fachada */
	public static void inicializaFacade(){

		/* inicializa uma instancia de logica a ser utilizada para o jogo */
		instancia = Singleton.getInstance();

		/* instancia a tela inicial */
		TelaInicial.mostrarTela();

	}

	/* Getters e Setters */	
	public static Jogador getJogador(int idJogador){
		if(idJogador == 1){
			return j1;
		} else {
			return j2;
		}
	}

	public static void setJogador(int idJogador, String nome){
		if(idJogador == 1){
			j1 = new Jogador(nome);
		} else {
			j2 = new Jogador(nome);
		}
	}

	/* Iniciar Novo Jogo */
	public static void novoJogo(){

		/* cria uma instancia da tela de novo jogo */
		TelaJogadores.mostrarTela();

	}

	/* Carregar Jogo Salvo */
	public static void carregarJogo(){

		/* chama funcao de carregamento de jogo passando uma tela de campo de 
		 * batalha vazia a ser preenchida com os dados salvos anteriormente */
		Salvamento.carregarJogo();
		iniciarAtaques();
		
	}

	/* Criaï¿½ï¿½o de Jogadores */
	public static void criarJogadores(String nomeJogador1, String nomeJogador2){
		
		/* inicializa duas instancias de jogador com os nomes fornecidos 
		 * por parametro e uma instancia de controladorNavio*/
		controladorNavio = new ControladorNavio();		
		j1 = new Jogador(nomeJogador1);
		j2 = new Jogador(nomeJogador2);

	}	

	
	/* Posicionamento de Armas */
	public static void escolherPosicaoArma(Matriz[][] matriz, int x, int y){

		/* chama metodo de posicionamento de embarcacoes definido na classe de logica,
		 * passando o ultimo navio clicado, a matriz e as coordenadas x e y clicadas */
		if(ControladorNavio.navioClicado != null && ControladorNavio.navioClicado.isPositioned == false){
			
			Singleton.getInstance().posicionarArma(ControladorNavio.navioClicado, matriz, x, y);
			ControladorNavio.navioClicado = null;
			
		}

	}

	public static void posicionarArmasJogador(int idJogador){

		/* cria uma instancia da tela de posicionamento de embarcacoes */
		if(idJogador==1){
			
			/* para o primeiro jogador e a adiciona a lista de Observers */
			instancia.listaObserver.add(TelaEmbarcacoes.getInstance(1));
			
		} else {
			
			/* para o segundo jogador e a adiciona a lista de Observers */
			instancia.listaObserver.add(TelaEmbarcacoes.getInstance(2));
			
		}
		
	}

	/* Fase de Ataques */
	public static void iniciarAtaques(){

		/* cria uma instancia da tela de campo de batalha, passando os 
		 * jogadores como parametro e a adiciona a lista de Observers */
		instancia.listaObserver.add(TelaCampoBatalha.getInstance(j1, j2));

	}

	/* Ataques */
	public static void atacar(Matriz[][] matriz, int x, int y){

		/* condicao garante que cada jogador ataque tres vezes 
		 * seguidas e depois a vez de atacar passe para o oponente */
		if(tiros>0){
			
			/* variavel acertou recebe retorno booleano de acerto fornecido 
			 * pela chamada do metodo atirar definido na classe de logica, onde 
			 * eh passada a matriz e as coordenadas x e y clicadas */
			acertou = instancia.atirar(matriz, x, y);

			/* se acertou o tiro, redesenha tela de campo de batalha atual e o 
			 * numero de embarcacoes do jogador oponente eh decrementado em um */ 
			if(acertou){
				
				TelaCampoBatalha.jogOponente.numEmbarcacoes--;
				System.out.println("FACADE: Blocos de embarcações restantes = " +TelaCampoBatalha.jogOponente.numEmbarcacoes);
				
				/* verifica se terminou jogo */
				if(TelaCampoBatalha.jogOponente.numEmbarcacoes==0){
					instancia.terminou = true;
				}
					
			}

			TelaCampoBatalha.getInstance(null, null).repaint();
			tiros--;
			
		} else {
			
			tiros = 3;
			/* chama metodo de alternar os tabuleiros */
			TelaCampoBatalha.getInstance(j1, j2).alternaTabuleiros();
			System.out.println("FACADE: Alternou jogador");
			
		}

		System.out.println("FACADE: Quantidade de tiros restantes = " +tiros);

		/* verifica se terminou o jogo ou se o jogo prossegue */
		if(instancia.terminou){
			
			/* cria uma instancia da tela final, passando o nome do vencedor */
			TelaFinal.getInstance(TelaCampoBatalha.jogVez.getNome());
		}
	}

	/* Salvamento do Jogo */
	public static void salvarJogo(TelaCampoBatalha tela){

		/* chama metodo de salvamento de jogo, passando 
		 * tela de campo de batalha com os dados a serem
		 * armazenados no arquivo de salvamento */
		Salvamento.salvarJogo(tela);

	}

}

