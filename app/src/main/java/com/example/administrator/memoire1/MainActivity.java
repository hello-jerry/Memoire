package com.example.administrator.memoire1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MainActivity extends Activity {
    Button AddButton;
    Button FinishButton;
    SQLiteDbHelper sqLiteDbHelper;
    ListView listview;
    static SimpleAdapter adapter;
    @Override    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button AddButton=(Button)findViewById(R.id.button1);
        AddButton.setOnClickListener(new Button.OnClickListener(){
                                         @Override
                                         public void onClick(View view) {
                                             Intent intent=new Intent(MainActivity.this,AddActivity.class);
                                             startActivity(intent);
                                         }
                                     }
        );
        sqLiteDbHelper=new SQLiteDbHelper(this,"contact.db",null,1);
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        MessageDao messageDao=new MessageDao(db);
        List<Message> list= messageDao.query();
        int m = list.size();
        Message message;
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < m; i++) {
            message = list.get(i);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",message.getId());
            map.put("date", message.getDate());
            map.put("title", message.getTitle());
            map.put("content", message.getContent());
            data.add(map);
        }
            listview = (ListView) findViewById(R.id.listview_note);
        //给列表增加内容 利用message_layout模板。
            adapter = new SimpleAdapter(MainActivity.this, data, R.layout.message, new String[]{"id","date", "title","content"}, new int[]{R.id.msg_id,R.id.msg_date, R.id.msg_title});
            adapter.notifyDataSetChanged();
            listview.setAdapter(adapter);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView Id=(TextView)view.findViewById(R.id.msg_id);
                    String strid=Id.getText().toString();
                    Intent intent=new Intent(MainActivity.this,XsActivity.class);
                    intent.putExtra("id",strid);
                    startActivity(intent);
                }
            });
    }
}

