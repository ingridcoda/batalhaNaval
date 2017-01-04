package controller;
import java.awt.event.*;

import view.TelaCampoBatalha;
import facade.Facade;


/* Classe de Controle do Salvamento */
public class ControladorSalvamento implements ActionListener {

	public TelaCampoBatalha tela;
	
	/* Construtor da Classe de Controle */
	public ControladorSalvamento(TelaCampoBatalha tela){
		this.tela = tela;
	}
	
	/* Tratamento de Eventos de Botao */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* verifica qual botao foi clicado */
		if(e.getSource() == TelaCampoBatalha.btnSave){
			
			/* chama metodo de salvamento de jogo, definido na classe de fachada */
			Facade.salvarJogo(tela);
			
		} else {
			
			/* chama metodo de salvamento de jogo, definido na classe de fachada */
			Facade.carregarJogo();
		}
		
	}

}
