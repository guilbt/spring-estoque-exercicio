package br.com.ithappens.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableException extends RuntimeException {

	public UnprocessableException() {
		super("Não foi possível concluir a requisição");
	}

	public UnprocessableException(String info) {
		super(info);
	}
}
