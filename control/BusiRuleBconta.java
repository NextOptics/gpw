package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;
import javafx.collections.*;
import java.util.List;

import gpw.model.ContasBanc;
import gpw.model.Bconta;
import gpw.dao.DaoContasBanc;
import gpw.dao.DaoBconta;
import gpw.control.*;


public class BusiRuleBconta implements BusiRuleSelec {
	
	public boolean validar(ArrayList dados) {	
		boolean valida = true;
		return valida;
	}

	
	public boolean lancar(ArrayList dados) {
	
		boolean valida = true;
		return valida;
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
	
	public boolean criar(ArrayList dados) {
		boolean valida = true;

		DaoBconta contaSelecionada;
		contaSelecionada = new DaoBconta();
		valida = contaSelecionada.criarBconta((Integer)dados.get(0));
		return valida;
	}	

	public boolean liberar(ArrayList dados) {
		boolean valida = true;
		ObservableList<Bconta> ultimoMovimento;

		DaoBconta contaSelecionada;
		contaSelecionada = new DaoBconta();
		ultimoMovimento = contaSelecionada.carregaSaldoBanc((Integer)dados.get(14));

		if(Math.abs(ultimoMovimento.get(0).getSaldoAtual()) > 0.) {
			valida = false;
		}

		return valida;
	}	
}