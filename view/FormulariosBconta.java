package gpw.view;

import java.util.ArrayList;



import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.scene.control.cell.*;
import javafx.collections.*;
import java.util.ArrayList;
import javafx.scene.text.*;

import gpw.control.*;
import gpw.model.ContasBanc;
import gpw.dao.DaoContasBanc;

/*
	protected static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela) {
	protected static void tbvContasBanc_Change() {
	protected static void btnValidar_Act() {
	protected static void processValidar() {
	protected static void btnLancar_Act() {
	protected static void btnNovoLanc_Act() {
	protected  static void limparEdts() {
	protected static void preencheEdts() {
	protected static boolean dadosEntrados() {
	protected static ArrayList populaArrAtualContaBanc()
	protected static void desabilitarEdts() {
	protected static void habilitarEdts() {
	public boolean performCadastra() {
	public boolean performMostra() {
	public boolean performEdita() {
	public boolean performConfirma() {
	public boolean performCancela() {
	public boolean performValida() {
	public boolean performLanca() {

*/



public abstract class FormulariosBconta {
	FormulariosBconta() {}

	FormSelec formSelecionado;

	protected static Stage stagePrimary;
	protected static Scene scenePrimary;
	
	protected static VBox panePrimary, paneBanco,paneCodBanco,paneAgencia,paneConta,paneTitular;
	protected static VBox paneTipoAg, paneAgenRua, paneBairro,paneCidade, paneCep;
	protected static VBox paneGerente, paneTel, paneCel, paneContasAtivas;

	protected static HBox paneLinha1,paneLinha2, paneLinha3,paneBotoes,paneLinha4;
	
	protected static Label lblRotBanco,lblRotCodBanco,lblRotAgencia,lblRotConta,lblRotTitular;
	protected static TextField edtNomeBanco,edtCodBanco,edtAgenBanco,edtContaNumero,edtTitularConta;

	protected static Label lblRotTipoAg, lblRotAgenRua,lblRotBairro, lblRotCidade, lblRotCep, lblRotContasAtivas;
	protected static TextField edtTipoAgen, edtAgenRua, edtBairro, edtCidade,edtCep;

	protected static Label lblRotGerente, lblRotTel, lblRotCel;
	protected static TextField edtGerente, edtTel, edtCel;

	protected static Button btnValidar, btnLancar, btnNovoLanc;


	protected static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasBancariasbanc do banco de dados
									// correspondentes as contas bancarias ativas
	protected static TableView<ContasBanc> tbvContasBanc;
	protected static DaoContasBanc contasBancarias;
	protected static ContasBanc atualContaBanc;


	protected static ArrayList arrListDados;
	protected static boolean chave;



	protected static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela) {
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

	protected static void tbvContasBanc_Change() {
		preencheEdts();
	}

	protected static void btnValidar_Act() {}

	protected static void btnLancar_Act() {}

	protected static void btnNovoLanc_Act() {}

	protected  static void limparEdts() {
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

	protected static void preencheEdts() {
		
		if(tbvContasBanc.getSelectionModel().getSelectedIndex() > -1) {

			edtNomeBanco.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getNomeBanco());
			edtCodBanco.setText(Integer.toString(tbvContasBanc.getSelectionModel().getSelectedItem().getCodBanco()));
			edtAgenBanco.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getAgenBanco());
			edtContaNumero.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getContaNumero());
			edtTitularConta.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getTitularConta());
			edtTipoAgen.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getTipoAgencia());
			edtAgenRua.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getAgenRua());
			edtBairro.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getAgenBairro());
			edtCidade.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getAgenCidade());
			edtCep.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getAgenCep());
			edtGerente.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getGerenteConta());
			edtTel.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getAgenTel());
			edtCel.setText(tbvContasBanc.getSelectionModel().getSelectedItem().getCelGerente());
		}
	}


	protected static boolean dadosEntrados() {
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

	protected static ArrayList populaArrAtualContaBanc() {
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


	protected static void desabilitarEdts() {
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

		protected static void habilitarEdts() {
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


	public boolean performCadastra() {
		boolean resultado = false;
		resultado = formSelecionado.cadastrar();
		return resultado;
	}

	public boolean performMostra() {
		boolean resultado = false;
		resultado = formSelecionado.mostrar();
		return resultado;
	}

	public boolean performEdita() {
		boolean resultado = false;
		resultado = formSelecionado.editar();
		return resultado;
	}

	public boolean performConfirma() {
		boolean resultado = false;
		resultado = formSelecionado.confirmar();
		return resultado;
	}

	public boolean performCancela() {
		boolean resultado = false;
		resultado = formSelecionado.cancelar();
		return resultado;
	}


	public boolean performValida() {
		boolean resultado = false;
		resultado = formSelecionado.validar();
		return resultado;
	}

	public boolean performLanca() {
		boolean resultado = false;
		resultado = formSelecionado.lancar();
		return resultado;
	}



}
