package com.rslakra.interview.utils;

import java.util.Collection;

public enum Utils {

    INSTANCE;

    public static final String EMPTY = "";

    /**
     * Get character count in the string.
     *
     * @param input
     * @param cChar
     * @return
     */
    public static int getCount(String input, char cChar) {
        int count = 0;
        for (char element : input.toCharArray()) {
            if (element == cChar) {
                count++;
            }
        }

        return count;
    }

    /**
     * Returns true if the string is null or empty.
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(CharSequence input) {
        return (input == null) || (input instanceof String) && ((String) input).isEmpty();
    }

    /**
     * @param input
     * @return
     */
    public static boolean isNotEmpty(CharSequence input) {
        return !isEmpty(input);
    }

    /**
     * check is the collection is <code>null</code> or empty
     *
     * @param collection
     * @return <code>true</code> if empty or <code>null</code>, <code>false</code> otherwise
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
