package com.iavtar.tradeviewservice.security;


/**
 * @author indra singh
 * */
public class SecurityConstants {

	public static final String SIGN_UP_URLS = "/api/users/**";
	public static final String SECRET = "SecretKeyToGenerateJWTs";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final long EXPIRATION_TIME = 172800000; //2 Days
}
