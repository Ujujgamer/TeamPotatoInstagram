package us.theappacademy.teampotatoinstagram;

import java.util.ArrayList;
import java.util.Random;

public class RandomPic {
    private ArrayList<InstagramImage> instagramImages;
    int max;
    int min;

    public RandomPic(ArrayList<InstagramImage> instagramImages) {
        this.instagramImages = instagramImages;
        min = 0;
        max = instagramImages.size();
    }

    public String getRandomImageUrl() {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return instagramImages.get(randomNum).imageURL;
    }
}