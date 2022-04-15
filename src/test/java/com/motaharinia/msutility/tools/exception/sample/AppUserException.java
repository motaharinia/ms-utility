package com.motaharinia.msutility.tools.exception.sample;


import com.motaharinia.msutility.custom.customexception.business.BusinessException;
import org.jetbrains.annotations.NotNull;

/**
 * @author eng.motahari@gmail.com<br>
 * App user business exception
 */
public class AppUserException extends BusinessException {

    public AppUserException(@NotNull String exceptionClassId, @NotNull String appUserExceptionEnum, @NotNull String exceptionDescription) {
        super(AppUserException.class, exceptionClassId, appUserExceptionEnum, exceptionDescription);
    }

}
