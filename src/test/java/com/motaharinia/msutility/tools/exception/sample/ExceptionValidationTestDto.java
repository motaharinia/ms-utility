package com.motaharinia.msutility.tools.exception.sample;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eng.motahari@gmail.com<br>
 * Sample class for exception validation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionValidationTestDto implements Serializable {

    //Required:
    @NotEmpty(message = "required1 is empty")
    private String required1;
	@NotNull(message = "required2 is null")
    private Integer required2;
	@NotNull(message = "required3 is null")
    private Boolean required3;
	@NotNull(message = "required4 is null")
    private List<String> required4;
	@NotNull(message = "required5 is null")
    private Map<Integer, String> required5;
}
