package com.alex.mylib.mylang;


public class MylangApplication {

	public static void main(String[] args) {
		// System.out.println(isLowerCase("abs"));
		// System.out.println(isLowerCase("Hello"));

	}

	public static boolean isLowerCase(String cs) {
        if (cs.length() == 0) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isLowerCase(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
