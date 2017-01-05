package model;

import java.io.*;
import java.util.*;

import javax.swing.*;

import view.TelaCampoBatalha;
import facade.Facade;

/* Classe de Neg�cio para salvamento do jogo */
public class Salvamento {
	private static JFileChooser escolherArquivo;	


	/* Salvamento de Jogo */
	@SuppressWarnings("static-access")
	public static void salvarJogo(TelaCampoBatalha tela){

		/* cria nova janela de sele��o de arquivos, atribui � vari�vel 
		 * escolherArquivo e coleta resultado obtido nas a��es dentro da 
		 * janela de escolha de arquivo para salvamento escolherArquivo */
		File caminho = new File(new File("").getAbsolutePath()+"/saves");
		escolherArquivo = new JFileChooser();
		escolherArquivo.setCurrentDirectory(caminho);
		int resultado = escolherArquivo.showSaveDialog(null);

		/* verifica se, quando o arquivo selecionado j� existe, n�o foi confirmada a escolha */
		if (escolherArquivo.getSelectedFile().exists() && JOptionPane.showConfirmDialog(null, "O arquivo j� existe? Deseja sobrescreve-lo?") != JOptionPane.OK_OPTION) {
			return;
		}

		/* verifica se arquivo n�o existe */
		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		}

		/* atribui arquivo selecionado � vari�vel arq e cria uma vari�vel do tipo PrintWriter com valor null */
		File arq = escolherArquivo.getSelectedFile();
		PrintWriter arquivo = null;

		/* tenta abrir o arquivo e escrever nele as informa��es da tela de campo de batalha fornecida por par�metro */
		try {

			arquivo = new PrintWriter(arq);
			
			arquivo.printf("%s", tela.jogVez.nome);
			arquivo.println("");
			arquivo.printf("%s", tela.jogOponente.nome);
			arquivo.println("");
			arquivo.println("");
			
			arquivo.printf("%d", Facade.tiros);
			arquivo.println("");
			arquivo.println("");
			
			arquivo.printf("%d", tela.jogVez.numEmbarcacoes);
			arquivo.println("");
			arquivo.printf("%d", tela.jogOponente.numEmbarcacoes);
			arquivo.println("");
			arquivo.println("");

			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					arquivo.printf("%d ", tela.jogVez.matriz[i][j].tipoArma);
				}
				arquivo.println("");
			}
			arquivo.println("");
			
			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					arquivo.printf("%d ", tela.jogOponente.matriz[i][j].tipoArma);
				}	
				arquivo.println("");
			}

		/* trata exce��o do tipo IOException */
		} catch (IOException e) {

			JOptionPane.showConfirmDialog(null, "Problema ao manipular arquivo. Tente novamente!");
			System.exit(1);

		/* fecha o arquivo */
		} finally {

			arquivo.close();

		}

	}

	/* Carregamento de Jogo */
	public static void carregarJogo(){
		String jog1, jog2;
		/* cria nova janela de sele��o de arquivos, atribui � vari�vel 
		 * escolherArquivo e coleta resultado obtido nas a��es dentro da 
		 * janela de escolha de arquivo para carregamento escolherArquivo */
		
		File caminho = new File(new File("").getAbsolutePath()+"/saves");
		escolherArquivo = new JFileChooser();
		escolherArquivo.setCurrentDirectory(caminho);
		int resultado = escolherArquivo.showOpenDialog(null);

		/* verifica se arquivo n�o existe */
		if (resultado != JFileChooser.APPROVE_OPTION) {
			//TelaInicial.getInstance();
		} 

		/* atribui arquivo selecionado � vari�vel arq e cria uma vari�vel do tipo Scanner com valor null */
		File arq = escolherArquivo.getSelectedFile();
		Scanner arquivo = null;

		/* tenta abrir o arquivo e carregar as informa��es dele e aplicar � tela de campo de batalha fornecida por par�metro */
		try {

			arquivo = new Scanner(new FileReader(arq));	

			while(arquivo.hasNext()){
				jog1 = arquivo.nextLine();
				jog2 = arquivo.nextLine();
				
				Facade.getFacadeInstance().criarJogadores(jog1, jog2);
				
				Facade.tiros = arquivo.nextInt();
				
				Facade.getFacadeInstance().j1.setNumEmbarcacoes(arquivo.nextInt());
				Facade.getFacadeInstance().j2.setNumEmbarcacoes(arquivo.nextInt());
				
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						Facade.getFacadeInstance().j1.matriz[i][j].tipoArma = arquivo.nextInt();
					}
				}
				
				arquivo.nextLine();
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						Facade.getFacadeInstance().j2.matriz[i][j].tipoArma = arquivo.nextInt();
					}
				}
			}		

		/* trata exce��o do tipo FileNotFoundException */
		} catch (FileNotFoundException e){

			JOptionPane.showConfirmDialog(null, "Arquivo n�o encontrado. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			carregarJogo();

		/* trata exce��o do tipo IOException */
		} catch (@SuppressWarnings("hiding") IOException e){

			JOptionPane.showConfirmDialog(null, "Erro ao carregar arquivo. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* trata exce��o do tipo Exception */
		} catch (Exception e){

			JOptionPane.showConfirmDialog(null, "Arquivo inv�lido. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* fecha o arquivo */
		} finally {

			arquivo.close();

		}
	}
}
