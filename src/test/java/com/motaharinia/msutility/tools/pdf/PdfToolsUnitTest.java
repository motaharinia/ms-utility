package com.motaharinia.msutility.tools.pdf;


import org.junit.jupiter.api.*;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * @author https://github.com/motaharinia<br>
 * کلاس تست PdfTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PdfToolsUnitTest {


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
    void generateFromHtmlTest() {
        try {
            File file = ResourceUtils.getFile("classpath:static/pdf/testhtml/test.html");
            String html =Files.readString(file.toPath(), StandardCharsets.UTF_8);

            try(OutputStream outputStream = new FileOutputStream("PdfToolsUnitTest_generateFromHtmlTest.pdf")) {
                PdfTools.generateFromHtml(html).writeTo(outputStream);
            }

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
