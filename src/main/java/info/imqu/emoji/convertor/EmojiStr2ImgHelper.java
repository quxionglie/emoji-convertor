package info.imqu.emoji.convertor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将含有emoji中文表情的字符串转的是成图片¬
 */
public class EmojiStr2ImgHelper {
    private static Map<String, String> cnKey2ImgMap = new HashMap<String, String>();

    static {
        List<Emoji> emojiList = EmojiData.getEmojiList();
        for (Emoji emoji : emojiList) {
            String cn = emoji.getCnKey();
            String img = emoji.getImg();
            cnKey2ImgMap.put(cn, img);
        }
    }

    static class Pair {
        int left;
        int right;
        String s;

        public Pair(int left, int right, String s) {
            this.left = left;
            this.right = right;
            this.s = s;
        }
    }

    /**
     * 传入[xx],输出对应图片文件名
     *
     * @param cnKey 表情对应的字符串，如[怒骂]
     * @return 图片文件名, 如果没有找到返回null
     */
    static String cnKey2Img(String cnKey) {
        if (StringUtils.isBlank(cnKey)) {
            return null;
        }
        return cnKey2ImgMap.get(cnKey);
    }

    /**
     * @param s      需要转换成图片名的字符串
     * @param prefix 每一个匹配的表情的前辍
     * @param suffix 每一个匹配的表情的后辍
     * @return
     */
    static String str2Img(String s, String prefix, String suffix) {
        if (StringUtils.isBlank(s)) {
            return s;
        }

        List<Pair> pairList = findPairs(s);
        if (pairList == null) {
            return s;
        }
        if (prefix == null) {
            prefix = "";
        }

        if (suffix == null) {
            suffix = "";
        }
        return joinByPair(s, pairList, prefix, suffix);
    }

    private static List<Pair> findPairs(String s) {
        String regEx = "(\\[[^\\[]*?\\])";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(s);
        List<Pair> pairList = null;
        while (m.find()) {
            if (pairList == null) {
                pairList = new ArrayList<Pair>();
            }
            Pair pair = new Pair(m.start(), m.end(), m.group());
            pairList.add(pair);
        }
        return pairList;
    }


    private static String joinByPair(String s, List<Pair> pairList, String prefix, String suffix) {
        StringBuffer sBuffer = new StringBuffer();
        int left = 0;
        int right = 0;
        for (Pair pair : pairList) {
            left = pair.left;
            if (right < left) {
                sBuffer.append(s.substring(right, left));
            }
            String cn = pair.s;
            String imgName = cnKey2Img(cn);
            if (StringUtils.isNotBlank(imgName)) {
                sBuffer.append(prefix);
                sBuffer.append(imgName);
                sBuffer.append(suffix);
            } else {
                sBuffer.append(cn);
            }
            right = pair.right;
        }

        int length = s.length();
        sBuffer.append(s.substring(right, length));
        return sBuffer.toString();
    }
}
