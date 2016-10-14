package util;

public class MyMD5 {

    public static void main(String[] args) {
        MyMD5 md5 = new MyMD5();
        String encrypt = md5.digiest("123456");
        System.out.println(encrypt);
    }

    /**
     * 16进制字符表现方式
     */
    private static final String[] hexs = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    /**
     * 标准幻数
     */
    private static final long A = 0x67452301L;
    private static final long B = 0xefcdab89L;
    private static final long C = 0x98badcfeL;
    private static final long D = 0x10325476L;

    /**
     * 四轮运算位移数量
     */
    static final int S11 = 7;
    static final int S12 = 12;
    static final int S13 = 17;
    static final int S14 = 22;

    static final int S21 = 5;
    static final int S22 = 9;
    static final int S23 = 14;
    static final int S24 = 20;

    static final int S31 = 4;
    static final int S32 = 11;
    static final int S33 = 16;
    static final int S34 = 23;

    static final int S41 = 6;
    static final int S42 = 10;
    static final int S43 = 15;
    static final int S44 = 21;

    private static final long[] result = {A, B, C, D};

    private String digiest(String inputStr) {
        byte[] inputBytes = inputStr.getBytes();
        int inputByteLength = inputBytes.length;
        int intactGroupCount = inputByteLength / 64; //完整分组个数,每组512bit位，即64字节

        int[] groups = null;//共16组，每组32bit位,4字节

        //处理每一个完整组
        for (int i = 0; i < intactGroupCount; i++) {
            groups = divGroup(inputBytes, i * 64);
            trans(groups);
        }

        //处理尾巴
        int tailLength = inputByteLength%64;
        byte[] tempBytes = new byte[64];
        if (tailLength <= 56) {
            for (int i=0; i<tailLength; i++) {
                tempBytes[i] = inputBytes[inputByteLength - tailLength + i];
            }
            if (tailLength < 56) {
                tempBytes[tailLength] = (byte)(1<<7);
                for (int i=1; i<56 - tailLength; i++) {
                    tempBytes[tailLength + i] = 0;
                }
            }
            int len = inputByteLength<<3;
            for (int i=0; i<8; i++) {
                tempBytes[56 + i] = (byte)len;
                len>>=8;
            }
            groups = divGroup(tempBytes, 0);
            trans(groups);
            //将Hash值转换成十六进制的字符串
            String resStr="";
            long temp=0;
            for(int i=0;i<4;i++){
                for(int j=0;j<4;j++){
                    temp=result[i]&0x0FL;
                    String a=hexs[(int)(temp)];
                    result[i]=result[i]>>4;
                    temp=result[i]&0x0FL;
                    resStr+=hexs[(int)(temp)]+a;
                    result[i]=result[i]>>4;
                }
            }
            return resStr;
        }
        else {
            return "";
        }

    }

