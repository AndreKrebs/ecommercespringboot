package br.eti.krebscode.ecommercespringboot.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	
	private EstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// static para não precisar instanciar
	public static EstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		// busca o código no objeto e retorna objeto se encontrar
		for (EstadoPagamento x : EstadoPagamento.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		// se não foi encontrado, lança exception
		throw new IllegalArgumentException("ID inválido: " + cod);
		
	}
	
}
