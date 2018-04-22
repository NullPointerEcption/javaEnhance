package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLMatchUtil {

    private static final String URL_MATCH_PATTERN="/(.+?)/(.+?)\\.do";

    public static String resolveURL(String url){
        Pattern compile = Pattern.compile(URL_MATCH_PATTERN);
        Matcher matcher = compile.matcher(url);
        if(!matcher.find()){
            return null;
        }
        String controllerName = matcher.group(1);
        String methodName = matcher.group(2);
        return controllerName+"#"+methodName;
    }
}
