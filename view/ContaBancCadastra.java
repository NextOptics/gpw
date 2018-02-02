package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.cell.*;
import javafx.collections.*;
import java.util.ArrayList;

import gpw.control.*;
import gpw.model.ContasBanc;
import gpw.dao.DaoContasBanc;


/*
	private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela)
	private static void btnValidar_Act()
	private static void btnLancar_Act() 
	private static void btnNovoLanc_Act()
	private static boolean dadosEntrados()

*/



public class ContaBancCadastra {
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static VBox panePrimary, paneBanco,paneCodBanco,paneAgencia,paneConta,paneTitular;
	static VBox paneTipoAg, paneAgenRua, paneBairro,paneCidade, paneCep;
	static VBox paneGerente, paneTel, paneCel, paneContasAtivas;

	static HBox paneLinha1,paneLinha2, paneLinha3,paneBotoes,paneLinha4;
	
	static private Label lblRotBanco,lblRotCodBanco,lblRotAgencia,lblRotConta,lblRotTitular;
	static private TextField edtNomeBanco,edtCodBanco,edtAgenBanco,edtContaNumero,edtTitularConta;

	static private Label lblRotTipoAg, lblRotAgenRua,lblRotBairro, lblRotCidade, lblRotCep, lblRotContasAtivas;
	static private TextField edtTipoAgen, edtAgenRua, edtBairro, edtCidade,edtCep;

	static private Label lblRotGerente, lblRotTel, lblRotCel;
	static private TextField edtGerente, edtTel, edtCel;

	static Button btnValidar, btnLancar, btnNovoLanc;


	static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasBancariasbanc do banco de dados
									// correspondentes as contas bancarias ativas
	static TableView<ContasBanc> tbvContasBanc;
	static DaoContasBanc contasBancarias;
	static ContasBanc atualContaBanc;

	public static void show() {	

		atualContaBanc = new ContasBanc();

		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Cadastra Conta Bancaria");
		stagePrimary.setMinWidth(930);
		stagePrimary.setMaxWidth(930);
		stagePrimary.setMaxHeight(930);


		// --------------------- Linha 1

		lblRotContasAtivas = new Label("Contas Bancarias Ativas");

		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancarias = ObservableList	
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);
		tbvContasBanc.setItems(contasBancAtivas);	
		
//		tbvContasBanc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasBanc_Change());	
		
		paneContasAtivas = new VBox(lblRotContasAtivas,tbvContasBanc);
		paneContasAtivas.setPrefHeight(200);
		paneContasAtivas.setPrefWidth(850);
		paneContasAtivas.setPadding(new Insets(0, 0 , 0 , 0));

		paneLinha1 = new HBox(paneContasAtivas);
		paneLinha1.setPadding(new Insets(30,0,0,20));

	
		// --------------------  Linha 2
		lblRotBanco = new Label("Banco");
		edtNomeBanco = new TextField("");
		paneBanco = new VBox(lblRotBanco, edtNomeBanco);
		paneBanco.setPrefWidth(150);

		lblRotCodBanco = new Label("NumBanco");
		edtCodBanco = new TextField("");
		paneCodBanco = new VBox(lblRotCodBanco,edtCodBanco);
		paneCodBanco.setPrefWidth(80);
		
		lblRotAgencia = new Label("Agencia");
		edtAgenBanco = new TextField("");
		paneAgencia = new VBox(lblRotAgencia,edtAgenBanco);
		paneAgencia.setPrefWidth(100);
		
		lblRotConta = new Label("Conta");
		edtContaNumero = new TextField("");
		paneConta = new VBox(lblRotConta,edtContaNumero);
		paneConta.setPrefWidth(120);
		
		lblRotTitular = new Label("Titular");
		edtTitularConta = new TextField("");
		paneTitular = new VBox(lblRotTitular,edtTitularConta);
		paneTitular.setPrefWidth(250);

		paneLinha2 = new HBox(paneBanco, paneCodBanco, paneAgencia, paneConta, paneTitular);
		paneLinha2.setSpacing(20);
		paneLinha2.setPadding(new Insets(30,0,0,20));


		// ---------------------- Linha 3
		lblRotTipoAg = new Label("Tipo Agencia");
		edtTipoAgen = new TextField("");
		paneTipoAg = new VBox(lblRotTipoAg, edtTipoAgen);
		paneTipoAg.setPrefWidth(115);

		lblRotAgenRua = new Label("Rua");
		edtAgenRua = new TextField("");
		paneAgenRua = new VBox(lblRotAgenRua,edtAgenRua);
		paneAgenRua.setPrefWidth(262);


