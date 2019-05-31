package br.com.ithappens.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	public NotFoundException() {
		super("Algum recurso necessário para a operação não foi encontrado.");
	}

	public NotFoundException(String info) {
		super(info);
	}
}
