package com.example.contactmanager;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.contactmanager.model.AddressBook;
import com.example.contactmanager.model.BaseContact;
import com.example.contactmanager.model.PersonContact;

public class personAdapter extends BaseAdapter {


    Activity mActivity;
    AddressBook addressBook;


    public personAdapter(Activity mActivity, AddressBook addressBook) {
        this.mActivity = mActivity;
        this.addressBook = addressBook;
    }


    @Override
    public int getCount() {
        return addressBook.getTheList().size();
    }

    @Override
    public BaseContact getItem(int position) {
        return addressBook.getTheList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View onePersonLine;

        LayoutInflater inflater = (LayoutInflater)mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePersonLine = inflater.inflate(R.layout.person_one_line, parent, false );

        TextView tv_name = onePersonLine.findViewById(R.id.tv_name);
        TextView tv_phoneNumber = onePersonLine.findViewById(R.id.tv_phoneNumber);
        ImageView iv_icon = onePersonLine.findViewById(R.id.iv_icon);

        BaseContact p = this.getItem(position);

        tv_name.setText(p.getName());
        tv_phoneNumber.setText(p.getPhoneNumber());



        return onePersonLine;
    }
}
