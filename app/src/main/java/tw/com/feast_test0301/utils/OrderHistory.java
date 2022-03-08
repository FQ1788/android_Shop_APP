package tw.com.feast_test0301.utils;

public class OrderHistory {
    private String storeName;
    private String orderData;
    private int storeImage;

    public OrderHistory(String storeName, String orderData, int storeImage) {
        this.storeName = storeName;
        this.orderData = orderData;
        this.storeImage = storeImage;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOrderData() {
        return orderData;
    }

    public void setOrderData(String orderData) {
        orderData = orderData;
    }

    public int getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(int storeImage) {
        this.storeImage = storeImage;
    }
}
