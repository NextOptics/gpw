package gpw.control;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

/*
		public static void printAutorzDesp(ArrayList dadosAutorzDesp) {

*/

public class TestUtils {
	
	public static void printDesp(ArrayList dadosSolicitacao) {
		System.out.println();
		System.out.println("------------ Solicitacao - DaoDespesas");
		System.out.println("********* : dataSolicit : " + dadosSolicitacao.get(0));
		System.out.println("********* : item :        " + dadosSolicitacao.get(1));
		System.out.println("********* : aplicacao:    " + dadosSolicitacao.get(2));
		System.out.println("********* : valorPrev:    " + dadosSolicitacao.get(3));
		System.out.println("********* : prevPagam:    " + dadosSolicitacao.get(4));
		System.out.println("********* : quantidade:   " + dadosSolicitacao.get(5));
		System.out.println("********* : unidade:      " + dadosSolicitacao.get(6));
		System.out.println("********* : situacao:     " + dadosSolicitacao.get(7));
		
		System.out.println("********* : numParcelas:  " + dadosSolicitacao.get(8));
		System.out.println("********* : parcPagas:    " + dadosSolicitacao.get(9));
		
		System.out.println("********* : docum:        " + dadosSolicitacao.get(10));
		System.out.println("********* : numSolicit:   " + dadosSolicitacao.get(11));
		System.out.println("********* : classe:       " + dadosSolicitacao.get(12));
		System.out.println("********* : grupo:        " + dadosSolicitacao.get(13));
		System.out.println("********* : ultAltera:    " + dadosSolicitacao.get(14));	
		System.out.println("********* : projeto:      " + dadosSolicitacao.get(15));
	}
	
	
	public static void printAutorzDesp(ArrayList dadosAutorzDesp) {
		System.out.println();
		System.out.println("---- Dautorzizacao");
		System.out.println("************  NumAutorz:	" + dadosAutorzDesp.get(0));
		System.out.println("************  NumSolicit:	" + dadosAutorzDesp.get(1));
		System.out.println("************  gValorAutorz:	" + dadosAutorzDesp.get(2));
		System.out.println("************  VencAutorz:	" + dadosAutorzDesp.get(3));
		System.out.println("************  DatAutorz:	" + dadosAutorzDesp.get(4));
		System.out.println("************  Situacao:		" + dadosAutorzDesp.get(5));
		System.out.println("************  TipoConta:	" + dadosAutorzDesp.get(6));
		System.out.println("************  Conta:		" + dadosAutorzDesp.get(7));
		System.out.println("************  NumdaParcela:	" + dadosAutorzDesp.get(8));
		System.out.println("Dautorizacao extends Dsolicit" );			
		System.out.println("************  DataSolicit:	" + dadosAutorzDesp.get(9));
		System.out.println("************  Item:			" + dadosAutorzDesp.get(10));
		System.out.println("************  Aplicacao:	" + dadosAutorzDesp.get(11));
		System.out.println("************  ValorPrev:	" + dadosAutorzDesp.get(12));
		System.out.println("************  PrevPagam:	" + dadosAutorzDesp.get(13));
		System.out.println("************  Quantidade:	" + dadosAutorzDesp.get(14));
		System.out.println("************  Unidade:		" + dadosAutorzDesp.get(15));
		System.out.println("************  Situacao:		" + dadosAutorzDesp.get(16));
		System.out.println("************  NumParcelas:	" + dadosAutorzDesp.get(17));
		System.out.println("************  ParcPagas:	" + dadosAutorzDesp.get(18));
		System.out.println("************  Docum:		" + dadosAutorzDesp.get(19));
		System.out.println("************  NumSolicit:	" + dadosAutorzDesp.get(20));
		System.out.println("************  Classe:		" + dadosAutorzDesp.get(21));
		System.out.println("************  Grupo:		" + dadosAutorzDesp.get(22));
		System.out.println("************  UltAltera:	" + dadosAutorzDesp.get(23));
		System.out.println("************  Projeto:		" + dadosAutorzDesp.get(24));
	}
	
