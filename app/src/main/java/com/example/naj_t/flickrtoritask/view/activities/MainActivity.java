package com.example.naj_t.flickrtoritask.view.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.naj_t.flickrtoritask.AndroidApplication;
import com.example.naj_t.flickrtoritask.DPINJ.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.R;
import com.example.naj_t.flickrtoritask.models.PhotoModel;
import com.example.naj_t.flickrtoritask.models.PhotosModel;
import com.example.naj_t.flickrtoritask.presenters.PhotosPresenter;
import com.example.naj_t.flickrtoritask.view.PhotosListView;
import com.example.naj_t.flickrtoritask.view.adapter.FilteredObjects;
import com.example.naj_t.flickrtoritask.view.adapter.PhotosAdapter;
import com.example.naj_t.flickrtoritask.view.adapter.PhotosLayoutManager;
import com.example.naj_t.flickrtoritask.view.adapter.SearchableAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Main application screen.
 * It shows both Recent Pictures And Search Results
 */
public class MainActivity extends AppCompatActivity implements PhotosListView {
    public static final String PHOTO_ID = "ID";
    public static final String PHOTO_OWNER = "OWNER";
    public static final String PHOTO_FARM = "FARM";
    public static final String PHOTO_TITLE = "TITLE";
    public static final String PHOTO_SERVER = "SERVER";
    public static final String PHOTO_SECRET = "SECRET";
    public static String QUERY = "imageSearch";
    private static int PAGE_SIZE =100;
    SearchView.SearchAutoComplete searchAutoComplete;
    @Inject
    Picasso picasso;
    @Inject
    PhotosPresenter photosPresenter;
    @Inject
    PhotosAdapter photosAdapter;
    @BindView(R.id.search_tab)
    SearchView searchView;
    static String queryText = null;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.load_prog)
    ProgressBar progressBar;
    @BindView(R.id.bt_retry)
    Button button;

    SearchableAdapter searchableAdapter;
    PhotosLayoutManager photosLayoutManager;
    static int page=1;

    @BindView(R.id.content_title)
    TextView contentTitleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        this.photosPresenter.setView(this);
        if (!initializeIntent() && savedInstanceState == null) {
            this.loadPhotos(null, page);
        }
        setupSearchView(this);
        setupRecyclerView();
    }


    /***
     * A Method to Expand the search tab when clicked
     */
    @OnClick(R.id.search_tab)
    public void searchTabClicked() {
        searchView.onActionViewExpanded();
    }

    /**
     * A Method to Setup the searchView and handle Query submits and Autocomplete
     *
     * @param context {@link Context} context
     */
    @SuppressLint("RestrictedApi")
    public void setupSearchView(final Context context) {
        ((EditText) this.searchView.findViewById(R.id.search_src_text)).setTextColor(getResources().getColor(R.color.black));
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent availableIntent = getIntent();
                if (!availableIntent.hasExtra(QUERY)) {
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra(QUERY, query);
                    intent.setAction(Intent.ACTION_SEARCH);
                    context.startActivity(intent);
                    ((MainActivity) context).onPause();
                } else {
                    doMySearch(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchAutoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setDropDownAnchor(R.id.search_tab);
        searchAutoComplete.setDropDownBackgroundResource(R.color.white);
        searchAutoComplete.setThreshold(1);
        FilteredObjects filteredObjects = getCatFromJson();
        searchableAdapter = new SearchableAdapter(context, filteredObjects.getFilteredObject(), picasso);
        searchAutoComplete.setAdapter(searchableAdapter);
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                String query = searchableAdapter.getLabel(position);
                searchView.setQuery(query, false);
                return true;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                String query = searchableAdapter.getLabel(position);
                searchView.setQuery(query, false);
                return true;
            }
        });

    }

    /**
     * A Method for Retrieving and parsing the categories from the json file attached in the raw folder
     */
    public FilteredObjects getCatFromJson() {
        InputStream in = getResources().openRawResource(R.raw.flickr_keyword_struct);
        JsonElement element = new JsonParser().parse(new InputStreamReader(in));
        return new Gson().fromJson(element.getAsJsonObject().toString(), FilteredObjects.class);
    }

    /**
     * A Method for Initializing Intents for this activity
     *
     * @return {@link Boolean} the intent received is a search action
     */
    public boolean initializeIntent() {
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            contentTitleText.setText(R.string.search_result);
            String query = intent.getStringExtra(QUERY);
            doMySearch(query);
            return true;
        }
        return false;
    }

    /**
     * A Method to execute search query
     *
     * @param query {@link String}  represents the query typed by the user in the search bar
     */
    private void doMySearch(String query) {
        searchView.onActionViewCollapsed();
        searchView.clearFocus();
        queryText = query;
        loadPhotos(query, 1);
    }

    /**
     * A Method to call Load the photo list on the current view
     * @param text {@link String} title or tag for a photo
     * @param page {@link int} the page number to be retrieved
     *
     * */
    private void loadPhotos(String text, int page) {
        this.photosPresenter.loadPhotos(text, page);
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.photosPresenter.pause();
    }

    @Override
    protected void onDestroy() {
        this.photosPresenter.destroy();
        this.recyclerView.setAdapter(null);
        super.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.photosPresenter.resume();
    }

    /**
     * A Method to setup the Recycleview with {@link PhotosAdapter} and {@link PhotosLayoutManager}
     *
     * */

    private void setupRecyclerView() {
        photosLayoutManager =  new PhotosLayoutManager(2);

        this.recyclerView.setLayoutManager(photosLayoutManager);
        //        photosLayoutManager.setAutoMeasureEnabled(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//                @SuppressLint("RestrictedApi")
//                @Override
//                public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                    photosLayoutManager.invalidateSpanAssignments();
//                    NestedScrollView v = (NestedScrollView) view;
//                    if( (v.computeVerticalScrollOffset() == (v.computeVerticalScrollRange() - v.getHeight()) )) {
//                        loadPhotos(++page);
//                    }
//
//
////
////                    int visibleItemCount = photosLayoutManager.getChildCount();
////                    int totalItemCount=photosLayoutManager.getItemCount();
////                    int firstVisibleItemPosition = photosLayoutManager.findFirstVisibleItemPositions(null)[0];
////                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
////                            && firstVisibleItemPosition >= 0&& totalItemCount >= PAGE_SIZE) {
////                        loadPhotos(++page);
////                    }
//                }
//            });
//        }
        this.recyclerView.addOnScrollListener(new ScrollListener());
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.recyclerView.setAdapter(photosAdapter);

//        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                if (recyclerViewReadyCallback != null) {
//                    recyclerViewReadyCallback.onLayoutReady();
//                }
//                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });

//        recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
//            @Override
//            public void onLayoutReady() {
//               Toast.makeText(context(),"done laying down items",Toast.LENGTH_SHORT).show();
//            }
//        };

    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    @Override
    public void renderPhotosList(PhotosModel photosModel) {
        if(photosModel!=null){
//            Toast.makeText(this,photosModel.getPage() + " " + photosModel.getPages(),Toast.LENGTH_LONG).show();
//            this.textViewPage.setText("page : "+photosModel.getPage());
//            PAGE_SIZE = photosModel.getPages();
//            this.textViewPages.setText("Pages : "+photosModel.getPages());
//            this.textViewTotal.setText("Total :" +photosModel.getTotal());
            this.photosAdapter.setPhotosCollection(photosModel.getPhoto());
        }
    }

    @Override
    public void showLoading() {
        this.progressBar.setVisibility(View.VISIBLE);
        this.setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.progressBar.setVisibility(View.GONE);
        this.setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        button.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        button.setVisibility(View.GONE);

    }
    @OnClick(R.id.bt_retry)
    void buttonRetryClicked(){
        MainActivity.this.loadPhotos(queryText, page);
    }
    @Override
    public void showError(String message) {

    }

    /**
     * A Method to create an intent with extras filled with the photo informations
     * that gets sent to the {@link PhotoDetailsActivity}
     *@param photoClicked The photo that was clicked by the user
     * */
    public  void setPhotoClicked(PhotoModel photoClicked){
        Intent intent = new Intent(this, PhotoDetailsActivity.class);
        intent.putExtra(PHOTO_ID, photoClicked.getId());
        intent.putExtra(PHOTO_OWNER, photoClicked.getOwner());
        intent.putExtra(PHOTO_FARM, photoClicked.getFarm());
        intent.putExtra(PHOTO_SERVER, photoClicked.getServer());
        intent.putExtra(PHOTO_SECRET, photoClicked.getSecret());
        intent.putExtra(PHOTO_TITLE,photoClicked.getTitle());
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isFocused()) {
            searchView.onActionViewCollapsed();
            searchView.clearFocus();
        }
        super.onBackPressed();
    }

    @Override
    public Context context() {
        return this.getApplicationContext();
    }

    /**
     * A scroll listener to know when you reach the end of the recycleview and load a new list
     * */
    private class ScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            PhotosLayoutManager photosLayoutManager=(PhotosLayoutManager)recyclerView.getLayoutManager();
            int visibleItemCount = photosLayoutManager.getChildCount();
            int totalItemCount=photosLayoutManager.getItemCount();
            int firstVisibleItemPosition = photosLayoutManager.findFirstVisibleItemPositions(null)[0];
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE) {
                photosLayoutManager.invalidateSpanAssignments();
//                loadPhotos(++page); TODO: Request a behavior subject on the GetPhoto!
            }
        }
    }

//    private RecyclerViewReadyCallback recyclerViewReadyCallback;
//
//    public interface RecyclerViewReadyCallback {
//        void onLayoutReady();
//    }

}
