package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("fullname")
    @Expose
    public String fullname;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("salary")
    @Expose
    public int salary;
    @SerializedName("access_level")
    @Expose
    public int accessLevel;

    public static int logged_id;

}
