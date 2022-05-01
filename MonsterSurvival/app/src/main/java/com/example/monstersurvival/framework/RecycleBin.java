package com.example.monstersurvival.framework;

import java.util.ArrayList;
import java.util.HashMap;

public class RecycleBin {
    private static HashMap<Class, ArrayList<com.example.monstersurvival.framework.Recyclable>> recycleBin = new HashMap<>();
    public static void init() {
        recycleBin.clear();
    }
    public static com.example.monstersurvival.framework.Recyclable get(Class clazz) {
        ArrayList<com.example.monstersurvival.framework.Recyclable> bin = recycleBin.get(clazz);
        if (bin == null) return null; // 한번도 재활용된적이 없다
        if (bin.size() == 0) return null; // 재활용된적이 있는 클래스이지만 지금 쓸수있는게 없다
        return bin.remove(0);
    }

    public static void add(com.example.monstersurvival.framework.Recyclable object) {
        Class clazz = object.getClass();
        ArrayList<com.example.monstersurvival.framework.Recyclable> bin = recycleBin.get(clazz);
        if (bin == null) {
            bin = new ArrayList<>();
            recycleBin.put(clazz, bin);
        }
        if (bin.indexOf(object) >= 0) {
            // already exists in the recycle bin
            return;
        }
        bin.add(object);
    }
}
