package com.motaharinia.msutility.custom.customvalidation.persiancharacters;

import com.motaharinia.msutility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی رشته با کارکترهای پارسی و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PersianCharactersValidator implements ConstraintValidator<PersianCharacters, String> {
    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(string)) {
            return true;
        }
        return string.matches(CustomValidationRegularExceptionEnum.PERSIAN_CHARACTERS_SPACE.getValue());
    }
}
