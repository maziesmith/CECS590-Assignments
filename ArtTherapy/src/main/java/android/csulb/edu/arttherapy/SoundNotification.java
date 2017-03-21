package android.csulb.edu.arttherapy;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;

/**
 * Created by Sumeet on 3/19/2017.
 */

public class SoundNotification extends IntentService {

    private static final String INTENT_TAG = "SimpleIntentService";
    private MediaPlayer player;

    public SoundNotification(){
        super(INTENT_TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        player=MediaPlayer.create(this,R.raw.eraser);
        player.start();
    }
}
