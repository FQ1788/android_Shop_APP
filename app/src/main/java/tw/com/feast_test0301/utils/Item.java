package tw.com.feast_test0301.utils;


public class Item {
    private int image;
    private int title;
    private String context;

    public Item(){}

    public Item(int image,int title){
        this.image=image;
        this.title=title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