	public static void printPagamAutorzDesp(ArrayList dadosPagamAutorzDesp) {
		System.out.println();
		System.out.println("---------- Dpagamen");
		System.out.println("*******  NumPagam:     " + dadosPagamAutorzDesp.get(0));
		System.out.println("*******  NumAutors:    " + dadosPagamAutorzDesp.get(1));
		System.out.println("*******  ValorPago:    " + dadosPagamAutorzDesp.get(2));
		System.out.println("*******  DataPagam:    " + dadosPagamAutorzDesp.get(3));
		System.out.println("*******  Situacao:     " + dadosPagamAutorzDesp.get(4));
		System.out.println("*******  ValorPagoMoc: " + dadosPagamAutorzDesp.get(5));
	
		System.out.println();
		System.out.println("----------- Dautorizacao");
		System.out.println("*******  NumAutorz:    " + dadosPagamAutorzDesp.get(6));
		System.out.println("*******  NumSolicit:   " + dadosPagamAutorzDesp.get(7));
		System.out.println("*******  ValorAutorz:  " + dadosPagamAutorzDesp.get(8));
		System.out.println("*******  VencAutorz:   " + dadosPagamAutorzDesp.get(9));
		System.out.println("*******  DatAutorz:    " + dadosPagamAutorzDesp.get(10));
		System.out.println("*******  Situacao:     " + dadosPagamAutorzDesp.get(11));
		System.out.println("*******  TipoConta:    " + dadosPagamAutorzDesp.get(12));
		System.out.println("*******  Conta:        " + dadosPagamAutorzDesp.get(13));
		System.out.println("*******  NumDaParcela: " + dadosPagamAutorzDesp.get(14));

		System.out.println();
		System.out.println("----------- Dsolicitacao");
		System.out.println("*******  DataSolicit:  " + dadosPagamAutorzDesp.get(15));
		System.out.println("*******  Item:         " + dadosPagamAutorzDesp.get(16));
		System.out.println("*******  Aplicacao:    " + dadosPagamAutorzDesp.get(17));
		System.out.println("*******  ValorPrev:    " + dadosPagamAutorzDesp.get(18));
		System.out.println("*******  PrevPagam:    " + dadosPagamAutorzDesp.get(19));
		System.out.println("*******  Quantidade:   " + dadosPagamAutorzDesp.get(20));
		System.out.println("*******  Unidade:      " + dadosPagamAutorzDesp.get(21));
		System.out.println("*******  Situacao:     " + dadosPagamAutorzDesp.get(22));
		System.out.println("*******  NumParcelas:  " + dadosPagamAutorzDesp.get(23));
		System.out.println("*******  ParcPagas:    " + dadosPagamAutorzDesp.get(24));
		System.out.println("*******  Docum:        " + dadosPagamAutorzDesp.get(25));
		System.out.println("*******  NumSolicit:   " + dadosPagamAutorzDesp.get(26));
		System.out.println("*******  Classe:       " + dadosPagamAutorzDesp.get(27));
		System.out.println("*******  Grupo:        " + dadosPagamAutorzDesp.get(28));
		System.out.println("*******  UltAltera:    " + dadosPagamAutorzDesp.get(29)); 	
		System.out.println("*******  Projeto:      " + dadosPagamAutorzDesp.get(30));
	}
	
