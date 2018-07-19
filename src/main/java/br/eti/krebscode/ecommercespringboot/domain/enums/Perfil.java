package br.eti.krebscode.ecommercespringboot.domain.enums;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	
	private Perfil(int cod, String descricao) {
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
	public static Perfil toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		// busca o código no objeto e retorna objeto se encontrar
		for (Perfil x : Perfil.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		// se não foi encontrado, lança exception
		throw new IllegalArgumentException("ID inválido: " + cod);
		
	}
}
