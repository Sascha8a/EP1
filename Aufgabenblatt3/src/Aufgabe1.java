/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
public class Aufgabe1 {
    /**
     * Sorts the characters in the string.
     * <p>
     *     Examples:
     *     <blockquote>
     *         <pre>
     *             sortStringLegacy("abc") returns "abc"
     *             sortStringLegacy("cba") returns "abc"
     *             sortStringLegacy("cccbba") returns "abbccc"
     *         </pre>
     *     </blockquote>
     * </p>
     *
     * @implNote Implemented using a very basic Insertion sort
     * @param str String to sort
     * @return Sorted String
     */
    private static String sortString(String str) {
        int i = 1;

        while (i < str.length()) {
            int j = i;

            while (j > 0 && (str.charAt(j) < str.charAt(j-1))) {
                str = str.substring(0, j-1) + str.charAt(j) + str.charAt(j-1) + str.substring(j + 1);
                j -= 1;
            }

            i += 1;
        }

        return str;
    }

    /**
     * Sorts the characters in the string.
     * <p>
     *     Examples:
     *     <blockquote>
     *         <pre>
     *             sortStringLegacy("abc") returns "abc"
     *             sortStringLegacy("cba") returns "abc"
     *             sortStringLegacy("cccbba") returns "abbccc"
     *         </pre>
     *     </blockquote>
     * </p>
     *
     * @param s String to sort
     * @return Sorted String
     * @deprecated
     */
    @Deprecated
    private static String sortStringLegacy(String s) {
        int i = 0;
        String sorted = s;

        while (i < strLen(sorted)) {
            if (i == 0 || charCmp(sorted.charAt(i), sorted.charAt(i - 1))) {
                i = sum(i, 1);
            } else {
                sorted = subString(sorted, 0, i - 1) + sorted.charAt(i) + sorted.charAt(i - 1) + subString(sorted, i + 1, strLen(sorted));
                i = sum(i, -1);
            }
        }
        return sorted;
    }

    /**
     * Returns the length of a String
     * @param str String to calculate length of
     * @return String Length
     * @deprecated
     */
    @Deprecated
    private static int strLen(String str) {
        return str.length();
    }

    /**
     * Returns the sum of two numbers
     * @param a First number
     * @param b Second number
     * @return Sum of two numbers
     * @deprecated
     */
    @Deprecated
    private static int sum(int a, int b) {
        return a + b;
    }

    /**
     * Returns, wether the first string's charCode ist bigger or equal to the 2nd character's code.
     * <p>
     * Examples:
     * <blockquote>
     *     <pre>
     *         charCmp('a', 'b') returns false
     *         charCmp('z', 'a') returns true
     *     </pre>
     * </blockquote>
     * </p>
     *
     *
     * @param c1 First character
     * @param c2 Second character
     * @return c1 >= c2
     * @deprecated
     */
    @Deprecated
    private static boolean charCmp(char c1, char c2) {
        return c1 >= c2;
    }

    /**
     * Returns a string that is a substring of this string. The
     * substring begins at the specified {@code beginIndex} and
     * extends to the character at index {@code endIndex - 1}.
     * Thus the length of the substring is {@code endIndex-beginIndex}.
     * <p>
     * Examples:
     * <blockquote><pre>
     * "hamburger".substring(4, 8) returns "urge"
     * "smiles".substring(1, 5) returns "mile"
     * </pre></blockquote>
     *
     * @param s String, from which the substring should be returned
     * @param beginIndex   the beginning index, inclusive.
     * @param endIndex     the ending index, exclusive.
     * @return the specified substring
     * @deprecated
     */
    @Deprecated
    private static String subString(String s, int beginIndex, int endIndex) {
        return s.substring(beginIndex, endIndex);
    }

    public static void main(String args[]) {
        System.out.println(sortString("ab"));
        System.out.println(sortString("ba"));
        System.out.println(sortString("aa"));
        System.out.println(sortString("cba"));
        System.out.println(sortString("abababab"));
        System.out.println(sortString("abcfghed"));
        System.out.println(sortString("abnasnasab"));
        System.out.println(sortString("najskaghkkjsfvjhbavbdfsan"));
        System.out.println(sortString("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv"));

        assert (sortString("ab").equals("ab"));
        assert (sortString("ba").equals("ab"));
        assert (sortString("aa").equals("aa"));
        assert (sortString("cba").equals("abc"));
        assert (sortString("abababab").equals("aaaabbbb"));
        assert (sortString("abcfghed").equals("abcdefgh"));
        assert (sortString("abnasnasab").equals("aaaabbnnss"));
        assert (sortString("najskaghkkjsfvjhbavbdfsan").equals("aaaabbdffghhjjjkkknnsssvv"));
        assert (sortString("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv").equals("aaaaabbbbbbbbbdddddffggggjjjjjjjjkkkksssvvvv"));
    }
}


