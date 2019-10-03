package me.paulojr.spbk.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"), PESSOAJURIDICA(2, "Pessoa Jurídica");

	private Integer id;
	private String nome;

	private TipoCliente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static TipoCliente toEnum(Integer id) {
		if (id == null) return null;
		for (TipoCliente type : TipoCliente.values()) {
			if(type.getId().equals(id)) return type;
		}
		throw new IllegalArgumentException("ID Inválido: " + id);
	}

}
