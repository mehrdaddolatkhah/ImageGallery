package ir.mskm.mskmimagegallery.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import ir.mskm.mskmimagegallery.Activities.GalleryFullView;
import ir.mskm.mskmimagegallery.Data.ImagePrefs;
import ir.mskm.mskmimagegallery.Data.Models.EnumNumbers;
import ir.mskm.mskmimagegallery.Data.Models.FullScreenModel;
import ir.mskm.mskmimagegallery.Data.Models.ImageGalleryModel;
import ir.mskm.mskmimagegallery.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ImageGalleryModel> items;
    private ImagePrefs imagePrefs;



    private final String TAG_MODEL = "TAG_MODEL";



    public ImageAdapter(Context context, ArrayList<ImageGalleryModel> items) {
        this.context = context;
        this.items = items;
        imagePrefs = new ImagePrefs(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        if (isList()) {
            View v = inflater.inflate(R.layout.item_linear_recycler, parent, false);
            return new ViewHolder(v);
        } else {
            View v = inflater.inflate(R.layout.item_recycler, parent, false);
            return new ViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.bindView(items.get(position), position);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgItem;
        TextView txtItem;
        TextView txtDesc;
        LinearLayout linTxtImage;
        CardView cardItem;

        ViewHolder(View itemView) {
            super(itemView);
            imgItem = itemView.findViewById(R.id.img_item);
            txtItem = itemView.findViewById(R.id.txt_item);
            txtDesc = itemView.findViewById(R.id.txt_desc);
            linTxtImage = itemView.findViewById(R.id.linTxtImage);
            cardItem = itemView.findViewById(R.id.card_item);
        }

        void bindView(final ImageGalleryModel model, final int pos) {

            Glide.with(context)
                    .load(model.getImagePath())
                    .into(imgItem);

            txtItem.setText(model.getImageTitle());
            txtDesc.setText(model.getImageDesc());

            if (isList()) {
                txtItem.setVisibility(View.VISIBLE);
                txtDesc.setVisibility(View.VISIBLE);
            } else {
                if (isTitle()) {
                    linTxtImage.setVisibility(View.VISIBLE);
                } else {
                    linTxtImage.setVisibility(View.INVISIBLE);
                }
            }

            cardItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent showFullScreenPic = new Intent(context, GalleryFullView.class);
                   Bundle bundle = new Bundle();
                     FullScreenModel fullScreenModel = new FullScreenModel(pos, items);
                    bundle.putParcelable(TAG_MODEL,fullScreenModel);
                    showFullScreenPic.putExtras(bundle);
                    showFullScreenPic.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(showFullScreenPic);
                }
            });

        }
    }

    private boolean isList() {
        if (imagePrefs.getState() == EnumNumbers.LISTVIEW) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isTitle() {
        return imagePrefs.isTitle();
    }

}
