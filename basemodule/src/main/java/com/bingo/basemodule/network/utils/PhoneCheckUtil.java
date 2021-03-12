package com.bingo.basemodule.network.utils;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by francisbingo on 2019-06-03 10:51
 * 手机号校验工具类
 */

public class PhoneCheckUtil {
    public PhoneCheckUtil() {
    }

    /**
     * 内容是数字
     */
    private static String NUMBER_PATTERN = "[1][345789]\\d{9}";
    /**
     * 内容是字母
     */
    private static Pattern ENGLIST_PATTERN = Pattern.compile("[a-zA-Z]");
    /**
     * 内容是汉字
     */
    private static Pattern CHINESS_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");


    private Pattern mPattern = Pattern.compile("^1[0-9]{10}$");


    //密码长度8~16位,数字、字母、字符至少包含两种
    Pattern pswPattern = Pattern.compile("^[a-zA-Z0-9~!@#$%^&*()_+-=;':\",./<>?`]{8,16}$");// 数字，字母，特殊字符。
    Pattern letter = Pattern.compile("^[a-zA-Z]+$");
    Pattern number = Pattern.compile("^[0-9]+$");
    Pattern otherChar = Pattern.compile("^[~!@#$%^&*()_+\\-=;':\",./<>?`]+$");

    public static PhoneCheckUtil getInstance() {
        return PcHolder.instance;
    }

    private static class PcHolder {
        private static final PhoneCheckUtil instance = new PhoneCheckUtil();
    }

    /**
     * 判断手机格式是否正确
     *
     * @param phone
     * @return 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    // true 表示是正确的手机号 false手机号有问题
    public boolean CheckPhone(String phone) {
        return phone.matches(NUMBER_PATTERN);
    }


    public boolean CheckPwd(String pwd) {
        return pswPattern.matcher(pwd).matches()
                && !letter.matcher(pwd).matches()
                && !number.matcher(pwd).matches()
                && !otherChar.matcher(pwd).matches();
    }


    public void setProhibitEmoji(EditText et) {
        InputFilter[] filters = {getInputFilterProhibitEmoji(), getInputFilterProhibitSP()};
        et.setFilters(filters);
    }

    public InputFilter getInputFilterProhibitEmoji() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                StringBuffer buffer = new StringBuffer();
                for (int i = start; i < end; i++) {
                    char codePoint = source.charAt(i);
                    if (!getIsEmoji(codePoint)) {
                        buffer.append(codePoint);
                    } else {
                        i++;
                        continue;
                    }
                }
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(buffer);
                    TextUtils.copySpansFrom((Spanned) source, start, end, null,
                            sp, 0);
                    return sp;
                } else {
                    return buffer;
                }
            }
        };
        return filter;
    }


    public boolean getIsEmoji(char codePoint) {
        return (codePoint != 0x0) && (codePoint != 0x9) && (codePoint != 0xA)
                && (codePoint != 0xD)
                && ((codePoint < 0x20) || (codePoint > 0xD7FF))
                && ((codePoint < 0xE000) || (codePoint > 0xFFFD))
                && ((codePoint < 0x10000) || (codePoint > 0x10FFFF));
    }


    public InputFilter getInputFilterProhibitSP() {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                StringBuffer buffer = new StringBuffer();
                for (int i = start; i < end; i++) {
                    char codePoint = source.charAt(i);
                    if (!getIsSp(String.valueOf(codePoint))) {
                        buffer.append(codePoint);
                    } else {
                        i++;
                        continue;
                    }
                }
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(buffer);
                    TextUtils.copySpansFrom((Spanned) source, start, end, null,
                            sp, 0);
                    return sp;
                } else {
                    return buffer;
                }
            }
        };
        return filter;
    }

    /**
     * 可以使用常用字符
     */

    public boolean getIsSp(String str) {
        String limitEx = "[$%^&=|{}\\[\\]<>￥%……&|{}【】]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        return m.find();

    }

    /**
     *      * 计算中文字符
     *      *
     *      * @param sequence
     *      * @return
     *     
     */
    public int countChineseChar(CharSequence sequence) {

        if (TextUtils.isEmpty(sequence)) {
            return 0;
        }
        int charNum = 0;
        for (int i = 0; i < sequence.length(); i++) {
            char word = sequence.charAt(i);
            //中文
            if (isChineseChar(word)) {
                charNum++;
            }
        }
        return charNum;
    }

    /**
     *      * 判断是否是中文
     *      * @param c
     *      * @return
     *     
     */
    public static boolean isChineseChar(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    // 设置手机号隐藏
    public static String setPhone(String aa) {
        String s = aa.substring(0, 3) + " **** " + aa.substring(7, 11);
        return s;
    }
}