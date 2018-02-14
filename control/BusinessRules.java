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
		boolean resultado = false;							// Verifica a consistencia de dados entrados
		resultado = ruleSelecionada.validar(x);
		return resultado;
	}

	public boolean performLancamento(ArrayList y) {
		boolean resultado = false;							// Faz o lancamento de registros novos
		resultado = ruleSelecionada.lancar(y);
		return resultado;
	}
	
	public boolean performEdicao(ArrayList z) {
		boolean resultado = false;							// Edita registros existentes
		resultado = ruleSelecionada.editar(z);
		return resultado;
	}
	
	public boolean performConfirmacao(ArrayList w) {
		boolean resultado = false;							// Confirma que lancamentos foram efetuados
		resultado = ruleSelecionada.confirmar(w);
		return resultado;
	}
	
	public boolean performCarregamento(ArrayList v) {
		boolean resultado = false;							// Carrega registros de banco de dados
		resultado = ruleSelecionada.carregar(v);
		return resultado;
	}

	public boolean performLiberacao(ArrayList t) {
		boolean resultado = false;							// Verifica o atendimento de regras de negocio especificas
		resultado = ruleSelecionada.liberar(t);
		return resultado;
	}
	
	public boolean performCriacao(ArrayList t) {
		boolean resultado = false;							// Cria tabela em banco de dados
		resultado = ruleSelecionada.criar(t);
		return resultado;
	}	
}