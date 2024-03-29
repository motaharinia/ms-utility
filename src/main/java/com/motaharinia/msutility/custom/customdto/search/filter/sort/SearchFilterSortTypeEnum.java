package com.motaharinia.msutility.custom.customdto.search.filter.sort;

/**
* @author eng.motahari@gmail.com<br>
 *     اینام مقادیر ثابت نوع مرتب سازی مدل مرتب سازی در جستجوی پیشرفته
 */
public enum SearchFilterSortTypeEnum {
    /**
     * مرتب سازی صعودی
     */
    ASC("ASC"),
    /**
     * مرتب سازی نزولی
     */
    DSC("DSC");

    //نوع مرتب سازی
    private String value;

    /**
     * تابع سازنده ثابت نوع مرتب سازی از روی مقدار نوع مرتب سازی آن
     *
     * @param value مقدار  ثابت نوع مرتب سازی
     */
    SearchFilterSortTypeEnum(String value) {
        this.value = value;
    }

    /**
     * مقدار نوع مرتب سازی را خروجی میدهد
     *
     * @return خروجی: مقدار نوع مرتب سازی
     */
    public String getValue() {
        return this.value;
    }

}
