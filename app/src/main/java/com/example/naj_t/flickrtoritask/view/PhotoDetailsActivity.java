package com.example.naj_t.flickrtoritask.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naj_t.flickrtoritask.AndroidApplication;
import com.example.naj_t.flickrtoritask.DPINJ.components.ApplicationComponent;
import com.example.naj_t.flickrtoritask.R;
import com.example.naj_t.flickrtoritask.models.PhotoModel;
import com.example.naj_t.flickrtoritask.models.UserModel;
import com.example.naj_t.flickrtoritask.presenters.PhotoDetailPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class PhotoDetailsActivity extends AppCompatActivity  implements PhotosDetailsView{
    public static final String PHOTO_ID = MainActivity.PHOTO_ID;
    public static final String PHOTO_OWNER = MainActivity.PHOTO_OWNER;
    public static final String PHOTO_FARM = MainActivity.PHOTO_FARM;
    public static final String PHOTO_TITLE = MainActivity.PHOTO_TITLE;
    public static final String PHOTO_SERVER = MainActivity.PHOTO_SERVER;
    public static final String PHOTO_SECRET = MainActivity.PHOTO_SECRET;

    @Inject
    PhotoDetailPresenter photoDetailPresenter;

    @Inject
    Picasso picasso;
    @BindView(R.id.imageSelected)
    ImageView imageView;
    @BindView(R.id.imageTitle)
    TextView title;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.userImage)
    ImageView userImage;
    @BindView(R.id.cancel)
    ImageView cancelImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        initialize((PhotoDetailsActivity) this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    public void initialize(final PhotoDetailsActivity context){
        ButterKnife.bind(context);
        this.getApplicationComponent().inject(context);
        this.photoDetailPresenter.setView(context);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });

        Intent i =context.getIntent();
        PhotoModel photoModel = getPhotoModelFromIntent(i);
        renderPhoto(photoModel);
//        Timber.tag("fatzo").d("PhotoModel%s", photoModel.getId());

    }
    private  Boolean intentIsFull(Intent i){
        return  i.hasExtra(PHOTO_ID)&&i.hasExtra(PHOTO_OWNER)&&i.hasExtra(PHOTO_FARM)&&i.hasExtra(PHOTO_SERVER)&&i.hasExtra(PHOTO_SECRET)&& i.hasExtra(PHOTO_TITLE);
    }

    private PhotoModel getPhotoModelFromIntent(Intent i){
        PhotoModel photoModel = null;
        if(intentIsFull(i)){
            photoModel = new PhotoModel();
            photoModel.setId(i.getStringExtra(PHOTO_ID));
            photoModel.setOwner(i.getStringExtra(PHOTO_OWNER));
            photoModel.setTitle(i.getStringExtra(PHOTO_TITLE));
            photoModel.setFarm(i.getStringExtra(PHOTO_FARM));
            photoModel.setSecret(i.getStringExtra(PHOTO_SECRET));
            photoModel.setServer(i.getStringExtra(PHOTO_SERVER));
            photoModel.setUser(new UserModel(i.getStringExtra(PHOTO_OWNER)));
        }
        return  photoModel;
    }

    public  void renderPhoto(PhotoModel photoModel){
        if(photoModel!=null){
            title.setText(photoModel.getTitle());
//            renderPhoto(photoModel.getUser());
            String url = "https://farm"+photoModel.getFarm()+".staticflickr.com/"+photoModel.getServer()+"/"+photoModel.getId()+"_"+photoModel.getSecret()+".jpg";
            picasso.load(url).into(imageView);
            photoDetailPresenter.loadPhotoDetails(photoModel.getOwner());
        }

    }

    @Override
    public void renderPhoto(UserModel userModel) {
        if(userModel!=null){
            //http://farm{icon-farm}.staticflickr.com/{icon-server}/buddyicons/{nsid}.jpg
            String url = "http://farm"+userModel.getIconfarm()+".staticflickr.com/"+userModel.getIconserver()+"/buddyicons/"+userModel.getNsid()+".jpg";
            picasso.load(url).noFade().placeholder(R.color.cardview_shadow_start_color).into(userImage);
            username.setText(userModel.getUsername());
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return  this.getApplicationContext();
    }
}
