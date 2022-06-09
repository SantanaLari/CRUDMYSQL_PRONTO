package applicationCaravana;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CaravanaControl {
	
	private ObservableList<Caravana> caravanas = FXCollections.observableArrayList();
	private CaravanaDAOImpl dao = new CaravanaDAOImpl();
	private StringProperty codigo = new SimpleStringProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty capacidade = new SimpleStringProperty();
	private StringProperty preco = new SimpleStringProperty();
	private StringProperty uf = new SimpleStringProperty();
	
	public StringProperty codigoProperty() {
		return codigo;
	}
	
	public StringProperty nomeProperty() {
		return nome;
	}
	
	public StringProperty capacidadeProperty() {
		return capacidade;
	}
	
	public StringProperty precoProperty() {
		return preco;
	}
	
	public StringProperty ufProperty() {
		return uf;
	}
	
	public void salvar() {
		System.out.println("Codigo: " + codigo.get());
		System.out.println("Nome: " + nome.get());
		System.out.println("Capacidade: " + capacidade.get());
		System.out.println("Preco: " + preco.get());
		System.out.println("Uf: " + uf.get());
	
		Caravana c = new Caravana();
		c.setCodigo(codigo.get());
		c.setNome(nome.get());
		c.setCapacidade(capacidade.get());
		c.setPreco(preco.get());
		c.setUf(uf.get());
		dao.salvar(c);
	}
	
	public void listar() {
		List<Caravana> encontrados = dao.listar(nome.get());
		caravanas.clear();
		caravanas.addAll(encontrados);
	}
	
	public void excluir() {
		Caravana c = new Caravana();
		c.setCodigo(codigo.get());
		dao.excluir(c);
		listar();
	}
	
	public void atualizar() {
		Caravana c = new Caravana();
		c.setCodigo(codigo.get());
		c.setNome(nome.get());
		c.setCapacidade(capacidade.get());
		c.setPreco(preco.get());
		c.setUf(uf.get());
		dao.atualizar(c);
		listar();
	}
	
	
	public ObservableList<Caravana> getCaravana(){
		return caravanas;
	}
}