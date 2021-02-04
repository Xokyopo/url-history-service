package ru.funbox.urlhistoryservice.utils;

public class UrlUtils {

    public boolean isUrlLink(String s) {
        //todo isUrlLink checking
        return true;
    }

    public String extractDomain(String link) {
        String regExPrefix = "^.*://";
        String regExPostfix = "[\\p{Punct}&&[^.]].*";

        return link.replaceFirst(regExPrefix, "").replaceFirst(regExPostfix, "");
    }
}
