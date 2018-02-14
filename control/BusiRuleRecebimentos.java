package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import gpw.control.DateUtils;
import java.time.LocalDate;
import gpw.view.MessageBox;
import javafx.collections.*;

import gpw.model.Bconta;		// Bconta é conta de movimento bancario
import gpw.model.Recebimentos;
import gpw.dao.DaoRecebimentos;
import gpw.dao.DaoBconta;    // Acessa contas de movimento bancario
import gpw.GpwFin;


/*
		public boolean validar(ArrayList dados)
		public boolean lancar(ArrayList dados)
		public boolean editar(ArrayList dados)
		public boolean confirmar(ArrayList dados)
						private static ArrayList novoRegistroConta()
						private static void populaAtualContaBanc(int conta)
						private static void populaAtualRecebimento(ArrayList xDados)
						private static ArrayList populaArrAtualRecebimento()
						public boolean editar(ArrayList dados) - Usado aqui tambem
						
		public boolean carregar(ArrayList dados)	
		public void teste(ArrayList dados) 	
*/

public class BusiRuleRecebimentos implements BusiRuleSelec {
	static Bconta atualContaBanc;		// Objeto que representa um registro da tabela bconta
	static DaoBconta contasMovimentoBanc; 
	static ObservableList<Bconta> historicoBconta;     // Recebe o ultimo registro lido da tabela bconta selecionada;
	static Recebimentos atualRecebimento;
	static DaoRecebimentos recebimentos;
	
	public boolean validar(ArrayList dados) {
		boolean valida = true;
		
		// Valor: 
				// (16) = valor contratual - (5): valor em lancamento
		
		if((Double)dados.get(5) < (Double)dados.get(16)) {
			// valida = false;
							// Somente alerta
			MessageBox.show("Valor previsto menor que valor contrato" ,"ALERTA");
		}
		
		if((Double)dados.get(5) > ((Double)dados.get(16) * 1.5)){
			// valida = false;
							// Somente alerta
			MessageBox.show("Valor previsto maior que 150% do valor contratual" ,"ALERTA");
		}
		
		if((Double)dados.get(5) < 0.){
			 valida = false;
							
			MessageBox.show("Valor previsto nao pode ser negativo" ,"ERRO");
		}
		
		// (26) Numero de meses a agendar
		if((Integer)dados.get(26) <1) {
			valida = false;
			MessageBox.show("Numero de meses nao pode ser menor que 1", "MESES");
		}
		
		// (26): Numero de meses a agendar
		if((Integer)dados.get(26) > 12) {
			valida = false;
			MessageBox.show("Numero de meses nao pode ser maior que 12", "MESES");
		}
		
		// (3): data da previsao de pagamento
		if(((LocalDate)dados.get(3)).isBefore(LocalDate.now())) {
			// valida = false;
			MessageBox.show("ATENCAO:Previsao de recebimento anterior a Hoje", "DATA");
		}
		
		// (10): Imp renda     (5): Valor da previsao
		if((Double)dados.get(10) > 0.25 * (Double)dados.get(5)){
			valida = false;
			MessageBox.show("Imposto de renda maximo 25%", "IMP RENDA");
		}	
		
		// (11): Comissao       (5): valor previsto
		if((Double)dados.get(11) > 0.15 * (Double)dados.get(5)) {
			// valida = false;
							// Somente alerta
			MessageBox.show("Comissao muito elevada" ,"ALERTA");
		}
		
		// (12): Outras despesas        (5): valor previsto
		if((Double)dados.get(12) > 0.15 * (Double)dados.get(5)) {
			// valida = false;
							// Somente alerta
			MessageBox.show("Valor de outras Desp muito elevada" ,"ALERTA");
		}
		
		// mesesAprogramar: (26)
		if((Integer)dados.get(26) == 1) {
			if(((LocalDate)dados.get(3)).isBefore((LocalDate)dados.get(17))) {
				valida = false;
				MessageBox.show("Contrato ainda nao em vigor", "DATAS");
			}
			
			if(((LocalDate)dados.get(18)).isBefore((LocalDate)dados.get(3))) {
				valida = false;
				MessageBox.show("Contrato ja expirado", "DATAS");
			}	
		}else{
				// Previsao pagamento: (3)     Numero de meses: (26)
				// tempData: Previsao de pagamento transportada para o mes do ultimo lancamento a ser feio

			LocalDate tempData = (LocalDate)dados.get(3);
			tempData = tempData.plusMonths((Integer)dados.get(26) - 1);
				// Passa o dia da data do ultimo lancamento para o diaVenc previsto no contrato
			tempData = DateUtils.mudaDia(tempData, (Integer)dados.get(24));
				// data de pagamento no contrato : (24)
			if(((LocalDate)dados.get(18)).isBefore(tempData)) {
				// data de termino do contrato : (18)
				valida = false;
				MessageBox.show("Lancamentos futuros ultrapassam validade contrato", "DATAS");		
			}	
		}
		
			// Verifica disponibilidade de moc valido para a data;
		if(GpwFin.existeMoc(LocalDate.now()) == false) {
			valida = false;
			MessageBox.show("Necessario lancar Indices de Inflacao", "INFLACAO");
		}
		return valida;
	}
	
