package model;

import java.io.*;
import java.util.*;

import javax.swing.*;

import view.TelaCampoBatalha;
import facade.Facade;

/* Classe de Negocio para salvamento do jogo */
public class Salvamento {
	private static JFileChooser escolherArquivo;	

	/* Salvamento de Jogo */
	@SuppressWarnings("static-access")
	public static void salvarJogo(TelaCampoBatalha tela){

		/* cria nova janela de selecao de arquivos, atribui a variavel 
		 * escolherArquivo e coleta resultado obtido nas acoes dentro da 
		 * janela de escolha de arquivo para salvamento escolherArquivo */
		File caminho = new File(new File("").getAbsolutePath()+"/saves");
		escolherArquivo = new JFileChooser();
		escolherArquivo.setCurrentDirectory(caminho);
		int resultado = escolherArquivo.showSaveDialog(null);

		/* verifica se, quando o arquivo selecionado ja existe, nao foi confirmada a escolha */
		if (escolherArquivo.getSelectedFile().exists() && JOptionPane.showConfirmDialog(null, "O arquivo já existe? Deseja sobrescreve-lo?") != JOptionPane.OK_OPTION) {
			return;
		}

		/* verifica se arquivo nao existe */
		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		} else {
			JOptionPane.showConfirmDialog(null, "Jogo salvo com sucesso!", "Batalha Naval - Concluído", JOptionPane.CLOSED_OPTION);
		}
		
		

		/* atribui arquivo selecionado a variável arq e cria uma variavel do tipo PrintWriter com valor null */
		File arq = escolherArquivo.getSelectedFile();
		PrintWriter arquivo = null;

		/* tenta abrir o arquivo e escrever nele as informações da tela de campo de batalha fornecida por parametro */
		try {

			arquivo = new PrintWriter(arq);
			
			arquivo.printf("%s", tela.jogVez.nome);
			arquivo.println();
			arquivo.printf("%s", tela.jogOponente.nome);
			arquivo.println();
			
			arquivo.println();
			
			arquivo.printf("%d", tela.jogVez.numEmbarcacoes);
			arquivo.println();
			arquivo.printf("%d", tela.jogOponente.numEmbarcacoes);
			arquivo.println();
			
			arquivo.println();

			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					arquivo.printf("%d ", tela.jogVez.matriz[i][j].tipoArma);
				}
				arquivo.println();
			}
			
			arquivo.println();
			
			for(int i = 0; i < 15; i++){
				for(int j = 0; j < 15; j++){
					arquivo.printf("%d ", tela.jogOponente.matriz[i][j].tipoArma);
				}	
				arquivo.println();
			}

		/* trata excecao do tipo IOException */
		} catch (IOException e) {

			JOptionPane.showConfirmDialog(null, "Erro no salvamento do jogo. Tente novamente!", "Batalha Naval - Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* fecha o arquivo */
		} finally {

			arquivo.close();

		}

	}

	/* Carregamento de Jogo */
	public static void carregarJogo(){
		String jog1, jog2;
		
		/* cria nova janela de selecao de arquivos, atribui a variavel 
		 * escolherArquivo e coleta resultado obtido nas acoes dentro da 
		 * janela de escolha de arquivo para carregamento escolherArquivo */		
		File caminho = new File(new File("").getAbsolutePath()+"/saves");
		escolherArquivo = new JFileChooser();
		escolherArquivo.setCurrentDirectory(caminho);
		int resultado = escolherArquivo.showOpenDialog(null);
		
		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		}

		/* atribui arquivo selecionado a variável arq e cria uma variavel do tipo Scanner com valor null */
		File arq = escolherArquivo.getSelectedFile();
		Scanner arquivo = null;

		/* tenta abrir o arquivo e carregar as informacoes dele e aplicar a tela de campo de batalha */
		try {

			arquivo = new Scanner(new FileReader(arq));	

			while(arquivo.hasNext()){
				jog1 = arquivo.nextLine();
				jog2 = arquivo.nextLine();
				
				Facade.criarJogadores(jog1, jog2);
				Facade.j1.setNumEmbarcacoes(arquivo.nextInt());
				Facade.j2.setNumEmbarcacoes(arquivo.nextInt());
				
				arquivo.nextLine();
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						Facade.j1.matriz[i][j].tipoArma = arquivo.nextInt();
					}
				}
				
				arquivo.nextLine();
				for(int i = 0; i < 15; i++){
					for(int j = 0; j < 15; j++){
						Facade.j2.matriz[i][j].tipoArma = arquivo.nextInt();
					}
				}
			}		

		/* trata excecao do tipo FileNotFoundException */
		} catch (FileNotFoundException e){

			JOptionPane.showConfirmDialog(null, "Arquivo de carregamento não encontrado. Tente novamente!", "Batalha Naval - Erro", JOptionPane.CLOSED_OPTION);
			carregarJogo();

		/* trata excecao do tipo IOException */
		} catch (@SuppressWarnings("hiding") IOException e){

			JOptionPane.showConfirmDialog(null, "Erro ao carregar jogo. Tente novamente!", "Batalha Naval - Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* trata excecao do tipo Exception */
		} catch (Exception e){

			JOptionPane.showConfirmDialog(null, "Arquivo de carregamento inválido. Tente novamente!", "Batalha Naval - Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* fecha o arquivo */
		} finally {

			arquivo.close();

		}
	}
}
