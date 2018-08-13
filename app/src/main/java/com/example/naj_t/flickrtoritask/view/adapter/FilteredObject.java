package com.example.naj_t.flickrtoritask.view.adapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * An object representing the json objects retrieved from the JsonFile in the raw folder
 */
public class FilteredObject {
    @SerializedName("category1")
    private String category1;
    @SerializedName("cat2")
    private List<Category2> category2;

    public FilteredObject(String category1, List<Category2> category2) {
        this.category1 = category1;
        this.category2 = category2;
    }

    public FilteredObject() {
    }

    /**
     * A method that verifies if a {@link FilteredObject}
     * contains a text
     *
     * @param text a String representing the searched for text
     * @return a list of {@link Category2} that contain the text
     */
    public List<Category2> hasCat(String text) {
        List<Category2> category2List = new ArrayList<>();
        if (category1.toLowerCase().contains(text)) {
            category2List.addAll(this.category2);
        } else
            for (Category2 category2 : this.category2) {
                List<String> results = category2.hasCat(text);
                if (results != null && !results.isEmpty()) {
                    category2List.add(new Category2(category2.category2, results));
                }
            }
        return category2List;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public List<Category2> getCategory2() {
        return category2;
    }

    public void setCategory2(List<Category2> category2) {
        this.category2 = category2;
    }

    public class Category2 {
        @SerializedName("category2")
        String category2;
        @SerializedName("labels")
        List<String> labels;

        public Category2() {
        }

        public Category2(String category2, List<String> labels) {
            this.category2 = category2;
            this.labels = labels;
        }

        public String getCategory2() {
            return category2;
        }

        public void setCategory2(String category2) {
            this.category2 = category2;
        }

        public List<String> getLabel() {
            return labels;
        }

        public void setLabel(List<String> label) {
            this.labels = label;
        }

        /**
         * A method that verifies if a {@link Category2}
         * contains a text
         *
         * @param text a String representing the searched for text
         * @return a list of {@link String} labels that contain the text
         */
        public List<String> hasCat(String text) {
            List<String> stringList = new ArrayList<>();
            if (category2.toLowerCase().contains(text)) {
                return labels;
            } else {
                for (String label : labels) {
                    if (label.toLowerCase().contains(text))
                        stringList.add(label);
                }
            }
            return stringList;
        }
    }
}
