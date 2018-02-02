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
		
		contasBancarias = new DaoContasBanc();	
		contasBancAtivas = contasBancarias.carregarContasBanc(1);

		int numObj = contasBancAtivas.size();
		System.out.println(); 
		for(int i = 0; i < numObj; i++){
			if(contasBancAtivas.get(i).getContaNumero().equals((String)dados.get(3))){
				valida = false;
				MessageBox.show("Ja existe conta bancaria com este numero", "CONTAS");
				break;
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
//		registro.editar(dados);
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
}