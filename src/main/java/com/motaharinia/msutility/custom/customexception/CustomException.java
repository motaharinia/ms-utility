package com.motaharinia.msutility.custom.customexception;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;

/**
 * @author eng.motahari@gmail.com<br>
 * Custom exception interface, which all custom exception should implement it.
 */
public interface CustomException {
	/**
	 * Fill required data from exception to DTO.
	 * @param exceptionDto Exception DTO.
	 */
	void fillDto(ExceptionDto exceptionDto);
}
