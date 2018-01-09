package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;
import javafx.collections.*;
import java.util.ArrayList;
import javafx.scene.control.cell.*;

import javafx.scene.paint.Color;
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Pagadores;
import gpw.model.ContasBanc;
import gpw.dao.DaoPagadores;
import gpw.dao.DaoContasBanc;

/*
	public static void show()
	Line 243: 	private static TableView<Pagadores> tablePagadoresCadas(ObservableList<Pagadores> tabela) {
	Line 286: 	private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela) {
	Line 303: 	private static void tbvCadastrados_Change() {
	Line 308: 	private static void tbvContasBanc_Change() {
	Line 314: 	private static void cbxCentro_Change() {
	Line 319: 	private static boolean houveAlteracao() {
	Line 364: 	private static void preencheEdts() {
	Line 388: 	private static void btnValidar_Act() {
	Line 411: 	private static void btnLancar_Act() {
	Line 427: 	private static void btnCarregar_Act() {
	Line 434: 	private static void populaAtualPagador() {
	Line 451: 	private static void inicializar() {
	Line 463: 	private static void limparAtualPagador() {
	Line 499: 	private static boolean dadosEntrados() {
	Line 600: 	private static ArrayList populaArrAtualPagador() {
	Line 619: 	private static void teste() {
*/


public class PagEdita {
	
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblCentro,lblSubCentro, lblDescr, lblNome,lblValor,lblDia,lblAgente,
				 lblInicio,lblTermino,lblRotFirstLanc, lblContas, lblContaSelec,lblFirstLanc;
	static TextField edtDescr, edtNome,edtValor,edtDia, edtAgente;
			 
	static Button  btnCarregar, btnValidar, btnLancar;
	static ChoiceBox cbxCentro, cbxSubCentro;
	static DatePicker dtpInicio, dtpTermino;
	
	static VBox panePrimary, paneCentro, paneSubCentro, paneDescr,paneNome,paneValor,
				paneDia,paneAgente,paneInicio,paneTermino,paneFirstLanc, paneLinha3d, paneLinha3e;
	static HBox paneLinha1, paneLinha2, paneLinha3a, paneLinha3b, paneLinha3c, paneLinha3;
	
	static TableView<ContasBanc> tbvContasBanc;
	static TableView<Pagadores> tbvPagadoresAtivos;
	
	static ObservableList<Pagadores> dadosPagadores;
	static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasbanc do banco de dadosPagadores
									// correspondentes as contas bancarias ativas
	
	static DaoPagadores pagadores; // Objeto criado para acessar metodos de DaoPagadores
	static Pagadores atualPagador;
	static DaoContasBanc contasBancarias;
	
	/*
			atualPagador = Objeto Pagadores cujas variaveis de instancia contem os campos 
			do registro da tabela pagadores, selecionado na TableView, é usado para:
			1- Mostrar os campos para possivel edicao
			2- Transferir através de um ArrayList, dados para os metodos de BusiRulePagadores
			
			Se houver edicao os dados sao checados quanto a forma e se validos sao enviados para consistencia no 
			metodo validar() de BusiRulePagadores. Se o metodo devolver true é habilitado o botao salvar
			
			O botao salvar envia o mesmo ArrayList validado para o metodo gravar() de BusiRulePagadores	
		*/
	
	public static void show() {
		atualPagador = new Pagadores();
		pagadores = new DaoPagadores();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Edita Pagadores");
		stagePrimary.setMinWidth(900);
		stagePrimary.setMaxWidth(900);
		stagePrimary.setMaxHeight(900);
		
		// ---------------------- Linha 1
		tbvPagadoresAtivos = new TableView<Pagadores>();
		dadosPagadores = pagadores.carregaPagadores();
		tbvPagadoresAtivos = tablePagadoresCadas(dadosPagadores);
		tbvPagadoresAtivos.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvPagadoresAtivos_Change());	
		paneLinha1 = new HBox(tbvPagadoresAtivos);
		paneLinha1.setPrefHeight(400);
		paneLinha1.setPrefWidth(850);
		paneLinha1.setPadding(new Insets(30,0,0,30));
		
