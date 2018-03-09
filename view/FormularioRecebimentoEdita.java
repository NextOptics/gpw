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
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Recebimentos;
import gpw.model.ContasBanc;

import gpw.dao.DaoRecebimentos;
import gpw.dao.DaoContasBanc;


/*
	
*/


public class FormularioRecebimentoEdita extends FormulariosRecebimento {

	public FormularioRecebimentoEdita() {
		formSelecionado = new FormRecebimentoEdita();
	}

	
	public static void show() {
		atualRecebimento = new Recebimentos();
		recebimentos = new DaoRecebimentos();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Edita Recebimentos Agendados");
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
		lblRotDocum = new Label("Docum.");
		lblRotDocum.setPrefWidth(120);
		edtDocum = new TextField("");
		edtDocum.setPrefWidth(200);
		edtDocum.setPromptText("opcional");
		
		paneDocum = new VBox(lblRotDocum, edtDocum);
		paneDocum.setPadding(new Insets(0,0,0,0));
		
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
		edtComiss = new TextField("");
		edtComiss.setPrefWidth(70);
		paneComiss = new VBox(lblRotComiss, edtComiss);
		paneComiss.setPadding(new Insets(0,0,0,30));
		
		lblRotImpRenda = new Label("Imp.Renda");
		lblRotImpRenda.setPrefWidth(70);
		edtImpRenda = new TextField("");
		edtImpRenda.setPrefWidth(70);
		paneImpRenda = new VBox(lblRotImpRenda, edtImpRenda);
		paneImpRenda.setPadding(new Insets(0,0,0,30));
		
		lblRotOutras = new Label("Outras Desp.");
		lblRotOutras.setPrefWidth(75);
		edtOutras = new TextField("");
		edtOutras.setPrefWidth(70);
		paneOutras = new VBox(lblRotOutras, edtOutras);
		paneOutras.setPadding(new Insets(0,0,0,30));
		
		lblRotValNovo = new Label("ValorPrevisto");
		lblRotValNovo.setPrefWidth(80);
		edtValNovo = new TextField("");
		edtValNovo.setPrefWidth(80);
		paneValPago = new VBox(lblRotValNovo, edtValNovo);
		paneValPago.setPadding(new Insets(0,0,0,30));
		
		paneLinha3 = new HBox(paneDocum, paneComiss, paneImpRenda, paneOutras,paneValPago,paneConta);
		paneLinha3.setPadding(new Insets(30,0,0,0));
		
		// ------------------- Linha 4
		lblRotPrev = new Label("Previsao:");
		lblRotPrev.setPrefWidth(150);
		dtpPrevPagam = new DatePicker(LocalDate.now());
		dtpPrevPagam.setPrefWidth(150);
		paneApartir = new VBox(lblRotPrev, dtpPrevPagam);
		paneApartir.setPadding(new Insets(0,0,0,0));
		
		btnCarregar = new Button("Carregar");
		btnCarregar.setPrefWidth(140);
		btnCarregar.setOnAction(e -> btnCarregar_Act());
		
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(140);
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(140);
		btnLancar.setOnAction(e -> btnLancar_Act());
		paneBotoes = new HBox(btnCarregar, btnValidar, btnLancar);
		paneBotoes.setSpacing(45);
		paneBotoes.setPadding(new Insets(15,0,0,30));
	
		paneLinha4 = new HBox(paneApartir, paneBotoes);
		paneLinha4.setPadding(new Insets(20, 0, 0,0));
		// -------------------------------------
		
		panePrimary = new VBox(paneLinha1, paneLinha2, paneLinha3, paneLinha4);
		panePrimary.setPadding(new Insets(30,0,30,30));
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		
		inicializar();
		stagePrimary.showAndWait();
	}

	private static void btnCarregar_Act() {
		populaAtualRecebimento();  // dados do registro selecionado no tbvRecebimentos -> atualRecebimento
		preencheEdts();
		btnCarregar.setDisable(true);
		btnValidar.setDisable(false);
	}
	
	private static void btnValidar_Act() {
		if(dadosEntrados()) { // Verifica se os campos tem valores formalmente aceitaveis
			ArrayList arrListDados = new ArrayList();
			arrListDados = populaArrAtualRecebimento();
			BusinessRules regra = new BusinessRecebimentos();
									// Usando mesmo metodo de BusiPagCadastra
			if(regra.performConsistencia(arrListDados)){ // Verifica se os dados entrados tem consistencia com as regras do negocio										
				btnValidar.setDisable(true);
				btnLancar.setDisable(false);
			}else {
				tbvRecebimentos.getSelectionModel().clearSelection();
				inicializar();
			}
		}	
	}
	
