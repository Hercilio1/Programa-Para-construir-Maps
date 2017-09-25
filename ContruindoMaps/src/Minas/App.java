package Minas;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    
    private static float largura, altura, quantidade;
	//Conjunto de minas 
	private static ArrayList<String> minas = new ArrayList<>();
	private static ArrayList<String> areaFinal = new ArrayList<>();
	//Obejto auxiliador na montagem de Strings
	private static StringBuilder str;
	private static int determinador = 1;
	
	public static void main(String[] args) {
	    calculaArea("Caso010.txt", "Resultado010.svg");
	    minas.clear();
	    areaFinal.clear();
	    calculaArea("caso020.txt", "Resultado020.svg");
	    minas.clear();
	    areaFinal.clear();
	    determinador = 3;
		calculaArea("caso030.txt", "Resultado030.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 4;
		calculaArea("caso040.txt", "Resultado040.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 5;
		calculaArea("caso050.txt", "Resultado050.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 6;
		calculaArea("caso060.txt", "Resultado060.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 7;
		calculaArea("caso070.txt", "Resultado070.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 8;
		calculaArea("caso080.txt", "Resultado080.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 9;
		calculaArea("caso090.txt", "Resultado090.svg");
		minas.clear();
	    areaFinal.clear();
	    determinador = 10;
		calculaArea("caso100.txt", "Resultado100.svg");
	}
    
    public static void load(String arquivo) {

		Path path = Paths.get(arquivo);

		try (Scanner sc = new Scanner(Files.newBufferedReader(path, Charset.forName("utf8")))) {

			largura = divisor(Integer.parseInt(sc.next()));
			altura = divisor(Integer.parseInt(sc.next()));
			quantidade = Integer.parseInt(sc.next());
			System.out.println("Largura Total: " + largura + " \nAltura Total: " + altura + " \nQuantidade de Minas:"
					+ quantidade);
			int i = 0;
			while (i < quantidade) {
				sc.hasNext();
				float x = divisor(Integer.parseInt(sc.next()));
				float y = divisor(Integer.parseInt(sc.next()));
				str = new StringBuilder();
				str.append("<rect x='");
				str.append(x);
				str.append("' y='");
				str.append(y);
				str.append("'");
				minas.add(str.toString());
				i++;

				// System.out.println(x + " " + y);
			}
			while (sc.hasNext()) {
				float x = divisor(Integer.parseInt(sc.next()));
				float y = divisor(Integer.parseInt(sc.next()));
				sc.hasNext();
				float x2 = divisor(Integer.parseInt(sc.next()));
				float y2 = divisor(Integer.parseInt(sc.next()));
				str = new StringBuilder();
				str.append("<line x1=\""+x+"\" y1=\"" + y + "\" x2=\"" + x2 + "\" y2=\""+ y2 + "\" \n" +
						"style=\"fill:black; stroke-width:" + (0.0025 * altura) + "\"/>");
				areaFinal.add(str.toString());
			}
			
		} catch (IOException e) {
			System.out.println("FALHOU");
			e.printStackTrace();
		} catch (Throwable e1) {
			System.out.println("A app apresentou o seguinte erro:");
			e1.printStackTrace();
		}

	}
    
    public static float divisor(int numero) {
    	return (float) numero / 100;
    }


    public static void writer(String arquivo2) {
        Path path1 = Paths.get(arquivo2); 
        // defaultCharset retorna a codificação padrão de 
        // textos (usualmente UTF-8) 
        try (PrintWriter writer = new PrintWriter (
        Files.newBufferedWriter(path1, Charset.defaultCharset()))) {
            writer.println("<svg xmlns='http://www.w3.org/2000/svg' width='20cm' height='20cm' viewBox='0 0 " 
                    + altura + " " + largura + "'> "); 
            writer.println("<g style='stroke-width:1; stroke:black'>");
            
            writer.println("<line x1=\"0\" y1=\"0\" x2=\"" + largura + "\" y2=\"0\"\r\n" + 
            		"style=\"fill:black; stroke-width:" + 0.01 * altura + "\"/>");
            writer.println("<line x1=\"0\" y1=\"0\" x2=\"0\" y2=\"" + altura + "\"\r\n" + 
            		"style=\"fill:black; stroke-width:" + 0.01 * altura + "\"/>");
            writer.println("<line x1=\"0\" y1=\"" + altura + "\" x2=\"" + largura + "\" y2=\"" + altura + "\"\r\n" + 
            		"style=\"fill:black; stroke-width:" + 0.01 * altura + "\"/>");
            writer.println("<line x1=\"" + largura + "\" y1=\"0\" x2=\"" + largura + "\" y2=\"" + altura + "\"\r\n" + 
            		"style=\"fill:black; stroke-width:" + 0.01 * altura + "\"/>");
            
            
            for(int i=0; i<minas.size(); i++) {
                writer.println(minas.get(i) + " width='" + 0.0001 * largura * determinador + "' height='" + 0.0001 * altura * determinador + "' style='fill:black'/>");
            }
            
            for(int i=0; i<areaFinal.size(); i++) {
            	writer.println(areaFinal.get(i));
            }
            
            writer.println("</g>\n</svg>"); 
        } catch (IOException e) {
            System.err.format("Erro de E/S: %s%n", e); 
        }

    }
    
    
	/**
	 * Metodo que a partir de um caminho para um arquivo .txt
	 * chama load() para ler os dados, calcula a maior area livre de 
	 * minas e imprime os resultados na tela
	 * @param arquivo
	 */
	public static void calculaArea(String arquivo, String arquivo2) {
		load(arquivo);
		writer(arquivo2);
		System.out.println("Finish");
		
	}
}

