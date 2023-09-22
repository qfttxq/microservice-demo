package com.example.gatewayservice.util;

import com.example.gatewayservice.common.Result;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.json.JsonUtil;
import org.jose4j.jwa.AlgorithmConstraints;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import javax.crypto.spec.SecretKeySpec;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * 不带2的是RSA-SHA256非对称加密
 * 带2的是HMAC-SHA256加密算法
 */
@Slf4j
public  class JwtUtils {

    private static final String SECRET = "abcdefsfsadfsadfsadfasdfsdfasdfadsfasdfafadsfadfasdfdsadsfasdfasdfasdfaf";
    @Getter
    private static String pubKey;
    private static String priKey;
    private static String keyId = "microservice-authServer";
    static  {
        RsaJsonWebKey rsaJsonWebKey = null;
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
            rsaJsonWebKey.setKeyId(keyId);
            pubKey = rsaJsonWebKey.toJson(JsonWebKey.OutputControlLevel.PUBLIC_ONLY);
            priKey = rsaJsonWebKey.toJson(JsonWebKey.OutputControlLevel.INCLUDE_PRIVATE);
        } catch (JoseException e) {
            log.error(e.getMessage(),e);
        }
    }

    public static String generateJwt(int timeout,String subject){
        JwtClaims claims = new JwtClaims();
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();

        NumericDate date = NumericDate.now();
        date.addSeconds(timeout*60);
        claims.setExpirationTime(date);
        claims.setNotBeforeMinutesInThePast(1);
        claims.setSubject(subject);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        jws.setKeyIdHeaderValue(keyId);
        jws.setPayload(claims.toJson());
        PrivateKey privateKey = null;
        try {
            privateKey = new RsaJsonWebKey(JsonUtil.parseJson(priKey)).getPrivateKey();
            jws.setKey(privateKey);
            String jwtResult = jws.getCompactSerialization();
            return jwtResult;
        } catch (JoseException e) {
            log.error(e.getMessage(),e);
        }
        return  null;
    }

    public static String generateJwt2(int timeout,String subject){
        JwtClaims claims = new JwtClaims();
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();

        NumericDate date = NumericDate.now();
        date.addSeconds(timeout*60);
        claims.setExpirationTime(date);
        claims.setNotBeforeMinutesInThePast(1);
        claims.setSubject(subject);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        jws.setKey(new SecretKeySpec(SECRET.getBytes(),AlgorithmIdentifiers.HMAC_SHA256));

        jws.setPayload(claims.toJson());

        try {

            String jwtResult = jws.getCompactSerialization();
            return jwtResult;
        } catch (JoseException e) {
            log.error(e.getMessage(),e);
        }
        return  null;
    }

    public static Result verify(String jwt){
        PublicKey publicKey = null;
        try {
            publicKey = new RsaJsonWebKey(JsonUtil.parseJson(pubKey)).getPublicKey();
            JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
                    .setVerificationKey(publicKey)
                    .setJwsAlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.RSA_USING_SHA256)
                    .build();
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            return Result.build(200,"token正确",jwtClaims.toJson());
        } catch (JoseException e) {
            log.error(e.getMessage(),e);
        } catch (InvalidJwtException e) {
            if (e.hasExpired()) {
               return Result.build(1000,"token过期了");
            }
        }
        return Result.build(1001,"无效的token");

    }

    public static Result verify2(String jwt){
        try {
            JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime()
                    .setVerificationKey(new SecretKeySpec(SECRET.getBytes(),AlgorithmIdentifiers.HMAC_SHA256))
                    .setJwsAlgorithmConstraints(AlgorithmConstraints.ConstraintType.PERMIT, AlgorithmIdentifiers.HMAC_SHA256)
                    .build();
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            return Result.build(200,"token正确",jwtClaims.toJson());
        } catch (InvalidJwtException e) {
            if (e.hasExpired()) {
                return Result.build(1000,"token过期了");
            }
        }
        return Result.build(1001,"无效的token");

    }

    public static void main(String[] args) {
//        String jwt = JwtUtils.generateJwt(1, "admin");
//        log.info("jwt is: {}",jwt);
//
//
//        Result verify = JwtUtils.verify(jwt);
//        System.out.println("verify = " + verify);


        String jwt = JwtUtils.generateJwt2(30, "admin");
        log.info("jwt:{}",jwt);

        Result result = JwtUtils.verify2(jwt);
        log.info("result:{}",result);

        byte[] encode = Base64.getEncoder().encode(SECRET.getBytes());
        log.info("base64 code:{}",new String(encode));
    }
}
