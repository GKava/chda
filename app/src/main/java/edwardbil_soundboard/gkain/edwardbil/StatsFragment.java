package edwardbil_soundboard.gkain.edwardbil;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static edwardbil_soundboard.gkain.edwardbil.MainFragment.APP_PREFERENCES;
import static edwardbil_soundboard.gkain.edwardbil.MainFragment.APP_PREFERENCES_CHIDA;
import static edwardbil_soundboard.gkain.edwardbil.MainFragment.APP_PREFERENCES_CHIDA_INCREMENT;
import static edwardbil_soundboard.gkain.edwardbil.MainFragment.APP_PREFERENCES_CRIT_SCORE;


/**
 * A simple {@link Fragment} subclass.
 */
public class StatsFragment extends Fragment implements View.OnClickListener{
    TextView stats;
    ImageView openDialogHelp;
    SharedPreferences mSettings;
    private int coins;
    private String coinsString;
    private int coinsFactor;
    private String coinsFactorString;
    private int critScore;
    private String critScoreString;

    public StatsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_frament, container, false);
        stats = view.findViewById(R.id.stats);
        openDialogHelp = view.findViewById(R.id.openDialogHelp);
        openDialogHelp.setOnClickListener(this);
        mSettings = this.getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

       // long spendTime = ((MainActivity) getActivity()).getSpendTime();

      //  stats.setText("ChiDaCoins: " + "\nМножитель:" + "\nКрит. бонус:" +"\nВыбито критов:"+"\nВремя проведённое в игре: "+ spendTime);
        return view;
    }

    @Override
    public void onResume() {
        if (mSettings.contains(APP_PREFERENCES_CHIDA)) {
            coins = mSettings.getInt(APP_PREFERENCES_CHIDA, 0);
        }
        if (mSettings.contains(APP_PREFERENCES_CHIDA_INCREMENT)) {
            coinsFactor = mSettings.getInt(APP_PREFERENCES_CHIDA_INCREMENT, 0);
        }
        if (mSettings.contains(APP_PREFERENCES_CRIT_SCORE)) {
            critScore = mSettings.getInt(APP_PREFERENCES_CRIT_SCORE, 0);
        }

        critScoreString = String.valueOf(critScore);
        coinsFactorString = String.valueOf(coinsFactor);
        coinsString = String.valueOf(coins);
        long spendTime = ((MainActivity) getActivity()).getSpendTime(); //зациклить в хендлере для реального времени
        String spendTimeString = DateUtils.formatElapsedTime(spendTime/1000);
        stats.setText("ChiDaCoins: " +coinsString+ "\nМножитель: x" +coinsFactorString+ "\nКрит. бонус: 0" +"\nВыбито критов: "+critScore+"\nВремя в игре: "+ spendTimeString);
        //потрачено в минииграх
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.openDialogHelp:
                createTwoButtonsAlertDialog("Подсказка", "ChiDaCoins - ваш баланс. \n\nМножитель - сколько вы получается ChiDaCoins за клик. \n\nКритический бонус - бонус к шансу выпадения критического Чи Да. \n\nВремя в игре - все то время, что вы находились в игре. ");
                break;
        }
    }
    private void createTwoButtonsAlertDialog(String title, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(content);
        builder.setNegativeButton(" ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.setPositiveButton("Закрыть",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {

                    }
                });
        builder.show();
    }
}
