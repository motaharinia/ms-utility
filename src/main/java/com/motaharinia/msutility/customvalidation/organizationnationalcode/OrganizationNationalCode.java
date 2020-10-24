package com.motaharinia.msutility.customvalidation.organizationnationalcode;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی کد ملی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = OrganizationNationalCodeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OrganizationNationalCode {
    String message() default "customValidation.organizationNationalCode";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
}
