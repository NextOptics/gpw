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
import gpw.model.Pagadores;
import gpw.model.Recebimentos;
import gpw.model.ContasBanc;
import gpw.dao.DaoPagadores;
import gpw.dao.DaoContasBanc;




public class FormularioRecebimentoAgenda extends FormulariosRecebimento {
	
	public FormularioRecebimentoAgenda() {
		formSelecionado = new FormRecebimentoAgenda();
	}
	
	public static void show() {
		atualPagador = new Pagadores();
		atualRecebimento = new Recebimentos();
		pagadores = new DaoPagadores();
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Agenda Recebimentos");
		stagePrimary.setMinWidth(900);
		stagePrimary.setMaxWidth(900);
		stagePrimary.setMaxHeight(900);		
		// ---------------------- Linha 1
		tbvPagadores = new TableView<Pagadores>();
		dadosPagadores = pagadores.carregaPagadores();
		tbvPagadores = tablePagadoresCadas(dadosPagadores);
		tbvPagadores.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvPagadores_Change());	
		paneLinha1 = new HBox(tbvPagadores);
		paneLinha1.setPrefHeight(400);
		paneLinha1.setPrefWidth(850);
		paneLinha1.setPadding(new Insets(30,0,0,30));	
		// -------------------- Linha 2
		lblRotDescr = new Label("Descricao");
		lblRotDescr.setPrefWidth(150);
		lblDescr = new Label("");
		lblDescr.setPrefWidth(200);
		lblDescr.setStyle("-fx-text-fill: blue");
		paneDescr = new VBox(lblRotDescr, lblDescr);
		
		lblRotNome = new Label("Nome do Contjarato");
		lblRotNome.setPrefWidth(150);
		lblNome = new Label("");
		lblNome.setPrefWidth(200);
		lblNome.setStyle("-fx-text-fill: blue");
		paneNome = new VBox(lblRotNome, lblNome);
		
		lblRotValor = new Label("Valor");
		lblRotValor.setPrefWidth(80);
		lblValor = new Label("");
		lblValor.setPrefWidth(100);
		lblValor.setStyle("-fx-text-fill: blue");
		paneValor = new VBox(lblRotValor, lblValor);
		
		lblRotDia = new Label("Dia");
		lblRotDia.setPrefWidth(80);
		lblDia = new Label("");
		lblDia.setPrefWidth(100);
		lblDia.setStyle("-fx-text-fill: blue");
		paneDia = new VBox(lblRotDia, lblDia);
		
		lblRotAgente = new Label("Agente");
		lblRotAgente.setPrefWidth(80);
		lblAgente = new Label("");
		lblAgente.setPrefWidth(100);
		lblAgente.setStyle("-fx-text-fill: blue");
		paneAgente = new VBox(lblRotAgente, lblAgente);
		
		lblRotFirstLanc = new Label("Prim.Lanc.");
		lblRotFirstLanc.setPrefWidth(80);
		lblFirstLanc = new Label();
		lblFirstLanc.setPrefWidth(100);
		lblFirstLanc.setStyle("-fx-text-fill: blue");

		paneFirstLanc = new VBox(lblRotFirstLanc, lblFirstLanc);
		paneFirstLanc.setPadding(new Insets(0,0,0,0));
		
		paneLinha2 = new HBox(paneDescr, paneNome, paneValor,paneDia, paneAgente, paneFirstLanc);
		paneLinha2.setPadding(new Insets(20, 0, 0, 30));
		
		// ----------------------- Linha 3
		lblRotCentro = new Label("Centros Recebimento");
		lblRotCentro.setPrefWidth(150);		
		lblCentro = new Label("");
		lblCentro.setPrefWidth(200);
		lblCentro.setStyle("-fx-text-fill: blue");
		paneCentro = new VBox(lblRotCentro, lblCentro);
		paneCentro.setPadding(new Insets(0,0,0,0));
		
		lblRotSubCentro = new Label("Sub-Centros");
		lblRotSubCentro.setPrefWidth(150);
		lblSubCentro = new Label("");
		lblSubCentro.setPrefWidth(200);
		lblSubCentro.setStyle("-fx-text-fill: blue");

		paneSubCentro = new VBox(lblRotSubCentro, lblSubCentro);
		paneSubCentro.setPadding(new Insets(0,0,0,0));

		lblRotInicio = new Label("Inicio");
		lblRotInicio.setPrefWidth(80);
		lblInicio = new Label();
		lblInicio.setPrefWidth(100);
		lblInicio.setStyle("-fx-text-fill: blue");
		paneInicio = new VBox(lblRotInicio, lblInicio);
		paneInicio.setPadding(new Insets(0,0,0,0));
		
		lblRotTermino = new Label("Termino");
		lblRotTermino.setPrefWidth(80);
		lblTermino = new Label();
		lblTermino.setPrefWidth(100);
		lblTermino.setStyle("-fx-text-fill: blue");
		paneTermino = new VBox(lblRotTermino, lblTermino);
		paneTermino.setPadding(new Insets(0,0,0,0));
		
