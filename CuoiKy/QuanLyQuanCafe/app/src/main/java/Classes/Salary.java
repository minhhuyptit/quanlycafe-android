package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Salary {
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("month")
    @Expose
    public int month;
    @SerializedName("year")
    @Expose
    public int year;
    @SerializedName("hour")
    @Expose
    public String hour;
    @SerializedName("salary")
    @Expose
    public float salary;
}
