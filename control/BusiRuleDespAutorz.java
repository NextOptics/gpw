package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;

import gpw.dao.DaoDespAutorz;
import gpw.dao.DaoDespesas;

/*
	public boolean validar(ArrayList  dadosAutorizacao)
	public boolean lancar(ArrayList  dadosAutorizacao)
	private ArrayList populaArrSolicitacao() {
	public boolean editar(ArrayList  dadosAutorizacao)
	public boolean confirmar(ArrayList  dadosAutorizacao)
	public boolean carregar(ArrayList  dadosAutorizacao)
*/

public class BusiRuleDespAutorz implements BusiRuleSelec {
	public boolean validar(ArrayList  dadosAutorizacao){
		boolean valida = true;
		if(((LocalDate) dadosAutorizacao.get(3)).isBefore(LocalDate.now())) {
			valida = false;
			MessageBox.show("Vencimento nao pode ser anterior a Hoje", "VENCIMENTO");
		}
		
		if((Double) dadosAutorizacao.get(2) < 1.0) {
			valida = false;
			MessageBox.show("Valor nao aceitavel ", "VALOR");
		}
		
		if((Double) dadosAutorizacao.get(2) > 4999.0) {
			MessageBox.show("Verfique. Valor elevado", "ATENCAO");
		}
		
		if((Integer) dadosAutorizacao.get(17) < 1) {
			valida = false;
			MessageBox.show("Numero de parcelas incorreto", "PARCELAS");
		}
		
		if((Integer) dadosAutorizacao.get(8) > 12) {
			valida = false;
			MessageBox.show("Nao possivel mais que 12 parcelas", "PARCELAS");
		}
		return valida;
	}
	
	
	
	
	public boolean lancar(ArrayList  dadosAutorizacao) {
		
		boolean valida = true;
		DaoDespAutorz registroAutorz = new DaoDespAutorz();
		DaoDespesas registroDesp = new DaoDespesas();
		double vValor = 0.;												// Valor autorizado da parcela ou total
		LocalDate dataTemp;
		dadosAutorizacao.set(12, (Double) dadosAutorizacao.get(2));		// Valorprev é atualizado com valor autorizado
														
		if(((String) dadosAutorizacao.get(6)).equals("B")) {			// Debito em conta bancaria
			dadosAutorizacao.set(16, "C"); 
			if(((Integer) dadosAutorizacao.get(17)) > 0) {
				vValor = (Double) dadosAutorizacao.get(2) / (Integer) dadosAutorizacao.get(17); 	// Valor da parcela =  valorTotal / numeroDeParcelas	
			}else
				vValor = 0.;
			dadosAutorizacao.set(2, vValor);
								
			for(int i=1; i<= (Integer)dadosAutorizacao.get(17); i++) {		// Lanca uma autorizacao para cada parcela
				dadosAutorizacao.set(8, i);
				dataTemp = (LocalDate)dadosAutorizacao.get(3);
				registroAutorz.gravar(dadosAutorizacao);
				dadosAutorizacao.set(3, DateUtils.mesSeguinte(dataTemp));	
			}	
		}
																
		if(((String) dadosAutorizacao.get(6)).equals("D")){		// No pagamento com cartao o valor eh lancado pelo total		
			dadosAutorizacao.set(16, "C");						// As parcelas serao divididas nas faturas
			dadosAutorizacao.set(8, (Integer)dadosAutorizacao.get(17));
			registroAutorz.gravar(dadosAutorizacao);	
		}
		
		registroDesp.editar(populaArrSolicitacao(dadosAutorizacao));  // Atualiza a solicitacao de despesa		
		return valida;	
	}
	
	private ArrayList populaArrSolicitacao(ArrayList dados) {
								// coloca dados na sequencia apropriada a DaoDespesas
		ArrayList dadosDesp = new ArrayList();
		dadosDesp.add((LocalDate) dados.get(9));		//	0	getDataSolicit()	
		dadosDesp.add((String) dados.get(10));			//	1	getItem()
		dadosDesp.add((String) dados.get(11));			//	2	getAplicacao()
		dadosDesp.add((Double) dados.get(12));			//	3	getValorPrev()
		dadosDesp.add((LocalDate) dados.get(13));		//	4	getPrevPagam()
		dadosDesp.add((Double) dados.get(14));			//	5	getQuantidade()
		dadosDesp.add((Integer) dados.get(15));			//	6	getUnidade()	
		dadosDesp.add((String) dados.get(16));			//	7	getSituacao()
		dadosDesp.add((Integer) dados.get(17));			//	8	getNumParcelas()
		dadosDesp.add((Integer) dados.get(18));			//	9	getParcPagas()
		dadosDesp.add((String) dados.get(19));			//	19	getDocum()	
		dadosDesp.add((Integer) dados.get(20));			//	11	getNumSolicit()	
		dadosDesp.add((Integer) dados.get(21));			//	12	getClasse()
		dadosDesp.add((Integer) dados.get(22));			//	13	getGrupo()
		dadosDesp.add((LocalDate) dados.get(23));		//	14	getUltAltera()	
		dadosDesp.add((Integer) dados.get(24));			//	15	getProjeto()
		return dadosDesp;
	}
	
