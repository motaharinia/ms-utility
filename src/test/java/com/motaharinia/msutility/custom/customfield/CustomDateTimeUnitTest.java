package com.motaharinia.msutility.custom.customfield;

import com.motaharinia.msutility.custom.customjson.CustomObjectMapper;
import org.junit.jupiter.api.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس تست CustomDateTime
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomDateTimeUnitTest {

    private final CustomObjectMapper mapper = new CustomObjectMapper();

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void beforeEach() {
        Locale.setDefault(new Locale("fa", "IR"));
    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void afterEach() {
        Locale.setDefault(Locale.US);
    }

    @Order(1)
    @Test
    void constructorByJsonTest() {
        try {
            String json = "{\"year\":1399,\"month\":12,\"day\":30,\"hour\":0,\"minute\":0,\"second\":0}";
            CustomDateTime customDateTime = mapper.readValue(json, CustomDateTime.class);
            assertThat(customDateTime.toString()).hasToString("CustomDateTime{2021-03-20 00:00:00}");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Order(2)
    @Test
    void constructorByDateTest() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
            Calendar cal = Calendar.getInstance();
            cal.setTime(simpleDateFormat.parse("2021-03-20 00:00:00"));
            CustomDateTime customDateTime = new CustomDateTime(cal.getTime());
            assertThat(customDateTime.toString()).hasToString("CustomDateTime{2021-03-20 00:00:00}");
            //اگر در دیتابیس فیلد تاریخ null بود به سمت کلاینت customDateTime با فیلدهای خالی برود
            customDateTime = new CustomDateTime(null);
            assertThat(customDateTime.toString()).hasToString("CustomDateTime{null-null-null null:null:null}");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(3)
    @Test
    void comparatorTest() {
        try {
            List<CustomDateTime> customDateTimeList = new ArrayList<>();
            customDateTimeList.add(new CustomDateTime(1395, 12, 13, 0, 0, 0));
            customDateTimeList.add(new CustomDateTime(1396, 10, 1, 0, 0, 0));
            customDateTimeList.add(new CustomDateTime(1394, 8, 2, 0, 0, 0));
            customDateTimeList.add(new CustomDateTime(1395, 11, 2, 0, 0, 0));
            customDateTimeList.add(new CustomDateTime(1394, 4, 14, 10, 10, 10));
            customDateTimeList.add(new CustomDateTime(1396, 1, 16, 0, 0, 0));
            Collections.sort(customDateTimeList);
            //1394-04-14 -> 2015-07-05
            assertThat(customDateTimeList.get(0).toString()).hasToString("CustomDateTime{2015-07-05 10:10:10}");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}
