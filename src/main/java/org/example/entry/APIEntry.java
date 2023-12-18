package org.example.entry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIEntry {
    public int ID;
    public String name;
    public List<String> categories = new ArrayList<>();
    public static List<APIEntry> apiList = new ArrayList<>();
    public static int size = 0;
    public APIEntry(String ID, String name, String primaryCategory, String secondaryCategories) {
        this.ID = Integer.parseInt(ID);
        this.name = name;
        this.categories.add(primaryCategory);
        if(secondaryCategories != null && !secondaryCategories.equals("")) {
            String[] s_list = secondaryCategories.split(",");
            this.categories.addAll(Arrays.asList(s_list));
        }
        apiList.add(this);
    }

    public static APIEntry searchByName(String name){
        for(APIEntry apiEntry : apiList){
            if(apiEntry.name.equalsIgnoreCase(name))
                return apiEntry;
        }
        System.out.println(name + " not found in apiList");
        return null;
    }

    public static List<APIEntry> searchByNames(String[] names){
        List<APIEntry> res = new ArrayList<>();
        for(String name : names){
            if("".equals(name))
                continue;
            APIEntry tmp = searchByName(name.strip());
            if(tmp != null)
                res.add(tmp);
        }
        return res;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    @Override
    public String toString() {
        return "APIEntry{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}
