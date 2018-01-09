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

import java.time.LocalDate;
import gpw.control.*;

import gpw.model.Pagadores;
import gpw.model.ContasBanc;
import gpw.dao.DaoPagadores;
import gpw.dao.DaoContasBanc;


	/*
	private static void btnValidar_Act()
	private static void btnLancar_Act() 
	private static void btnNovoLanc_Act()
	private static void cbxCentro_Change()
	private static void tbvContasBanc_Change()
	private static void cbxSubCentro_Change()
	private static TableView<Pagadores> tablePagadoresCadas(ObservableList<Pagadores> tabela)
	private static TableView<ContasBanc> criaTableContas(ObservableList<ContasBanc> tabela)
	private static boolean dadosEntrados()
	private static void inicializar()
	private static void limparEdts()
	private static void desabilitaTxts()
	private static void habilitarEdts()
	private static ArrayList populaArrAtualPagador()
	private static void limparAtualPagador()
*/	


public class PagCadastra{	
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblCentro,lblSubCentro,lblDescr,lblNome,lblValor,lblDia,lblAgente,
				 lblInicio, lblTermino,lblFirstContr, lblContas, lblContaSelec, lblFirstLanc;
	static TextField edtDescr, edtNome,edtValor,edtDia, edtAgente;
	static Button btnValidar, btnLancar, btnNovoLanc;
	static ChoiceBox cbxCentro, cbxSubCentro;
	static DatePicker dtpInicio, dtpTermino;
	static VBox panePrimary, paneCentro, paneSubCentro,paneDescr, paneNome,paneValor,paneDia,
				paneAgente, paneInicio, paneTermino,paneFirstContr, paneLinha4c, paneLinha4d;
	static HBox paneLinha1, paneLinha2,paneLinha3,paneLinha4a, paneLinha4b, paneLinha4;
	
	static ObservableList<Pagadores> dadosPagadores;  // Contem os registros lidos  da tabela pagadores do banco de dados
									// correspondentes aos pagadores ativos com o Centro e SubCentro especificados
									
	static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasBancariasbanc do banco de dados
									// correspondentes as contas bancarias ativas
									
	static TableView<Pagadores> tbvPagadores;
	static TableView<ContasBanc> tbvContasBanc;
	
	static DaoPagadores pagadores; // Objeto criado para acessar metodos de DaoPagadores.
	static Pagadores atualPagador;
	static DaoContasBanc contasBancarias;
	
