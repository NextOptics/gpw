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
	public FormularioBcontaCadastra() {
	public static void show() {	
*/


public class FormularioBcontaCadastra  extends FormulariosBconta{

	public FormularioBcontaCadastra() {
		formSelecionado = new FormBcontaCadastra();	
	}

	public static void show() {	

		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Cadastra Conta Bancaria");
		stagePrimary.setMinWidth(940);
		stagePrimary.setMaxWidth(940);
		stagePrimary.setMaxHeight(940);

		// --------------------- Linha 1

		lblRotContasAtivas = new Label("Contas Bancarias Ativas");

		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancarias = ObservableList	
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);
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
		edtNomeBanco.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneBanco = new VBox(lblRotBanco, edtNomeBanco);
		paneBanco.setPrefWidth(150);

		lblRotCodBanco = new Label("NumBanco");
		edtCodBanco = new TextField("");
		edtCodBanco.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneCodBanco = new VBox(lblRotCodBanco,edtCodBanco);
		paneCodBanco.setPrefWidth(80);
		
		lblRotAgencia = new Label("Agencia");
		edtAgenBanco = new TextField("");
		edtAgenBanco.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneAgencia = new VBox(lblRotAgencia,edtAgenBanco);
		paneAgencia.setPrefWidth(100);
		
		lblRotConta = new Label("Conta");
		edtContaNumero = new TextField("");
		edtContaNumero.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneConta = new VBox(lblRotConta,edtContaNumero);
		paneConta.setPrefWidth(120);
		
		lblRotTitular = new Label("Titular");
		edtTitularConta = new TextField("");
		edtTitularConta.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneTitular = new VBox(lblRotTitular,edtTitularConta);
		paneTitular.setPrefWidth(250);

		paneLinha2 = new HBox(paneBanco, paneCodBanco, paneAgencia, paneConta, paneTitular);
		paneLinha2.setSpacing(20);
		paneLinha2.setPadding(new Insets(30,0,0,20));

		// ---------------------- Linha 3
		lblRotTipoAg = new Label("Tipo Agencia");
		edtTipoAgen = new TextField("");
		edtTipoAgen.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneTipoAg = new VBox(lblRotTipoAg, edtTipoAgen);
		paneTipoAg.setPrefWidth(115);

		lblRotAgenRua = new Label("Rua");
		edtAgenRua = new TextField("");
		edtAgenRua.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneAgenRua = new VBox(lblRotAgenRua,edtAgenRua);
		paneAgenRua.setPrefWidth(262);

		lblRotBairro = new Label("Bairro");
		edtBairro = new TextField("");
		edtBairro.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneBairro = new VBox(lblRotBairro,edtBairro);
		paneBairro.setPrefWidth(130);

		lblRotCidade = new Label("Cidade");
		edtCidade = new TextField("");
		edtCidade.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneCidade = new VBox(lblRotCidade,edtCidade);
		paneCidade.setPrefWidth(175);

		lblRotCep = new Label("Cep");
		edtCep = new TextField("");
		edtCep.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneCep = new VBox(lblRotCep, edtCep);
		paneCep.setPrefWidth(100);

		paneLinha3 = new HBox(paneTipoAg, paneAgenRua, paneBairro,paneCidade, paneCep);
		paneLinha3.setSpacing(20);
		paneLinha3.setPadding(new Insets(30,0, 0 ,20));

		// --------------------------LInha 4
		lblRotGerente = new Label("Gerente");
		edtGerente = new TextField("");
		edtGerente.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneGerente = new VBox(lblRotGerente, edtGerente);
		paneGerente.setPrefWidth(115);

		lblRotTel = new Label("Tel.");
		edtTel = new TextField("");
		edtTel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneTel = new VBox(lblRotTel, edtTel);
		paneTel.setPrefWidth(135);

		lblRotCel = new Label("Celular");
		edtCel = new TextField("");
		edtCel.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		paneCel = new VBox(lblRotCel, edtCel);
		paneCel.setPrefWidth(135);

		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(115);
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(115);
		btnLancar.setOnAction(e -> btnLancar_Act());
		
		btnNovoLanc = new Button("Novo Lanc.");
		btnNovoLanc.setPrefWidth(120);
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


	protected static void btnValidar_Act() {
		atualContaBanc = new ContasBanc();
		chave = dadosEntrados();
		arrListDados = populaArrAtualContaBanc();
		atualContaBanc.setCodConta(0);
		if (chave) {
			BusinessRules regra = new BusinessContaBanc();
			if(regra.performConsistencia(arrListDados)) {										
				desabilitarEdts();
				btnLancar.setDisable(false);
				btnValidar.setDisable(true);		
			}
		}
	}


	protected static void btnLancar_Act() {

		BusinessRules regra = new BusinessContaBanc();
		if(regra.performLancamento(arrListDados)){				// Cria um novo registro na tabela contasbanc

			contasBancarias = new DaoContasBanc();							
			contasBancAtivas = contasBancarias.carregarContasBanc(1); 		
			tbvContasBanc.setItems(contasBancAtivas);
			tbvContasBanc.getSelectionModel().selectLast();    // Carrega contas bancarias ativas com a ultima criada


			ArrayList arrListBconta = new ArrayList();
			arrListBconta.add(tbvContasBanc.getSelectionModel().getSelectedItem().getCodConta());

			BusinessRules regraBconta = new BusinessBconta();
			if(regraBconta.performCriacao(arrListBconta)){      // cria uma conta movimento correspondente a conta criada
				limparEdts();
				habilitarEdts();			
			}else{
				MessageBox.show("Falha na criacao da conta MOVIMENTO ", "LANCAMENTO");
			}

			
		} else {
			MessageBox.show("Falha na criacao da conta ", "LANCAMENTO");
		}

		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(false);
	}

	protected static void btnNovoLanc_Act() {
		limparEdts();
		habilitarEdts();
		btnValidar.setDisable(false);
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(true);
	}

}