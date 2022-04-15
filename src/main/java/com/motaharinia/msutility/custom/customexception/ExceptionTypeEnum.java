package com.motaharinia.msutility.custom.customexception;

/**
 * @author eng.motahari@gmail.com<br>
 * Exception type enums
 */
public enum ExceptionTypeEnum {

	/**
	 * Runtime exceptions, which not categorized.
	 * Ex: database exceptions
	 */
	GENERAL_EXCEPTION("GENERAL_EXCEPTION"),
	/**
	 * Exceptions, handled by a developer.
	 * Ex: user already exists.
	 */
	BUSINESS_EXCEPTION("BUSINESS_EXCEPTION"),
	/**
	 * Exceptions, handled by DTO validations.
	 * Ex: this field in required.
	 */
	VALIDATION_EXCEPTION("VALIDATION_EXCEPTION"),
	/**
	 * Exceptions, caught from calling external APIS.
	 */
	EXTERNAL_CALL_EXCEPTION("EXTERNAL_CALL_EXCEPTION"),
	/**
	 * Rate limit exceptions, which occurs when an API call more than expected.
	 */
	RATE_LIMIT_EXCEPTION("RATE_LIMIT_EXCEPTION"),
	;

	private final String value;

	ExceptionTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
