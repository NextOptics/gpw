package gpw.model;

//import java.util.Date;
import java.time.LocalDate;

public class Dsolicit {
	private LocalDate dataSolicit;
	private String item;
	private String aplicacao;
	private double valorPrev;
	private LocalDate prevPagam;
	private double quantidade;
	private int unidade;
	private String situacao;
	private int numParcelas;
	private int parcPagas;
	private String docum;
	private int numSolicit;
	private int classe;
	private int grupo;
	private LocalDate ultAltera;	
	private int projeto;
	
	public Dsolicit() {
//		this.dataSolicit;
		this.item = "";
		this.aplicacao = "";
		this.valorPrev = 0.;
//		this.prevPagam;
		this.quantidade = 0.;
		this.unidade = 0;
		this.situacao = "";
		this.numParcelas = 0;
		this.parcPagas = 0;
		this.docum = "";
		this.numSolicit = 0;
		this.classe = 0;
		this.grupo = 0;
//		this.ultAltera;	
		this.projeto = 0;	
	}
	public Dsolicit(LocalDate dataSolicit,String item,String aplicacao,double valorPrev,LocalDate prevPagam,double quantidade,
					int unidade,String situacao,int numParcelas,int parcPagas,String docum,int numSolicit,
					int classe,int grupo,LocalDate ultAltera,int projeto) {
		
		this.dataSolicit = dataSolicit;
		this.item = item;
		this.aplicacao = aplicacao;
		this.valorPrev = valorPrev;
		this.prevPagam = prevPagam;
		this.quantidade = quantidade;
		this.unidade = unidade;
		this.situacao = situacao;
		this.numParcelas = numParcelas;
		this.parcPagas = parcPagas;
		this.docum = docum;
		this.numSolicit = numSolicit;
		this.classe = classe;
		this.grupo = grupo;
		this.ultAltera = ultAltera;	
		this.projeto = projeto;								
	}
	
	public LocalDate getDataSolicit() {return this.dataSolicit;}
	public void setDataSolicit(LocalDate dataSolicit) {this.dataSolicit = dataSolicit;};
	
	public String getItem() {return this.item;}
	public void setItem(String item) {this.item = item;};
	
	public String getAplicacao() {return this.aplicacao;}
	public void setAplicacao(String aplicacao) {this.aplicacao = aplicacao;};
	
	public double getValorPrev() {return this.valorPrev;}
	public void setValorPrev(double valorPrev) {this.valorPrev = valorPrev;};
	
	public LocalDate getPrevPagam() {return this.prevPagam;}
	public void setPrevPagam(LocalDate prevPagam) {this.prevPagam = prevPagam;};
	
	public double getQuantidade() {return this.quantidade;}
	public void setQuantidade(double quantidade) {this.quantidade = quantidade;};
	
	public int getUnidade() {return this.unidade;}
	public void setUnidade(int unidade) {this.unidade = unidade;};
	
	public String getSituacao() {return this.situacao;}
	public void setSituacao(String situacao) {this.situacao = situacao;};
	
	public int getNumParcelas() {return this.numParcelas;}
	public void setNumParcelas(int numParcelas) {this.numParcelas = numParcelas;};
	
	public int getParcPagas() {return this.parcPagas;}
	public void setParcPagas(int parcPagas) {this.parcPagas = parcPagas;};
	
	public String getDocum() {return this.docum;}
	public void setDocum(String docum) {this.docum = docum;};
	
	public int getNumSolicit() {return this.numSolicit;}
	public void setNumSolicit(int numSolicit) {this.numSolicit= numSolicit;} // incrementado automaticamente pelo MySQL
	
	public int getClasse() {return this.classe;}
	public void setClasse(int classe) {this.classe = classe;};
	
	public int getGrupo() {return this.grupo;}
	public void setGrupo(int grupo) {this.grupo = grupo;};
	
	public LocalDate getUltAltera() {return this.ultAltera;}
	public void setUltAltera(LocalDate ultAltera) {this.ultAltera = ultAltera;};
	
	public int getProjeto() {return this.projeto;}
	public void setProjeto(int projeto) {this.projeto = projeto;};
}

/*
	
SITUAÇÃO
A – Lançada
B – Agendada parte das parcelas
C – Agendadas todas as parcelas
X – Cancelada

1 – datasolicit		- Data em que foi lançada a previsão de despesa
2 – item		- Descrição do item que gerou a despesa
3 – aplicação		- Onde será utilizado o item que gerou a despesa
4 – valorprev		- Valor total previsto para a despesa
5 – prevpagam	- Data prevista para o pagamento
6 – quantidade		- Quantidade comprada
7 – unidade		- Unidade em que o item foi comprado
8 – situação		- Estado da previsão de despesa
9 – numparcelas	- Numero de parcelas previstas para o pagamento
10 – parcpagas  	- Parcelas já pagas. São consideradas pagas as parcelas agendadas
11 –docum		- Documento gerado pela compra
12 – numsolicit	- Numero da previsão de despesa (automático)
13 – classe		- Classe do plano de contas
14 – grupo		- Grupo do plano de contas
15 – ultaltera		- Data da última edição da previsão de despesa
16 – projeto		- Número do projeto ao qual está vinculada a despesa

*/