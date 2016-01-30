package br.edu.devmedia.sis_escolar.entidade;

public enum StatusExtrato {
	
	A_VENCER("A vencer", "pctFaltas-azul"), PAGO("Pago", "pctFaltas-green"), ATRASADO("Em atraso", "pctFaltas-red");
	
	private StatusExtrato(String descricao, String cssClass) {
		this.descricao = descricao;
		this.cssClass = cssClass;
	}
	
	private String descricao;
	
	private String cssClass;

	public String getDescricao() {
		return descricao;
	}

	public String getCssClass() {
		return cssClass;
	}

}