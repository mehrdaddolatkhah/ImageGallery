package ir.mskm.mskmimagegallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ir.mskm.mskmimagegallery.Activities.GalleryFullView;
import ir.mskm.mskmimagegallery.Activities.GalleryListView;
import ir.mskm.mskmimagegallery.Data.ImagePrefs;
import ir.mskm.mskmimagegallery.Data.Models.EnumViews;
import ir.mskm.mskmimagegallery.Data.Models.FullScreenModel;
import ir.mskm.mskmimagegallery.Data.Models.ImageGalleryModel;

import java.util.ArrayList;

public class GalleryWorker {

    private final String TAG_MODEL = "TAG_MODEL";
    private final String TAG_VIEW_TYPE = "TAG_VIEW_TYPE";

    private ImagePrefs imagePrefs;

    private Context context;
    private ArrayList<ImageGalleryModel> images;


    public GalleryWorker(Context context) {
        this.context = context;
    }

    public ArrayList<ImageGalleryModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageGalleryModel> images) {
        this.images = images;
    }

    public void clearImages() {
        images.clear();
    }

    public void openDialogView(Context context, EnumViews viewType) throws ClassNotFoundException {

        if (getImages() != null && getImages().size() != 0) {
            imagePrefs = new ImagePrefs(context);

            Intent showListView = new Intent(context, GalleryListView.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(TAG_MODEL, getImages());
            showListView.putExtras(bundle);
            showListView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            imagePrefs.saveView(viewType);
            context.startActivity(showListView);
        } else {
            Toast.makeText(context, "please add image" , Toast.LENGTH_LONG).show();
        }
    }

    public void openFullView(int position) {

        FullScreenModel fullScreenModel = new FullScreenModel(position, images);

        if (getImages() != null && getImages().size() != 0) {
            Intent showListView = new Intent(context, GalleryFullView.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable(TAG_MODEL, fullScreenModel);
            showListView.putExtras(bundle);
            showListView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(showListView);
        } else {
            Toast.makeText(context, "please add image" , Toast.LENGTH_LONG).show();
        }
    }

}
