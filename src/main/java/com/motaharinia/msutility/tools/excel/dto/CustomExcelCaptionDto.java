package com.motaharinia.msutility.tools.excel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس مدل عنوان سربرگ اکسل
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomExcelCaptionDto implements Serializable {
    /**
     * عنوان سربرگ
     */
    private String title = "title1";
    /**
     * تنظیمات ظاهری سربرگ
     */
    private CustomExcelStyleDto style;
}
