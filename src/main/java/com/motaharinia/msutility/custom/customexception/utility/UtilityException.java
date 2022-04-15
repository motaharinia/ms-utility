package com.motaharinia.msutility.custom.customexception.utility;


import java.io.Serializable;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.msutility.custom.customexception.CustomException;
import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import org.springframework.http.HttpStatus;

/**
 * @author eng.motahari@gmail.com<br>
 * Exceptions occurs inside utility package
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class UtilityException extends RuntimeException implements Serializable, CustomException {

	/**
	 * The class, which exception occurs.
	 */
	private final Class exceptionOccurredClass;

	/**
	 * Exception message.
	 */
	private final String exceptionMessage;

	/**
	 * Developer description, used to search in Kibana.
	 */
	private final String exceptionDescription;


	public UtilityException(@NotNull Class exceptionOccurredClass, @NotNull UtilityExceptionKeyEnum exceptionKeyEnum, String exceptionDescription) {
		this.exceptionOccurredClass = exceptionOccurredClass;
		this.exceptionMessage = "UTILITY_EXCEPTION." + exceptionOccurredClass.getSimpleName().toUpperCase() + "." + exceptionKeyEnum.getValue().toUpperCase();
		this.exceptionDescription = exceptionDescription;
	}

	@Override
	public String getMessage() {
		return this.exceptionMessage;
	}

	/**
	 * Fill required data from exception to DTO.
	 * @param exceptionDto Exception DTO.
	 */
	@Override
	public void fillDto(ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.BUSINESS_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.BAD_REQUEST.value());
		exceptionDto.setExceptionClassName(this.getExceptionOccurredClass().getName());
		exceptionDto.setDataId("");
		exceptionDto.setMessage(this.getMessage());
		exceptionDto.setDescription(this.getExceptionDescription());
	}
}
