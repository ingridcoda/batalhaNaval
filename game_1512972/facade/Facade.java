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

		/* inicializa uma instï¿½ncia de lï¿½gica a ser utilizada para o jogo */
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

		/* chama funï¿½ï¿½o de carregamento de jogo passando uma tela de campo de 
		 * batalha vazia a ser preenchida com os dados salvos anteriormente */
		Salvamento.carregarJogo();
		iniciarAtaques();
	}

	/* Criaï¿½ï¿½o de Jogadores */
	public void criarJogadores(String nomeJogador1, String nomeJogador2){
		/* inicializa duas instï¿½ncias de jogador
		 * com os nomes fornecidos por parï¿½metro */
		j1 = new Jogador(nomeJogador1);
		j2 = new Jogador(nomeJogador2);

	}

	/* Posicionamento de Armas */
	public void posicionarArmasJogador(int idJogador){
		instancia.listaObserver.add(new TelaEmbarcacoes(idJogador));
	}

	/* Fase de Ataques */
	public void iniciarAtaques(){

		/* cria uma instï¿½ncia da tela de campo de batalha, passando os 
		 * jogadores como parï¿½metro e a adiciona ï¿½ lista de Observers */
		j1.numEmbarcacoes = 38;
		j2.numEmbarcacoes = 38;
		Facade.jCorrente = null;
		
		instancia.listaObserver.clear();
		instancia.listaObserver.add(TelaCampoBatalha.getInstance(j1, j2));

	}

	/* Ataques */
	public void atacar(Matriz[][] matriz, int x, int y){
		
		/* repetiï¿½ï¿½o garante que cada jogador ataque trï¿½s vezes 
		 * seguidas e depois a vez de atacar passe para o oponente */
		if(tiros>0){
			/* variï¿½vel acertou recebe retorno booleano de acerto fornecido 
			 * pela chamada do mï¿½todo atirar definido na classe lï¿½gica, onde 
			 * ï¿½ passada a matriz e as coordenadas x e y clicadas */
			acertou = instancia.atirar(matriz, x, y);
			
			/* se acertou o tiro, redesenha tela de campo de batalha atual e o 
			 * nï¿½mero de embarcaï¿½ï¿½es do jogador oponente ï¿½ decrementado em um */ 
			if(acertou){
				TelaCampoBatalha.jogOponente.numEmbarcacoes--;
				System.out.println("FACADE: Blocos de embarcações restantes="+TelaCampoBatalha.jogOponente.numEmbarcacoes);
				if(TelaCampoBatalha.jogOponente.numEmbarcacoes==0)
					instancia.terminou = true;
			}
			
			TelaCampoBatalha.getInstance(null, null).repaint();
			tiros--;
		}
		else{
			tiros=3;
			/* chama mï¿½todo de alternar os tabuleiros */
			TelaCampoBatalha.getInstance(j1, j2).alternaTabuleiros();
			System.out.println("FACADE: Alternou jogador");
		}
		
		System.out.println("FACADE: Quantidade de tiros restantes="+tiros);
		
		/* verifica se terminou o jogo ou se o jogo prossegue */
		if(instancia.terminou)
			/* cria uma instï¿½ncia da tela final, passando o nome do vencedor */
			TelaFinal.getInstance(TelaCampoBatalha.jogVez.getNome());
	}

	/* Salvamento do Jogo */
	public static void salvarJogo(TelaCampoBatalha tela){
		
		/* chama mï¿½todo de salvamento de jogo, passando 
		 * tela de campo de batalha com os dados a serem
		 * armazenados no arquivo de salvamento */
		Salvamento.salvarJogo(tela);
		
	}

}

