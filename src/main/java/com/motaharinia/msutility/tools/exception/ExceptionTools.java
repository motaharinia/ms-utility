package com.motaharinia.msutility.tools.exception;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;

import com.motaharinia.msutility.custom.customdto.exception.ExceptionDto;
import com.motaharinia.msutility.custom.customdto.exception.ExceptionValidationDto;
import com.motaharinia.msutility.custom.customexception.CustomException;
import com.motaharinia.msutility.custom.customexception.ExceptionTypeEnum;
import com.motaharinia.msutility.tools.string.StringTools;
import org.jetbrains.annotations.NotNull;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

/**
 * @author eng.motahari@gmail.com<br>
 * Exception tools to convert exception to DTO.
 */
public interface ExceptionTools {

	String CUSTOM_VALIDATION_KEY = "VALIDATION_EXCEPTION";

	/**
	 * Converts an exception to DTO.
	 * @param exception Exception
	 * @param httpServletRequest HTTP request
	 * @param serviceName Service name, where exception occurred.
	 * @param servicePort Service port, where exception occurred.
	 * @param userId Exception occurring logged-in user ID.
	 * @param username Exception occurring logged-in user username.
	 * @param messageSource Message source to translate messages for client.
	 * @return Exception DTO
	 */
	static ExceptionDto getDto(@NotNull Exception exception, HttpServletRequest httpServletRequest, String serviceName, int servicePort, Long userId, String username, MessageSource messageSource) {
		//Set exception data
		ExceptionDto exceptionDto = new ExceptionDto(getStackTraceString(exception), getStackTraceLineString(exception));
		if (exception.getClass() != null) {
			if (exception instanceof CustomException) {
				CustomException customException = (CustomException) exception;
				customException.fillDto(exceptionDto);
			} else if (exception instanceof MethodArgumentNotValidException) {
				fillDtoFromMethodArgumentNotValidException((MethodArgumentNotValidException) exception, exceptionDto);
			} else if (exception instanceof ConstraintViolationException) {
				fillDtoFromConstraintViolationException((ConstraintViolationException) exception, exceptionDto);
			} else {
				fillDtoFromGeneralException(exception, exceptionDto);
			}
		}
		//Set service data
		exceptionDto.setServiceName(serviceName);
		exceptionDto.setServicePort(String.valueOf(servicePort));
		//Set environment data
		exceptionDto.setUrl(getRequestUrl(httpServletRequest));
		exceptionDto.setIpAddress(getRequestIpAddress(httpServletRequest));
		//Set logged-in user data
		exceptionDto.setUserId(userId);
		exceptionDto.setUsername(username);
		//Translate
		if (!exceptionDto.getType().equals(ExceptionTypeEnum.GENERAL_EXCEPTION)) {
			translateDto(exceptionDto, messageSource);
		}
		return exceptionDto;
	}