	public static void printDcontaMov (ArrayList dados){
		System.out.println("********** DataLanc :     " + dados.get(0));	// 0 	DataLanc
		System.out.println("********** Docum :        " + dados.get(1));	// 1 	Docum
		System.out.println("********** Valor :		  " + dados.get(2));	// 2	ValorPago (valor da parcela)
		System.out.println("********** NatOpera:      " + dados.get(3));	// 3 	NatOpera
		System.out.println("********** Usuario:       " + dados.get(4));	// 4 	Usuario
		System.out.println("********** Lancanum:");
		System.out.println("********** Estado :       " + dados.get(5));	// 5 	Estado
		System.out.println("********** ValorMoc:      " + dados.get(6)); 	// c	alculado quando do faturamento do cartão		// 6 valorMoc
		System.out.println("********** Data Parcela   " + dados.get(7));
		System.out.println("********** NumPagam :     " + dados.get(8));	// 8 	NumPagam
		System.out.println("********** Campo Padding  ");					// 9	Campo Padding
		System.out.println("********** Conta :        " + dados.get(10));	// 10 	Conta

	}


	/*


	private static void printPagadores() {
		System.out.println();
		System.out.println("+++++++ TESTE obj. atualPagador");
		System.out.println("**** codPaPagador : "  + atualPagador.getCodPagador());
		System.out.println("**** estPagador   : "  +atualPagador.getEstPagador());
		System.out.println("**** recDescr     : "  +atualPagador.getRecDescr());
		System.out.println("**** valContrato  : "  +atualPagador.getValContrato());
		System.out.println("**** contratoInic : "  +atualPagador.getContratoInic());	
		System.out.println("**** ontratoFim   : "  +atualPagador.getContratoFim());
		System.out.println("**** nomeContrato : "  +atualPagador.getNomeContrato());
		System.out.println("**** contaVinc    : "  +atualPagador.getContaVinc());
		System.out.println("**** centroReceb  : "  +atualPagador.getCentroReceb());
		System.out.println("**** subCentroRec : "  +atualPagador.getSubCentroRec());
		System.out.println("**** dataLanc     : "  +atualPagador.getDataLanc());
		System.out.println("**** diaVenc      : "  +atualPagador.getDiaVenc());
		System.out.println("**** agente       : "  +atualPagador.getAgente());	
	}
*/

	/*
	private static void teste() {
		System.out.println();
		System.out.println("+++++++ TESTE obj. atualRecebimento");
		System.out.println("**** codReceb     : "  +atualRecebimento.getCodReceb());
		System.out.println("**** recEstado    : "  +atualRecebimento.getRecEstado());
		System.out.println("**** dataLanc     : "  +atualRecebimento.getDataLanc());
		System.out.println("**** prevPagam    : "  +atualRecebimento.getPrevPagam());
		System.out.println("**** dataRecebido : "  +atualRecebimento.getDataRecebido());
		
		System.out.println("**** valorPrev    : "  +atualRecebimento.getValorPrev());
		System.out.println("**** valRecebido  : "  +atualRecebimento.getValRecebido());
		System.out.println("**** codPagador   : "  +atualRecebimento.getCodPagador());
		System.out.println("**** documento    : "  +atualRecebimento.getDocumento());
		System.out.println("**** recebidoMoc  : "  +atualRecebimento.getRecebidoMoc());
		
		System.out.println("**** impRenda     : "  +atualRecebimento.getImpRenda());
		System.out.println("**** comisRec     : "  +atualRecebimento.getComisRec());
		System.out.println("**** outrasDesp   : "  +atualRecebimento.getOutrasDesp());	
	}
*/

/*
		public void teste(ArrayList dados) {
		System.out.println("****** CodReceb:      " + (Integer)dados.get(0));		// 0
		System.out.println("****** RecEstado:     " + (String)dados.get(1));		// 1
		System.out.println("****** DataLanc:      " + (LocalDate)dados.get(2));		// 2
		System.out.println("****** PrevPagam:     " + (LocalDate)dados.get(3));		// 3
		System.out.println("****** DataRecebido:  " + (LocalDate)dados.get(4));		// 4
	
		System.out.println("****** ValorPrev:     " + (Double)dados.get(5));		// 5
		System.out.println("****** ValRecebido:   " + (Double)dados.get(6));		// 6
		System.out.println("****** CodPagador:    " + (Integer)dados.get(7));		// 7
		System.out.println("****** Documento:     " + (String)dados.get(8));		// 8
		System.out.println("****** RecebidoMoc:   " + (Double)dados.get(9));		// 9
	
		System.out.println("****** ImpRenda:      " + (Double)dados.get(10));		//10
		System.out.println("****** ComisRec:      " + (Double)dados.get(11));		//11
		System.out.println("****** OutrasDesp:    " + (Double)dados.get(12));		//12
		//----------------------------------------
		// Campos referentes ao pagador atual
		System.out.println("****** CodPagador:    " + (Integer)dados.get(13));		//13
		System.out.println("****** EstPagador:    " + (String)dados.get(14));		//14
		System.out.println("****** RecDescr:      " + (String)dados.get(15));		//15
		System.out.println("****** ValContrato:   " + (Double)dados.get(16));		//16
		System.out.println("****** ContratoInic:  " + (LocalDate)dados.get(17));	//17
		System.out.println("****** ContratoFim:   " + (LocalDate)dados.get(18));	//18
		System.out.println("****** NomeContrato:  "+ (String)dados.get(19));		//19
		System.out.println("****** ContaVinc:     " + (Integer)dados.get(20));		//20
		System.out.println("****** CentroReceb:   " + (Integer)dados.get(21));		//21
		System.out.println("****** SubCentroRec:  " + (Integer)dados.get(22));		//22
		System.out.println("****** DataLanc:      " + (LocalDate)dados.get(23));	//23
		System.out.println("****** DiaVenc:       " + (Integer)dados.get(24));		//24
		System.out.println("****** Agente:        " + (String)dados.get(25));		//25
		System.out.println("****** Compatiblidade:  " + (Integer)dados.get(26));	//26
		// ---------------------------------------				
	}	

*/

