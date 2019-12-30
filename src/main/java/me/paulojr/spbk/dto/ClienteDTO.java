package me.paulojr.spbk.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import me.paulojr.spbk.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import me.paulojr.spbk.domain.Cliente;
@ClienteUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Preenchimento obrigatório.")
	@Length(min = 5, max = 120, message = "Tamanho máximo de 120 e mínimo de 5 caracteres.")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório.")
	@Email(message = "Email inválido.")
	private String email;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente c) {
		this.id = c.getId();
		this.email = c.getEmail();
		this.nome = c.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
