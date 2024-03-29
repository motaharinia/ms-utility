package com.motaharinia.msutility.custom.customdto.search.filter.restriction;

/**
* @author eng.motahari@gmail.com<br>
 *     مقادیر ثابت عملیات شرط گذاری بر روی جستجوی پیشرفته
 */
public enum SearchFilterOperationEnum {

    /**
     * بزرگتر از عدد یا تاریخ
     */
    GREATER_THAN("GREATER_THAN"),
    /**
     * کوچکتر  از عدد یا تاریخ
     */
    LESS_THAN("LESS_THAN"),
    /**
     * بزرگتر مساوی از عدد یا تاریخ
     */
    GREATER_THAN_EQUAL("GREATER_THAN_EQUAL"),
    /**
     * کوچکتر مساوی از عدد یا تاریخ
     */
    LESS_THAN_EQUAL("LESS_THAN_EQUAL"),
    /**
     * برابر باشد با
     */
    EQUAL("EQUAL"),
    /**
     * برابر نباشد با
     */
    NOT_EQUAL("NOT_EQUAL"),
    /**
     * تطبیق رشته ای داشته باشد با
     */
    MATCH("MATCH"),
    /**
     * با ابتدای عبارت تطبیق رشته ای داشته باشد
     */
    MATCH_START("MATCH_START"),
    /**
     * با انتهای عبارت تطبیق رشته ای داشته باشد
     */
    MATCH_END("MATCH_END"),
    /**
     * مقدار فیلد انتیتی در بین یکی از گزینه های لیست مقادیر ورودی دلخواه باشد<br>
     * SELECT a FROM EntityA a WHERE a.field IN :valueCollection
     */
    IN("IN"),
    /**
     * مقدار فیلد انتیتی در بین هیچ یک از گزینه های لیست مقادیر ورودی دلخواه نباشد<br>
     * SELECT a FROM EntityA a WHERE a.field NOT IN :valueCollection
     */
    NOT_IN("NOT_IN"),
    /**
     *مقدار ورودی دلخواه عضوی از گزینه های فیلد انتیتی از نوع لیست باشد<br>
     * SELECT a FROM EntityA a WHERE :value MEMBER OF a.fieldCollection
     */
    MEMBER_OF("MEMBER_OF"),
    /**
     *مقدار ورودی دلخواه عضوی از گزینه های فیلد انتیتی از نوع لیست نباشد<br>
     * SELECT a FROM EntityA a WHERE :value NOT MEMBER OF a.fieldCollection
     */
    NOT_MEMBER_OF("NOT_MEMBER_OF"),
    ;

    /**
     * عملیات شرط گذاری بر روی جستجوی پیشرفته
     */
    private final String value;

    SearchFilterOperationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
