package accountservice.accountservice.client;

import feign.RequestInterceptor;
import org.springframework.cloud.openfeign.security.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NotificationFeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), resources());
    }

    private OAuth2ProtectedResourceDetails resources() {
        final ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        details.setAccessTokenUri("http://localhost:9000/oauth/token");
        details.setClientId("accountservice");
        details.setClientSecret("123");
        details.setScope(Arrays.asList("notification", "log"));
        return details;
    }

}
