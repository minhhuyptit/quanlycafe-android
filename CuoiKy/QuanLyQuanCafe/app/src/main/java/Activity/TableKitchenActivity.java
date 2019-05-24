package Activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import Adapter.TableKitchenRecyclerViewAdapter;
import Api.CommonAPI;
import Api.RootFirebase;
import Classes.TableKitchen;
import Classes.TableOrder;
import xyz.khang.quanlyquancafe.R;
import xyz.khang.quanlyquancafe.databinding.ActivityTableKitchenBinding;

public class TableKitchenActivity extends AppCompatActivity implements TableKitchenRecyclerViewAdapter.Callback {
    ActivityTableKitchenBinding binding;
    public static List<TableOrder> tableOrders;
    TableKitchenRecyclerViewAdapter adapter;
    int sumOrder = 0;

    //------------Sound
    private SoundPool soundPool;
    private AudioManager audioManager;
    // Số luồng âm thanh phát ra tối đa.
    private static final int MAX_STREAMS = 5;
    // Chọn loại luồng âm thanh để phát nhạc.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private boolean loaded;
    private int soundIdDestroy;
    private int soundIdGun;
    private float volume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_kitchen);
        tableOrders = new ArrayList<>();
        setEvent();
        Query queryChickenCart = RootFirebase.rootKitchen.child(RootFirebase.getDay()).orderByChild("status").equalTo("order");
//        queryChickenCart.addChildEventListener(eventListener);
        queryChickenCart.addValueEventListener(valueEventListener);

        //Notification Sound-----------------------------------------------------------------------
        // Đối tượng AudioManager sử dụng để điều chỉnh âm lượng.
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        // Chỉ số âm lượng hiện tại của loại luồng nhạc cụ thể (streamType).
        float currentVolumeIndex = (float) audioManager.getStreamVolume(streamType);
        // Chỉ số âm lượng tối đa của loại luồng nhạc cụ thể (streamType).
        float maxVolumeIndex  = (float) audioManager.getStreamMaxVolume(streamType);
        // Âm lượng  (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;
        // Cho phép thay đổi âm lượng các luồng kiểu 'streamType' bằng các nút
        // điều khiển của phần cứng.
        this.setVolumeControlStream(streamType);

        // Với phiên bản Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // Với phiên bản Android SDK < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // Sự kiện SoundPool đã tải lên bộ nhớ thành công.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                loaded = true;
            }
        });

        // Tải file nhạc tiếng vật thể bị phá hủy (destroy.war) vào SoundPool.
        this.soundIdDestroy = this.soundPool.load(this, R.raw.notify,1);

    }
    // Khi người dùng nhấn vào button "Gun".
    public void playSoundGun(View view)  {
        if(loaded)  {
            float leftVolumn = volume;
            float rightVolumn = volume;
            // Phát âm thanh tiếng súng. Trả về ID của luồng mới phát ra.
            int streamId = this.soundPool.play(this.soundIdDestroy,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    void setEvent() {
        RecyclerView recyclerView = binding.rvTable;
        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recyclerView.setLayoutManager(manager);
        adapter = new TableKitchenRecyclerViewAdapter(this, tableOrders);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSelectTable(int position) {
        Log.e("Key", tableOrders.get(position).getKey());
        RootFirebase.rootTableKitchen = RootFirebase.rootKitchen.child(RootFirebase.getDay()).child(tableOrders.get(position).getKey()).child("status");
        Intent intent = new Intent(getApplicationContext(), OrderDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            Log.e("onDataChange", "-----");
            tableOrders.clear();
            for(DataSnapshot d: dataSnapshot.getChildren()){
                TableOrder tableOrder = d.getValue(TableOrder.class);
                tableOrder.setKey(d.getKey());
                tableOrders.add(tableOrder);
            }

            adapter.notifyDataSetChanged();
            //Notification----Nếu số lượng hiện tại lớn hơn so với số lượng trước đó => có bàn vừa order => thông báo
            if(tableOrders.size()>sumOrder){
                sumOrder = tableOrders.size();
                int positionLast = sumOrder-1;
                if(loaded)  {
                    float leftVolumn = volume;
                    float rightVolumn = volume;
                    // Phát âm thanh tiếng súng. Trả về ID của luồng mới phát ra.
                    int streamId = TableKitchenActivity.this.soundPool.play(TableKitchenActivity.this.soundIdDestroy,leftVolumn, rightVolumn, 1, 0, 1f);
                }
                Toast.makeText(TableKitchenActivity.this, tableOrders.get(positionLast).getTable() + ": Vừa order", Toast.LENGTH_SHORT).show();
            }else sumOrder = tableOrders.size();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    ChildEventListener eventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Log.e("onChildAdded", "--");

            for(TableOrder tableOrder: tableOrders)
                if(dataSnapshot.getKey().equals(tableOrder.getKey())) return;
            TableOrder tableOrder = dataSnapshot.getValue(TableOrder.class);
            tableOrder.setKey(dataSnapshot.getKey());
                Toast.makeText(TableKitchenActivity.this, tableOrder.getTable() + ": Vừa order", Toast.LENGTH_SHORT).show();
            tableOrders.add(tableOrder);
            Log.e("Size: ", tableOrders.size() + "");
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Toast.makeText(TableKitchenActivity.this, "Changed " + dataSnapshot.toString(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            TableOrder tableOrder = dataSnapshot.getValue(TableOrder.class);
            for(TableOrder t: tableOrders){
                if(t.getKey().equals(dataSnapshot.getKey())){
                    tableOrders.remove(t);
                    return;
                }
            }
            Toast.makeText(TableKitchenActivity.this, tableOrder.getTable() + ": Bắt đầu được làm nước", Toast.LENGTH_SHORT).show();
            Log.e("Size: ", tableOrders.size() + "");

            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

}
