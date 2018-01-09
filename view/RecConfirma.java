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

//import java.time.LocalDate;

import gpw.control.*;

import gpw.model.Recebimentos;
import gpw.model.ContasBanc;
import gpw.dao.DaoRecebimentos;
import gpw.dao.DaoContasBanc;


/*
		public static void show() 
		private static void cbxCentro_Change()
		private static void cbxSubCentro_Change()
		private static void tbvRecebimentos_Change()
		private static void btnCarregar_Act()
		private static void btnValidar_Act()
		private static void btnLancar_Act()
		private static TableView<PagRecebimentos> tableRecebimentosAberto(ObservableList<Recebimentos> tabela)
		private static void populaAtualRecebimento()
		private static void inicializar()
		private static void preencheEdts()
		private static boolean dadosEntrados()
		private static ArrayList populaArrAtualRecebimento())
	
		private static void teste()
*/

/*
	Nesta classe sao criados os objetos:
		 atualRecebimento<Recebimentos> : Que possui os campos do Recebimento e
												do Pagador - Recebimentos extends Pagadores
												e representa o recebimento atual
												com seu respectivo pagador
		atualContaBanc<Bconta>
		
	Na classe RecAgenda sao criados os objetos:
		atualPagador<Pagadores>		   : Pagador responsavel pelo recebimento atual
		atualRecebimento<Recebimentos> : Recebimento a ser cadastrado
		
*/

public class RecConfirma {
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblCentro, lblSubCentro,lblRotComiss,lblRotImpRenda,lblRotOutras,lblRotValPago, lblRotDescr, lblDescr,lblRotConta,lblConta,lblRotDocum;
	static Button  btnCarregar, btnValidar, btnLancar;
	
	static TextField edtComiss,edtImpRenda,edtOutras,edtValPago, edtDocum;
	
	static ChoiceBox cbxCentro, cbxSubCentro;
	
	static TableView<Recebimentos> tbvRecebimentos;
	
	static VBox panePrimary, paneCentro, paneSubCentro,paneComiss,paneImpRenda,paneOutras,paneValPago,paneDescr,paneConta,paneDocum;
	static HBox paneBotoes,paneLinha1, paneLinha2, paneLinha3,paneLinha4;
	
	static ObservableList<Recebimentos> dadosRecebimentos; // Recebe os Recebimentos lidos no dao
	static Recebimentos atualRecebimento; 
	static DaoRecebimentos recebimentos;
	
	static DaoContasBanc contasBancarias;
	static ObservableList<ContasBanc> contas; // Contem os registros lidos da tabela contasbanc do banco de dados
											  // correspondentes as contas bancarias ativas
	
	public static void show() {
		atualRecebimento = new Recebimentos();
		recebimentos = new DaoRecebimentos();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Confirma  Recebimentos");
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
		contas = contasBancarias.carregarContasBanc(1);  // contas = ObservableList
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
		
		lblRotValPago = new Label("ValorPago");
		lblRotValPago.setPrefWidth(80);
		edtValPago = new TextField("");
		edtValPago.setPrefWidth(80);
		paneValPago = new VBox(lblRotValPago, edtValPago);
		paneValPago.setPadding(new Insets(0,0,0,30));
		
		paneLinha3 = new HBox(paneDescr, paneComiss, paneImpRenda, paneOutras,paneValPago,paneConta);
		paneLinha3.setPadding(new Insets(30,0,0,0));
		
		// ------------------- Linha 4
		lblRotDocum = new Label("Docum.");
		lblRotDocum.setPrefWidth(120);
		edtDocum = new TextField("");
		edtDocum.setPrefWidth(200);
		edtDocum.setPromptText("opcional");
		
		paneDocum = new VBox(lblRotDocum, edtDocum);
		paneDocum.setPadding(new Insets(0,0,0,0));
		
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
		btnCarregar.setDisable(false);
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
									// Usando mesmo metodo de BusiPagCadastra
		
		if(regra.performConfirmacao(arrListDados)){ 									
			btnLancar.setDisable(true);
			tbvRecebimentos.getSelectionModel().clearSelection();
			inicializar();
			dadosRecebimentos = recebimentos.carregaRecebimentos(atualRecebimento.getCentroReceb(), atualRecebimento.getSubCentroRec());
			tbvRecebimentos.setItems(dadosRecebimentos);
		}else {
			MessageBox.show("Lancamento nao  finalizado ", "ERRO FATAL");
			inicializar();
		}	
													
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		dadosRecebimentos = recebimentos.carregaRecebimentos(atualRecebimento.getCentroReceb(), atualRecebimento.getSubCentroRec());
		tbvRecebimentos.setItems(dadosRecebimentos);			
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
	
	private static void populaAtualRecebimento() {
		
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
	
		atualRecebimento.setDataLanc(tbvRecebimentos.getSelectionModel().getSelectedItem().getDataLanc());
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
	
	private static void inicializar() {
		btnCarregar.setDisable(true);
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		edtDocum.setText("");
		edtComiss.setText("");
		edtImpRenda.setText("");
		edtOutras.setText("");
	}
	
	private static void preencheEdts() {
		lblDescr.setText(atualRecebimento.getRecDescr());
		edtDocum.setText(atualRecebimento.getDocumento());
		edtComiss.setText(Double.toString(atualRecebimento.getComisRec()));
		edtImpRenda.setText(Double.toString(atualRecebimento.getImpRenda()));
		edtOutras.setText(Double.toString(atualRecebimento.getOutrasDesp()));
		lblConta.setText((contas.get(atualRecebimento.getContaVinc()-1).getNomeBanco()).toString() +
			           "  " + (contas.get(atualRecebimento.getContaVinc() -1).getContaNumero()).toString());
	}
	
	private static boolean dadosEntrados() {
		boolean libera = true;
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
			
		//  ValorPago
		if(edtValPago.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o campo do Valor Pago", "PAGAMENTO");
		}else
			try {
				atualRecebimento.setValRecebido(Double.parseDouble(edtValPago.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}	
		return libera;
	}
	
	private static ArrayList populaArrAtualRecebimento() {
		ArrayList arrList = new ArrayList();
			// Recebimentos extends Pagadores
			// Campos referentes ao recebimento	
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
}
