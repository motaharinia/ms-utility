package com.motaharinia.msutility.custom.customjson.sample;


import com.motaharinia.msutility.custom.customfield.CustomDate;
import com.motaharinia.msutility.custom.customfield.CustomDateTime;
import com.motaharinia.msutility.custom.customvalidation.companyphone.CompanyPhone;
import com.motaharinia.msutility.custom.customvalidation.email.Email;
import com.motaharinia.msutility.custom.customvalidation.mobile.Mobile;
import com.motaharinia.msutility.custom.customvalidation.nationalcode.NationalCode;
import com.motaharinia.msutility.custom.customvalidation.personphone.PersonPhone;
import com.motaharinia.msutility.custom.customvalidation.postalcode.PostalCode;
import com.motaharinia.msutility.custom.customvalidation.required.Required;
import com.motaharinia.msutility.custom.customvalidation.usernameemailormobile.UsernameEmailOrMobile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author eng.motahari@gmail.com<br>
 * کلاس سمپل مدل برای تست سریالایز
 */

@Getter
@Setter
@NoArgsConstructor
public class MembershipRequestFrontDto implements Serializable {

    //socialGroup:
    @Required
    private Integer socialGroup_id;

    //user:
    @Required
    @UsernameEmailOrMobile
    private String user_username;
    @Required
    private String user_name;
    @Required
    private String user_family;
    //نام لاتین
    private String user_foreignName;
    //نام خانوادگی لاتین
    private String user_foreignFamily;
    @Required
    @NationalCode
    private String user_nationalCode;
    @Required
    private String user_nationalCodeBId;
    @Required
    private String user_fatherName;
    @Required
    private Integer user_gender_id;
    @Required
    private String user_shNo;
    //نوع شناسنامه
    //@Required
    private Integer user_shType_id;
    //شماره سریال شناسنامه
    //@Required
    private String user_shSerialAlphabet;
    //شماره سریال شناسنامه
    //@Required
    //@StringLength(min = 2, max = 2)
    private String user_shSerialNo1;
    //شماره سریال شناسنامه
    //@Required
    //@StringLength(min = 6, max = 6)
    private String user_shSerialNo2;
    //@Required
    private CustomDate user_dateOfBorn;
    //حوزه صدور
    @Required
    private String user_shArea;
    @Required
    private Integer user_maritalStatus_id;
    private Integer user_childNumber;
    //ملیت
    @Required
    private Integer user_nationality_id;
    //اقامت
    @Required
    private Integer user_inhabitancy_id;
    //دین
    private Integer user_religion_id;
    //مذهب
    private Integer user_faith_id;
    @Required
    private Integer user_education_id;
    private Integer user_job_id;
    private String user_jobCompanyName;
    //شرح شغل
    private String jobDescription;
    //علاقه مندی
    private String user_favorite;

    //user_defaultUserContact:
    //user_contact
    //خیابان
    @Required
    private String user_defaultUserContact_addressStreet;
    //کوچه
    private String user_defaultUserContact_addressAlley;
    //پلاک
    @Required
    private String user_defaultUserContact_addressNo;
    //طبقه
    private String user_defaultUserContact_addressFloor;
    //واحد
    private String user_defaultUserContact_addressFloorNo;
    //کدپستی
    @Required
    @PostalCode
    private String user_defaultUserContact_addressPostalCode;
    //تلفن
    @PersonPhone
    private String user_defaultUserContact_addressPhoneNo;
    //تلفن ضروری
    @Mobile
    private String user_defaultUserContact_addressPhoneNoNecessary;
    @Required
    @Mobile
    private String user_defaultUserContact_mobileNo;
    //ایمیل
    @Email
    private String user_defaultUserContact_emailAddress;
    //نمابر
    @PersonPhone
    private String user_defaultUserContact_faxNo;

    //خیابان
    private String user_defaultUserContact_jobAddressStreet;
    //کوچه
    private String user_defaultUserContact_jobAddressAlley;
    //پلاک
    private String user_defaultUserContact_jobAddressNo;
    //طبقه
    private String user_defaultUserContact_jobAddressFloor;
    //واحد
    private String user_defaultUserContact_jobAddressFloorNo;
    //کدپستی 
    @PostalCode
    private String user_defaultUserContact_jobAddressPostalCode;
    //تلفن محل کار
    @CompanyPhone
    private String user_defaultUserContact_jobAddressPhoneNo;
    //تلفن ضروری
    @Mobile
    private String user_defaultUserContact_jobAddressPhoneNoNecessary;
    //نمابر
    private String user_defaultUserContact_jobAddressFaxNo;

    //محل مراجعه 
    private Integer meetingLocation_id;

    //تاریخ و زمان مراجعه
    //ساعت پیشنهادی زمان مراجعه - از

    private CustomDateTime suggestionDateTimeFrom;
    //ساعت پیشنهادی زمان مراجعه - تا

    private CustomDateTime suggestionDateTimeTo;

    //introducer:
    private String introducer_username;
    private String introducer_name;
    private String introducer_family;
    private String introducer_nationalCode;


}
