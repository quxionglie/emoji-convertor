package info.imqu.emoji.convertor;

import java.io.UnsupportedEncodingException;

public class Emoji {
    private String unicode = "";
    private String utf8 = "";
    private String utf16 = "";
    private String sbUnicode = "";
    private String faces = "";
    private String name = "";     //
    private String shortName = "";
    private String cn = "";       //中文含义

    public String getUnicode() {
        return unicode;
    }

    public String getUnicodeStr() {
        String tmpStr = utf16.toUpperCase().replaceAll("(U\\+| )", "");
        byte[] bytes = HexConvertor.hexStrToBytes(tmpStr);
        try {
            return new String(bytes, "utf-16");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }


    public void setUnicode(String unicode) {
        this.unicode = unicode.trim();
    }

    public String getUtf8() {
        return utf8;
    }

    public byte[] getUtf8ToBytes() throws UnsupportedEncodingException {
        return HexConvertor.hexStrToBytes(utf8.replaceAll(" ", ""));
    }

    public String getUtf8ToStr() throws UnsupportedEncodingException {
        if (utf8 == null) {
            return null;
        }
        return new String(getUtf8ToBytes(), "utf-8");
    }

    public void setUtf8(String utf8) {
        this.utf8 = utf8;
    }

    public String getUtf16() {
        return utf16;
    }

    public void setUtf16(String utf16) {
        this.utf16 = utf16;
    }

    public String getSbUnicode() {
        return sbUnicode;
    }

    public void setSbUnicode(String sbUnicode) {
        this.sbUnicode = sbUnicode;
    }

    public String getSbUnicodeStr() {
        String tmpStr = sbUnicode.toUpperCase().replaceAll("(U\\+| )", "");
        byte[] bytes = HexConvertor.hexStrToBytes(tmpStr);
        try {
            return new String(bytes, "utf-16");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public String getFaces() {
        return faces;
    }

    public void setFaces(String faces) {
        this.faces = faces;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCn() {
        return cn;
    }

    public String getCnKey() {
        return "[" + this.cn + "]";
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getImg() {
        String tmpStr = unicode.toLowerCase().replaceAll("u\\+", "").replaceAll(" ", "-");
        return tmpStr + ".png";
    }

    @Override
    public String toString() {
        return "Emoji{" +
                "unicode='" + unicode + '\'' +
                ", utf8='" + utf8 + '\'' +
                ", utf16='" + utf16 + '\'' +
                ", sbUnicode='" + sbUnicode + '\'' +
                ", faces='" + faces + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", cn='" + cn + '\'' +
                '}';
    }

}
