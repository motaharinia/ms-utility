package com.motaharinia.msutility.custom.customvalidation.email;

import com.motaharinia.msutility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی نشانی پست الکترونیکی <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class EmailValidator  implements ConstraintValidator<Email, String> {


    @Override
    public boolean isValid(String email, ConstraintValidatorContext cvc) {
        if(ObjectUtils.isEmpty(email)){
            return true;
        }
        return email.toLowerCase().matches(CustomValidationRegularExceptionEnum.EMAIL.getValue());
    }
    
}
