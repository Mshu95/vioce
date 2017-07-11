package com.xfyun; /**
 * Created by Mshu on 2017/6/27.
 * 语音合成
 */
import com.iflytek.cloud.speech.*;
public class Spesking {
    private static final String APPID = "59522b3c";
    public static void specking() {
    SpeechUtility.createUtility("appid=" + APPID);
//1.创建SpeechSynthesizer对象
        SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer( );
//2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
        mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");    //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "50");              //设置语速  范围0~100
        mTts.setParameter(SpeechConstant.VOLUME, "80");             //设置音量，范围0~100
//设置合成音频保存位置（可自定义保存位置），保存在“./tts_test.pcm”
//如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");
//3.开始合成
        Synthesizer synthesizer = new Synthesizer();
        mTts.startSpeaking("语音合成测试程序", synthesizer);
    }
}
