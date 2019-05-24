package Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Adapter.TableRecyclerViewAdapter;
import Api.CommonAPI;
import Api.RootFirebase;
import Classes.Area;
import Classes.Table;
import Classes.TableKitchen;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableBinding;

public class TableActivity extends AppCompatActivity implements CommonAPI.Callback, TableRecyclerViewAdapter.Callback {
    ActivityTableBinding binding;
    NotificationCompat.Builder notBuiler;
    public static int index;

    HashMap<String, String> Notification = new HashMap<>();
    String keyTemp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_table);
        new CommonAPI(this).get_table(getApplicationContext(), Area.curent_id);

        Query queryOrderEnd = RootFirebase.rootKitchen.child(RootFirebase.getDay()).orderByChild("status").equalTo("end");
        queryOrderEnd.addChildEventListener(eventNotify);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CommonAPI(this).get_table(getApplicationContext(), Area.curent_id);
    }

    @Override
    public void onResponse(String response) {
        try {
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<Table>>() {
            }.getType();

            List<Table> tables = gson.fromJson(response, collectionType);

            RecyclerView recyclerView = binding.rvTable;
            GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 4);
            recyclerView.setLayoutManager(manager);
            TableRecyclerViewAdapter adapter = new TableRecyclerViewAdapter(tables, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSelectTable(int table_id, String table_name) {
        try {
            Table.current_id = table_id;
            Table.current_name = table_name;
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    ChildEventListener eventNotify = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.e("onChildAdded", "----------------");
            if(dataSnapshot.getKey().equals(keyTemp))return;
            keyTemp = dataSnapshot.getKey();
            NotifyChickenCart chickenCart = dataSnapshot.getValue(NotifyChickenCart.class);
            Log.e("onChildAdded", ++index + " " + chickenCart.toString());

            //-----------------------
            Notification.Builder nb = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                nb = new Notification.Builder(getApplicationContext(), "second")
                        .setShowWhen(true)
                        .setContentTitle(chickenCart.table)
                        .setContentText(chickenCart.toString())
                        .setSmallIcon(android.R.drawable.stat_notify_chat)
                        .setAutoCancel(true);
            }
            NotificationChannel chan2 = new NotificationChannel("second","sdfd", NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(chan2);
            manager.notify(index++, nb.build());
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}

class NotifyChickenCart {
    String table;
    String area;
    String loginname;
    String timein;

    public NotifyChickenCart() {
    }

    public NotifyChickenCart(String table, String area, String loginname, String timein) {
        this.table = table;
        this.area = area;
        this.loginname = loginname;
        this.timein = timein;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getTimein() {
        return timein;
    }

    public void setTimein(String timein) {
        this.timein = timein;
    }

    @Override
    public String toString() {
        return "NV: " + loginname + " order lúc " + getTimein() + " đã có nước";
    }
}