	private static void btnLancar_Act() {
		ArrayList arrListDados = new ArrayList();
		arrListDados = populaArrAtualRecebimento();
		BusinessRules regra = new BusinessRecebimentos();
			regra.performEdicao(arrListDados);
		btnLancar.setDisable(true);	
	}

	protected static void inicializar() {
		btnCarregar.setDisable(true);
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		edtDocum.setText("");
		edtComiss.setText("");
		edtImpRenda.setText("");
		edtOutras.setText("");
	}

	protected static ArrayList populaArrAtualRecebimento() {
		ArrayList arrList = new ArrayList();
		
			// Campos referentes ao recebimento atual	
		arrList.add(atualRecebimento.getCodReceb());		// 0
		arrList.add(atualRecebimento.getRecEstado());		// 1
		arrList.add(atualRecebimento.getDataLanc());		// 2
		arrList.add(atualRecebimento.getPrevPagam());		// 3
		arrList.add(atualRecebimento.getDataRecebido());	// 4
	
		arrList.add(atualRecebimento.getValorPrev());		// 5
		arrList.add(atualRecebimento.getValRecebido());		// 6
		arrList.add(atualRecebimento.getCodPagador());		// 7
		arrList.add(atualRecebimento.getDocumento());		// 8
		arrList.add(atualRecebimento.getRecebidoMoc());		// 9
	
		arrList.add(atualRecebimento.getImpRenda());		//10
		arrList.add(atualRecebimento.getComisRec());		//11
		arrList.add(atualRecebimento.getOutrasDesp());		//12
		//----------------------------------------
		// Campos referentes ao pagador atual
		arrList.add(atualRecebimento.getCodPagador());		//13
		arrList.add(atualRecebimento.getEstPagador());		//14
		arrList.add(atualRecebimento.getRecDescr());		//15
		arrList.add(atualRecebimento.getValContrato());		//16
		arrList.add(atualRecebimento.getContratoInic());	//17
		arrList.add(atualRecebimento.getContratoFim());		//18
		arrList.add(atualRecebimento.getNomeContrato());	//19
		arrList.add(atualRecebimento.getContaVinc());		//20
		arrList.add(atualRecebimento.getCentroReceb());		//21
		arrList.add(atualRecebimento.getSubCentroRec());	//22
		arrList.add(atualRecebimento.getDataLanc());		//23
		arrList.add(atualRecebimento.getDiaVenc());			//24
		arrList.add(atualRecebimento.getAgente());			//25
		// ---------------------------------------
		arrList.add(1);										//26
					// Para manter compatibilidade com metodo validar	
		return arrList;
	}

	protected static void preencheEdts() {
		dtpPrevPagam.setValue(atualRecebimento.getPrevPagam());
		edtComiss.setText(Double.toString(atualRecebimento.getComisRec()));
		edtImpRenda.setText(Double.toString(atualRecebimento.getImpRenda()));
		edtOutras.setText(Double.toString(atualRecebimento.getOutrasDesp()));
		edtValNovo.setText(Double.toString(atualRecebimento.getValorPrev()));
		lblConta.setText((contasBancAtivas.get(atualRecebimento.getContaVinc()-1).getNomeBanco()).toString() +
			           "  " + (contasBancAtivas.get(atualRecebimento.getContaVinc() -1).getContaNumero()).toString());
	}

	protected static boolean dadosEntrados() {
		boolean libera = true;
		atualRecebimento.setPrevPagam(dtpPrevPagam.getValue());
		
		// Docum
		if(edtDocum.getText().equals("")) {
			atualRecebimento.setDocumento("RECEBIMENTO");
		}
		
		//  Comissao
		if(edtComiss.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor da Comissao", "COMISSAO");
		}else
			try {
				atualRecebimento.setComisRec(Double.parseDouble(edtComiss.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "COMISSAO");
			}
			
		//  Imp Renda
		if(edtImpRenda.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor do Imp. Renda", "IMP RENDA");
		}else
			try {
				atualRecebimento.setImpRenda(Double.parseDouble(edtImpRenda.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "IMP RENDA");
			}
			
		//  OutrasDesp
		if(edtOutras.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor de Outras Desp", "OUTRAS DESP");
		}else
			try {
				atualRecebimento.setOutrasDesp(Double.parseDouble(edtOutras.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "OUTRAS DESP");
			}
			
		//  ValorPrevisto
		if(edtValNovo.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o campo do Valor Previsto", "PAGAMENTO");
		}else
			try {
				atualRecebimento.setValorPrev(Double.parseDouble(edtValNovo.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}	
		return libera;
	}

}