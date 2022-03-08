package tw.com.feast_test0301.utils;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

public class DataBean {
    private String sComName,sComNumber, sComTotal, sComPrice, sUserAccount, sPKID;
    private Drawable comImage;

    public Drawable getComImage() {
        return comImage;
    }

    public void setComImage(Drawable comImage) {
        this.comImage = comImage;
    }

    public String getsComName() {
        return sComName;
    }

    public void setsComName(String sComName) {
        this.sComName = sComName;
    }

    public String getsComNumber() {
        return sComNumber;
    }

    public void setsComNumber(String sComNumber) {
        this.sComNumber = sComNumber;
    }

    public String getsComTotal() {
        return sComTotal;
    }

    public void setsComTotal(String sComTotal) {
        this.sComTotal = sComTotal;
    }

    public String getsComPrice() {
        return sComPrice;
    }

    public void setsComPrice(String sComPrice) {
        this.sComPrice = sComPrice;
    }

    public String getsUserAccount() {
        return sUserAccount;
    }

    public void setsUserAccount(String sUserAccount) {
        this.sUserAccount = sUserAccount;
    }

    public String getsPKID() {
        return sPKID;
    }

    public void setsPKID(String sPKID) {
        this.sPKID = sPKID;
    }
}
