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
		public FormularioBcontaEncerra() {
		public static void show() {
		private static void btnValidar_Act() {
*/


public class FormularioBcontaEncerra extends FormulariosBconta {

	public FormularioBcontaEncerra() {
		formSelecionado = new FormBcontaEncerra();	
	}

	public static void show() {

		atualContaBanc = new ContasBanc();

		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Encerra Conta Bancaria");
		stagePrimary.setMinWidth(940);
		stagePrimary.setMaxWidth(940);
		stagePrimary.setMaxHeight(940);

		// --------------------- Linha 1

		lblRotContasAtivas = new Label("Contas Bancarias Ativas");

		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // Este metodo está em dao/DaoContasBanc
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);			// Metodo está em ContaBancCadastra
		tbvContasBanc.setItems(contasBancAtivas);	

		tbvContasBanc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasBanc_Change());	
		
		paneContasAtivas = new VBox(lblRotContasAtivas,tbvContasBanc);
		paneContasAtivas.setPrefHeight(200);
		paneContasAtivas.setPrefWidth(850);
		paneContasAtivas.setPadding(new Insets(0, 0 , 0 , 0));

		paneLinha1 = new HBox(paneContasAtivas);
		paneLinha1.setPadding(new Insets(30,0,0,20));
	
	
		// --------------------  Linha 2
		lblRotBanco = new Label("Banco");
		edtNomeBanco = new TextField("");
		edtNomeBanco.setStyle("-fx-text-inner-color: blue;");
		edtNomeBanco.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtNomeBanco.setEditable(false);
		paneBanco = new VBox(lblRotBanco, edtNomeBanco);
		paneBanco.setPrefWidth(150);

		lblRotCodBanco = new Label("NumBanco");
		edtCodBanco = new TextField("");
		edtCodBanco.setStyle("-fx-text-inner-color: blue;");
		edtCodBanco.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtCodBanco.setEditable(false);
		paneCodBanco = new VBox(lblRotCodBanco,edtCodBanco);
		paneCodBanco.setPrefWidth(80);
		
		lblRotAgencia = new Label("Agencia");
		edtAgenBanco = new TextField("");
		edtAgenBanco.setStyle("-fx-text-inner-color: blue;");
		edtAgenBanco.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtAgenBanco.setEditable(false);
		paneAgencia = new VBox(lblRotAgencia,edtAgenBanco);
		paneAgencia.setPrefWidth(100);
		
		lblRotConta = new Label("Conta");
		edtContaNumero = new TextField("");
		edtContaNumero.setStyle("-fx-text-inner-color: blue;");
		edtContaNumero.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtContaNumero.setEditable(false);
		paneConta = new VBox(lblRotConta,edtContaNumero);
		paneConta.setPrefWidth(120);
		
		lblRotTitular = new Label("Titular");
		edtTitularConta = new TextField("");
		edtTitularConta.setStyle("-fx-text-inner-color: blue;");
		edtTitularConta.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtTitularConta.setEditable(false);
		paneTitular = new VBox(lblRotTitular,edtTitularConta);
		paneTitular.setPrefWidth(250);

		paneLinha2 = new HBox(paneBanco, paneCodBanco, paneAgencia, paneConta, paneTitular);
		paneLinha2.setSpacing(20);
		paneLinha2.setPadding(new Insets(30,0,0,20));
	
		// ---------------------- Linha 3
		lblRotTipoAg = new Label("Tipo Agencia");
		edtTipoAgen = new TextField("");
		edtTipoAgen.setStyle("-fx-text-inner-color: blue;");
		edtTipoAgen.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtTipoAgen.setEditable(false);
		paneTipoAg = new VBox(lblRotTipoAg, edtTipoAgen);
		paneTipoAg.setPrefWidth(115);

		lblRotAgenRua = new Label("Rua");
		edtAgenRua = new TextField("");
		edtAgenRua.setStyle("-fx-text-inner-color: blue;");
		edtAgenRua.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtAgenRua.setEditable(false);
		paneAgenRua = new VBox(lblRotAgenRua,edtAgenRua);
		paneAgenRua.setPrefWidth(262);

		lblRotBairro = new Label("Bairro");
		edtBairro = new TextField("");
		edtBairro.setStyle("-fx-text-inner-color: blue;");
		edtBairro.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtBairro.setEditable(false);
		paneBairro = new VBox(lblRotBairro,edtBairro);
		paneBairro.setPrefWidth(130);

		lblRotCidade = new Label("Cidade");
		edtCidade = new TextField("");
		edtCidade.setStyle("-fx-text-inner-color: blue;");
		edtCidade.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtCidade.setEditable(false);
		paneCidade = new VBox(lblRotCidade,edtCidade);
		paneCidade.setPrefWidth(180);

		lblRotCep = new Label("Cep");
		edtCep = new TextField("");
		edtCep.setStyle("-fx-text-inner-color: blue;");
		edtCep.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtCep.setEditable(false);
		paneCep = new VBox(lblRotCep, edtCep);
		paneCep.setPrefWidth(95);

		paneLinha3 = new HBox(paneTipoAg, paneAgenRua, paneBairro,paneCidade, paneCep);
		paneLinha3.setSpacing(20);
		paneLinha3.setPadding(new Insets(30,0, 0 ,20));

	
		// --------------------------LInha 4
		lblRotGerente = new Label("Gerente");
		edtGerente = new TextField("");
		edtGerente.setStyle("-fx-text-inner-color: blue;");
		edtGerente.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtGerente.setEditable(false);
		paneGerente = new VBox(lblRotGerente, edtGerente);
		paneGerente.setPrefWidth(115);

		lblRotTel = new Label("Tel.");
		edtTel = new TextField("");
		edtTel.setStyle("-fx-text-inner-color: blue;");
		edtTel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtTel.setEditable(false);
		paneTel = new VBox(lblRotTel, edtTel);
		paneTel.setPrefWidth(125);

		lblRotCel = new Label("Celular");
		edtCel = new TextField("");
		edtCel.setStyle("-fx-text-inner-color: blue;");
		edtCel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		edtCel.setEditable(false);
		paneCel = new VBox(lblRotCel, edtCel);
		paneCel.setPrefWidth(125);

		btnValidar = new Button("Encerrar");
		btnValidar.setPrefWidth(115);
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(115);
//		btnLancar.setOnAction(e -> btnLancar_Act());
		
		btnNovoLanc = new Button("Novo Lanc.");
		btnNovoLanc.setPrefWidth(120);
		btnNovoLanc.setOnAction(e -> btnNovoLanc_Act());
		
		paneBotoes = new HBox(btnValidar, btnNovoLanc);
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

	protected static void btnValidar_Act() {
		chave = dadosEntrados();	
		atualContaBanc.setCodConta(tbvContasBanc.getSelectionModel().getSelectedItem().getCodConta());
		arrListDados = populaArrAtualContaBanc();
		if(chave) {
			BusinessRules regra = new BusinessBconta();
			if(regra.performLiberacao(arrListDados)) {
				arrListDados.set(13, 0);

				BusinessRules regraNova = new BusinessContaBanc();
				if(regraNova.performEdicao(arrListDados)) {
					limparEdts();
					habilitarEdts();
					contasBancAtivas = contasBancarias.carregarContasBanc(1);  // Este metodo está em dao/DaoContasBanc
					tbvContasBanc.setItems(contasBancAtivas);	
				}
				btnValidar.setDisable(true);
				btnNovoLanc.setDisable(false);		
			}else {
				MessageBox.show("Esta conta nao possui saldo zero ", "LANCAMENTOj");
			}
		}
	}


	protected static void btnNovoLanc_Act() {
		limparEdts();
		habilitarEdts();
		btnValidar.setDisable(false);
		btnNovoLanc.setDisable(true);
	}			
}