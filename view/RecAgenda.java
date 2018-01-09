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


// import javafx.scene.paint.Color;
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Pagadores;
import gpw.model.Recebimentos;
import gpw.model.ContasBanc;
import gpw.dao.DaoPagadores;
import gpw.dao.DaoContasBanc;

/*
	public static void show()
	private static TableView<Pagadores> tablePagadoresCadas(ObservableList<Pagadores> tabela)
	private static void tbvCadastrados_Change()
	private static void preencheEdts()
	private static void btnCarregar_Act()
	private static void btnValidar_Act()
	private static void btnLancar_Act()
	
	private static void populaAtualPagador()
	private static void inicializar()
	public static void limparCampos()
	private static void limparAtualPagador()
	private static boolean dadosEntrados()
	private static ArrayList populaArrAtualRecebimento()
*/

/*
	Nesta classe sao criados os objetos:
		atualPagador<Pagadores>		   : Pagador responsavel pelo recebimento atual
		atualRecebimento<Recebimentos> : Recebimento a ser cadastrado
		
	Na classe RecConfirma é criado o objeto:
		 atualPagRecebimento<PagRecebimentos> : Que possui os campos do Recebimento e
												do Pagador e representa o recebimento atual
												com seu respectivo pagador
	
*/


public class RecAgenda {
	
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblRotDescr,lblRotNome,lblRotValor,lblRotDia,lblRotAgente,lblRotFirstLanc,
				 lblDescr,   lblNome,   lblValor,   lblDia,   lblAgente,   lblFirstLanc;
	
	static Label lblRotCentro,lblRotSubCentro,lblRotInicio,lblRotTermino,lblRotConta,
				 lblCentro,   lblSubCentro,   lblInicio,   lblTermino,   lblConta;
	
	static Label     lblRotValNovo,lblRotComiss,lblRotImpRenda,lblRotOutras,lblRotMeses, lblRotApartir;
	static TextField edtValNovo,   edtComiss,   edtImpRenda,   edtOutras,   edtMeses;
	static DatePicker dtpApartir;
				 
	static Button  btnCarregar, btnValidar, btnLancar;
	
	static VBox panePrimary, paneCentro, paneSubCentro, paneDescr,paneNome,paneValor,
				paneDia,paneAgente,paneInicio,paneTermino,paneFirstLanc, paneConta, 
				paneValNovo, paneComiss, paneImpRenda, paneOutras,paneMeses, paneApartir;
	static HBox paneLinha1, paneLinha2, paneLinha3, paneLinha4, paneBotoes;
	
	static DaoPagadores pagadores; // Objeto criado para acessar metodos de DaoPagadores
	static TableView<Pagadores> tbvPagadores;
	static ObservableList<Pagadores> dadosPagadores;
	
	static DaoContasBanc contasBancarias;
	static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasBancAtivasbanc do banco de dados
									// correspondentes as contas bancarias ativas
	static ObservableList grupo, subGrupo;
	
	static Pagadores atualPagador;
	static Recebimentos atualRecebimento;
	
	
	static int mesesAprogramar;
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
		
		lblRotNome = new Label("Nome do Contrato");
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
	
	private static void tbvPagadores_Change() {
		inicializar();
		btnCarregar.setDisable(false);	
	}
	
	
	private static void preencheEdts() {
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
	
	private static void populaAtualPagador() {

		atualPagador.setCodPagador((Integer)tbvPagadores.getSelectionModel().getSelectedItem().getCodPagador());
		atualPagador.setEstPagador(tbvPagadores.getSelectionModel().getSelectedItem().getEstPagador());
		atualPagador.setRecDescr(tbvPagadores.getSelectionModel().getSelectedItem().getRecDescr());
		atualPagador.setValContrato(tbvPagadores.getSelectionModel().getSelectedItem().getValContrato());
		atualPagador.setContratoInic(tbvPagadores.getSelectionModel().getSelectedItem().getContratoInic());	
		atualPagador.setContratoFim(tbvPagadores.getSelectionModel().getSelectedItem().getContratoFim());
		atualPagador.setNomeContrato(tbvPagadores.getSelectionModel().getSelectedItem().getNomeContrato());
		atualPagador.setContaVinc(tbvPagadores.getSelectionModel().getSelectedItem().getContaVinc());
		atualPagador.setCentroReceb(tbvPagadores.getSelectionModel().getSelectedItem().getCentroReceb());
		atualPagador.setSubCentroRec(tbvPagadores.getSelectionModel().getSelectedItem().getSubCentroRec());
		atualPagador.setDataLanc(tbvPagadores.getSelectionModel().getSelectedItem().getDataLanc());
		atualPagador.setDiaVenc(tbvPagadores.getSelectionModel().getSelectedItem().getDiaVenc());
		atualPagador.setAgente(tbvPagadores.getSelectionModel().getSelectedItem().getAgente());
	}
	
	private static void inicializar() {
		limparAtualPagador();
		limparCampos();
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		btnCarregar.setDisable(true);
		lblInicio.setText("");
		lblTermino.setText("");
		lblFirstLanc.setText("");
	}
	
	public static void limparCampos() {
		lblDescr.setText("");
		lblNome.setText("");
		lblValor.setText("");
		lblDia.setText("");
		lblAgente.setText("");
		lblFirstLanc.setText("");
		
		lblCentro.setText("");
		lblSubCentro.setText("");
		lblInicio.setText("");
		lblTermino.setText("");
		lblConta.setText("");
		
		edtValNovo.setText("");
		edtComiss.setText("");
		edtImpRenda.setText("");
		edtOutras.setText("");
		edtMeses.setText("");
		dtpApartir.setValue(LocalDate.now());
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
	
	private static boolean dadosEntrados() {
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
	
	private static ArrayList populaArrAtualRecebimento() {
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
}

