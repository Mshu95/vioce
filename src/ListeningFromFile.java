import com.iflytek.cloud.speech.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Asus on 2017/6/27.
 */
public class ListeningFromFile {
    private static final String APPID = "59522b3c";
    private static StringBuffer mResult = new StringBuffer();
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
                FileInputStream fis = null;
                final byte[] buffer = new byte[64*1024];
                try {
                    fis = new FileInputStream(new File("./tts_test.pcm"));
                    if (0 == fis.available()) {
                        mResult.append("no audio avaible!");
                        mIat.cancel();
                    } else {
                        int lenRead = buffer.length;
                        while( buffer.length==lenRead ){
                            lenRead = fis.read( buffer );
                            mIat.writeAudio( buffer, 0, lenRead );
                        }//end of while
                        mIat.stopListening();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (null != fis) {
                            fis.close();
                            fis = null;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
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
