package com.motaharinia.msutility.tools.excel;

import com.motaharinia.msutility.tools.excel.dto.CustomExcelColumnDto;
import com.motaharinia.msutility.tools.excel.dto.CustomExcelColumnHeaderDto;
import com.motaharinia.msutility.tools.excel.dto.CustomExcelDto;
import com.motaharinia.msutility.tools.excel.dto.CustomExcelStyleDto;
import com.motaharinia.msutility.tools.zip.ZipTools;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    static XSSFWorkbook generate(@NotNull CustomExcelDto excelDto) {

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
            for (CustomExcelColumnHeaderDto dto : excelDto.getColumnHeaderList()) {
                style = makeStyle(workbook, dto.getStyle());
                cell = row.createCell(columnHeaderIndex++);
                cell.setCellStyle(style);
                cell.setCellValue(dto.getTitle());
            }
        }


        //متغیرهای مربوط به سطرهای داده
        BigInteger bigIntegerTest = null;
        BigDecimal bigDecimalTest = null;
        CustomExcelColumnDto customExcelColumnDto;
        HashMap<Object, Object> formatterMap = new HashMap<>();
        style = makeStyle(workbook, new CustomExcelStyleDto(HorizontalAlignment.CENTER, "Tahoma", false, Color.BLACK, Color.WHITE, BorderStyle.THIN, Color.BLACK, "General"));
        for (Object[] dataColumnArray : excelDto.getRowList()) {
            row = worksheet.createRow(rowIndex++);
            for (int columnIndex = 0; columnIndex < dataColumnArray.length; columnIndex++) {
                if (!ObjectUtils.isEmpty(excelDto.getColumnList()) && excelDto.getColumnList().size() > columnIndex) {
                    customExcelColumnDto = excelDto.getColumnList().get(columnIndex);
                    style = makeStyle(workbook, customExcelColumnDto.getStyle());
                    formatterMap = customExcelColumnDto.getFormatterMap();
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
     * متد ایجاد و فشرده سازی فایل های اکسل
     *
     * @param excelDto مدل اطلاعات و تنظیمات تولید اکسل
     * @param rowCount تعداد سطر هر فایل
     * @param password رمز فایل زیپ
     * @param zipName اسم فایل زیپ
     * @return خروجی: آرایه بایت
     */
    static byte[] generateBatch(@NotNull CustomExcelDto excelDto, @NotNull Integer rowCount, @NotNull String password, @NotNull String zipName) throws IOException {
        //نام پوشه برای ذخیره موقت فایل ها
        String tempFolder = "temp/";
        //تعداد کل فایل هایی که باید تولید شود
        int batchSize = excelDto.getRowList().size() / rowCount;
        //مسیر هر فایل تولید شده
        List<String> paths = new ArrayList<>();
        //شماره آخرین سطر آخرین اکسل تولید شده
        int lastPosition = 0;
        for (int i = 1; i <= batchSize; i++) {


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
                for (CustomExcelColumnHeaderDto dto : excelDto.getColumnHeaderList()) {
                    style = makeStyle(workbook, dto.getStyle());
                    cell = row.createCell(columnHeaderIndex++);
                    cell.setCellStyle(style);
                    cell.setCellValue(dto.getTitle());
                }
            }


            //متغیرهای مربوط به سطرهای داده
            BigInteger bigIntegerTest = null;
            BigDecimal bigDecimalTest = null;
            CustomExcelColumnDto customExcelColumnDto;
            HashMap<Object, Object> formatterMap = new HashMap<>();
            style = makeStyle(workbook, new CustomExcelStyleDto(HorizontalAlignment.CENTER, "Tahoma", false, Color.BLACK, Color.WHITE, BorderStyle.THIN, Color.BLACK, "General"));
            for (int j = lastPosition; j < (lastPosition + rowCount); j++) {
                Object[] dataColumnArray = excelDto.getRowList().get(j);
                row = worksheet.createRow(rowIndex++);
                for (int columnIndex = 0; columnIndex < dataColumnArray.length; columnIndex++) {
                    if (!ObjectUtils.isEmpty(excelDto.getColumnList()) && excelDto.getColumnList().size() > columnIndex) {
                        customExcelColumnDto = excelDto.getColumnList().get(columnIndex);
                        style = makeStyle(workbook, customExcelColumnDto.getStyle());
                        formatterMap = customExcelColumnDto.getFormatterMap();
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
            //ذخیره فایل و اضافه کردن مسیر آن به لیست مسیرها
            String path = (tempFolder + lastPosition + "_" + (lastPosition + rowCount) + ".xlsx");
            saveTempExcel(path, workbook);
            paths.add(path);
            lastPosition += rowCount;
        }

        ZipTools.zip(paths, tempFolder + zipName, CompressionMethod.DEFLATE, CompressionLevel.MAXIMUM, password, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);

        byte[] bytes = FileUtils.readFileToByteArray(new File(tempFolder + zipName));
        //حذف پوشه موقت فایل ها
        FileUtils.forceDelete(new File(tempFolder));
        return bytes;
    }


    /**
     * این متد با دریافت مدل تنظیمات ظاهری شیی استایل اکسل را ایجاد میکند
     *
     * @param workbook            شیی کتاب اکسل
     * @param customExcelStyleDto مدل تنظیمات ظاهری
     * @return خروجی: شیی استایل اکسل
     */
    private static XSSFCellStyle makeStyle(XSSFWorkbook workbook, CustomExcelStyleDto customExcelStyleDto) {
        XSSFFont styleFont = workbook.createFont();
        XSSFCellStyle style = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        //قلم
        styleFont.setBold(customExcelStyleDto.getFontIsBold());
        styleFont.setFontName(customExcelStyleDto.getFontName());
        styleFont.setColor(new XSSFColor(customExcelStyleDto.getFontColor()));
        //ظاهر
        style.setFont(styleFont);
        style.setAlignment(customExcelStyleDto.getAlignment());
        style.setFillForegroundColor(new XSSFColor(customExcelStyleDto.getBackgroundColor()));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(customExcelStyleDto.getBorderStyle());
        style.setBorderRight(customExcelStyleDto.getBorderStyle());
        style.setBorderLeft(customExcelStyleDto.getBorderStyle());
        style.setBorderTop(customExcelStyleDto.getBorderStyle());
        style.setDataFormat(dataFormat.getFormat(customExcelStyleDto.getDataFormat()));

        return style;
    }

    /**
     * متد ذخیره فایل اکسل به صورت موقت
     *
     * @param filePath مسیر فایل
     * @param workbook شی اکسل
     */
    private static void saveTempExcel(@NotNull String filePath, @NotNull XSSFWorkbook workbook) throws IOException {
        File file = new File(filePath.substring(0, filePath.indexOf("/")));
        if (!file.exists())
            FileUtils.forceMkdir(file);
        String path = filePath;
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }


}