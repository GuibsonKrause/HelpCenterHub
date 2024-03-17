package guibson.helpcenterhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import guibson.helpcenterhub.domain.usecase.ProcessUserLogin;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final ProcessUserLogin processUserLogin;

    @Autowired
    public CustomOAuth2UserService(ProcessUserLogin processUserLogin) {
        this.processUserLogin = processUserLogin;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        return processUserLogin.execute(oAuth2User);
    }
}
