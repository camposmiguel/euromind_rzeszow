package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.PropertyType;
import de.greenrobot.daogenerator.Schema;

public class MyGreenDaoExampleGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "com.miguelcr.usercrud.greendao");

        addUserOverclock(schema);
        //addCustomerOrder(schema);

        new DaoGenerator().generateAll(schema, "../02_UserCRUD/app/src/main/java");
    }

    private static void addUserOverclock(Schema schema) {
        Entity user = schema.addEntity("User");
        user.addIdProperty().autoincrement().primaryKey();
        user.addStringProperty("name").notNull();
        user.addIntProperty("age").notNull();
        user.addStringProperty("sex");
        user.addStringProperty("location");
    }

}
