package ir.mskm.mskmimagegallery.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.*;
import ir.mskm.mskmimagegallery.Adapters.ImageAdapter;
import ir.mskm.mskmimagegallery.Data.ImagePrefs;
import ir.mskm.mskmimagegallery.Data.Models.EnumNumbers;
import ir.mskm.mskmimagegallery.Data.Models.EnumViews;
import ir.mskm.mskmimagegallery.Data.Models.ImageGalleryModel;
import ir.mskm.mskmimagegallery.Data.Models.LoadPrefsModel;
import ir.mskm.mskmimagegallery.R;

import java.util.ArrayList;

import static android.support.v7.widget.StaggeredGridLayoutManager.VERTICAL;

public class GalleryListView extends AppCompatActivity implements View.OnClickListener {

    private final String TAG_MODEL = "TAG_MODEL";
    private final String TAG_VIEW_TYPE = "TAG_VIEW_TYPE";

    private RecyclerView recyclerImage;
    //private Toolbar mTopToolbar;

    private LinearLayout linList;
    private LinearLayout linTwoColumn;
    private LinearLayout linThreeColumn;
    private LinearLayout linLinearList;
    private Switch swTitleMain;
    private Switch swTitleDialog;

    private ImageView imgList;
    private ImageView imgTwoCol;
    private ImageView imgThreeCol;

    private TextView txtList;
    private TextView txtTwoCol;
    private TextView txtThreeCol;

    private EnumNumbers witchLayoutSelected = EnumNumbers.LISTVIEW;

    private boolean isTitle = true;

    private ArrayList<ImageGalleryModel> imagesOb;

    private Dialog dialog;

    private ImagePrefs imagePrefs;

    private ImageAdapter adapter;

