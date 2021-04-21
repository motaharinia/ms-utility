package com.motaharinia.msutility.custom.customvalidation.organizationeconomiccode;

import com.motaharinia.msutility.custom.customvalidation.CustomValidationRegularExceptionEnum;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی کد اقتصادی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class OrganizationEconomicCodeValidator implements ConstraintValidator<OrganizationEconomicCode, String> {
    @Override
    public boolean isValid(String economicCode, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(economicCode)) {
            return true;
        }
        return economicCode.matches(CustomValidationRegularExceptionEnum.ORGANIZATION_ECONOMIC_CODE.getValue());
    }
}
