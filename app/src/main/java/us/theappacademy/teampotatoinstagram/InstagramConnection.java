package us.theappacademy.teampotatoinstagram;

import us.theappacademy.oauth.OAuthApplication;
import us.theappacademy.oauth.OAuthConnection;
import us.theappacademy.oauth.OAuthProvider;

public class InstagramConnection extends OAuthConnection{
    @Override
    //create path for authorization
    protected OAuthProvider createOAuthProvider() {
        return new OAuthProvider("https://api.instagram.com/v1/",
                "https://api.instagram.com/oauth/authorize",
                "https://api.instagram.com/oauth/access_token",
                "https://localhost");
    }

    @Override
    //create path to client id and client secret
    protected OAuthApplication createOAuthApplication() {
        return new OAuthApplication("af078197ca22405ab23546926fb3cf07", "362cdaf52e0f453b8c9bb699d94c2484");
    }
}
