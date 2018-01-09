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
import javafx.collections.*;
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Dsolicit;
import gpw.model.Projeto;
import gpw.dao.DaoContasProj;


/*
	private static void cbxCentro_Change()
	private static void cbxSubCentro_Change()
	private static TableView<Projeto> projsEmAberto(ObservableList<Projeto> tabela)
	private static void tbvProjetos_Change()
	private static boolean dadosEntrados()
	private static void btnValidar_Act()
	private static void btnLancar_Act()
	private static void btnNovoLanc_Act()

	private static void limparSolicitacao()
	private static void inicializar()
	private static void limparTxts()
	private static void habilitaTxts()
	private static void desabilitaTxts()
	private static ArrayList populaArrAtualSolicitacao()
*/


public class DespPrevEmite extends Dsolicit {
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Button btnValidar, btnLancar, btnNovoLanc;
	
	static VBox panePrimary, paneItem, paneDocum, paneAplic, paneValor,paneParcelas,panePrevPagam,paneQuant,
				paneUnid, paneCentro, paneSubCentro, paneProjetos, paneBotoes;
	static HBox paneUnidade,paneMeses, paneLinha1, paneLinha2, paneLinha3, paneLinha4, paneLinha5, paneLinha6;
	
	static Label lblItem,lblDocum, lblAplic, lblValor,lblParcelas,lblPrevPagam,lblQuant,lblUnid,lblMeses,
				 lblCentro, lblSubCentro, lblProjSelec;
				 
	static TextField edtItem, edtDocum, edtAplic, edtValor,edtParcelas,edtQuant,edtMeses;
	static DatePicker dtpPrevPagam;
	static RadioButton rdbDiv,rdbUnid,rdbKg,rdbM,rdbM2,rdbM3,rdbEmb,rdbSacos;
	static ChoiceBox cbxCentro, cbxSubCentro;
	
	static ObservableList<Projeto> dados;  // Contem os dados lidos do ultimo registro da tabela projetos do banco de dados
	static TableView<Projeto> tbvProjetos;
	
