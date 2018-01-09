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
import gpw.control.DateUtils;
import gpw.control.*;
  
import gpw.model.Inflacao;  
import gpw.dao.DaoInflacao;

/*
-public static void show()
-private static void btnValidar_Act()
-private static void btnLancar_Act()
-private static void btnNovoLanc_Act()
-private static void inicializar()
-private static boolean dadosEntrados()
-private static void limparTxts()
-private static void desabilitaTxts()
-private static void habilitaTxts()
-private static TableView<Inflacao> tabelaUltimoRegistro(ObservableList<Inflacao> tabela)
-private static ArrayList getInflacaoMensal()
-private static void limparInflacaoMensal()

*/  
public class LancaIndicesInfla {
	
	static TextField txtPoup, txtIgpm, txtInpc, txtIpca, txtIpam;
	static DatePicker dtpSelecionaData;
	static Button btnValidar, btnLancar, btnNovoLanc;
	static HBox paneLinha1, paneLinha2, paneLinha3, paneLinha4;
	static VBox panePrimary;
	
	static Stage stagePrimary; 
	static Scene scenePrimary; 
	
	static DaoInflacao indices;
	static Inflacao inflacaoMensal;
	
	static TableView<Inflacao> tbvRegistros; // Mostra o ultimo registro da dabela inflacao do bando de dados
	static ObservableList<Inflacao> dados; // Contem os dados lidos do ultimo registro da tabela inflacao do banco de dados
										
										
	static LocalDate dataSelecionada; // data do lancamento - Passado como campo adicional no ArrayList
									  // usado para transferir dados no objeto inflacaoMensal, ver metodo
									  // getInflacaoMensal()

			// inflacaoMensal = Objeto Inflacao com as variaveis de Instancia com os valores do
			//			ultimo registro da tabela inflacao.
			// As variaveis de instancia poup,igpm,inpc,ipca e ipam
			// 							sao atualizadas com os correspondes valores entrados
			// As variaveis de instancia poupMoc,igpmMoc,ipcaMoc, ipamMoc, moc e idxMensal sao mantidas com
							// seus valores Old e sao passadas para o metodo lancar() da classe BusiRuleLancaIndices
							// onde os correspondentes valores New sao calculados
							
			// Se os dados entrados forem aceitos o objeto é mandado para verificar
			// a consistencia na classe BusiRuleLancaIndices
			
			// Se a consistencia for valida é habilitado o botao Lancar
			// O lancamento chama o metodo lancar de classe BusiRuleLancaIndices que
			// calcula os campos moc e atualiza um outro objeto inflacaoMensal criado nesta
			// classe como espelho do objeto inflacaoMensal existente em LancaIndicesInfla
			