		lblRotBairro = new Label("Bairro");
		edtBairro = new TextField("");
		paneBairro = new VBox(lblRotBairro,edtBairro);
		paneBairro.setPrefWidth(140);

		lblRotCidade = new Label("Cidade");
		edtCidade = new TextField("");
		paneCidade = new VBox(lblRotCidade,edtCidade);
		paneCidade.setPrefWidth(180);


		lblRotCep = new Label("Cep");
		edtCep = new TextField("");
		paneCep = new VBox(lblRotCep, edtCep);
		paneCep.setPrefWidth(85);


		paneLinha3 = new HBox(paneTipoAg, paneAgenRua, paneBairro,paneCidade, paneCep);
		paneLinha3.setSpacing(20);
		paneLinha3.setPadding(new Insets(30,0, 0 ,20));

		


		// --------------------------LInha 4
		lblRotGerente = new Label("Gerente");
		edtGerente = new TextField("");
		paneGerente = new VBox(lblRotGerente, edtGerente);
		paneGerente.setPrefWidth(115);

		lblRotTel = new Label("Tel.");
		edtTel = new TextField("");
		paneTel = new VBox(lblRotTel, edtTel);
		paneTel.setPrefWidth(125);

		lblRotCel = new Label("Celular");
		edtCel = new TextField("");
		paneCel = new VBox(lblRotCel, edtCel);
		paneCel.setPrefWidth(125);

		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(120);
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(120);
		btnLancar.setOnAction(e -> btnLancar_Act());
		
		btnNovoLanc = new Button("Novo Lancamento");
		btnNovoLanc.setPrefWidth(130);
		btnNovoLanc.setOnAction(e -> btnNovoLanc_Act());
		
		paneBotoes = new HBox(btnValidar, btnLancar, btnNovoLanc);
		paneBotoes.setSpacing(25);
		paneBotoes.setPadding(new Insets(17, 0, 0, 15));

		paneLinha4 = new HBox(paneGerente, paneTel, paneCel, paneBotoes);
		paneLinha4.setSpacing(20);
		paneLinha4.setPadding(new Insets(30,0,30,20));

		// ----------------------------
		
		panePrimary = new VBox(paneLinha1,paneLinha2, paneLinha3, paneLinha4);
		panePrimary.setPadding(new Insets(30,0,0,20));
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
							   
