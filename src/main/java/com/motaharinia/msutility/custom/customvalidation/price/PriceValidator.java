package com.motaharinia.msutility.custom.customvalidation.price;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی قیمت (عدد بالای صفر و بدون مقدار اعشار) <br>
 * فقط برای فیلدهای از نوع BigDecimal میتوان از این اعتبارسنجی استفاده کرد
 */
public class PriceValidator implements ConstraintValidator<Price, BigDecimal>  {
    @Override
    public boolean isValid(BigDecimal price, ConstraintValidatorContext cvc) {
        if(ObjectUtils.isEmpty(price)){
            return true;
        }
        //اگر عدد مثبت است و حاوی اعداد اعشار نیست
        return (price.compareTo(BigDecimal.ZERO) > 0) && (price.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0);
    }
}
