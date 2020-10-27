package com.leyou.auth.test;

import com.leyou.auth.enity.UserInfo;
import com.leyou.auth.utils.JwtUtils;
import com.leyou.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {
    private static final String pubKeyPath = "C:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "C:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZC6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU2NTIzNjgyOX0.b9HrggFEeZEQX4zgRdUnOSix_WXAO3EpIV3jyTdejdhreGsUp8u7ea_zYncDzGlj6SLQJbh9zZtV7bCMC4Fy8G5NEvzQ8l0kh6J379tF03M_gEjR0ZET1pFgZ7wZPiLe-h6fyj1PgL48bfqZZ-EoMESzAgFP3lo-Ky6HCYdbu4w";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
        System.out.println(user);
    }
}
