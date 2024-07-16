package it.trefin.erecruitment.key;

import java.security.Key;
import java.util.Base64;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class KeyGenerator {
	public static void main(String[] args) {
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
		String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
		System.out.println(base64Key);
	}
}
