package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class DaoExampleGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1000, "de.greenrobot.daoexample");

        addTeam(schema);

        new DaoGenerator().generateAll(schema, "../01_SimpleListViewDatabase/app/src/main/java");
    }

    private static void addTeam(Schema schema) {
        Entity team = schema.addEntity("Team");
        team.addIdProperty().primaryKey().autoincrement();
        team.addStringProperty("name");
        //team.addIntProperty("numberOfPlayers");
    }

    private static void addNote(Schema schema) {
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }
}
