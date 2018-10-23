package edwardbil_soundboard.gkain.edwardbil;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment implements View.OnClickListener {
    Button comment;


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        comment = view.findViewById(R.id.comment);
        comment.setOnClickListener(this);


        MainActivity ma = (MainActivity) getActivity();
        ma.invisibleGameView();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment:
                Uri address = Uri.parse("https://play.google.com/store/apps/details?id=com.edwardbil_prank.tidatopstyle");
                Intent openlinkIntent = new Intent(Intent.ACTION_VIEW, address);
                startActivity(openlinkIntent);
                break;

        }
    }
}