	public boolean editar(ArrayList  dadosAutorizacao) {
		boolean valida = true;
		
		return valida;
	}
	
	public boolean confirmar(ArrayList  dadosAutorizacao) {
		boolean valida = true;
		
		return valida;
	}
	
	public boolean carregar(ArrayList  dadosAutorizacao) {
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
}

/*
		 .getNumAutorz()		 0
		 .getNumSolicit()		 1
		 .getValorAutorz()		 2
		 .getVencAutorz()		 3
		 .getDatAutorz()		 4
		 .getSituacao()			 5
		 .getTipoConta()		 6
		 .getConta()			 7
		 .getNumdaParcela()		 8
											 Dautorizacao extends Dsolicit			
		 .getDataSolicit()		 9
		 .getItem()				10
		 .getAplicacao()		11
		 .getValorPrev()		12
		 .getPrevPagam()		13
		 .getQuantidade()		14
		 .getUnidade()			15
		 .getSituacao()			16
		 .getNumParcelas()		17
		 .getParcPagas()		18
		 .getDocum()			19
		 .getNumSolicit()		20
		 .getClasse()			21
		 .getGrupo()			22
		 .getUltAltera()		23	
		 .getProjeto()			24	

		 // SITUAÇÃO: A– Lançada   B– Paga parte das parcelas  C– Pagas todas as parcelas  X- Cancelada
*/




/*
	Validar
	1- Data de vencimento nao pode ser anterior a hoje;
	2- Valor nao pode ser menor que 1;
	3- Numero de parcelas nao pode ser menor que 1;
	
	B – AGENDAMENTO
1 – O agendamento  é a operação pela qual um meio de pagamento, conta bancário ou 
       cartão de crédito, é associado com uma despesa já prevista. O agendamento NÃO 
       gera débito em conta bancária ou cartão de crédito.

2 – Não é possível agendar uma pagamento cuja despesa não foi prevista mas não é
       necessário fazer  previsão e agendamento para realizar um pagamento. Isto pode ser
       feito diretamente na tela de PAGAMENTO na data em que este foi realizado. NESTE
      CASO SOMENTE SÃO   ADMISSIVEIS PAGAMENTOS EM UMA SÓ 
       PARACELA (A VISTA)

3 -  DAUTORZ contem os dados das despesas que já tem os pagamentos agendados, isto
       é despesas para as quais  se tem uma data e valor definidos com segurança

4 - Quando do agendamento são definidos:
                   - O valor final da despesa ( após este agendamento este valor não pode 
                       ser mais alterado)
                    - A quantidade de parcelas em que o pagamento foi dividido – Após o
                       agendamento esta quantidade não pode ser alterada   ESTES VALORES 
                       SÃO ARMAZENADOS EM DSOLICIT

5 – Se a primeira parcela de um despesa for agendada para pagamento em conta bancária
      todas as demais também deverão ser agendadas para pagamento em conta bancária,
      não necessariamente a mesma  O sistema não aceitará que seja feito de outra maneira.

6 – Se o agendamento for feito para pagamento parcelado em cartão de crédito todas as
      parcelas serão debitadas neste mesmo cartão nas datas corretas.

7 – Uma despesa paga com cartão de débito deverá ser agendada para pagamento na  
      conta bancária correspondente a este cartão.

8 – Despesas parceladas fora de cartão de crédito terão UM agendamento por parcela.

9 – Despesas parceladas no cartão de crédito terão UM ÚNICO agendamento.

10 – No agendamento do pagamento de uma parcela por conta bancária 
        dautorz.numdaparcela terá o numero da parcela agendada.


11 – No agendamento de um pagamento total por conta bancária
        dautorz.numdaparcela terá o valor 1.

12 – No agendamento de um pagamento em parcela única por cartão de crédito
        dautorz.numdaparcela terá o valor 1

13 – No agendamento de um pagamento em múltiplas parcelas por cartão de crédito
         dautorz.numdaparcela terá a quantidade de parcelas e não o número de uma parcela

14 – Embora o débito efetivo de um pagamento ocorra automaticamente na data agendada
      ou manualmente na tela PAGAMENTO, uma despesa total ou uma parcela serão
      consideradas pagas imediatamente após o agendamento.
	  
15 - Despesa parcelada no cartao:
	- A solicitacao de despesa é finalizada
	- A autorizacao de despesa retem os parametros atualizados
	- Ao confirmar o pagamento as parcelas, em seus valores corretos, sao lancadas
	  nas datas respectivas na fatura do cartao de credito.
	  
16 - Despesa parcelada em conta bancaria:
	- A solicitacao de despesa só finalizada quando for autorizada a ultima parcela
	- Ao se fazer a autorizacao de uma parcela é gerada uma autorizacao no valor da
	  parcela . Na solicitacao de despesas a data de vencimento é alterada para a
	  data correspondente à parcela seguinte e é acrescentado 1(um) na quantidade
	  de parcelas pagas

*/