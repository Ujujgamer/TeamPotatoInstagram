package us.theappacademy.teampotatoinstagram;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import us.theappacademy.oauth.OAuthParameters;
import us.theappacademy.oauth.task.GetRequestTask;
import us.theappacademy.oauth.util.JsonBuilder;
import us.theappacademy.oauth.util.UrlBuilder;
import us.theappacademy.oauth.view.OAuthFragment;

public class ProfileFragment extends OAuthFragment{
    private TextView profileName;
    private TextView profileUsername;
    private TextView editText;
    private Button button;

    @Override
    //occur when wanting to send something to Instagram
    public void onTaskFinished(String responseString) {
        //check what the response is
        //build JSONObjects and set
            JSONObject jsonObject= JsonBuilder.jsonObjectFromString(responseString);
            setJsonObject(jsonObject);
        //gather all information within JSONObject and display
            try {
                profileName.setText(getJsonObject().getJSONObject("data").getString("full_name"));
                profileUsername.setText(getJsonObject().getJSONObject("data").getString("username"));
            }
            catch(JSONException error){
                Log.e("Profile Fragment", "JSONException: "+ error);
            }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create an OAuthParameter
        OAuthParameters oauthParameters= new OAuthParameters();

        //add a parameter
        oauthParameters.addParameter("access_token", getOAuthConnection().accessToken);

        //build a url
        String url= UrlBuilder.buildUrlWithParameters(getOAuthConnection().getApiUrl()+ "users/self",oauthParameters);

        //set url for an api call
        setUrlForApiCall(url);

        //get a request for task
        new GetRequestTask().execute(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate our own view, creating an XML-based layout
        View fragmentView= inflater.inflate(R.layout.fragment_profile, container, false);

        //get ids within XML layout and save in variable; profileName
        profileName= (TextView)fragmentView.findViewById(R.id.profileName);

        //get ids within XML layout and save in variable; profileUsername
        profileUsername= (TextView)fragmentView.findViewById(R.id.profileUsername);

        //get ids within XML layout and save in variable; editText
        editText= (TextView)fragmentView.findViewById(R.id.editText);

        if(getJsonObject() != null) {
            try {
                profileName.setText(getJsonObject().getJSONObject("data").getString("full_name"));
                profileUsername.setText(getJsonObject().getJSONObject("data").getString("username"));
            }
            catch(JSONException error){
                Log.e("Profile Fragment", "JSONException: "+ error);
            }
        }
        //get ids within XML layout and save in variable; button
        button= (Button)fragmentView.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentActivity().replaceCurrentFragment(PictureFragment.newInstance(editText.getText().toString()), true);
            }
        });

        return fragmentView;
    }

}

