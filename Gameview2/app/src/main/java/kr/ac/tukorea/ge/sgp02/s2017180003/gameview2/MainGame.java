package kr.ac.tukorea.ge.sgp02.s2017180003.gameview2;

public class MainGame {
    public static MainGame getInstance(){
        if(singleton == null){
            singleton = new MainGame();
        }
        return singleton;
    }

    // 싱글톤으로 
    private MainGame(){

    }

    private static MainGame singleton;

}
