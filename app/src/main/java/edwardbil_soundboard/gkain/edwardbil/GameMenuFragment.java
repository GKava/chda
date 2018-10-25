package edwardbil_soundboard.gkain.edwardbil;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameMenuFragment extends Fragment implements View.OnClickListener {
Button game1;

    public GameMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_menu, container, false);
        game1 = view.findViewById(R.id.game1);
        game1.setOnClickListener(this);

        MainActivity ma = (MainActivity) getActivity();
        ma.invisibleGameView();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.game1:
                MiniGameFragments frag = new MiniGameFragments();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, frag)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
