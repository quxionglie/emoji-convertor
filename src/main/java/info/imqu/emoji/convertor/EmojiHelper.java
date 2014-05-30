package info.imqu.emoji.convertor;

/**
 * emoji表情帮助类
 */
public class EmojiHelper {
    /**
     * 将emoji表情替换成[表情]的形式
     *
     * @param s
     * @return
     */
    public static String emoji2cn(String s) {
        return EmojiUnicode2CnHelper.replace2cn(s);
    }

    /**
     * 删除字符串中的emoji表情
     *
     * @param s
     * @return
     */
    public static String remove(String s) {
        return EmojiUnicode2CnHelper.replace2empty(s);
    }

    /**
     * 传入[xx],输出对应图片文件名
     *
     * @param cnKey 表情对应的字符串，如[怒骂]
     * @return 图片文件名, 如果没有找到返回null
     */
    public static String cn2Img(String cnKey) {
        return EmojiStr2ImgHelper.cnKey2Img(cnKey);
    }

    /**
     * 替换字符串的表情成图片，并在每个表情图片名前加prefix，后面suffix
     * 如： cn2ImgWrapper("[伤心]", "<img src=\"", "\"/>");
     * 输出：<img src="59.gif"/>
     *
     * @param str    原始字符串
     * @param prefix 每个表情前加的字符串,null相当于""
     * @param suffix 每个表情后加的字符串,null相当于""
     * @return
     */
    public static String cn2ImgWrapper(String str, String prefix, String suffix) {
        return EmojiStr2ImgHelper.str2Img(str, prefix, suffix);
    }
}