		lblRotConta = new Label("Conta vinculada: ");
		lblRotConta.setPrefWidth(80);		
		lblConta = new Label("");
		lblConta.setPrefWidth(140);
		lblConta.setStyle("-fx-text-fill: blue");
		paneConta = new VBox(lblRotConta,lblConta);
		paneConta.setPadding(new Insets(0, 0 , 0 , 0));
		
		paneLinha3 = new HBox(paneCentro,paneSubCentro, paneInicio, paneTermino, paneConta);
		paneLinha3.setPadding(new Insets(20, 0, 0, 30));
		
		
		// ------------------------- Linha 4
		
		 lblRotValNovo = new Label("Valor");
		 lblRotValNovo.setPrefWidth(80);
		 edtValNovo = new TextField("");
		 edtValNovo.setPrefWidth(80);
		 paneValNovo = new VBox(lblRotValNovo, edtValNovo);
		 
		lblRotComiss = new Label("Comissao");
		lblRotComiss.setPrefWidth(70);
		edtComiss = new TextField("");
		edtComiss.setPrefWidth(70);
		paneComiss = new VBox(lblRotComiss, edtComiss);
		paneComiss.setPadding(new Insets(0,0,0,20));
		
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
		paneOutras.setPadding(new Insets(0,0,0,25));
		
		lblRotMeses = new Label("Meses");
		lblRotMeses.setPrefWidth(40);
		edtMeses = new TextField("");
		edtMeses.setPrefWidth(25);
		paneMeses = new VBox(lblRotMeses, edtMeses);
		paneMeses.setPadding(new Insets(0,0,0,30));
		
		lblRotApartir = new Label("A partir de:");
		lblRotApartir.setPrefWidth(150);
		dtpApartir = new DatePicker(LocalDate.now());
		dtpApartir.setPrefWidth(150);
		paneApartir = new VBox(lblRotApartir, dtpApartir);
		paneApartir.setPadding(new Insets(0,0,0,60));
		 
		paneLinha4 = new HBox(paneValNovo, paneComiss, paneImpRenda, paneOutras, paneMeses, paneApartir);
		paneLinha4.setPadding(new Insets(20,0,0,30));
		 
		// ------------------------- Linha 5
		
		btnCarregar = new Button("Carregar");
		btnCarregar.setPrefWidth(130);
		btnCarregar.setOnAction(e -> btnCarregar_Act());	
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(120);
		btnValidar.setOnAction(e -> btnValidar_Act());	
		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(120);
		btnLancar.setOnAction(e -> btnLancar_Act());	
		paneBotoes = new HBox(btnCarregar, btnValidar, btnLancar);
		paneBotoes.setSpacing(25);
		paneBotoes.setPadding(new Insets(20, 0, 30, 30));
		
		lblRotConta = new Label("Conta vinculada");
		lblRotConta.setPrefWidth(150);
		contasBancarias = new DaoContasBanc();
		contasBancAtivas = contasBancarias.carregarContasBanc(1);  // contas = ObservableList
				  //O int 1 em carregaContasBanc() significa que serao carregadas apenas as contas validas

