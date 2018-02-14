package gpw.model;

import java.time.LocalDate;

public class Bconta {
	private LocalDate dataLanc;
	private String documento;
	private Double valorMov;
	private String natOpera;
	private Double saldoAnt;
	
	private Double saldoAtual;
	private String descrLanc;
	private int user;
	private int	lancaNum;
	private String contaOrigDest;
	
	private int numPagam;
	
	public Bconta() {
		this.dataLanc = LocalDate.now();
		this.documento = "";
		this.valorMov = 0.;
		this.natOpera = "";
		this.saldoAnt = 0.;
	
		this.saldoAtual = 0.;
		this.descrLanc = "";
		this.user =0;
		this.lancaNum = 0;
		this.contaOrigDest = "";
	
		this.numPagam = 0;
	}
	
	public Bconta(LocalDate dataLanc,String documento,Double valorMov,String natOpera,Double saldoAnt,
					Double saldoAtual,String descrLanc,int user,int	lancaNum,String contaOrigDest,int numPagam){
		this.dataLanc = dataLanc;
		this.documento = documento;
		this.valorMov = valorMov;
		this.natOpera = natOpera;
		this.saldoAnt = saldoAnt;
	
		this.saldoAtual = saldoAtual;
		this.descrLanc = descrLanc;
		this.user = user;
		this.lancaNum = lancaNum;
		this.contaOrigDest = contaOrigDest;
	
		this.numPagam = numPagam;
	}
	
	public LocalDate getDataLanc() {return this.dataLanc;}
	public void setDataLanc(LocalDate dataLanc) {this.dataLanc = dataLanc;}
	
	public String getDocumento() {return this.documento;}
	public void setDocumento(String documento) {this.documento = documento;}
	
	public Double getValorMov() {return this.valorMov;}
	public void setValorMov(Double valorMov) {this.valorMov = valorMov;}
	
	public String getNatOpera() {return this.natOpera;}
	public void setNatOpera(String natOpera) {this.natOpera = natOpera;}
	
	public Double getSaldoAnt() {return this.saldoAnt;}
	public void setSaldoAnt(Double saldoAnt) {this.saldoAnt = saldoAnt;}
	
	public Double getSaldoAtual() {return this.saldoAtual;}
	public void setSaldoAtual(Double saldoAtual) {this.saldoAtual = saldoAtual;}
	
	public String getDescrLanc() {return this.descrLanc;}
	public void setDescrLanc(String descrLanc) { this.descrLanc = descrLanc;}
	
	public int getUser() {return this.user;}
	public void setUser(int user) {this.user = user;}
	
	public int getLancaNum() {return this.lancaNum;}
	public void setLancaNum(int lancaNum) {this.lancaNum = lancaNum;}
	
	public String getContaOrigDest() {return this.contaOrigDest;}
	public void setContaOrigDest(String contaOrigDest) {this.contaOrigDest = contaOrigDest;}
	
	public int getNumPagam() {return this.numPagam;};
	public void setNumPagam(int numPagam) {this.numPagam = numPagam;}

/*
bcontaX


datalanc	DATE			Data do lançamento
docum		VARCHAR(16)	Número do documento	
valor		DECIMAL(13,2)	Valor do lançamento
natopera	VARCHAR(1)	Tipo da operação
saldoant	DECIMAL(13,2)	Saldo antes do lançamento

saldoatual	DECIMAL(13,2)	Saldo após o lançamento
histórico	VARCHAR(30)	Descrição sucinta do lançamento
usuário		INTEGER		Username do usuário que fez o lançamento
lancanum	INTEGER		Número do lançamento
contaorigdest	VARCHAR(16)	Conta de origem ou destino

numpagam	INTEGER		Numero confirmação de pagamento

*/	
}