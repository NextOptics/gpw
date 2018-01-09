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
import gpw.model.Dsolicit;
import gpw.model.Dautorizacao;
import gpw.model.ContasBanc;
import gpw.model.ContasCartao;
import gpw.dao.DaoDespesas;
import gpw.dao.DaoContasBanc;
import gpw.dao.DaoContasCartao;


/*
	 private static TableView<Dsolicit> tableDespEmAberto(ObservableList<Dsolicit> tabela)
	private static void tbvDespEmAberto_Change()
	private static void btnCarregar_Act()
	private static void btnValidar_Act()
	private static void btnLancar_Act()
	private static void inicializar() 
	private static void populaAtualAutorizacao()
	private static void preencheEdts()
	private static void limparCampos()
	private static void tbvContasBanc_Change()
	private static void tbvContasCard_Change()
	private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela)
	private static TableView<ContasCartao> criaTableCard(ObservableList<ContasCartao> tabela)
	private static boolean dadosEntrados()
	private static ArrayList populaArrAtualAutorz()
	private static void teste()
*/

public class DespAgenEmite {
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static TableView<Dsolicit> tbvDespEmAberto;
	static TableView<ContasBanc> tbvContasBanc;
	static TableView<ContasCartao> tbvContasCard;
	
	static ObservableList<Dsolicit> despEmAberto;
	static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasbanc do banco de dados correspondentes as contas bancarias ativas
	static ObservableList<ContasCartao> cartoesAtivos;
	static ObservableList grupo, subGrupo;
	
	static Dautorizacao atualAutorizacao; // Dautorizacao   extends  Dsolicit
	static DaoDespesas despesas;
	static DaoContasBanc contasBancarias;
	static DaoContasCartao contasCartao;

	static Label lblRotNumero,lblNumero,lblGrupo, lblSubGrupo,lblRotItem,lblItem,lblRotAplic,lblAplic,
				 lblRotValPrev,lblValPrev, lblRotPrevisao,lblPrevisao,lblRotValAgenda,lblRotParcelas,
				 lblRotAgendar,lblContas,lblRotSelecionado, lblRotCartoes, lblSelecionado;
	static Button btnCarregar, btnValidar, btnLancar;
	static TextField edtValAgenda, edtParcelas;
	static DatePicker dtpAgendar;
	
	static HBox paneLinha1, paneLinha2, paneLinha3,paneLinha4, paneLinha5, paneLinha6;
	static VBox panePrimary, paneItem, paneAplic,panePrevisao,paneValPrev,paneValAgenda,paneParcelas,
			paneAgendar,paneLinha4a,paneLinha4b;

	private static String letraConta;
	private static int numeroConta;
	
	public static void show() {
		atualAutorizacao = new Dautorizacao();
		despesas = new DaoDespesas();
		grupo = Ini.loadProps("CentroDesp", "Cen");
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Agenda Despesas");
		stagePrimary.setMinWidth(880);
		stagePrimary.setMaxWidth(880);
		stagePrimary.setMaxHeight(900);
		
			// -------------------- Linha 1
		tbvDespEmAberto = new TableView<Dsolicit>();
		despEmAberto = despesas.carregarSolicAbertas();
		tbvDespEmAberto = tableDespEmAberto(despEmAberto);
		tbvDespEmAberto.setPrefHeight(200);
		
		tbvDespEmAberto.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvDespEmAberto_Change());
		paneLinha1 = new HBox(tbvDespEmAberto);
		paneLinha1.setMaxWidth(850);
		paneLinha1.setPadding(new Insets(30,0,0,20));
		
			// --------------------- Linha 2
		lblRotNumero = new Label("Solicitacao selecionada: ");
		lblRotNumero.setPrefWidth(140);
		lblNumero = new Label("");
		lblNumero.setStyle("-fx-text-fill: blue");
		lblNumero.setPrefWidth(140);
		
		lblGrupo = new Label("");
		lblGrupo.setPrefWidth(140);
		lblGrupo.setStyle("-fx-text-fill: blue");
		
		lblSubGrupo = new Label("");
		lblSubGrupo.setPrefWidth(140);
		lblSubGrupo.setStyle("-fx-text-fill: blue");
		
