package org.throwable.amqp.utils.stream;

/**
 * @author zhangjinci
 * @version 2017/1/14 18:41
 * @function
 */
public final class Strings {

	public static String firstCharToLowerCase(String input) {
		if (null == input || 0 == input.length()){
			return null;
		}
		char[] ch = input.toCharArray();
		if (ch[0] >= 'A' && ch[0] <= 'Z') {
			ch[0] ^= 32;
		}
		return String.valueOf(ch);
	}

	public static String firstCharToUpperCase(String input) {
		if (null == input || 0 == input.length()){
			return null;
		}
		char[] ch = input.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] ^= 32;
		}
		return String.valueOf(ch);
	}

	public static boolean isFirstCharUpperCase(String input) {
		if (null == input || 0 == input.length()){
			return false;
		}
		char[] ch = input.toCharArray();
		return Character.isUpperCase(ch[0]);
	}

	public static boolean isFirstCharLowerCase(String input) {
		if (null == input || 0 == input.length()){
			return false;
		}
		char[] ch = input.toCharArray();
		return Character.isLowerCase(ch[0]);
	}

	public static void main(String[] args) {
		System.out.println(firstCharToUpperCase("zjc"));
	}

}