	public boolean lancar(ArrayList dados)	{
		boolean valida = true;
			// Para numero de meses = 1 lancamento é feito com a data entrada
		recebimentos = new DaoRecebimentos();	
		recebimentos.gravar(dados);
	
			// para numero de meses > 1 O lancamento do primeiro mes é feito
			// com a data entrada e os subsequentes com o dia ajustado conf contrato
		int N = (Integer)dados.get(26);  // numero de meses a lancar
				// passa a data de pagamento para o dia de vencimento(conf.contrato)
		LocalDate tempData = (LocalDate)dados.get(3);
		tempData = DateUtils.mudaDia(tempData, (Integer)dados.get(24));
	
				// Se numero de meses > 1, faz um lancamento para cada mes
		for(int i=1; i < N; i++) {
			tempData = tempData.plusMonths(1);
			dados.set(3, tempData);
			recebimentos.gravar(dados);
		}
		return true;
	}
	
	public boolean editar(ArrayList dados) {
		boolean valida = true;
		recebimentos = new DaoRecebimentos();
		recebimentos.editar(dados);		
		return valida;
	}
	
	public boolean confirmar(ArrayList dados) {
			// O objeto atualRecebimento é populado com as informacacoes do arrayList dados para que sejam
			// atualizados os campos especificos da confirmacao de pagamento e posteriormente é retransformado
			// em arrayList para ser fornecido como parametro para o metodo editar de DaoRecebimentos
		
		atualRecebimento = new Recebimentos();
		populaAtualRecebimento(dados);			// atualiza campos especificos do atualRecebimento			
		atualRecebimento.setRecEstado("B");  // especifica "pago"
		atualRecebimento.setDataRecebido(LocalDate.now());   // Lancamento na data de hoje
		atualRecebimento.setRecebidoMoc(atualRecebimento.getValRecebido() / GpwFin.mocAplicavel(atualRecebimento.getDataRecebido()));
		editar(populaArrAtualRecebimento());	// Grava atualizacao do registro correspondente ao atualRecebimento
										// passa o atualRecebimento para um ArrayList e grava atraves do metodo editar.	
		
			// Lanca o credito na conta de movimento bancario correspondente
			// atualContaBanc com o ultimo registro existente
		atualContaBanc = new Bconta();
		populaAtualContaBanc(atualRecebimento.getContaVinc());
			// Atualiza autalContaBanc com novos saldos
		ArrayList novoSaldo = novoRegistroConta();
			// Grava um novo registro em BConta
		DaoBconta reg = new DaoBconta();
		reg.gravar(novoSaldo);
		
		boolean valida = true;
		return valida;
	}
	
	public boolean carregar(ArrayList dados) {
		boolean valida = true;
		return valida;
	}

	public boolean liberar(ArrayList dados) {
		boolean valida = false;	
		return valida;
	}
	
	public boolean criar(ArrayList dados) {
		boolean valida = false;	
		return valida;
	}	
	
