package com.example.monstersurvival.framework;

import android.graphics.RectF;

public class CollisionHelper {
    public static boolean collides(com.example.monstersurvival.framework.BoxCollidable o1, com.example.monstersurvival.framework.BoxCollidable o2) {
        RectF r1 = o1.getBoundingRect();
        RectF r2 = o2.getBoundingRect();

        if (r1.left > r2.right) return false;
        if (r1.top > r2.bottom) return false;
        if (r1.right < r2.left) return false;
        if (r1.bottom < r2.top) return false;

        return true;
    }
}
