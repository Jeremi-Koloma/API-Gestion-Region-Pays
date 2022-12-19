package com.myCompagny.Apigestionregions.Controller;

public class JWTUtil {
    public static final String SECRET = "monSecretJeremi123";
    public static final String PREFIX_BEARER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final long EXPIRE_ACCESS_TOKEN = 2*60*1000; // 2 minutes
    public static final long EXPIRE_REFRESH_TOKEN = 30*60*1000; // 30 minutes
}
