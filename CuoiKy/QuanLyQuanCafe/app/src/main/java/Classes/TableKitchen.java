package Classes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TableKitchen {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("time_in")
    @Expose
    public String timeIn;
    @SerializedName("time_out")
    @Expose
    public String timeOut;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("id_user_in")
    @Expose
    public Integer idUserIn;
    @SerializedName("id_user_out")
    @Expose
    public Integer idUserOut;
    @SerializedName("id_table")
    @Expose
    public Integer idTable;

}
