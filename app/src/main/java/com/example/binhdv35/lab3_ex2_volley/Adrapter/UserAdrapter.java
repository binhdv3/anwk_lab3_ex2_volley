package com.example.binhdv35.lab3_ex2_volley.Adrapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.binhdv35.lab3_ex2_volley.R;
import com.example.binhdv35.lab3_ex2_volley.model.User;

import java.util.List;

public class UserAdrapter extends BaseAdapter {

    private Context context;
    private List<User> userList;

    public UserAdrapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private static class ViewHolderUser{
        private TextView tvName, tvEmail, tvHome, tvPhone;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderUser viewHolderUser = null;
        if (convertView == null){
            viewHolderUser = new ViewHolderUser();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_lv_,null);

            viewHolderUser.tvName = convertView.findViewById(R.id.item_tv_name);
            viewHolderUser.tvEmail = convertView.findViewById(R.id.item_tv_email);
            viewHolderUser.tvHome = convertView.findViewById(R.id.item_tv_home);
            viewHolderUser.tvPhone = convertView.findViewById(R.id.item_tv_mobile);

            convertView.setTag(viewHolderUser);
        }else {
            viewHolderUser = (ViewHolderUser) convertView.getTag();
        }

        User user = userList.get(position);

        viewHolderUser.tvName.setText(user.getName());
        viewHolderUser.tvEmail.setText(user.getEmail());
        viewHolderUser.tvHome.setText(user.getHome());
        viewHolderUser.tvPhone.setText(user.getPhone());

        return convertView;
    }
}
