package com.motaharinia.msutility.tools.string;

import com.motaharinia.msutility.custom.customexception.utility.UtilityException;
import com.motaharinia.msutility.custom.customexception.utility.UtilityExceptionKeyEnum;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author https://github.com/motaharinia
 * اینترفیس پدر تمامی اینترفیسهای جستجوی پیشرفته دیتابیس که حکم میکند تمام آنها باید متد گتر آی دی را داشته باشند
 * تمام اینترفیسهای جستجوی پیشرفته دیتابیس باید از این اینترفیس گسترش یابند
 */
public interface StringTools {

    String CHARACTERS_LOWER = "abcdefghigklmnopqrstuvwxyz";
    String CHARACTERS_UPPER = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
    String NUMBERS = "1234567890";
    String PUNCTUATIONS = "_-$#@%^*&=+";
    String CHARACTERS_PERSIAN = "ابپتثجچحخدذرزژسشصضطظعغفقکگلمنوهی";
    Random RANDOM = new Random();

    /**
     * این متد رشته و طول مورد نظر را از ورودی دریافت میکند و رشته هش شده بر مبنای الگوی رمزگذاری ام دی 5 با حداکثر طول درخواستی را تولید میکند
     *
     * @param input        رشته ورودی جهت رمزنگاری
     * @param resultLength حداکثر طول رشته هش شده خروجی
     * @return خروجی: رشته هش شده بر مبنای الگوی رمزگذاری ام دی 5 با حداکثر طول درخواستی
     * @throws NoSuchAlgorithmException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static String generateMD5Hash(@NotNull String input, @NotNull Integer resultLength) throws NoSuchAlgorithmException {
        if (ObjectUtils.isEmpty(input)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(resultLength)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "resultLength");
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5sum = md.digest(input.getBytes());
        String output = String.format("%032x", new BigInteger(1, md5sum));
        if (resultLength <= input.length()) {
            return output.substring(0, resultLength);
        } else {
            return output;
        }
    }

    /**
     * این متد رشته ای را از ورودی دریافت میکند و تمام تگهای اچ تی ام ال آن را حذف میکند
     *
     * @param htmlString رشته ورودی
     * @return خروجی: رشته بدون تگهای اچ تی ام ال
     */
    @NotNull
    static String removeHtml(@NotNull String htmlString) {
        if (ObjectUtils.isEmpty(htmlString)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "htmlString");
        }
        return Jsoup.parse(htmlString).text();
    }

    /**
     * این متد رشته ورودی و طول مورد نظر رشته خروجی را دریافت میکند و رشته مورد نظر را به اندازه طول درخواستی کوتاه میکند
     * اگر طول رشته ورودی کمتر یا مساوی طول درخواستی باشد تغییری در رشته داده نمیشود
     * ولی در غیر این صورت رشته ورودی کوتاه شده و در انتهای آن سه نقطه قرار میگیرد
     *
     * @param input      رشته ورودی
     * @param charNumber طول مورد نظر
     * @return خروجی: رشته اصلاح شده طبق طول مورد نظر
     */
    @NotNull
    static String summarizeString(@NotNull String input, @NotNull Integer charNumber) {
        if (ObjectUtils.isEmpty(input)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(charNumber)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "charNumber");
        }
        String result;
        if (input.length() < charNumber) {
            result = input;
        } else {
            if (charNumber > 3) {
                result = input.substring(0, charNumber - 3) + "...";
            } else {
                result = input.substring(0, charNumber) + "...";
            }
        }
        return result;
    }

    /**
     * این متد رشته ورودی و عبارت مورد نظر را از ورودی میگیرد و آن عبارت در رشته را هایلایت مینماید
     *
     * @param inputText رشته ورودی
     * @param search    عبارت مورد نظر جهت هایلایت
     * @return خروجی: رشته با عبارات هایلایت شده
     */
    @NotNull
    static String highlight(@NotNull String inputText, @NotNull String search) {
        if (ObjectUtils.isEmpty(inputText)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputText");
        }
        if (ObjectUtils.isEmpty(search)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "search");
        }
        return inputText.replaceAll(search, "<span class='highlight'>" + search + "</span>");
    }

    /**
     * این متد رشته ای تصادفی را با توجه به نوع ، طول و اینکه اعداد با پیش صفر باشند یا خیر را تولید میکند
     *
     * @param randomGenerationTypeEnum نوع ترکیب رشته تصادفی
     * @param length                   طول رشته مورد نظر
     * @param withLeadingZero          با صفر شروع شود؟
     * @return خروجی: رشته تصادفی
     */
    @NotNull
    static String generateRandomString(@NotNull RandomGenerationTypeEnum randomGenerationTypeEnum, @NotNull Integer length, @NotNull Boolean withLeadingZero) {
        if (ObjectUtils.isEmpty(randomGenerationTypeEnum)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "randomGenerationTypeEnum");
        }
        if (ObjectUtils.isEmpty(length)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "length");
        }
        if (ObjectUtils.isEmpty(withLeadingZero)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withLeadingZero");
        }
        String characters = getCharacters(randomGenerationTypeEnum);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(characters.charAt(RANDOM.nextInt(characters.length())));
        }
        checkBlankZero(stringBuilder, characters, length, withLeadingZero);
        return stringBuilder.toString();
    }

    /**
     * متد بررسی جای خالی و عدد صفر در رشته تولید شده
     *
     * @param stringBuilder   شیی سازنده رشته
     * @param characters      رشته حاوی کارکترهای مورد نظر
     * @param length          طول رشته مورد نظر
     * @param withLeadingZero با صفر شروع شود؟
     */
    static void checkBlankZero(@NotNull StringBuilder stringBuilder, @NotNull String characters, @NotNull Integer length, @NotNull Boolean withLeadingZero) {
        //بررسی عدم وجود صفر در ابتدای رشته با بررسی withLeadingZero
        if (((stringBuilder.toString().charAt(0)) == 48) && (!withLeadingZero)) {
            stringBuilder.deleteCharAt(0);
            stringBuilder.insert(0, characters.charAt(RANDOM.nextInt(characters.length())));
            checkBlankZero(stringBuilder, characters, length, withLeadingZero);
        }
        //بررسی عدم وجود جای خالی در ابتدا و انتهای رشته
        if (Character.isWhitespace(stringBuilder.toString().charAt(0))) {
            stringBuilder.deleteCharAt(0);
            stringBuilder.insert(0, characters.charAt(RANDOM.nextInt(characters.length())));
            checkBlankZero(stringBuilder, characters, length, withLeadingZero);
        }
        if (Character.isWhitespace(stringBuilder.toString().charAt(length - 1))) {
            stringBuilder.deleteCharAt(length - 1);
            stringBuilder.append(characters.charAt(RANDOM.nextInt(characters.length())));
            checkBlankZero(stringBuilder, characters, length, withLeadingZero);
        }
    }

    /**
     * متد تولید رشته حاوی کارکترهای مورد نظر
     *
     * @param randomGenerationTypeEnum نوع ترکیب رشته
     * @return خروجی: رشته حاوی کارکترهای مورد نظر
     */
    static String getCharacters(@NotNull RandomGenerationTypeEnum randomGenerationTypeEnum) {
        switch (randomGenerationTypeEnum) {
            case LATIN_CHARACTERS:
                return CHARACTERS_LOWER + CHARACTERS_UPPER;
            case LATIN_LOWER_CHARACTERS:
                return CHARACTERS_LOWER;
            case LATIN_UPPER_CHARACTERS:
                return CHARACTERS_UPPER;
            case NUMBER:
                return NUMBERS;
            case LATIN_CHARACTERS_NUMBERS:
                return CHARACTERS_LOWER + CHARACTERS_UPPER + NUMBERS;
            case PUNCTUATIONS:
                return PUNCTUATIONS;
            case LATIN_CHARACTERS_NUMBERS_PUNCTUATIONS:
                return CHARACTERS_LOWER + CHARACTERS_UPPER + NUMBERS + PUNCTUATIONS;
            case LATIN_CHARACTERS_NUMBERS_UNDERLINE:
                return CHARACTERS_LOWER + CHARACTERS_UPPER + NUMBERS + "_";
            case NUMBERS_UNDERLINE:
                return NUMBERS + "_";
            case PERSIAN_CHARACTERS_SPACE:
                return CHARACTERS_PERSIAN + " ";
            case PERSIAN_CHARACTERS_SPACE_NUMBERS:
                return CHARACTERS_PERSIAN + NUMBERS + " ";
            default:
                return "";
        }
    }

    /**
     * متد ترجمه متن مورد نظر
     *
     * @param messageSource منبع ترجمه
     * @param customMessage کلید پیام برای ترجمه
     * @return خروجی: پیام ترجمه شده
     */
    static String translateCustomMessage(MessageSource messageSource, String customMessage) {
        if (!ObjectUtils.isEmpty(messageSource) && !ObjectUtils.isEmpty(customMessage)) {
            if (customMessage.split("::").length > 1) {
                String[] customMessageArray = customMessage.split("::");
                String[] customMessageParametersArray = customMessageArray[1].split(",", -1);
                customMessage = messageSource.getMessage(customMessageArray[0], customMessageParametersArray, LocaleContextHolder.getLocale());
            } else {
                customMessage = messageSource.getMessage(customMessage, new Object[]{}, LocaleContextHolder.getLocale());
            }
        }
        return customMessage;
    }


}
