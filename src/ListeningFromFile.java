import com.iflytek.cloud.speech.*;

import java.util.ArrayList;

/**
 * Created by Asus on 2017/6/27.
 */
public class ListeningFromFile {
    private static final String APPID = "591010d4";
    public static void main(String[] args) {
        SpeechUtility.createUtility("appid=" + APPID);
//1.创建SpeechRecognizer对象
        SpeechRecognizer mIat= SpeechRecognizer.createRecognizer( );
//2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat");
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");
        mIat.setParameter (SpeechConstant.ACCENT, "mandarin ");
        mIat.setParameter(SpeechConstant.AUDIO_SOURCE, "-1");
//3.开始听写
        mIat.startListening(mRecoListener);
//voiceBuffer为音频数据流，splitBuffer为自定义分割接口，将其以4.8k字节分割成数组
        /*ArrayList<byte[]> buffers = splitBuffer(voiceBuffer,voiceBuffer.length, 4800);
        for (int i = 0; i < buffers.size(); i++) {
            // 每次写入msc数据4.8K,相当150ms录音数据
            mIat.writeAudio(buffers.get(i), 0, buffers.get(i).length);
        }*/
        mIat.stopListening();
    }
    //听写监听器
    private static RecognizerListener mRecoListener = new RecognizerListener(){
        public void onResult(RecognizerResult results, boolean isLast) {
            System.out.println("Result:"+results.getResultString ());
        }
        //会话发生错误回调接口
        public void onError(SpeechError error) {
            //error.getPlainDescription(true); //获取错误码描述
            System.out.println(error.getErrorDesc());
        }

        @Override
        public void onEvent(int i, int i1, int i2, String s) {

        }

        @Override
        public void onVolumeChanged(int i) {

        }
        //开始录音
        public void onBeginOfSpeech() {}
        @Override
        public void onEndOfSpeech() {

        }
        //音量值0~30
        public void onVolumeChange( int volume ) {}
    };
}
