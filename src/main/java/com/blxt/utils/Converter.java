package com.blxt.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/***
 * 快速转换工具
 */
public class Converter {


    /**
     * byte[] 转 char[]
     * @param bytes
     * @param code    编码,如"UTF-8"
     * @return
     */
    public static char[] toChars(byte[] bytes, String code) {
        Charset cs = Charset.forName(code);
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    /**
     * byte[] 转 string
     * @param bytes
     * @param code   编码,如"UTF-8"
     * @return
     */
    public static String toStr(byte[] bytes, String code) {
        char[] a = toChars(bytes, code);
        return new String(a);
    }

    /**
     * byte[] 格式化成 16进制 字符串
     * @param src
     * @param format  格式, 如 "%x "
     * @return
     */
    public static String toStrFormat(byte[] src, String format) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            stringBuilder.append(String.format(format, new Object[]{Byte.valueOf(src[i])}));
        }

        return stringBuilder.toString();
    }

    /**
     * int[] 格式化成 字符串
     * @param src
     * @param format 格式, 如 "%x "
     * @return
     */
    public static String toStrFormat(int[] src, String format) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            stringBuilder.append(String.format(format, new Object[]{Integer.valueOf(src[i])}));
        }

        return stringBuilder.toString();
    }

    public static short[] toShorts(String licString) {
        if (licString == null || licString.equals("")) {
            return null;
        }
        licString = licString.toUpperCase();
        int length = licString.length();
        char[] hexChars = licString.toCharArray();
        short[] date = new short[length];
        for (int i = 0; i < length; i++) {
            date[i] = (short) hexChars[i];
        }
        return date;
    }

    public static byte[] toBytes(char[] chars, String code) {
        Charset cs = Charset.forName(code);
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        byte[] bytes = bb.array();

        int i = bytes.length;
        for (; i > 1 && bytes[i - 1] == 0; i--) ;
        byte[] bytes_new = new byte[i];
        System.arraycopy(bytes, 0, bytes_new, 0, i);
        return bytes_new;
    }

    /**
     * 16进制字符串转 byte
     * 如 A1 B3 FE 转成 byte[]
     * @param content  数据
     * @param split    数据分隔符, 如' '或'0x'
     * @return
     */
    public static byte[] toBytesByFormat(String content, String split) {
        String[] beans = content.split(split);
        byte[] byteNum = new byte[beans.length];

        int index = 0;
        for (String bean : beans) {
            if (bean != null) {

                bean = bean.trim();
                if (bean.length() > 0) {
                    byteNum[index++] = Byte.parseByte(bean, 0x10);
                }
            }
        }
        return byteNum;
    }

    /**
     * long 转 bytes
     * @param num
     * @return
     */
    public static byte[] toBytes(long num) {
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ix++) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) (int) (num >> offset & 0xFFL);
        }
        return byteNum;
    }

    /**
     * int 转 byte
     * @param num
     * @return
     */
    public static byte toByte(int num) {
        return (byte) (num & 0xFF);
    }

    /**
     * byte 转 long
     * @param byteNum
     * @return
     */
    public static long toLong(byte[] byteNum) {
        long num = 0L;
        for (int ix = 0; ix < 8; ix++) {
            num <<= 8L;
            num |= (byteNum[ix] & 0xFF);
        }
        return num;
    }


    /**
     * byte 转int
     * @param byteNum
     * @return
     */
    public static int toInt(byte byteNum) {
        return (byteNum > 0) ? byteNum : (0x80 + 0x80 + byteNum);
    }

    /**
     * 获取当前格式化时间
     * @param format
     * @return
     */
    public static String getTimeStr(String format) {
        Date currentTime = new Date();
        return getTimeStr(currentTime, format);
    }

    /**
     * 获取格式化时间
     * @param currentTime
     * @param format
     * @return
     */
    public static String getTimeStr(Date currentTime, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取文件名和文件后缀
     * @param file
     * @return   [0] 文件名; [1] 文件后缀
     */
    public static String[] getFileDisassemble(String file){
        int sum = file.lastIndexOf(".");
        return new String[]{
                file.substring(0, sum),
                file.substring(sum + 1)
        };
    }

    /**
     * 转map
     * @param object
     * @return
     */
    public static Map<String, Object> toMap(JSONObject object) {
        //map对象
        Map<String, Object> map = new HashMap<>();
        //循环转换
        Iterator it = object.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }


    /**
     * url中的参数转json
     * 如 <code>String paramStr = "a=a1&b=b1&c=c1";</code>
     *
     * @param paramStr
     * @return
     */
    public static JSONObject urlParam2Json(String paramStr) {

        String[] params = paramStr.split("&");
        JSONObject obj = new JSONObject();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                try {
                    obj.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
 