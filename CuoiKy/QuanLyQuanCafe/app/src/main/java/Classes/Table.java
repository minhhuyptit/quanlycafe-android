package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Table {

    public static int current_id;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("seat")
    @Expose
    public int seat;
    @SerializedName("status")
    @Expose
    public int status;
    @SerializedName("id_area")
    @Expose
    public int idArea;

    public Table(int id, String name, int seat, int status, int idArea) {
        this.id = id;
        this.name = name;
        this.seat = seat;
        this.status = status;
        this.idArea = idArea;
    }
}
