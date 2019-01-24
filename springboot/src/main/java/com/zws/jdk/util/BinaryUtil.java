package com.zws.jdk.util;

import javafx.css.Match;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zws
 * @email 2848392861@qq.com
 * date 2019/1/24
 */
public class BinaryUtil {
    static String NUMBER_REGEX = "[0-9].*";


    public static String twoToTen(String binary) {
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(binary);
        if (!matcher.matches()) {
            return "";
        }
        int length = binary.length();
        if (binary.indexOf(".") != -1) {
            length = binary.split(".")[0].length();
            binary = binary.replace(".", "");
        }

        BigDecimal ten = BigDecimal.ZERO;
        for (int i = length-1, j = 0; j < binary.length(); i--, j++) {
            ten = ten.add(BigDecimal.valueOf(Long.valueOf(binary.substring(j,j+1))).multiply(BigDecimal.valueOf(Math.pow(2, i))));
        }

        return ten.toString();

    }


}
