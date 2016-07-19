package custom.sql.com.mymessagesapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jbt on 19/07/2016.
 */
public class MyMsgsAdapter extends CursorAdapter {

    Context context;


    public MyMsgsAdapter(Context context, Cursor c) {
        super(context, c);
        this.context=context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View v= LayoutInflater.from(context).inflate(R.layout.msg_item, parent, false);

        return v;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView senderTV= (TextView) view.findViewById(R.id.senderTV);
        TextView msgTV= (TextView) view.findViewById(R.id.msgTV);

        String sender= cursor.getString(cursor.getColumnIndex(DBConstants.senderName));
        String text= cursor.getString(cursor.getColumnIndex(DBConstants.msgText));
        int isReadAsInt= cursor.getInt(cursor.getColumnIndex(DBConstants.isread));

        senderTV.setText(sender);
        msgTV.setText(text);

        ImageView isReadIV= (ImageView) view.findViewById(R.id.imageView);

        if(isReadAsInt==0)
           isReadIV.setImageResource(R.drawable.close);
        else
             isReadIV.setImageResource(R.drawable.open);
    }
}
