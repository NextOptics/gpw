package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;

import javafx.geometry.*;		// Insets
import javafx.scene.control.cell.*;		// PropertyValueFactory
import javafx.collections.*;	// Usado para os Observables

import gpw.control.*;   // usado para acessar os dados do arquivo Ini
import gpw.model.Pagadores;
import gpw.dao.DaoPagadores;

/*
	private static TableView<Pagadores> tablePagadoresAtivos(ObservableList<Pagadores> tabela)
	private static void tbvCadastrados_Change()

*/

public class PagMostra {
	static Stage stagePrimary;
	static Scene scenePrimary;
	static VBox panePrimary;
	static HBox paneLinha1, paneLinha2;
	
	static Label lblFonte, lblPagFonte;
	
	static TableView<Pagadores> tbvPagadoresAtivos;
	static ObservableList<Pagadores> dadosPagadores;
	static ObservableList grupo, subGrupo;
	
	static DaoPagadores cadastrados; // Objeto criado para acessar metodos de DaoPagadores
	
	public static void show() {
		cadastrados = new DaoPagadores();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Mostra Pagadores");
		stagePrimary.setMinWidth(900);
		stagePrimary.setMaxWidth(900);
		stagePrimary.setMaxHeight(900);
		
		// ---------------------- Linha 1
		tbvPagadoresAtivos = new TableView<Pagadores>();
		dadosPagadores = cadastrados.carregaPagadores();
		tbvPagadoresAtivos = tablePagadoresAtivos(dadosPagadores);
		tbvPagadoresAtivos.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvPagadoresAtivos_Change());
		
		paneLinha1 = new HBox(tbvPagadoresAtivos);
		paneLinha1.setPrefHeight(400);
		paneLinha1.setPrefWidth(850);
		paneLinha1.setPadding(new Insets(30,0,0,30));
 
		// ----------------------- Linha 2
		lblFonte = new Label("Fonte Pagadora : ");
		lblFonte.setFont(Font.font("Arial", 16));
		lblFonte.setPrefWidth(150);
		
		lblPagFonte = new Label("");
		lblPagFonte.setFont(Font.font("Arial", 16));
		paneLinha2 = new HBox(lblFonte,lblPagFonte);
		paneLinha2.setPadding(new Insets(30, 0, 50, 30));
		
		// -------------------------
		grupo = Ini.loadProps("CentroReceb", "RecCen"); // passar esta linha para baixo
		
		panePrimary = new VBox(paneLinha1, paneLinha2);
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		stagePrimary.showAndWait();
	}
							
	private static TableView<Pagadores> tablePagadoresAtivos(ObservableList<Pagadores> tabela) {
		TableView<Pagadores> tableCadastrados = new TableView<Pagadores>();	
		
		TableColumn<Pagadores, Integer> colEstado = new TableColumn("Cod.");
		colEstado.setPrefWidth(35);
		colEstado.setCellValueFactory(new PropertyValueFactory<Pagadores, Integer>("CodPagador"));
			
		TableColumn<Pagadores, String> colCodigo = new TableColumn("Descricao");
		colCodigo.setPrefWidth(160);
		colCodigo.setCellValueFactory(new PropertyValueFactory<Pagadores, String>("RecDescr"));
			
		TableColumn<Pagadores, String> colNome = new TableColumn("Nome Contrato");
		colNome.setPrefWidth(200);
		colNome.setCellValueFactory(new PropertyValueFactory<Pagadores, String>("NomeContrato"));
		
		TableColumn<Pagadores, Double> colValor = new TableColumn("Valor");
		colValor.setPrefWidth(85);
		colValor.setCellValueFactory(new PropertyValueFactory<Pagadores, Double>("ValContrato"));
		
		TableColumn<Pagadores, String> colInicio = new TableColumn("Inicio");
		colInicio.setPrefWidth(80);
		colInicio.setCellValueFactory(new PropertyValueFactory<Pagadores, String>("ContratoInic"));
			
		TableColumn<Pagadores, String> colTermino = new TableColumn("Termino");
		colTermino.setPrefWidth(80);
		colTermino.setCellValueFactory(new PropertyValueFactory<Pagadores, String>("ContratoFim"));
			
		TableColumn<Pagadores, Integer> colDiaVenc = new TableColumn("Dia");
		colDiaVenc.setPrefWidth(30);
		colDiaVenc.setCellValueFactory(new PropertyValueFactory<Pagadores, Integer>("DiaVenc"));
			
		TableColumn<Pagadores, String> colAgente = new TableColumn("Agente");
		colAgente.setPrefWidth(100);
		colAgente.setCellValueFactory(new PropertyValueFactory<Pagadores, String>("Agente"));
		
		TableColumn<Pagadores, Integer> colConta = new TableColumn("Conta");
		colConta.setPrefWidth(70);
		colConta.setCellValueFactory(new PropertyValueFactory<Pagadores, Integer>("ContaVinc"));

		tableCadastrados.setItems(tabela);
		tableCadastrados.getColumns().addAll(colEstado,colCodigo,colNome,colValor,colInicio,colTermino,colDiaVenc,colAgente,colConta);
		return tableCadastrados;
	}
	
	private static void tbvPagadoresAtivos_Change() {
		int iGrupo = tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getCentroReceb();
		int iSubGrupo = tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getSubCentroRec();
		
		subGrupo = Ini.loadProps("RecCen" + iGrupo, "SubReceb");
		
		lblPagFonte.setText(grupo.get(iGrupo) + "   /   " + subGrupo.get(iSubGrupo));
	}
}