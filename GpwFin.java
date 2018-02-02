package gpw;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.scene.control.cell.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.control.MenuBar;
import gpw.model.Inflacao;
import java.util.HashMap;

import java.time.LocalDate;

import gpw.view.*;
import gpw.dao.DaoInflacao;
import gpw.control.DateUtils;



public class GpwFin extends Application { 

	public static HashMap<LocalDate, Double> mocMensal = new HashMap<LocalDate, Double>();
	private static ObservableList<Inflacao> indices;
	private static DaoInflacao registro;
	
	@Override
	public void start(Stage primaryStage) {
		//setUserAgentStylesheet(STYLESHEET_MODENA);
		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		
							//  *************  INICIO DO  MENU  **************
		MenuBar menuBar = new MenuBar();
		
		Menu menuReceitas = new Menu("Receitas");
			Menu menuRecebimentos = new Menu("Recebimentos");
				MenuItem menuItemRecAgenda = new MenuItem("Agenda");
					menuItemRecAgenda.setOnAction(e -> menuItemRecAgenda_Act());
				MenuItem menuItemRecConfirma = new MenuItem("Confirma");
					menuItemRecConfirma.setOnAction(e -> menuItemRecConfirma_Act());
				MenuItem menuItemRecMostra = new MenuItem("Mostra");
					menuItemRecMostra.setOnAction( e -> menuItemRecMostra_Act());
				MenuItem menuItemRecEdita = new MenuItem("Edita");
					menuItemRecEdita.setOnAction(e -> menuItemRecEdita_Act());
				MenuItem menuItemRecCancela = new MenuItem("Cancela");
				Menu menuRecRelatorios = new Menu("Relatorios");
					MenuItem menuItemRecRelAbertos = new MenuItem("Receb. em Aberto");
					MenuItem menuItemRecRelEfetivados = new MenuItem("Receb. Efetivados");
				menuRecRelatorios.getItems().addAll(menuItemRecRelAbertos, menuItemRecRelEfetivados);					
				menuRecebimentos.getItems().addAll(menuItemRecAgenda, menuItemRecConfirma, menuItemRecMostra, menuItemRecEdita, menuItemRecCancela, menuRecRelatorios);
			Menu menuPagadores = new Menu("Pagadores");
				MenuItem menuItemPagCadastra = new MenuItem("Cadastra");
					menuItemPagCadastra.setOnAction(e -> menuItemPagCadastra_Act());
				MenuItem menuItemPagMostra = new MenuItem("Mostra");
					menuItemPagMostra.setOnAction(e -> menuItemPagMostra_Act());
				MenuItem menuItemPagEdita = new MenuItem("Edita");
					menuItemPagEdita.setOnAction(e -> menuItemPagEdita_Act());
				MenuItem menuItemPagDesativar = new MenuItem("Desativar");
				MenuItem menuItemPagRelatorios = new MenuItem("Relatorios");
				menuPagadores.getItems().addAll(menuItemPagCadastra, menuItemPagMostra, menuItemPagEdita, menuItemPagDesativar, menuItemPagRelatorios);	
			menuReceitas.getItems().addAll(menuRecebimentos, menuPagadores);
		
		
		Menu menuDespesas = new Menu("Despesas");
			Menu menuDespPrevisao = new Menu("Previsao");
				MenuItem menuItemDespPrevEmite = new MenuItem("Emite");
					menuItemDespPrevEmite.setOnAction(e -> menuItemDespPrevEmite_Act());
				MenuItem menuItemDespPrevEdita = new MenuItem("Edita");
					menuItemDespPrevEdita.setOnAction(e -> menuItemDespPrevEdita_Act());
				MenuItem menuItemDespPrevCancela = new MenuItem("Cancela");
				menuDespPrevisao.getItems().addAll(menuItemDespPrevEmite, menuItemDespPrevEdita, menuItemDespPrevCancela);
			
			Menu menuDespAgendamento = new Menu("Agendamento");
				MenuItem menuItemDespAgenEmite = new MenuItem("Emite");
					menuItemDespAgenEmite.setOnAction(e ->  menuItemDespAgenEmite_Act());
				MenuItem menuItemDespAgenCancela = new MenuItem("Cancela");
				menuDespAgendamento.getItems().addAll(menuItemDespAgenEmite, menuItemDespAgenCancela);
			
			Menu menuDespPagamento = new Menu("Pagamento");
				MenuItem menuItemDespPagConfirma = new MenuItem("Confirma");
					menuItemDespPagConfirma.setOnAction(e -> menuItemDespPagConfirma_Act());
				MenuItem menuItemDespPagCancela = new MenuItem("Cancela");
				menuDespPagamento.getItems().addAll(menuItemDespPagConfirma, menuItemDespPagCancela);
			
			Menu menuDespRelatorios = new Menu("Relatorios");
				MenuItem menuItemDespRelPrevistas = new MenuItem("Despesas Previstas");
				MenuItem menuItemDespRelPagAgendados = new MenuItem("Pagamentos Agendados");
				MenuItem menuItemDespRelPagRealizados = new MenuItem("Pagamentos Realizados");
				MenuItem menuItemDespRelPagAberto = new MenuItem("Pagamentos em Aberto");
				menuDespRelatorios.getItems().addAll(menuItemDespRelPrevistas, menuItemDespRelPagAgendados, menuItemDespRelPagRealizados, menuItemDespRelPagAberto);
			
			menuDespesas.getItems().addAll(menuDespPrevisao, menuDespAgendamento, menuDespPagamento, menuDespRelatorios);
		
		
		
		Menu menuBancos = new Menu("Bancos");
			Menu menuBancContas = new Menu("ContasBanc");
				MenuItem menuItemBancConCadastra = new MenuItem("Cadastra");
					menuItemBancConCadastra.setOnAction(e -> menuItemBancConCadastra_Act());
				MenuItem menuItemBancConMostra = new MenuItem("Mostra");
				MenuItem menuItemBancConEdita = new MenuItem("Edita");
				Menu menuBancConRelatorios = new Menu("Relatorios");
					MenuItem menuItemBancConRelHistorico = new MenuItem("Historico");
					menuBancConRelatorios.getItems().addAll(menuItemBancConRelHistorico);
				MenuItem menuItemBancConEncerra = new MenuItem("Encerra");
				MenuItem menuItemBancConMovimenta = new MenuItem("Credito/Debito");
				menuBancContas.getItems().addAll(menuItemBancConCadastra, menuItemBancConMostra, menuItemBancConEdita, menuBancConRelatorios, menuItemBancConEncerra, menuItemBancConMovimenta);
					
			
			Menu menuBancAplicacoes = new Menu("Aplicacoes");
				MenuItem menuItemBancAplMovimenta = new MenuItem("Movimenta");
				Menu menuBancAplContas = new Menu("Contas");
					MenuItem menuItemBancAplConCadastra = new MenuItem("Cadastra");
					MenuItem menuItemBancAplConMostra = new MenuItem("Mostra");
					MenuItem menuItemBancAplConEdita = new MenuItem("Edita");
					MenuItem menuItemBancAplConInicial = new MenuItem("Aplicacao Inicial");
					MenuItem menuItemBancAplConEncerra = new MenuItem("Encerra Conta");
					menuBancAplContas.getItems().addAll(menuItemBancAplConCadastra, menuItemBancAplConMostra, menuItemBancAplConEdita, menuItemBancAplConInicial, menuItemBancAplConEncerra);
				
				MenuItem menuItemBancAplRendimentos = new MenuItem("lanca Rendimentos");
				Menu menuBancAplRelatorios = new Menu("Relatorios");
					MenuItem menuItemBancAplRelBasico = new MenuItem("Relatorio 1");
					MenuItem menuItemBancAplRelInflacao = new MenuItem("Indices Inflacao");
					menuBancAplRelatorios.getItems().addAll(menuItemBancAplRelBasico, menuItemBancAplRelInflacao);
				
				menuBancAplicacoes.getItems().addAll(menuItemBancAplMovimenta, menuBancAplContas, menuItemBancAplRendimentos, menuBancAplRelatorios);
			
			
			Menu menuBancCartoes = new Menu("Cartoes");
				Menu menuBancCartCartoes = new Menu("Cartoes");
					MenuItem menuItemBancCartCartCadastra = new MenuItem("Cadastra");
					MenuItem menuItemBancCartCartMostra = new MenuItem("Mostra");
					MenuItem menuItemBancCartCartEdita = new MenuItem("Edita");
					MenuItem menuItemBancCartCartEncerra = new MenuItem("Encerra");
					menuBancCartCartoes.getItems().addAll(menuItemBancCartCartCadastra, menuItemBancCartCartMostra, menuItemBancCartCartEdita, menuItemBancCartCartEncerra);
				MenuItem menuItemBancCartFatura = new MenuItem("Fatura");
				Menu menuBancCartRelatorios = new Menu("Relatorios");
					MenuItem menuItemBancCartRelHistorico = new MenuItem("Historico");
					menuBancCartRelatorios.getItems().addAll(menuItemBancCartRelHistorico);
				menuBancCartoes.getItems().addAll(menuBancCartCartoes, menuItemBancCartFatura, menuBancCartRelatorios);
				
			
			menuBancos.getItems().addAll(menuBancContas, menuBancAplicacoes, menuBancCartoes);
		
		Menu menuImoveis = new Menu("Imoveis");
			MenuItem menuItemImovCadastra = new MenuItem("Cadastra");
			MenuItem menuItemImovEdita = new MenuItem("Edita");
			MenuItem menuItemImovAluga = new MenuItem("Aluga");
			MenuItem menuItemImovRelatorios = new MenuItem("Relatorios");
			menuImoveis.getItems().addAll(menuItemImovCadastra, menuItemImovEdita, menuItemImovAluga, menuItemImovRelatorios);
			
		
		Menu menuProjetos = new Menu("Projetos");
			MenuItem menuItemProjCadastra = new MenuItem("Cadastra");
			Menu menuProjMostra = new Menu("Mostra");
				MenuItem menuItemProjMosAtivos = new MenuItem("Ativos");
				MenuItem menuItemProjMosEncerrados = new MenuItem("Encerrados");
				menuProjMostra.getItems().addAll(menuItemProjMosAtivos, menuItemProjMosEncerrados);
			MenuItem menuItemProjEdita = new MenuItem("Edita");
			MenuItem menuItemProjEncerra = new MenuItem("Encerra");
			menuProjetos.getItems().addAll(menuItemProjCadastra, menuProjMostra, menuItemProjEdita, menuItemProjEncerra);
			
		
		Menu menuVeiculos = new Menu("Veiculos");
			MenuItem menuItemVeicCadastra = new MenuItem("Cadastra");
			MenuItem menuItemVeicEdita = new MenuItem("Edita");
			MenuItem menuItemVeicSeguros = new MenuItem("Seguros");
			MenuItem menuItemVeicManutencao = new MenuItem("Manutencao");
			MenuItem menuItemVeicRelatorios = new MenuItem("Relatorios");
			menuVeiculos.getItems().addAll(menuItemVeicCadastra, menuItemVeicEdita, menuItemVeicSeguros, menuItemVeicManutencao, menuItemVeicRelatorios);
		
		Menu menuAnalise = new Menu("Analise");
			MenuItem menuItemAnaConsolidado = new MenuItem("Consolidado");
			MenuItem menuItemAnaFluxo = new MenuItem("Fluxo de Caixa");
			menuAnalise.getItems().addAll(menuItemAnaConsolidado, menuItemAnaFluxo);
		
		Menu menuGeral = new Menu("Geral");
			MenuItem menuItemGerUser = new MenuItem("Novo Usuario");
			Menu menuGerInflacao = new Menu("Inflacao");
				MenuItem menuItemGerInflMostra = new MenuItem("Mostra Indices");
				menuItemGerInflMostra.setOnAction(e -> menuItemGerInflMostra_Act());
				
			
				MenuItem menuItemGerInflLanca = new MenuItem("Lanca Indices");
				menuItemGerInflLanca.setOnAction(e -> menuItemGerInflLanca_Act());
				menuGerInflacao.getItems().addAll(menuItemGerInflMostra, menuItemGerInflLanca);
				
			menuGeral.getItems().addAll(menuItemGerUser, menuGerInflacao);
		
		menuBar.getMenus().addAll(menuReceitas, menuDespesas, menuBancos, menuImoveis, menuProjetos, menuVeiculos, menuAnalise, menuGeral);
		
							//  ************  FINAL DO MENU **************
		VBox paneMain = new VBox();															
		
		paneMain.setSpacing(10);
		paneMain.setPadding(new Insets(10, 10, 10, 10));
		paneMain.getChildren().addAll(menuBar);
		
		Scene scene = new Scene(paneMain);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Gpw - Administracao Financeira");
		
		registro = new DaoInflacao();
		carregaInflacao();
		
		
		
		
		
		primaryStage.show();
		primaryStage.setX(0);
		primaryStage.setY(0);
		
		//					***************
		
	}
	
