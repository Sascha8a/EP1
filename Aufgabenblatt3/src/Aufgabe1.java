/*
    Aufgabe 1) Codeanalyse, Codingstyle und Methoden
*/
public class Aufgabe1 {

    private static String f0(String s) {
        int xednI = 0; String ss = s;
        while (xednI < f1(ss)){
        if (xednI == 0 || f3(ss.charAt(xednI), ss.charAt(xednI-1))){xednI = f2(xednI,1);}
        else {ss = f4(ss,0,xednI-1) + ss.charAt(xednI) + ss.charAt(xednI-1) + f4(ss,xednI+1,f1(ss));xednI = f2(xednI,-1);}}
        return ss;
    }

    private static int f1(String s) {
        return s.length();
    }

    private static int f2(int n, int s) {
        return n + s;
    }

    private static boolean f3(char c1, char c2) {
        return c1 >= c2;
    }

    private static String f4(String s, int n1, int n2) {
        return s.substring(n1, n2);
    }

    public static void main(String args[]) {

        System.out.println(f0("ab"));
        System.out.println(f0("ba"));
        System.out.println(f0("aa"));
        System.out.println(f0("cba"));
        System.out.println(f0("abababab"));
        System.out.println(f0("abcfghed"));
        System.out.println(f0("abnasnasab"));
        System.out.println(f0("najskaghkkjsfvjhbavbdfsan"));
        System.out.println(f0("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv"));

        assert (f0("ab").equals("ab"));
        assert (f0("ba").equals("ab"));
        assert (f0("aa").equals("aa"));
        assert (f0("cba").equals("abc"));
        assert (f0("abababab").equals("aaaabbbb"));
        assert (f0("abcfghed").equals("abcdefgh"));
        assert (f0("abnasnasab").equals("aaaabbnnss"));
        assert (f0("najskaghkkjsfvjhbavbdfsan").equals("aaaabbdffghhjjjkkknnsssvv"));
        assert (f0("jgbgdsjabkjdbvbdjabkjsavbkjbdsvkjbagfgafjdbv").equals("aaaaabbbbbbbbbdddddffggggjjjjjjjjkkkksssvvvv"));
    }
}