		/*
			atualPagador = Objeto Pagadores cujas variaveis de instancia contem os campos entrados, é usado
			para transferir através de um ArrayList, dados para os metodos de BusiRulePagadores
			
			Os dados entrados sao checados quanto a forma e se validos sao enviados para consistencia no 
			metodo validar() de BusiRulePagadores. Se o metodo devolver true é habilitado o botao salvar
			
			O botao salvar envia o mesmo ArrayList validado para o metodo gravar() de BusiRulePagadores
		
		*/
	
	
	public static void show() {
		atualPagador = new Pagadores();
		pagadores = new DaoPagadores();
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Cadastra Pagadores");
		stagePrimary.setMinWidth(900);
		stagePrimary.setMaxWidth(900);
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

		// ---------------- Linha 2		
		tbvPagadores = new TableView<Pagadores>();
		tbvPagadores = tablePagadoresCadas(dadosPagadores);//
		paneLinha2 = new HBox(tbvPagadores);
		paneLinha2.setPrefHeight(200);
		paneLinha2.setPadding(new Insets(30,0,0,0));

		// -----------------  Linha 3		
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
		
		paneLinha3 = new HBox(paneDescr, paneNome, paneValor,paneDia, paneAgente);
		paneLinha3.setSpacing(20);
		paneLinha3.setPadding(new Insets(20, 0, 0, 0));

		// -----------------  Linha 4		
		lblInicio = new Label("Inicio");
		lblInicio.setPrefWidth(70);
		dtpInicio = new DatePicker();
		dtpInicio.setPrefWidth(140);
		dtpInicio.setOnAction(event -> {atualPagador.setContratoInic(dtpInicio.getValue());});
		paneInicio = new VBox(lblInicio, dtpInicio);
		paneInicio.setPadding(new Insets(0,0,0,0));
		
		lblTermino = new Label("Termino");
		lblTermino.setPrefWidth(70);
		dtpTermino = new DatePicker();
		dtpTermino.setPrefWidth(140);
		dtpTermino.setOnAction(event -> {atualPagador.setContratoFim(dtpTermino.getValue());});
		paneTermino = new VBox(lblTermino, dtpTermino);
		
		lblFirstContr = new Label("Prim.Lanc.");
		lblFirstContr.setPrefWidth(70);
		lblFirstLanc = new Label(DateUtils.asString(LocalDate.now()));
		lblFirstLanc.setPrefWidth(140);
		lblFirstLanc.setPadding(new Insets(5,0,0,0));
		paneFirstContr = new VBox(lblFirstContr, lblFirstLanc);
		paneFirstContr.setPadding(new Insets(0,0,0,0));
		
		paneLinha4a = new HBox(paneInicio, paneTermino, paneFirstContr);	
		paneLinha4a.setSpacing(35);
		paneLinha4a.setPadding(new Insets(20,0,0,0));
		
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(120);
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(120);
		btnLancar.setOnAction(e -> btnLancar_Act());
		
		btnNovoLanc = new Button("Novo Lancamento");
		btnNovoLanc.setPrefWidth(130);
		
		paneLinha4b = new HBox(btnValidar, btnLancar, btnNovoLanc);
		paneLinha4b.setSpacing(25);
		paneLinha4b.setPadding(new Insets(50, 0, 0, 0));
		
		paneLinha4c = new VBox(paneLinha4a, paneLinha4b);
		paneLinha4c.setPadding(new Insets(0,0,0,0));
		lblContas = new Label("Contas bancarias");
		lblContas.setPrefWidth(150);
		
		
		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contasBancarias = ObservableList	
		tbvContasBanc = new TableView<ContasBanc>();
		tbvContasBanc = criaTableContas(contasBancAtivas);
		tbvContasBanc.setItems(contasBancAtivas);	
		
		tbvContasBanc.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvContasBanc_Change());	
		lblContaSelec = new Label("Conta Selecionada: ");
		lblContaSelec.setPrefWidth(300);
		lblContaSelec.setPadding(new Insets(10,0,0,0));
		
		paneLinha4d = new VBox(lblContas, tbvContasBanc,lblContaSelec);
		paneLinha4d.setPrefHeight(140);
		paneLinha4d.setPrefWidth(220);
		paneLinha4d.setPadding(new Insets(0, 0 , 0 , 0));
		
		paneLinha4 = new HBox(paneLinha4c, paneLinha4d);
		paneLinha4.setSpacing(50);
		paneLinha4.setPadding(new Insets(20,0, 20,0));
		
