package edwardbil_soundboard.gkain.edwardbil;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener, RewardedVideoAdListener {
    MediaPlayer mediaPlayer;
    Button randoms;
    Button admob;
    ImageView shareview;
    TextView header;
    Random random = new Random();
    int chiDa = 0;
    private RewardedVideoAd mRewardedVideoAd;

    //    private InterstitialAd mInterstitialAd;
    public static final String APP_PREFERENCES_CHIDA = "act";
    public static final String APP_PREFERENCES = "mysettings";
    private SharedPreferences mSettings;

    public MainFragment() {
        // Required empty public constructor
    }

    public void randomSound(int rand) {


        if (rand == 1) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tineti);
            mediaPlayer.start();
        }
        if (rand == 2) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tidaone);
            mediaPlayer.start();
        }
        if (rand == 3) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida);
            mediaPlayer.start();
        }
        if (rand == 4) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida2);
            mediaPlayer.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mRewardedVideoAd.pause(getActivity());
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_CHIDA, chiDa);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        mRewardedVideoAd.resume(getActivity());
        if (mSettings.contains(APP_PREFERENCES_CHIDA)) {
            chiDa = mSettings.getInt(APP_PREFERENCES_CHIDA, 0);

        }
        header.setText(" Edward Bill \n Чи да: " + chiDa +" coin");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544/5224354917");


        shareview = view.findViewById(R.id.shareview);
        header = view.findViewById(R.id.header);
        randoms = view.findViewById(R.id.randomss);
        admob = view.findViewById(R.id.admob);

        randoms.setOnClickListener(this);
        shareview.setOnClickListener(this);
        admob.setOnClickListener(this);



        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();

        return view;
    }

    @Override
    public void onClick(View view) {
        releaseMP();
        switch (view.getId()) {
            case R.id.shareview:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Чи да, скачивай в Google Play приложение - https://play.google.com/store/apps/details?id=com.edwardbil_prank.tidatopstyle ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Поделиться"));
                break;
            case R.id.randomss:
                int rnd = random.nextInt(4);
                randomSound(rnd);
                chiDa++;
                header.setText(" Edward Bill \n Чи да: " + chiDa +" coin");
                break;
            case R.id.admob:
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
                break;

        }

    }
    public void testForOpenButton(){
        if (chiDa >= 500){

            // set visibility
        }
    }




    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRewardedVideoAd.destroy(getActivity());
        releaseMP();
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        chiDa =chiDa+300;
        header.setText(" Edward Bill \n Чи да: " + chiDa +" coin");

        mSettings.edit().putInt(APP_PREFERENCES_CHIDA, chiDa).apply();

        Toast.makeText(getActivity(), "Начисленно 300 Чи Да coin. ", Toast.LENGTH_SHORT).show();
        // Reward the user.

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
