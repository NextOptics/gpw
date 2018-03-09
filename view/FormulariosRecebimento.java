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
import javafx.scene.text.*;
import java.time.LocalDate;

import gpw.control.*;
import gpw.model.Pagadores;
import gpw.model.Recebimentos;
import gpw.model.ContasBanc;
import gpw.dao.DaoPagadores;
import gpw.dao.DaoContasBanc;
import gpw.dao.DaoRecebimentos;


public abstract class FormulariosRecebimento {

	FormulariosRecebimento() {}

	FormSelec formSelecionado;

	static Stage stagePrimary;
	static Scene scenePrimary;
	
	static Label lblRotDescr,lblRotNome,lblRotValor,lblRotDia,lblRotAgente,lblRotFirstLanc,
				 lblDescr,   lblNome,   lblValor,   lblDia,   lblAgente,   lblFirstLanc;
	
	static Label lblRotCentro,lblRotSubCentro,lblRotInicio,lblRotTermino,lblRotConta,
				 lblCentro,   lblSubCentro,   lblInicio,   lblTermino,   lblConta;


	static Label lblComiss,lblImpRenda,lblOutras,lblValPago,lblDocum,lblRotPrev;
	
	static Label     lblRotValNovo,lblRotComiss,lblRotImpRenda,lblRotOutras,lblRotMeses, lblRotApartir,lblRotValPago,lblRotDocum;

	static TextField edtValNovo,   edtComiss,   edtImpRenda,   edtOutras,   edtMeses, edtValPago, edtDocum;

	static DatePicker dtpApartir;
				 
	static Button  btnCarregar, btnValidar, btnLancar;

	static ChoiceBox cbxCentro, cbxSubCentro;

	static DatePicker dtpPrevPagam;
	
	static VBox panePrimary, paneCentro, paneSubCentro, paneDescr,paneNome,paneValor,
				paneDia,paneAgente,paneInicio,paneTermino,paneFirstLanc, paneConta, 
				paneValNovo, paneComiss, paneImpRenda, paneOutras,paneMeses, paneApartir, paneValPago,paneDocum;

	static HBox paneLinha1, paneLinha2, paneLinha3, paneLinha4, paneBotoes;

	static DaoPagadores pagadores; // Objeto criado para acessar metodos de DaoPagadores
	static DaoContasBanc contasBancarias;
	static DaoRecebimentos recebimentos;

	static TableView<Pagadores> tbvPagadores;
	static TableView<Recebimentos> tbvRecebimentos;

	static ObservableList<Pagadores> dadosPagadores;
	static ObservableList<ContasBanc> contasBancAtivas; // Contem os registros lidos da tabela contasBancAtivasbanc do banco de dados								
	static ObservableList grupo, subGrupo;
	static ObservableList<Recebimentos> dadosRecebimentos; // Recebe os Recebimentos lidos no dao
	static ObservableList<ContasBanc> contas; // Contem os registros lidos da tabela contasbanc do banco de dados
											  
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

