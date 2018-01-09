package gpw.control;

import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class BusinessRules {
	BusiRuleSelec ruleSelecionada;
	
/*
public boolean performConsistencia(ArrayList x)
public boolean performLancamento(ArrayList y)
public boolean performEdicao(ArrayList z)
public boolean performConfirmacao(ArrayList w)
public boolean performCarregamento(ArrayList v)

*/
	
	public BusinessRules() {	
	}

	public boolean performConsistencia(ArrayList x) {
		boolean resultado = false;
		resultado = ruleSelecionada.validar(x);
		return resultado;
	}

	public boolean performLancamento(ArrayList y) {
		boolean resultado = false;
		resultado = ruleSelecionada.lancar(y);
		return resultado;
	}
	
	public boolean performEdicao(ArrayList z) {
		boolean resultado = false;
		resultado = ruleSelecionada.editar(z);
		return resultado;
	}
	
	public boolean performConfirmacao(ArrayList w) {
		boolean resultado = false;
		resultado = ruleSelecionada.confirmar(w);
		return resultado;
	}
	
	public boolean performCarregamento(ArrayList v) {
		boolean resultado = false;
		resultado = ruleSelecionada.carregar(v);
		return resultado;
	}	
}