			// Este objeto é passado como um ArrayList correspondete ao registro a ser gravado
			// A gravacao é feito pelo metodo gravar da classe DaoInflacao
									  
		
	public static void show() {
		inflacaoMensal = new Inflacao();

		stagePrimary = new Stage();
//		stagePrimary.initModality(Modality.APPLICATION_MODAL);
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Lanca Indices de Inflacao");
		stagePrimary.setMinWidth(300);
		stagePrimary.setMaxHeight(900);
		
		//----------------- Linha 1		
		dtpSelecionaData = new DatePicker();
		dtpSelecionaData.setOnAction(event -> {dataSelecionada = dtpSelecionaData.getValue();});
		paneLinha1 = new HBox(dtpSelecionaData);
		
		//----------------- Linha 2	
		Label lblPoup = new Label("Poupanca");
		lblPoup.setPrefWidth(50);
		txtPoup = new TextField("");
		txtPoup.setPromptText("Poup");
		VBox panePoup = new VBox(lblPoup, txtPoup);
		
		Label lblIgpm = new Label("IGP-M");
		lblIgpm.setPrefWidth(40);
		txtIgpm = new TextField("");
		txtIgpm.setPromptText("Igpm");
		VBox paneIgpm = new VBox(lblIgpm, txtIgpm);
		
		Label lblInpc = new Label("INPC");
		lblInpc.setPrefWidth(40);
		txtInpc = new TextField("");
		txtInpc.setPromptText("Inpc");
		VBox paneInpc = new VBox(lblInpc, txtInpc);
		
		Label lblIpca = new Label("IPC-A");
		lblIpca.setPrefWidth(40);
		txtIpca = new TextField("");
		txtIpca.setPromptText("Ipca");
		VBox paneIpca = new VBox(lblIpca, txtIpca);
		
		Label lblIpam = new Label("IPA-M");
		lblIpam.setPrefWidth(40);
		txtIpam = new TextField("");
		txtIpam.setPromptText("Ipam");
		VBox paneIpam = new VBox(lblIpam, txtIpam);
		
		paneLinha2 = new HBox(panePoup, paneIgpm, paneInpc, paneIpca, paneIpam);
		paneLinha2.setSpacing(20);
		
		//------------------ Linha 3
		btnValidar = new Button("Validar");
		btnValidar.setOnAction(e -> btnValidar_Act());
		
		btnNovoLanc = new Button("Novo Lancamento");
		btnNovoLanc.setOnAction(e -> btnNovoLanc_Act());
		
		btnLancar = new Button("Lancar");
		btnLancar.setOnAction(e -> btnLancar_Act());
		
		paneLinha3 = new HBox(btnValidar,btnLancar, btnNovoLanc);
		paneLinha3.setSpacing(40);
	
//---------------------- Linha 4	
		indices = new DaoInflacao();
		dados = indices.buscarUltimoRegistro();
		tbvRegistros = new TableView<Inflacao>();
		tbvRegistros = tabelaUltimoRegistro(dados);
		paneLinha4 = new HBox(tbvRegistros);
		paneLinha4.setMaxHeight(170);
		paneLinha4.setPadding(new Insets(0,0,40,0));
		tbvRegistros.setItems(dados);
		
	
		panePrimary = new VBox(paneLinha1, paneLinha2, paneLinha3, paneLinha4);
		panePrimary.setSpacing(30);
		panePrimary.setPadding(new Insets(10, 10, 10, 10));
		// -----------------------------------------------------		
		inicializar();
		Scene scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		stagePrimary.showAndWait();
	}
	
