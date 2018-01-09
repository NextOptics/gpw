package gpw.view;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;

import javafx.geometry.*;
import javafx.collections.*;
import java.util.ArrayList;
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Dsolicit;
import gpw.model.Dautorizacao;
import gpw.model.Projeto;
import gpw.dao.DaoDespesas;
import gpw.dao.DaoContasProj;

/*
	 	private static TableView<Dsolicit> tableDespPrevistas(ObservableList<Dsolicit> tabela)
		private static void tbvDespPrevistas_Change() 
		private static void btnCarregar_Act()
		private static void btnValidar_Act()
		private static void btnLancar_Act()
		private static void inicializar()
		private static void tbvProjetos_Change() 
		private static boolean dadosEntrados()
		private static void populaAtualDespesa()
		private static void preencheEdts()
		private static void limparCampos()
		private static ArrayList populaArrAtualSolicitacao()
		private static void limparSolicitacao()
		private static TableView<Projeto> projsEmAberto(ObservableList<Projeto> tabela)
		private static void cbxCentro_Change()
		private static void desabilitaEdts()
		private static void habilitaEdts()
		private static void teste1()

*/


public class DespPrevEdita {
	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static TableView<Dsolicit> tbvDespPrevistas;
	static TableView<Projeto> tbvProjetos;
	static ObservableList<Dsolicit> dadosSolic;
	static ObservableList<Projeto> dadosProj;  // Contem os dados lidos do ultimo registro da tabela projetos do banco de dados
	static ObservableList grupo, subGrupo;

	static Label lblRotNumero,lblNumero,lblRotCentro,lblPrevPagam,lblSelecionado,lblRotValPrev, 
				lblRotParcelas,lblRotAplic,lblRotSubCentro,lblRotItem,lblQuant,
				lblUnid,lblDocum,lblProjSelec;
					
	static TextField edtItem,edtAplic,edtValPrev,edtParcelas,edtDocum,edtQuant;
				
	static Button btnCarregar, btnValidar, btnLancar;
	static DatePicker dtpPrevPagam;
	static RadioButton rdbDiv,rdbUnid,rdbKg,rdbM,rdbM2,rdbM3,rdbEmb,rdbSacos;
	static ChoiceBox cbxCentro, cbxSubCentro;
	
	static HBox paneUnidade,paneLinha1, paneLinha2, paneLinha3,paneLinha4, paneLinha5, paneLinha6;
	static VBox panePrimary, paneItem, paneAplic,panePrevisao,paneValPrev,paneParcelas,
			paneAgendar,paneQuant,paneDocum,paneUnid,paneMeses,		
			paneCentro, paneSubCentro,paneCentrosDesp, paneProjetos, paneBotoes;
	
	
	static Dsolicit atualDespesa;		// Objeto da classe Dsolicit cujas variaveis de instancia tem os	
										// valores a serem persistidos na tabela dsolicit do banco d
	static DaoDespesas despesas;
	static DaoContasProj projetos;
	

	static int vezesAprogramar;
	
	
	public static void show() {
		atualDespesa = new Dsolicit();
		despesas = new DaoDespesas();
		grupo = Ini.loadProps("CentroDesp", "Cen");
		
		stagePrimary = new Stage();
		stagePrimary.initModality(Modality.WINDOW_MODAL);
		stagePrimary.setTitle("Edita Previsoes de Despesas");
		stagePrimary.setMinWidth(880);
		stagePrimary.setMaxWidth(880);
		stagePrimary.setMaxHeight(900);
		
			// -------------------- Linha 1
		tbvDespPrevistas = new TableView<Dsolicit>();
		dadosSolic = despesas.carregarSolicAbertas();
		tbvDespPrevistas = tableDespPrevistas(dadosSolic);
		tbvDespPrevistas.setPrefHeight(200);
		
		tbvDespPrevistas.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvDespPrevistas_Change());
		paneLinha1 = new HBox(tbvDespPrevistas);
		paneLinha1.setMaxWidth(850);
		paneLinha1.setPadding(new Insets(30,0,0,20));
		
