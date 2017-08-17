package neo.uap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

public class CachedJdbcTokenStore extends JdbcTokenStore {
    public static final String CACHE_ACCESS_TOKEN = "accessToken";
    public static final String CACHE_USER_ACCESS_TOKEN_VALUE = "userAccessTokenValue";

    @Autowired
    private CacheManager cacheManager;

    public CachedJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        Cache accessTokenCache = cacheManager.getCache(CACHE_ACCESS_TOKEN);
        OAuth2AccessToken accessToken = accessTokenCache.get(tokenValue, OAuth2AccessToken.class);

        if (accessToken != null && !accessToken.isExpired()) {
            return accessToken;
        }

        return super.readAccessToken(tokenValue);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Cache accessTokenCache = cacheManager.getCache(CACHE_ACCESS_TOKEN);
        Cache userAccessTokenValueCache = cacheManager.getCache(CACHE_USER_ACCESS_TOKEN_VALUE);
        String username = authentication.getName();
        String existedTokenValue = userAccessTokenValueCache.get(username, String.class);
        String tokenValue = token.getValue();

        userAccessTokenValueCache.evict(username);
        userAccessTokenValueCache.put(username, tokenValue);

        if (existedTokenValue != null) {
            accessTokenCache.evict(existedTokenValue);
            accessTokenCache.put(tokenValue, token);
        }

        super.storeAccessToken(token, authentication);
    }
}
