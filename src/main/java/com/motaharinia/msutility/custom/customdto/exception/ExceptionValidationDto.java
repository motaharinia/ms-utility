package com.motaharinia.msutility.custom.customdto.exception;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author eng.motahari@gmail.com<br>
 * Exception validation DTO.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionValidationDto implements Serializable {
    /**
	 * Validation reference.
     */
    private String reference;
	/**
	 * Validation message.
	 */
	private String message;

}
