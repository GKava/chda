package edwardbil_soundboard.gkain.edwardbil;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    ImageView shareview,helpview,shop;
    TextView header;
    Random random = new Random();

    int chiDa=0;

    int chiDaIncrement=1;
    int critScore=0;
    int checkpoint=0;
    private RewardedVideoAd mRewardedVideoAd;

    TextView soundtxt2,soundtxt3,soundtxt4,soundtxt5,soundtxt6,soundtxt7,soundtxt8,soundtxt9,soundtxt10,soundtxt11, btntxt1,btntxt2,btntxt3,btntxt4,btntxt5,btntxt6,btntxt7;
    Button soundbutton2,soundbutton3,soundbutton4,soundbutton5,soundbutton6,soundbutton7,soundbutton8,soundbutton9,soundbutton10,soundbutton11,btn1,btn2,btn3,btn4,btn5,btn6,btn7;
    Context context;
    public static final String APP_PREFERENCES_CHIDA = "act";
    public static final String APP_PREFERENCES_CHECKPOINT = "checkpoint";
    public static final String APP_PREFERENCES_CRIT_SCORE = "crit_score";
    public static final String APP_PREFERENCES_CHIDA_INCREMENT = "chida_increment";
    public static final String APP_PREFERENCES = "mysettings";
    private SharedPreferences mSettings;

    public MainFragment() {
        // Required empty public constructor
    }

//    public void sendBundleChiDa(){
//        MiniGameFragments frag = new MiniGameFragments();
//        Bundle bundle = new Bundle();
//        bundle.putInt("coins", chiDa);
//        frag.setArguments(bundle);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, frag)
//                .addToBackStack(null)
//                .commit();
//    }
//
//    public void miniGameMethod(int coins){
//        chiDa = chiDa - coins;
//        //header.setText(" Баланс \n ChiDaCoin: " + chiDa );
//    }

    @Override
    public void onPause() {
        super.onPause();
        mRewardedVideoAd.pause(getActivity());
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_CHIDA, chiDa);
        editor.putInt(APP_PREFERENCES_CHIDA_INCREMENT, chiDaIncrement);
        editor.putInt(APP_PREFERENCES_CHECKPOINT, checkpoint);
        editor.putInt(APP_PREFERENCES_CRIT_SCORE, critScore);
        editor.apply();

    }

    @Override
    public void onResume() {
        super.onResume();

        mRewardedVideoAd.resume(getActivity());
        if (mSettings.contains(APP_PREFERENCES_CHIDA)) {
            chiDa = mSettings.getInt(APP_PREFERENCES_CHIDA, 0);
        }
        if (mSettings.contains(APP_PREFERENCES_CHIDA_INCREMENT)) {
            chiDaIncrement = mSettings.getInt(APP_PREFERENCES_CHIDA_INCREMENT, 0);
        }
        if (mSettings.contains(APP_PREFERENCES_CHECKPOINT)) {
            checkpoint = mSettings.getInt(APP_PREFERENCES_CHECKPOINT, 0);
        }
        if (mSettings.contains(APP_PREFERENCES_CRIT_SCORE)) {
            critScore = mSettings.getInt(APP_PREFERENCES_CRIT_SCORE, 0);
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

        helpview = view.findViewById(R.id.helpview);
        shareview = view.findViewById(R.id.shareview);
        shop = view.findViewById(R.id.shop);
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

        // 50.000 - 1.000.000
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

        btntxt1 = view.findViewById(R.id.btntxt1);
        btntxt2 = view.findViewById(R.id.btntxt2);
        btntxt3 = view.findViewById(R.id.btntxt3);
        btntxt4 = view.findViewById(R.id.btntxt4);
        btntxt5 = view.findViewById(R.id.btntxt5);
        btntxt6 = view.findViewById(R.id.btntxt6);
        btntxt7 = view.findViewById(R.id.btntxt7);

        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);
        btn5 = view.findViewById(R.id.btn5);
        btn6 = view.findViewById(R.id.btn6);
        btn7 = view.findViewById(R.id.btn7);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);

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
        shop.setOnClickListener(this);


        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        loadRewardedVideoAd();
        testForOpenButton();
        return view;
    }


    @Override
    public void onStart() {
        MainActivity ma = (MainActivity) getActivity();
        ma.visibleGameView();
        super.onStart();
    }

    public void chanceChiDa(){
    final Random random = new Random();
    int chance1 = random.nextInt(100);
    int chance2 = random.nextInt(1000);
    int chance3 = random.nextInt(10000);

    if ((chance1==0) & (chance2==0)){
        chance1=1;
    }
    if ((chance1==0) & (chance3==0)){
        chance1=1;
    }
    if ((chance2==0) & (chance3==0)){
        chance2=1;
    }
    if (chance1==0) {
        if (getActivity() != null) {
            MainActivity ma = (MainActivity) getActivity();
            ma.showAnimate(1); // передать в параметрах картинку
            chiDa = chiDa + 100;
            header.setText(" Баланс \n ChiDaCoin: " + chiDa);
            critScore++;
        }
    }
    if (chance2==0) {
        if (getActivity() != null) {
            MainActivity ma = (MainActivity) getActivity();
            ma.showAnimate(2);
            chiDa = chiDa + 700;
            header.setText(" Баланс \n ChiDaCoin: " + chiDa);
            critScore++;
        }
    }
    if (chance3==0) {
        if (getActivity() != null) {
            MainActivity ma = (MainActivity) getActivity();
            ma.showAnimate(3);
            chiDa = chiDa + 10000;
            header.setText(" Баланс \n ChiDaCoin: " + chiDa);
            critScore++;
        }
    }
}

