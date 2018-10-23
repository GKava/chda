package edwardbil_soundboard.gkain.edwardbil;


import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MiniGameFragments extends Fragment implements View.OnClickListener{
//Button btn1,btn2,btn3;
TextView header;
ImageView image;
Button comment;
    public MiniGameFragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment_mini_game_fragments, container, false);
//        btn1 = view.findViewById(R.id.btn1);
//        btn2 = view.findViewById(R.id.btn2);
//        btn3 = view.findViewById(R.id.btn3);
        header = view.findViewById(R.id.header);
        comment = view.findViewById(R.id.comment);
        comment.setOnClickListener(this);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
//        btn3.setOnClickListener(this);


        MainActivity ma = (MainActivity) getActivity();
        ma.invisibleGameView();

        return view;
    }

// Эдвард выглядывает снизу экрана и скрывается
    @Override
    public void onClick(View v) {
switch (v.getId()){
//    case R.id.btn1:
//        Animation animation1 = null;
//        animation1 = AnimationUtils.loadAnimation(getActivity(), R.anim.upupup);
//        image.startAnimation(animation1);
//        break;
//    case R.id.btn2:
//
//        break;
//    case R.id.btn3:
//
//        break;
    case R.id.comment:
        Uri address = Uri.parse("https://play.google.com/store/apps/details?id=com.edwardbil_prank.tidatopstyle");
        Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
        startActivity(openlinkIntent);
        break;
}
    }
}
