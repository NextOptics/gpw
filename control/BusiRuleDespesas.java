package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;
import java.util.Date;

import gpw.dao.DaoDespesas;

/*
		public boolean validar(ArrayList dadosSolicitacao)
		public boolean lancar(ArrayList dadosSolicitacao)
		public boolean editar(ArrayList dadosSolicitacao)
		public boolean confirmar(ArrayList dadosSolicitacao)
		public boolean carregar(ArrayList dadosSolicitacao)
*/

public class BusiRuleDespesas implements BusiRuleSelec {
	public boolean validar(ArrayList dadosSolicitacao) {
		boolean valida = true;
		if(dadosSolicitacao.size() == 17) {	// 16 campos de Dsolicit + mesesAprogramar
				// ValorPrevisto
			if((Double)dadosSolicitacao.get(3) < 0) {
				MessageBox.show("Valor previsto nao pode ser negativo", "VALOR");
				valida = false;
			}else {
				if((Double)dadosSolicitacao.get(3) < 0.1) {
					MessageBox.show("Valor nao pode ser zero", "VALOR");
					valida = false;
				}else {
					if((Double)dadosSolicitacao.get(3) > 999) 
						MessageBox.show("Cuidado: VALOR ELEVADO", "VALOR");
				}
			}
				//  PrevPagam
			LocalDate hoje = (LocalDate)dadosSolicitacao.get(0);
			LocalDate prev = (LocalDate)dadosSolicitacao.get(4);
			if(prev.isBefore(hoje) == true ) {
				MessageBox.show("Data pagamento nao pode ser anterior a hoje ", "DATA");
				valida = false;
			}

				//	Numero de parcelas			
			if((Integer)dadosSolicitacao.get(8) < 1) {
				MessageBox.show("Quantidade de parcelas nao pode ser menor que 1", "PARCELAS");
				valida = false;
			}else {
				if((Integer)dadosSolicitacao.get(8) > 12) {
					MessageBox.show("Quantidade de parcelas nao pode ser maior que 12", "PARCELAS");
					valida = false;
				}		
			}
				//	Vezes a programar			
			if((Integer)dadosSolicitacao.get(16) > 12) {
					MessageBox.show("Possivel programar no maximo 12 meses", "VEZES A PROGRAMAR");
					valida = false;
			}
			    // Projeto 
				// Escolhido de uma lista entao so pode ser escolhido projeto valido
		}else
			valida = false;
		
		return valida;
	}
	
	public boolean lancar(ArrayList dadosSolicitacao) {
		boolean valida = true;
		int vNumero = (Integer)dadosSolicitacao.get(16);       // meses a programar
		LocalDate vData = (LocalDate)dadosSolicitacao.get(4);  // prevPagam
 
		DaoDespesas registroDesp = new DaoDespesas();
		for(int i=0; i< vNumero; i++) {   // Grava tantas vezes quanto forem os meses
			dadosSolicitacao.set(4, vData);		  // a programar
			registroDesp.gravar(dadosSolicitacao);
			vData = vData.plusMonths(1);
		}
		return valida;
	}
	
	public boolean editar(ArrayList dadosSolicitacao) {
		boolean valida = true;
		DaoDespesas registroDesp = new DaoDespesas();
		registroDesp.editar(dadosSolicitacao);	
		return valida;
	}
	
	public boolean confirmar(ArrayList dadosSolicitacao) {
		boolean valida = true;
		return valida;
	}
	
	public boolean carregar(ArrayList dadosSolicitacao) {
		boolean valida = true;
		return valida;
	}
}