		// -------------------------
		panePrimary = new VBox(paneLinha1,paneLinha2, paneLinha3, paneLinha4);
		panePrimary.setPadding(new Insets(30, 0, 0, 20));		
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);	
		inicializar();
		stagePrimary.showAndWait();	
	}
	
	private static void btnValidar_Act() {
		if (dadosEntrados()) {
			ArrayList arrListDados = new ArrayList();
			arrListDados = populaArrAtualPagador();
			if(arrListDados.isEmpty()) {
				MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
				stagePrimary.close();
			}
		
			BusinessRules regra = new BusinessPagadores();
			if(regra.performConsistencia(arrListDados)){
					// Verifica se os dados entrados tem consistencia com as regras do
					// negocio 
				desabilitaTxts();
				btnLancar.setDisable(false);
				btnValidar.setDisable(true);
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
			regra.performLancamento(arrListDados);
		
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(false);
		dadosPagadores = pagadores.carregaPagadores(atualPagador.getCentroReceb(), atualPagador.getSubCentroRec());
							// Medodo com overload 
							// Sem parametros carrega todos os todos os pagadores validos
							// Com parametros classe e grupo carreg apenas validos deste subgrupo
	
				
		tbvPagadores.setItems(dadosPagadores);
	}
	
	private static void btnNovoLanc_Act() {
		inicializar();
		btnNovoLanc.setDisable(true);
		btnValidar.setDisable(false);
	}
		
	private static void cbxCentro_Change() {
		int i = cbxCentro.getSelectionModel().getSelectedIndex();
		cbxSubCentro.setItems(Ini.loadProps("RecCen" + i, "SubReceb"));	
	}
	
	private static void tbvContasBanc_Change() {
		lblContaSelec.setText("Conta Selecionada: " + 
					tbvContasBanc.getSelectionModel().getSelectedItem().getNomeBanco() + " - " + 
					 tbvContasBanc.getSelectionModel().getSelectedItem().getContaNumero());	
		atualPagador.setContaVinc(tbvContasBanc.getSelectionModel().getSelectedIndex() + 1);
	}
	
	private static void cbxSubCentro_Change() {
		int classe = cbxCentro.getSelectionModel().getSelectedIndex(); //******* ?????
		int grupo = cbxSubCentro.getSelectionModel().getSelectedIndex();
		if(classe > 0 && grupo >0) {
			atualPagador.setCentroReceb(classe);
			atualPagador.setSubCentroRec(grupo);
			dadosPagadores = pagadores.carregaPagadores(classe, grupo);
			tbvPagadores.setItems(dadosPagadores);
		}	
	}
	
	private static TableView<Pagadores> tablePagadoresCadas(ObservableList<Pagadores> tabela) {
		TableView<Pagadores> tableCadastrados = new TableView<Pagadores>();	
		
		TableColumn<Pagadores, String> colEstado = new TableColumn("Est.");
		colEstado.setPrefWidth(30);
		colEstado.setCellValueFactory(new PropertyValueFactory<Pagadores, String>("EstPagador"));
			
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

		tableCadastrados.setItems(tabela);
		tableCadastrados.getColumns().addAll(colEstado, colCodigo, colNome, colValor, colInicio, colTermino, colDiaVenc, colAgente);
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
		if((atualPagador.getContratoInic()) == null) { 
			libera = false;
			MessageBox.show("Deve ser selecionada data de Inicio ", "DATAS");
		}
		
		// Termino
		if((atualPagador.getContratoFim()) == null) {
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
		if(atualPagador.getContaVinc() <= 0) {
			MessageBox.show("Deve ser escolhida uma conta bancaria", "Conta Banc");
			libera = false;
		}
		
		// Centro e SubCentro
		if((atualPagador.getCentroReceb()) < 0 || (atualPagador.getSubCentroRec() < 0)) {
			libera = false;
			MessageBox.show("Deve ser selecionado Grupo e Subgrupo", "CLASSIFICACAO");
		}
		// Data Lancamento
		atualPagador.setDataLanc(LocalDate.now());

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

	private static void inicializar() {
		limparAtualPagador();
		limparEdts();
		habilitarEdts();
		lblContaSelec.setText("Conta Selecionada: ");
		btnValidar.setDisable(false);
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(false);
		try {
			dtpInicio.setValue(LocalDate.now());
			dtpTermino.setValue(LocalDate.now());
		}catch(NullPointerException e) {}
	}
	
	private static void limparEdts() {
		edtDescr.setText("");
		edtNome.setText("");
		edtValor.setText("");
		edtDia.setText("");
		edtAgente.setText("");
	}
	
	private static void desabilitaTxts() {
		edtDescr.setEditable(false);
		edtNome.setEditable(false);
		edtValor.setEditable(false);
		edtDia.setEditable(false);
		edtAgente.setEditable(false);
	}
	
	private static void habilitarEdts() {
		edtDescr.setEditable(true);
		edtNome.setEditable(true);
		edtValor.setEditable(true);
		edtDia.setEditable(true);
		edtAgente.setEditable(true);
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
}
