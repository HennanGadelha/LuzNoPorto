package testePackage;


import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;


public class MedicaoDao {

	private Connection con;
	
	//cria conexão com o banco
	public Connection gerarConexao() {
		 con = null;
		try {
			//jdbc:postgresql://tdevcgen.macieiras.com.br:5432/unicap_bd_sin", "aluno_bd_sin", "aluno_bd_sin
			con = DriverManager.getConnection("jdbc:postgresql://tdevcgen.macieiras.com.br:5432/unicap_bd_sin", "aluno_bd_sin", "aluno_bd_sin");
			 
		} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}
	
	//inseri valores na tabela medicao1
	public void inserirDados(List<MedicaoEntity> list) {
				
		int id = selecionar().size();
		con = gerarConexao();
		Calendar cal = Calendar.getInstance();
		
		try{
			String sql = "INSERT INTO public.medicao1(id_medicao,valor,data_inicio,data_fim) "
					+ "VALUES(?,?,?,?)";
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			
			for(MedicaoEntity lista : list) {
				id++;
				prepareStatement.setInt( 1, id);
				prepareStatement.setDouble(2, lista.getValor());
				prepareStatement.setTimestamp(3, new java.sql.Timestamp(lista.getDataInicio().getTime()));
				cal.setTime(lista.getDataInicio());
				cal.add(Calendar.HOUR_OF_DAY, 1);
				Date d = (Date) cal.getTime();
				prepareStatement.setTimestamp(4, new java.sql.Timestamp(d.getTime()));
				
				prepareStatement.execute();
			}
			
				
				prepareStatement.close();
				con.close();
				
		}catch (SQLException e) {
			System.out.println("Connection failure.");
            e.printStackTrace();
		
		}
	}
	
	//busca os registros na tabela medicao1
	public List<MedicaoEntity> selecionar(){
		
		con = gerarConexao();
		ResultSet rs = null;
		List<MedicaoEntity> list = new ArrayList<>();
		
		try {
				
			String sql = "SELECT * FROM public.medicao1";
			PreparedStatement prepareStatement = con.prepareStatement(sql);
			rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				list.add(new MedicaoEntity(rs.getInt("id_medicao"),rs.getTimestamp("data_inicio"),rs.getTimestamp("data_fim"),rs.getDouble("valor")));
			}
			con.close();
			rs.close();
			
		}catch(SQLException e) {
			System.out.println("Connection failure.");
            e.printStackTrace();
		
		}
		return list;
		

	}
}
