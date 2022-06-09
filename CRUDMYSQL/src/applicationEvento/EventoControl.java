package applicationEvento;

import javafx.collections.ObservableList;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class EventoControl {
	
	private ObservableList<Evento> eventos = FXCollections.observableArrayList();
	private EventoDAOImpl dao = new EventoDAOImpl();
	private StringProperty codigo = new SimpleStringProperty(); 
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty data = new SimpleStringProperty();
	private StringProperty horario = new SimpleStringProperty();
	private StringProperty endereco = new SimpleStringProperty();
	
	public StringProperty codigoProperty() {
		return codigo;
	}
	public StringProperty nomeProperty() {
		return nome;
	}
	public StringProperty dataProperty() {
		return data;
	}
	public StringProperty horarioProperty() {
		return horario;
	}
	public StringProperty enderecoProperty() {
		return endereco;
	}
	
	public void salvar() {
		Evento ev = new Evento();
		ev.setCodigo(codigo.get());
		ev.setNome(nome.get());
		ev.setData(data.get());
		ev.setHorario(horario.get());
		ev.setEndereco(endereco.get());
		dao.salvar(ev);
	//	eventos.add(ev);
	}
	
	public void apagar() {
		Evento ev = new Evento();
		ev.setCodigo(codigo.get());
		dao.apagar(ev);
		listar();
	}

	public void atualizar() {
		Evento ev = new Evento();
		ev.setCodigo(codigo.get());
		ev.setNome(nome.get());
		ev.setData(data.get());
		ev.setHorario(horario.get());
		ev.setEndereco(endereco.get());
		dao.atualizar(ev);
		listar();
	}
	
	public void listar() {
		List<Evento> encontrados = dao.listar(nome.get());
		eventos.clear();
		eventos.addAll(encontrados);
	}
	
	public ObservableList<Evento> getEvento(){
		return eventos;
	}
}
