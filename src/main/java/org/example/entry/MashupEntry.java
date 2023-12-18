package org.example.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MashupEntry {
    public String ID;
    public String name;
    public List<APIEntry> relatedApis = new ArrayList<>();
    public List<String> categories = new ArrayList<>();

    public static List<MashupEntry> mashupList = new ArrayList<>();

    public MashupEntry(String ID, String name, String relatedApis, String categories) {
        this.ID = ID;
        this.name = name;
        String[] tmp = categories.split(",");
        this.categories.addAll(Arrays.asList(tmp));

        tmp = relatedApis.split(",");
        this.relatedApis = APIEntry.searchByNames(tmp);

        mashupList.add(this);
    }

    @Override
    public String toString() {
        return "MashupEntry{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                ", relatedApis=" + relatedApis +
                '}';
    }
}
