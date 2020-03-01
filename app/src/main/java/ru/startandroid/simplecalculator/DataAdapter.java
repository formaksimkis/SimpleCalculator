package ru.startandroid.simplecalculator;

import android.content.Context;
import android.widget.ArrayAdapter;

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

    // возвращает содержимое выделенного элемента списка
    public String getItem(int position) {
        return mContacts[position];
    }

}
