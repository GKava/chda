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
    ImageView shareview,helpview;
    TextView header;
    Random random = new Random();
    int chiDa = 0;
    int eboInt = 0;
    int chiDaIncrement=1;
    int checkpoint=0;
    private RewardedVideoAd mRewardedVideoAd;
    private InterstitialAd mInterstitialAd;
    TextView soundtxt2,soundtxt3,soundtxt4,soundtxt5,soundtxt6,soundtxt7,soundtxt8,soundtxt9,soundtxt10,soundtxt11;
    Button soundbutton2,soundbutton3,soundbutton4,soundbutton5,soundbutton6,soundbutton7,soundbutton8,soundbutton9,soundbutton10,soundbutton11;
    private final int IDD_THREE_BUTTONS = 0;
    Context context;
    //    private InterstitialAd mInterstitialAd;
    public static final String APP_PREFERENCES_CHIDA = "act";
    public static final String APP_PREFERENCES_CHECKPOINT = "checkpoint";
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
        editor.putInt(APP_PREFERENCES_CHECKPOINT, checkpoint);
        editor.apply();
    }

    @Override
    public void onResume() {
        super.onResume();
        mRewardedVideoAd.resume(getActivity());
        if (mSettings.contains(APP_PREFERENCES_CHIDA)) {
            chiDa = mSettings.getInt(APP_PREFERENCES_CHIDA, 0);
        }
        if (mSettings.contains(APP_PREFERENCES_CHECKPOINT)) {
            checkpoint = mSettings.getInt(APP_PREFERENCES_CHECKPOINT, 0);
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

        helpview = view.findViewById(R.id.helpview);
        shareview = view.findViewById(R.id.shareview);
        header = view.findViewById(R.id.header);
        randoms = view.findViewById(R.id.randomss);
        admob = view.findViewById(R.id.admob);

        soundtxt2 = view.findViewById(R.id.soundtxt2);
        soundtxt3 = view.findViewById(R.id.soundtxt3);
        soundtxt4 = view.findViewById(R.id.soundtxt4);
        soundtxt5 = view.findViewById(R.id.soundtxt5);
        soundtxt6 = view.findViewById(R.id.soundtxt6);
        soundtxt7 = view.findViewById(R.id.soundtxt7);
        soundtxt8 = view.findViewById(R.id.soundtxt8);
        soundtxt9 = view.findViewById(R.id.soundtxt9);
        soundtxt10 = view.findViewById(R.id.soundtxt10);
        soundtxt11 = view.findViewById(R.id.soundtxt11);



        soundbutton2 = view.findViewById(R.id.soundbutton2);
        soundbutton3 = view.findViewById(R.id.soundbutton3);
        soundbutton4 = view.findViewById(R.id.soundbutton4);
        soundbutton5 = view.findViewById(R.id.soundbutton5);
        soundbutton6 = view.findViewById(R.id.soundbutton6);
        soundbutton7 = view.findViewById(R.id.soundbutton7);
        soundbutton8 = view.findViewById(R.id.soundbutton8);
        soundbutton9 = view.findViewById(R.id.soundbutton9);
        soundbutton10 = view.findViewById(R.id.soundbutton10);
        soundbutton11 = view.findViewById(R.id.soundbutton11);


        randoms.setOnClickListener(this);
        shareview.setOnClickListener(this);
        admob.setOnClickListener(this);


        soundbutton2.setOnClickListener(this);
        soundbutton3.setOnClickListener(this);
        soundbutton4.setOnClickListener(this);
        soundbutton5.setOnClickListener(this);

        soundbutton6.setOnClickListener(this);
        soundbutton7.setOnClickListener(this);
        soundbutton8.setOnClickListener(this);
        soundbutton9.setOnClickListener(this);
        soundbutton10.setOnClickListener(this);
        soundbutton11.setOnClickListener(this);
        helpview.setOnClickListener(this);



        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
        testForOpenButton();

        MobileAds.initialize(getActivity(),
                "ca-app-pub-1336421761813784~1676720313");

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-1336421761813784/5956773882");


        return view;
    }

    public void showInterstitial (){
//        if (chiDa==(10^20^600^900^1400^2100^2700^3300)) {
//            if (mInterstitialAd.isLoaded()) {
//                mInterstitialAd.show();
//            }
//        }
//        if (chiDa==10){
//            if (mInterstitialAd.isLoaded()) {
//                mInterstitialAd.show();
//            }
//        }
    }


public void incrementRegreshTxt(){
    showInterstitial();
    chiDa=chiDa+chiDaIncrement;
    header.setText(" Баланс \n ChiDaCoin: " + chiDa );
    testForOpenButton();
}
    @Override
    public void onClick(View view) {
      //  releaseMP();
        switch (view.getId()) {
            case R.id.helpview:
               createHelpDialog("Help","ChiDaCoins игровая валюта, с помощью которой можно открывать новые звуки и получать бонусы.\n \n" +
                       "Достигая определенных отметок вы начнете получать дополнительные ChiDaCois. \n\n" +
                       "1000 ChiDaCois   +2 за клик. \n" +
                       "10000 ChiDaCois +3 за клик. \n" +
                       "50000 ChiDaCois +5 за клик. \n\n" +
                       "Так же вы можете получить 500 ChiDaCoins просмотрев видео с рекламой. \n");
                break;
            case R.id.shareview:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Чи да, скачивай в Google Play - https://play.google.com/store/apps/details?id=com.edwardbil_prank.tidatopstyle ");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Поделиться"));
                break;
            case R.id.randomss:
                releaseMP();
                mediaPlayer = MediaPlayer.create(getActivity(), R.raw.chidatop);
                mediaPlayer.start();
                incrementRegreshTxt();
                break;
            case R.id.admob:
                createTwoButtonsAlertDialog("Реклама","Просмотреть видео и получить 500 ChiDaCoins ");
                break;
            case R.id.soundbutton2:
                if (chiDa>=100){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tineti);
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 100 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                break;
            case R.id.soundbutton3:
                if (chiDa>=350){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida3 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Что бы открыть этот звук, наберите 350 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton4:
                if (chiDa>=700){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.chidarobot );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 700 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton5:
                if (chiDa>=1000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.neti );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 1000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton6:
                if (chiDa>=5000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k7500 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 5000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton7:
                if (chiDa>=10000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k10000 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 10000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton8:
                if (chiDa>=15000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k15000 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 15000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton9:
                if (chiDa>=20000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k20000 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 20000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton10:
                if (chiDa>=30000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k30000 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 30000 ChiDaCoins!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                break;
            case R.id.soundbutton11:
                if (chiDa>=50000){
                    releaseMP();
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k50000 );
                    mediaPlayer.start();
                } else {
                    tiEbanyti();
                    Toast toast = Toast.makeText(getActivity(),
                            "Чтобы открыть этот звук, наберите 50000 ChiDaCoins!", Toast.LENGTH_SHORT);
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

        if (chiDa >= 100){
            soundtxt2.setText("Ти или не ти?");
        }
        if (chiDa >= 350){
            soundtxt3.setText("Chi da?");
        }
        if (chiDa >= 700){
            soundtxt4.setText("Чи да robot");
        }
        if (chiDa >= 1000){
            soundtxt5.setText("Не ти");
            chiDaIncrement=2;
        }
        if (chiDa >= 5000){
            soundtxt6.setText("Чи да Light");
        }
        if (chiDa >= 10000){
            soundtxt7.setText("Слыш, ты чё");
            chiDaIncrement=3;
        }
        if (chiDa >= 15000){
            soundtxt8.setText("Я не понимаю");
        }
        if (chiDa >= 20000){
            soundtxt9.setText("Я щас маме позвоню ");
        }
        if (chiDa >= 30000){
            soundtxt10.setText("Сидеть!");
        }
        if (chiDa >= 50000){
            soundtxt11.setText("Это ты мне сказал?");
            chiDaIncrement=5;
            if (checkpoint==0){
                checkpoint=1;
                createTwoButtonsAlertDialogRecord("!!! ПОЗДРАВЛЯЕМ !!!","Вы набрали 50000 ChiDaCoins!!! \n" +
                        "Отныне за каждый клик вам будет начисляться по 5 ChiDaCois на баланс вместо 1 \n" +
                        "Это поможет вам быстрее получить новые плюшки в следующих обновлениях ;) ");
            }
        }
    }
    private void createHelpDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("ЗАКРЫТЬ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.setPositiveButton("ПОСМОТРЕТЬ ВИДЕО И ПОЛУЧИТЬ 500 ChiDaCois",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        if (mRewardedVideoAd.isLoaded()) {
                            mRewardedVideoAd.show();
                        } else {
                            Toast toast = Toast.makeText(getActivity(),
                                    "Что-то пошло не так, возможно проблемы с интернетом или загрузкой рекламы \nПопробуйте сделать это позже.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
        builder.show();
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
        builder.setPositiveButton("ПОЛУЧИТЬ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        if (mRewardedVideoAd.isLoaded()) {
                            mRewardedVideoAd.show();
                        } else {
                            Toast toast = Toast.makeText(getActivity(),
                                    "Что-то пошло не так, возможно проблемы с интернетом или загрузкой рекламы \nПопробуйте сделать это позже.", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
        builder.show();
    }

    private void createTwoButtonsAlertDialogRecord(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("ТИ ДА",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        chiDa=chiDa+15000;
                        Toast toast = Toast.makeText(getActivity(),
                                "Один из первых! \n Вам начисленно 15000 ChiDaCoins!", Toast.LENGTH_SHORT);
                        toast.show();
                        header.setText(" Баланс \n ChiDaCoin: " + chiDa );
                    }
                });
        builder.setPositiveButton("ЧИ ДА",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        chiDa=chiDa+15000;
                        Toast toast = Toast.makeText(getActivity(),
                                "Один из первых! \n Вам начисленно 15000 ChiDaCoins!", Toast.LENGTH_SHORT);
                        toast.show();
                        header.setText(" Баланс \n ChiDaCoin: " + chiDa );
                    }
                });
        builder.show();
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
