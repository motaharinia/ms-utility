package com.motaharinia.msutility.tools.fso.view;

/**
 * @author eng.motahari@gmail.com<br>
 *     مقادیر ثابت وضعیت فایل
 */
public enum FileViewDtoStatusEnum {

    /**
     * فایل جدید آپلود شده است
     */
    ADDED("ADDED"),
    /**
     * فایل حذف شده است
     */
    DELETED("DELETED"),
    /**
     * فایل از قبل وجود داشته و بدون تغییر مانده است
     */
    EXISTED("EXISTED");

    private final String value;

    FileViewDtoStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
