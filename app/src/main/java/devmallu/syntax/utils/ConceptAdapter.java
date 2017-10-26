package devmallu.syntax.utils;

import android.content.Context;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import devmallu.syntax.MainActivity;
import devmallu.syntax.R;

/**
 * Created by mashal on 23/10/17.
 *
 */

public class ConceptAdapter extends ArrayAdapter<Concept>{
    public ConceptAdapter(Context context, ArrayList<Concept> concepts) {
        super(context, 0,concepts);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Concept concept = getItem(position);
        View listItemView = convertView;
        if(listItemView==null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_concept,parent,false);
        }

        TextView titleTV    = (TextView) listItemView.findViewById(R.id.item_concept_title);
        TextView desciption = (TextView) listItemView.findViewById(R.id.item_concept_description);
        titleTV.setText(concept.getConcept());
        desciption.setText(concept.getDescription());

        listItemView.setElevation(10);

        return listItemView;

    }
}