		paneLinha2 = new HBox(lblRotNumero, lblNumero,lblGrupo, lblSubGrupo);
		paneLinha2.setSpacing(30);
		paneLinha2.setPadding(new Insets(20,0,0,20));
		
			// -------------------- Linha 3
		lblRotItem = new Label("Item");
		lblRotItem.setPrefWidth(140);
		lblItem = new Label();
		lblItem.setStyle("-fx-text-fill: blue");
		lblItem.setPrefWidth(140);
		paneItem = new VBox(lblRotItem, lblItem);
		
		lblRotAplic = new Label("Aplicacao");
		lblRotAplic.setPrefWidth(140);
		lblAplic = new Label();
		lblAplic.setStyle("-fx-text-fill: blue");
		lblAplic.setPrefWidth(140);
		paneAplic = new VBox(lblRotAplic, lblAplic);
		
		lblRotPrevisao = new Label("Previsao");
		lblRotPrevisao.setPrefWidth(70);
		lblPrevisao = new Label("");
		lblPrevisao.setStyle("-fx-text-fill: blue");
		lblPrevisao.setPrefWidth(70);
		panePrevisao = new VBox(lblRotPrevisao, lblPrevisao);
		
		lblRotValPrev = new Label("Valor Prev.");
		lblRotValPrev.setPrefWidth(70);
		lblValPrev = new Label("");
		lblValPrev.setStyle("-fx-text-fill: blue");
		lblValPrev.setPrefWidth(70);
		paneValPrev = new VBox(lblRotValPrev,lblValPrev);
		
		lblRotValAgenda = new Label("Valor Agen.");
		lblRotValAgenda.setPrefWidth(70);
		edtValAgenda = new TextField("");
		edtValAgenda.setPrefWidth(70);
		paneValAgenda = new VBox(lblRotValAgenda, edtValAgenda);
		
		lblRotParcelas = new Label("Parc.");
		lblRotParcelas.setPrefWidth(40);
		edtParcelas = new TextField("");
		edtParcelas.setPrefWidth(40);
		paneParcelas = new VBox(lblRotParcelas, edtParcelas);
		
		lblRotAgendar = new Label("Agendar para:");
		lblRotAgendar.setPrefWidth(120);
		dtpAgendar = new DatePicker();
		dtpAgendar.setPrefWidth(120);
		dtpAgendar.setValue(LocalDate.now());
		paneAgendar = new VBox(lblRotAgendar, dtpAgendar);
		
		paneLinha3 = new HBox(paneItem,paneAplic,panePrevisao,paneValPrev,paneValAgenda,paneParcelas, paneAgendar);
		paneLinha3.setSpacing(30);
		paneLinha3.setPadding(new Insets(20,0,0,20));
		
