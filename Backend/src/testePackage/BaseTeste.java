package testePackage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class BaseTeste {

	//Empresa_A
	/*
	 sala 1A:
	 3 ar condicionados / potência 2100w
	 20 lâmpadas / potência 40w
	 15 pcs / 180w
	 */
	static final double[] potenciaSala1A = {2100.00, 40.00, 180.00};
	/*
	 * posição 0,1 qtd min max de ar
	 * posição 2,3 qtd min max de lâmpadas
	 * posição 4,5 qtd min max de pcs
	 * */
	
	static final int[] qtdSala1A = {2, 4, 12 , 21, 10, 16};
	static final int[] qtdFimDeSemana1A = {1, 3, 5, 11, 4, 9};
	
	/*
	 sala 2A:
	 2 ar condicionados / potência 2000w
	 15 lâmpadas / potência 40w
	 10 pcs / 180w
	 */
	static final double[] potenciaSala2A = {2000.00, 40.00, 180.00};
	/*
	 * posição 0,1 qtd min max de ar
	 * posição 2,3 qtd min max de lâmpadas
	 * posição 4,5 qtd min max de pcs
	 * */
	static final int[] qtdSala2A = {1, 3, 8, 16, 6, 11};
	static final int[] qtdFimDeSemana2A = {1, 2, 5, 11, 3, 7};
	
	//Empresa_B
	static final double[] potenciaSala1B = {1450.00, 23.00, 200.00};
	/*
	 * posição 0,1 qtd min max de ar
	 * posição 2,3 qtd min max de lâmpadas
	 * posição 4,5 qtd min max de pcs
	 * */
	//sala 1B
	/*5 ar condicionados / potência 1450w
	 30 lâmpadas / potência 23w
	 20 pcs / 200w
	 * 
	 * */
	static final int[] qtdSala1B = {3, 6, 15 , 31, 12, 21};
	static final int[] qtdFimDeSemana1B = {1, 4, 8, 16, 5, 11};
	
	/*
	 sala 2B:
	 6 ar condicionados / potência 2000w
	 35 lâmpadas / potência 40w
	 25 pcs / 180w
	 */
	static final double[] potenciaSala2B = {1350.00, 15.00, 190.00};
	/*
	 * posição 0,1 qtd min max de ar
	 * posição 2,3 qtd min max de lâmpadas
	 * posição 4,5 qtd min max de pcs
	 * */
	static final int[] qtdSala2B = {3, 7, 16, 36, 15, 26};
	static final int[] qtdFimDeSemana2B = {1, 5, 10, 19, 6, 11};
	
	
	//Empresa_C
		static final double[] potenciaSala1C = {2100.00, 15.00, 170.00};
		/*
		 * posição 0,1 qtd min max de ar
		 * posição 2,3 qtd min max de lâmpadas
		 * posição 4,5 qtd min max de pcs
		 * */
		//sala 1C
		/*8 ar condicionados / potência 1450w
		 40 lâmpadas / potência 23w
		 30 pcs / 200w
		 * 
		 * */
		static final int[] qtdSala1C = {4, 9, 20 , 41, 20, 31};
		static final int[] qtdFimDeSemana1C = {1, 5, 10, 21, 10, 16};
		
		/*
		 sala 2C:
		 7 ar condicionados / potência 2000w
		 35 lâmpadas / potência 40w
		 25 pcs / 180w
		 */
		static final double[] potenciaSala2C = {2000.00, 30.00, 180.00};
		/*
		 * posição 0,1 qtd min max de ar
		 * posição 2,3 qtd min max de lâmpadas
		 * posição 4,5 qtd min max de pcs
		 * */
		static final int[] qtdSala2C = {3, 8, 12, 36, 15, 26};
		static final int[] qtdFimDeSemana2C = {1, 5, 8, 18, 8, 16};
	
	
	
	
	static final int dias = 10;
	static final int[] salas = {1, 2};
	
	/*posição 0,1 : horas fim de semana
	 * posição 2,3 : horas dia de semana
	 * posição 4,5 : hora extra
	 * 	*/
	static final int[] horas = {2,7,8,10,10,17};
	
	public static void main(String[] args) {
		

		Calendar cal = Calendar.getInstance();
		String path = "C:\\Users\\cabra\\OneDrive\\Área de Trabalho\\Empresa_E.txt";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))){
			
			calcularConsumo(cal,potenciaSala1C, qtdSala1C, salas[0],  bw, horas, qtdFimDeSemana1C);
			cal = Calendar.getInstance();
			calcularConsumo(cal,potenciaSala2C, qtdSala2C, salas[1], bw, horas, qtdFimDeSemana2C);
			
		}catch (IOException e) {
			e.getMessage();
		}
	}
	
static void calcularConsumo(Calendar cal,double[] potencia, int[] qtd, int sala, BufferedWriter bw, int[] horas, int[] qtdFimSem) throws IOException {
		
		DecimalFormat df = new DecimalFormat("0.0000");
		df.setRoundingMode(RoundingMode.DOWN);
		String line = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		for(int i = 0;i < dias;i++) {
			
			if(cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK) == 7) {
								
				line = "S;" + sala + "#d;" + sdf.format(cal.getTime()) + "#A;" + 
						df.format(potencia[0] * (gerarNumero(horas[0], horas[1]) + gerarMinutos()) / 1000.0 * gerarNumero(qtdFimSem[0], qtdFimSem[1])) +
						"#L;" + df.format(potencia[1] * (gerarNumero(horas[0], horas[1]) + gerarMinutos()) / 1000.0 * gerarNumero(qtdFimSem[2], qtdFimSem[3])) + "#E;" +
						df.format(potencia[2] * (gerarNumero(horas[0], horas[1]) + gerarMinutos()) / 1000.0 * gerarNumero(qtdFimSem[4], qtdFimSem[5])) + "#";
				
			}else if(cal.get(Calendar.DAY_OF_WEEK) == 4) {
				
				line = "S;" + sala + "#d;" + sdf.format(cal.getTime()) + "#A;" + 
						df.format(potencia[0] * (gerarNumero(horas[4], horas[5]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[0], qtd[1])) +
						"#L;" + df.format(potencia[1] * (gerarNumero(horas[4], horas[5]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[2], qtd[3])) + "#E;" +
						df.format(potencia[2] * (gerarNumero(horas[4], horas[5]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[4], qtd[5])) + "#";
				
			}else {
			
				line = "S;" + sala + "#d;" + sdf.format(cal.getTime()) + "#A;" + 
						df.format(potencia[0] * (gerarNumero(horas[2], horas[3]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[0], qtd[1])) +
						"#L;" + df.format(potencia[1] * (gerarNumero(horas[2], horas[3]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[2], qtd[3])) + "#E;" +
						df.format(potencia[2] * (gerarNumero(horas[2], horas[3]) + gerarMinutos()) / 1000.0 * gerarNumero(qtd[4], qtd[5])) + "#";
			}
			bw.write(line);
			bw.newLine();
			cal.add(Calendar.DAY_OF_MONTH, 1);
			
		}
		
	}
	
	static int gerarNumero(int min, int max) {
		int x = 0;
		while(x < min) {
			x = new Random().nextInt(max);
		}
		return x;
		
	}
	
	static double gerarMinutos() {
		double x = 0.0;
		while(x < 1.0) {
			x = (double) new Random().nextInt(60);
		}
		return x / 60;
	}

}
