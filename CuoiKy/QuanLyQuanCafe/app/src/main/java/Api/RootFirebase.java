package Api;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RootFirebase {
    public static DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    public static DatabaseReference rootKitchen = root.child("notification").child("kitchen");
    public static DatabaseReference rootNotify = root.child("notification");
    public static DatabaseReference rootTableKitchen;

    public static String getDay(){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date today = new Date();
        return format.format(today);
    }

    public static String getTime(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date today = new Date();
        return format.format(today);
    }

}