	public static void printContaBanc(ArrayList dadosContaBanc) {
		System.out.println("********* : banco :       " + dadosContaBanc.get(0));
		System.out.println("********* : codBanco :    " + dadosContaBanc.get(1));
		System.out.println("********* : agencia :     " + dadosContaBanc.get(2)); 
		System.out.println("********* : conta :       " + dadosContaBanc.get(3));
		System.out.println("********* : tipoAgencia : " + dadosContaBanc.get(4));

		System.out.println("********* : titular :     " + dadosContaBanc.get(5));
		System.out.println("********* : gerente :     " + dadosContaBanc.get(6));
		System.out.println("********* : agenCidade :  " + dadosContaBanc.get(7));
		System.out.println("********* : agenBairro :  " + dadosContaBanc.get(8));
		System.out.println("********* : agenRua :     " + dadosContaBanc.get(9));

		System.out.println("********* : agenCep :     " + dadosContaBanc.get(10));
		System.out.println("********* : agenTel :     " + dadosContaBanc.get(11));
		System.out.println("********* : celGerente :  " + dadosContaBanc.get(12));
		System.out.println("********* : contaValida : " + dadosContaBanc.get(13));
		System.out.println("********* : codConta :    " + dadosContaBanc.get(14));
		System.out.println("********* : contaTipo :   " + dadosContaBanc.get(15));
	}

	public static void printBconta(ArrayList dadosBconta) {

		System.out.println("********* : datalanc :      " + dadosBconta.get(0));	
		System.out.println("********* : docum :         " + dadosBconta.get(1));
		System.out.println("********* : valor :         " + dadosBconta.get(2));	
		System.out.println("********* : natopera :      " + dadosBconta.get(3));
		System.out.println("********* : saldoant :      " + dadosBconta.get(4));

		System.out.println("********* : saldoatual :    " + dadosBconta.get(5));
		System.out.println("********* : histórico :     " + dadosBconta.get(6));
		System.out.println("********* : usuario :       " + dadosBconta.get(7));
		System.out.println("********* : lancanum :      " + dadosBconta.get(8));
		System.out.println("********* : contaorigdest : " + dadosBconta.get(9));

		System.out.println("********* : numpagam :      " + dadosBconta.get(10));
		
	}	
}