	private static void btnValidar_Act() {	
		if(dadosEntrados()) {	// Verifica se foram entrados dados numericamente validos	
			ArrayList arrListDados = new ArrayList();
			arrListDados = getInflacaoMensal();
			if(arrListDados.isEmpty()) {
				MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
				stagePrimary.close();
			}
			BusinessRules regra = new BusinessInflacao();
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
		arrListDados = getInflacaoMensal();
		if(arrListDados.isEmpty()) {
			MessageBox.show("Erro no metodo getSolicitacao", "FALHA CATASTROFICA");
			stagePrimary.close();
		}
		BusinessRules regra = new BusinessInflacao();
		regra.performLancamento(arrListDados);
			// A preparacao do lancamento e feita pelo metodo lancar da classe BusiRuleLancaIndices
			// A persistencia e feita pelo metodo gravar da classe DaoInflacao
					 
		dados = indices.buscarUltimoRegistro();
		tbvRegistros.setItems(dados);
		btnNovoLanc.setDisable(false);
		btnLancar.setDisable(true);
	}
	
	private static void btnNovoLanc_Act() {
		inicializar();
		btnNovoLanc.setDisable(true);
		btnValidar.setDisable(false);	
	}
	private static void inicializar() {
		limparInflacaoMensal();
		limparTxts();
		habilitaTxts();
		btnLancar.setDisable(false);
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(false);
		try {
			dtpSelecionaData.setValue(LocalDate.now());
		}catch(NullPointerException e) {}
	}

	private static boolean dadosEntrados() {
			// Preenche as variaveis de instancia de inflacaoMensal com
			// os valores entrados e lidos no ultimo registro da tabela inflacao do bd
			
		boolean libera = true;
				// dataMoc  - Lida no ultimo registro da tabela atraves do ObservableList dados
		inflacaoMensal.setDataMoc(dados.get(0).getDataMoc());

			// Poup
		if(txtPoup.getText().equals("")) {
			libera = false;
			MessageBox.show("Campo nao digitado", "POUPANCA");
		}else {
			try {
				inflacaoMensal.setPoup(Double.parseDouble(txtPoup.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Poupanca - Digitacao Incorreta", "ERRO");	
			}
		}

			// PoupMoc - Lida no ultimo registro da tabela atraves do ObservableList dados
		inflacaoMensal.setPoupMoc(dados.get(0).getPoupMoc());

			// Igpm				
		if(txtIgpm.getText().equals("")) {
			libera = false;
			MessageBox.show("Campo nao digitado", "IGPM");
		}else {
			try {
				inflacaoMensal.setIgpm(Double.parseDouble(txtIgpm.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("IGP-M - Digitacao Incorreta", "ERRO");	
			}
		}
			// IgpmMoc - Lida no ultimo registro da tabela atraves do ObservableList dados
		inflacaoMensal.setIgpmMoc(dados.get(0).getIgpmMoc());

			// Inpc
		if(txtInpc.getText().equals("")){
			libera = false;
			MessageBox.show("Campo nao digitado", "IGPM");
		}else {
			try {
				inflacaoMensal.setInpc(Double.parseDouble(txtInpc.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("INPC - Digitacao Incorreta", "ERRO");	
			}
		}

			// InpcMoc - Lida no ultimo registro da tabela atraves do ObservableList dados
		inflacaoMensal.setInpcMoc(dados.get(0).getInpcMoc());

			// Ipca		
		if(txtIpca.getText().equals("")){
			libera = false;
			MessageBox.show("Campo nao digitado", "IGPM");
		}else {
			try {
				inflacaoMensal.setIpca(Double.parseDouble(txtIpca.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("IPC-A - Digitacao Incorreta", "ERRO");	
			}
		}

			// IpcaMoc
		inflacaoMensal.setIpcaMoc(dados.get(0).getIpcaMoc());

			// Ipam				
		if(txtIpam.getText().equals("")) {
			libera = false;
			MessageBox.show("Campo nao digitado", "IPAM");
		}else {
			try {
				inflacaoMensal.setIpam(Double.parseDouble(txtIpam.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("IPA-M - Digitacao Incorreta", "ERRO");	
			}	
		}
			// IpamMoc
		inflacaoMensal.setIpamMoc(dados.get(0).getIpamMoc());

			// dataSelecionada
		if(dtpSelecionaData.getValue() != null)
			dataSelecionada = dtpSelecionaData.getValue();
		else {
			MessageBox.show("Necessario especificar a Data", "DATA");
			libera = false;
		}
		
		inflacaoMensal.setMoc(dados.get(0).getMoc());
		inflacaoMensal.setValidMoc(dados.get(0).getValidMoc());
		inflacaoMensal.setIdxMens(dados.get(0).getIdxMens());
		return libera;
	}
	
	private static void limparTxts() {
		txtPoup.setText("");
		txtIgpm.setText("");
		txtInpc.setText("");
		txtIpca.setText("");
		txtIpam.setText("");
	}
	
	private static void desabilitaTxts() {
		txtPoup.setEditable(false);
		txtIgpm.setEditable(false);
		txtInpc.setEditable(false);
		txtIpca.setEditable(false);
		txtIpam.setEditable(false);
	}
	
	private static void habilitaTxts() {
		txtPoup.setEditable(true);
		txtIgpm.setEditable(true);
		txtInpc.setEditable(true);
		txtIpca.setEditable(true);
		txtIpam.setEditable(true);
	}

	private static TableView<Inflacao> tabelaUltimoRegistro(ObservableList<Inflacao> tabela)  {	
		TableView<Inflacao> tableIndices = new TableView<Inflacao>();
		tableIndices.setItems(tabela);
		
		TableColumn<Inflacao, String> colData = new TableColumn("Data");						// 26
		colData.setMinWidth(100);
		colData.setCellValueFactory(new PropertyValueFactory<Inflacao, String> ("DataMoc"));
		
		TableColumn<Inflacao, Double> colPoup = new TableColumn("Poup");						// 31
		colPoup.setMinWidth(80);
		colPoup.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Poup"));
		
		TableColumn<Inflacao, Double> colIgpm = new TableColumn("Igpm");						// 36
		colIgpm.setMinWidth(80);
		colIgpm.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Igpm"));
		
		TableColumn<Inflacao, Double> colInpc = new TableColumn("Inpc");						// 36
		colInpc.setMinWidth(80);
		colInpc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Inpc"));
		
		TableColumn<Inflacao, Double> colIpca = new TableColumn("Ipca");						// 36
		colIpca.setMinWidth(80);
		colIpca.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Ipca"));
		
		TableColumn<Inflacao, Double> colIpam = new TableColumn("Ipam");						// 36
		colIpam.setMinWidth(80);
		colIpam.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Ipam"));
		
		TableColumn<Inflacao, Double> colMoc = new TableColumn("Moc");						// 36
		colMoc.setMinWidth(80);
		colMoc.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("Moc"));
		
		TableColumn<Inflacao, Double> colIdxmens = new TableColumn("Idx-mensal");						// 36
		colIdxmens.setMinWidth(80);
		colIdxmens.setCellValueFactory(new PropertyValueFactory<Inflacao, Double> ("IdxMens"));
		
		tableIndices.getColumns().addAll(colData, colPoup, colIgpm, colInpc, colIpca,
		colIpam, colMoc, colIdxmens
		);
		
		return tableIndices;
	}
	
	private static ArrayList getInflacaoMensal() {
		ArrayList arrList = new ArrayList();

		arrList.add(inflacaoMensal.getDataMoc());
		arrList.add(inflacaoMensal.getPoup());
		arrList.add(inflacaoMensal.getPoupMoc());
		arrList.add(inflacaoMensal.getIgpm());
		arrList.add(inflacaoMensal.getIgpmMoc());
		
		arrList.add(inflacaoMensal.getInpc());
		arrList.add(inflacaoMensal.getInpcMoc());
		arrList.add(inflacaoMensal.getIpca());
		arrList.add(inflacaoMensal.getIpcaMoc());
		arrList.add(inflacaoMensal.getIpam());
		
		arrList.add(inflacaoMensal.getIpamMoc());
		arrList.add(inflacaoMensal.getMoc());
		arrList.add(inflacaoMensal.getValidMoc());
		arrList.add(inflacaoMensal.getIdxMens());
		arrList.add(dataSelecionada);
					// a variavel dataSelecionada, passada junto com as variaveis de instancia, é utilizada
					// na classe BusiRuleLancaIndices pelos metodos:
							// validar() - verifica se o lancamento corresponde a um periodo valido
							// gravar() - para calcular a data do lancamento a ser gravado
						

		return arrList;
	}
	
	private static void limparInflacaoMensal() {
		inflacaoMensal.setDataMoc(LocalDate.now());
		inflacaoMensal.setPoup(0.);
		inflacaoMensal.setPoupMoc(0.);
		inflacaoMensal.setIgpm(0.);
		inflacaoMensal.setIgpmMoc(0.);
		
		inflacaoMensal.setInpc(0.);
		inflacaoMensal.setInpcMoc(0.);
		inflacaoMensal.setIpca(0.);
		inflacaoMensal.setIpcaMoc(0.);
		inflacaoMensal.setIpam(0.);
		
		inflacaoMensal.setIpamMoc(0.);
		inflacaoMensal.setMoc(0.);
		inflacaoMensal.setValidMoc("");
		inflacaoMensal.setIdxMens(0.);	
	}
} 