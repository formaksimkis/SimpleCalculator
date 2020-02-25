package ru.startandroid.simplecalculator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataAdapter extends ArrayAdapter<String> {

    private static final String[] mContacts = { "0", "1", "2",
            "3", "4", "5", "6", "7", "8",
            "9", "+", "-", "*", "/", "^", "(", ".", ")" };



    Context mContext;

    // Конструктор
    public DataAdapter(Context context, int layout, int textViewResourceId) {
        super(context, layout, textViewResourceId, mContacts);
        // TODO Auto-generated constructor stub
        this.mContext = context;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        // TODO Auto-generated method stub
//
//        TextView label = (TextView) convertView;
//
//        if (convertView == null) {
//            convertView = new TextView(mContext);
//            label = (TextView) convertView;
//        }
//        label.setText(mContacts[position]);
//
//        return (convertView);
//    }

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mContacts[position];
    }

}
