package model;

import java.io.*;
import java.util.*;

import javax.swing.*;

import view.TelaCampoBatalha;
import view.TelaInicial;

/* Classe de Neg�cio para salvamento do jogo */
public class Salvamento {
	private static JFileChooser escolherArquivo;	


	/* Salvamento de Jogo */
	@SuppressWarnings("static-access")
	public static void salvarJogo(TelaCampoBatalha tela){

		/* cria nova janela de sele��o de arquivos, atribui � vari�vel 
		 * escolherArquivo e coleta resultado obtido nas a��es dentro da 
		 * janela de escolha de arquivo para salvamento escolherArquivo */
		escolherArquivo = new JFileChooser();
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
			arquivo.printf("%d\n", tela.jogVez.numEmbarcacoes);
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
	@SuppressWarnings("static-access")
	public static void carregarJogo(TelaCampoBatalha tela){

		/* cria nova janela de sele��o de arquivos, atribui � vari�vel 
		 * escolherArquivo e coleta resultado obtido nas a��es dentro da 
		 * janela de escolha de arquivo para carregamento escolherArquivo */
		escolherArquivo = new JFileChooser();
		int resultado = escolherArquivo.showOpenDialog(null);

		/* verifica se arquivo n�o existe */
		if (resultado != JFileChooser.APPROVE_OPTION) {
			TelaInicial.getInstance();
		} 

		/* atribui arquivo selecionado � vari�vel arq e cria uma vari�vel do tipo Scanner com valor null */
		File arq = escolherArquivo.getSelectedFile();
		Scanner arquivo = null;

		/* tenta abrir o arquivo e carregar as informa��es dele e aplicar � tela de campo de batalha fornecida por par�metro */
		try {

			arquivo = new Scanner(new FileReader(arq));	

			while(arquivo.hasNext()){
				//System.out.println("Valor atual num embarcacoes jogador vez: " +tela.jogVez.numEmbarcacoes);
				tela.jogVez.numEmbarcacoes = arquivo.nextInt();
				//System.out.println("Valor alterado num embarcacoes jogador vez: " +tela.jogVez.numEmbarcacoes);
				//System.out.println("Valor atual num embarcacoes jogador oponente: " +tela.jogOponente.numEmbarcacoes);
				tela.jogOponente.numEmbarcacoes = arquivo.nextInt();
				//System.out.println("Valor alterado num embarcacoes jogador oponente: " +tela.jogOponente.numEmbarcacoes);
				//System.out.println("Valor atual nome jogador vez: " +tela.jogVez.nome);
				System.out.println(arquivo.next());
				tela.jogVez.nome = arquivo.next();
				//System.out.println("Valor alterado nome jogador vez: " +tela.jogVez.nome);

				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						//System.out.println("Valor atual matriz jogador vez posicao i j: " +tela.jogVez.matriz[i][j].tipoArma);
////////EXCE��O AQUI	tela.jogVez.matriz[i][j].tipoArma = arquivo.nextInt();
						//System.out.println("Valor alterado matriz jogador vez posicao i j: " +tela.jogVez.matriz[i][j].tipoArma);
					}
				}
				//System.out.println("Valor atual nome jogador oponente: " +tela.jogOponente.nome);
				System.out.println(arquivo.next());
				tela.jogOponente.nome = arquivo.next();
				//System.out.println("Valor alterado nome jogador oponente: " +tela.jogOponente.nome);
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						//System.out.println("Valor atual matriz jogador oponente posicao i j: " +tela.jogOponente.matriz[i][j].tipoArma);
						tela.jogOponente.matriz[i][j].tipoArma = arquivo.nextInt(); 
						//System.out.println("Valor alterado matriz jogador oponente posicao i j: " +tela.jogVez.matriz[i][j].tipoArma);
					}
				}
			}		

		/* trata exce��o do tipo FileNotFoundException */
		} catch (FileNotFoundException e){

			JOptionPane.showConfirmDialog(null, "Arquivo n�o encontrado. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			carregarJogo(tela);

		/* trata exce��o do tipo IOException */
		} catch (IOException e){

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
