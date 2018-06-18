package br.eti.krebscode.ecommercespringboot.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	
	private TipoCliente(int cod, String descricao) {
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
	public static TipoCliente toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		// busca o código no objeto e retorna objeto se encontrar
		for (TipoCliente x : TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		// se não foi encontrado, lança exception
		throw new IllegalArgumentException("ID inválido: " + cod);
		
	}
}
