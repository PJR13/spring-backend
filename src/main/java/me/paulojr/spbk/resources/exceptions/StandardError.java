package me.paulojr.spbk.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer erro;
	private String mensagem;
	private Long timeStamp;
	
	
	
	public StandardError(Integer erro, String mensagem, Long timeStamp) {
		this.erro = erro;
		this.mensagem = mensagem;	
		this.timeStamp = timeStamp;
	}



	public Integer getErro() {
		return erro;
	}



	public void setErro(Integer erro) {
		this.erro = erro;
	}



	public String getMensagem() {
		return mensagem;
	}



	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}



	public Long getTimeStamp() {
		return timeStamp;
	}



	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}



	
	
	

}
