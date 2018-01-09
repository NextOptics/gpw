package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;
import gpw.control.*;
import javafx.collections.*;

import gpw.dao.DaoDespesas;
import gpw.dao.DaoDespAutorz;
import gpw.dao.DaoPagamentos;
import gpw.model.Dpagamen;
import gpw.model.Bconta;
import gpw.model.Dconta;
import gpw.dao.DaoBconta;
import gpw.dao.DaoDconta;



/*
		public boolean validar(ArrayList  dadosAutorizacao)
		public boolean lancar(ArrayList  dadosAutorizacao)
		public boolean editar(ArrayList  dadosAutorizacao)
		public boolean confirmar(ArrayList  dadosAutorizacao)
		public boolean carregar(ArrayList  dadosAutorizacao)
		private static void populaAtualContaBanc(int conta) {
		private ArrayList populaArrSolicitacao(ArrayList dados) {
		private ArrayListpopulaArrLancCartao(ArrayList dados) {

*/

public class BusiRuleDespPagam implements BusiRuleSelec {
	static Bconta atualContaBanc;		// Objeto que representa um registro da tabela bconta
	static DaoBconta contasMovimentoBanc; 
	static ObservableList<Bconta> historicoBconta; 
	
	public boolean validar(ArrayList dados) {
		boolean valida = true;
		
		if(Math.abs((Double)dados.get(2) - (Double)dados.get(8)) > 0.15 * (Double)dados.get(8))
			MessageBox.show("Valor Pago discrepante com  valor Autorizado", "ATENCAO");
		if(Math.abs((Double)dados.get(2) - (Double)dados.get(8)) > 0.49 * (Double)dados.get(8)) {
			MessageBox.show("Valor Pago Nao aceitavel", "ERRO");
			valida = false;
		}
		
		if(DateUtils.buscarMoc(LocalDate.now()) < 0.001){
			valida = false;
			MessageBox.show("Nao estao disponiveis indices de Inflacao para a data", "ERRO");
		}
		
		if(((Integer)dados.get(23)) < 1) {
			valida = false;
			MessageBox.show("Numero de paracelas nao pode ser zero", "ERRO");
		}
		
		return valida;	
	}
	
	public boolean lancar(ArrayList dados) {
		DaoDespesas despesa = new DaoDespesas();
		DaoDespAutorz autorizacao = new DaoDespAutorz();
		
		boolean valida = true;
			
				// Atualiza registro em Dautorz
		dados.set(11, "C");   									// Seta situacao para "C" em Dautorz
		autorizacao.editar(dados);								// Atualiza a autorizacao de despesa
		
				// Grava registro novo em Dpagamen
		double valorEmMoc = 0.;
		try {
			valorEmMoc = (Double)dados.get(2) / DateUtils.buscarMoc(LocalDate.now());   //  valorPago / valor de moc aplicavel													
		}catch (Exception e) {
			MessageBox.show("Indice de inflacao nao disponivel para a Data", "ERRO");
		}	
		dados.set(5, valorEmMoc);   									// Seta valor da despesa em moc
		dados.set(4, "A");												// Seta situacao para "A"  em Dpagamen
		DaoPagamentos pagamento = new DaoPagamentos();
		pagamento.gravar(dados);										// Gera novo registro em Dpagamen					
		ArrayList<Dpagamen> arrDados = pagamento.ultRegPagam(); 		// Numero do pagamento atual
		dados.set(0, (Integer)arrDados.get(0).getNumPagam());   		// Numero ultimo pagamento colocado na posicao 0  ArrayList dados
																																																																																																												
				// Debita o valor pago  na respectiva conta (bancaria ou cartao)												
		if(((String)dados.get(12)).equals("B")){	// Eh uma conta bancaria
		
					// Atualiza registro em Dsolicit
			dados.set(24, ((Integer)dados.get(24) + 1)); 							// Atualiza numero de parcelas pagas
			despesa.editar(populaArrSolicitacao(dados));        // Atualiza a solicitacao de despesa

			atualContaBanc = new Bconta();
			populaAtualContaBanc((Integer)dados.get(13));  				// (Integer)dados.get(13) = Numero da conta
			DaoBconta regNovo = new DaoBconta();						// Grava um novo registro na conta bancaria
			regNovo.gravar(populaArrNovoSaldoBanc(dados));
		}
		
		if(((String)dados.get(12)).equals("D")) {
					// Atualiza registro em Dsolicit
			dados.set(24, (Integer)dados.get(23)); 				// Considera todas a parcelas como pagas
			despesa.editar(populaArrSolicitacao(dados));        // Atualiza a solicitacao de despesa	
													// Lanca a despesa na conta cartao
			DaoDconta regCartao = new DaoDconta();
			ArrayList cartao = populaArrLancCartao(dados);
			cartao.set(2, ((Double)dados.get(2) / (Integer)dados.get(23)));
			LocalDate dataTemp = LocalDate.now();
			String vNumSolicit = Integer.toString((Integer)dados.get(0));
			String vTotParcs = Integer.toString((Integer)dados.get(23));
			for(int vParcela=1; vParcela <= (Integer)dados.get(23); vParcela++) {
				cartao.set(7, dataTemp);
				dataTemp = DateUtils.mesSeguinte(dataTemp);
				cartao.set(1, vNumSolicit + " : " + Integer.toString(vParcela) + "/" + vTotParcs);
				regCartao.gravar(cartao);
				TestUtils.printDcontaMov(cartao);
			}		
		}
		return valida;	
	}
	
