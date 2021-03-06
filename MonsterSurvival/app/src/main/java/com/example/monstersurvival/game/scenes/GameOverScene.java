package com.example.monstersurvival.game.scenes;

import com.example.monstersurvival.R;
import com.example.monstersurvival.framework.Scene;
import com.example.monstersurvival.framework.objects.Sprite;
import com.example.monstersurvival.framework.res.Metrics;
import com.example.monstersurvival.game.object.Button;
import com.example.monstersurvival.game.object.Score;

public class GameOverScene extends Scene {
    private static GameOverScene singleton;
    public static GameOverScene get() {
        if (singleton == null) {
            singleton = new GameOverScene();
            singleton.init();
        }
        return singleton;
    }

    public enum Layer {
        ui, touchUi, COUNT;
    }

    @Override
    public void init() {
        super.init();
        initLayers(Layer.COUNT.ordinal());

        add(Layer.ui.ordinal(), new Sprite(
                Metrics.width / 2, Metrics.height / 2,
                Metrics.width / 1.5f, Metrics.height / 2,
                R.mipmap.trans_50p));

        add(Layer.ui.ordinal(), new Sprite(
                Metrics.width / 2, Metrics.height / 2.5f,
                Metrics.width / 2, Metrics.width / 2,
                R.mipmap.game_over));

        float btn_width = Metrics.width / 4;
        float btn_height = btn_width * 256 / 512;
        float btn_x = Metrics.width / 2;
        float btn_y = Metrics.height / 2 + btn_height / 2;

        btn_y += btn_height;
        add(Layer.touchUi.ordinal(), new Button(
                btn_x, btn_y, btn_width, btn_height,
                R.mipmap.btn_exit_n, R.mipmap.btn_exit_p, new Button.Callback()
        {
            @Override
            public boolean onTouch(Button.Action action) {
                finish();
                return true;
            }
        }));

        Score score = new Score(btn_x * 0.7f, btn_y * 0.9f);
        add(Layer.touchUi.ordinal(), score);
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touchUi.ordinal();
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean handleBackKey() {
        Scene.popScene();
        return true;
    }
}
