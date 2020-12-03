package testePackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicaoEntity {

	private Integer id;
	private Date dataInicio;
	private Date dataFim;
	private Double valor;

	private SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	public MedicaoEntity() {

	}

	public MedicaoEntity(Integer id, Date dataInicio, Date dataFim, Double valor) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.valor = valor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	

	public String toString() {
		return id + " - " + sf.format(dataInicio) + " - " + sf.format(dataFim) + " - " + valor + " kWh";
	}

	public List<MedicaoEntity> gerarLista(String path) {

		DecimalFormat df = new DecimalFormat("0.0000");
		df.setRoundingMode(RoundingMode.DOWN);
		FileReader fr = null;
		BufferedReader br = null;
		String result = "";
		List<MedicaoEntity> list = new ArrayList<>();
		MedicaoEntity me = null;
		double ar = 0.0;
		double lam = 0.0;
		double pc = 0.0;

		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);

			String line = br.readLine();

			// percorre o arquivo
			while (line != null) {
				int cont2 = 0;
				me = new MedicaoEntity();

				// percorre a string da vez
				for (int i = 0; i < line.length(); i++) {

					if (line.substring(i, (i + 1)).equals(";")) {
						int cont = 1;
						int cont1 = i + 1;

						// percorre a string a partir do momento que achar o ';'
						while (cont == 1) {

							result += line.substring(cont1, cont1 + 1);
							cont1++;

							if (line.substring(cont1, cont1 + 1).equals("#")) {
								cont = 0;
								cont2++;
							}
						}

						// cria o objeto de acordo com a sequencia da string
						switch (cont2) {
						case 1:
							me.setId(Integer.parseInt(result));
							break;
						case 2:
							me.setDataInicio(sf.parse(result));
							break;
						case 3:
							result = result.replaceAll(",", ".");
							ar = (Double.parseDouble(result));
							break;
						case 4:
							result = result.replaceAll(",", ".");
							lam = (Double.parseDouble(result));
							break;
						case 5:
							result = result.replaceAll(",", ".");
							pc = (Double.parseDouble(result));
							String resultado = df.format((ar + lam + pc));
							resultado = resultado.replaceAll(",", ".");
							me.setValor(Double.parseDouble(resultado));
							break;
						default:
							break;
						}

					}
					result = "";
				}
				line = br.readLine();
				list.add(me);
			}

		} catch (IOException e) {
			System.out.println("Error:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error ao gerar lista " + e.getMessage());
		}
		return list;
	}

}
