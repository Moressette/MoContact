package com.moressette.mocontact.util;

import java.io.UnsupportedEncodingException;

public class MyEncoding
{
    public static String doEncoding(String str) throws UnsupportedEncodingException
    {
       return new String(str.getBytes("ISO-8859-1"),"UTF-8");
    }
}
