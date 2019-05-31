package br.com.ithappens.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

	public BadRequestException() {
		super("Algo errado nos parametros para a requisição");
	}

	public BadRequestException(String info) {
		super(info);
	}
}
