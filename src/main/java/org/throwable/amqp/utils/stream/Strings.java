package org.throwable.amqp.utils.stream;

/**
 * @author zhangjinci
 * @version 2017/1/14 18:41
 * @function
 */
public final class Strings {

    public static String firstCaseToLower(String input) {
        char[] ch = input.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] ^= 32;
        }
        return String.valueOf(ch);
    }

}
