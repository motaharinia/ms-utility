package com.motaharinia.msutility.custom.customvalidation.longrange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author eng.motahari@gmail.com<br>
 * انوتیشن اعتبارسنجی محدوده اعداد long<br>
 * فقط برای فیلدهای از نوع Long میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = LongRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LongRange {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * ابتدای محدوده عددی
     *
     * @return خروجی:
     */
    long min() default Long.MIN_VALUE;

    /**
     * انتهای محدوده عددی
     *
     * @return خروجی:
     */
    long max() default Long.MAX_VALUE;

}
