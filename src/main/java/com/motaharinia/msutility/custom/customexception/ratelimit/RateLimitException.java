package com.motaharinia.msutility.custom.customexception.ratelimit;

import java.io.Serializable;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.msutility.custom.customexception.CustomException;
import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.springframework.http.HttpStatus;

/**
 * @author eng.motahari@gmail.com<br>
 * Rate limit exception.
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class RateLimitException extends RuntimeException implements Serializable, CustomException {

	/**
	 * Fill required data from exception to DTO.
	 * @param exceptionDto Exception DTO.
	 */
	@Override
	public void fillDto(ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.RATE_LIMIT_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.TOO_MANY_REQUESTS.value());
		exceptionDto.setExceptionClassName("");
		exceptionDto.setDataId("");
		exceptionDto.setMessage("RATE_LIMIT_EXCEPTION.BAN");
		exceptionDto.setDescription("");
	}
}
