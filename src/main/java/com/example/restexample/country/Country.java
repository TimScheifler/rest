package com.example.restexample.country;

public class Country {
    //time_ref,account,code,country_code,product_type,value,status
    private String time_ref;
    private String account;
    private String code;
    private String country_code;
    private String product_type;
    private String value;
    private String status;

    public Country() {
    }

    public Country(String time_ref, String account, String code, String country_code, String product_type, String value, String status) {
        this.time_ref = time_ref;
        this.account = account;
        this.code = code;
        this.country_code = country_code;
        this.product_type = product_type;
        this.value = value;
        this.status = status;
    }

    public String getTime_ref() {
        return time_ref;
    }

    public void setTime_ref(String time_ref) {
        this.time_ref = time_ref;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
