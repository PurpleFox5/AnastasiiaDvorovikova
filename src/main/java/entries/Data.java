package entries;

import com.epam.commons.DataClass;
import com.google.gson.annotations.SerializedName;

import java.util.List;

// TODO What do you mean 'Data' ?
// The name of this class should be selected in according to purpose of it.
public class Data extends DataClass {

    @SerializedName("summary")
    public List<String> summaries;
    public List<Elements> elements;
    public Colors color;
    public Metals metals;
    public List<Vegetables> vegetables;

    public Data(List<String> summaries, List<Elements> elements, Colors color, Metals metals, List<Vegetables> vegetables) {
        this.summaries = summaries;
        this.elements = elements;
        this.color = color;
        this.metals = metals;
        this.vegetables = vegetables;
    }
}