	private static ArrayList novoRegistroConta() {
			// Os campos do objeto atualContaBanc sao atualizados para representar um novo registro em Bconta
			// correspondente ao credito do recebimento atual;
		atualContaBanc.setDataLanc(atualRecebimento.getDataRecebido());
		atualContaBanc.setDocumento(atualRecebimento.getDocumento());
		atualContaBanc.setValorMov(atualRecebimento.getValRecebido());
		atualContaBanc.setNatOpera("C");
		atualContaBanc.setSaldoAnt(atualContaBanc.getSaldoAtual());
		atualContaBanc.setSaldoAtual(atualContaBanc.getSaldoAtual() + atualContaBanc.getValorMov());
		atualContaBanc.setDescrLanc(atualRecebimento.getNomeContrato());
		atualContaBanc.setUser(537);
		atualContaBanc.setContaOrigDest(atualRecebimento.getAgente());
		atualContaBanc.setNumPagam(atualRecebimento.getCodReceb());
		    // Eh retornado um arrayList com os campos atualizados da atualContaBanc que será utilizado
			// para gravar um registro no Bconta  especifico
		ArrayList arrList = new ArrayList();
		arrList.add(atualContaBanc.getDataLanc());
		arrList.add(atualContaBanc.getDocumento());
		arrList.add(atualContaBanc.getValorMov());
		arrList.add(atualContaBanc.getNatOpera());
		arrList.add(atualContaBanc.getSaldoAnt());
		arrList.add(atualContaBanc.getSaldoAtual());
		arrList.add(atualContaBanc.getDescrLanc());
		arrList.add(atualContaBanc.getUser());
		arrList.add(atualContaBanc.getContaOrigDest());
		arrList.add(atualContaBanc.getNumPagam());
		arrList.add(atualRecebimento.getContaVinc());

		return arrList;
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
	
	private static void populaAtualRecebimento(ArrayList xDados) {

		atualRecebimento.setCodReceb((Integer)xDados.get(0));
		atualRecebimento.setRecEstado((String)xDados.get(1));
		atualRecebimento.setDataLanc((LocalDate)xDados.get(2));	
		atualRecebimento.setPrevPagam((LocalDate)xDados.get(3));
		atualRecebimento.setDataRecebido((LocalDate)xDados.get(4));	
	
		atualRecebimento.setValorPrev((Double)xDados.get(5));
		atualRecebimento.setValRecebido((Double)xDados.get(6));
		atualRecebimento.setCodPagador((Integer)xDados.get(7));
		atualRecebimento.setDocumento((String)xDados.get(8));
		atualRecebimento.setRecebidoMoc((Double)xDados.get(9));
	
		atualRecebimento.setImpRenda((Double)xDados.get(10));
		atualRecebimento.setComisRec((Double)xDados.get(11));
		atualRecebimento.setOutrasDesp((Double)xDados.get(12));	
		
		atualRecebimento.setCodPagador((Integer)xDados.get(13));
		atualRecebimento.setEstPagador((String)xDados.get(14));
		atualRecebimento.setRecDescr((String)xDados.get(15));
		atualRecebimento.setValContrato((Double)xDados.get(16));
		atualRecebimento.setContratoInic((LocalDate)xDados.get(17));

		atualRecebimento.setContratoFim((LocalDate)xDados.get(18));
		atualRecebimento.setNomeContrato((String)xDados.get(19));
		atualRecebimento.setContaVinc((int)xDados.get(20));
		atualRecebimento.setCentroReceb((int)xDados.get(21));
		atualRecebimento.setSubCentroRec((int)xDados.get(22));
	
		atualRecebimento.setDataLanc((LocalDate)xDados.get(23));
		atualRecebimento.setDiaVenc((int)xDados.get(24));
		atualRecebimento.setAgente((String)xDados.get(25));
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
		// Campos referentes ao pagador atual
		arrList.add(atualRecebimento.getCodPagador());		//13
		arrList.add(atualRecebimento.getEstPagador());		//14
		arrList.add(atualRecebimento.getRecDescr());			//15
		arrList.add(atualRecebimento.getValContrato());		//16
		arrList.add(atualRecebimento.getContratoInic());		//17
		arrList.add(atualRecebimento.getContratoFim());		//18
		arrList.add(atualRecebimento.getNomeContrato());		//19
		arrList.add(atualRecebimento.getContaVinc());		//20
		arrList.add(atualRecebimento.getCentroReceb());		//21
		arrList.add(atualRecebimento.getSubCentroRec());		//22
		arrList.add(atualRecebimento.getDataLanc());		//23
		arrList.add(atualRecebimento.getDiaVenc());			//24
		arrList.add(atualRecebimento.getAgente());			//25
		// ---------------------------------------
		arrList.add(1);											//26
					// Para manter compatibilidade com metodo validar
		return arrList;
	}	
}


/*
Business rules - validaçao de dados
x	1 - O numero de meses a programar nao pode ser <1 ou > 12
x	2 - O agendamento inicial nao pode ser anterior a hoje
x	3 - O valor maximo do imposto de renda deve ser 25% do valor programado
x	4 - O valor maximo da comissao deve ser 15% do valor programado
x	5 - Outras despesas nao pode exceder 15% do valor programado
	6 - A data de pagagento dos recebimentos, mesmo os programados no numero
		de meses indicado, dever ser uma data valida perante o contrato
X	7 - Para a previsao de um recebimento unico sera considerada a dada entrada,
		que deve ser uma data valida perante o contrato.
	8 - Em caso de programacao para multiplos meses os lancamentos ocorrerao,
		para cada mes, em dia especificado no contrato.

*/