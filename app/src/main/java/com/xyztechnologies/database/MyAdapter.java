package com.xyztechnologies.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    List<Contact> contactsList;
    LayoutInflater inflater;

    Context context;


    public MyAdapter(List<Contact> contacts, Context context) {
        this.contactsList = contacts;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return contactsList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contactsList.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView==null){
            convertView = inflater.inflate(R.layout.activity_contact,null);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView number = convertView.findViewById(R.id.number);
        Contact contacts = contactsList.get(position);

        name.setText(contacts.getName());
        number.setText(contacts.getPhoneNumber());

        return convertView;
    }
}
