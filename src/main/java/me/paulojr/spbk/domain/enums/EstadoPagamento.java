package me.paulojr.spbk.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private Integer id;
	private String nome;

	private EstadoPagamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static EstadoPagamento toEnum(Integer id) {
		if (id == null) return null;
		for (EstadoPagamento type : EstadoPagamento.values()) {
			if (type.getId().equals(id))
				return type;
		}
		throw new IllegalArgumentException("ID Inválido: " + id);
	}

}
