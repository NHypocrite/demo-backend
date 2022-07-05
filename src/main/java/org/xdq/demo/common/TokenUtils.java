package org.xdq.demo.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.util.StringUtils;

import java.util.Date;

public class TokenUtils {


    private static final String CLARM_NAME_USERID = "CLARM_NAME_USERID";
    private static final String CLARM_NAME_USERNAME = "CLARM_NAME_USERNAME";
    private static final String SECRET = "asdfgh";

    private static int expiredTime = 200;//令牌过期时间，单位s

    /**
     * 将用户信息封装为一个Token（令牌字符串）
     * @param tokenUser
     * @return
     */
    public static String loginSign(TokenUser tokenUser){

        //创建一个加密算法对象,SECRET：密钥
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        String token = JWT.create()
                .withClaim(CLARM_NAME_USERID,tokenUser.getUserId())//设置令牌需要携带的信息
                .withClaim(CLARM_NAME_USERNAME, tokenUser.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiredTime*1000))//设置令牌的过期时间
                .sign(algorithm);

        return token;
    }

    public static TokenUser getTokenUser(String clientToken){

        if(!StringUtils.hasText(clientToken)){//令牌为空
            throw new TokenException("令牌为空");
        }
        //获得解码后的令牌
        DecodedJWT decodedJWT = null;
        try {
            decodedJWT =  JWT.decode(clientToken);
        } catch (JWTDecodeException e) {
            throw new TokenException("令牌错误");
        }

        String userId = decodedJWT.getClaim(CLARM_NAME_USERID).asString();
        String userName = decodedJWT.getClaim(CLARM_NAME_USERNAME).asString();

        if(!StringUtils.hasText(userId) || !StringUtils.hasText(userName)){
            throw new TokenException("令牌缺失用户信息");
        }

        return new TokenUser(userId,userName);

    }


    public static TokenUser verify(String clientToken){
        TokenUser tokenUser = getTokenUser(clientToken);

        //获取令牌的验证器
        JWTVerifier verifier =  JWT.require(Algorithm.HMAC256(SECRET)).build();

        try {
            verifier.verify(clientToken);
            return tokenUser;
        } catch (TokenExpiredException e) {
            throw new TokenException("令牌过期");
        } catch(Exception e){
            throw new TokenException("非法令牌");
        }

    }



}