		// -------------------------
		grupo = Ini.loadProps("CentroReceb", "RecCen");
		panePrimary = new VBox(paneLinha1,paneLinha2, paneLinha3,paneLinha4, paneBotoes);
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		inicializar();
		stagePrimary.showAndWait();
	}

	
	
	private static void btnCarregar_Act() {
		populaAtualPagador();  // dados do registro selecionado no tbvPagadores -> atualPagador
		preencheEdts();		   // dados do atualPagador -> nos campos da tela
		btnValidar.setDisable(false);
		btnCarregar.setDisable(true);
	}
	
	private static void btnValidar_Act() {		
		if(dadosEntrados()) { // Verifica se os campos tem valores formalmente aceitaveis
			ArrayList arrListDados = new ArrayList();
			arrListDados = populaArrAtualRecebimento();
			
			BusinessRules regra = new BusinessRecebimentos();
									// Usando mesmo metodo de BusiPagCadastra
			if(regra.performConsistencia(arrListDados)){
													// Verifica se os dados entrados tem consistencia com as regras do
													// negocio 
				btnValidar.setDisable(true);
				btnLancar.setDisable(false);
			}
		}			
	}
	
	private static void btnLancar_Act() {
		ArrayList arrListDados = new ArrayList();
		arrListDados = populaArrAtualRecebimento();

		BusinessRules regra = new BusinessRecebimentos();
			regra.performLancamento(arrListDados);
		
		btnLancar.setDisable(true);	
	}

	protected static void preencheEdts() {
		edtComiss.setText("0.");
		edtImpRenda.setText("0.");
		edtOutras.setText("0.");
		lblDescr.setText(atualPagador.getRecDescr());
		lblValor.setText(Double.toString(atualPagador.getValContrato()));
		lblInicio.setText(DateUtils.asString(atualPagador.getContratoInic()));	
		lblTermino.setText(DateUtils.asString(atualPagador.getContratoFim()));
		lblNome.setText(atualPagador.getNomeContrato());
		
		lblConta.setText((contasBancAtivas.get(atualPagador.getContaVinc()-1).getNomeBanco()).toString() +
			           "  " + (contasBancAtivas.get(atualPagador.getContaVinc() -1).getContaNumero()).toString());
				 				
		lblCentro.setText(grupo.get(atualPagador.getCentroReceb()).toString());
		subGrupo = Ini.loadProps("RecCen" + atualPagador.getCentroReceb(), "SubReceb");
		lblSubCentro.setText(subGrupo.get(atualPagador.getSubCentroRec()).toString());

		lblFirstLanc.setText(DateUtils.asString(atualPagador.getDataLanc()));
		lblDia.setText(Integer.toString(atualPagador.getDiaVenc()));
		lblAgente.setText(atualPagador.getAgente());

		edtValNovo.setText(Double.toString(atualPagador.getValContrato()));
		edtMeses.setText("1");
		
				// Se o dia na data de hoje for anterior ao dia de vencimento(conf.contrato)
				// a data é colocada com o dia de vencimento(conf.contrato) e o mes ATUAL
			// Se o dia na data de hoje for posterior ao dia de vencimento(conf contrato)
			// a data é colocada com o dia de vencimento(conf.contrato) e o mes SEGUINTE
		LocalDate dataTemp = LocalDate.now();
		dataTemp = DateUtils.mudaDia(dataTemp, atualPagador.getDiaVenc());
		if(dataTemp.isBefore(LocalDate.now()))
			dataTemp = dataTemp.plusMonths(1);
		dtpApartir.setValue(dataTemp);
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
		arrList.add(atualPagador.getCodPagador());			//13
		arrList.add(atualPagador.getEstPagador());			//14
		arrList.add(atualPagador.getRecDescr());			//15
		arrList.add(atualPagador.getValContrato());			//16
		arrList.add(atualPagador.getContratoInic());		//17
		arrList.add(atualPagador.getContratoFim());			//18
		arrList.add(atualPagador.getNomeContrato());		//19
		arrList.add(atualPagador.getContaVinc());			//20
		arrList.add(atualPagador.getCentroReceb());			//21
		arrList.add(atualPagador.getSubCentroRec());		//22
		arrList.add(atualPagador.getDataLanc());			//23
		arrList.add(atualPagador.getDiaVenc());				//24
		arrList.add(atualPagador.getAgente());				//25
		// ---------------------------------------
		arrList.add((Integer)mesesAprogramar);				//26
				// Sao feitos tantos lancamentos quanto os meses a programar	
		return arrList;
	}

	protected static void inicializar() {
		limparAtualPagador();
		limparCampos();
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		btnCarregar.setDisable(true);
		lblInicio.setText("");
		lblTermino.setText("");
		lblFirstLanc.setText("");
	}

	protected static boolean dadosEntrados() {
		boolean libera = true;
		
		// 1 – codreceb
					// gerado automaticamento pelo MySQL
					
		// 2 – recestado
		atualRecebimento.setRecEstado("A");
		
		// 3 – datalanc
		atualRecebimento.setDataLanc(LocalDate.now());
		
		// 4 – prevpagam
		atualRecebimento.setPrevPagam(dtpApartir.getValue());
		
		// 5 – datarecebido
		
		// 6 – valorprev
		if(edtValNovo.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor do recebimento", "VALOR");
		}else
			try {
				atualRecebimento.setValorPrev(Double.parseDouble(edtValNovo.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}
		
		
		// 7 – valrecebido
		atualRecebimento.setValRecebido(0.);
		
		// 8 – codpagador
		atualRecebimento.setCodPagador(atualPagador.getCodPagador());
		
		// 9 - docum
		atualRecebimento.setDocumento("");
		
        // 10 -  recebidomoc
		atualRecebimento.setRecebidoMoc(0.);
		
        // 11 – imprenda
		if(edtImpRenda.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor do Imposto de Renda", "IMP RENDA");
		}else
			try {
				atualRecebimento.setImpRenda(Double.parseDouble(edtImpRenda.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}
		
        // 12 – comisrec
		if(edtComiss.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor do Imposto da comissao", "COMISSAO");
		}else
			try {
				atualRecebimento.setComisRec(Double.parseDouble(edtComiss.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "COMISSAO");
			}
		
        //13 – outrasdesp
		if(edtImpRenda.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor de Outras Desp.", "OUTRAS DESP");
		}else
			try {
				atualRecebimento.setOutrasDesp(Double.parseDouble(edtOutras.getText()));	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "OUTRAS DESP");
			}
			
		//14 - Meses a programar ( Variavel int - nao é campo)
		if(edtMeses.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario especificar meses a programar", "VALOR");
		}else
			try {
				mesesAprogramar = Integer.parseInt(edtMeses.getText());	
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "MESES");
			}
		return libera;
	}

	
	
}

