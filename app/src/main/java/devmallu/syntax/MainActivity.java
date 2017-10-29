package devmallu.syntax;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import devmallu.syntax.utils.AppController;
import devmallu.syntax.utils.Concept;
import devmallu.syntax.utils.ConceptAdapter;

public class MainActivity extends AppCompatActivity implements ExploreFragment.OnFragmentInteractionListener {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ProgressBar progressBar;
    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**
         *  Create the adapter that will return a fragment for each of the three
         *  primary sections of the activity.
         *  The {@link android.support.v4.view.PagerAdapter} that will provide
         *  fragments for each of the sections. We use a
         *  {@link FragmentPagerAdapter} derivative, which will keep every
         *  loaded fragment in memory. If this becomes too memory intensive, it
         *  may be best to switch to a
         *  {@link FragmentStatePagerAdapter}.
         */
        PagerAdapter mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewPager.getCurrentItem() != 0) {
                    mViewPager.setCurrentItem(0, true);
                }else{
                        conceptQuery();
                }
            }
        });
    }

    private void conceptQuery() {
        EditText queryEditText  = (EditText) findViewById(R.id.find_query);
        final ListView listView = (ListView) findViewById(R.id.concept_list);
        final View emptyView = findViewById(R.id.emptyView);
//        Log.v("List View",listView.toString());

        String query = queryEditText.getText().toString();
        if(query.isEmpty()){
            Snackbar.make(mViewPager,"Empty query",Snackbar.LENGTH_SHORT).show();
            Log.v("Empty query",query);
            return;
        }

        String url = "https://syntaxdb.com/api/v1/concepts/search?q=" + query;
        // Tag used to cancel the request
        String tag_json_arry = "json_array_req";

//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();

        emptyView.setVisibility(View.GONE);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Log.d("Concept Response : ", response.toString());
//                        pDialog.hide();
                        progressBar.setVisibility(View.GONE);

                        final ArrayList<Concept> concepts = extractConcepts(response);
//                        if(concepts.size()==0){
//                            dialog = new Dialog(getApplicationContext());
//                            dialog.setTitle("No result..");
//                            dialog.setCancelable(true);
//                            dialog.show();
//                        }
//                        Log.v("concepts :",concepts.toString());
                        ConceptAdapter mAdapter = new ConceptAdapter(getApplicationContext(),concepts);
                        listView.setAdapter(mAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Concept c = concepts.get(position);
                                Intent  i = new Intent(MainActivity.this,ConceptActivity.class);
                                i.putExtra("concept", c);
                                startActivity(i);
                            }
                        });
//                        Log.v("list item count",String.valueOf(listView.getCount()));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Snackbar.make(mViewPager,"Error : check connection ",Snackbar.LENGTH_SHORT).show();

                VolleyLog.d("Concept Error", "Error: " + error.getMessage());
//                pDialog.hide();
                emptyView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_arry);
    }

    public static ArrayList<Concept> extractConcepts(JSONArray jsonResponse){
        ArrayList<Concept> concepts = new ArrayList<>();

        try {
//            JSONObject root  = new JSONObject(jsonResponse);
//            JSONArray jsonResponse = root.getJSONArray("features");
            /**
             * nested JSON object path finding
             */
            for (int i = 0;i<jsonResponse.length();i++){
                JSONObject ithConcept = jsonResponse.getJSONObject(i);
                int id = ithConcept.getInt("id");
                String concept = ithConcept.getString("concept_search");
                String syntax  = ithConcept.getString("syntax");
                String description = ithConcept.getString("description");
                String notes   = ithConcept.getString("notes");
                String example = ithConcept.getString("example");
                String docu    = ithConcept.getString("documentation");
                concepts.add(new Concept(id, concept, description, syntax, notes,example, docu));
            }
        }catch (JSONException e){
            Log.e("FindQuery","JSON parsing error",e);
        }
        return concepts;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // TODO: method onFragmentInteraction
    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}