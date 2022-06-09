package applicationEvento;

import applicationCaravana.Caravana;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BoudaryEvento extends Application{

	private Label lblCodigo = new Label("Codigo: ");
	private Label lblNome = new Label("Nome: ");
	private Label lblData = new Label("Data: ");
	private Label lblHorario = new Label("Horario: ");
	private Label lblEndereco = new Label("Endereço: ");
	private TextField txtCodigo = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtData = new TextField();
	private TextField txtHorario = new TextField();
	private TextField txtEndereco = new TextField();
	private EventoControl control = new EventoControl();
	private Button btnSalvar = new Button("Salvar");
	private Button btnListar = new Button("Listar");
	private Button btnExcluir = new Button("Excluir");
	private Button btnAtualizar = new Button("Atualizar");
	private TableView<Evento> table = new TableView<>();
	
	@Override
	public void start(Stage stage) throws Exception {
		BorderPane principal = new BorderPane();
		GridPane grid = new GridPane();
		principal.setTop(grid);
		principal.setCenter(table);
		Scene scn = new Scene(principal,400,400);
		
		grid.add(lblCodigo, 0,0);
		grid.add(lblNome, 0, 1);
		grid.add(lblData, 0, 2);
		grid.add(lblHorario, 0, 3);
		grid.add(lblEndereco, 0, 4);
		
		grid.add(txtCodigo, 1, 0);
		grid.add(txtNome, 1, 1);
		grid.add(txtData, 1, 2);
		grid.add(txtHorario, 1, 3);
		grid.add(txtEndereco, 1, 4);
		
		grid.add(btnSalvar, 0, 5);
		grid.add(btnListar, 1, 5);
		grid.add(btnExcluir, 2, 5);
		grid.add(btnAtualizar, 3, 5);
		
		
		Bindings.bindBidirectional(control.codigoProperty(), txtCodigo.textProperty());
		Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
        Bindings.bindBidirectional(control.dataProperty(), txtData.textProperty());
        Bindings.bindBidirectional(control.horarioProperty(), txtHorario.textProperty());
        Bindings.bindBidirectional(control.enderecoProperty(), txtEndereco.textProperty());
		
		TableColumn<Evento, String> col1 = new TableColumn<>("Codigo");
		col1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		
		TableColumn<Evento, String> col2 = new TableColumn<>("Nome");
		col2.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Evento, String> col3 = new TableColumn<>("Data");
		col3.setCellValueFactory(new PropertyValueFactory<>("data"));
		
		TableColumn<Evento, String> col4 = new TableColumn<>("Horario");
		col4.setCellValueFactory(new PropertyValueFactory<>("horario"));
		
		TableColumn<Evento, String> col5 = new TableColumn<>("Endereco");
		col5.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
		table.getColumns().addAll(col1, col2, col3, col4, col5);
		
		table.setItems(control.getEvento());
		
		btnSalvar.setOnAction((e) -> {
			control.salvar();
		});
		
		btnListar.setOnAction((e) -> {
			control.listar();
		});
		
		btnExcluir.setOnAction((e) -> {
			control.apagar();
		});
		
		btnAtualizar.setOnAction((e) -> {
			control.atualizar();
		});
		
		stage.setScene(scn);
		stage.setTitle("Cadastrar evento");
		stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(BoudaryEvento.class, args);
	}
}
