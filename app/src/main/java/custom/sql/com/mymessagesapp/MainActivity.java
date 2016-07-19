package custom.sql.com.mymessagesapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b= (Button) findViewById(R.id.fillBtn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             fillDummyData();
            }
        });


        refreshList();


    }

    private void refreshList() {

        ListView lv=(ListView) findViewById(R.id.messagesLV);
        MySqlOpenHelper helper= new MySqlOpenHelper(this);

        Cursor c= helper.getReadableDatabase().query(DBConstants.tableName, null,null, null,null,null,null);


        //create an adapter
        MyMsgsAdapter adapter= new MyMsgsAdapter(this, c);

        lv.setAdapter(adapter);

    }


    private void fillDummyData() {

        MySqlOpenHelper helper= new MySqlOpenHelper(MainActivity.this);
        ContentValues msg1= new ContentValues();
        msg1.put(DBConstants.senderName, "moshe ofir");
        msg1.put(DBConstants.msgText, "you wont get more tirgul...");
        msg1.put(DBConstants.isread, 0);

        helper.getWritableDatabase().insert(DBConstants.tableName, null, msg1);


        ContentValues msg2= new ContentValues();
        msg2.put(DBConstants.senderName, "dad");
        msg2.put(DBConstants.msgText, "call mom...");
        msg2.put(DBConstants.isread, 1);

        helper.getWritableDatabase().insert(DBConstants.tableName, null, msg2);

        refreshList();
    }
}
