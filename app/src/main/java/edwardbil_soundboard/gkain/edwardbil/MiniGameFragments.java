package edwardbil_soundboard.gkain.edwardbil;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static com.google.android.gms.internal.zzahn.runOnUiThread;
import static edwardbil_soundboard.gkain.edwardbil.MainFragment.APP_PREFERENCES;
import static edwardbil_soundboard.gkain.edwardbil.MainFragment.APP_PREFERENCES_CHIDA;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiniGameFragments extends Fragment implements View.OnClickListener{
    TextView header,txtv,score;
    ImageView image;
    Button comment;
    Integer paramBundle;
    EditText edTxt;
    private SharedPreferences mSettings;
    private  int coins;
    String coinsString;
    int stavka;

    private ImageView img1, img2, img3;
    private Wheel wheel1, wheel2, wheel3;
    private boolean isStarted;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    public MiniGameFragments() {
        // Required empty public constructor
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(APP_PREFERENCES_CHIDA, coins);
        editor.apply();
    }

    @Override
    public void onResume() {
        if (mSettings.contains(APP_PREFERENCES_CHIDA)) {
            coins = mSettings.getInt(APP_PREFERENCES_CHIDA, 0);
        }
        coinsString = String.valueOf(coins);
        score.setText(coinsString);
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_mini_game_fragments, container, false);
        header = view.findViewById(R.id.header);
        txtv = view.findViewById(R.id.txtv);
        score = view.findViewById(R.id.score);
        comment = view.findViewById(R.id.comment);
        comment.setOnClickListener(this);

        img1 = view.findViewById(R.id.img1);
        img2 = view.findViewById(R.id.img2);
        img3 = view.findViewById(R.id.img3);
        edTxt = view.findViewById(R.id.edTxt);

        MainActivity ma = (MainActivity) getActivity();
        ma.invisibleGameView();

        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
       // txtv.setText("ChiDaCoins: "+coins);

//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            paramBundle = bundle.getInt("coins");
//        }
//        txtv.setText("ChiDaCoins: "+paramBundle);

        return view;
    }


    // Эдвард выглядывает снизу экрана и скрывается
    @Override
    public void onClick(View v) {
         switch (v.getId()){
     case R.id.comment:


//         paramBundle=paramBundle-1;
//         MainFragment mf = new MainFragment();
//         mf.miniGameMethod(paramBundle);
//
        startGame();

         coinsString = String.valueOf(coins);
         score.setText(coinsString);

//        MainFragment frag = new MainFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("tag", coins);
//        frag.setArguments(bundle);
//        break;
        }
    }


    public void startGame(){

        if (isStarted) {
            wheel1.stopWheel();
            wheel2.stopWheel();
            wheel3.stopWheel();

            if (wheel1.currentIndex == wheel2.currentIndex && wheel2.currentIndex == wheel3.currentIndex) {
                coins=coins+stavka+stavka*2;
                txtv.setText("+"+stavka+stavka*2+" ChiDaCoins x2.5 ");
                coinsString = String.valueOf(coins);
                score.setText(coinsString);

            } else if (wheel1.currentIndex == wheel2.currentIndex || wheel2.currentIndex == wheel3.currentIndex
                    || wheel1.currentIndex == wheel3.currentIndex) {
                coins=coins+stavka;
                txtv.setText("+"+stavka+" ChiDaCoins  ");
                coinsString = String.valueOf(coins);
                score.setText(coinsString);
            } else {
                coins=coins-stavka;
                txtv.setText("-"+stavka+" ChiDaCoins ");
                coinsString = String.valueOf(coins);
                score.setText(coinsString);
            }

            comment.setText("Старт");
            isStarted = false;

        } else {
            if (edTxt.getText().length()!=0) {
                stavka = Integer.parseInt(edTxt.getText().toString());
                if (coins>stavka){
            if (coins>=50 & stavka <= coins & stavka>=50) {


                comment.setClickable(false);
                comment.setText("...");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        comment.setClickable(true);
                        comment.setText("Stop");
                    }
                }, 3000);

            wheel1 = new Wheel(new Wheel.WheelListener() {
                @Override
                public void newImage(final int img) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img1.setImageResource(img);
                        }
                    });
                }
            }, 100, randomLong(0, 300));

            wheel1.start();

            wheel2 = new Wheel(new Wheel.WheelListener() {
                @Override
                public void newImage(final int img) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img2.setImageResource(img);
                        }
                    });
                }
            }, 100, randomLong(150, 300));

            wheel2.start();

            wheel3 = new Wheel(new Wheel.WheelListener() {
                @Override
                public void newImage(final int img) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            img3.setImageResource(img);
                        }
                    });
                }
            }, 100, randomLong(150, 300));

            wheel3.start();

        //    comment.setText("Stop");
            txtv.setText("");
            isStarted = true;
        } else { Toast toast = Toast.makeText(getActivity(), "Минимальная сумма 50 ChiDaCoins" , Toast.LENGTH_SHORT);toast.show(); }
                }else {Toast toast = Toast.makeText(getActivity(), "Недостаточно ChiDaCoins ", Toast.LENGTH_SHORT);toast.show();}
            }else {Toast toast = Toast.makeText(getActivity(), "Некоректная сумма", Toast.LENGTH_SHORT);toast.show();}


        }
    }
}
