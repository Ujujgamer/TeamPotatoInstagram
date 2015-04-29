package us.theappacademy.teampotatoinstagram;

import android.app.Fragment;
import android.os.Bundle;

import us.theappacademy.oauth.OAuthParameters;
import us.theappacademy.oauth.util.UrlBuilder;
import us.theappacademy.oauth.view.AuthorizeFragment;
import us.theappacademy.oauth.view.OAuthActivity;

public class AuthorizeActivity extends OAuthActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //create an Instagram Connection
        oauthConnection= new InstagramConnection();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayoutView() {

    }

    @Override
    public void replaceCurrentFragment(Fragment newFragment, boolean addToStack) {

    }

    //contain multiple fragments for the activity
    @Override
    protected Fragment createFragment() {
        //provide a way to log in to Instagram on app
        AuthorizeFragment authorizeFragment= new AuthorizeFragment();

        //in order to make connection to API, check its parameter
        OAuthParameters oAuthParameters= new OAuthParameters();
        oAuthParameters.addParameter("client_id", oauthConnection.getClientID());
        oAuthParameters.addParameter("redirect_uri", oauthConnection.getRedirectUrl());
        oAuthParameters.addParameter("response_type", "code");
        oAuthParameters.addParameter("state", UrlBuilder.generateUniqueState(16));

        //check the state and store
        oauthConnection.state= oAuthParameters.getValueFromParameter("state");

        //get storage from legit user
        authorizeFragment.setOAuthParameters(oAuthParameters);
        return authorizeFragment;
    }
}
