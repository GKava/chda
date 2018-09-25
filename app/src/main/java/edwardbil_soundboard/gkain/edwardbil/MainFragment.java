package edwardbil_soundboard.gkain.edwardbil;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
    int eboInt = 0;
    private RewardedVideoAd mRewardedVideoAd;

    TextView soundtxt2,soundtxt3,soundtxt4,soundtxt5;
    Button soundbutton2,soundbutton3,soundbutton4,soundbutton5;
    private final int IDD_THREE_BUTTONS = 0;
    Context context;
    //    private InterstitialAd mInterstitialAd;
    public static final String APP_PREFERENCES_CHIDA = "act";
    public static final String APP_PREFERENCES = "mysettings";
    private SharedPreferences mSettings;
    AlertDialog.Builder ad;

    public MainFragment() {
        // Required empty public constructor
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
        header.setText(" Баланс \n ChiDaCoin: " + chiDa );
        testForOpenButton();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main,
                container, false);

        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        MobileAds.initialize(getActivity(), "ca-app-pub-1336421761813784/9903738272");


        shareview = view.findViewById(R.id.shareview);
        header = view.findViewById(R.id.header);
        randoms = view.findViewById(R.id.randomss);
        admob = view.findViewById(R.id.admob);

        soundtxt2 = view.findViewById(R.id.soundtxt2);
        soundtxt3 = view.findViewById(R.id.soundtxt3);
        soundtxt4 = view.findViewById(R.id.soundtxt4);
        soundtxt5 = view.findViewById(R.id.soundtxt5);

        soundbutton2 = view.findViewById(R.id.soundbutton2);
        soundbutton3 = view.findViewById(R.id.soundbutton3);
        soundbutton4 = view.findViewById(R.id.soundbutton4);
        soundbutton5 = view.findViewById(R.id.soundbutton5);


        randoms.setOnClickListener(this);
        shareview.setOnClickListener(this);
        admob.setOnClickListener(this);

        soundbutton2.setOnClickListener(this);
        soundbutton3.setOnClickListener(this);
        soundbutton4.setOnClickListener(this);
        soundbutton5.setOnClickListener(this);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
        testForOpenButton();


        return view;
    }

    private void createTwoButtonsAlertDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("Нет",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.setPositiveButton("ПОЛУЧИТЬ 500 ChiDaCoins!",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                                        if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                } else {
                    Toast toast = Toast.makeText(getActivity(),
                            "Включите интернет, чтобы просмотреть видео и получить 500 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                    }
                });
        builder.show();
    }

    // вспомогательный метод для вывода всплывающих сообщений
    private void showMessage(String textInMessage) {
        Toast.makeText(getActivity(), textInMessage, Toast.LENGTH_LONG).show();
    }

public void incrementRegreshTxt(){
    chiDa=chiDa+1;
    header.setText(" Баланс \n ChiDaCoin: " + chiDa );
}
    @Override
    public void onClick(View view) {
      //  releaseMP();
        switch (view.getId()) {
            case R.id.shareview:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Чи да, скачивай приложение в Google Play  - https://play.google.com/store/apps/details?id=com.edwardbil_prank.tidatopstyle ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Поделиться"));
                break;
            case R.id.randomss:
                releaseMP();
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.chidatop);
                mediaPlayer.start();
                incrementRegreshTxt();
                testForOpenButton();
                break;
            case R.id.admob:
                createTwoButtonsAlertDialog("Реклама","Просмотреть видео и получить 500 ChiDaCoins ");
                break;
            case R.id.soundbutton2:
                if (chiDa>=500){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tineti);
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Что бы открыть этот звук, наберите 500 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                break;
            case R.id.soundbutton3:
                if (chiDa>=1000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida3 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Что бы открыть этот звук, наберите 1000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton4:
                if (chiDa>=2500){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.chidarobot );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Что бы открыть этот звук, наберите 2500 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton5:
                if (chiDa>=5000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.neti );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Что бы открыть этот звук, наберите 5000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;

        }

    }
    public void tiEbanyti(){
        eboInt++;
        if (eboInt ==7){
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.otvetmonkey );
            mediaPlayer.start();
        }
        if (eboInt ==30){
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.otvetmonkey );
            mediaPlayer.start();
        }
        if (eboInt ==50){
            mediaPlayer = MediaPlayer.create(getActivity(), R.raw.otvetmonkey );
            mediaPlayer.start();
        }
    }



    public void testForOpenButton(){

        if (chiDa >= 500){
            soundtxt2.setText("Ти или не ти?");
        }
        if (chiDa >= 1000){
            soundtxt3.setText("Chi da?");

        }
        if (chiDa >= 2500){
            soundtxt4.setText("Чи да robot");

        }
        if (chiDa >= 5000){
            soundtxt5.setText("Не ти");

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
        mRewardedVideoAd.loadAd("ca-app-pub-1336421761813784/9903738272",
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
        chiDa =chiDa+500;
        header.setText(" Баланс \n ChiDaCoin: " + chiDa );

        mSettings.edit().putInt(APP_PREFERENCES_CHIDA, chiDa).apply();

        Toast.makeText(getActivity(), "Начисленно 500 ChiDaCoins. ", Toast.LENGTH_SHORT).show();
        testForOpenButton();
        // Reward the user.

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
