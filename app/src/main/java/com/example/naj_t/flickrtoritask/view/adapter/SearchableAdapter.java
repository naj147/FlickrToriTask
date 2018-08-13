package com.example.naj_t.flickrtoritask.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naj_t.flickrtoritask.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adaptar that manages a {@link android.support.v7.widget.SearchView} and its {@link android.support.v7.widget.SearchView.SearchAutoComplete}
 * //
 */
public class SearchableAdapter extends BaseAdapter implements Filterable {

    private Picasso picasso;
    private Context context;
    private List<FilteredObject> originalData = null;
    private List<FilteredObject> filteredData = null;
    private LayoutInflater mInflater;
    private ItemFilter mFilter = new ItemFilter();

    public SearchableAdapter(Context context, List<FilteredObject> data, Picasso picasso) {
        this.context = context;
        this.filteredData = data;
        this.originalData = data;
        this.picasso = picasso;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        int count = 0;
        if (filteredData != null)
            for (FilteredObject filtered : this.filteredData)
                for (FilteredObject.Category2 category2 : filtered.getCategory2())
                    count += category2.getLabel().size();
        return count;
    }

    public Object getItem(int position) {
        int index1 = position;
        for (FilteredObject filteredObject : filteredData) {
            for (FilteredObject.Category2 category2 : filteredObject.getCategory2()) {
                if (index1 > category2.labels.size()) {
                    index1 -= category2.labels.size();
                } else {
                    return filteredObject;
                }
            }
        }
        return filteredData.get(0);
    }

    public String getLabel(int position) {
        int index1 = position;
        for (FilteredObject filteredObject : filteredData) {
            for (FilteredObject.Category2 category2 : filteredObject.getCategory2()) {
                if (index1 >= category2.labels.size()) {
                    index1 -= category2.labels.size();
                } else {
                    return category2.labels.get(index1);
                }
            }
        }
        return "";
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {
        int index1 = position;
//        int index2=0;
//        int index3=0;
        // A ViewHolder keeps references to children views to avoid unnecessary calls
        // to findViewById() on each row.
        ViewHolder holder;
        // When convertView is not null, we can reuse it directly, there is no need
        // to reinflate it. We only inflate a new View when the convertView supplied
        // by ListView is null.
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            // Creates a ViewHolder and store references to the two children views
            // we want to bind data to.
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            // Get the ViewHolder back to get fast access to the TextView
            // and the ImageView.
            holder = (ViewHolder) convertView.getTag();
        }
        for (FilteredObject filteredObject : filteredData) {
            for (FilteredObject.Category2 category2 : filteredObject.getCategory2()) {
                if (index1 >= category2.labels.size()) {
                    index1 -= category2.labels.size();
                } else {
                    holder.category1.setText(filteredObject.getCategory1());
                    holder.category2.setText(category2.category2);
                    holder.text.setText(category2.labels.get(index1));
                    picasso.load(imageToBeShown(filteredObject.getCategory1().toLowerCase())).into(holder.categoryImage);
                    return convertView;
                }
            }
        }
        // If weren't re-ordering this you could rely on what you set last time
        return convertView;
    }

    @DrawableRes
    private int imageToBeShown(String category1) {
        switch (category1) {
            case "animals":
                return R.drawable.animal;
            case "food":
                return R.drawable.food;
            case "vechicle":
                return R.drawable.car;
            case "movie":
                return R.drawable.movie;
            default:
                return R.drawable.search;

        }
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    static class ViewHolder {
        View view;
        @BindView(R.id.suggIm)
        ImageView categoryImage;
        @BindView(R.id.category1)
        TextView category1;
        @BindView(R.id.category2)
        TextView category2;
        @BindView(R.id.label)
        TextView text;

        ViewHolder(View view) {
            this.view = view;
            ButterKnife.bind(this, view);
        }
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();
            final List<FilteredObject> list = originalData;


            int count = list.size();
            final ArrayList<FilteredObject> nlist = new ArrayList<>(count);
            for (FilteredObject filteredObject : list) {
                List<FilteredObject.Category2> category2List = filteredObject.hasCat(filterString);
                if (category2List != null && !category2List.isEmpty()) {
                    nlist.add(new FilteredObject(filteredObject.getCategory1(), category2List));
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<FilteredObject>) results.values;
            notifyDataSetChanged();
        }
    }
}