	static DaoContasProj projetos; // Para acessar lancamentos existentes na tabela contasproj do banco de dados
	static Dsolicit atualSolicitacao;   // Objeto da classe Dsolicit cujas variaveis de instancia tem os
								   // valores a serem persistidos na tabela dsolicit do banco de dados
	static int vezesAprogramar;
				// Vezes a programar nao tem correspondencia com um campo da tabela "Dsolicit", o programa
				// gera tantas solicitacoes de despesa quanto
	/*
			atualSolicitacao = Objeto Dsolicit cujas variaveis de instancia contem os campos entrados é usado
			para transferir, atraves de um ArrayList, dados para os metodos de BusiRuleDespPrevEmite.
			
			Os dados entrados sao checados quanto a forma e se validos sao enviados para consistencia no 
			metodo validar() de BusiDespPrevEmite. Se o metodo devolver true é habilitado o botao salvar
			
			O botao salvar envia o mesmo ArrayList validado para o metodo gravar() de BusiDespPrevEmite
	*/
	
	
	public static void show() {
		atualSolicitacao = new Dsolicit();
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Emite Previsao de Despesa");
		stagePrimary.setMinWidth(660);
		stagePrimary.setMaxWidth(660);
		
		//----------- Linha 1		
		lblItem = new Label("Item");
		edtItem = new TextField();
		edtItem.setPrefWidth(280);
		edtItem.setPromptText("produto ou servico adquirido");
		paneItem = new VBox(lblItem, edtItem);
		paneItem.setPadding(new Insets(0,0,0,0));
		
		lblDocum = new Label("Documento");
		edtDocum = new TextField();
		edtDocum.setPrefWidth(150);
		edtDocum.setPromptText("opcional");
		paneDocum = new VBox(lblDocum, edtDocum);
		paneDocum.setPadding(new Insets(0,0,0,20));
		paneLinha1 = new HBox(paneItem, paneDocum);
		paneLinha1.setPadding(new Insets(50,0,20,30));
		
		// -------------------   Linha 2				
		lblAplic = new Label("Aplicacao");
		edtAplic = new TextField();
		edtAplic.setPrefWidth(235);
		edtAplic.setPromptText(" para que sera usado oproduto ou servico");
		paneAplic = new VBox(lblAplic, edtAplic);
		paneAplic.setPadding(new Insets(0,0,0,0));
			
		lblValor = new Label("Valor Previsto");
		edtValor = new TextField();
		edtValor.setPrefWidth(90);
		paneValor = new VBox(lblValor, edtValor);
		paneValor.setPadding(new Insets(0,0,0,20));
			
		lblParcelas = new Label("Parcelas");
		edtParcelas = new TextField("1");
		edtParcelas.setPrefWidth(10);
		paneParcelas = new VBox(lblParcelas, edtParcelas);
		paneParcelas.setPadding(new Insets(0,0,0,20));
			
	
		lblPrevPagam = new Label("Previsao de Pagamento");
		dtpPrevPagam = new DatePicker();
		dtpPrevPagam.setPrefWidth(150);

		dtpPrevPagam.setOnAction(event -> {atualSolicitacao.setPrevPagam(dtpPrevPagam.getValue());});
		panePrevPagam = new VBox(lblPrevPagam, dtpPrevPagam);
		panePrevPagam.setPadding(new Insets(0,0,0,20));
		paneLinha2 = new HBox(paneAplic, paneValor, paneParcelas,panePrevPagam);
		paneLinha2.setPadding(new Insets(0,0,20,30));
		
		// --------------------  Linha  3		
		lblQuant = new Label("Qtde   ");
		edtQuant = new TextField("1");
		edtQuant.setPrefWidth(15);
		paneQuant = new VBox(lblQuant, edtQuant);
		paneQuant.setPadding(new Insets(0,0,0,0));
			
		lblUnid = new Label("Unidade");
		rdbDiv = new RadioButton("Div      ");
		rdbDiv.setSelected(true);
		rdbUnid = new RadioButton("Unid     ");
		rdbKg = new RadioButton("kg       ");
		rdbM = new RadioButton("m        ");
		rdbM2 = new RadioButton("m2       ");
		rdbM3 = new RadioButton("m3       ");
		rdbEmb = new RadioButton("Emb      ");
		rdbSacos = new RadioButton("Sacos    ");
		
		ToggleGroup unidade = new ToggleGroup();
		rdbDiv.setToggleGroup(unidade);
		rdbUnid.setToggleGroup(unidade);
		rdbKg.setToggleGroup(unidade);
		rdbM.setToggleGroup(unidade);
		rdbM2.setToggleGroup(unidade);
		rdbM3.setToggleGroup(unidade);
		rdbEmb.setToggleGroup(unidade);
		rdbSacos.setToggleGroup(unidade);
		paneUnidade = new HBox(rdbDiv,rdbUnid,rdbKg,rdbM,rdbM2,rdbM3,rdbEmb,rdbSacos);
		paneUnidade.setPadding(new Insets(5,0,0,0));
		paneUnid = new VBox(lblUnid,paneUnidade);
		paneUnid.setPadding(new Insets(0,0,0,20));
		paneLinha3 = new HBox(paneQuant, paneUnid);
		paneLinha3.setPadding(new Insets(0,0,20,30));
			
		// ------------------ Linha  4		
		lblMeses = new Label("Meses a programar: ");
		edtMeses = new TextField();
		edtMeses.setMaxWidth(50);
		paneMeses = new HBox(lblMeses, edtMeses);
		paneMeses.setPadding(new Insets(15,20,0,0));
			
		lblCentro = new Label("Centro de Despesas");
		cbxCentro = new ChoiceBox(Ini.loadProps("CentroDesp","Cen"));
		cbxCentro.setPrefWidth(170);
		cbxCentro.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> cbxCentro_Change());
		paneCentro = new VBox(lblCentro, cbxCentro);
		paneCentro.setPadding(new Insets(0,20,0,20));
			
			
		lblSubCentro = new Label("Sub-centro");
		cbxSubCentro = new ChoiceBox();
		cbxSubCentro.setPrefWidth(170);
		cbxSubCentro.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> cbxSubCentro_Change());
		paneSubCentro = new VBox(lblSubCentro, cbxSubCentro);
		paneSubCentro.setPadding(new Insets(0,0,0,20));
		paneLinha4 = new HBox(paneMeses, paneCentro, paneSubCentro);
		paneLinha4.setPadding(new Insets(0,0,20,30));
		
		// -----------------  Linha 5		
		projetos = new DaoContasProj();
		dados = projetos.carregaContasProj("C");
		
		tbvProjetos = new TableView<Projeto>();
		tbvProjetos = projsEmAberto(dados);
		tbvProjetos.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvProjetos_Change());
		
		paneProjetos = new VBox(tbvProjetos);
		paneProjetos.setMaxHeight(150);
		paneProjetos.setPadding(new Insets(0,0,40,0));
		
		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(150);
		btnValidar.setOnAction(e -> btnValidar_Act());

		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(150);
		btnLancar.setOnAction(e -> btnLancar_Act());

		btnNovoLanc = new Button("Novo Lanc.");
		btnNovoLanc.setPrefWidth(150);
		btnNovoLanc.setOnAction(e -> btnNovoLanc_Act());

		paneBotoes = new VBox(20);
		paneBotoes.getChildren().addAll(btnValidar, btnLancar, btnNovoLanc);	
		paneBotoes.setPadding(new Insets(0,0,0,40));
		
		paneLinha5 = new HBox(paneProjetos,paneBotoes);
		paneLinha5.setPadding(new Insets(0,0,10,30));
		
		// -------------- Linha 6
		lblProjSelec = new Label("Projeto Selecionado : ");
		lblProjSelec.setPrefWidth(400);
		
		paneLinha6 = new HBox(lblProjSelec);
		paneLinha6.setPadding(new Insets(0,0,20,30));
		
		inicializar();
