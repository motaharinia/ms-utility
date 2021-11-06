/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.motaharinia.msutility.tools.excel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس مدل عناوین ستونهای اکسل
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomExcelColumnHeaderDto implements Serializable {
    /**
     * عنوان ستون
     */
    private String title = "";
    /**
     * تنظیمات ظاهری عنوان ستون
     */
    private CustomExcelStyleDto style;
}
