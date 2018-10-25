package edwardbil_soundboard.gkain.edwardbil;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment implements View.OnClickListener {
Button stat, help;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        stat = view.findViewById(R.id.stat);
        help = view.findViewById(R.id.help);
        stat.setOnClickListener(this);
        help.setOnClickListener(this);
        MainActivity ma = (MainActivity) getActivity();
        ma.invisibleGameView();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.stat:
                StatsFragment statsFrament = new StatsFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, statsFrament)
                        .addToBackStack(null)
                        .commit();

                break;

            case R.id.help:
                HelpTextFragment helpTextFragment = new HelpTextFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container , helpTextFragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