// ------------------------------		
					
		panePrimary = new VBox(paneLinha1,paneLinha2, paneLinha3, paneLinha4, paneLinha5, paneLinha6);
		panePrimary.setPadding(new Insets(20, 0, 30, 0));
		
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		stagePrimary.showAndWait();
	}
	
	private static void cbxCentro_Change() {
		int i = cbxCentro.getSelectionModel().getSelectedIndex();
		cbxSubCentro.setItems(Ini.loadProps("Cen" + i, "SubCen"));
		atualSolicitacao.setClasse(i);
	}
	
	private static void cbxSubCentro_Change() {
		atualSolicitacao.setClasse(cbxCentro.getSelectionModel().getSelectedIndex()); //******* ?????
		atualSolicitacao.setGrupo(cbxSubCentro.getSelectionModel().getSelectedIndex());
		
	}
	
	private static TableView<Projeto> projsEmAberto(ObservableList<Projeto> tabela) {
		TableView<Projeto> tableProjs = new TableView<Projeto>();
		tableProjs.setItems(tabela);
		
		TableColumn<Projeto, Integer> colCodProj = new TableColumn("Cod");
		colCodProj.setMinWidth(80);
		colCodProj.setCellValueFactory(new PropertyValueFactory<Projeto, Integer> ("CodProj"));
		
		TableColumn<Projeto, String> colProjNome = new TableColumn("Projeto");
		colProjNome.setMinWidth(120);
		colProjNome.setCellValueFactory(new PropertyValueFactory<Projeto, String> ("ProjNome"));
		
		TableColumn<Projeto, String> colProjDescr = new TableColumn("Descricao");
		colProjDescr.setMinWidth(200);
		colProjDescr.setCellValueFactory(new PropertyValueFactory<Projeto, String> ("ProjDescr"));
		
		tableProjs.getColumns().addAll(colCodProj, colProjNome, colProjDescr);
		return tableProjs;
	}
	
	private static void tbvProjetos_Change() {
		atualSolicitacao.setProjeto(tbvProjetos.getSelectionModel().getSelectedItem().getCodProj());
		lblProjSelec.setText("Projeto Selecionado :  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getCodProj() + "  -  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getProjNome());
	}
	
	private static boolean dadosEntrados() {
		boolean libera = true;
		String tempS;
		
			// dataSolicit
		atualSolicitacao.setDataSolicit(LocalDate.now());

			// Item
		if(edtItem.getText().length() < 5) {
			libera = false;
			MessageBox.show("O campo deve ter, no minimo, 5 caracters", "ITEM");
		} else {
			tempS = edtItem.getText();
			if(tempS.length() > 40)  tempS = tempS.substring(0,40);
			atualSolicitacao.setItem(tempS);
		}

			// Aplicacao
		if(edtAplic.getText().length() < 5) {
			libera = false;
			MessageBox.show("O campo deve ter, no minimo, 5 caracteres", "APLICACAO");
		} else {
			tempS = edtAplic.getText();
			if(tempS.length() > 30) tempS = tempS.substring(0,30);
			atualSolicitacao.setAplicacao(tempS);
		}
		
			// ValorPrev
		if(edtValor.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor da despesa", "VALOR");
		} else {
			try {
				atualSolicitacao.setValorPrev(Double.parseDouble(edtValor.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}
		}
		
			// PrevPagam
		if(dtpPrevPagam.getValue() != null)
			atualSolicitacao.setPrevPagam(dtpPrevPagam.getValue());
		else {
			MessageBox.show("Necessario especificar a Data", "DATA");
			libera = false;
		}
		
			// Quantidade
		if(edtQuant.getText().equals("")) {
			libera = false;
			MessageBox.show("Necessario especificar a quantidade", "QUANTIDADE");
		} else {
			try{
				atualSolicitacao.setQuantidade(Double.parseDouble(edtQuant.getText()));
			} catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao Incorreta", "QUANTIDADE");
			}
		}

			// Unidade
		if(rdbDiv.isSelected()) atualSolicitacao.setUnidade(1);
		if(rdbUnid.isSelected()) atualSolicitacao.setUnidade(2);
		if(rdbKg.isSelected()) atualSolicitacao.setUnidade(3);
		if(rdbM.isSelected()) atualSolicitacao.setUnidade(4);
		if(rdbM2.isSelected()) atualSolicitacao.setUnidade(5);
		if(rdbM3.isSelected()) atualSolicitacao.setUnidade(6);
		if(rdbEmb.isSelected()) atualSolicitacao.setUnidade(7);
		if(rdbSacos.isSelected()) atualSolicitacao.setUnidade(8);		
		
			// Situacao
		atualSolicitacao.setSituacao("A");
		
			// NumParcelas
		if(edtParcelas.getText().equals("")) {
			libera = false;
			MessageBox.show("	Necessario especificar a quantidade de parcelas","PARCELAS");
		} else {
			try{
				atualSolicitacao.setNumParcelas(Integer.parseInt(edtParcelas.getText()));
			} catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "NUMERO DE PARCELAS");
			}
		}

				// Parcelas pagas
		atualSolicitacao.setParcPagas(0);
		
				// Docum		
		if(edtDocum.getText().length() > 0) {
			tempS = edtDocum.getText();
			if(tempS.length() > 30) tempS = tempS.substring(0,30);
			atualSolicitacao.setDocum(tempS);
		}
		
			// NumSolicit - Automatico no MySQL

			// Classe
			// atualSolicitacao.setClasse - carregado no metodo cbxCentro_Change()

			// Grupo
			// atualSolicitacao.setGrupo - carregado no metodo cbxSubCentro_Change()
		if((atualSolicitacao.getClasse() < 1) || (atualSolicitacao.getGrupo() < 1)) {
			libera = false;
			MessageBox.show("Necessario definir centros de despesa", "CENTROS DESPESAS");
		}		
		
			// UltAltera
		atualSolicitacao.setUltAltera(LocalDate.now());

			// Projeto	
		if(tbvProjetos.getSelectionModel().getSelectedIndex() < 0) {
			libera = false;
			MessageBox.show("Deve ser escolhida uma conta de projeto", "CONTA PROJETO");
		} else 
			atualSolicitacao.setProjeto(tbvProjetos.getSelectionModel().getSelectedItem().getCodProj());
	
			// Vezes a programar
		vezesAprogramar = 1;
		if(edtMeses.getText().equals("")) {
			libera = false;
			MessageBox.show("Necessario especificar quantidade de meses", "MESES  A  PROGRAMAR");
		} else {
			try {
				vezesAprogramar = Integer.parseInt(edtMeses.getText());
			} catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao Incorreta", "MESES  A  PROGRAMAR");
			}
			if(vezesAprogramar < 1) {
				libera = false;
				MessageBox.show("Programar, no minimo uma vez", "MESES  A  PROGRAMAR");
			}
		}	
		return libera;
	}
	
	private static void btnValidar_Act() {
		if(dadosEntrados()) {
			ArrayList arrListDados = new ArrayList();
			arrListDados =  populaArrAtualSolicitacao();
			if(arrListDados.isEmpty()) {
				MessageBox.show("Erro no metodo populaArrAtualSolicitacao", "FALHA CATASTROFICA");
				stagePrimary.close();
			}
			BusinessRules regra = new BusinessDespesas();
			if(regra.performConsistencia(arrListDados)) {
				desabilitaTxts();
				btnLancar.setDisable(false);
				btnValidar.setDisable(true);	
			}		
		} else{
			MessageBox.show("Falha na entrada dos daods", "DADOS");
		}
	}
	
	private static void btnLancar_Act() {
		ArrayList arrListDados = new ArrayList();
		arrListDados =  populaArrAtualSolicitacao();
		if(arrListDados.isEmpty()) {
			MessageBox.show("Erro no metodo populaArrAtualSolicitacao", "FALHA CATASTROFICA");
			stagePrimary.close();
		}
		
		BusinessRules regra = new BusinessDespesas();
		if(regra.performLancamento(arrListDados) == true) 
			btnLancar.setDisable(true);
		else
			MessageBox.show("Falha na gravaçao do lancamento", "PERSISTENCIA");
	}
	
	private static void btnNovoLanc_Act() {
		inicializar();
		dtpPrevPagam.setValue(LocalDate.now());
		
	}
	
	private static void limparSolicitacao() {
//		atualSolicitacao.setDataSolicit(LocalDate.now()); 
					// Inicializada na entrada de dados
		atualSolicitacao.setItem("");
		atualSolicitacao.setAplicacao("");
		atualSolicitacao.setValorPrev(0.);
		atualSolicitacao.setPrevPagam(LocalDate.now());
		atualSolicitacao.setQuantidade(0.);
		atualSolicitacao.setUnidade(-1);
		atualSolicitacao.setSituacao("");
		atualSolicitacao.setNumParcelas(0);
		atualSolicitacao.setParcPagas(0);
		atualSolicitacao.setDocum("");
		atualSolicitacao.setClasse(0);
		atualSolicitacao.setGrupo(0);
//		atualSolicitacao.setUltAltera(LocalDate.now());
				// Inicializaada na entradada de dados
		atualSolicitacao.setProjeto(0);	
	}
	
	private static void inicializar() {
		limparSolicitacao();
		limparTxts();
		habilitaTxts();

		btnValidar.setDisable(false);
		btnLancar.setDisable(true);
		btnNovoLanc.setDisable(false);
		
		rdbDiv.setSelected(true);
		cbxCentro.getSelectionModel().selectFirst();
		cbxSubCentro.getSelectionModel().selectFirst();
		
		tbvProjetos.getSelectionModel().selectFirst();
		lblProjSelec.setText("Projeto Selecionado :  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getCodProj() + "  -  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getProjNome());		
	}
	
	private static void limparTxts() {
		edtItem.setText("");
		edtDocum.setText("");
		edtAplic.setText("");
		edtValor.setText("");
		edtParcelas.setText("1");
		edtQuant.setText("1");
		edtMeses.setText("1");
	}

	private static void habilitaTxts() {
		edtItem.setEditable(true);
		edtDocum.setEditable(true);
		edtAplic.setEditable(true);
		edtValor.setEditable(true);
		edtParcelas.setEditable(true);
		edtQuant.setEditable(true);
		edtMeses.setEditable(true);
	}
	
	private static void desabilitaTxts() {
		edtItem.setEditable(false);
		edtDocum.setEditable(false);
		edtAplic.setEditable(false);
		edtValor.setEditable(false);
		edtParcelas.setEditable(false);
		edtQuant.setEditable(false);
		edtMeses.setEditable(false);
	}
	
	private static ArrayList populaArrAtualSolicitacao() {
		ArrayList arrList = new ArrayList();
		arrList.add(atualSolicitacao.getDataSolicit());
		arrList.add(atualSolicitacao.getItem());
		arrList.add(atualSolicitacao.getAplicacao());
		arrList.add(atualSolicitacao.getValorPrev());
		arrList.add(atualSolicitacao.getPrevPagam());	
		arrList.add(atualSolicitacao.getQuantidade());
		arrList.add(atualSolicitacao.getUnidade());
		arrList.add(atualSolicitacao.getSituacao());
		arrList.add(atualSolicitacao.getNumParcelas());
		arrList.add(atualSolicitacao.getParcPagas());
		arrList.add(atualSolicitacao.getDocum());
		arrList.add(atualSolicitacao.getNumSolicit());
		arrList.add(atualSolicitacao.getClasse());
		arrList.add(atualSolicitacao.getGrupo());
		arrList.add(atualSolicitacao.getUltAltera());
		arrList.add(atualSolicitacao.getProjeto());
		arrList.add(vezesAprogramar);	
		return arrList;		
	}		
}