package com.motaharinia.msutility.custom.customdto;

import java.io.Serializable;
import java.time.Instant;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author eng.motahari@gmail.com<br>
 * Client response DTO (used in all controller responses as a generic response)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDto<T> implements Serializable {
	/**
	 * Response data (filled when back-end response OK status).
	 */
	private T data;

	/**
	 * The translated message to show in the client.
	 */
	private String message;

	/**
	 * Response date time.
	 */
	private Long currentTime = Instant.now().toEpochMilli();

	/**
	 * Response exception (filled when back-end has any exception or validation).
	 */
	private ExceptionDto exception;

	/**
	 * Additional data for client.
	 */
	private String additionalData;

	public ClientResponseDto(T data, String message) {
		this.data = data;
		this.message = message;
	}

	public ClientResponseDto(ExceptionDto exception, String message) {
		this.exception = exception;
		this.message = message;
	}
}