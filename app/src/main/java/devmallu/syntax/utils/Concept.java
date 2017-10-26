package devmallu.syntax.utils;

import android.os.Parcel;
import android.os.Parcelable;

public class Concept implements Parcelable {

    private int id;
    private String concept; // concept_search
    private String description;
    private String syntax;
    private String notes;
    private String example;
    private String documentation;

    public Concept(int id, String concept, String description, String syntax, String notes, String example, String documentation) {
        this.id = id;
        this.concept = concept;
        this.description = description;
        this.syntax = syntax;
        this.notes = notes;
        this.example = example;
        this.documentation=documentation;
    }

    protected Concept(Parcel in) {
        id = in.readInt();
        concept = in.readString();
        description = in.readString();
        syntax = in.readString();
        notes = in.readString();
        example = in.readString();
        documentation=in.readString();
    }

    public int getId() {
        return id;
    }

    public String getConcept() {
        return concept;
    }
    public String getSyntax() {
        return syntax;
    }
    public String getDescription() {
        return description;
    }
    public String getNotes() {
        return notes;
    }
    public String getExample() {
        return example;
    }
    public String getDocumentation(){
        return documentation;
    }

    public static final Creator<Concept> CREATOR = new Creator<Concept>() {
        @Override
        public Concept createFromParcel(Parcel in) {
            return new Concept(in);
        }

        @Override
        public Concept[] newArray(int size) {
            return new Concept[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(concept);
        dest.writeString(description);
        dest.writeString(syntax);
        dest.writeString(notes);
        dest.writeString(example);
    }
}
