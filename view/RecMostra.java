package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

import javafx.geometry.*;
import javafx.collections.*;
import javafx.scene.control.cell.*;
import java.util.ArrayList;

import gpw.control.*;

import gpw.model.Recebimentos;
import gpw.model.ContasBanc;

import gpw.dao.DaoRecebimentos;
import gpw.dao.DaoContasBanc;

/*
	private static void cbxCentro_Change() 
	private static void cbxSubCentro_Change()
	private static void tbvRecebimentos_Change()
	private static TableView<Recebimentos> tableRecebimentosAberto(ObservableList<Recebimentos> tabela) 
	private static void btnDetalhes_Act()
	private static void populaPagRecebimento() 
	private static void preencheEdts()
	private static void inicializar()
*/


public class RecMostra {
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblCentro,lblSubCentro,lblRotDescr,lblDescr,lblRotConta,lblConta,lblRotComiss,lblComiss,
            	lblRotImpRenda,lblImpRenda,lblRotOutras,lblOutras,lblRotValPago,lblValPago,lblRotDocum,lblDocum;
	static Button btnDetalhes;
	static ChoiceBox cbxCentro, cbxSubCentro;
	
	static HBox paneLinha1, paneLinha2,paneLinha3, paneLinha4,paneBotoes ;
	static VBox panePrimary, paneCentro, paneSubCentro,paneDescr,paneConta, paneComiss,paneImpRenda, paneOutras, paneValPago, paneDocum;
	
	static ObservableList<Recebimentos> dadosRecebimentos; // Recebimentos extends Pagadores
	static TableView<Recebimentos> tbvRecebimentos;
	static Recebimentos atualRecebimento;
	
	static ObservableList<ContasBanc> contasBancAtivas;
	
	static DaoRecebimentos recebimentos;
	static DaoContasBanc contasBancarias;
	
	public static void show() {
		atualRecebimento = new Recebimentos();
		recebimentos = new DaoRecebimentos();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Mostra Recebimentos em Aberto");
		stagePrimary.setMinWidth(830);
		stagePrimary.setMaxWidth(830);
		stagePrimary.setMaxHeight(600);
		

		// -------------- Linha 1
		lblCentro = new Label("Centros Recebimento");
		lblCentro.setPrefWidth(150);		
		cbxCentro = new ChoiceBox(Ini.loadProps("CentroReceb", "RecCen"));
		cbxCentro.setPrefWidth(150);
		cbxCentro.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> cbxCentro_Change());
		paneCentro = new VBox(lblCentro, cbxCentro);
		paneCentro.setPadding(new Insets(0,20,0,0));
		
