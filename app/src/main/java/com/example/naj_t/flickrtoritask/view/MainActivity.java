package com.example.naj_t.flickrtoritask.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naj_t.flickrtoritask.AndroidApplication;
import com.example.naj_t.flickrtoritask.DPINJ.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.R;
import com.example.naj_t.flickrtoritask.adapter.PhotosAdapter;
import com.example.naj_t.flickrtoritask.adapter.PhotosLayoutManager;
import com.example.naj_t.flickrtoritask.models.PhotoModel;
import com.example.naj_t.flickrtoritask.models.PhotosModel;
import com.example.naj_t.flickrtoritask.presenters.PhotosPresenter;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity  implements PhotosListView{
    public static final String PHOTO_ID = "ID";
    public static final String PHOTO_OWNER = "OWNER";
    public static final String PHOTO_FARM = "FARM";
    public static final String PHOTO_TITLE = "TITLE";
    public static final String PHOTO_SERVER = "SERVER";
    public static final String PHOTO_SECRET = "SECRET";

    @Inject
    PhotosPresenter photosPresenter;
    @Inject
    PhotosAdapter photosAdapter;

    @BindView(R.id.page)
    TextView textViewPage;
    @BindView(R.id.pages)
    TextView textViewPages;
    @BindView(R.id.total)
    TextView textViewTotal;
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.load_prog)
    ProgressBar progressBar;
    @BindView(R.id.bt_retry)
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        initializingRealm(this);
        this.photosPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadPhotos();
        }
        setupRecyclerView();

    }

    public void initializingRealm(Context context){
        Realm.init(context);
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(context)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(context))
//                        .build());
    }

    private void loadPhotos() {
        this.photosPresenter.loadPhotos();
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


    private void setupRecyclerView() {
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager( new PhotosLayoutManager(2));

        this.recyclerView.setAdapter(photosAdapter);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    @Override
    public void renderPhotosList(PhotosModel photosModel) {
        if(photosModel!=null){
//            Toast.makeText(this,photosModel.getPage() + " " + photosModel.getPages(),Toast.LENGTH_LONG).show();
            this.textViewPage.setText("page : "+photosModel.getPage());
            this.textViewPages.setText("Pages : "+photosModel.getPages());
            this.textViewTotal.setText("Total :" +photosModel.getTotal());
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
        MainActivity.this.loadPhotos();
    }
    @Override
    public void showError(String message) {

    }

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
    public Context context() {
        return this.getApplicationContext();
    }
}
