package com.miguelcr.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import de.greenrobot.daoexample.DaoSession;
import de.greenrobot.daoexample.User;
import de.greenrobot.daoexample.UserDao;


/**
 * A simple {@link Fragment} subclass.
 */
public class RankingFragment extends Fragment {
    TextView tvRanking;

    public RankingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ranking, container, false);

        tvRanking = (TextView) v.findViewById(R.id.textViewRanking);

        //Database connection
        DaoSession daoSession = DatabaseConnection.getConnection(getContext());
        UserDao userDao = daoSession.getUserDao();
        //List<User> lista = userDao.queryBuilder()
        //        .orderAsc(UserDao.Properties.Points).list();
        // SELECT * FROM User ORDER BY Points ASC

        List<User> lista = userDao.loadAll();

        for(int i=0; i<lista.size(); i++) {
            User currentUser = lista.get(i);
            tvRanking.setText(tvRanking.getText().toString()+"\n"
                    +currentUser.getNickname()+"("+currentUser.getPoints()+")");
        }

        return v;
    }

}