    private FloatingActionButton fabMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_list);
        imagePrefs = new ImagePrefs(this);
        linLinearList = findViewById(R.id.lin_linear_view);
        fabMenu = findViewById(R.id.fab_menu);

        if (imagePrefs.getView() == EnumViews.DIALOGVIEW) {
            linLinearList.setVisibility(View.GONE);
            fabMenu.setVisibility(View.VISIBLE);
        } else {
            linLinearList.setVisibility(View.VISIBLE);
            fabMenu.setVisibility(View.GONE);
        }


        showLayoutLinear();
        getItem();


        recyclerImage = findViewById(R.id.recycler_images);


        adapter = new ImageAdapter(getApplicationContext(),imagesOb);

        imagePrefs.getView();


       // adapter = new ImageAdapter(getApplicationContext(),imagesOb);
        recyclerImage.setHasFixedSize(true);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerImage.setLayoutAnimation(animation);
       setLayout();

       fabMenu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showLayoutDialog();
           }
       });

    }


    private void showLayoutDialog() {

        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_view);

        linList = dialog.findViewById(R.id.lin_list);
        linTwoColumn = dialog.findViewById(R.id.lin_two_column);
        linThreeColumn = dialog.findViewById(R.id.lin_three_column);

        swTitleDialog = dialog.findViewById(R.id.sw_title_dialog);

        imgList = dialog.findViewById(R.id.img_list);
        imgTwoCol = dialog.findViewById(R.id.img_two_col);
        imgThreeCol = dialog.findViewById(R.id.img_three_col);

        txtList = dialog.findViewById(R.id.txt_list);
        txtTwoCol = dialog.findViewById(R.id.txt_two_col);
        txtThreeCol = dialog.findViewById(R.id.txt_three_col);

        linList.setOnClickListener(this);
        linTwoColumn.setOnClickListener(this);
        linThreeColumn.setOnClickListener(this);

        swTitleDialog.setOnClickListener(this);

        swTitleDialog.setChecked(isTitle);
        witchItemSelected(witchLayoutSelected);


        if (witchLayoutSelected == EnumNumbers.LISTVIEW) {
            swTitleDialog.setChecked(true);
            swTitleDialog.setEnabled(false);
        }

        swTitleDialog.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    isTitle = true;
                    saveState();
                    setLayout();

                } else {
                    isTitle = false;
                    saveState();
                    setLayout();
                }
            }
        });

        dialog.show();
    }


    private void showLayoutLinear() {

        imgList = findViewById(R.id.img_list);
        imgTwoCol = findViewById(R.id.img_two_col);
        imgThreeCol = findViewById(R.id.img_three_col);
        swTitleMain = findViewById(R.id.sw_title_main);

        imgList.setOnClickListener(this);
        imgTwoCol.setOnClickListener(this);
        imgThreeCol.setOnClickListener(this);

        swTitleMain.setOnClickListener(this);

        swTitleMain.setChecked(isTitle);
        //witchItemSelected(witchLayoutSelected);

       /* if (witchLayoutSelected == EnumNumbers.LISTVIEW) {
            swTitleMain.setChecked(true);
            swTitleMain.setEnabled(false);
        }*/

        swTitleMain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    isTitle = true;
                    saveState();
                    setLayout();

                } else {
                    isTitle = false;
                    saveState();
                    setLayout();
                }
            }
        });

    }

    private void witchItemSelected(EnumNumbers viewType) {

        switch (viewType) {
            case LISTVIEW:

                imgList.setColorFilter(getApplicationContext().getResources().getColor(R.color.buttonPressed));
                imgTwoCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgThreeCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));

                    txtList.setTextColor(Color.parseColor("#00574B"));
                    txtTwoCol.setTextColor(Color.parseColor("#000000"));
                    txtThreeCol.setTextColor(Color.parseColor("#000000"));

                break;

            case TWOCOLUMNVIEW:

                imgList.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgTwoCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.buttonPressed));
                imgThreeCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));


                    txtList.setTextColor(Color.parseColor("#000000"));
                    txtTwoCol.setTextColor(Color.parseColor("#00574B"));
                    txtThreeCol.setTextColor(Color.parseColor("#000000"));


                break;

            case THREECOLUMNVIEW:

                imgList.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgTwoCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgThreeCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.buttonPressed));


                    txtList.setTextColor(Color.parseColor("#000000"));
                    txtTwoCol.setTextColor(Color.parseColor("#000000"));
                    txtThreeCol.setTextColor(Color.parseColor("#00574B"));

                break;
        }
    }


    private void getItem() {

        if (getIntent().hasExtra(TAG_MODEL)) {
            Intent getData = this.getIntent();
            Bundle bundle = getData.getExtras();
            imagesOb = bundle.getParcelableArrayList(TAG_MODEL);
        }
    }


  private void setLayout(){

        loadState();

      RecyclerView.LayoutManager mLayoutManager = null;

      switch (witchLayoutSelected) {
          case LISTVIEW:

              mLayoutManager =  new LinearLayoutManager(getApplicationContext());

              break;
          case TWOCOLUMNVIEW:

              mLayoutManager = new StaggeredGridLayoutManager(witchLayoutSelected.ordinal() + 1, VERTICAL);

              break;

          case THREECOLUMNVIEW:

              mLayoutManager = new StaggeredGridLayoutManager(witchLayoutSelected.ordinal() + 1, VERTICAL);

              break;
      }

      recyclerImage.setLayoutManager(mLayoutManager);
      recyclerImage.setAdapter(adapter);
  }

    private void loadState() {
        LoadPrefsModel loadPrefsModel = imagePrefs.load();
        witchLayoutSelected = loadPrefsModel.getState();
        isTitle = loadPrefsModel.isTitle();
    }

    private void saveState() {
        imagePrefs.save(witchLayoutSelected, isTitle);
    }


    @Override
    public void onClick(View v) {

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplicationContext(), resId);
        recyclerImage.setLayoutAnimation(animation);

        int i = v.getId();
        if (i == R.id.lin_list) {
            witchLayoutSelected = EnumNumbers.LISTVIEW;
            saveState();
            setLayout();
            dialog.dismiss();

        } else if (i == R.id.lin_two_column) {
            witchLayoutSelected = EnumNumbers.TWOCOLUMNVIEW;
            saveState();
            setLayout();
            dialog.dismiss();

        } else if (i == R.id.lin_three_column) {
            witchLayoutSelected = EnumNumbers.THREECOLUMNVIEW;
            saveState();
            setLayout();
            dialog.dismiss();

        } else if (i == R.id.img_list) {
            setStateInLinear(EnumNumbers.LISTVIEW);
        } else if (i == R.id.img_two_col) {
            setStateInLinear(EnumNumbers.TWOCOLUMNVIEW);
        } else if (i == R.id.img_three_col) {
            setStateInLinear(EnumNumbers.THREECOLUMNVIEW);
        }

    }

    private void setStateInLinear(EnumNumbers item) {
        witchLayoutSelected = item;
        saveState();
        setLayout();

        switch (item) {
            case LISTVIEW:

                swTitleMain.setChecked(true);
                swTitleMain.setEnabled(false);

                imgList.setColorFilter(getApplicationContext().getResources().getColor(R.color.buttonPressed));
                imgTwoCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgThreeCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                break;

            case TWOCOLUMNVIEW:

                swTitleMain.setEnabled(true);

                imgList.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgTwoCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.buttonPressed));
                imgThreeCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                break;

            case THREECOLUMNVIEW:

                swTitleMain.setEnabled(true);

                imgList.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgTwoCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.colorBlack));
                imgThreeCol.setColorFilter(getApplicationContext().getResources().getColor(R.color.buttonPressed));
                break;
        }
    }
}

