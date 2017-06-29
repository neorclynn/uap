package neo.uap.controller;

import neo.uap.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {
    @Autowired
    private TokenStore tokenStore;

    @RequestMapping(value = "oauth/revoke-token", method = RequestMethod.POST)
    public ResponseEntity logout(@RequestHeader("Authorization") String authorization) {
        String tokenValue = authorization.replace("bearer", "").trim();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
        Preconditions.checkPrerequisite(accessToken != null);
        tokenStore.removeAccessToken(accessToken);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
