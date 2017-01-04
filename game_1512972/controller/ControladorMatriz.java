package controller;

import java.awt.event.*;

import facade.Facade;
import model.Matriz;
import view.TelaCampoBatalha;
import view.TelaEmbarcacoes;

/* Classe de Controle do tipo Matriz */
public class ControladorMatriz implements MouseListener{
	private Matriz[][] matrizVez;

	/* Construtor da Classe de Controle */
	public ControladorMatriz(Matriz[][] matrizControleJogadorVez, MouseEvent mouse){
		this.matrizVez = matrizControleJogadorVez;
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

		/* verifica em qual tela ocorreu o evento */
		if(!TelaCampoBatalha.estaInstanciado() && TelaEmbarcacoes.getInstance(1).isEnabled()){

			System.out.println("CONTROLADORMATRIZ: Posicionar navios do jogador 1");

			/*chama metodo de posicionamento, definido na classe da fachada */
			Facade.escolherPosicaoArma(matrizVez, mouse.getX(), mouse.getY());

		} else if(!TelaCampoBatalha.estaInstanciado() && TelaEmbarcacoes.getInstance(2).isEnabled()){
			System.out.println("CONTROLADORMATRIZ: Posicionar navios do jogador 2");

			/*chama metodo de posicionamento, definido na classe da fachada */
			Facade.escolherPosicaoArma(matrizVez, mouse.getX(), mouse.getY());

		} else {

			/*chama metodo de ataque, definido na classe da fachada */
			Facade.atacar(TelaCampoBatalha.jogOponente.matriz, mouse.getX(), mouse.getY());

		}

	}

	@Override
	public void mouseReleased(MouseEvent mouse) {

	}
}
