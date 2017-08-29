package com.drore.cloud.control.manger.common.base.utils;

import com.google.common.base.Strings;

/**
 * 卓锐科技有限公司
 * Created by wmm on 2017/8/29.
 * email：6492178@gmail.com
 * description:
 *
 * @author wmm
 */
public class ControlStringUtils {
    /**
     * Is not empty boolean.
     *
     * @param strings the strings
     * @return the boolean
     */
    public static boolean isNotEmpty(String... strings) {
        for (String string : strings) {
            if (Strings.isNullOrEmpty(string)||"null".equals(string)) {
                return false;
            }
        }
        return true;
    }
}
