package ru.funbox.urlhistoryservice.utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UrlUtilsTest {
    private UrlUtils urlUtils;

    @BeforeEach
    void init() {
        this.urlUtils = new UrlUtils();
    }

    /*
      ftp://ftp.is.co.za/rfc/rfc1808.txt

      http://www.ietf.org/rfc/rfc2396.txt

      ldap://[2001:db8::7]/c=GB?objectClass?one

      mailto:John.Doe@example.com

      news:comp.infosystems.www.servers.unix

      tel:+1-816-555-1212

      telnet://192.0.2.16:80/

      urn:oasis:names:specification:docbook:dtd:xml:4.1.2
     */

    //TODO need write method first
//    @Test
//    void isUrlLink_ShouldReturnTrue_WhenCheckValidUrlLink() {
//        List<String> validLinks = List.of("ya.ru");
//        validLinks.forEach(expected -> {
//            assertTrue(this.urlUtils.isUrlLink(expected));
//        });
//    }
//
//    @Test
//    void isUrlLink_ShouldReturnFalse_WhenCheckInvalidUrlLink() {
//
//    }

    @Test
    void extractDomain_ShouldReturnTrue_WhenURLHaveSchema() {
        String expected = "ya.ru";
        String actual = this.urlUtils.extractDomain("http://" + expected);
        assertEquals(expected, actual);
    }

    @Test
    void extractDomain_ShouldReturnTrue_WhenURLWithoutSchema() {
        String expected = "ya.ru";
        String actual = this.urlUtils.extractDomain(expected);
        assertEquals(expected, actual);
    }

    @Test
    void extractDomain_ShouldReturnTrue_WhenURLHavePort() {
        String expected = "ya.ru";
        String actual = this.urlUtils.extractDomain(expected + ":8080");
        assertEquals(expected, actual);
    }

    @Test
    void extractDomain_ShouldReturnTrue_WhenURLHaveUserInfo() {
        String expected = "ya.ru";
        String actual = this.urlUtils.extractDomain("user@" + expected);
        assertEquals(expected, actual);
    }

    @Test
    void extractDomain_ShouldReturnTrue_WhenURLIsIPAddress() {
        String expected = "12.1.3.2";
        String actual = this.urlUtils.extractDomain(expected);
        assertEquals(expected, actual);
    }

    @Test
    void extractDomain_ShouldReturnTrue_WhenURLHaveRussianLater() {
        String expected = "яндекс.рф";
        String actual = this.urlUtils.extractDomain(expected);
        assertEquals(expected, actual);
    }
}
