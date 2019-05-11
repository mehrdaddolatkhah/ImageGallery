package ir.mskm.mskmimagegallery.Data;

import android.content.Context;
import android.content.SharedPreferences;
import ir.mskm.mskmimagegallery.Data.Models.EnumNumbers;
import ir.mskm.mskmimagegallery.Data.Models.EnumViews;
import ir.mskm.mskmimagegallery.Data.Models.LoadPrefsModel;

public class ImagePrefs {

    private SharedPreferences mPreferences;

    private final String TAG_STATELAYOUYT = "TAG_STATELAYOUYT";
    private final String TAG_STATETITLE = "TAG_STATETITLE";
    private final String TAG_VIEW_TYPE = "TAG_VIEW_TYPE";


    private final String PATH = "ir.mskm.mskmimagegallery.stateShared";


    public ImagePrefs(Context context) {
        this.mPreferences = context.getSharedPreferences(PATH, context.MODE_PRIVATE);
    }


    public void save(EnumNumbers witchLayoutSelected, boolean isTitle) {
       setState(witchLayoutSelected);
       setIsTitle(isTitle);
    }

    public void saveView(EnumViews viewType) {
        setView(viewType);
    }

    public LoadPrefsModel load() {
        LoadPrefsModel loadPrefsModel = new LoadPrefsModel();
        loadPrefsModel.setState(getState());
        loadPrefsModel.setTitle(isTitle());
        return loadPrefsModel;
    }



    private void setState(EnumNumbers witchLayoutSelected) {
        mPreferences.edit().putInt(TAG_STATELAYOUYT, witchLayoutSelected.ordinal()).apply();
    }

    private void setView(EnumViews viewType) {
        mPreferences.edit().putInt(TAG_VIEW_TYPE, viewType.ordinal()).apply();
    }

    private void setIsTitle(boolean isTitle) {
        mPreferences.edit().putBoolean(TAG_STATETITLE, isTitle).apply();
    }


    public EnumNumbers getState() {
        return EnumNumbers.values()[mPreferences.getInt(TAG_STATELAYOUYT, 0)];
    }

    public EnumViews getView() {
        return EnumViews.values()[mPreferences.getInt(TAG_VIEW_TYPE, 0)];
    }

    public boolean isTitle() {
        return mPreferences.getBoolean(TAG_STATETITLE, true);
    }

}
