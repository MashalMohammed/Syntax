package devmallu.syntax;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import devmallu.syntax.utils.AppController;


/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the
 * {@link ExploreFragment.OnFragmentInteractionListener} interface to handle interaction events.
 * Use the {@link ExploreFragment#newInstance} factory method to create an instance of this fragment.
 */
public class ExploreFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private View rootView;

    public ExploreFragment() {
        // Required empty public constructor
    }
    public static ExploreFragment newInstance() {
        return new ExploreFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_explore, container, false);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed() {
//        if (mListener != null) {
//            mListener.onFragmentInteraction();
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
//        setup();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that activity.
     * <p> See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction();
    }

    void setup(){
        String url = "https://syntaxdb.com/api/v1/concepts/search?";
        String tag_json_arry = "json_array_req";

        final GridView gridView = (GridView) rootView.findViewById(R.id.lang_gridView);
        final ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.lang_progressBar);
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        progressBar.setVisibility(View.GONE);
//                        Log.v("list item count",String.valueOf(listView.getCount()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(rootView,"Error : check connection ",Snackbar.LENGTH_SHORT).show();

                VolleyLog.d("Concept Error", "Error: " + error.getMessage());
                progressBar.setVisibility(View.GONE);

            }
        });
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }
}