		stagePrimary.showAndWait();	
	
	}

	private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela) {
		TableView<ContasBanc> tableContas = new TableView<ContasBanc>();

		TableColumn<ContasBanc, String> colNum = new TableColumn("Num.");
		colNum.setPrefWidth(50);
		colNum.setCellValueFactory(new PropertyValueFactory<ContasBanc, String> ("CodConta"));

		TableColumn<ContasBanc, String> colCodBanco = new TableColumn("Cod");
		colCodBanco.setPrefWidth(80);
		colCodBanco.setCellValueFactory(new PropertyValueFactory<ContasBanc, String> ("CodBanco"));
			
		TableColumn<ContasBanc, String> colBanco = new TableColumn("Banco");
		colBanco.setPrefWidth(100);
		colBanco.setCellValueFactory(new PropertyValueFactory<ContasBanc, String>("NomeBanco"));
			
		TableColumn<ContasBanc, String> colConta = new TableColumn("Conta");
		colConta.setPrefWidth(80);
		colConta.setCellValueFactory(new PropertyValueFactory<ContasBanc, String>("ContaNumero"));


		TableColumn<ContasBanc, String> colAgencia = new TableColumn("Agencia");
		colAgencia.setPrefWidth(80);
		colAgencia.setCellValueFactory(new PropertyValueFactory<ContasBanc, String>("AgenBanco"));

		TableColumn<ContasBanc, String> colTitular = new TableColumn("Titular");
		colTitular.setPrefWidth(100);
		colTitular.setCellValueFactory(new PropertyValueFactory<ContasBanc, String> ("TitularConta"));

		TableColumn<ContasBanc, String> colGerente = new TableColumn("Gerente");
		colGerente.setPrefWidth(80);
		colGerente.setCellValueFactory(new PropertyValueFactory<ContasBanc,String> ("GerenteConta"));

		TableColumn<ContasBanc, String> colTel = new TableColumn("Telefone");
		colTel.setPrefWidth(100);
		colTel.setCellValueFactory(new PropertyValueFactory<ContasBanc, String> ("AgenTel"));

		TableColumn<ContasBanc, String> colCel = new TableColumn("Celular");
		colCel.setPrefWidth(100);
		colCel.setCellValueFactory(new PropertyValueFactory<ContasBanc, String> ("CelGerente"));

		TableColumn<ContasBanc, String> colCep = new TableColumn("Cep");
		colCep.setPrefWidth(80);
		colCep.setCellValueFactory(new PropertyValueFactory<ContasBanc, String> ("AgenCep"));

			
		tableContas.setItems(tabela);
		tableContas.getColumns().addAll(colNum,colBanco,colCodBanco,colAgencia, colConta, colTitular,colGerente,colTel,colCel,colCep);
		return tableContas;
	}


	private static void btnValidar_Act() {
		if ((dadosEntrados() == true)) {
			ArrayList arrListDados = new ArrayList();
			arrListDados = populaArrAtualContaBanc();
			BusinessRules regra = new BusinessContaBanc();

			if(regra.performConsistencia(arrListDados)) {
				desabilitarEdts();
				btnLancar.setDisable(false);
				btnValidar.setDisable(true);		
			}		
		}
	}	
	private static void btnLancar_Act() {
		ArrayList arrListDados = new ArrayList();
		arrListDados = populaArrAtualContaBanc();
		if(arrListDados.isEmpty()){
			MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
			stagePrimary.close();
		}

		BusinessRules regra = new BusinessContaBanc();
			regra.performLancamento(arrListDados);

		contasBancAtivas = contasBancarias.carregarContasBanc(1); 
		tbvContasBanc.setItems(contasBancAtivas);
		
//		limparEdts();
//		habilitarEdts();
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(false);	
	}
	
	private static void btnNovoLanc_Act() {
		limparEdts();
		habilitarEdts();
		btnValidar.setDisable(false);
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(true);
	}

	private static boolean dadosEntrados() {
		boolean libera = true;
		String temp = "";

		// CodConta (int) : gerado automaticamente na tabela do banco de dados

		// banco  (Str 16)
		if(edtNomeBanco.getText().length() < 4) {
			libera = false;
			MessageBox.show("O campo Banco deve ter mais que 4 caracters", "BANCO");
		}else {
			temp = edtNomeBanco.getText();
			if(temp.length() > 16) temp = temp.substring(0, 15);
			
			atualContaBanc.setNomeBanco(temp);
		}

		// codbanco  (int)
		if(edtCodBanco.getText().length() < 1) {
			libera = false;
			MessageBox.show("Deve ser indicado o codibo do banco", "BANCO");
		}else {
			try{
				atualContaBanc.setCodBanco(Integer.parseInt(edtCodBanco.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "BANCO");
			}
		}
		
		// agencia  (Str 10)
		if(edtAgenBanco.getText().length() < 4) {
			libera = false;
			MessageBox.show("E necessario preencher o campo Agencia", "AGENCIA");
		}else {
			temp = edtAgenBanco.getText();
			if(temp.length() > 10) temp = temp.substring(0, 9);
			atualContaBanc.setAgenBanco(temp);
		}
			

		// conta  (Str 12)
		if(edtContaNumero.getText().length() < 4) { 
			libera = false;
			MessageBox.show("Deve ser digitado o numero da conta ", "CONTA");
		}else {
			temp = edtContaNumero.getText();
			if(temp.length() > 12) temp = temp.substring(0,11);
			atualContaBanc.setContaNumero(temp);
		}
		
		// titularConta   (Str - 12)
		if(edtTitularConta.getText().length() < 6) { 
			libera = false;
			MessageBox.show("Deve ser digitado o nome do titular ", "TITULAR");
		}else {
			temp = edtTitularConta.getText();
			if(temp.length() > 12) temp = temp.substring(0,11);
			atualContaBanc.setTitularConta(temp);
		}

		
		// tipoAgencia  (Str -12)
		if(edtTipoAgen.getText().length() < 6) { 
			libera = false;
			MessageBox.show("Deve ser digitado o tipo da Agencia ", "AGENCIA");
		}else {
			temp = edtTipoAgen.getText();
			if(temp.length() > 12) temp = temp.substring(0,11);
			atualContaBanc.setTipoAgencia(temp);
		}


		// agenRua  (Str -20)
		if(edtAgenRua.getText().length() < 10) { 
			libera = false;
			MessageBox.show("Deve ser digitado o nome da rua  ", "AGENCIA");
		}else {
			temp = edtAgenRua.getText();
			if(temp.length() > 20) temp = temp.substring(0,19);
			atualContaBanc.setAgenRua(temp);
		}
		
		// agenBairro (Str - 15)
		if(edtBairro.getText().length() < 5) { 
			libera = false;
			MessageBox.show("Deve ser digitado o nome do bairro  ", "AGENCIA");
		}else {
			temp = edtBairro.getText();
			if(temp.length() > 15) temp = temp.substring(0,14);
			atualContaBanc.setAgenBairro(temp);
		}

		// Cidade (Str 20)
		if(edtCidade.getText().length() < 6) { 
			libera = false;
			MessageBox.show("Deve ser digitado o nome da cidade  ", "AGENCIA");
		}else {
			temp = edtCidade.getText();
			if(temp.length() > 20) temp = temp.substring(0,19);
			atualContaBanc.setAgenCidade(temp);
		}


		// agenCep  (Str 9)
		if(edtCep.getText().length() < 9) { 
			libera = false;
			MessageBox.show("Deve ser digitado o Cep   ", "AGENCIA");
		}else {
			temp = edtCep.getText();
			if(temp.length() > 9) temp = temp.substring(0, 8);
			atualContaBanc.setAgenCep(temp);
		}

		// Gerente (Str 12)
		if(edtGerente.getText().length() < 4) { 
			libera = false;
			MessageBox.show("Deve ser digitado o nome do gerente da conta  ", "AGENCIA");
		}else {
			temp = edtGerente.getText();
			if(temp.length() > 12) temp = temp.substring(0, 11);
			atualContaBanc.setGerenteConta(temp);
		}


		// agenTel (Str 13)
		if(edtTel.getText().length() < 9) { 
			libera = false;
			MessageBox.show("Deve ser digitado o numero do telefone  ", "AGENCIA");
		}else {
			temp = edtTel.getText();
			if(temp.length() > 12) temp = temp.substring(0, 11);
			atualContaBanc.setAgenTel(temp);
		}


		// agenCel (Str 13)
		if(edtCel.getText().length() < 9) { 
			libera = false;
			MessageBox.show("Deve ser digitado o numero do celular  ", "AGENCIA");
		}else {
			temp = edtTel.getText();
			if(temp.length() > 12) temp = temp.substring(0, 11);
			atualContaBanc.setCelGerente(temp);
		}
		

		// contaValida  (int) 

		atualContaBanc.setContaValida(0);
		if(libera == true) {
			atualContaBanc.setContaValida(1);   // valida: 1   encerrrada: 0
			atualContaBanc.setContaTipo("b");	// b: conta movimento bancario
		}

	
		return libera;
	}

	private static ArrayList populaArrAtualContaBanc() {
		ArrayList arrList = new ArrayList();

		arrList.add(atualContaBanc.getNomeBanco());
		arrList.add(atualContaBanc.getCodBanco());
		arrList.add(atualContaBanc.getAgenBanco()); 
		arrList.add(atualContaBanc.getContaNumero());
		arrList.add(atualContaBanc.getTipoAgencia());

		arrList.add(atualContaBanc.getTitularConta());
		arrList.add(atualContaBanc.getGerenteConta());
		arrList.add(atualContaBanc.getAgenCidade());
		arrList.add(atualContaBanc.getAgenBairro());
		arrList.add(atualContaBanc.getAgenRua());

		arrList.add(atualContaBanc.getAgenCep());
		arrList.add(atualContaBanc.getAgenTel());
		arrList.add(atualContaBanc.getCelGerente());
		arrList.add(atualContaBanc.getContaValida());
		arrList.add(atualContaBanc.getCodConta());
		arrList.add(atualContaBanc.getContaTipo());

		return arrList;

	}

	private static void limparEdts() {
		edtNomeBanco.setText("");
		edtCodBanco.setText("");
		edtAgenBanco.setText("");
		edtContaNumero.setText("");
		edtTitularConta.setText("");
		edtTipoAgen.setText("");
		edtAgenRua.setText("");
		edtBairro.setText("");
		edtCidade.setText("");
		edtCep.setText("");
		edtGerente.setText("");
		edtTel.setText("");
		edtCel.setText("");
	}

	public static void desabilitarEdts() {
		edtNomeBanco.setEditable(false);
		edtCodBanco.setEditable(false);
		edtAgenBanco.setEditable(false);
		edtContaNumero.setEditable(false);
		edtTitularConta.setEditable(false);
		edtTipoAgen.setEditable(false);
		edtAgenRua.setEditable(false);
		edtBairro.setEditable(false);
		edtCidade.setEditable(false);
		edtCep.setEditable(false);
		edtGerente.setEditable(false);
		edtTel.setEditable(false);
		edtCel.setEditable(false);
	}

	public static void habilitarEdts() {
		edtNomeBanco.setEditable(true);
		edtCodBanco.setEditable(true);
		edtAgenBanco.setEditable(true);
		edtContaNumero.setEditable(true);
		edtTitularConta.setEditable(true);
		edtTipoAgen.setEditable(true);
		edtAgenRua.setEditable(true);
		edtBairro.setEditable(true);
		edtCidade.setEditable(true);
		edtCep.setEditable(true);
		edtGerente.setEditable(true);
		edtTel.setEditable(true);
		edtCel.setEditable(true);

	}
}