			// ---------------------- Linha 4
			
		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancAtivas = ObservableList
		
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);
		tbvContasBanc.setItems(contasBancAtivas);	
		tbvContasBanc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasBanc_Change());
		
		lblContas = new Label("Contas bancarias");
		lblContas.setPrefWidth(150);
	
		paneLinha4a = new VBox(lblContas, tbvContasBanc);
		paneLinha4a.setMaxHeight(150);
		paneLinha4a.setMaxWidth(250);

		contasCartao = new DaoContasCartao();
		cartoesAtivos = contasCartao.carregarContasCard("A");

		tbvContasCard = new TableView<ContasCartao>();
		tbvContasCard = criaTableCard(cartoesAtivos);
		tbvContasCard.setItems(cartoesAtivos);
		
		tbvContasCard.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasCard_Change()); 		
		lblRotCartoes = new Label("Cartoes: ");
		lblRotCartoes.setPrefWidth(150);
		
		paneLinha4b = new VBox(lblRotCartoes, tbvContasCard);
		paneLinha4b.setMaxHeight(150);
		paneLinha4b.setPrefWidth(420);
		
		paneLinha4 = new HBox(paneLinha4a,paneLinha4b);
		paneLinha4.setPadding(new Insets(20,0,0,20));
		paneLinha4.setSpacing(50);
		
			// ----------------------- Linha 5
		lblRotSelecionado = new Label("Conta/Cartao Selecionado: ");
		lblRotSelecionado.setPrefWidth(170);
		lblSelecionado = new Label("");
		lblSelecionado.setStyle("-fx-text-fill: blue");
		lblSelecionado.setPrefWidth(300);
		
		paneLinha5 = new HBox(lblRotSelecionado, lblSelecionado);
		paneLinha5.setPadding(new Insets(20,0,0,20));
		
			// -----------------------  Linha 6
		btnCarregar = new Button("Carregar");
		btnCarregar.setPrefWidth(130);
		btnCarregar.setOnAction(e -> btnCarregar_Act());	
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(120);
		btnValidar.setOnAction(e -> btnValidar_Act());	
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(120);
		btnLancar.setOnAction(e -> btnLancar_Act());	
		paneLinha6 = new HBox(btnCarregar, btnValidar, btnLancar);
		paneLinha6.setSpacing(25);
		paneLinha6.setPadding(new Insets(20, 0, 30, 30));
			
			// ---------------------	
		panePrimary = new VBox(paneLinha1, paneLinha2, paneLinha3, paneLinha4,paneLinha5,paneLinha6);
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		inicializar();
		stagePrimary.showAndWait();	
	}
	
	private static TableView<Dsolicit> tableDespEmAberto(ObservableList<Dsolicit> tabela){
		TableView<Dsolicit> tableDespesas = new TableView<Dsolicit>();
				
		TableColumn<Dsolicit, String> colItem = new TableColumn("Descricao");
		colItem.setPrefWidth(200);
		colItem.setCellValueFactory(new PropertyValueFactory<Dsolicit, String >("Item"));
		
		TableColumn<Dsolicit, String> colAplicacao = new TableColumn("Aplicacao");
		colAplicacao.setPrefWidth(200);
		colAplicacao.setCellValueFactory(new PropertyValueFactory<Dsolicit, String >("Aplicacao"));
		
		TableColumn<Dsolicit, Double> colValorPrev = new TableColumn("Valor");
		colValorPrev.setPrefWidth(100);
		colValorPrev.setCellValueFactory(new PropertyValueFactory<Dsolicit, Double >("ValorPrev"));
		
		TableColumn<Dsolicit, LocalDate> colPrevPagam = new TableColumn("Previsao");
		colPrevPagam.setPrefWidth(100);
		colPrevPagam.setCellValueFactory(new PropertyValueFactory<Dsolicit, LocalDate>("PrevPagam"));
		
		TableColumn<Dsolicit, Integer> colNumParcelas = new TableColumn("Parc.");
		colNumParcelas.setPrefWidth(80);
		colNumParcelas.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer>("NumParcelas"));
		
		TableColumn<Dsolicit, Integer> colParcPagas = new TableColumn("Pagas");
		colParcPagas.setPrefWidth(80);
		colParcPagas.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer >("ParcPagas"));
		
		TableColumn<Dsolicit, Integer> colNumSolicit = new TableColumn("Numero");
		colNumSolicit.setPrefWidth(80);
		colNumSolicit.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer>("NumSolicit"));
		
		TableColumn<Dsolicit, Integer> colProjeto = new TableColumn("Proj.");
		colProjeto.setPrefWidth(80);
		colProjeto.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer>("Projeto"));
		
		tableDespesas.setItems(tabela);
		tableDespesas.getColumns().addAll(colNumSolicit,colPrevPagam,colValorPrev,colItem,colAplicacao,colNumParcelas,colParcPagas,colProjeto );
		
		return tableDespesas;	
	}

	private static void tbvDespEmAberto_Change() {
		inicializar();
		btnCarregar.setDisable(false);	
	}
	
	private static void btnCarregar_Act() {
		populaAtualAutorizacao();  // preenche os campos de objeto Dautorizacao que extends Dsolicit
		preencheEdts();		       // dados do atualAutorizacao-> nos campos da tela
		btnValidar.setDisable(false);
		btnCarregar.setDisable(true);
	}

	private static void btnValidar_Act() {
		if(lblSelecionado.getText() != "") {  // Foi selecionada uma conta bancaria ou conta cartao;
			if(dadosEntrados()){
				ArrayList arrListDados = new ArrayList();
				arrListDados = populaArrAtualAutorz();
				if(arrListDados.isEmpty()) {
					MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
					stagePrimary.close();
				}
				BusinessRules regra = new BusinessDespAutorz();
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
		arrListDados = populaArrAtualAutorz();
		BusinessRules regra = new BusinessDespAutorz();
		if(regra.performLancamento(arrListDados)) {
			limparCampos();
			btnLancar.setDisable(true);		
			despEmAberto = despesas.carregarSolicAbertas();
			tbvDespEmAberto.setItems(despEmAberto);
		}	
	}
	
	private static void inicializar() {
		dtpAgendar.setValue(LocalDate.now());
		limparCampos();
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		btnCarregar.setDisable(true);
		letraConta = "";
		numeroConta = 0;
	}

	private static void populaAtualAutorizacao() {
				// Campos herdados de Dsolicit - Dautorz  extends Dsolicit
		atualAutorizacao.setDataSolicit((LocalDate)tbvDespEmAberto.getSelectionModel().getSelectedItem().getDataSolicit());
		atualAutorizacao.setItem((String)tbvDespEmAberto.getSelectionModel().getSelectedItem().getItem());
		atualAutorizacao.setAplicacao((String)tbvDespEmAberto.getSelectionModel().getSelectedItem().getAplicacao());
		atualAutorizacao.setValorPrev((double)tbvDespEmAberto.getSelectionModel().getSelectedItem().getValorPrev());
		atualAutorizacao.setPrevPagam((LocalDate)tbvDespEmAberto.getSelectionModel().getSelectedItem().getPrevPagam());
		atualAutorizacao.setQuantidade((double)tbvDespEmAberto.getSelectionModel().getSelectedItem().getQuantidade());
		atualAutorizacao.setUnidade((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getUnidade());
		atualAutorizacao.setSituacao((String)tbvDespEmAberto.getSelectionModel().getSelectedItem().getSituacao());
		atualAutorizacao.setNumParcelas((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getNumParcelas());
		atualAutorizacao.setParcPagas((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getParcPagas());
		atualAutorizacao.setDocum((String)tbvDespEmAberto.getSelectionModel().getSelectedItem().getDocum());
		atualAutorizacao.setNumSolicit((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getNumSolicit());
		atualAutorizacao.setClasse((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getClasse());
		atualAutorizacao.setGrupo((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getGrupo());
		atualAutorizacao.setUltAltera((LocalDate)tbvDespEmAberto.getSelectionModel().getSelectedItem().getUltAltera());	
		atualAutorizacao.setProjeto((int)tbvDespEmAberto.getSelectionModel().getSelectedItem().getProjeto());
	}

	
	private static void preencheEdts() {
		lblNumero.setText(Integer.toString(atualAutorizacao.getNumSolicit()));
		lblItem.setText(atualAutorizacao.getItem());
		lblAplic.setText(atualAutorizacao.getAplicacao());
		lblPrevisao.setText(DateUtils.asString(atualAutorizacao.getPrevPagam()));
		lblValPrev.setText(Double.toString(atualAutorizacao.getValorPrev()));
		
		edtValAgenda.setText(Double.toString(atualAutorizacao.getValorPrev()));
		edtParcelas.setText(Integer.toString(atualAutorizacao.getNumParcelas()));
		dtpAgendar.setValue(atualAutorizacao.getPrevPagam());
		
		int iGrupo = atualAutorizacao.getClasse();
		int iSubGrupo = atualAutorizacao.getGrupo();
		subGrupo = Ini.loadProps("Cen" + iGrupo, "SubCen");
		lblGrupo.setText((String)grupo.get(iGrupo));
		lblSubGrupo.setText((String)subGrupo.get(iSubGrupo));
	}
	
	
	private static void limparCampos() {
		lblNumero.setText("");
		lblGrupo.setText("");
		lblSubGrupo.setText("");
		lblItem.setText("");
		lblAplic.setText("");
		lblValPrev.setText("");
		lblPrevisao.setText("");
		edtValAgenda.setText("");
		dtpAgendar.setValue(LocalDate.now());
		edtParcelas.setText("");
		lblSelecionado.setText("");	
	}

	private static void tbvContasBanc_Change() {
		letraConta = "B";
		numeroConta = tbvContasBanc.getSelectionModel().getSelectedItem().getCodConta();
		lblSelecionado.setText(	tbvContasBanc.getSelectionModel().getSelectedItem().getNomeBanco() + " - " + 
					            tbvContasBanc.getSelectionModel().getSelectedItem().getContaNumero());
	}

	private static void tbvContasCard_Change() {
		letraConta = "D";
		numeroConta = tbvContasCard.getSelectionModel().getSelectedItem().getCodCard();
		lblSelecionado.setText(tbvContasCard.getSelectionModel().getSelectedItem().getCardNumero() + " - " + 
					           tbvContasCard.getSelectionModel().getSelectedItem().getCardTitular());
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
	
	private static boolean dadosEntrados() {
		boolean valida = true;
		if(edtValAgenda.getText().equals("")){
			valida = false;
			MessageBox.show("E necessario preencher o valor do aluguel", "VALOR");
		}else 
			try {
				atualAutorizacao.setValorAutorz(Double.parseDouble(edtValAgenda.getText()));
				
			}catch(NumberFormatException ne) {
				valida = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}
		
		if(edtParcelas.getText().equals("")){
			valida = false;
			MessageBox.show("E necessario indicar o numero de parcelas", "PARCELAS");
		}else
			try {
				atualAutorizacao.setNumParcelas(Integer.parseInt(edtParcelas.getText()));
			}catch(NumberFormatException ne) {
				valida = false;
				MessageBox.show("Digitacao incorreta", "PARCELAS");
			}
		
//		atualAutorizacao.setNumSolicit(atualAutorizacao.getNumSolicit());
				// field com mesmo nome na classe e subclasse
		atualAutorizacao.setVencAutorz(dtpAgendar.getValue());
		atualAutorizacao.setDatAutorz(LocalDate.now());
		atualAutorizacao.setSituacao("A");
		atualAutorizacao.setTipoConta(letraConta);
		atualAutorizacao.setConta(numeroConta);
		  //atualAutorizacao.setNumdaParcela = gerado no BusiRuleDesAutorz
						
		return valida;
	}


	private static ArrayList populaArrAtualAutorz() {
		ArrayList arrList = new ArrayList();
				// É passado um ArrayList ao invés de objeto para viabilizar o uso do pattern "Strategy"
				// nos BusiRules
		arrList.add(atualAutorizacao.getNumAutorz());		//  0
		arrList.add(atualAutorizacao.getNumSolicit());		//  1
		arrList.add(atualAutorizacao.getValorAutorz());		//  2
		arrList.add(atualAutorizacao.getVencAutorz());		//  3
		arrList.add(atualAutorizacao.getDatAutorz());		//  4
		arrList.add(atualAutorizacao.getSituacao());		//  5
		arrList.add(atualAutorizacao.getTipoConta());		//  6
		arrList.add(atualAutorizacao.getConta());			//  7
		arrList.add(atualAutorizacao.getNumdaParcela());	//  8
									// Dautorizacao extends Dsolicit			
		arrList.add(atualAutorizacao.getDataSolicit());			//  9
		arrList.add(atualAutorizacao.getItem());				// 10
		arrList.add(atualAutorizacao.getAplicacao());			// 11
		arrList.add(atualAutorizacao.getValorPrev());			// 12
		arrList.add(atualAutorizacao.getPrevPagam());			// 13
		arrList.add(atualAutorizacao.getQuantidade());			// 14
		arrList.add(atualAutorizacao.getUnidade());				// 15
		
		arrList.add(atualAutorizacao.getSituacao());			// 16
		arrList.add(atualAutorizacao.getNumParcelas());			// 17
		arrList.add(atualAutorizacao.getParcPagas());			// 18
		arrList.add(atualAutorizacao.getDocum());				// 19
		arrList.add(atualAutorizacao.getNumSolicit());			// 20
		arrList.add(atualAutorizacao.getClasse());				// 21
		arrList.add(atualAutorizacao.getGrupo());				// 22
		arrList.add(atualAutorizacao.getUltAltera());			// 23	
		arrList.add(atualAutorizacao.getProjeto());				// 24	
		return arrList;
	}
}

