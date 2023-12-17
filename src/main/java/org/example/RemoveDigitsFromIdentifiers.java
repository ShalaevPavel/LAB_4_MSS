package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveDigitsFromIdentifiers {
    public static void main(String[] args) {
        String code = "int number1 = 10; String text = \"number2\";";

        Pattern pattern = Pattern.compile("\\b\\w+\\b");
        Matcher matcher = pattern.matcher(code);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String identifier = matcher.group();
            String modifiedIdentifier = identifier.replaceAll("\\d", "");
            matcher.appendReplacement(sb, modifiedIdentifier);
        }
        matcher.appendTail(sb);

        System.out.println(sb.toString());
    }
}

