package com.motaharinia.msutility.custom.customdto.exception;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eng.motahari@gmail.com<br>
 * Exception data used to show error message in client and log in Kibana.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto implements Serializable {
	/**
	 * Unique logging key used to search in Kibana.
	 */
	private String logKey = UUID.randomUUID().toString();

	/**
	 * Service name, where exception occurred.
	 */
	private String serviceName;

	/**
	 * Service port, where exception occurred.
	 */
	private String servicePort;

	/**
	 * Exception stack trace.
	 */
	private String stackTrace;

	/**
	 * Exception first line of stack trace
	 */
	private String stackTraceLine;

	/**
	 * Exception type.
	 */
	private ExceptionTypeEnum type;

	/**
	 * Response HTTP status value.
	 */
	private Integer httpStatusValue;

	/**
	 * The class, which exception occurs.
	 */
	private String exceptionClassName;

	/**
	 * Related data ID, used to search in Kibana.
	 * Ex: ID of a request or duplicated national code.
	 */
	private String dataId;

	/**
	 * Exception message.
	 */
	private String message;

	/**
	 * External call exception message.
	 */
	private String externalMessage;

	/**
	 * Developer description, used to search in Kibana.
	 */
	private String description;

	/**
	 * Exception validation list.
	 */
	private List<ExceptionValidationDto> validationList = new ArrayList<>();

	/**
	 * Exception occurring calling API address.
	 */
	private String url;

	/**
	 * Exception occurring user IP address.
	 */
	private String ipAddress;

	/**
	 * Exception occurring logged-in user username.
	 */
	private String username;

	/**
	 * Exception occurring logged-in user ID.
	 */
	private Long userId;

	/**
	 * Exception occurring date and time.
	 */
	private String dateOfException = String.valueOf(Instant.now().toEpochMilli());


	public ExceptionDto( String stackTrace, String stackTraceLine) {
		this.stackTrace = stackTrace;
		this.stackTraceLine = stackTraceLine;
	}
}
