package com.example.naj_t.flickrtoritask.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.naj_t.flickrtoritask.R;
import com.example.naj_t.flickrtoritask.models.PhotoModel;
import com.example.naj_t.flickrtoritask.view.MainActivity;
import com.squareup.haha.perflib.Main;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PhotosAdapter  extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>{

    @Inject
    Picasso picasso;
    private List<PhotoModel> photosCollection;
    private final LayoutInflater layoutInflater;
    private  Context context;

@Inject
    PhotosAdapter(Context context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.photosCollection = Collections.emptyList();
    }
    @Override
    public int getItemCount() {
        return (this.photosCollection != null) ? this.photosCollection.size() : 0;
    }
    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        final View view = this.layoutInflater.inflate(R.layout.row_card, parent, false);//Create a fragment
        return new PhotosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PhotosViewHolder holder, final int position) {
        PhotoModel photoModel = this.photosCollection.get(position);
//        holder.textViewTitle.setText(photoModel.getParam1());
        String url = "https://farm"+photoModel.getFarm()+".staticflickr.com/"+photoModel.getServer()+"/"+photoModel.getId()+"_"+photoModel.getSecret()+".jpg";
        picasso.load(url).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).setPhotoClicked(photosCollection.get(position));
            }
        });
//        holder.textViewId.setText(photoModel.getId());
//        holder.textViewServer.setText(photoModel.getServer());
//        holder.textViewOwner.setText(photoModel.getOwner());
        //TODO: OnCLickLIstener on images etc;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    public  void setPhotosCollection(Collection<PhotoModel> photosCollection){
    if(photosCollection!=null){
        this.photosCollection = (List<PhotoModel>) photosCollection;
        this.notifyDataSetChanged();
    }


    }
    static class PhotosViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.title)
//        TextView textViewTitle;
        @BindView(R.id.image)
        ImageView imageView;
//        @BindView(R.id.titleFrame)
//        FrameLayout frameLayout;
//        @BindView(R.id.id)
//        TextView textViewId;
//        @BindView(R.id.server)
//        TextView textViewServer;
//        @BindView(R.id.owner)
//        TextView textViewOwner;
        PhotosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(frameLayout.getVisibility()==View.GONE){
//                        frameLayout.setVisibility(View.VISIBLE);
//                        frameLayout.animate()
//                                .alpha(1f)
//                                .setDuration(300);
//                    }else{
//
//                        frameLayout.animate()
//                                .alpha(0f)
//                                .setDuration(300);
//                        frameLayout.setVisibility(View.GONE);
//                    }
//                }
//            });
        }
    }
}
