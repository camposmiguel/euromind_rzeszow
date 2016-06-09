package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoExampleGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "de.greenrobot.daoexample");

        addUsers(schema);

        new DaoGenerator().generateAll(schema, "../02_NavigationDrawer/app/src/main/java");
    }

    private static void addUsers(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().primaryKey().autoincrement();
        user.addStringProperty("nickname");
        user.addIntProperty("points");
    }
}
