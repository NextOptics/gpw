package gpw.model;

import java.time.LocalDate;


public class Dautorizacao extends Dsolicit {
	private int numAutorz;
	private int numSolicit;
	private double valorAutorz;
	private LocalDate vencAutorz;
	private LocalDate datAutorz;
	private String situacao;
	private String tipoConta;
	private int conta;
	private int numdaParcela;
	
	public Dautorizacao() {
		this.numAutorz = 0;
		this.numSolicit = 0;
		this.valorAutorz = 0;
		this.vencAutorz = LocalDate.now();
		this.datAutorz = LocalDate.now();
		this.situacao = "";
		this.tipoConta = "";
		this.conta = 0;
		this.numdaParcela = 0;
	}
	
	public Dautorizacao(int numAutorz,int numSolicit,double valorAutorz,LocalDate vencAutorz,
				LocalDate datAutorz,String situacao,String tipoConta,int conta,int numdaParcela) {
		this.numAutorz = numAutorz;
		this.numSolicit = numSolicit;
		this.valorAutorz = valorAutorz;
		this.vencAutorz = vencAutorz;
		this.datAutorz = datAutorz;
		this.situacao = situacao;
		this.tipoConta = tipoConta;
		this.conta = conta;
		this.numdaParcela = numdaParcela;
	}
	
	public int getNumAutorz() {return this.numAutorz;} 
	public void setNumAutorz(int numAutorz) {this.numAutorz = numAutorz;}
	
	public int getNumSolicit() {return this.numSolicit;} 
	public void setNumSolicit(int numSolicit) {this.numSolicit = numSolicit;}
	
	public double getValorAutorz() {return this.valorAutorz;} 
	public void setValorAutorz(double valorAutorz) {this.valorAutorz = valorAutorz;}
	
	public LocalDate getVencAutorz() {return this.vencAutorz;} 
	public void setVencAutorz(LocalDate vencAutorz) {this.vencAutorz = vencAutorz;}
	
	public LocalDate getDatAutorz() {return this.datAutorz;} 
	public void setDatAutorz(LocalDate datAutorz) {this.datAutorz = datAutorz;}
	
	public String getSituacao() {return this.situacao;} 
	public void setSituacao(String situacao) {this.situacao = situacao;}
	
	public String getTipoConta() {return this.tipoConta;} 
	public void setTipoConta(String tipoConta) {this.tipoConta = tipoConta;}
	
	public int getConta() {return this.conta;} 
	public void setConta(int conta) {this.conta = conta;}
	
	public int getNumdaParcela() {return this.numdaParcela;}
	public void setNumdaParcela(int numdaParcela) {this.numdaParcela = numdaParcela;}
}

/*
dautorz

	1 – numautorz 		INTEGER  KEY AUTO INCREMENT
	2 – numsolicit		INTEGER
	3 – valorautorz		DECIMAL (12.2)
	4 – vencautorz		DATE
	5 – datautorz		DATE
	6 – situacao		VARCHAR(1
	7 – tipoconta		VARCHAR(1)
	8 – conta			INTEGER
	9 – numdaparcela 	INTEGER


	1 – numautorz		Numero da autorização de agendamento – automático sequencial	
	2 – numsolicit		Numero da Previsão de Despesas
	3 – valorautorz		Valor agendado
	4 – vencautorz		Data agendada para o pagamento
	5 – datautorz		Data em que foi feito o agendamento
	6 – situacao
	7 – tipoconta		Indica se conta bancária ou conta cartão
	8 – conta			Numero da conta, banco ou cartão,  pela qual será feito o pagamento
	9 – numdaparcela	Número da parcela autorizada  - se pagamento em conta bancária
			Número total de parcelas – se pagamento em cartão de crédito

	situacao:
		A - Lançada		
		B – 
		C - Gerado pagamento
		X - Cancelada	

*/