	public void menuItemGerInflMostra_Act() {IndicesInflacao.show();}
	
	public void menuItemGerInflLanca_Act() {LancaIndicesInfla.show();}
	
	public void menuItemRecAgenda_Act() {RecAgenda.show();}
	
	public void menuItemRecConfirma_Act() {RecConfirma.show();}
	
	public void menuItemRecMostra_Act() {RecMostra.show();}
	
	public void menuItemRecEdita_Act() {RecEdita.show();}
	
	public void menuItemPagCadastra_Act() {PagCadastra.show();}
	
	public void menuItemPagMostra_Act() {PagMostra.show();}
	
	public void menuItemPagEdita_Act() {PagEdita.show();}
	
	public void menuItemDespPrevEmite_Act() {DespPrevEmite.show();}
	
	public void menuItemDespPrevEdita_Act() {DespPrevEdita.show();}
	
	public void menuItemDespAgenEmite_Act() {DespAgenEmite.show();}
	
	public void menuItemDespPagConfirma_Act() {DespPagConfirma.show();}
	
	public void menuItemBancConCadastra_Act() {
		System.out.println();
		System.out.println("******  Entrei no menuItem");
		ContaBancCadastra.show();}
			    
	
	public static void main (String[] args) {
		launch(args);
	}
	
