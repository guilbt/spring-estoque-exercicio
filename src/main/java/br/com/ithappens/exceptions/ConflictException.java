package br.com.ithappens.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

	public ConflictException() {
		super("Houve conflito ao tentar realizar a solicitação.");
	}

	public ConflictException(String info) {
		super(info);
	}
}
