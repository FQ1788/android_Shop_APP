package tw.com.feast_test0301.utils;

import android.graphics.drawable.Drawable;

public class OrderBean {

    private String orderId,userAccount,comName,comNo;
    private int comNumber,comTotal;
    private Drawable comImage;

    public Drawable getComImage() {
        return comImage;
    }

    public void setComImage(Drawable comImage) {
        this.comImage = comImage;
    }

    public String getId() {
        return orderId;
    }

    public void setId(String id) {
        this.orderId = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComNo() {
        return comNo;
    }

    public void setComNo(String comNo) {
        this.comNo = comNo;
    }

    public int getComNumber() {
        return comNumber;
    }

    public void setComNumber(int comNumber) {
        this.comNumber = comNumber;
    }

    public int getComTotal() {
        return comTotal;
    }

    public void setComTotal(int comTotal) {
        this.comTotal = comTotal;
    }

    @Override
    public String toString() {
        return "{" +
                "orderId='" + orderId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", comName='" + comName + '\'' +
                ", comNo='" + comNo + '\'' +
                ", comNumber=" + comNumber +
                ", comTotal=" + comTotal +
                '}';
    }
}
