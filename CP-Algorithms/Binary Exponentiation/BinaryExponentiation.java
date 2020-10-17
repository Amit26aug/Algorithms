public class BinaryExponentiation {

    static long binPowRecursive(long base, long exponent) {
        if (exponent == 0) {
            return 1;
        }
        long res = binPowRecursive(base, exponent / 2);
        if ((exponent & 1) == 1) {
            return res * res * base;
        } else {
            return res * res;
        }
    }

    static long binPowIterative(long base, long exponent) {
        long res = 1L;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                res = res * base;
            }
            base = base * base;
            exponent >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        long base = 3L;
        long exponent = 10L;
        System.out.println(binPowRecursive(base, exponent));
        System.out.println(binPowIterative(base, exponent));
    }
}