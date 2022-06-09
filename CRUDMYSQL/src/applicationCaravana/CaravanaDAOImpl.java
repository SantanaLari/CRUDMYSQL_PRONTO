package applicationCaravana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CaravanaDAOImpl implements CaravanaDAO{

	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/crudEventos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "123456";
	private Connection con = null;
	
	CaravanaDAOImpl(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void salvar(Caravana c) {
		String sql = "INSERT INTO caravana (codigo, nome, capacidade, preco, uf) ";
		sql += " VALUES ('" + c.getCodigo() + "', ";
		sql += " '" + c.getNome() + "',";
		sql += " '" + c.getCapacidade() + "',";
		sql += " '" + c.getPreco() + "',";
		sql += " '" + c.getUf() + "')";
		System.out.println("Query preparada: " + sql);
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<Caravana> listar(String nome) {
		String sql = "SELECT * FROM caravana";
		List<Caravana> caravanas = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	Caravana c = new Caravana();
            	c.setCodigo(rs.getString("Codigo"));
            	c.setNome(rs.getString("Nome"));
            	c.setCapacidade(rs.getString("Capacidade"));
            	c.setPreco(rs.getString("Preco"));
            	c.setUf(rs.getString("Uf"));
            	caravanas.add(c);
            }
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return caravanas;
	}
	
	public void excluir(Caravana c) {
		String sql = "DELETE FROM caravana WHERE codigo = ";
		sql += "'" + c.getCodigo() + "'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public void atualizar(Caravana c) {
		String sql = "UPDATE caravana SET ";
		sql += " nome = '" + c.getNome() + "', ";
		sql += " capacidade = '" + c.getCapacidade() + "', ";
		sql += " preco = '" + c.getPreco() + "', ";
		sql += " uf = '" + c.getUf() + "' ";
		sql += " WHERE codigo = ";
		sql += "'" + c.getCodigo() + "'";
		System.out.println("Query preparada: " + sql);
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

}