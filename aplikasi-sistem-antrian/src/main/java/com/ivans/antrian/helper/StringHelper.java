/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivans.antrian.helper;

import com.ivans.antrian.exception.AntrianServerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.springframework.util.StringUtils;

/**
 *
 * @author ivans
 */
public class StringHelper {
    
    public static String tokenizerGetLastToken(String filename, String token) {
        StringTokenizer tokenizer = new StringTokenizer(filename, token);
        String result = "";
        while (tokenizer.hasMoreTokens()) {
            result = tokenizer.nextToken();
        }
        return result;
    }
    
    public static String tokenizerGetLastElement(String filename, String token) {
        StringTokenizer tokenizer = new StringTokenizer(filename, token);
        String result = "";
        while (tokenizer.hasMoreElements()) {
            result = tokenizer.nextElement().toString();
        }
        return result;
    }
    
    public static Boolean stringToBoolean(String value){
        if(!StringUtils.hasText(value)) return false;
        
        if(value.equalsIgnoreCase("YES")){
            return true;
        } else if(value.equalsIgnoreCase("NO")){
            return false;
        } else {
            return new Boolean(value);
        }
    }
    
    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getInstance().format(n);
    }
    
    public static List<String> readLineStream(InputStream inputStream) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        List<String> result = new ArrayList<String>();
        String data;
        reader.readLine();//skip satu baris
        while((data=reader.readLine()) != null){
            result.add(data);
        }
        return result;
    }
    
    public static void validateRow(String[] data, Integer line, Integer columns){
        if(data.length < columns){
            throw new AntrianServerException("Invalid data length in line " + line);
        }
    }
    
}
