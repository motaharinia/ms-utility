package com.motaharinia.msutility.custom.customexception.business;

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
 * Business class which all business classes extend from it.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException implements Serializable, CustomException {

	/**
	 * The class, which exception occurs
	 */
	private final String exceptionClassName;

	/**
	 * Related data ID, used to search in Kibana.
	 * Ex: ID of a request or duplicated national code.
	 */
	private final String dataId;

	/**
	 * Exception message.
	 */
	private final String message;

	/**
	 * Developer description, used to search in Kibana.
	 */
	private final String description;


	protected BusinessException(@NotNull Class exceptionClass, @NotNull String dataId, @NotNull String message, String description) {
		this.exceptionClassName = exceptionClass.getSimpleName();
		this.dataId = dataId;
		this.message = message;
		this.description = description;
	}

	/**
	 * Fill required data from exception to DTO.
	 * @param exceptionDto Exception DTO.
	 */
	@Override
	public void fillDto(ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.BUSINESS_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.BAD_REQUEST.value());
		exceptionDto.setExceptionClassName(this.getExceptionClassName());
		exceptionDto.setDataId(this.getDataId());
		exceptionDto.setMessage(this.getMessage());
		exceptionDto.setDescription(this.getDescription());
	}
}
