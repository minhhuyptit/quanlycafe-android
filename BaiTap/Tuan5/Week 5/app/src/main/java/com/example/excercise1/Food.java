package com.example.excercise1;

public class Food {
    private String title;
    private int imageId;
    private String desc;

    public Food(String title, int imageId, String desc) {
        super();
        this.title = title;
        this.imageId = imageId;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //    @Override
//    public String toString() {
//        return this.countryName + "(Population:" + this.population +")";
//    }
}
