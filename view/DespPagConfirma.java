package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

import javafx.geometry.*;
import javafx.collections.*;
import java.util.ArrayList;
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Dpagamen;
import gpw.model.ContasBanc;
import gpw.model.ContasCartao;

import gpw.dao.DaoPagamentos;
import gpw.dao.DaoContasBanc;
import gpw.dao.DaoContasCartao;


/*
		private static TableView<Dpagamen> tableDespPagamEmAberto(ObservableList<Dpagamen> tabela) {
		private static void tbvDespPagamEmAberto_Change() {
		private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela) {
		private static void tbvContasBanc_Change() {
		private static void tbvContasCard_Change() {
		private static TableView<ContasCartao> criaTableCard(ObservableList<ContasCartao> tabela) {
		private static void inicializar() {
		private static void populaAtualPagamento() {
		private static void populaAtualPagamento() {
		private static void preencheEdts() {
		private static void btnCarregar_Act() {
		private static void btnValidar_Act() {
		private static void btnLancar_Act() {
		private static boolean dadosEntrados()
		private static ArrayList populaArrAtualPagam() {
		private static void teste() {
*/


public class DespPagConfirma {
	
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblRotContas,lblRotSelecionado, lblRotCartoes, lblRotAplic,lblAplic,lblRotDebitar,
				 lblDebitar,lblRotValPagar;
				 
	static TextField edtValPagar;
	
	static Button btnCarregar, btnValidar, btnLancar;
	
	static VBox panePrimary, paneLinha2a, paneLinha2b, paneAplic, paneConta, paneValPagar;
	static HBox paneLinha1, paneLinha2, paneLinha3, paneLinha4;
	
	static TableView<Dpagamen> tbvDespPagamEmAberto;
	static TableView<ContasBanc> tbvContasBanc;
	static TableView<ContasCartao> tbvContasCard;
	
	static DaoPagamentos pagamentos;
	static ObservableList<Dpagamen> despPagamEmAberto;
	static Dpagamen atualPagamento;   // Dpagamen extends Dautorizacao extends Dsolicit;
	
	static DaoContasBanc contasBancarias;
	static ObservableList<ContasBanc> contasBancAtivas;
	
	static DaoContasCartao contasCartao;
	static ObservableList<ContasCartao> cartoesAtivos;

	public static void show() {
		
		pagamentos = new DaoPagamentos();
		atualPagamento = new Dpagamen();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Confirma Pagamento de Despesa");
		stagePrimary.setMinWidth(880);
		stagePrimary.setMaxWidth(880);
		stagePrimary.setMaxHeight(900);
		
			// ------------------Linha 1
		tbvDespPagamEmAberto = new TableView<Dpagamen>();
		despPagamEmAberto = pagamentos.carregaPagsEmAberto();
		tbvDespPagamEmAberto = tableDespPagamEmAberto(despPagamEmAberto);
		tbvDespPagamEmAberto.setPrefHeight(200);
		tbvDespPagamEmAberto.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvDespPagamEmAberto_Change());
		paneLinha1 = new HBox(tbvDespPagamEmAberto);
		paneLinha1.setMaxWidth(850);
		paneLinha1.setPadding(new Insets(30,0,0,20));
				
