package com.motaharinia.msutility.custom.customvalidation.personphone;

import com.motaharinia.msutility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی شماره تلفن منزل که میتواند 8 رقم باشد<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PersonPhoneValidator implements ConstraintValidator<PersonPhone, String> {
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(phone)) {
            return true;
        }
        return phone.matches(CustomValidationRegularExceptionEnum.PERSON_PHONE.getValue());
    }
}
