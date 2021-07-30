package com.jindan.jdy.utils.exceloperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelHeaders {

    public static List<String> getHeadersList(String[] headers){

        /*将数组存入list中*/
        List<String> headersList = new ArrayList<>();
        headersList =  Arrays.asList(headers);

        return headersList;
    }
}