		// ---------------------- Linha 2		
		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancAtivas = ObservableList
		
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);
		tbvContasBanc.setItems(contasBancAtivas);	
		tbvContasBanc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasBanc_Change());
		
		lblRotContas = new Label("Contas bancarias");
		lblRotContas.setPrefWidth(150);
	
		paneLinha2a = new VBox(lblRotContas, tbvContasBanc);
		paneLinha2a.setMaxHeight(150);
		paneLinha2a.setMaxWidth(250);

		contasCartao = new DaoContasCartao();
		cartoesAtivos = contasCartao.carregarContasCard("A");

		tbvContasCard = new TableView<ContasCartao>();
		tbvContasCard = criaTableCard(cartoesAtivos);
		tbvContasCard.setItems(cartoesAtivos);
		
		tbvContasCard.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasCard_Change()); 		
		lblRotCartoes = new Label("Cartoes: ");
		lblRotCartoes.setPrefWidth(150);
		
		paneLinha2b = new VBox(lblRotCartoes, tbvContasCard);
		paneLinha2b.setMaxHeight(150);
		paneLinha2b.setPrefWidth(420);
		
		paneLinha2 = new HBox(paneLinha2a,paneLinha2b);
		paneLinha2.setPadding(new Insets(20,0,0,20));
		paneLinha2.setSpacing(50);
		
		    // ------------- Linha 3
			
		lblRotAplic = new Label("Aplicacao");
		lblAplic = new Label("");
		lblAplic.setPrefWidth(150);
		lblAplic.setStyle("-fx-text-fill: blue");
		paneAplic = new VBox(lblRotAplic, lblAplic);
		
		lblRotDebitar = new Label("Debitar:");
		lblRotDebitar.setPrefWidth(250);
		lblDebitar = new Label("");
		lblDebitar.setStyle("-fx-text-fill: blue");
		lblDebitar.setPrefWidth(250);
		paneConta = new VBox(lblRotDebitar, lblDebitar);
		paneConta.setPadding(new Insets(0,0,0,30));
		
		lblRotValPagar = new Label("Valor a Pagar");
		lblRotValPagar.setPrefWidth(90);
		edtValPagar = new TextField("");
		edtValPagar.setPrefWidth(90);
		paneValPagar = new VBox(lblRotValPagar,edtValPagar);
		
		paneLinha3 = new HBox(paneAplic,paneConta, paneValPagar);
		paneLinha3.setPadding(new Insets(20,0,00,20));
		paneLinha3.setSpacing(30);
		
		    // --------------- Linha 4
		btnCarregar = new Button("Carregar");
		btnCarregar.setPrefWidth(130);
		btnCarregar.setOnAction(e -> btnCarregar_Act());	
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(120);
		btnValidar.setOnAction(e -> btnValidar_Act());	
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(120);
		btnLancar.setOnAction(e -> btnLancar_Act());	
		paneLinha4 = new HBox(btnCarregar, btnValidar, btnLancar);
		paneLinha4.setSpacing(25);
		paneLinha4.setPadding(new Insets(20, 0, 30, 30));
	
			// ----------------------		  
		panePrimary = new VBox(paneLinha1, paneLinha2, paneLinha3, paneLinha4);
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		inicializar();
		stagePrimary.showAndWait();	
	}
	
	private static TableView<Dpagamen> tableDespPagamEmAberto(ObservableList<Dpagamen> tabela) {
		TableView<Dpagamen> tableDespPagam = new TableView<Dpagamen>();
		
		TableColumn<Dpagamen, Integer> colDesp = new TableColumn("Desp.");
		colDesp.setPrefWidth(80);
		colDesp.setCellValueFactory(new PropertyValueFactory<Dpagamen, Integer>("NumSolicit"));
		
		TableColumn<Dpagamen, Integer> colAutorz = new TableColumn("Autorz.");
		colAutorz.setPrefWidth(80);
		colAutorz.setCellValueFactory(new PropertyValueFactory<Dpagamen, Integer>("NumAutorz"));
		
		TableColumn<Dpagamen, String> colItem = new TableColumn("Item");
		colItem.setPrefWidth(150);
		colItem.setCellValueFactory(new PropertyValueFactory<Dpagamen, String> ("Item"));
		
		TableColumn<Dpagamen, Double> colValorPrev = new TableColumn("Prev.");
		colValorPrev.setPrefWidth(100);
		colValorPrev.setCellValueFactory(new PropertyValueFactory<Dpagamen, Double> ("ValorPrev"));
		
		TableColumn<Dpagamen, Double> colValorAutorz = new TableColumn("Autorz");
		colValorAutorz.setPrefWidth(100);
		colValorAutorz.setCellValueFactory(new PropertyValueFactory<Dpagamen, Double> ("ValorAutorz"));
		
		TableColumn<Dpagamen, LocalDate> colVencAutorz = new TableColumn("Venc.");
		colVencAutorz.setPrefWidth(100);
		colVencAutorz.setCellValueFactory(new PropertyValueFactory<Dpagamen, LocalDate> ("VencAutorz"));
		
		TableColumn<Dpagamen, Integer> colParcelas = new TableColumn("Tot.Parcs.");
		colParcelas.setPrefWidth(80);
		colParcelas.setCellValueFactory(new PropertyValueFactory<Dpagamen, Integer> ("NumParcelas"));
		
		TableColumn<Dpagamen, Integer> colNumParcela = new TableColumn("Parcela");
		colNumParcela.setPrefWidth(60);
		colNumParcela.setCellValueFactory(new PropertyValueFactory<Dpagamen, Integer> ("NumdaParcela"));
		
		tableDespPagam.setItems(tabela);
		tableDespPagam.getColumns().addAll(colDesp,colAutorz, colItem, colValorPrev,colValorAutorz, colVencAutorz,colParcelas,colNumParcela);
		return tableDespPagam;
	}
	private static void tbvDespPagamEmAberto_Change() {
		inicializar();
		btnCarregar.setDisable(false);		
	}
	
	private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela) {
		TableView<ContasBanc> tableContas = new TableView<ContasBanc>();
			
		TableColumn<ContasBanc, String> colBanco = new TableColumn("Banco");
		colBanco.setPrefWidth(100);
		colBanco.setCellValueFactory(new PropertyValueFactory<ContasBanc, String>("NomeBanco"));
			
		TableColumn<ContasBanc, String> colConta = new TableColumn("Conta");
		colConta.setPrefWidth(100);
		colConta.setCellValueFactory(new PropertyValueFactory<ContasBanc, String>("ContaNumero"));
			
		tableContas.setItems(tabela);
		tableContas.getColumns().addAll(colBanco, colConta);
		return tableContas;
	}
	
	private static void tbvContasBanc_Change() {
		atualPagamento.setConta(tbvContasBanc.getSelectionModel().getSelectedItem().getCodConta());
		lblDebitar.setText(	tbvContasBanc.getSelectionModel().getSelectedItem().getNomeBanco() + " - " + 
					            tbvContasBanc.getSelectionModel().getSelectedItem().getContaNumero());
		atualPagamento.setTipoConta("B");
	}

	private static void tbvContasCard_Change() {
		atualPagamento.setConta(tbvContasCard.getSelectionModel().getSelectedItem().getCodCard());
		lblDebitar.setText(tbvContasCard.getSelectionModel().getSelectedItem().getCardNumero() + " - " + 
					           tbvContasCard.getSelectionModel().getSelectedItem().getCardTitular());
		atualPagamento.setTipoConta("D");
	}
	
	private static TableView<ContasCartao> criaTableCard(ObservableList<ContasCartao> tabela) {
		TableView<ContasCartao> tableCartoes = new TableView<ContasCartao>();
		
		TableColumn<ContasCartao, String> colBandeira = new TableColumn("Cartao");
		colBandeira.setPrefWidth(80);
		colBandeira.setCellValueFactory(new PropertyValueFactory<ContasCartao,String>("CardBandeira"));
		
		TableColumn<ContasCartao, String> colNumero = new TableColumn("Numero");
		colNumero.setPrefWidth(140);
		colNumero.setCellValueFactory(new PropertyValueFactory<ContasCartao, String>("CardNumero"));
		
		TableColumn<ContasCartao, String> colTitular = new TableColumn("Titular");
		colTitular.setPrefWidth(200);
		colTitular.setCellValueFactory(new PropertyValueFactory<ContasCartao, String>("CardTitular"));
		
		tableCartoes.setItems(tabela);
		tableCartoes.getColumns().addAll(colBandeira, colNumero, colTitular);
		return tableCartoes;
	}
	
	private static void inicializar() {
		lblAplic.setText("");
		lblDebitar.setText("");
		edtValPagar.setText("");
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		btnCarregar.setDisable(true);
	}
	
	private static void populaAtualPagamento() {
	
		atualPagamento.setNumPagam((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumPagam());
		atualPagamento.setNumAutorz((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumAutorz());
		atualPagamento.setValorPago((double)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getValorPago());
		atualPagamento.setDataPagam((LocalDate)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getDataPagam());
		atualPagamento.setSituacao((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getSituacao());
		atualPagamento.setValorPagoMoc((double)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getValorPagoMoc());
	
		atualPagamento.setNumAutorz((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumAutorz());
		atualPagamento.setNumSolicit((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumSolicit());
		atualPagamento.setValorAutorz((double)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getValorAutorz());
		atualPagamento.setVencAutorz((LocalDate)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getVencAutorz());
		atualPagamento.setDatAutorz((LocalDate)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getDatAutorz());
		atualPagamento.setSituacao((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getSituacao());
		atualPagamento.setTipoConta((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getTipoConta());
		atualPagamento.setConta((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getConta());
		atualPagamento.setNumdaParcela((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumdaParcela());
		
		atualPagamento.setDataSolicit((LocalDate)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getDataSolicit());
		atualPagamento.setItem((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getItem());
		atualPagamento.setAplicacao((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getAplicacao());
		atualPagamento.setValorPrev((double)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getValorPrev());
		atualPagamento.setPrevPagam((LocalDate)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getPrevPagam());
		atualPagamento.setQuantidade((double)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getQuantidade());
		atualPagamento.setUnidade((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getUnidade());
		atualPagamento.setSituacao((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getSituacao());
		atualPagamento.setNumParcelas((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumParcelas());
		atualPagamento.setParcPagas((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getParcPagas());
		atualPagamento.setDocum((String)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getDocum());
		atualPagamento.setNumSolicit((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getNumSolicit());
		atualPagamento.setClasse((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getClasse());
		atualPagamento.setGrupo((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getGrupo());
		atualPagamento.setUltAltera((LocalDate)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getUltAltera()); 	
		atualPagamento.setProjeto((int)tbvDespPagamEmAberto.getSelectionModel().getSelectedItem().getProjeto());	
	}
	
	private static void preencheEdts() {
		lblAplic.setText(atualPagamento.getAplicacao());
		edtValPagar.setText(Double.toString(atualPagamento.getValorAutorz()));
		
		if(atualPagamento.getTipoConta().equals("B"))
			lblDebitar.setText((contasBancAtivas.get(atualPagamento.getConta()-1).getNomeBanco()).toString() +
			"  " + (contasBancAtivas.get(atualPagamento.getConta() -1).getContaNumero()).toString());
					   
		if(atualPagamento.getTipoConta().equals("D"))
			lblDebitar.setText((cartoesAtivos.get(atualPagamento.getConta()-1).getCardBandeira()).toString() +
			"  " + (cartoesAtivos.get(atualPagamento.getConta() -1).getCardNumero()).toString());
	}
	
	private static void btnCarregar_Act() {
		populaAtualPagamento();
		TestUtils.printPagamAutorzDesp(populaArrAtualPagam());
		preencheEdts();
		btnCarregar.setDisable(true);
		btnValidar.setDisable(false);	
	}
	
	private static void btnValidar_Act() {
		if(lblDebitar.getText() != "") {  // Foi selecionada uma conta bancaria ou conta cartao;
			if(dadosEntrados()){
				ArrayList arrListDados = new ArrayList();

				arrListDados = populaArrAtualPagam();
				if(arrListDados.isEmpty()) {
					MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
					stagePrimary.close();
				}
				BusinessRules regra = new BusinessDespPagam();
				if(regra.performConsistencia(arrListDados)) {
					btnValidar.setDisable(true);
					btnLancar.setDisable(false);
				}
			}
		}else {
			MessageBox.show("Necessario especificar conta bancaria ou cartao", "MODALIDADE");
		}		
	}
	
	private static void btnLancar_Act() {

		ArrayList arrListDados = new ArrayList();
		arrListDados = populaArrAtualPagam();
		BusinessRules regra = new BusinessDespPagam();
		if(regra.performLancamento(arrListDados)) {
			inicializar();
			btnLancar.setDisable(true);
			despPagamEmAberto = pagamentos.carregaPagsEmAberto();
			tbvDespPagamEmAberto.setItems(despPagamEmAberto);	
		}		
	}
	
	private static boolean dadosEntrados() {
		boolean valida = true;
	
		if(edtValPagar.getText().equals("")){
			valida = false;
			MessageBox.show("E necessario indicar o Valor do Pagamento", "VALOR");
		}else
			try {
				atualPagamento.setValorPago(Double.parseDouble(edtValPagar.getText()));
			}catch(NumberFormatException ne) {
				valida = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}						
		return valida;
	}
	
	private static ArrayList populaArrAtualPagam() {
		ArrayList arrList = new ArrayList();
		
		arrList.add(atualPagamento.getNumPagam());		// 0
		arrList.add(atualPagamento.getNumAutorz());		// 1
		arrList.add(atualPagamento.getValorPago());		// 2
		arrList.add(atualPagamento.getDataPagam());		// 3
		arrList.add(atualPagamento.getSituacao());		// 4
		arrList.add(atualPagamento.getValorPagoMoc());	// 5
		
		// ----------- Dautorizacao");
		arrList.add(atualPagamento.getNumAutorz());		// 6
		arrList.add(atualPagamento.getNumSolicit());	// 7
		arrList.add(atualPagamento.getValorAutorz());	// 8
		arrList.add(atualPagamento.getVencAutorz());	// 9
		arrList.add(atualPagamento.getDatAutorz());		// 10
		arrList.add(atualPagamento.getSituacao());		// 11
		arrList.add(atualPagamento.getTipoConta());		// 12
		arrList.add(atualPagamento.getConta());			// 13
		arrList.add(atualPagamento.getNumdaParcela());	// 14
		
		  // ------------ Dsolicit
		arrList.add(atualPagamento.getDataSolicit());	// 15
		arrList.add(atualPagamento.getItem());			// 16
		arrList.add(atualPagamento.getAplicacao());		// 17
		arrList.add(atualPagamento.getValorPrev());		// 18
		arrList.add(atualPagamento.getPrevPagam());		// 19
		arrList.add(atualPagamento.getQuantidade());	// 20
		arrList.add(atualPagamento.getUnidade());		// 21
		arrList.add(atualPagamento.getSituacao());		// 22
		arrList.add(atualPagamento.getNumParcelas());	// 23
		arrList.add(atualPagamento.getParcPagas());		// 24
		arrList.add(atualPagamento.getDocum());			// 25
		arrList.add(atualPagamento.getNumSolicit());	// 26
		arrList.add(atualPagamento.getClasse());		// 27
		arrList.add(atualPagamento.getGrupo());			// 28
		arrList.add(atualPagamento.getUltAltera()); 	// 29
		arrList.add(atualPagamento.getProjeto());		// 30
		return arrList;
	}
}