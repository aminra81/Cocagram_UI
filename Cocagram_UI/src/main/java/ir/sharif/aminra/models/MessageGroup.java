package ir.sharif.aminra.models;

import ir.sharif.aminra.db.Context;

import java.util.ArrayList;
import java.util.List;

public class MessageGroup {
    ID id;
    private List<ID> users;
    private String groupName;
    public MessageGroup(String groupName) {
        id = new ID(true);
        this.groupName = groupName;
        users = new ArrayList<>();

        Context.getInstance().getMessageGroupDB().saveIntoDB(this);
    }
    public ID getID() { return id; }
}