		lblSubCentro = new Label("Sub-Centros");
		lblSubCentro.setPrefWidth(150);
		cbxSubCentro = new ChoiceBox();
		cbxSubCentro.setPrefWidth(150);
		cbxSubCentro.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> cbxSubCentro_Change());
		paneSubCentro = new VBox(lblSubCentro, cbxSubCentro);
		paneSubCentro.setPadding(new Insets(0,0,0,0));	
		paneLinha1 = new HBox(paneCentro,paneSubCentro);
		paneLinha1.setPadding(new Insets(0, 0, 0, 0));
		
		// -------------- Linha 2
		tbvRecebimentos = new TableView<Recebimentos>();
		dadosRecebimentos = recebimentos.carregaRecebimentos(0, 0);
		tbvRecebimentos = tableRecebimentosAberto(dadosRecebimentos);
		tbvRecebimentos.setPrefHeight(300);
	
		tbvRecebimentos.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvRecebimentos_Change());
		paneLinha2 = new HBox(tbvRecebimentos);
		paneLinha2.setPrefHeight(400);
		paneLinha2.setPrefWidth(850);
		paneLinha2.setPadding(new Insets(30,0,0,0));
		
		// ------------------ Linha 3
		
		lblRotDescr = new Label("Descricao");
		lblRotDescr.setPrefWidth(150);
		lblDescr = new Label("");
		lblDescr.setPrefWidth(200);
		lblDescr.setStyle("-fx-text-fill: blue");
		paneDescr = new VBox(lblRotDescr, lblDescr);
		
		lblRotConta = new Label("Conta Banc.");
		lblRotConta.setPrefWidth(100);
		lblConta = new Label("");
		lblConta.setStyle("-fx-text-fill: blue");
		lblConta.setPrefWidth(100);
		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancAtivas = ObservableList
				  //O int 1 em carregaContasBanc() significa que serao carregadas apenas as contas validas
		
		paneConta = new VBox(lblRotConta, lblConta);
		paneConta.setPadding(new Insets(0,0,0,30));
		
		lblRotComiss = new Label("Comissao");
		lblRotComiss.setPrefWidth(70);
		lblComiss = new Label("");
		lblComiss.setStyle("-fx-text-fill: blue");
		lblComiss.setPrefWidth(70);
		paneComiss = new VBox(lblRotComiss, lblComiss);
		paneComiss.setPadding(new Insets(0,0,0,30));
		
		lblRotImpRenda = new Label("Imp.Renda");
		lblRotImpRenda.setPrefWidth(70);
		lblImpRenda = new Label("");
		lblImpRenda.setStyle("-fx-text-fill: blue");
		lblImpRenda.setPrefWidth(70);
		paneImpRenda = new VBox(lblRotImpRenda, lblImpRenda);
		paneImpRenda.setPadding(new Insets(0,0,0,30));
		
		lblRotOutras = new Label("Outras Desp.");
		lblRotOutras.setPrefWidth(75);
		lblOutras = new Label("");
		lblOutras.setStyle("-fx-text-fill: blue");
		lblOutras.setPrefWidth(70);
		paneOutras = new VBox(lblRotOutras, lblOutras);
		paneOutras.setPadding(new Insets(0,0,0,30));
		
		lblRotValPago = new Label("ValorPago");
		lblRotValPago.setPrefWidth(80);
		lblValPago = new Label("");
		lblValPago.setStyle("-fx-text-fill: blue");
		lblValPago.setPrefWidth(80);
		paneValPago = new VBox(lblRotValPago, lblValPago);
		paneValPago.setPadding(new Insets(0,0,0,30));
		
		paneLinha3 = new HBox(paneDescr, paneComiss, paneImpRenda, paneOutras,paneValPago,paneConta);
		paneLinha3.setPadding(new Insets(30,0,0,0));
		
		// ------------------- Linha 4
		lblRotDocum = new Label("Docum.");
		lblRotDocum.setPrefWidth(120);
		lblDocum = new Label("");
		lblDocum.setStyle("-fx-text-fill: blue");
		lblDocum.setPrefWidth(200);
		
		paneDocum = new VBox(lblRotDocum, lblDocum);
		paneDocum.setPadding(new Insets(0,0,0,0));
		
		btnDetalhes = new Button("Detalhes");
		btnDetalhes.setPrefWidth(140);
		btnDetalhes.setOnAction(e -> btnDetalhes_Act());	
		paneBotoes = new HBox(btnDetalhes);
		paneBotoes.setSpacing(45);
		paneBotoes.setPadding(new Insets(15,0,0,30));
		paneLinha4 = new HBox(paneDocum, paneBotoes);	
		paneLinha4.setPadding(new Insets(20, 0, 0,0));
		
		
		// -------------------------------------
		
		panePrimary = new VBox(paneLinha1, paneLinha2, paneLinha3, paneLinha4);
		panePrimary.setPadding(new Insets(30,0,30,30));
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		
		inicializar();
		stagePrimary.showAndWait();
	}
	
	private static void cbxCentro_Change() {
		int i = cbxCentro.getSelectionModel().getSelectedIndex();
		cbxSubCentro.setItems(Ini.loadProps("RecCen" + i, "SubReceb"));	
	}
	
	private static void cbxSubCentro_Change() {
		int centro = cbxCentro.getSelectionModel().getSelectedIndex(); //******* ?????
		int subcentro = cbxSubCentro.getSelectionModel().getSelectedIndex();
		if(centro > 0 && subcentro >0) {		
			dadosRecebimentos = recebimentos.carregaRecebimentos(centro, subcentro);
			tbvRecebimentos.setItems(dadosRecebimentos);
		}	
	}
	
	private static void tbvRecebimentos_Change() {
		btnDetalhes.setDisable(false);
	}
	
	private static TableView<Recebimentos> tableRecebimentosAberto(ObservableList<Recebimentos> tabela) {
		TableView<Recebimentos> tableAbertos = new TableView<Recebimentos>();
		
		TableColumn<Recebimentos, Integer> colCodigo = new TableColumn("Cod.");
		colCodigo.setPrefWidth(35);
		colCodigo.setCellValueFactory(new PropertyValueFactory<Recebimentos, Integer>("CodReceb"));
	
		TableColumn<Recebimentos, String> colPrevPagam = new TableColumn("Vencim.");
		colPrevPagam.setPrefWidth(80);
		colPrevPagam.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("PrevPagam"));
			
		TableColumn<Recebimentos, String> colValorPrev = new TableColumn("Val.Prev");
		colValorPrev.setPrefWidth(80);
		colValorPrev.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("ValorPrev"));
		
		TableColumn<Recebimentos, Integer> colCodPagador = new TableColumn("Pagador");
		colCodPagador.setPrefWidth(60);
		colCodPagador.setCellValueFactory(new PropertyValueFactory<Recebimentos, Integer>("CodPagador"));
	
		TableColumn<Recebimentos, String> colNomeContrato = new TableColumn("Nome contr.");
		colNomeContrato.setPrefWidth(200);
		colNomeContrato.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("NomeContrato"));
		
		TableColumn<Recebimentos, String> colInicio = new TableColumn("Inicio");
		colInicio.setPrefWidth(80);
		colInicio.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("ContratoInic"));
		
		TableColumn<Recebimentos, String> colTermino = new TableColumn("Termino");
		colTermino.setPrefWidth(80);
		colTermino.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("ContratoFim"));
		
		TableColumn<Recebimentos, String> colAgente = new TableColumn("Agente");
		colAgente.setPrefWidth(150);
		colAgente.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("Agente"));
			
		tableAbertos.setItems(tabela);
		tableAbertos.getColumns().addAll(colCodigo,colPrevPagam,colValorPrev,colCodPagador,colNomeContrato,
										colInicio, colTermino, colAgente);
		return tableAbertos;
	}
	
	private static void btnDetalhes_Act() {
		populaPagRecebimento();  // dados do registro selecionado no tbvRecebimentos -> atualRecebimento
		preencheEdts();
		btnDetalhes.setDisable(true);	
	}
	
	private static void populaPagRecebimento() {
		
		atualRecebimento.setCodPagador((Integer)tbvRecebimentos.getSelectionModel().getSelectedItem().getCodPagador());
		atualRecebimento.setEstPagador(tbvRecebimentos.getSelectionModel().getSelectedItem().getEstPagador());
		atualRecebimento.setRecDescr(tbvRecebimentos.getSelectionModel().getSelectedItem().getRecDescr());
		atualRecebimento.setValContrato(tbvRecebimentos.getSelectionModel().getSelectedItem().getValContrato());
		atualRecebimento.setContratoInic(tbvRecebimentos.getSelectionModel().getSelectedItem().getContratoInic());

		atualRecebimento.setContratoFim(tbvRecebimentos.getSelectionModel().getSelectedItem().getContratoFim());
		atualRecebimento.setNomeContrato(tbvRecebimentos.getSelectionModel().getSelectedItem().getNomeContrato());
		atualRecebimento.setContaVinc(tbvRecebimentos.getSelectionModel().getSelectedItem().getContaVinc());
		atualRecebimento.setCentroReceb(tbvRecebimentos.getSelectionModel().getSelectedItem().getCentroReceb());
		atualRecebimento.setSubCentroRec(tbvRecebimentos.getSelectionModel().getSelectedItem().getSubCentroRec());
	
		atualRecebimento.setDiaVenc(tbvRecebimentos.getSelectionModel().getSelectedItem().getDiaVenc());
		atualRecebimento.setAgente(tbvRecebimentos.getSelectionModel().getSelectedItem().getAgente());
		atualRecebimento.setCodReceb(tbvRecebimentos.getSelectionModel().getSelectedItem().getCodReceb());
		atualRecebimento.setRecEstado(tbvRecebimentos.getSelectionModel().getSelectedItem().getRecEstado());
		atualRecebimento.setDataLanc(tbvRecebimentos.getSelectionModel().getSelectedItem().getDataLanc());
		
		atualRecebimento.setPrevPagam(tbvRecebimentos.getSelectionModel().getSelectedItem().getPrevPagam());
		atualRecebimento.setDataRecebido(tbvRecebimentos.getSelectionModel().getSelectedItem().getDataRecebido());
		atualRecebimento.setValorPrev(tbvRecebimentos.getSelectionModel().getSelectedItem().getValorPrev());
		atualRecebimento.setValRecebido(tbvRecebimentos.getSelectionModel().getSelectedItem().getValRecebido());
	
		atualRecebimento.setDocumento(tbvRecebimentos.getSelectionModel().getSelectedItem().getDocumento());
		atualRecebimento.setRecebidoMoc(tbvRecebimentos.getSelectionModel().getSelectedItem().getRecebidoMoc());
		atualRecebimento.setImpRenda(tbvRecebimentos.getSelectionModel().getSelectedItem().getImpRenda());
		atualRecebimento.setComisRec(tbvRecebimentos.getSelectionModel().getSelectedItem().getComisRec());
		atualRecebimento.setOutrasDesp(tbvRecebimentos.getSelectionModel().getSelectedItem().getOutrasDesp());
	}
	
	private static void preencheEdts() {
		lblDescr.setText(atualRecebimento.getRecDescr());
		lblDocum.setText(atualRecebimento.getDocumento());
		lblComiss.setText(Double.toString(atualRecebimento.getComisRec()));
		lblImpRenda.setText(Double.toString(atualRecebimento.getImpRenda()));
		lblOutras.setText(Double.toString(atualRecebimento.getOutrasDesp()));
		lblConta.setText((contasBancAtivas.get(atualRecebimento.getContaVinc()-1).getNomeBanco()).toString() +
			           "  " + (contasBancAtivas.get(atualRecebimento.getContaVinc() -1).getContaNumero()).toString());
	}
	
	private static void inicializar() {
		btnDetalhes.setDisable(true);
		lblDocum.setText("");
		lblComiss.setText("");
		lblImpRenda.setText("");
		lblOutras.setText("");
	}	
}