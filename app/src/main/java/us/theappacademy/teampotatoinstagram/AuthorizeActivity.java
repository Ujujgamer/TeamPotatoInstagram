package us.theappacademy.teampotatoinstagram;

import android.app.Fragment;
import android.os.Bundle;

import us.theappacademy.oauth.view.OAuthActivity;

public class AuthorizeActivity extends OAuthActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
    }

    @Override
    public void setLayoutView() {

    }

    @Override
    public void replaceCurrentFragment(Fragment newFragment, boolean addToStack) {

    }

    @Override
    protected Fragment createFragment() {
        return null;
    }
}