		// -------------------- Linha 2
		lblDescr = new Label("Descricao");
		lblDescr.setPrefWidth(100);
		edtDescr = new TextField("");
		edtDescr.setPrefWidth(200);
		edtDescr.setPromptText("Objeto do recebimento");
		paneDescr = new VBox(lblDescr, edtDescr);
		
		lblNome = new Label("Nome do Contrato");
		lblNome.setPrefWidth(120);
		edtNome = new TextField("");
		edtNome.setPrefWidth(200);
		edtNome.setPromptText("Nome do contrato");
		paneNome = new VBox(lblNome, edtNome);
		
		lblValor = new Label("Valor");
		lblValor.setPrefWidth(80);
		edtValor = new TextField("");
		edtValor.setPrefWidth(100);
		edtValor.setPromptText("Aluguel");
		paneValor = new VBox(lblValor, edtValor);
		
		lblDia = new Label("Dia");
		lblDia.setPrefWidth(30);
		edtDia = new TextField("");
		edtDia.setPrefWidth(30);
		paneDia = new VBox(lblDia, edtDia);
		
		lblAgente = new Label("Agente");
		lblAgente.setPrefWidth(120);
		edtAgente = new TextField("");
		edtAgente.setPromptText("Pagador/Corretor");
		paneAgente = new VBox(lblAgente, edtAgente);
		
		paneLinha2 = new HBox(paneDescr, paneNome, paneValor,paneDia, paneAgente);
		paneLinha2.setSpacing(20);
		paneLinha2.setPadding(new Insets(20, 0, 0, 30));
		
