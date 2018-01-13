package com.ai.parse_row_data.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 9:21 2018/1/12
 * @Modified By:
 */
public class StringUtil {

    public static String clearEmpty(String str) {
        return str.replaceAll(" ", " ").replaceAll(" ", " ");
    }

    @Test
    public void test() {
        String str = "AAAAAA";
        System.out.println(clearNumSpace(str));
    }

    /**
     * 将字符串中出现的小写大写连续的情况切割
     *
     * @param str
     * @return
     */
    public static String[] splitStrByaA(String str) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char chr = str.charAt(i);
            if (Character.isLowerCase(chr) && i + 1 < str.length() && Character.isUpperCase(str.charAt(i + 1))) {
                list.add(i);
            }
        }
        if (list.isEmpty()) {
            String[] result = {str};
            return result;
        }
        String[] result = new String[list.size() + 1];
        for (int i = 0; i < result.length; i++) {
            if (i == 0) {
                result[0] = str.substring(0, list.get(0) + 1);
            } else if (i == result.length - 1) {
                result[i] = str.substring(list.get(i - 1) + 1, str.length());
            } else {
                result[i] = str.substring(list.get(i - 1) + 1, list.get(i) + 1);
            }
        }
        return result;
    }

    /**
     * 去掉字符串最前面的空串或者数字字符
     */
    public static String clearNumSpace(String str) {
        String tmp = str.trim();
        int num = 0;
        for (int i = 0; i < tmp.length(); i++) {
            if (Character.isDigit(tmp.charAt(i))) {
                num++;
                continue;
            }
            break;
        }
        return tmp.substring(num,tmp.length());
    }
}
