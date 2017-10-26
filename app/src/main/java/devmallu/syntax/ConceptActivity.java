package devmallu.syntax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

import devmallu.syntax.utils.Concept;

public class ConceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concept);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Concept concept = getIntent().getParcelableExtra("concept");

        TextView cHead = (TextView) findViewById(R.id.c_head);
        TextView cDesc = (TextView) findViewById(R.id.c_description);
        TextView cSyntax = (TextView) findViewById(R.id.c_syntax);
        TextView cExample = (TextView) findViewById(R.id.c_example);
        TextView cNotes = (TextView) findViewById(R.id.c_notes);
        TextView cDocu  = (TextView) findViewById(R.id.c_documentation);

        getSupportActionBar().setTitle(concept.getConcept());
        cHead.setText(concept.getConcept());
        cDesc.setText(concept.getDescription());
        cSyntax.setText(concept.getSyntax());
        cExample.setText(concept.getExample());
        cNotes.setText(concept.getNotes());
        if (Objects.equals(concept.getDocumentation(), "")){
            cDocu.setVisibility(View.GONE);
        }else{
            cDocu.setText(concept.getDocumentation());
            cDocu.setVisibility(View.VISIBLE);
        }
    }
}