	protected static TableView<Pagadores> tablePagadoresCadas(ObservableList<Pagadores> tabela) {
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
	
	protected static void tbvPagadores_Change() {
		inicializar();
		btnCarregar.setDisable(false);	
	}

	protected static void tbvRecebimentos_Change() {
		btnCarregar.setDisable(false);
	}
	
	
	protected static void preencheEdts() {
		
	}

	protected static void populaAtualPagador() {

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
	
	protected static void inicializar() {
	
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
	
	protected static void limparAtualPagador() {
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
	
	protected static boolean dadosEntrados() {
		boolean libera = true;
	
		return libera;
	}


	protected static void populaAtualRecebimento() {
		
		atualRecebimento.setCodPagador((Integer)tbvRecebimentos.getSelectionModel().getSelectedItem().getCodPagador());
		atualRecebimento.setEstPagador(tbvRecebimentos.getSelectionModel().getSelectedItem().getEstPagador());
		atualRecebimento.setRecDescr(tbvRecebimentos.getSelectionModel().getSelectedItem().getRecDescr());
		atualRecebimento.setValContrato(tbvRecebimentos.getSelectionModel().getSelectedItem().getValContrato());
		atualRecebimento.setContratoInic(tbvRecebimentos.getSelectionModel().getSelectedItem().getContratoInic());

		atualRecebimento.setContratoFim(tbvRecebimentos.getSelectionModel().getSelectedItem().getContratoFim());
		atualRecebimento.setNomeContrato(tbvRecebimentos.getSelectionModel().getSelectedItem().getNomeContrato());
		atualRecebimento.setContaVinc(tbvRecebimentos.getSelectionModel().getSelectedItem().getContaVinc());
		atualRecebimento.setCentroReceb(tbvRecebimentos.getSelectionModel().getSelectedItem().getCentroReceb());
		atualRecebimento.setSubCentroRec(tbvRecebimentos.getSelectionModel().getSelectedItem().getSubCentroRec());
	
		atualRecebimento.setDataLanc(tbvRecebimentos.getSelectionModel().getSelectedItem().getDataLanc());
		atualRecebimento.setDiaVenc(tbvRecebimentos.getSelectionModel().getSelectedItem().getDiaVenc());
		atualRecebimento.setAgente(tbvRecebimentos.getSelectionModel().getSelectedItem().getAgente());
		atualRecebimento.setCodReceb(tbvRecebimentos.getSelectionModel().getSelectedItem().getCodReceb());
		atualRecebimento.setRecEstado(tbvRecebimentos.getSelectionModel().getSelectedItem().getRecEstado());

		atualRecebimento.setDataLanc(tbvRecebimentos.getSelectionModel().getSelectedItem().getDataLanc());
		atualRecebimento.setPrevPagam(tbvRecebimentos.getSelectionModel().getSelectedItem().getPrevPagam());
		atualRecebimento.setDataRecebido(tbvRecebimentos.getSelectionModel().getSelectedItem().getDataRecebido());
		atualRecebimento.setValorPrev(tbvRecebimentos.getSelectionModel().getSelectedItem().getValorPrev());
		atualRecebimento.setValRecebido(tbvRecebimentos.getSelectionModel().getSelectedItem().getValRecebido());
		
		atualRecebimento.setDocumento(tbvRecebimentos.getSelectionModel().getSelectedItem().getDocumento());
		atualRecebimento.setRecebidoMoc(tbvRecebimentos.getSelectionModel().getSelectedItem().getRecebidoMoc());
		atualRecebimento.setImpRenda(tbvRecebimentos.getSelectionModel().getSelectedItem().getImpRenda());
		atualRecebimento.setComisRec(tbvRecebimentos.getSelectionModel().getSelectedItem().getComisRec());
		atualRecebimento.setOutrasDesp(tbvRecebimentos.getSelectionModel().getSelectedItem().getOutrasDesp());
	}
	
	protected static ArrayList populaArrAtualRecebimento() {
		ArrayList arrList = new ArrayList();
			
		return arrList;
	}

	protected static TableView<Recebimentos> tableRecebimentosAberto(ObservableList<Recebimentos> tabela) {
		TableView<Recebimentos> tableAbertos = new TableView<Recebimentos>();
		
		TableColumn<Recebimentos, Integer> colCodigo = new TableColumn("Cod.");
		colCodigo.setPrefWidth(50);
		colCodigo.setCellValueFactory(new PropertyValueFactory<Recebimentos, Integer>("CodReceb"));
	
		TableColumn<Recebimentos, String> colPrevPagam = new TableColumn("Vencim.");
		colPrevPagam.setPrefWidth(100);
		colPrevPagam.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("PrevPagam"));
			
		TableColumn<Recebimentos, String> colValorPrev = new TableColumn("Val.Prev");
		colValorPrev.setPrefWidth(80);
		colValorPrev.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("ValorPrev"));
		
		TableColumn<Recebimentos, Integer> colCodPagador = new TableColumn("Pagador");
		colCodPagador.setPrefWidth(60);
		colCodPagador.setCellValueFactory(new PropertyValueFactory<Recebimentos, Integer>("CodPagador"));
	
		TableColumn<Recebimentos, String> colNomeContrato = new TableColumn("Nome contr.");
		colNomeContrato.setPrefWidth(200);
		colNomeContrato.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("NomeContrato"));
		
		TableColumn<Recebimentos, String> colInicio = new TableColumn("Inicio");
		colInicio.setPrefWidth(80);
		colInicio.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("ContratoInic"));
		
		TableColumn<Recebimentos, String> colTermino = new TableColumn("Termino");
		colTermino.setPrefWidth(80);
		colTermino.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("ContratoFim"));
		
		TableColumn<Recebimentos, String> colAgente = new TableColumn("Agente");
		colAgente.setPrefWidth(150);
		colAgente.setCellValueFactory(new PropertyValueFactory<Recebimentos, String>("Agente"));
			
		tableAbertos.setItems(tabela);
		tableAbertos.getColumns().addAll(colCodigo,colPrevPagam,colValorPrev,colCodPagador,colNomeContrato,
										colInicio, colTermino, colAgente);
		return tableAbertos;
	}

	protected static void cbxCentro_Change() {
		int i = cbxCentro.getSelectionModel().getSelectedIndex();
		cbxSubCentro.setItems(Ini.loadProps("RecCen" + i, "SubReceb"));	
	}
	
	protected static void cbxSubCentro_Change() {
		int centro = cbxCentro.getSelectionModel().getSelectedIndex(); //******* ?????
		int subcentro = cbxSubCentro.getSelectionModel().getSelectedIndex();
		if(centro > 0 && subcentro >0) {
			
			dadosRecebimentos = recebimentos.carregaRecebimentos(centro, subcentro);
			tbvRecebimentos.setItems(dadosRecebimentos);
		}	
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
