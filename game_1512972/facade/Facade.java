package facade;
import model.*;
import view.*;

/* Classe de Fachada */
public class Facade {
	private static Singleton instancia;
	private static Facade fInstancia;
	private static boolean acertou;
	public static int tiros=3;
	public static Jogador jCorrente;
	public Jogador j1;
	public Jogador j2;

	public static Facade getFacadeInstance(){
		if(fInstancia == null)
			fInstancia = new Facade();
		return fInstancia;
	}
	/* Inicializador da Classe Fachada */
	public static void inicializaFacade(){

		/* inicializa uma inst�ncia de l�gica a ser utilizada para o jogo */
		instancia = Singleton.getInstance();
		
		/* instancia a tela inicial */
		new TelaInicial();

	}
	
	public Jogador getJogador(int idJogador){
		if(idJogador == 1)
			return j1;
		else
			return j2;
	}
	
	public void setJogador(int idJogador, String nome){
		if(idJogador == 1)
			j1 = new Jogador(nome);
		else
			j2 = new Jogador(nome);
	}

	/* Iniciar Novo Jogo */
	public static void novoJogo(){

		/* cria uma instancia da tela de novo jogo */
		TelaJogadores.mostrarTela();

	}

	/* Carregar Jogo Salvo */
	public void carregarJogo(){

		/* chama fun��o de carregamento de jogo passando uma tela de campo de 
		 * batalha vazia a ser preenchida com os dados salvos anteriormente */
		Salvamento.carregarJogo();
		iniciarAtaques();
	}

	/* Cria��o de Jogadores */
	public void criarJogadores(String nomeJogador1, String nomeJogador2){
		/* inicializa duas inst�ncias de jogador
		 * com os nomes fornecidos por par�metro */
		j1 = new Jogador(nomeJogador1);
		j2 = new Jogador(nomeJogador2);

	}

	/* Posicionamento de Armas */
	public void posicionarArmasJogador(int idJogador){
		instancia.listaObserver.add(new TelaEmbarcacoes(idJogador));
	}

	/* Fase de Ataques */
	public void iniciarAtaques(){

		/* cria uma inst�ncia da tela de campo de batalha, passando os 
		 * jogadores como par�metro e a adiciona � lista de Observers */
		j1.numEmbarcacoes = 38;
		j2.numEmbarcacoes = 38;
		Facade.jCorrente = null;
		
		instancia.listaObserver.clear();
		instancia.listaObserver.add(TelaCampoBatalha.getInstance(j1, j2));

	}

	/* Ataques */
	public void atacar(Matriz[][] matriz, int x, int y){
		
		/* repeti��o garante que cada jogador ataque tr�s vezes 
		 * seguidas e depois a vez de atacar passe para o oponente */
		if(tiros>0){
			/* vari�vel acertou recebe retorno booleano de acerto fornecido 
			 * pela chamada do m�todo atirar definido na classe l�gica, onde 
			 * � passada a matriz e as coordenadas x e y clicadas */
			acertou = instancia.atirar(matriz, x, y);
			
			/* se acertou o tiro, redesenha tela de campo de batalha atual e o 
			 * n�mero de embarca��es do jogador oponente � decrementado em um */ 
			if(acertou){
				TelaCampoBatalha.jogOponente.numEmbarcacoes--;
				System.out.println("FACADE: Blocos de embarca��es restantes="+TelaCampoBatalha.jogOponente.numEmbarcacoes);
				if(TelaCampoBatalha.jogOponente.numEmbarcacoes==0)
					instancia.terminou = true;
			}
			
			TelaCampoBatalha.getInstance(null, null).repaint();
			tiros--;
		}
		else{
			tiros=3;
			/* chama m�todo de alternar os tabuleiros */
			TelaCampoBatalha.getInstance(j1, j2).alternaTabuleiros();
			System.out.println("FACADE: Alternou jogador");
		}
		
		System.out.println("FACADE: Quantidade de tiros restantes="+tiros);
		
		/* verifica se terminou o jogo ou se o jogo prossegue */
		if(instancia.terminou)
			/* cria uma inst�ncia da tela final, passando o nome do vencedor */
			TelaFinal.getInstance(TelaCampoBatalha.jogVez.getNome());
	}

	/* Salvamento do Jogo */
	public static void salvarJogo(TelaCampoBatalha tela){
		
		/* chama m�todo de salvamento de jogo, passando 
		 * tela de campo de batalha com os dados a serem
		 * armazenados no arquivo de salvamento */
		Salvamento.salvarJogo(tela);
		
	}

}

