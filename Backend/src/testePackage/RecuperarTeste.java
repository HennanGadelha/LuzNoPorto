package testePackage;

import java.util.ArrayList;

import java.util.List;




public class RecuperarTeste {

	public static void main(String[] args) {
		
		//String path = "C:\\Users\\cabra\\OneDrive\\Área de Trabalho\\Empresa_A.txt";
		
		List<MedicaoEntity> list = new ArrayList<>();
		//MedicaoEntity me = new MedicaoEntity();
		//list = me.gerarLista(path);
				
		MedicaoDao md = new MedicaoDao();
		//md.inserirDados(me.gerarLista(path));
		list = md.selecionar();
		
		
		for(MedicaoEntity lista:list) {
			System.out.println(lista);
		}
	}

}