	public void carregaInflacao() {
		indices = registro.buscarInflacao();
		int tamanho = indices.size();
		for(int i = 0; i< tamanho; i++) {	
			mocMensal.put(indices.get(i).getDataMoc(), indices.get(i).getMoc());
		}
	}
	
	public static boolean existeMoc(LocalDate xData) {
		boolean valida = true;
		LocalDate dataRef;
		dataRef = DateUtils.lastDateOfPreviousMonth(xData);
		if(!mocMensal.containsKey(dataRef)) {
			if(LocalDate.now().getDayOfMonth() < 12 ){
				dataRef = DateUtils.lastDateOfPreviousMonth(dataRef);
				if(!mocMensal.containsKey(dataRef)) {
					valida = false;
				}
			}else {
				valida = false;
			}	
		}
		return valida;	
	}
	
	public static double mocAplicavel(LocalDate xData) {
		LocalDate dataRef;
		boolean valida = true;
		dataRef = DateUtils.lastDateOfPreviousMonth(xData);
		if(!mocMensal.containsKey(dataRef)) {
			if(LocalDate.now().getDayOfMonth() < 12 ){
				dataRef = DateUtils.lastDateOfPreviousMonth(dataRef);
				if(!mocMensal.containsKey(dataRef)) {
					valida = false;
				}
			}else {
				valida = false;
			}	
		}
		if(valida){
			return mocMensal.get(dataRef); 
		}
		else {
			MessageBox.show("Inflacao Nao disponivel...", "ERRO GRAVE");
			return -1.0;
		}
	}
}
