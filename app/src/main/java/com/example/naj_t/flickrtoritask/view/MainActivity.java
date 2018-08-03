package com.example.naj_t.flickrtoritask.view;

import android.content.Context;
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
import com.example.naj_t.flickrtoritask.models.PhotosModel;
import com.example.naj_t.flickrtoritask.presenters.PhotosPresenter;
import com.facebook.stetho.Stetho;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity  implements PhotosListView{
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
        Realm.init(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
        this.photosPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadPhotos();
        }
        setupRecyclerView();

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
        this.recyclerView.setLayoutManager( new PhotosLayoutManager(context()));
        this.recyclerView.setAdapter(photosAdapter);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    @Override
    public void renderPhotosList(PhotosModel photosModel) {
        if(photosModel!=null){
            Toast.makeText(this,photosModel.getPage() + " " + photosModel.getPages(),Toast.LENGTH_LONG).show();
//            this.textViewPage.setText(photosModel.getPage());
 //           this.textViewPages.setText(photosModel.getPages());
   //         this.textViewTotal.setText(photosModel.getTotal());
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

    @Override
    public Context context() {
        return this.getApplicationContext();
    }
}
