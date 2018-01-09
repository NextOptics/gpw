package gpw.model;

import java.time.LocalDate;

public class ContasCartao {
	private int codCard;
	private String cardBandeira;
	private String cardTipo;
	private String cardNumero;
	private String cardTitular;
	
	private String cardEmissor;
	private String cardVerific;
	private int cardVencDia;
	private int cardValidMes;
	private int cardContaVinc;
	
	private String cardComent;
	private int cardValidAno;
	private int cardBoss;
	private String cardEstado;
	private int cardFechaDia;
	
	public ContasCartao() {
		this.codCard = 0;
		this.cardBandeira = "";
		this.cardTipo = "";
		this.cardNumero = "";
		this.cardTitular = "";
	
		this.cardEmissor = "";
		this.cardVerific = "";
		this.cardVencDia = 0;
		this.cardValidMes = 0;
		this.cardContaVinc = 0;
	
		this.cardComent = "";
		this.cardValidAno = 0;
		this.cardBoss = 0;
		this.cardEstado = "";
		this.cardFechaDia = 0;
	}
	
	public ContasCartao(int codCard,String cardBandeira,String cardTipo,String cardNumero,String cardTitular,
						String cardEmissor,String cardVerific,int cardVencDia,int cardValidMes,int cardContaVinc,
						String cardComent,int cardValidAno,int cardBoss,String cardEstado,int cardFechaDia) {
		this.codCard = codCard;
		this.cardBandeira = cardBandeira;
		this.cardTipo = cardTipo;
		this.cardNumero = cardNumero;
		this.cardTitular = cardTitular;
	
		this.cardEmissor = cardEmissor;
		this.cardVerific = cardVerific;
		this.cardVencDia = cardVencDia;
		this.cardValidMes = cardValidMes;
		this.cardContaVinc = cardContaVinc;
	
		this.cardComent = cardComent;
		this.cardValidAno = cardValidAno;
		this.cardBoss = cardBoss;
		this.cardEstado = cardEstado;
		this.cardFechaDia = cardFechaDia;
	}
	
	public int getCodCard(){return this.codCard;}
	public void setCodCard(int codCard) {this.codCard = codCard;}
	
	public String getCardBandeira() {return this.cardBandeira;}
	public void setCardBandeira(String cardBandeira) {this.cardBandeira = cardBandeira;}
	
	public String getCardTipo() {return this.cardTipo;}
	public void setCardTipo(String cardTipo) {this.cardTipo = cardTipo;}
	
	public String getCardNumero() {return this.cardNumero;}
	public void setCardNumero(String cardNumero) {this.cardNumero = cardNumero;}
	
	public String getCardTitular() {return this.cardTitular;}
	public void setCardTitular(String cardTitular) {this.cardTitular = cardTitular;}
	
	public String getCardEmissor() {return this.cardEmissor;}
	public void setCardEmissor(String cardEmissor) {this.cardEmissor = cardEmissor;}
	
	public String getCardVerifica() {return this.cardVerific;}
	public void setCardVerifica(String cardVerific) {this.cardVerific = cardVerific;}
	
	public int getCardVencDia() {return this.cardVencDia;}
	public void setCardVencDia(int cardVencDia) {this.cardVencDia = cardVencDia;}
	
	public int getCardValidMes() {return this.cardValidMes;}
	public void setCardValidMes(int cardValidMes) {this.cardValidMes = cardValidMes;}
	
	public int getCardContaVinc() {return this.cardContaVinc;}
	public void setCardContaVinc(int cardContaVinc) {this.cardContaVinc = cardContaVinc;}
	
	public String getCardComent() {return this.cardComent;}
	public void setCardComent(String cardComent) {this.cardComent = cardComent;}
	
	public int getCardValidAno() {return this.cardValidAno;}
	public void setCardValidAno(int cardValidAno) {this.cardValidAno = cardValidAno;}
	
	public int getCardBoss() {return this.cardBoss;}
	public void setCardBoss(int cardBoss) {this.cardBoss = cardBoss;}
	
	public String getCardEstado() {return this.cardEstado;}
	public void setCardEstado(String cardEstado) {this.cardEstado = cardEstado;}
	
	public int getCardFechaDia() {return this.cardFechaDia;}
	public void setCardFechaDia(int cardFechaDia) {this.cardFechaDia = cardFechaDia;}
	
	
/*
contascartao

-1 - codcard 		INT 		KEY AUTOINCREMENT
-2 - cardbandeira	VARCHAR(12)
-3 - cardtipo		VARCHAR(12)
-4 – cardnumero	VARCHAR(19)
-5 - cardtitular		VARCHAR(24
-6 - cardemissor	VARCHAR(16)
-7 – cardverific		VARCHAR(4)
-8 - cardvencdia	INT			Dia de débito da fatura
-9 – cardvalidmes	INT
10 - cardcontavinc	INTEGER		Numero da conta bancaria vinculada
-11 – cardcoment	VARCHAR(16)
-12 – cardvalidano	INT
13  - cardboss		INT
14 – cardestado 	VARCHAR(1)
-15 – cardfechadia	INT 			Dia fechamento da fatura

++++ Como assegurar que a fatura do cartão foi paga no mês corrente?


cardboss  será 0 se o cartão for do titular da conta cartão ou
então, se for de um dependente,  conterá o codcard correspondente ao cartão do titular

cardestado :  
A – Cartão ativo e valido
B – Cartão substituído
X  - Cartão cancelado
*/
}