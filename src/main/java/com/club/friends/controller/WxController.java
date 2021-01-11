
package com.club.friends.controller;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/wx")
@RestController
public class WxController {

    @RequestMapping(value = "/oauth")
    public String index(String signature ,String timestamp,String nonce,String echostr){
        try {
            System.out.println(signature + " || " + timestamp + " || " + nonce + " || " + echostr);
            String[] arr = new String[]{"huroro", nonce, echostr};
            String tmpStr = null;
            // 2. 将三个参数字符串
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                content.append(arr[i]);
            }

            //sha1加密
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);

            System.out.println("tmpStr : " + tmpStr);

            // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
            if(tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false){
                return echostr;
            }else{
                return "false";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
    }

    private static String byteToStr(byte[] byteArray) {
        StringBuilder strDigest = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            strDigest.append(byteToHexStr(byteArray[i]));
        }
        return strDigest.toString();
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    private static String byteToStr2(byte[] byteArray) {
        return byteToHexStr(byteArray);
    }

    private static String byteToHexStr(byte[] data) {

        char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5',
                '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_UPPER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_UPPER[0x0F & data[i]];
        }
        return new String(out);
    }

    public static void main(String[] args) {
        try {

            String[] arr = new String[]{"huroro", "387928845", "8179048880720050763"};
            String tmpStr = null;
            // 2. 将三个参数字符串
            StringBuilder content = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                content.append(arr[i]);
            }

            System.out.println(content.toString());

            //sha1加密
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
            MessageDigest md = MessageDigest.getInstance("SHA-1");

            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr2(digest);

            System.out.println("tmpStr : " + tmpStr);
            String signature = "b7d7b7dcf1f546cf825a8e1fd1742ebcbc983ecf";
            signature = signature.toUpperCase();
            System.out.println("signature : " + signature);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

