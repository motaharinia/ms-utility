package com.motaharinia.msutility.custom.customexception.externalcall;

import java.io.Serializable;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.msutility.custom.customexception.CustomException;
import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * @author eng.motahari@gmail.com<br>
 * External call exception, which used when an external call has exception.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class ExternalCallException extends RuntimeException implements Serializable, CustomException {

	/**
	 * The class, which exception occurs.
	 */
	private final String exceptionClassName;

	/**
	 * Request URL.
	 * Ex: http://ms-notification/send
	 */
	private final String requestUrl;

	/**
	 * HTTP Method, which called.
	 * Ex: GET, POST, PUT, DELETE
	 */
	private final HttpMethod requestMethod;

	/**
	 * A code, which developers assign to each external API call.
	 * Ex: REQ-1001.
	 */
	private final String requestCode;

	/**
	 * Response status code.
	 * Ex: 400
	 */
	private final String responseCode;

	/**
	 * Response error message.
	 * Ex: national code not found.
	 */
	private final String responseCustomError;

	/**
	 * Response exception.
	 */
	private final Exception responseException;


	public ExternalCallException(@NotNull Class exceptionClass, @NotNull String requestUrl, @NotNull HttpMethod requestMethod, @NotNull String requestCode, @NotNull String responseCode, @NotNull String responseCustomError, @NotNull Exception responseException) {
		super("ExternalCallException");
		this.exceptionClassName = exceptionClass.getSimpleName();
		this.requestUrl = requestUrl;
		this.requestMethod = requestMethod;
		this.requestCode = requestCode;
		this.responseCode = responseCode;
		this.responseCustomError = responseCustomError;
		this.responseException = responseException;
	}

	/**
	 * Fill required data from exception to DTO.
	 * @param exceptionDto Exception DTO.
	 */
	@Override
	public void fillDto(ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.EXTERNAL_CALL_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.CONFLICT.value());
		exceptionDto.setExceptionClassName(this.getExceptionClassName());
		exceptionDto.setDataId(this.getRequestUrl());
		if (this.getResponseCustomError().equalsIgnoreCase("I/O error. Connection refused: connect")) {
			exceptionDto.setMessage("EXTERNAL_CALL_EXCEPTION.IO::" + this.getRequestCode());
		} else if (this.getResponseCode().isEmpty()) {
			exceptionDto.setMessage("EXTERNAL_CALL_EXCEPTION.UNKNOWN::" + this.getRequestCode());
		} else {
			exceptionDto.setMessage("EXTERNAL_CALL_EXCEPTION.CODE::" + this.getResponseCode() + "," + this.getRequestCode());
			exceptionDto.setExternalMessage(this.getResponseCustomError());
		}
		exceptionDto.setDescription(this.getRequestMethod().toString());
	}
}
