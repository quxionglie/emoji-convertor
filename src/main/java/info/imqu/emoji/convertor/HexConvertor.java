package info.imqu.emoji.convertor;

public class HexConvertor {
    /**
     * @param hex
     * @return
     */
    public static byte[] hexStrToBytes(String hex) {
        hex = hex.toUpperCase();
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (hexChar2Byte(achar[pos]) << 4 | hexChar2Byte(achar[pos + 1]));
        }
        return result;
    }

    /**
     * @param c
     * @return
     */
    public static byte hexChar2Byte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }
}

