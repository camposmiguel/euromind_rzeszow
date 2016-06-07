package de.greenrobot.daoexample;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import de.greenrobot.daoexample.Team;

import de.greenrobot.daoexample.TeamDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig teamDaoConfig;

    private final TeamDao teamDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        teamDaoConfig = daoConfigMap.get(TeamDao.class).clone();
        teamDaoConfig.initIdentityScope(type);

        teamDao = new TeamDao(teamDaoConfig, this);

        registerDao(Team.class, teamDao);
    }
    
    public void clear() {
        teamDaoConfig.getIdentityScope().clear();
    }

    public TeamDao getTeamDao() {
        return teamDao;
    }

}
