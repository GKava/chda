package edwardbil_soundboard.gkain.edwardbil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView image,game_view;
    Handler mHandler;
    private static final String TAG = "MainActivity";
    public static final String APP_PREFERENCES_CHECKPOINTS = "ads_inter";
    public static final String ADS_PREFERENCES = "ads_settings";
    public static final String SPEND_TIME = "spend_time";
    private SharedPreferences adsSettings;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private long mStartTime;
    int intBanner =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        adsSettings = this.getSharedPreferences(ADS_PREFERENCES, Context.MODE_PRIVATE);
        image = (ImageView) findViewById(R.id.image);
        game_view = (ImageView) findViewById(R.id.game_view);
        game_view.setOnClickListener(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        MobileAds.initialize(this,
                "ca-app-pub-1336421761813784~1676720313");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-1336421761813784/5956773882");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        if (findViewById(R.id.fragment_container)!=null){
            if (savedInstanceState!=null){
                return;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new MainFragment(), "MainFragment").commit();
        }

        startAnimGame();
    }


    public void viewAds(){
        if (intBanner==0) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                intBanner++;
            }
        } else {
            intBanner++;
            if (intBanner ==3){
                intBanner=0;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.game_view:
                viewAds();
                //MainFragment mf = new MainFragment();
                //mf.sendBundleChiDa();
//                MiniGameFragments frag = new MiniGameFragments();
                GameMenuFragment frag = new GameMenuFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("coins", ((MainFragment)(getSupportFragmentManager().findFragmentByTag("MainFragment"))).chiDa);
                frag.setArguments(bundle);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, frag)
                        .addToBackStack(null)
                        .commit();


//            fragmentManager.beginTransaction()
//                    .replace(R.id.fragment_container, new MiniGameFragments())
//                    .addToBackStack(null)
//                    .commit();
//        Log.e(TAG, "Значение intBanner " + intBanner);

                break;
        }
    }

    public void visibleGameView(){
        game_view.setVisibility(View.VISIBLE);
    }
    public void invisibleGameView(){
        game_view.setVisibility(View.INVISIBLE);
    }

    public void startAnimGame(){
for (int ti=2000; ti<=4000; ti=ti+2000) {
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Animation animation1 = null;
            animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.game_view);
            game_view.startAnimation(animation1);
              }
            }, ti);
        }
    }


    public void showAnimate(int imageResource){

        if(imageResource==1){
            image.setImageResource(R.drawable.krit1 );
        }
        if (imageResource==2){
            image.setImageResource(R.drawable.krit2);
        }
        if (imageResource==3){
            image.setImageResource(R.drawable.krit3 );
        }
        Animation animation1 = null;
        animation1 = AnimationUtils.loadAnimation(this, R.anim.left_right);
        image.startAnimation(animation1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStartTime = System.currentTimeMillis();
        if (adsSettings.contains(APP_PREFERENCES_CHECKPOINTS)) {
            intBanner = adsSettings.getInt(APP_PREFERENCES_CHECKPOINTS, 0);
        }
        mAdView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdView.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = adsSettings.edit();
        editor.putInt(APP_PREFERENCES_CHECKPOINTS, intBanner);
        mAdView.pause();

        long spendTime = System.currentTimeMillis() - mStartTime;
        editor.putLong(SPEND_TIME, adsSettings.getLong(SPEND_TIME, 0) + spendTime);

        editor.apply();
    }

    public long getSpendTime() {
        long spendTime = System.currentTimeMillis() - mStartTime;
        return adsSettings.getLong(SPEND_TIME, 0) + spendTime;
    }
}
