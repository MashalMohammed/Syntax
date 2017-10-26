package devmallu.syntax;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FindFragment extends Fragment {


    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find,container,false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //TODO: create methods for fetching query and adapting
}