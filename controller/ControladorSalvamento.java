package controller;
import java.awt.event.*;

import view.TelaCampoBatalha;
import facade.Facade;

public class ControladorSalvamento implements ActionListener {

	TelaCampoBatalha tela;
	/*Construtor da Classe de Controle*/
	public ControladorSalvamento(TelaCampoBatalha tela){
		this.tela = tela;
	}
	/*Tratamento de Eventos de Botão*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == TelaCampoBatalha.btnSave){
			Facade.salvarJogo(tela);
		} else {
			Facade.carregarJogo(tela);
		}
		
	}

}
