package facade;
import controller.*;
import model.*;
import view.*;

/*Classe de Fachada*/
public class Facade {
	private static Logica l;
	private static boolean acertou;
	public static Jogador j1;
	public static Jogador j2;
	
	/*Inicializador da Classe Fachada*/
	public static void inicializaFacade(){
		l = new Logica();
	}
	
	/*Iniciar Novo Jogo*/
	public static void novoJogo(){
		TelaJogadores.getInstance();
	}
	
	/*Carregar Jogo Salvo*/
	public static void carregarJogo(TelaCampoBatalha tela){
		Salvamento.carregarJogo(tela);
	}
	
	/*Criação de Jogadores*/
	public static void criarJogadores(String nomeJogador1, String nomeJogador2){
		j1 = new Jogador(nomeJogador1);
		j2 = new Jogador(nomeJogador2);
	}	

	/*Posicionamento de Armas*/
	public static void posicionarArmasJogador1(String nomeJogador1){
		TelaEmbarcacoes.getInstance1();	
		l.listaObserver.add(TelaEmbarcacoes.getInstance1());
	}
	
	public static void posicionarArmasJogador2(String nomeJogador2){
		TelaEmbarcacoes.getInstance2();	
		l.listaObserver.add(TelaEmbarcacoes.getInstance2());
	}
	
	public static void escolherPosicaoArma(Matriz[][] matriz, int x, int y){
		l.posicionarArma(ControladorNavio.navioClicado, matriz, x, y);
	}
	
	/*Fase de Ataques*/
	public static void iniciarAtaques(){
		TelaCampoBatalha.getInstance(j1, j2);
		l.listaObserver.add(TelaCampoBatalha.getInstance(j1, j2));
	}
	
	/*Ataques*/
	public static void atacar(Matriz[][] matriz, int x, int y){
		for(int i = 0; i < 3; i++){
			acertou = l.atirar(matriz, x, y);
			if(acertou){
				TelaCampoBatalha.getInstance(j1, j2).repaint();
				j1.numEmbarcacoes--;
			}
		}
		TelaCampoBatalha.getInstance(j1, j2).alternaTabuleiros();
		if(l.terminou){
			TelaFinal.getInstance(Logica.nomeVencedor);
		}
	}
	
	/*Salvamento do Jogo*/
	public static void salvarJogo(TelaCampoBatalha tela){
		Salvamento.salvarJogo(tela);
	}

}

