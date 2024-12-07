package br.com.cotiinformatica.application.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ValidationExceptionHandler {
	/*
	 * Método para capturar erros de validação gerados pelo BeanValidation e
	 * retornar uma resposta mais 'amigável' contendo as mensagens de erro
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public List<String> errorHandler(MethodArgumentNotValidException e) {
		
		var errors = new ArrayList<String>();
		
		for(var error : e.getBindingResult().getFieldErrors())
			errors.add(error.getDefaultMessage());
		
		for(var error : e.getBindingResult().getGlobalErrors())
			errors.add(error.getDefaultMessage());
		
		return errors; 
	}
}



