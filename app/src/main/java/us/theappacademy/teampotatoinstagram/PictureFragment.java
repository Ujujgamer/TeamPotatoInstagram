package us.theappacademy.teampotatoinstagram;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import us.theappacademy.oauth.OAuthParameters;
import us.theappacademy.oauth.task.GetRequestTask;
import us.theappacademy.oauth.util.JsonBuilder;
import us.theappacademy.oauth.util.UrlBuilder;
import us.theappacademy.oauth.view.OAuthFragment;

public class PictureFragment extends OAuthFragment{
    private TextView textView;
    private ImageView imageView;
    private ImageView imageView2;
    private Button button;

    public static String searchTag;
    public ArrayList<InstagramImage> profile;

    public PictureFragment(){
        profile=new ArrayList<InstagramImage>();
    }


    public static Fragment newInstance(String tag) {
        searchTag = tag;
        return new PictureFragment();
    }

    @Override
    //occur when wanting to send something to Instagram
    public void onTaskFinished(String responseString) {
        //check what the response is
        //build JSONObjects and set
        JSONObject jsonObject= JsonBuilder.jsonObjectFromString(responseString);
        setJsonObject(jsonObject);
        //gather all information within JSONObject and display
        try {
            JSONArray jsonArray = getJsonObject().getJSONArray("data");
            for(int index = 0; index < jsonArray.length(); index++) {
                JSONObject instagramImage = (JSONObject)jsonArray.get(index);
                if("image".equals(instagramImage.getString("type"))) {
                    profile.add(new InstagramImage(instagramImage.getJSONObject("images").getJSONObject("low_resolution").getString("url"), instagramImage.getString("id")));
                }
            }

        }
        catch(JSONException error){
            Log.e("Picture Fragment", "JSONException: " + error);
        }

        //imageView=profile.get(0).imageURL.;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //create an OAuthParameter
        OAuthParameters oauthParameters= new OAuthParameters();

        //add a parameter
        oauthParameters.addParameter("access_token", getOAuthConnection().accessToken);
        oauthParameters.addParameter("count", "10");

        //build a url
        String media= UrlBuilder.buildUrlWithParameters(getOAuthConnection().getApiUrl()+ "tags/" + searchTag + "/media/recent",oauthParameters);

        //set url for an api call
        setUrlForApiCall(media);

        //get a request for task
        new GetRequestTask().execute(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate our own view, creating an XML-based layout
        View fragmentView= inflater.inflate(R.layout.fragment_game, container, false);

        //get ids within XML layout and save in variable; textView
        textView= (TextView)fragmentView.findViewById(R.id.textView);

        //get ids within XML layout and save in variable; imageView
        imageView= (ImageView)fragmentView.findViewById(R.id.image1);

        //get ids within XML layout and save in variable; imageView2
        imageView2= (ImageView)fragmentView.findViewById(R.id.image2);

        //get ids within XML layout and save in variable; button
        button= (Button)fragmentView.findViewById(R.id.button);

        textView.setText(searchTag);
        return fragmentView;
    }
}
