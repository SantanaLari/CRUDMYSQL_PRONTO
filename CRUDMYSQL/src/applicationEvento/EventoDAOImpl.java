package applicationEvento;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDAOImpl implements EventoDAO{
	
	private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/crudEventos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "123456";
	private Connection con = null;
	
	EventoDAOImpl(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void salvar(Evento ev) {
		String sql = "INSERT INTO evento (codigo, nome, data, horario, endereco) ";
		sql += " VALUES('" + ev.getCodigo() + "', ";
		sql += " '" + ev.getNome() + "', ";
		sql += " '" + ev.getData() + "', ";
		sql += " '" + ev.getHorario() + "', ";
		sql += " '" + ev.getEndereco() + "')";
		System.out.println("Query preparada: " + sql);
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public List<Evento> listar(String nome) {
		String sql = "SELECT * FROM evento";
		List<Evento> eventos = new ArrayList<>();
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
            	Evento ev = new Evento();
            	ev.setCodigo(rs.getString("Codigo"));
            	ev.setNome(rs.getString("Nome"));
            	ev.setData(rs.getString("Data"));
            	ev.setHorario(rs.getString("Horario"));
            	ev.setEndereco(rs.getString("Endereco"));
            	eventos.add(ev);
            }
		} catch (SQLException e) {
            e.printStackTrace();
        }
		return eventos;
	}
	
	public void apagar(Evento ev) {
		String sql = "DELETE FROM evento WHERE codigo = "; 
		sql += "'" + ev.getCodigo() + "'";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	public void atualizar(Evento ev) {
		String sql = "UPDATE evento SET ";
		sql += " nome = '" + ev.getNome() + "', ";
		sql += " data = '" + ev.getData() + "', ";
	    sql += " horario = '" + ev.getHorario() + "', ";
	    sql += " endereco = '" + ev.getEndereco() + "' ";
	    sql += " WHERE codigo = ";
		sql += "'" + ev.getCodigo() + "'";
	    System.out.println("Query preparada: " + sql);
	    try {
			PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeUpdate();
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
}