    /**
     * 讲传进来的64字节，
     */
    private static int[] divGroup(byte[] inputBytes, int index) {
        int[] tmp = new int[16];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = byteUnsignedIntValue(inputBytes[4 * i + index]) |
                    byteUnsignedIntValue(inputBytes[4 * i + index + 1]) << 8 |
                    byteUnsignedIntValue(inputBytes[4 * i + index + 2]) << 16 |
                    byteUnsignedIntValue(inputBytes[4 * i + index + 3]) << 24;
        }
        return tmp;
    }

    private static int byteUnsignedIntValue(byte value) {
        return value < 0 ? value & 0x7F + 128 : value;
    }

    /**
     * 主要操作，4轮循环
     */
    private static void trans(int[] group) {

        long a = result[0], b = result[1], c = result[2], d = result[3];

        //第一轮
        a = FF(a, b, c, d, group[0], 7, 0xd76aa478L);
        d = FF(d, a, b, c, group[1], 12, 0xe8c7b756L);
        c = FF(c, d, a, b, group[2], 17, 0x242070dbL);
        b = FF(b, c, d, a, group[3], 22, 0xc1bdceeeL);
        a = FF(a, b, c, d, group[4], 7, 0xf57c0fafL);
        d = FF(d, a, b, c, group[5], 12, 0x4787c62aL);
        c = FF(c, d, a, b, group[6], 17, 0xa8304613L);
        b = FF(b, c, d, a, group[7], 22, 0xfd469501L);
        a = FF(a, b, c, d, group[8], 7, 0x698098d8L);
        d = FF(d, a, b, c, group[9], 12, 0x8b44f7afL);
        c = FF(c, d, a, b, group[10], 17, 0xffff5bb1L);
        b = FF(b, c, d, a, group[11], 22, 0x895cd7beL);
        a = FF(a, b, c, d, group[12], 7, 0x6b901122L);
        d = FF(d, a, b, c, group[13], 12, 0xfd987193L);
        c = FF(c, d, a, b, group[14], 17, 0xa679438eL);
        b = FF(b, c, d, a, group[15], 22, 0x49b40821L);

//        第二轮
        a = GG(a, b, c, d, group[1], 5, 0xf61e2562L);
        d = GG(d, a, b, c, group[6], 9, 0xc040b340L);
        c = GG(c, d, a, b, group[11], 14, 0x265e5a51L);
        b = GG(b, c, d, a, group[0], 20, 0xe9b6c7aaL);
        a = GG(a, b, c, d, group[5], 5, 0xd62f105dL);
        d = GG(d, a, b, c, group[10], 9, 0x02441453L);
        c = GG(c, d, a, b, group[15], 14, 0xd8a1e681L);
        b = GG(b, c, d, a, group[4], 20, 0xe7d3fbc8L);
        a = GG(a, b, c, d, group[9], 5, 0x21e1cde6L);
        d = GG(d, a, b, c, group[14], 9, 0xc33707d6L);
        c = GG(c, d, a, b, group[3], 14, 0xf4d50d87L);
        b = GG(b, c, d, a, group[8], 20, 0x455a14edL);
        a = GG(a, b, c, d, group[13], 5, 0xa9e3e905L);
        d = GG(d, a, b, c, group[2], 9, 0xfcefa3f8L);
        c = GG(c, d, a, b, group[7], 14, 0x676f02d9L);
        b = GG(b, c, d, a, group[12], 20, 0x8d2a4c8aL);

//        第三轮
        a = HH(a, b, c, d, group[5], 4, 0xfffa3942L);
        d = HH(d, a, b, c, group[8], 11, 0x8771f681L);
        c = HH(c, d, a, b, group[11], 16, 0x6d9d6122L);
        b = HH(b, c, d, a, group[14], 23, 0xfde5380cL);
        a = HH(a, b, c, d, group[1], 4, 0xa4beea44L);
        d = HH(d, a, b, c, group[4], 11, 0x4bdecfa9L);
        c = HH(c, d, a, b, group[7], 16, 0xf6bb4b60L);
        b = HH(b, c, d, a, group[10], 23, 0xbebfbc70L);
        a = HH(a, b, c, d, group[13], 4, 0x289b7ec6L);
        d = HH(d, a, b, c, group[0], 11, 0xeaa127faL);
        c = HH(c, d, a, b, group[3], 16, 0xd4ef3085L);
        b = HH(b, c, d, a, group[6], 23, 0x04881d05L);
        a = HH(a, b, c, d, group[9], 4, 0xd9d4d039L);
        d = HH(d, a, b, c, group[12], 11, 0xe6db99e5L);
        c = HH(c, d, a, b, group[15], 16, 0x1fa27cf8L);
        b = HH(b, c, d, a, group[2], 23, 0xc4ac5665L);

//        第四轮
        a = II(a, b, c, d, group[0], 6, 0xf4292244L);
        d = II(d, a, b, c, group[7], 10, 0x432aff97L);
        c = II(c, d, a, b, group[14], 15, 0xab9423a7L);
        b = II(b, c, d, a, group[5], 21, 0xfc93a039L);
        a = II(a, b, c, d, group[12], 6, 0x655b59c3L);
        d = II(d, a, b, c, group[3], 10, 0x8f0ccc92L);
        c = II(c, d, a, b, group[10], 15, 0xffeff47dL);
        b = II(b, c, d, a, group[1], 21, 0x85845dd1L);
        a = II(a, b, c, d, group[8], 6, 0x6fa87e4fL);
        d = II(d, a, b, c, group[15], 10, 0xfe2ce6e0L);
        c = II(c, d, a, b, group[6], 15, 0xa3014314L);
        b = II(b, c, d, a, group[13], 21, 0x4e0811a1L);
        a = II(a, b, c, d, group[4], 6, 0xf7537e82L);
        d = II(d, a, b, c, group[11], 10, 0xbd3af235L);
        c = II(c, d, a, b, group[2], 15, 0x2ad7d2bbL);
        b = II(b, c, d, a, group[9], 21, 0xeb86d391L);

        result[0] += a;
        result[1] += b;
        result[2] += c;
        result[3] += d;
    }

    /**
     * FF(a,b,c,d,Mj,s,ti)表示a=b+((a+F(b,c,d)+Mj+ti)<<<s)
     * GG(a,b,c,d,Mj,s,ti)表示a=b+((a+G(b,c,d)+Mj+ti)<<<s)
     * HH(a,b,c,d,Mj,s,ti)表示a=b+((a+H(b,c,d)+Mj+ti)<<<s)
     * II(a,b,c,d,Mj,s,ti)表示a=b+((a+I(b,c,d)+Mj+ti)<<<s)
     */
    private static long FF(long a, long b, long c, long d, long mj, long s, long ti) {
        a += F(b, c, d) + mj + ti;
        a = ((a&0xFFFFFFFFL)<<s | (a&0xFFFFFFFFL)>>>(32-s));
        a += b;
        return a&0xFFFFFFFFL;
    }

    private static long GG(long a, long b, long c, long d, long mj, long s, long ti) {
        a += (G(b, c, d)&0xFFFFFFFFL) + mj + ti;
        a = ((a&0xFFFFFFFFL)<<s) | ((a&0xFFFFFFFFL)>>>(32-s));
        a += b;
        return a&0xFFFFFFFFL;
    }

    private static long HH(long a, long b, long c, long d, long mj, long s, long ti) {
        a += (H(b, c, d)&0xFFFFFFFFL) + mj + ti;
        a = ((a&0xFFFFFFFFL)<<s) | ((a&0xFFFFFFFFL)>>>(32-s));
        a += b;
        return a&0xFFFFFFFFL;

    }

    private static long II(long a, long b, long c, long d, long mj, long s, long ti) {
        a += (I(b, c, d)&0xFFFFFFFFL) + mj + ti;
        a = ((a&0xFFFFFFFFL)<<s) | ((a&0xFFFFFFFFL)>>>(32-s));
        a += b;
        return a&0xFFFFFFFFL;

    }
//E10ADC3949BA59ABBE56E057F20F883E
//E10ADC3949BA59ABBE56E057F20F883E

    /**
     * F(X,Y,Z)=(X&Y)|((~X)&Z)
     * G(X,Y,Z)=(X&Z)|(Y&(~Z))
     * H(X,Y,Z)=X^Y^Z
     * I(X,Y,Z)=Y^(X|(~Z))
     */
    private static long F(long x, long y, long z) {
        return (x & y) | ((~x) & z);
    }

    private static long G(long x, long y, long z) {
//        return (x & z) | (y & (~z));
        return (x & z) | (y & (~z));
    }

    private static long H(long x, long y, long z) {
        return x ^ y ^ z;
    }

    private static long I(long x, long y, long z) {
        return y ^ (x | (~z));
    }

}
