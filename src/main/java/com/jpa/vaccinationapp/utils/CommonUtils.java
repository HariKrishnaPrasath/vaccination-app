package com.jpa.vaccinationapp.utils;

import org.springframework.beans.BeanUtils;

public class CommonUtils {
    public static void copyAllProperties(Object source,Object target){
        BeanUtils.copyProperties(source,target);
    }
}
