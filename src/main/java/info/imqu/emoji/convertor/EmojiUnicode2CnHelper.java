package info.imqu.emoji.convertor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 将emoji的unicode转化为中文
 */
public class EmojiUnicode2CnHelper {
    static class CharData {
        Map<Character, String> secondChars;
        String cn = null;

        boolean containsKey(Character key) {
            if (secondChars == null) {
                return false;
            }
            return secondChars.containsKey(key);
        }

        String get(Character key) {
            if (secondChars == null) {
                return null;
            }
            return secondChars.get(key);
        }

        void put(Character key, String value) {
            if (secondChars == null) {
                secondChars = new HashMap<Character, String>();
            }
            secondChars.put(key, value);
        }
    }

    private final static String EMPTY_STR = "";

    private static Map<Character, CharData> charMaps = new HashMap<Character, CharData>();
    private static Map<String, String> mutilCharsMap;

    static {
        List<Emoji> emojiList = EmojiData.getEmojiList();
        mutilCharsMap = new HashMap<String, String>();
        for (Emoji emj : emojiList) {
            String unicodeStr = emj.getUnicodeStr();
            String sbUnicode = emj.getSbUnicode();
            String cn = emj.getCnKey();

            if (StringUtils.isNotBlank(sbUnicode)) {
                initCharsMap(emj.getSbUnicodeStr(), cn);
            }
            if (unicodeStr.length() > 2) {
                mutilCharsMap.put(unicodeStr, cn);
            } else {
                initCharsMap(unicodeStr, cn);
            }
        }
    }

    static String replace2cn(String s) {
        return replace(s, false);
    }

    /**
     * 删除字符串中的emoji表情
     *
     * @param s
     * @return
     */
    static String replace2empty(String s) {
        return replace(s, true);
    }

    /**
     * 判断是否包含emoji表情
     *
     * @param s
     * @return
     */
    static boolean hasEmoji(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        return !s.equals(replace2empty(s));
    }


    static String replace(String s, boolean replace2null) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        //去除ios7中的FEOF,以兼容ios5,ios6
        s = s.replaceAll("\uFE0F", EmojiUnicode2CnHelper.EMPTY_STR);

        //替换两个以上utf16的字符
        s = replaceOfMutilChar(s, replace2null);

        //替换1个或2个utf16的字符,包括softbankUnicode
        s = replaceOf2Char(s, replace2null);

        //替换基本平面的私有域的字符
        s = s.replaceAll("[\uE000-\uF8FF]", EmojiUnicode2CnHelper.EMPTY_STR);
        return s;
    }


    private static void initCharsMap(String unicodeStr, String cn) {
        int length = unicodeStr.length();
        if (length == 1) {
            char c1 = unicodeStr.charAt(0);

            CharData charData = new CharData();
            charData.cn = cn;
            charMaps.put(c1, charData);
        } else if (length == 2) {
            char c1 = unicodeStr.charAt(0);
            char c2 = unicodeStr.charAt(1);

            CharData charData = null;
            if (charMaps.containsKey(c1)) {
                charData = charMaps.get(c1);
            } else {
                charData = new CharData();
            }
            charData.put(c2, cn);
            charMaps.put(c1, charData);
        }
    }


    private static String replaceOfMutilChar(String s, boolean replace2null) {
        if (mutilCharsMap.size() > 0) {  //替换多char的emoji
            Set<String> keySet = mutilCharsMap.keySet();
            for (String k : keySet) {
                String toStr = EmojiUnicode2CnHelper.EMPTY_STR;
                if (!replace2null) {
                    toStr = mutilCharsMap.get(k);
                }
                s = s.replaceAll(k, toStr);
            }
        }
        return s;
    }

    private static String replaceOf2Char(String s, boolean replace2null) {
        StringBuffer sBuffer = new StringBuffer();
        int length = s.length();
        int i = 0;
        while (i < length) {
            char c1 = s.charAt(i);
            if (charMaps.containsKey(c1)) {
                CharData charData = charMaps.get(c1);
                if (charData.cn != null) {
                    if (!replace2null) {
                        sBuffer.append(charData.cn);
                    }
                } else {//双char
                    if ((i + 1) < length) {
                        char c2 = s.charAt(i + 1);
                        if (charData.containsKey(c2)) {
                            if (!replace2null) {
                                String cn = charData.get(c2);
                                sBuffer.append(cn);
                            }
                            i = i + 1;
                        } else {
                            sBuffer.append(c1);
                        }
                    } else {
                        sBuffer.append(c1);
                    }
                }
            } else {
                sBuffer.append(c1);
            }
            i = i + 1;
        }
        return sBuffer.toString();
    }

}