	private ArrayList populaArrSolicitacao(ArrayList dados) {
								// coloca dados na sequencia apropriada a DaoDespesas
		ArrayList dadosDesp = new ArrayList();
		dadosDesp.add((LocalDate) dados.get(15));		//	0	getDataSolicit()	
		dadosDesp.add((String) dados.get(16));			//	1	getItem()
		dadosDesp.add((String) dados.get(17));			//	2	getAplicacao()
		dadosDesp.add((Double) dados.get(18));			//	3	getValorPrev()
		dadosDesp.add((LocalDate) dados.get(19));		//	4	getPrevPagam()
		dadosDesp.add((Double) dados.get(20));			//	5	getQuantidade()
		dadosDesp.add((Integer) dados.get(21));			//	6	getUnidade()	
		dadosDesp.add((String) dados.get(22));			//	7	getSituacao()
		dadosDesp.add((Integer) dados.get(23));			//	8	getNumParcelas()
		dadosDesp.add((Integer) dados.get(24));			//	9	getParcPagas()
		dadosDesp.add((String) dados.get(25));			//	19	getDocum()	
		dadosDesp.add((Integer) dados.get(26));			//	11	getNumSolicit()	
		dadosDesp.add((Integer) dados.get(27));			//	12	getClasse()
		dadosDesp.add((Integer) dados.get(28));			//	13	getGrupo()
		dadosDesp.add((LocalDate) dados.get(29));		//	14	getUltAltera()	
		dadosDesp.add((Integer) dados.get(30));			//	15	getProjeto()
		return dadosDesp;
	}
	
	private ArrayList populaArrNovoSaldoBanc(ArrayList dados){
		ArrayList arrList = new ArrayList();
		arrList.add(LocalDate.now());											// 0	DataLanc
		arrList.add(Integer.toString((Integer)dados.get(6)));				    // 1 	Docum
		arrList.add((Double)dados.get(2));   									// 2	ValorPago
		arrList.add("D");														// 3	NatOpera
		arrList.add(atualContaBanc.getSaldoAtual());							// 4	SaldoAnt
		arrList.add(atualContaBanc.getSaldoAtual() - (Double)dados.get(2));		// 5	SaldoAtual
		arrList.add((String)dados.get(16));										// 6	Historico
		arrList.add(537);														// 7	Usuario
		arrList.add(" ");														// 8	ContaOrigDest
		arrList.add((Integer)dados.get(0));										// 9	Numpagam
		arrList.add((Integer)dados.get(13));									// 10	conta
		return arrList;
	}
	
	private ArrayList populaArrLancCartao(ArrayList dados) {
		ArrayList arrList = new ArrayList();	
		arrList.add(LocalDate.now());											// 0 DataLanc - DataCompra
		arrList.add("");														// 1 Documento	
		arrList.add(0.);									        			// 2 ValorPago (valor da compra/parcela)
		arrList.add("D");														// 3 NatOpera
		arrList.add(537);														// 4 Usuario
		arrList.add("A");														// 5 Estado
		arrList.add(0.); 	// calculado quando do faturamento do cartão		// 6 valorMoc
		arrList.add(LocalDate.now());											// 7 dataParcela
		arrList.add((Integer)dados.get(0));										// 8 NumPagam
		arrList.add("");														// 9 Campo Padding
		arrList.add((Integer)dados.get(13));									// 10 conta
		return arrList;
	}
	
	public boolean editar(ArrayList dados) {
		boolean valida = true;
		
		return valida;	
	}
	
	public boolean confirmar(ArrayList dados) {
		boolean valida = true;
		
		return valida;	
	}
	
	public boolean carregar(ArrayList dados) {
		boolean valida = true;
		
		return valida;	
	}
	
	private static void populaAtualContaBanc(int conta) {
		contasMovimentoBanc = new DaoBconta();
		historicoBconta = contasMovimentoBanc.carregaSaldoBanc(conta);   // atualPagRecebimento.getContaVinc()
			// atualContaBanc carregado com os dados dos ultimo registro do movimento da conta bancaria entrada como 
			// parametro antes do credito do recebimento atual
		atualContaBanc.setDataLanc((LocalDate)historicoBconta.get(0).getDataLanc());
		atualContaBanc.setDocumento(historicoBconta.get(0).getDocumento());
		atualContaBanc.setValorMov(historicoBconta.get(0).getValorMov());
		atualContaBanc.setNatOpera(historicoBconta.get(0).getNatOpera());
		atualContaBanc.setSaldoAnt(historicoBconta.get(0).getSaldoAnt());
		atualContaBanc.setSaldoAtual(historicoBconta.get(0).getSaldoAtual());
		atualContaBanc.setDescrLanc(historicoBconta.get(0).getDescrLanc());
		atualContaBanc.setUser(historicoBconta.get(0).getUser());
		atualContaBanc.setLancaNum(historicoBconta.get(0).getLancaNum());
		atualContaBanc.setContaOrigDest(historicoBconta.get(0).getContaOrigDest());
		atualContaBanc.setNumPagam(historicoBconta.get(0).getNumPagam());
	}
}