			// --------------------- Linha 2
		lblRotNumero = new Label("Solicitacao selecionada: ");
		lblRotNumero.setPrefWidth(140);
		lblNumero = new Label("");
		lblNumero.setStyle("-fx-text-fill: blue");
		lblNumero.setPrefWidth(140);
			
		paneLinha2 = new HBox(lblRotNumero, lblNumero);
		paneLinha2.setSpacing(30);
		paneLinha2.setPadding(new Insets(20,0,0,20));
		
			// -------------------- Linha 3
		lblRotItem = new Label("Item");
		lblRotItem.setPrefWidth(140);
		edtItem = new TextField();
		edtItem.setPrefWidth(140);
		paneItem = new VBox(lblRotItem, edtItem);
		
		lblRotAplic = new Label("Aplicacao");
		lblRotAplic.setPrefWidth(140);
		edtAplic = new TextField();
		edtAplic.setPrefWidth(140);
		paneAplic = new VBox(lblRotAplic, edtAplic);
		
		lblRotValPrev = new Label("Valor Prev.");
		lblRotValPrev.setPrefWidth(70);
		edtValPrev = new TextField("");
		edtValPrev.setPrefWidth(70);
		paneValPrev = new VBox(lblRotValPrev,edtValPrev);
		
		lblRotParcelas = new Label("Parc.");
		lblRotParcelas.setPrefWidth(40);
		edtParcelas = new TextField("");
		edtParcelas.setPrefWidth(40);
		paneParcelas = new VBox(lblRotParcelas, edtParcelas);
		
		lblPrevPagam = new Label("Prev.Pagam.:");
		lblPrevPagam.setPrefWidth(120);
		dtpPrevPagam = new DatePicker();
		dtpPrevPagam.setPrefWidth(120);
		dtpPrevPagam.setValue(LocalDate.now());
		paneAgendar = new VBox(lblPrevPagam, dtpPrevPagam);
		
		lblDocum = new Label("Documento");
		edtDocum = new TextField();
		edtDocum.setPrefWidth(150);
		edtDocum.setPromptText("opcional");
		paneDocum = new VBox(lblDocum, edtDocum);

		paneLinha3 = new HBox(paneItem,paneAplic,paneValPrev,paneParcelas, paneAgendar, paneDocum);
		paneLinha3.setSpacing(25);
		paneLinha3.setPadding(new Insets(20,0,0,20));
		
			// ---------------------- Linha 4
		
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
		paneLinha4 = new HBox(paneQuant,paneUnid);
		paneLinha4.setSpacing(30);
		paneLinha4.setPadding(new Insets(20,0,0,20));
		
		
		// -----------------  Linha 5
		
		projetos = new DaoContasProj();
		dadosProj = projetos.carregaContasProj("C");
		
