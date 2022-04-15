package com.motaharinia.msutility.tools.exception;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import com.motaharinia.msutility.tools.exception.sample.AppUserException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author eng.motahari@gmail.com<br>
 * ExceptionTools test class
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExceptionToolsTest {

	private static final String BUSINESS_EXCEPTION_APP_USER_NATIONAL_CODE_IS_DUPLICATE = "BUSINESS_EXCEPTION.APP_USER_NATIONAL_CODE_IS_DUPLICATE";


	@Order(1)
	@Test
	void businessExceptionTest() {
		try {
			throw new AppUserException("0063234114", BUSINESS_EXCEPTION_APP_USER_NATIONAL_CODE_IS_DUPLICATE, "");
		} catch (Exception ex) {
			ExceptionDto exceptionDto = ExceptionTools.getDto(ex,null,"service1",8080,125L,"eng.motahari@gmail.com", null);
			assertThat(exceptionDto).isNotNull();
			assertThat(exceptionDto.getType()).isEqualTo(ExceptionTypeEnum.BUSINESS_EXCEPTION);
			assertThat(exceptionDto.getMessage()).isEqualTo(BUSINESS_EXCEPTION_APP_USER_NATIONAL_CODE_IS_DUPLICATE);
		}
	}
}