	/**
	 * Fill required data from DTO fields validation exception.
	 *
	 * @param methodArgumentNotValidException DTO fields validation exception
	 * @param exceptionDto Exception DTO
	 */
	static void fillDtoFromMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException, ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.VALIDATION_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.BAD_REQUEST.value());
		exceptionDto.setExceptionClassName("");
		exceptionDto.setDataId("");
		exceptionDto.setMessage(CUSTOM_VALIDATION_KEY);
		exceptionDto.setDescription("");
		List<ExceptionValidationDto> validationDtoList = new ArrayList<>();
		BindingResult result = methodArgumentNotValidException.getBindingResult();
		String fieldName;
		for (FieldError fieldError : result.getFieldErrors()) {
			//reference: fieldName[DTOName + fieldName]
			fieldName = fieldError.getObjectName() + "." + fieldError.getField();
			validationDtoList.add(new ExceptionValidationDto(fieldName, fieldError.getDefaultMessage()));
		}
		exceptionDto.setValidationList(validationDtoList);
	}

	/**
	 * Fill required data from Method parameters validation exception.
	 *
	 * @param constraintViolationException Method parameters validation exception
	 * @param exceptionDto Exception DTO
	 */
	static void fillDtoFromConstraintViolationException(ConstraintViolationException constraintViolationException, ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.VALIDATION_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.BAD_REQUEST.value());
		exceptionDto.setExceptionClassName("");
		exceptionDto.setDataId("");
		exceptionDto.setMessage(CUSTOM_VALIDATION_KEY);
		exceptionDto.setDescription("");
		Set<ConstraintViolation<?>> constraintViolationSet = constraintViolationException.getConstraintViolations();
		List<ExceptionValidationDto> validationDtoList = new ArrayList<>();
		String fieldName;
		for (ConstraintViolation<?> violation : constraintViolationSet) {
			fieldName = "";
			for (Path.Node node : violation.getPropertyPath()) {
				fieldName = node.getName();
			}
			validationDtoList.add(new ExceptionValidationDto(fieldName, violation.getMessage()));
		}
		exceptionDto.setValidationList(validationDtoList);
	}


	/**
	 * Fill required data from unknown general exception.
	 *
	 * @param generalException General Exception
	 * @param exceptionDto Exception DTO
	 */
	static void fillDtoFromGeneralException(Exception generalException, ExceptionDto exceptionDto) {
		exceptionDto.setType(ExceptionTypeEnum.GENERAL_EXCEPTION);
		exceptionDto.setHttpStatusValue(HttpStatus.INTERNAL_SERVER_ERROR.value());
		exceptionDto.setExceptionClassName("");
		exceptionDto.setDataId("");
		exceptionDto.setMessage(generalException.getMessage());
		exceptionDto.setDescription("");
	}


	/**
	 * Get exception stacktrace string.
	 *
	 * @param exception Exception
	 * @return Stacktrace string
	 */
	static String getStackTraceString(@NotNull Exception exception) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		exception.printStackTrace(printWriter);
		return stringWriter.toString();
	}

	/**
	 * Get first line of exception stacktrace string.
	 *
	 * @param exception Exception
	 * @return First line of exception stacktrace string
	 */
	static String getStackTraceLineString(@NotNull Exception exception) {
		if (exception.getStackTrace() != null && exception.getStackTrace().length > 0) {
			String relatedToClassName = exception.getStackTrace()[0].getClassName();
			String relatedToMethodName = exception.getStackTrace()[0].getMethodName();
			String relatedToLineNumber = Integer.toString(exception.getStackTrace()[0].getLineNumber());
			return ("ClassName:" + relatedToClassName + " MethodName:" + relatedToMethodName + " LineNumber:" + relatedToLineNumber);
		} else {
			return "";
		}
	}


	/**
	 * Get API address from HTTP request
	 * @param httpServletRequest HTTP request
	 * @return API address
	 */
	static String getRequestUrl(HttpServletRequest httpServletRequest) {
		if (httpServletRequest != null) {
			return httpServletRequest.getServletPath();
		} else {
			return "";
		}
	}

	/**
	 * Get IP address from HTTP request.
	 *
	 * @param httpServletRequest HTTP request
	 * @return IP address
	 */
	static String getRequestIpAddress(HttpServletRequest httpServletRequest) {
		if (httpServletRequest != null) {
			String ipAddress = httpServletRequest.getHeader("X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = httpServletRequest.getRemoteAddr();
			}
			return ipAddress;
		} else {
			return "";
		}
	}


	/**
	 * Translate the exception DTO
	 * @param exceptionDto Exception DTO
	 * @param messageSource Message Source
	 */
	static void translateDto(@NotNull ExceptionDto exceptionDto, MessageSource messageSource) {
		if (messageSource != null) {
			exceptionDto.setMessage(StringTools.translateCustomMessage(messageSource, exceptionDto.getMessage()));
			if (exceptionDto.getValidationList() != null) {
				exceptionDto.getValidationList().forEach(validationItem -> {
					if (validationItem.getMessage() != null && validationItem.getMessage().toUpperCase(Locale.getDefault()).startsWith("CUSTOM_VALIDATION")) {
						validationItem.setMessage(StringTools.translateCustomMessage(messageSource, validationItem.getMessage()));
					}
				});
			}
		}
	}
}
