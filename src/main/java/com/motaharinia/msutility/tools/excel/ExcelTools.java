package com.motaharinia.msutility.tools.excel;

import com.motaharinia.msutility.tools.excel.dto.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس ابزارهای مربوط به اکسل
 */
public interface ExcelTools {

    /**
     * متد تولید شیی کتاب اکسل
     *
     * @param excelDto مدل اطلاعات و تنظیمات تولید اکسل
     * @return خروجی: شیی کتاب اکسل
     */
    @NotNull
    static XSSFWorkbook generate(@NotNull ExcelDto excelDto) {

        //ساخت شیی اکسل و صفحه اکسل داخل آن
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet worksheet = workbook.createSheet(excelDto.getSheetTitle());
        worksheet.setRightToLeft(excelDto.getSheetRightToLeft());

        //تعریف متغیرها
        XSSFRow row;
        XSSFCell cell;
        XSSFCellStyle style;
        int rowIndex = 0;


        //اگر نیاز به وجود عنوان سربرگ بود
        if (!ObjectUtils.isEmpty(excelDto.getCaptionDto())) {
            //متغیرهای مربوط به سطر سربرگ
            style = makeStyle(workbook, excelDto.getCaptionDto().getStyle());
            System.out.println("style.getDataFormat():" + style.getDataFormat() + " style.getDataFormatString():" + style.getDataFormatString());
            //تنظیم سطر اکسل به عنوان سطر سربرگ
            row = worksheet.createRow(rowIndex++);
            cell = row.createCell(0);
            cell.setCellStyle(style);
            cell.setCellValue(excelDto.getCaptionDto().getTitle());
            worksheet.addMergedRegion(new CellRangeAddress(0, 0, 0, excelDto.getColumnList().size() - 1));
        }

        //اگر نیاز به وجود عناوین ستونها بود
        if (!ObjectUtils.isEmpty(excelDto.getColumnHeaderList())) {
            //متغیرهای مربوط به سطر عناوین ستونها
            int columnHeaderIndex = 0;
            //تنظیم سطر اکسل به عنوان سطر عناوین ستونها
            row = worksheet.createRow(rowIndex++);
            for (ExcelColumnHeaderDto dto : excelDto.getColumnHeaderList()) {
                style = makeStyle(workbook, dto.getStyle());
                cell = row.createCell(columnHeaderIndex++);
                cell.setCellStyle(style);
                cell.setCellValue(dto.getTitle());
            }
        }


        //متغیرهای مربوط به سطرهای داده
        BigInteger bigIntegerTest = null;
        BigDecimal bigDecimalTest = null;
        ExcelColumnDto excelColumnDto;
        HashMap<Object, Object> formatterMap = new HashMap<>();
        style = makeStyle(workbook, new ExcelStyleDto(HorizontalAlignment.CENTER, "Tahoma", false, Color.BLACK, Color.WHITE, BorderStyle.THIN, Color.BLACK, "General"));
        for (Object[] dataColumnArray : excelDto.getRowList()) {
            row = worksheet.createRow(rowIndex++);
            for (int columnIndex = 0; columnIndex < dataColumnArray.length; columnIndex++) {
                if (!ObjectUtils.isEmpty(excelDto.getColumnList()) && excelDto.getColumnList().size() > columnIndex) {
                    excelColumnDto = excelDto.getColumnList().get(columnIndex);
                    style = makeStyle(workbook, excelColumnDto.getStyle());
                    formatterMap = excelColumnDto.getFormatterMap();
                }
                cell = row.createCell(columnIndex);
                cell.setCellStyle(style);
                if (!ObjectUtils.isEmpty(formatterMap) && formatterMap.get(dataColumnArray[columnIndex]) != null) {
                    cell.setCellValue((String) formatterMap.get(dataColumnArray[columnIndex]));
                } else {
                    if (dataColumnArray[columnIndex] instanceof String) {
                        cell.setCellValue((String) dataColumnArray[columnIndex]);
                    } else if (dataColumnArray[columnIndex] instanceof Boolean) {
                        cell.setCellValue((Boolean) dataColumnArray[columnIndex]);
                    } else if (dataColumnArray[columnIndex] instanceof Integer) {
                        cell.setCellValue((Integer) dataColumnArray[columnIndex]);
                    } else if (dataColumnArray[columnIndex] instanceof Long) {
                        cell.setCellValue((Long) dataColumnArray[columnIndex]);
                    } else if (dataColumnArray[columnIndex] instanceof Float) {
                        cell.setCellValue((Float) dataColumnArray[columnIndex]);
                    } else if (dataColumnArray[columnIndex] instanceof Double) {
                        cell.setCellValue((Double) dataColumnArray[columnIndex]);
                    } else if (dataColumnArray[columnIndex] instanceof BigInteger) {
                        bigIntegerTest = (BigInteger) dataColumnArray[columnIndex];
                        cell.setCellValue(bigIntegerTest.doubleValue());
                    } else if (dataColumnArray[columnIndex] instanceof BigDecimal) {
                        bigDecimalTest = (BigDecimal) dataColumnArray[columnIndex];
                        cell.setCellValue(bigDecimalTest.doubleValue());
                    } else {
                        cell.setCellValue(dataColumnArray[columnIndex] + "");
                    }
                }
            }
        }

        //تنظیم عرض خودکار روی صفحه اکسل که داده داخل آن پر شده است
        for (int columnIndex = 0; columnIndex < excelDto.getColumnList().size(); columnIndex++) {
            worksheet.autoSizeColumn(columnIndex);
        }


        return workbook;
    }


    /**
     * این متد با دریافت مدل تنظیمات ظاهری شیی استایل اکسل را ایجاد میکند
     *
     * @param workbook      شیی کتاب اکسل
     * @param excelStyleDto مدل تنظیمات ظاهری
     * @return خروجی: شیی استایل اکسل
     */
    private static XSSFCellStyle makeStyle(XSSFWorkbook workbook, ExcelStyleDto excelStyleDto) {
        XSSFFont styleFont = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        //قلم
        styleFont.setBold(excelStyleDto.getFontIsBold());
        styleFont.setFontName(excelStyleDto.getFontName());
        styleFont.setColor(new XSSFColor(excelStyleDto.getFontColor()));
        //ظاهر
        style.setFont(styleFont);
        style.setAlignment(excelStyleDto.getAlignment());
        style.setFillForegroundColor(new XSSFColor(excelStyleDto.getBackgroundColor()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(excelStyleDto.getBorderStyle());
        style.setBorderRight(excelStyleDto.getBorderStyle());
        style.setBorderLeft(excelStyleDto.getBorderStyle());
        style.setBorderTop(excelStyleDto.getBorderStyle());
        style.setDataFormat(dataFormat.getFormat(excelStyleDto.getDataFormat()));

        return style;
    }
}
