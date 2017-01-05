package model;

import java.io.*;
import java.util.*;

import javax.swing.*;

import view.TelaCampoBatalha;
import facade.Facade;

/* Classe de Negócio para salvamento do jogo */
public class Salvamento {
	private static JFileChooser escolherArquivo;	


	/* Salvamento de Jogo */
	@SuppressWarnings("static-access")
	public static void salvarJogo(TelaCampoBatalha tela){

		/* cria nova janela de seleção de arquivos, atribui à variável 
		 * escolherArquivo e coleta resultado obtido nas ações dentro da 
		 * janela de escolha de arquivo para salvamento escolherArquivo */
		File caminho = new File(new File("").getAbsolutePath()+"/saves");
		escolherArquivo = new JFileChooser();
		escolherArquivo.setCurrentDirectory(caminho);
		int resultado = escolherArquivo.showSaveDialog(null);

		/* verifica se, quando o arquivo selecionado já existe, não foi confirmada a escolha */
		if (escolherArquivo.getSelectedFile().exists() && JOptionPane.showConfirmDialog(null, "O arquivo já existe? Deseja sobrescreve-lo?") != JOptionPane.OK_OPTION) {
			return;
		}

		/* verifica se arquivo não existe */
		if (resultado != JFileChooser.APPROVE_OPTION) {
			return;
		}

		/* atribui arquivo selecionado à variável arq e cria uma variável do tipo PrintWriter com valor null */
		File arq = escolherArquivo.getSelectedFile();
		PrintWriter arquivo = null;

		/* tenta abrir o arquivo e escrever nele as informações da tela de campo de batalha fornecida por parâmetro */
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

		/* trata exceção do tipo IOException */
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
		/* cria nova janela de seleção de arquivos, atribui à variável 
		 * escolherArquivo e coleta resultado obtido nas ações dentro da 
		 * janela de escolha de arquivo para carregamento escolherArquivo */
		
		File caminho = new File(new File("").getAbsolutePath()+"/saves");
		escolherArquivo = new JFileChooser();
		escolherArquivo.setCurrentDirectory(caminho);
		int resultado = escolherArquivo.showOpenDialog(null);

		/* verifica se arquivo não existe */
		if (resultado != JFileChooser.APPROVE_OPTION) {
			//TelaInicial.getInstance();
		} 

		/* atribui arquivo selecionado à variável arq e cria uma variável do tipo Scanner com valor null */
		File arq = escolherArquivo.getSelectedFile();
		Scanner arquivo = null;

		/* tenta abrir o arquivo e carregar as informações dele e aplicar à tela de campo de batalha fornecida por parâmetro */
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

		/* trata exceção do tipo FileNotFoundException */
		} catch (FileNotFoundException e){

			JOptionPane.showConfirmDialog(null, "Arquivo não encontrado. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			carregarJogo();

		/* trata exceção do tipo IOException */
		} catch (@SuppressWarnings("hiding") IOException e){

			JOptionPane.showConfirmDialog(null, "Erro ao carregar arquivo. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* trata exceção do tipo Exception */
		} catch (Exception e){

			JOptionPane.showConfirmDialog(null, "Arquivo inválido. Tente novamente!", "Erro", JOptionPane.CLOSED_OPTION);
			System.exit(1);

		/* fecha o arquivo */
		} finally {

			arquivo.close();

		}
	}
}
