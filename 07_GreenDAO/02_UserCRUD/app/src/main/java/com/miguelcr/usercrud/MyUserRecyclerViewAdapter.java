package com.miguelcr.usercrud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miguelcr.usercrud.greendao.User;

import java.util.List;


public class MyUserRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnUserClickListener mListener;

    public MyUserRecyclerViewAdapter(List<User> items, OnUserClickListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.tvName.setText(mValues.get(position).getName());
        holder.tvAge.setText(String.valueOf((mValues.get(position).getAge())));
        String sex = mValues.get(position).getSex();
        if(sex=="m") {
            holder.ivAvatar.setImageResource(R.drawable.boy);
        } else {
            holder.ivAvatar.setImageResource(R.drawable.girl);
        }


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvName;
        public final TextView tvAge;
        public final ImageView ivAvatar;
        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvName = (TextView) view.findViewById(R.id.textViewName);
            tvAge = (TextView) view.findViewById(R.id.textViewAge);
            ivAvatar = (ImageView) view.findViewById(R.id.imageViewAvatar);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvAge.getText() + "'";
        }
    }
}
