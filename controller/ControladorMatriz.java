package controller;

import java.awt.event.*;

import facade.Facade;
import model.Matriz;
import model.Singleton;
import view.TelaCampoBatalha;
import view.TelaEmbarcacoes;

/* Classe de Controle do tipo Matriz */
public class ControladorMatriz implements MouseListener{
	private Matriz[][] matrizVez;
	
	/* Construtor da Classe de Controle */
	public ControladorMatriz(Matriz[][] matrizControleJogadorVez, MouseEvent mouse){
		this.matrizVez = matrizControleJogadorVez;
	}
	
	public void escolherPosicaoArma(Matriz[][] matriz, int x, int y){

		/* chama m�todo de posicionamento de embarca��es definido na classe l�gica,
		 * passando o �ltimo navio clicado, a matriz e as coordenadas x e y clicadas */
		if(ControladorNavio.navioClicado != null && ControladorNavio.navioClicado.isPositioned == false){
			Singleton.getInstance().posicionarArma(ControladorNavio.navioClicado, matriz, x, y);
			ControladorNavio.navioClicado = null;
		}
		
	}
	
	/* Tratamentos de Eventos de Mouse */
	@Override
	public void mouseClicked(MouseEvent mouse) {
		
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
		
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
		
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
		int x = mouse.getX();
        int y = mouse.getY();
        System.out.println("CONTROLADORMATRIZ: Tabuleiro x: "+ x +" Tabuleiro y: "+ y);
        
		if(!TelaCampoBatalha.estaInstanciado() && TelaEmbarcacoes.getInstance(1).isEnabled()){			
			System.out.println("CONTROLADORMATRIZ: Posicionar navios do jogador 1");
			escolherPosicaoArma(matrizVez, mouse.getX(), mouse.getY());
		} 
		else 
		if(!TelaCampoBatalha.estaInstanciado() && TelaEmbarcacoes.getInstance(2).isEnabled()){
			System.out.println("CONTROLADORMATRIZ: Posicionar navios do jogador 2");
			escolherPosicaoArma(matrizVez, mouse.getX(), mouse.getY());
		} 
		else {
			Facade.atacar(TelaCampoBatalha.jogOponente.matriz, mouse.getX(), mouse.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
		
	}
}
