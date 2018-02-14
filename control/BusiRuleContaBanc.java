package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;
import javafx.collections.*;
import java.util.List;

import gpw.model.ContasBanc;
import gpw.dao.DaoContasBanc;
import gpw.control.*;

/*
		public boolean validar(ArrayList dados)
		public boolean lancar(ArrayList dados)
		public boolean editar(ArrayList dados)
		public boolean confirmar(ArrayList dados)
		public boolean carregar(ArrayList dados)
*/

public class BusiRuleContaBanc implements BusiRuleSelec {
	
	public boolean validar(ArrayList dados) {	
		boolean valida = true;
		ObservableList<ContasBanc> contasBancAtivas;
		DaoContasBanc contasBancarias;
		int codContaExistente = 0;
		
		contasBancarias = new DaoContasBanc();	
		contasBancAtivas = contasBancarias.carregarContasBanc(1);

													// Verifica se o numero de contra entrada corresponte a uma conta ja existente
		int numObjetos = contasBancAtivas.size();  	// Verifica quantas contas estao cadastradas como validas
		for(int i = 0; i < numObjetos; i++){
			if(contasBancAtivas.get(i).getContaNumero().equals((String)dados.get(3))){
				codContaExistente = contasBancAtivas.get(i).getCodConta();
				break;
			}else {
				codContaExistente = 0;
			}
		}
		
		if((Integer)dados.get(14) > 0 ) {      // codConta > 0 ---> Edicao de conta
			if(codContaExistente > 0 ) {
				valida = true;		
			}else {
				MessageBox.show("Nao pode ser alterado o numero da conta em edicao", "CONTAS");
				valida = false;
			}

		}else { 								// codConta = 0  ----->  Cadastramento de nova conta		
			if(codContaExistente > 0 ) {
				MessageBox.show("Ja existe conta bancaria com este numero", "CONTAS");
				valida = false;
			}	
		}	
		return valida;
	}

	
	public boolean lancar(ArrayList dados) {
	
		boolean valida = true;
		DaoContasBanc registro = new DaoContasBanc();
		registro.gravar(dados);	
		return valida;
	}
	
	public boolean editar(ArrayList dados) {

		boolean valida = true;
		DaoContasBanc registro = new DaoContasBanc();
		registro.editar(dados);
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

	public boolean liberar(ArrayList dados) {
		boolean valida = false;	
		return valida;
	}
	
	public boolean criar(ArrayList dados) {
		boolean valida = false;


		return valida;
	}	
}