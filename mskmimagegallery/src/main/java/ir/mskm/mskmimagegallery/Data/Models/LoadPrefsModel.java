package ir.mskm.mskmimagegallery.Data.Models;

public class LoadPrefsModel {

    private EnumNumbers state;
    private boolean isTitle;


    public EnumNumbers getState() {
        return state;
    }

    public void setState(EnumNumbers state) {
        this.state = state;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }
}
