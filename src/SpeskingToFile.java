import com.iflytek.cloud.speech.*;

/**
 * Created by Asus on 2017/6/27.
 */
public class SpeskingToFile {
    private static final String APPID = "591010d4";

    public static void main(String[] args) {
        SpeechUtility.createUtility("appid=" + APPID);
        //1.创建SpeechSynthesizer对象
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );
//2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速，范围0~100
        mTts.setParameter(SpeechConstant.PITCH, "50");//设置语调，范围0~100
        mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围0~100
//3.开始合成
//设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
        mTts.synthesizeToUri("语音合成测试程序", "./tts1_test.pcm",synthesizeToUriListener);
    }
    //合成监听器
   public static SynthesizeToUriListener synthesizeToUriListener = new SynthesizeToUriListener() {
        //progress为合成进度0~100
        public void onBufferProgress(int progress) {}
        //会话合成完成回调接口
        //uri为合成保存地址，error为错误信息，为null时表示合成会话成功
        public void onSynthesizeCompleted(String uri, SpeechError error) {}
        @Override
        public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {
        }
    };
}
