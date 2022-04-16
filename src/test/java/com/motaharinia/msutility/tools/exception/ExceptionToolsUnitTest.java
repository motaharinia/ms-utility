package com.motaharinia.msutility.tools.exception;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import com.motaharinia.msutility.tools.exception.sample.AppUserException;
import com.motaharinia.msutility.tools.exception.sample.ExceptionValidationTestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author eng.motahari@gmail.com<br>
 * ExceptionTools test class
 */
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ExceptionToolsUnitTest {

	private static final String BUSINESS_EXCEPTION_APP_USER_NATIONAL_CODE_IS_DUPLICATE = "BUSINESS_EXCEPTION.APP_USER_NATIONAL_CODE_IS_DUPLICATE";

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

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

	@Order(2)
	@Test
	void constraintViolationExceptionTest() {
		try {
			ExceptionValidationTestDto dto = new ExceptionValidationTestDto();
			Set<ConstraintViolation<ExceptionValidationTestDto>> violations = validator.validate(dto);
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		} catch (Exception ex) {
			ExceptionDto exceptionDto = ExceptionTools.getDto(ex,null,"service1",8080,125L,"eng.motahari@gmail.com", null);
			log.info("exceptionDto: {}", exceptionDto);
			assertThat(exceptionDto).isNotNull();
			assertThat(exceptionDto.getType()).isEqualTo(ExceptionTypeEnum.VALIDATION_EXCEPTION);
			assertThat(exceptionDto.getMessage()).isEqualTo("VALIDATION_EXCEPTION");
			assertThat(exceptionDto.getValidationList()).isNotEmpty();
		}
	}
}
