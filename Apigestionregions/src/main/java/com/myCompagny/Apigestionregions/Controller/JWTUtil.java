package com.myCompagny.Apigestionregions.Controller;

public class JWTUtil {
    public static final String SECRET = "monSecretJeremi123";
    public static final String PREFIX_BEARER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 15*60*1000; // 15 minutes
    public static final long EXPIRE_REFRESH_TOKEN = 365L *24*60*60*1000; // 30 minutes
}
