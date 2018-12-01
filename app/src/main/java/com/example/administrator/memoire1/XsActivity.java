package com.example.administrator.memoire1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class XsActivity extends Activity {
    SQLiteDbHelper sqLiteDbHelper;
    MessageDao messageDao;
    int id_query;
    EditText detail_title;
    EditText detail_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xianshi);
        sqLiteDbHelper=new SQLiteDbHelper(this,"contact.db",null,1);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        id_query=Integer.parseInt(id);
        SQLiteDatabase db = sqLiteDbHelper.getWritableDatabase();
        messageDao=new MessageDao(db);
        List<Message> list=messageDao.visit(id);
        detail_title=(EditText)this.findViewById(R.id.EditText1);

        for(int i = 0; i < list.size(); i++){
            System.out.println("title " + list.get(i).getTitle());
            System.out.println("content " + list.get(i).getContent());
        }

        messageDao.query();

        detail_title.setText(list.get(0).getTitle());
        detail_content=(EditText)this.findViewById(R.id.EditText2);
        detail_content.setText(list.get(0).getContent());
    }
    //返回按钮
        public void btnReturn_detail(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    //完成按钮
    public void btnUpdate_detail(View view){
        Intent intent=getIntent();
        String Id=intent.getStringExtra("id");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd");// HH:mm:ss
        Date date = new Date(System.currentTimeMillis());
        EditText txtTitle = (EditText) findViewById(R.id.EditText1);
        EditText txtContext = (EditText) findViewById(R.id.EditText2);
        String dates = simpleDateFormat.format(date);
        String title = txtTitle.getText().toString();
        String context = txtContext.getText().toString();
        Message message = new Message();
        message.setId(Integer.valueOf(Id));
        message.setDate(dates);
        message.setContent(context);
        message.setTitle(title);
        // ok
        boolean ok =  messageDao.update(message);
        if(ok)
            Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    //删除按钮
    public void btnDelete_detail(View view){
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        messageDao.delete(id);
        intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}

