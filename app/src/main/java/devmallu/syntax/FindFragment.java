package devmallu.syntax;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;

public class FindFragment extends Fragment {

    public FindFragment() {
        // Required empty public constructor
    }

    public static FindFragment newInstance() {
        return new FindFragment();
    }


    //TODO: can use onSaveInstance() and onActivityCreated()


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_find,container,false);
        final EditText queryET = (EditText)view.findViewById(R.id.find_query);
        final ImageView imageView = (ImageView) view.findViewById(R.id.find_et_x);
        imageView.bringToFront();
        queryET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (Objects.equals(queryET.getText().toString(), ""))
                    imageView.setVisibility(View.INVISIBLE);
                else
                    imageView.setVisibility(View.VISIBLE);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryET.setText("");
                Log.v("etX","onClick X");
            }
        });
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //TODO : transfer methods to fragment & use volley cache
}