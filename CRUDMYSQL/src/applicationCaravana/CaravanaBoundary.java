package applicationCaravana;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CaravanaBoundary extends Application {

	private Label lblCodigo = new Label("Codigo: ");
	private Label lblNome = new Label("Nome: ");
	private Label lblCapacidade = new Label("Capacidade: ");
	private Label lblPreco = new Label("Preco: ");
	private Label lblUf = new Label("Uf: ");
	private TextField txtCodigo = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtCapacidade = new TextField();
	private TextField txtPreco = new TextField();
	private TextField txtUf = new TextField();
	private Button btnSalvar = new Button("Salvar");
	private Button btnListar = new Button("Listar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnAtualizar = new Button("Atualizar");
	private CaravanaControl control = new CaravanaControl();
	private TableView<Caravana> table = new TableView<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane principal = new BorderPane();
		GridPane grid = new GridPane(); 
		principal.setTop(grid); 
		principal.setCenter(table); 
		Scene scn = new Scene(principal, 400,400);  
	
		grid.add(lblCodigo, 0, 0);
		grid.add(lblNome, 0, 1);
		grid.add(lblCapacidade, 0, 2);
		grid.add(lblPreco, 0, 3);
		grid.add(lblUf, 0, 4);
		
		grid.add(txtCodigo, 1, 0);
		grid.add(txtNome, 1, 1);
		grid.add(txtCapacidade, 1, 2);
		grid.add(txtPreco, 1, 3);
		grid.add(txtUf, 1, 4);
		
		grid.add(btnSalvar, 0, 5);
		grid.add(btnListar, 1, 5);
		grid.add(btnExcluir, 2, 5);
		grid.add(btnAtualizar, 3, 5);
		
		Bindings.bindBidirectional(control.codigoProperty(), txtCodigo.textProperty());
		Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.capacidadeProperty(), txtCapacidade.textProperty());
        Bindings.bindBidirectional(control.precoProperty(), txtPreco.textProperty());
        Bindings.bindBidirectional(control.ufProperty(), txtUf.textProperty());
        
        
		TableColumn<Caravana, String> col1 = new TableColumn<>("Codigo");
		col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<Caravana, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Caravana, String> col3 = new TableColumn<>("Capacidade");
		col3.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
		
		TableColumn<Caravana, String> col4 = new TableColumn<>("Preco");
		col4.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Caravana, String> col5 = new TableColumn<>("Uf");
		col5.setCellValueFactory(new PropertyValueFactory<>("uf"));
		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		
		table.setItems(control.getCaravana());
		
		btnSalvar.setOnAction((e) -> {
			control.salvar();
		});
		
		btnListar.setOnAction((e) -> {
			control.listar();
		});	
		
		btnExcluir.setOnAction((e) -> {
			control.excluir();
		});
		
		btnAtualizar.setOnAction((e) -> {
			control.atualizar();
		});
		
		stage.setScene(scn); 
		stage.setTitle("Cadastrar caravana"); 
		stage.show(); 
	}
	
	public static void main(String[] args) {
		Application.launch(CaravanaBoundary.class, args);
	}
}