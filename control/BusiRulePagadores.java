package gpw.control;

import java.util.ArrayList;
import gpw.control.BusiRuleSelec;
import java.time.LocalDate;
import gpw.view.MessageBox;

import gpw.dao.DaoPagadores;

/*
		public boolean validar(ArrayList dados)
		public boolean lancar(ArrayList dados)
		public boolean editar(ArrayList dados)
		public boolean confirmar(ArrayList dados)
		public boolean carregar(ArrayList dados)
*/

public class BusiRulePagadores implements BusiRuleSelec {
	
	public boolean validar(ArrayList dados) {	
		boolean valida = true;

		if((Double)dados.get(3) < 0.01) {
				valida = false;
				MessageBox.show("Nao pode ser menor que 0.01", "VALOR");
		}
	
		if(((LocalDate)dados.get(4)).isBefore((LocalDate)dados.get(10))){
			MessageBox.show("ATENCAO: Inicio do contrao anterior a hoje", "DATAS");
			// valida = false;
			// dados.get(10) representa a data em que o registro foi lancado
			// Este comando foi comentado porque este metodo de validadacao é usado tando pelo
			// cadastramento do pagador (valida deveria ser true) como por editar pagador,caso
			// em que valida deve ser false para permitir edicao de contratos com datas mais
			// passadas
		}
		
		if(((LocalDate)dados.get(5)).isBefore((LocalDate)dados.get(10))){
			MessageBox.show("Termino do contrato nao pode ser anterior a hoje", "DATAS");
			valida = false;
			// dados.get(10) representa a data em que o registro foi lancado
		}
		
		if(((LocalDate)dados.get(4)).isAfter((LocalDate)dados.get(5))){
			MessageBox.show("Termino deve ser posterior ao Inicio", "DATAS");
			valida = false;
		}
			
		if(((LocalDate)dados.get(4)).isEqual((LocalDate)dados.get(5))) {
			MessageBox.show("Contrato nao pode iniciar e terminar no mesmo dia", "DATAS");
			valida = false;
		}
		
		if((Double)dados.get(3) > 5999.0)
			MessageBox.show("Valor elevado, verifique com atencao", "VALOR");
		return valida;
	}
	
	public boolean lancar(ArrayList dados) {
		boolean valida = true;
		DaoPagadores registro = new DaoPagadores();
		registro.gravar(dados);
		
		return valida;
	}
	
	public boolean editar(ArrayList dados) {
		boolean valida = true;
		DaoPagadores registro = new DaoPagadores();
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
}

