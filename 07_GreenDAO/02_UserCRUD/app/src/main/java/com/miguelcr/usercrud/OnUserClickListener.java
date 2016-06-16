package com.miguelcr.usercrud;

import com.miguelcr.usercrud.greendao.User;

/**
 * Created by miguelcampos on 15/6/16.
 */
public interface OnUserClickListener {

    public void onUserClick(User u);
    public void onUserDelete(User u);
    public void onUserEdit(User u);
}
