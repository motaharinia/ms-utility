package com.motaharinia.msutility.tools.fso;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس مدل تنظیمات ابزار فایل
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FsoConfigDto implements Serializable {
    /**
     * ابعاد تصویر بندانگشتی  فایلهای تصویری
     */
    private Integer[] thumbSizeArray = new Integer[]{60, 120};
    /**
     * پسوند فایلهای بندانگشتی تصاویر
     */
    private String thumbExtension = "thumb";
    /**
     * حداکثر تعداد مجاز فایل در یک دایرکتوری
     */
    private Integer directoryFileLimit = 100;
}