		tbvProjetos = new TableView<Projeto>();
		tbvProjetos = projsEmAberto(dadosProj);
		tbvProjetos.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> tbvProjetos_Change());
		
		paneProjetos = new VBox(tbvProjetos);
		paneProjetos.setMaxHeight(150);
		paneProjetos.setPadding(new Insets(0,0,0,0));
		
		lblRotCentro = new Label("Centro de Despesas");
		cbxCentro = new ChoiceBox(Ini.loadProps("CentroDesp","Cen"));
		cbxCentro.setPrefWidth(170);
		cbxCentro.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> cbxCentro_Change());
		paneCentro = new VBox(lblRotCentro, cbxCentro);
		paneCentro.setPadding(new Insets(0,0,0,0));
			
		lblRotSubCentro = new Label("Sub-centro");
		cbxSubCentro = new ChoiceBox();
		cbxSubCentro.setPrefWidth(170);
		paneSubCentro = new VBox(lblRotSubCentro, cbxSubCentro);
		paneSubCentro.setPadding(new Insets(0,0,0,0));
		
		paneCentrosDesp = new VBox(paneCentro, paneSubCentro);
		paneCentrosDesp.setSpacing(40);
		
		btnCarregar = new Button("Carregar");
		btnCarregar.setPrefWidth(150);
		btnCarregar.setOnAction(e -> btnCarregar_Act());

		btnValidar = new Button("Validar");
		btnValidar.setPrefWidth(150);
		btnValidar.setOnAction(e -> btnValidar_Act());

		btnLancar = new Button("Lancar");
		btnLancar.setPrefWidth(150);
		btnLancar.setOnAction(e -> btnLancar_Act());

		paneBotoes = new VBox(20);
		paneBotoes.getChildren().addAll(btnCarregar, btnValidar, btnLancar);	
		paneBotoes.setPadding(new Insets(0,0,0,0));
		
		paneLinha5 = new HBox(paneProjetos,paneCentrosDesp,paneBotoes);
		paneLinha5.setSpacing(40);
		paneLinha5.setPadding(new Insets(20,0,0,20));
	
			// -----------------------  Linha 6
		
		lblProjSelec = new Label("Projeto Selecionado : ");
		lblProjSelec.setPrefWidth(400);
		
		paneLinha6 = new HBox(lblProjSelec);
		paneLinha6.setPadding(new Insets(0,0,20,30));
		paneLinha6.setPadding(new Insets(20, 0, 30, 20));
		inicializar();
	
			// ---------------------
		
		panePrimary = new VBox(paneLinha1, paneLinha2, paneLinha3,paneLinha4,paneLinha5,paneLinha6);
		scenePrimary = new Scene(panePrimary);
		stagePrimary.setScene(scenePrimary);
		inicializar();
		stagePrimary.showAndWait();	
	}
	
	private static TableView<Dsolicit> tableDespPrevistas(ObservableList<Dsolicit> tabela){
		TableView<Dsolicit> tableDespesas = new TableView<Dsolicit>();
		
		
		TableColumn<Dsolicit, String> colItem = new TableColumn("Descricao");
		colItem.setPrefWidth(200);
		colItem.setCellValueFactory(new PropertyValueFactory<Dsolicit, String >("Item"));
		
		TableColumn<Dsolicit, String> colAplicacao = new TableColumn("Aplicacao");
		colAplicacao.setPrefWidth(200);
		colAplicacao.setCellValueFactory(new PropertyValueFactory<Dsolicit, String >("Aplicacao"));
		
		TableColumn<Dsolicit, Double> colValorPrev = new TableColumn("Valor");
		colValorPrev.setPrefWidth(100);
		colValorPrev.setCellValueFactory(new PropertyValueFactory<Dsolicit, Double >("ValorPrev"));
		
		TableColumn<Dsolicit, LocalDate> colPrevPagam = new TableColumn("Previsao");
		colPrevPagam.setPrefWidth(100);
		colPrevPagam.setCellValueFactory(new PropertyValueFactory<Dsolicit, LocalDate>("PrevPagam"));
		
		TableColumn<Dsolicit, Integer> colNumParcelas = new TableColumn("Parc.");
		colNumParcelas.setPrefWidth(80);
		colNumParcelas.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer>("NumParcelas"));
		
		TableColumn<Dsolicit, Integer> colParcPagas = new TableColumn("Pagas");
		colParcPagas.setPrefWidth(80);
		colParcPagas.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer >("ParcPagas"));
		
		TableColumn<Dsolicit, Integer> colNumSolicit = new TableColumn("Numero");
		colNumSolicit.setPrefWidth(80);
		colNumSolicit.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer>("NumSolicit"));
		
		TableColumn<Dsolicit, Integer> colProjeto = new TableColumn("Proj.");
		colProjeto.setPrefWidth(80);
		colProjeto.setCellValueFactory(new PropertyValueFactory<Dsolicit, Integer>("Projeto"));
		
		tableDespesas.setItems(tabela);
		tableDespesas.getColumns().addAll(colNumSolicit,colPrevPagam,colValorPrev,colItem,colAplicacao,colNumParcelas,colParcPagas,colProjeto );
		
		return tableDespesas;	
	}
	private static void tbvDespPrevistas_Change() {
		inicializar();
		btnCarregar.setDisable(false);	
	}
	
	private static void btnCarregar_Act() {
		populaAtualDespesa();  // dados do registro selecionado no tbvDespPrevistas -> atualDespesa
		preencheEdts();		   // dados do atualDespesa -> nos campos da tela
		btnValidar.setDisable(false);
		btnCarregar.setDisable(true);	
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
				desabilitaEdts();
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
		if(regra.performEdicao(arrListDados) == true) {
			btnLancar.setDisable(true);
			dadosSolic = despesas.carregarSolicAbertas();
			tbvDespPrevistas.setItems(dadosSolic);
		}
		else
			MessageBox.show("Falha na gravaçao do lancamento", "PERSISTENCIA");	
	}
	
	private static void inicializar() {
		limparCampos();
		habilitaEdts();
		dtpPrevPagam.setValue(LocalDate.now());
		btnValidar.setDisable(true);
		btnLancar.setDisable(true);
		btnCarregar.setDisable(true);
		tbvProjetos.getSelectionModel().selectFirst();
		lblProjSelec.setText("Projeto Selecionado :  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getCodProj() + "  -  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getProjNome());			
	}
	private static boolean dadosEntrados() {
		boolean libera = true;
		String tempS;
		
			// dataSolicit
		atualDespesa.setDataSolicit(LocalDate.now());

			// Item
		if(edtItem.getText().length() < 5) {
			libera = false;
			MessageBox.show("O campo deve ter, no minimo, 5 caracters", "ITEM");
		} else {
			tempS = edtItem.getText();
			if(tempS.length() > 40)  tempS = tempS.substring(0,40);
			atualDespesa.setItem(tempS);
		}

			// Aplicacao
		if(edtAplic.getText().length() < 5) {
			libera = false;
			MessageBox.show("O campo deve ter, no minimo, 5 caracteres", "APLICACAO");
		} else {
			tempS = edtAplic.getText();
			if(tempS.length() > 30) tempS = tempS.substring(0,30);
			atualDespesa.setAplicacao(tempS);
		}
		
			// ValorPrev
		if(edtValPrev.getText().equals("")) {
			libera = false;
			MessageBox.show("E necessario preencher o valor da despesa", "VALOR");
		} else {
			try {
				atualDespesa.setValorPrev(Double.parseDouble(edtValPrev.getText()));
			}catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "VALOR");
			}
		}
		
			// PrevPagam
		if(dtpPrevPagam.getValue() != null)
			atualDespesa.setPrevPagam(dtpPrevPagam.getValue());
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
				atualDespesa.setQuantidade(Double.parseDouble(edtQuant.getText()));
			} catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao Incorreta", "QUANTIDADE");
			}
		}

			// Unidade
		if(rdbDiv.isSelected()) atualDespesa.setUnidade(1);
		if(rdbUnid.isSelected()) atualDespesa.setUnidade(2);
		if(rdbKg.isSelected()) atualDespesa.setUnidade(3);
		if(rdbM.isSelected()) atualDespesa.setUnidade(4);
		if(rdbM2.isSelected()) atualDespesa.setUnidade(5);
		if(rdbM3.isSelected()) atualDespesa.setUnidade(6);
		if(rdbEmb.isSelected()) atualDespesa.setUnidade(7);
		if(rdbSacos.isSelected()) atualDespesa.setUnidade(8);		
		
			// Situacao
		atualDespesa.setSituacao("A");
		
			// NumParcelas
		if(edtParcelas.getText().equals("")) {
			libera = false;
			MessageBox.show("	Necessario especificar a quantidade de parcelas","PARCELAS");
		} else {
			try{
				atualDespesa.setNumParcelas(Integer.parseInt(edtParcelas.getText()));
			} catch(NumberFormatException ne) {
				libera = false;
				MessageBox.show("Digitacao incorreta", "NUMERO DE PARCELAS");
			}
		}

			// Parcelas pagas
		atualDespesa.setParcPagas(0);
		
				// Docum		
		if(edtDocum.getText().length() > 0) {
			tempS = edtDocum.getText();
			if(tempS.length() > 30) tempS = tempS.substring(0,30);
			atualDespesa.setDocum(tempS);
		}
		
			// NumSolicit - Automatico no MySQL

			// Classe
		atualDespesa.setClasse(cbxCentro.getSelectionModel().getSelectedIndex());
		

			// Grupo
		atualDespesa.setGrupo(cbxSubCentro.getSelectionModel().getSelectedIndex());
		
		if((atualDespesa.getClasse() < 1) || (atualDespesa.getGrupo() < 1)) {
			libera = false;
			MessageBox.show("Necessario definir centros de despesa", "CENTROS DESPESAS");
		}
		
			// UltAltera
		atualDespesa.setUltAltera(LocalDate.now());

			// Projeto	

		return libera;
	}
	
	private static void populaAtualDespesa() {
		atualDespesa.setDataSolicit((LocalDate)tbvDespPrevistas.getSelectionModel().getSelectedItem().getDataSolicit());
		atualDespesa.setItem((String)tbvDespPrevistas.getSelectionModel().getSelectedItem().getItem());
		atualDespesa.setAplicacao((String)tbvDespPrevistas.getSelectionModel().getSelectedItem().getAplicacao());
		atualDespesa.setValorPrev((double)tbvDespPrevistas.getSelectionModel().getSelectedItem().getValorPrev());
		atualDespesa.setPrevPagam((LocalDate)tbvDespPrevistas.getSelectionModel().getSelectedItem().getPrevPagam());
		atualDespesa.setQuantidade((double)tbvDespPrevistas.getSelectionModel().getSelectedItem().getQuantidade());
		atualDespesa.setUnidade((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getUnidade());
		atualDespesa.setSituacao((String)tbvDespPrevistas.getSelectionModel().getSelectedItem().getSituacao());
		atualDespesa.setNumParcelas((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getNumParcelas());
		atualDespesa.setParcPagas((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getParcPagas());
		atualDespesa.setDocum((String)tbvDespPrevistas.getSelectionModel().getSelectedItem().getDocum());
		atualDespesa.setNumSolicit((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getNumSolicit());
		atualDespesa.setClasse((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getClasse());
		atualDespesa.setGrupo((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getGrupo());
		atualDespesa.setUltAltera((LocalDate)tbvDespPrevistas.getSelectionModel().getSelectedItem().getUltAltera());	
		atualDespesa.setProjeto((int)tbvDespPrevistas.getSelectionModel().getSelectedItem().getProjeto());

	}
	
	private static void preencheEdts() {
		lblNumero.setText(Integer.toString(atualDespesa.getNumSolicit()));
		edtItem.setText(atualDespesa.getItem());
		edtAplic.setText(atualDespesa.getAplicacao());
		edtValPrev.setText(Double.toString(atualDespesa.getValorPrev()));
		edtParcelas.setText(Integer.toString(atualDespesa.getNumParcelas()));
		dtpPrevPagam.setValue(atualDespesa.getPrevPagam());
		
		int iGrupo = atualDespesa.getClasse();
		int iSubGrupo = atualDespesa.getGrupo();
		subGrupo = Ini.loadProps("Cen" + iGrupo, "SubCen");

		cbxCentro.getSelectionModel().select(iGrupo);
		cbxSubCentro.getSelectionModel().select(iSubGrupo);
		
		tbvProjetos.getSelectionModel().select(0);
		for(int i=0;i< tbvProjetos.getItems().size(); i++){	
			if(atualDespesa.getProjeto() == tbvProjetos.getItems().get(i).getCodProj())
				tbvProjetos.getSelectionModel().select(i);	
		}
		
		
		switch(atualDespesa.getUnidade()) {
			case 1:
				rdbDiv.setSelected(true);
				rdbDiv.requestFocus();
				break;
			case 2:
				rdbUnid.setSelected(true);
				rdbUnid.requestFocus();
				break;
			case 3:
				rdbKg.setSelected(true);
				rdbKg.requestFocus();
				break;
			case 4:
				rdbM.setSelected(true);
				rdbM.requestFocus();
				break;
			case 5:
				rdbM2.setSelected(true);
				rdbM2.requestFocus();
				break;
			case 6:
				rdbM3.setSelected(true);
				rdbM3.requestFocus();
				break;
			case 7:
				rdbEmb.setSelected(true);
				rdbEmb.requestFocus();
				break;
			case 8:
				rdbSacos.setSelected(true);
				rdbSacos.requestFocus();
				break;
		}
	}
	
	private static void limparCampos() {
		lblNumero.setText("");
		edtItem.setText("");
		edtAplic.setText("");
		edtValPrev.setText("");
		dtpPrevPagam.setValue(LocalDate.now());
		edtParcelas.setText("");
		cbxCentro.getSelectionModel().select(0);
		cbxSubCentro.getSelectionModel().select(0);
	}
	
	private static ArrayList populaArrAtualSolicitacao() {
		ArrayList arrList = new ArrayList();
		arrList.add(atualDespesa.getDataSolicit());
		arrList.add(atualDespesa.getItem());
		arrList.add(atualDespesa.getAplicacao());
		arrList.add(atualDespesa.getValorPrev());
		arrList.add(atualDespesa.getPrevPagam());	
		arrList.add(atualDespesa.getQuantidade());
		arrList.add(atualDespesa.getUnidade());
		arrList.add(atualDespesa.getSituacao());
		arrList.add(atualDespesa.getNumParcelas());
		arrList.add(atualDespesa.getParcPagas());
		arrList.add(atualDespesa.getDocum());
		arrList.add(atualDespesa.getNumSolicit());
		arrList.add(atualDespesa.getClasse());
		arrList.add(atualDespesa.getGrupo());
		arrList.add(atualDespesa.getUltAltera());
		arrList.add(atualDespesa.getProjeto());
		arrList.add(1);  // Introduzido para compatibilidade com o metodo "performValidacao"
		                    // "validar" na classe "BusiRuleDespesas"
	
		return arrList;
			
	}

	private static void limparSolicitacao() {
			//		atualDespesa.setDataSolicit(LocalDate.now()); 
					// Inicializada na entrada de dados
		atualDespesa.setItem("");
		atualDespesa.setAplicacao("");
		atualDespesa.setValorPrev(0.);
		atualDespesa.setPrevPagam(LocalDate.now());
		atualDespesa.setQuantidade(0.);
		atualDespesa.setUnidade(-1);
		atualDespesa.setSituacao("");
		atualDespesa.setNumParcelas(0);
		atualDespesa.setParcPagas(0);
		atualDespesa.setDocum("");
		atualDespesa.setClasse(0);
		atualDespesa.setGrupo(0);
			//		atualDespesa.setUltAltera(LocalDate.now());
				// Inicializaada na entradada de dados
		atualDespesa.setProjeto(0);	
	}
	
	private static void tbvProjetos_Change() {
		atualDespesa.setProjeto(tbvProjetos.getSelectionModel().getSelectedItem().getCodProj());

		lblProjSelec.setText("Projeto Selecionado :  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getCodProj() + "  -  " +
			tbvProjetos.getSelectionModel().getSelectedItem().getProjNome());

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
	
	private static void cbxCentro_Change() {
		int i = cbxCentro.getSelectionModel().getSelectedIndex();
		cbxSubCentro.setItems(Ini.loadProps("Cen" + i, "SubCen"));
		atualDespesa.setClasse(i);
	}
	
	private static void desabilitaEdts() {
		edtItem.setEditable(false);
		edtDocum.setEditable(false);
		edtAplic.setEditable(false);
		edtValPrev.setEditable(false);
		edtParcelas.setEditable(false);
		edtQuant.setEditable(false);
		
	}
	
	private static void habilitaEdts() {
		edtItem.setEditable(true);
		edtDocum.setEditable(true);
		edtAplic.setEditable(true);
		edtValPrev.setEditable(true);
		edtParcelas.setEditable(true);
		edtQuant.setEditable(true);
	}
	
	private static void limparAtualDespesa() {
//		atualDespesa.setDataSolicit(LocalDate.now()); 
					// Inicializada na entrada de dados
		atualDespesa.setItem("");
		atualDespesa.setAplicacao("");
		atualDespesa.setValorPrev(0.);
		atualDespesa.setPrevPagam(LocalDate.now());
		atualDespesa.setQuantidade(0.);
		atualDespesa.setUnidade(-1);
		atualDespesa.setSituacao("");
		atualDespesa.setNumParcelas(0);
		atualDespesa.setParcPagas(0);
		atualDespesa.setDocum("");
		atualDespesa.setClasse(0);
		atualDespesa.setGrupo(0);
//		atualDespesa.setUltAltera(LocalDate.now());
				// Inicializada na entradada de dados
		atualDespesa.setProjeto(0);	
	}
}

