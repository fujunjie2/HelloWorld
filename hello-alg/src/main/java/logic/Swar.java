package logic;

public class Swar {

    public static int doSwar(int i)
    {
        i = i - ((i >> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
        return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
    }

    /**
     *   假设 i = 13   0001 0101
     *   0x55555555 ->   1010101 01010101 01010101 01010101
     *   i >> 1 & 0x55555555  将i右移一位,  相与,  则偶数位全变为 0
     *
     *   假设
     */
    public static void analysis() {
        // 1010101 01010101 01010101 01010101 :31位
        int constant = 0x55555555;

        // 1010
        int i = 10;
//        System.out.println(Integer.toBinaryString(i));
//
//        System.out.println(Integer.toBinaryString(i >> 1));

        System.out.println(i = i - ((i >> 1) & 0x55555555));


    }

    public static void main(String[] args) {

        int k = 13;

        String binaryK = Integer.toBinaryString(k);

        int count = doSwar(k);

        System.out.println(binaryK);
        System.out.println(count);

//        analysis();

    }


}
