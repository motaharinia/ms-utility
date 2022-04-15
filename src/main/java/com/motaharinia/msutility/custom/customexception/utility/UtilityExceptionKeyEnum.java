package com.motaharinia.msutility.custom.customexception.utility;


/**
 * @author eng.motahari@gmail.com<br>
 * Utility exception enums, used to throwing utility exception messages.
 */
public enum UtilityExceptionKeyEnum {

	/**
	 * One of method parameters value null or empty.
	 */
	METHOD_PARAMETER_IS_NULL_OR_EMPTY("METHOD_PARAMETER_IS_NULL_OR_EMPTY"),
	/**
	 * One of method parameters value zero or negative.
	 */
	METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE("METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE"),
	/**
	 * Date string format not valid.
	 */
	INCORRECT_STRING_DATE_FORMAT("INCORRECT_STRING_DATE_FORMAT"),
	/**
	 * Date not valid.
	 */
	DATE_VALIDATION_FAILED("DATE_VALIDATION_FAILED"),
	/**
	 * DateTime not valid.
	 */
	DATE_TIME_VALIDATION_FAILED("DATE_TIME_VALIDATION_FAILED"),
	/**
	 * Font file to create captcha code not found.
	 */
	CAPTCHA_FONT_FILE_IS_NOT_EXIST("CAPTCHA_FONT_FILE_IS_NOT_EXIST"),
	/**
	 * Background image file to create captcha code not found.
	 */
	CAPTCHA_BACKGROUND_FILE_IS_NOT_EXIST("CAPTCHA_BACKGROUND_FILE_IS_NOT_EXIST"),
	/**
	 * Reflection: field name not exist in class.
	 */
	REFLECTION_FIELD_IS_NOT_EXISTED("REFLECTION_FIELD_IS_NOT_EXISTED"),
	/**
	 * Reflection: method name not exist in class.
	 */
	REFLECTION_METHOD_IS_NOT_EXISTED("REFLECTION_METHOD_IS_NOT_EXISTED"),
	/**
	 * File: file name format not valid.
	 */
	FSO_FILE_NAME_FORMAT_IS_INVALID("FSO_FILE_NAME_FORMAT_IS_INVALID"),
	/**
	 * File: file path not existed.
	 */
	FSO_PATH_IS_NOT_EXISTED("FSO_PATH_IS_NOT_EXISTED"),
	/**
	 * File: file path not a directory in file system.
	 */
	FSO_PATH_IS_NOT_DIRECTORY("FSO_PATH_IS_NOT_DIRECTORY"),
	/**
	 * File: the path existed before.
	 */
	FSO_DESTINATION_PATH_EXISTED("FSO_DESTINATION_PATH_EXISTED"),
	/**
	 * File: file path not valid.
	 */
	FSO_MIMETYPE_NOT_VALID_FILE_PATH("FSO_MIMETYPE_NOT_VALID_FILE_PATH"),
	/**
	 * File: directory creation failed.
	 */
	FSO_DIRECTORY_CREATION_FAILED("FSO_DIRECTORY_CREATION_FAILED"),
	/**
	 * Image: input image not readable.
	 */
	IMAGE_ORIGINAL_READ_PROBLEM("IMAGE_ORIGINAL_READ_PROBLEM"),
	/**
	 * Search: exception occurred in search.
	 */
	SEARCH_TOOLS_EXCEPTION("SEARCH_TOOLS_EXCEPTION"),
	;

	private final String value;

	UtilityExceptionKeyEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
