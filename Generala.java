package Ejercicio005.Ejercicio3.Generala;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Generala {
	final int CANT_DADOS = 5;
	
	// ESTA ES LA FUNCIÓN QUE HAY QUE IMPLEMENTAR
	// TAMBIÉN PUEDEN AGREGAR OTRAS FUNCIONES y/o CLASES
	// QUE NECESITEN PARA RESOLVER EL EJERCICIO DE LA
	// MANERA MÁS ORDENADA POSIBLE
	String jugada(String dados)
	{
		if(!esValido(dados)) {
			return "INVALIDO";
		}
		
		int [] da2 = new int [CANT_DADOS];
		da2 = convertirDados(dados);
		
		if (esGenerala(da2)) {
			return "GENERALA";
		}
		
		if (esPoker(da2)) {
			return "POKER";
		}
		
		if (esFull(da2)) {
			return "FULL";
		}
		
		if (esEscalera(da2)) {
			return "ESCALERA";
		}
		
		return "NADA";
	}
	
	// Ustedes pueden ignorar esto
	String[] jugadas(String[] losdados)
	{

		String[] ret = new String[losdados.length];
		int i = 0;
		
		for (String dados : losdados)
		{
			ret[i] = this.jugada(dados);
			i++;
		}
		
		return ret;
	}
	
	// Ustedes pueden ignorar esto
	static String[] processBatch(String fileName)
	throws Exception
	{
		Scanner sc = new Scanner(new File(fileName));
		List<String> lines = new ArrayList<String>();
		
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		
		return lines.toArray(new String[0]);
	}
	
	public static void main(String[] args)
	throws Exception
	{
		Generala g = new Generala();
		/* IGNORAR PORQUE ESTO NO SE VA A EJECUTAR PARA USTEDES */
		if (args.length > 0) {
			String[] jugadas = processBatch(args[0]);
			String resultados[] = g.jugadas(jugadas);
			
			for(String res : resultados) {
				System.out.println(res);
			}
			return;
		}
		// ESTO SI SE EJECUTA PARA USTEDES
		//generala
		System.out.println(g.jugada("22222"));
		System.out.println(g.jugada("33333"));
		System.out.println(g.jugada("66666"));
		//poker
		System.out.println(g.jugada("32222"));
		System.out.println(g.jugada("22622"));
		System.out.println(g.jugada("21222"));
		//full
		System.out.println(g.jugada("32232"));
		System.out.println(g.jugada("62622"));
		System.out.println(g.jugada("21221"));
		//escalera
		System.out.println(g.jugada("12345"));
		System.out.println(g.jugada("23456"));
		System.out.println(g.jugada("34561"));
		//nada
		System.out.println(g.jugada("11345"));
		System.out.println(g.jugada("23356"));
		System.out.println(g.jugada("34511"));
		//invalido
		System.out.println(g.jugada("12a45"));
		System.out.println(g.jugada("23 56"));
		System.out.println(g.jugada("3456b"));
	}
	
	public boolean esValido(String dados) {
		/* valida si los dados introducidos son correctos */
		final int TAM = dados.length();
		int [] vector = new int [TAM];

		if( TAM != CANT_DADOS ) { // validar que sean cinco dados.
			return false;
		}
		
		try {
			for(int i = 0; i < TAM; i++) {
				vector[i] = Integer.parseInt(dados.charAt(i) + ""); // validar que sean numerico
				
				if( !(vector[i] >= 1 && vector[i] <= 6) ) { // validar que los dados sean del 1 al 6
					return false;
				}
			}
		} catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean esGenerala(int [] dados) {
		/* validar si la jugada es generala */
		int valor = dados[0];
		
		for(int i = 1; i < CANT_DADOS; i++) {
			if(dados[i] != valor) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean esPoker(int [] dados) {
		/* validar si la jugada es poker */
		int [][] aux = new int[CANT_DADOS][2]; //columna 1: valor, columna: cantidad
		int tam = 0;
		boolean flag;
		
		for(int i = 0; i < CANT_DADOS; i++) {
			flag = true;
			
			for(int k = 0; k < tam; k++) {
				if(dados[i] == aux[k][0]) {
					aux[k][1] ++;
					flag = false;
					break;
				}
			}
			
			if(flag) { // nuevo numero
				aux[tam][0] = dados[i];
				aux[tam][1] = 1;
				tam++;
			}
		}
		
		if(tam != 2) { //solo debe haber dos valores posibles
			return false;
		}
		
		if( !(aux[0][1] == 4 || aux[1][1] == 4) ) { // si ninguno de los dos numeros se repite 4 veces
			return false;
		}
		
		return true;
	}
	
	public boolean esFull(int [] dados) {
		/* validar si la jugada es full */
		int [][] aux = new int[CANT_DADOS][2]; //columna 1: valor, columna: cantidad
		int tam = 0;
		boolean flag;
		
		for(int i = 0; i < CANT_DADOS; i++) {
			flag = true;
			
			for(int k = 0; k < tam; k++) {
				if(dados[i] == aux[k][0]) {
					aux[k][1] ++;
					flag = false;
					break;
				}
			}
			
			if(flag) { // nuevo numero
				aux[tam][0] = dados[i];
				aux[tam][1] = 1;
				tam++;
			}
		}
		
		if(tam != 2) { //solo debe haber dos valores posibles
			return false;
		}
		
		if( !(aux[0][1] == 3 || aux[1][1] == 3) ) { // si ninguno de los dos numeros se repite 3 veces
			return false;
		}
		
		return true;
	}
	
	public boolean esEscalera(int [] dados) {
		/* validar que la jugada sea escalera */
		for(int i = 1; i < (CANT_DADOS-1); i++) {
			if(dados[i] != (dados[i-1] + 1) ) {
				return false;
			}
		}
		
		// ultimo != penultimo + 1  OR  contrario(si el ultimo es 1 y el penultimo es 6)
		// si el ultimo dado sale 1
		if(dados[CANT_DADOS-1] == 1) {
			if( dados[CANT_DADOS-2] != 6 ) {
				return false;
			}
		} else {
			if( dados[CANT_DADOS-1] != dados[CANT_DADOS-2] + 1 ) {
				return false;
			}
		}
		
		return true;
	}
	
	public int[] convertirDados(String dados) {
		/* convierte el string en un vector numerico */
		int [] da2 = new int [dados.length()];
		
		
		for(int i = 0; i < dados.length(); i++) {
			da2[i] = Integer.parseInt(dados.charAt(i) + "");
		}
		
		return da2;
	}
}
