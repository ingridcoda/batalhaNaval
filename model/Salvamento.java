package model;

import java.io.*;
import java.util.*;

import javax.swing.*;

import view.TelaCampoBatalha;
import view.TelaInicial;

/*Classe de Negócio para salvamento do jogo*/
public class Salvamento {
	private static JFileChooser escolherArquivo;	


	/*Salvamento de Jogo*/
	public static void salvarJogo(TelaCampoBatalha tela){
		escolherArquivo = new JFileChooser();
		int resultado = escolherArquivo.showSaveDialog(null);

		if (escolherArquivo.getSelectedFile().exists() && JOptionPane.showConfirmDialog(null, "O arquivo já existe? Deseja sobrescreve-lo?") != JOptionPane.OK_OPTION) {
			return;
		}

		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File arq = escolherArquivo.getSelectedFile();
		PrintWriter arquivo = null;

		try {
			
			arquivo = new PrintWriter(arq);
			arquivo.printf("%d\n", TelaCampoBatalha.jogVez.numEmbarcacoes);
			arquivo.printf("%d\n", tela.jogOponente.numEmbarcacoes);
			arquivo.printf("%s\n", tela.jogVez.nome);

			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					arquivo.printf("%d ", tela.jogVez.matriz[i][j].tipoArma);
				}
				arquivo.println("");
			}

			arquivo.printf("%s\n", tela.jogOponente.nome);

			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					arquivo.printf("%d ", tela.jogOponente.matriz[i][j].tipoArma);
				}	
				arquivo.println("");
			}

		} catch (IOException e) {

			JOptionPane.showConfirmDialog(null, "Problema ao manipular arquivo. Tente novamente!");
			System.exit(1);

		} finally {

			arquivo.close();

		}
	}

	/*Carregamento de Jogo*/
	public static void carregarJogo(TelaCampoBatalha tela){

		escolherArquivo = new JFileChooser();
		int resultado = escolherArquivo.showOpenDialog(null);

		if (resultado != JFileChooser.APPROVE_OPTION) {
			TelaInicial.getInstance();
		} 
		
		File arq = escolherArquivo.getSelectedFile();
		Scanner arquivo = null;

		try {

			arquivo = new Scanner(new FileReader(arq));	

			while(arquivo.hasNext()){
				tela.jogVez.numEmbarcacoes = arquivo.nextInt();
				tela.jogOponente.numEmbarcacoes = arquivo.nextInt();
				tela.jogVez.nome = arquivo.nextLine();

				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						tela.jogVez.matriz[i][j].tipoArma = arquivo.nextInt();
					}
				}
				
				tela.jogOponente.nome = arquivo.nextLine();

				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						tela.jogOponente.matriz[i][j].tipoArma = arquivo.nextInt();
					}
				}
			}		

		} catch (FileNotFoundException e){

			JOptionPane.showConfirmDialog(null, "Arquivo não encontrado. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			//System.out.println("Arquivo não encontrado. Tente novamente!");
			carregarJogo(tela);

		} catch (IOException e){

			JOptionPane.showConfirmDialog(null, "Erro ao carregar arquivo. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			//System.out.println("Erro ao carregar arquivo. Tente novamente!");
			System.exit(1);

		} catch (Exception e){
			
			JOptionPane.showConfirmDialog(null, "Arquivo inválido. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			//System.out.println("Arquivo inválido. Tente novamente!");
			System.exit(1);
			
		} finally {
			
			arquivo.close();

		}
	}

}
