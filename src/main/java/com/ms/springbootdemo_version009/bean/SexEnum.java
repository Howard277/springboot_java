package com.ms.springbootdemo_version009.bean;

public enum SexEnum {
    MAN(
            0, "男"),
    WOMAN(
            1, "女");

    private Integer code;
    private String desc;

    private SexEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SexEnum get(Integer code) {
        if (code == 0) {
            return SexEnum.MAN;
        } else {
            return SexEnum.WOMAN;
        }
    }
}