		// ----------------------- Linha 3a
		lblCentro = new Label("Centros Recebimento");
		lblCentro.setPrefWidth(150);		
		cbxCentro = new ChoiceBox(Ini.loadProps("CentroReceb", "RecCen"));
		cbxCentro.setPrefWidth(150);
		cbxCentro.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> cbxCentro_Change());
		paneCentro = new VBox(lblCentro, cbxCentro);
		paneCentro.setPadding(new Insets(0,0,0,0));
		
		lblSubCentro = new Label("Sub-Centros");
		lblSubCentro.setPrefWidth(150);
		cbxSubCentro = new ChoiceBox();
		cbxSubCentro.setPrefWidth(150);

		paneSubCentro = new VBox(lblSubCentro, cbxSubCentro);
		paneSubCentro.setPadding(new Insets(0,0,0,0));	
		paneLinha3a = new HBox(paneCentro,paneSubCentro);
		paneLinha3a.setSpacing(120);
		paneLinha3a.setPadding(new Insets(0, 0, 0, 0));
		
		// ------------------------- Linha 3b
		lblInicio = new Label("Inicio");
		lblInicio.setPrefWidth(70);		
		lblTermino = new Label("Termino");
		lblTermino.setPrefWidth(70);
		lblRotFirstLanc = new Label("Prim.Lanc.");
		lblRotFirstLanc.setPrefWidth(70);
		
		dtpInicio = new DatePicker();
		dtpInicio.setPrefWidth(140);
		dtpInicio.setOnAction(event -> {atualPagador.setContratoInic(dtpInicio.getValue());});
		paneInicio = new VBox(lblInicio, dtpInicio);
		paneInicio.setPadding(new Insets(0,0,0,0));
		
		dtpTermino = new DatePicker();
		dtpTermino.setPrefWidth(140);
		dtpTermino.setOnAction(event -> {atualPagador.setContratoFim(dtpTermino.getValue());});
		paneTermino = new VBox(lblTermino, dtpTermino);
		
		lblFirstLanc = new Label();
		lblFirstLanc.setPrefWidth(140);
		lblFirstLanc.setPadding(new Insets(5,0,0,0));
		paneFirstLanc = new VBox(lblRotFirstLanc, lblFirstLanc);
		paneFirstLanc.setPadding(new Insets(0,0,0,0));
		
		paneLinha3b = new HBox(paneInicio, paneTermino, paneFirstLanc);	
		paneLinha3b.setSpacing(35);
		paneLinha3b.setPadding(new Insets(20,0,0,0));
		
		// ----------------------- Linha 3c
		btnCarregar = new Button("Carregar");
		btnCarregar.setPrefWidth(130);
		btnCarregar.setOnAction(e -> btnCarregar_Act());
		
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(120);
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(120);
		btnLancar.setOnAction(e -> btnLancar_Act());
		
		paneLinha3c = new HBox(btnCarregar, btnValidar, btnLancar);
		paneLinha3c.setSpacing(25);
		paneLinha3c.setPadding(new Insets(20, 0, 0, 0));
		
		// ----------------------- Linha 3d
		paneLinha3d = new VBox(paneLinha3a, paneLinha3b,paneLinha3c);
		paneLinha3d.setPadding(new Insets(0,0,0,0));
		
		// ----------------------- Linha 3e
		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancAtivas = ObservableList
		
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);
		tbvContasBanc.setItems(contasBancAtivas);	
		tbvContasBanc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasBanc_Change());
		
		lblContas = new Label("Contas bancarias");
		lblContas.setPrefWidth(150);
		
		lblContaSelec = new Label("Conta Selecionada: ");
		lblContaSelec.setPrefWidth(300);
		lblContaSelec.setPadding(new Insets(10,0,0,0));
		
		paneLinha3e = new VBox(lblContas, tbvContasBanc,lblContaSelec);
		paneLinha3e.setPrefHeight(140);
		paneLinha3e.setPrefWidth(220);
		paneLinha3e.setPadding(new Insets(0, 0 , 0 , 0));
		
		// -------------------------- Linha 3
		paneLinha3 = new HBox(paneLinha3d, paneLinha3e);
		paneLinha3.setSpacing(50);
		paneLinha3.setPadding(new Insets(20,0, 20,30));
		
		// -------------------------
		panePrimary = new VBox(paneLinha1,paneLinha2, paneLinha3);
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		inicializar();
		stagePrimary.showAndWait();
	}

	private static TableView<Pagadores> tablePagadoresCadas(ObservableList<Pagadores> tabela) {
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
	
	private static void tbvPagadoresAtivos_Change() {
		inicializar();
		btnCarregar.setDisable(false);	
	}
	
	private static void tbvContasBanc_Change() {
		lblContaSelec.setText("Conta Selecionada: " + 
					tbvContasBanc.getSelectionModel().getSelectedItem().getNomeBanco() + " - " + 
					 tbvContasBanc.getSelectionModel().getSelectedItem().getContaNumero());		
	}
	
	private static void cbxCentro_Change() {
		int i = cbxCentro.getSelectionModel().getSelectedIndex();
		cbxSubCentro.setItems(Ini.loadProps("RecCen" + i, "SubReceb"));	
	}
	
	private static void preencheEdts() {
//		atualPagador.getCodPagador());
//		atualPagador.getEstPagador());
		edtDescr.setText(atualPagador.getRecDescr());
		edtValor.setText(Double.toString(atualPagador.getValContrato()));
		dtpInicio.setValue(atualPagador.getContratoInic());	
		dtpTermino.setValue(atualPagador.getContratoFim());
		edtNome.setText(atualPagador.getNomeContrato());
		
		tbvContasBanc.getSelectionModel().select(atualPagador.getContaVinc() -1);
		lblContaSelec.setTextFill(Color.BLACK);
		lblContaSelec.setText("Conta Selecionada: " + 
					tbvContasBanc.getSelectionModel().getSelectedItem().getNomeBanco() + " - " + 
					 tbvContasBanc.getSelectionModel().getSelectedItem().getContaNumero());
					 
		cbxCentro.getSelectionModel().select(atualPagador.getCentroReceb());
		cbxSubCentro.setItems(Ini.loadProps("RecCen" + atualPagador.getCentroReceb(), "SubReceb"));
		cbxSubCentro.getSelectionModel().select(atualPagador.getSubCentroRec());

		lblFirstLanc.setText(DateUtils.asString(atualPagador.getDataLanc()));
		edtDia.setText(Integer.toString(atualPagador.getDiaVenc()));
		edtAgente.setText(atualPagador.getAgente());	
	}
	
	private static void btnValidar_Act() {
		if(dadosEntrados()) { // Verifica se os campos tem valores formalmente aceitaveis
			ArrayList arrListDados = new ArrayList();
			arrListDados = populaArrAtualPagador();
				
			BusinessRules regra = new BusinessPagadores();
									// Usando mesmo metodo de BusiPagCadastra
			if(regra.performConsistencia(arrListDados)){
													// Verifica se os dados entrados tem consistencia com as regras do
													// negocio 
				btnValidar.setDisable(true);
				btnLancar.setDisable(false);
			}else{
				tbvPagadoresAtivos_Change();
			}			
		}
	}
	
	private static void btnLancar_Act() {
		ArrayList arrListDados = new ArrayList();
		arrListDados = populaArrAtualPagador();

		if(arrListDados.isEmpty()) {
			MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
			stagePrimary.close();
		}

		BusinessRules regra = new BusinessPagadores();
		regra.performEdicao(arrListDados);
		btnLancar.setDisable(true);
		dadosPagadores = pagadores.carregaPagadores();
		tbvPagadoresAtivos.setItems(dadosPagadores);
	}
	
	private static void btnCarregar_Act() {
		populaAtualPagador();  // dados do registro selec. no tbvPagadoresAtivos -> atualPagador
		preencheEdts();		   // dados do atualPagador -> nos campos da tela
		btnValidar.setDisable(false);
		btnCarregar.setDisable(true);
	}
	
	private static void populaAtualPagador() {

		atualPagador.setCodPagador((Integer)tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getCodPagador());
		atualPagador.setEstPagador(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getEstPagador());
		atualPagador.setRecDescr(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getRecDescr());
		atualPagador.setValContrato(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getValContrato());
		atualPagador.setContratoInic(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getContratoInic());	
		atualPagador.setContratoFim(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getContratoFim());
		atualPagador.setNomeContrato(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getNomeContrato());
		atualPagador.setContaVinc(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getContaVinc());
		atualPagador.setCentroReceb(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getCentroReceb());
		atualPagador.setSubCentroRec(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getSubCentroRec());
		atualPagador.setDataLanc(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getDataLanc());
		atualPagador.setDiaVenc(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getDiaVenc());
		atualPagador.setAgente(tbvPagadoresAtivos.getSelectionModel().getSelectedItem().getAgente());
	}
	
	private static void inicializar() {
		limparAtualPagador();
		limparCampos();
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		btnCarregar.setDisable(true);
		dtpInicio.setValue(LocalDate.now());
		dtpTermino.setValue(LocalDate.now());
		lblFirstLanc.setText("");
		cbxCentro.getSelectionModel().select(0);
	}
	
	private static void limparAtualPagador() {
		atualPagador.setCodPagador(0);
		atualPagador.setEstPagador("");
		atualPagador.setRecDescr("");
		atualPagador.setValContrato(0.);
		atualPagador.setContratoInic(LocalDate.now());	
		atualPagador.setContratoFim(LocalDate.now());
		atualPagador.setNomeContrato("");
		atualPagador.setContaVinc(0);
		atualPagador.setCentroReceb(0);
		atualPagador.setSubCentroRec(0);
		atualPagador.setDataLanc(LocalDate.now());
		atualPagador.setDiaVenc(0);
		atualPagador.setAgente("");
	}
	
	public static void limparCampos() {
		edtDescr.setText("");
		edtNome.setText("");
		edtValor.setText("");
		edtDia.setText("");
		edtAgente.setText("");
		lblContaSelec.setText("Conta Selecionada: ");
		tbvContasBanc.getSelectionModel().select(-1);
		dtpInicio.setValue(LocalDate.now());
		dtpTermino.setValue(LocalDate.now());
		lblFirstLanc.setText("");
	}
	
	private static boolean dadosEntrados() {
		boolean libera = true;

		// Cod Pagador : gerado automaticamente na tabela do banco de dados

		// Estado
		atualPagador.setEstPagador("A");

		//RecDescr		
		if(edtDescr.getText().length() < 6) {
			libera = false;
			MessageBox.show("O campo deve ter mais de 5 caracteres", "DESCRICAO");
		}else {
			String temp = edtDescr.getText();
			if(temp.length() > 34) temp = temp.substring(0, 34);
			atualPagador.setRecDescr(temp);
		}
		
		//ValCotrato
		if(edtValor.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor do aluguel", "VALOR");
		}else
			try {
				atualPagador.setValContrato(Double.parseDouble(edtValor.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}

		//Inicio
		if(!(dtpInicio.getValue() == null)) 
			atualPagador.setContratoInic(dtpInicio.getValue());
		else {
			libera = false;
			MessageBox.show("Deve ser selecionada data de Inicio ", "DATAS");
		}
				
		// Termino
		if(!(dtpTermino.getValue() == null)) 
			atualPagador.setContratoFim(dtpTermino.getValue());	
		else{
			libera = false;
			MessageBox.show("Deve ser selecionada data de  Termino", "DATAS");
		}
		
		// Nome
		if(edtNome.getText().length() < 6) {
			libera = false;
			MessageBox.show(" O campo deve ter mais de 5 caracteres", "NOME" );
		}else {
			String temp1 = edtNome.getText();
			if(temp1.length() > 34) temp1 = temp1.substring(0, 34);
			atualPagador.setNomeContrato(temp1);
		}

		// Conta Vinc
		if(tbvContasBanc.getSelectionModel().getSelectedIndex() < 0) {
			MessageBox.show("Deve ser escolhida uma conta bancaria", "Conta Banc");
			libera = false;
		}else
			atualPagador.setContaVinc(tbvContasBanc.getSelectionModel().getSelectedIndex() + 1);	

		
		// Centro e SubCentro
		if((cbxCentro.getSelectionModel().getSelectedIndex() < 0) || (cbxCentro.getSelectionModel().getSelectedIndex() < 0)) {
			libera = false;
			MessageBox.show("Deve ser selecionado Grupo e Subgrupo", "CLASSIFICACAO");
		}else{
			atualPagador.setCentroReceb(cbxCentro.getSelectionModel().getSelectedIndex());
			atualPagador.setSubCentroRec(cbxSubCentro.getSelectionModel().getSelectedIndex());
		}
		
		// Data Lancamento (Primeiro contrato)
				// mantem o valor lido do registro selecionado em tbvPagadoresAtivos

		// Dia Vencimento
		try {
			atualPagador.setDiaVenc(Integer.parseInt(edtDia.getText()));
			if((atualPagador.getDiaVenc() < 1) || (atualPagador.getDiaVenc()) > 28) {
				libera = false;
				MessageBox.show("O dia de pagamento deve estar entre 1 e 28", "DIA PAGAMENTO");
			}	
		}catch(NumberFormatException ne) {
			libera = false;
			MessageBox.show("Digitacao numero em forma nao valida", "DIA PAGAMENTO");	
		}
		
		// Agente
		if(edtAgente.getText().length() < 2) {
			atualPagador.setAgente("Nao aplicavel"); 
		}else {
			String temp2 = edtAgente.getText();
			if(temp2.length() > 20) temp2 = temp2.substring(0, 19);
			atualPagador.setAgente(temp2);
		}
		return libera;
	}
	
	private static ArrayList populaArrAtualPagador() {
		ArrayList arrList = new ArrayList();
		
		arrList.add(atualPagador.getCodPagador());
		arrList.add(atualPagador.getEstPagador());
		arrList.add(atualPagador.getRecDescr());
		arrList.add(atualPagador.getValContrato());
		arrList.add(atualPagador.getContratoInic());	
		arrList.add(atualPagador.getContratoFim());
		arrList.add(atualPagador.getNomeContrato());
		arrList.add(atualPagador.getContaVinc());
		arrList.add(atualPagador.getCentroReceb());
		arrList.add(atualPagador.getSubCentroRec());
		arrList.add(atualPagador.getDataLanc());
		arrList.add(atualPagador.getDiaVenc());
		arrList.add(atualPagador.getAgente());
		return arrList;
	}
}
