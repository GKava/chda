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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    MediaPlayer mediaPlayer;
    Button randoms;
    ImageView shareview;
    TextView header;
    Random random = new Random();
    int chiDa=0;


//    private InterstitialAd mInterstitialAd;
    public static final String APP_PREFERENCES_CHIDA = "act";
    public static final String APP_PREFERENCES = "mysettings";
    private SharedPreferences mSettings;
    public MainFragment() {
        // Required empty public constructor
    }
    public void randomSound (int rand){


        if (rand == 1){
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tineti);
            mediaPlayer.start();
        }if (rand == 2) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tidaone);
            mediaPlayer.start();
        }if (rand == 3) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida);
            mediaPlayer.start();
        }if (rand == 4) {
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida2);
            mediaPlayer.start();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_CHIDA, chiDa);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSettings.contains(APP_PREFERENCES_CHIDA)) {
            chiDa = mSettings.getInt(APP_PREFERENCES_CHIDA, 0);

        }
        header.setText(" Edward Bill \n Чи да: " + chiDa);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


//        MobileAds.initialize(getActivity(),
//                "ca-app-pub-1336421761813784~97891902231");
//
//        mInterstitialAd = new InterstitialAd(getActivity());
//        mInterstitialAd.setAdUnitId("ca-app-pub-1336421761813784/90296464741");
//        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        shareview = view.findViewById(R.id.shareview);
        header = view.findViewById(R.id.header);
        randoms = view.findViewById(R.id.randomss);


        randoms.setOnClickListener(this);
        shareview.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {

        releaseMP();

        switch (view.getId()){
            case R.id.shareview:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Чи да? Мой счёт " +chiDa+ " Скачивай в Google Play приложение (Чи да) ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Поделиться"));
                break;


            case R.id.randomss:
                int rnd = random.nextInt(4);
                randomSound(rnd);
                header.setText(" Edward Bill \n Чи да: " + chiDa);
                chiDa++;
                break;


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
        releaseMP();
    }
}
