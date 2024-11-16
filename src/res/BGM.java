//package res;
//
//import sun.audio.AudioPlayer;
//import sun.audio.AudioStream;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//
///**
// * 待完善
// */
//public enum BGM{
////    ENTERED(""), //鼠标移入音效
////    EXITED(""),  //鼠标移出
//    CLICKED(""), //点击音效
//    GAME(""),   //游戏BGM
//    VICTORY(""),  //胜利
//    FAILED(""),   //失败
//    BUILD(""),   //建造第1种塔音效
////    BUILD2(""),   //建造第2种塔音效
////    BUILD3(""),   //建造第3种塔音效
//    LEVELUP(""),   //升级塔
//    SALE(""),      //售卖塔
//    ;
//
//    private AudioStream music;
//
//    BGM(String path) {
//        try {
//            this.music = new AudioStream(new FileInputStream(path));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void play(){
//        AudioPlayer.player.start(music);
//    }
//
//    public void stop(){
//        AudioPlayer.player.stop(music);
//    }
//
//}
