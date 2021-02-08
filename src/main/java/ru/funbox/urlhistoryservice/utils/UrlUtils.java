package ru.funbox.urlhistoryservice.utils;

public class UrlUtils {

    public boolean isUrlLink(String s) {
        //todo isUrlLink checking
        return true;
    }

    /**
     * template
        foo://userinfo@example.com:8042/over/there?name=ferret#nose

     * examples
      ftp://ftp.is.co.za/rfc/rfc1808.txt

      http://www.ietf.org/rfc/rfc2396.txt

     * not support
      ldap://[2001:db8::7]/c=GB?objectClass?one

      mailto:John.Doe@example.com

      news:comp.infosystems.www.servers.unix

      tel:+1-816-555-1212

      telnet://192.0.2.16:80/

      urn:oasis:names:specification:docbook:dtd:xml:4.1.2
     */
    public String extractDomain(String link) {
        String regExSchema = "^.*://";
        String regExUserInfo = ".*@";
        String regExPostfix = "[?#/:].*";

        return link.replaceFirst(regExSchema, "").replaceFirst(regExUserInfo, "").replaceFirst(regExPostfix, "");
    }
}