public void incrementRegreshTxt(){
    chiDa=chiDa+chiDaIncrement;
    header.setText(" Баланс \n ChiDaCoin: " + chiDa );
    testForOpenButton();
    chanceChiDa();
}
    @Override
    public void onClick(View view) {
      //  releaseMP();
        switch (view.getId()) {
            case R.id.shop:
                createRateDialog("Оценка", "Поддержите разработчиков поставив оценку приложению. \n\nК оценке вы можете добавить комментарий, мы его обязательно прочтём и ответим вам.");

//                MainActivity.fragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, new ShopFragment())
//                        .addToBackStack(null)
//                        .commit();
                break;
            case R.id.helpview:
                InfoFragment infoFragment = new InfoFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, infoFragment)
                        .addToBackStack(null)
                        .commit();

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
                Animation animation = null;
                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                randoms.startAnimation(animation);
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
                    Animation animation2 = null;
                    animation2 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton2.startAnimation(animation2);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tineti);
                    mediaPlayer.start();
                } else {
                    showToast("100");
                }

                break;
            case R.id.soundbutton3:
                if (chiDa>=350){
                    releaseMP();
                    Animation animation3 = null;
                    animation3 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton3.startAnimation(animation3);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.tida3 );
                    mediaPlayer.start();
                } else {
                    showToast("350");
                }
                break;
            case R.id.soundbutton4:
                if (chiDa>=700){
                    releaseMP();
                    Animation animation4 = null;
                    animation4 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton4.startAnimation(animation4);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.chidarobot );
                    mediaPlayer.start();
                } else {
                    showToast("700");
                }
                break;
            case R.id.soundbutton5:
                if (chiDa>=1000){
                    releaseMP();
                    Animation animation5 = null;
                    animation5 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton5.startAnimation(animation5);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.neti );
                    mediaPlayer.start();
                } else {
                    showToast("1.000");
                }
                break;
            case R.id.soundbutton6:
                if (chiDa>=5000){
                    releaseMP();
                    Animation animation6 = null;
                    animation6 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton6.startAnimation(animation6);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k7500 );
                    mediaPlayer.start();
                } else {
                    showToast("5.000");
                }
                break;
            case R.id.soundbutton7:
                if (chiDa>=10000){
                    releaseMP();
                    Animation animation7 = null;
                    animation7 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton7.startAnimation(animation7);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k10000 );
                    mediaPlayer.start();
                } else {
                    showToast("10.000");
                }
                break;
            case R.id.soundbutton8:
                if (chiDa>=15000){
                    releaseMP();
                    Animation animation8 = null;
                    animation8 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton8.startAnimation(animation8);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k15000 );
                    mediaPlayer.start();
                } else {
                    showToast("15.000");
                }
                break;
            case R.id.soundbutton9:
                if (chiDa>=20000){
                    releaseMP();
                    Animation animation9 = null;
                    animation9 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton9.startAnimation(animation9);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k20000 );
                    mediaPlayer.start();
                } else {
                    showToast("20.000");
                }
                break;
            case R.id.soundbutton10:
                if (chiDa>=30000){
                    releaseMP();
                    Animation animation10 = null;
                    animation10 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton10.startAnimation(animation10);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k30000 );
                    mediaPlayer.start();
                } else {
                    showToast("30.000");
                }
                break;
            case R.id.soundbutton11:
                if (chiDa>=50000){
                    releaseMP();
                    Animation animation11 = null;
                    animation11 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    soundbutton11.startAnimation(animation11);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.k50000 );
                    mediaPlayer.start();
                } else {
                    showToast("50.000");
                }
                break;

            case R.id.btn1:
                if (chiDa>=75000){
                    releaseMP();
                    Animation animation12 = null;
                    animation12 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn1.startAnimation(animation12);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.neproshaytakix);
                    mediaPlayer.start();
                } else {
                    showToast("75.000");
                }
                break;

            case R.id.btn2:
                if (chiDa>=100000){
                    releaseMP();
                    Animation animation13 = null;
                    animation13 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn2.startAnimation(animation13);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.vizivaitemcs);
                    mediaPlayer.start();
                } else {
                    showToast("100.000");
                }
                break;

            case R.id.btn3:
                if (chiDa>=150000){
                    releaseMP();
                    Animation animation14 = null;
                    animation14 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn3.startAnimation(animation14);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.vsexobliaaal);
                    mediaPlayer.start();
                } else {
                    showToast("150.000");
                }
                break;

            case R.id.btn4:
                if (chiDa>=250000){
                    releaseMP();
                    Animation animation16 = null;
                    animation16 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn4.startAnimation(animation16);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.scastyazdorovia);
                    mediaPlayer.start();
                } else {
                    showToast("250.000");
                }
                break;

            case R.id.btn5:
                if (chiDa>=350000){
                    releaseMP();
                    Animation animation18 = null;
                    animation18 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn5.startAnimation(animation18);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.okno);
                    mediaPlayer.start();
                } else {
                    showToast("350.000");
                }
                break;

            case R.id.btn6:
                if (chiDa>=500000){
                    releaseMP();
                    Animation animation19 = null;
                    animation19 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn6.startAnimation(animation19);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.proshyogidat);
                    mediaPlayer.start();
                } else {
                    showToast("500.000");
                }
                break;

            case R.id.btn7:
                if (chiDa>=1000000){
                    releaseMP();
                    Animation animation20 = null;
                    animation20 = AnimationUtils.loadAnimation(getActivity(), R.anim.combination);
                    btn7.startAnimation(animation20);
                    incrementRegreshTxt();
                    mediaPlayer = MediaPlayer.create(getActivity(), R.raw.svertuxi);
                    mediaPlayer.start();
                } else {
                    showToast("1000.000");
                }
                break;
        }

    }

    public void showToast (String col){
        Toast toast = Toast.makeText(getActivity(),
                "Чтобы открыть этот звук, наберите "+ col + " ChiDaCoins!", Toast.LENGTH_SHORT);
        toast.show();
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
            chiDaIncrement=3;
        }
        if (chiDa >= 5000){
            soundtxt6.setText("Чи да Light");
        }
        if (chiDa >= 10000){
            soundtxt7.setText("Слыш, ты чё");
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
                        "Отныне за каждый клик вам будет начисляться по 5 ChiDaCois на баланс вместо 3");
            }
        }
        if (chiDa >= 75000){
            btntxt1.setText("Я не прощаю таких");
        }
        if (chiDa >= 100000){
            chiDaIncrement=7;
            btntxt2.setText("Вызывайте МЧС");
        }
        if (chiDa >= 150000){
            btntxt3.setText("Обнимашки");
        }
        if (chiDa >= 250000){
            chiDaIncrement=8;
            btntxt4.setText("Счастья здоровья");
        }
        if (chiDa >= 350000){
            btntxt5.setText("Идите домой");
        }
        if (chiDa >= 500000){
            chiDaIncrement=9;
            btntxt6.setText("Прошу ожидать");
        }
        if (chiDa >= 1000000){
            chiDaIncrement=10;
            btntxt7.setText("WIN");
            if (checkpoint==1){
                checkpoint=5;
                createTwoButtonsAlertDialogRecord("!!! ПОЗДРАВЛЯЕМ !!!","Вы набрали 1 Миллион ChiDaCoins!!! \n" +
                        "Отныне за каждый клик вам будет начисляться по 10 ChiDaCois \n" +
                        "Это поможет вам быстрее получить новые плюшки в следующих обновлениях ;) ");
            }
        }
    }

    private void createRateDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton("ЗАКРЫТЬ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.setPositiveButton("Поставить оценку",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        Uri address = Uri.parse("https://play.google.com/store/apps/details?id=com.edwardbil_prank.tidatopstyle");
                        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                        startActivity(openlinkIntent);
                    }
                });
        builder.show();
    }

//    private void createHelpDialog(String title, String content) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle(title);
//        builder.setMessage(content);
//        builder.setNegativeButton("ЗАКРЫТЬ",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//
//                    }
//                });
//        builder.setPositiveButton("ПОСМОТРЕТЬ ВИДЕО И ПОЛУЧИТЬ 500 ChiDaCois",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,
//                                        int which) {
//                        if (mRewardedVideoAd.isLoaded()) {
//                            mRewardedVideoAd.show();
//                        } else {
//                            Toast toast = Toast.makeText(getActivity(),
//                                    "Что-то пошло не так, возможно проблемы с интернетом или загрузкой рекламы \nПопробуйте посмотреть видео позже.", Toast.LENGTH_SHORT);
//                            toast.show();
//                        }
//                    }
//                });
//        builder.show();
//    }

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
