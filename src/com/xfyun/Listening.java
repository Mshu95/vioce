package com.xfyun;

import com.iflytek.cloud.speech.*;

/**
 * Created by Mshu on 2017/6/27.
 * 语音听写
 */
public class Listening {
    private static final String APPID = "59522b3c";
    public static void main(String[] args) {
        SpeechUtility.createUtility("appid=" + APPID);
//1.创建SpeechRecognizer对象
        SpeechRecognizer mIat= SpeechRecognizer.createRecognizer( );
//2.设置听写参数，详见《MSC Reference Manual》SpeechConstant类
        mIat.setParameter(SpeechConstant.DOMAIN, "iat"); //领域短信和日常用语：iat (默认)；视频：video；地图：poi；音乐：music
        mIat.setParameter(SpeechConstant.LANGUAGE, "zh_cn");//简体中文：zh_cn（默认）；美式英文：en_us
        mIat.setParameter(SpeechConstant.ACCENT, "mandarin ");//方言普通话：mandarin(默认);粤 语：cantonese四川话：lmz;河南话：henanese
        mIat.setParameter(SpeechConstant.ASR_AUDIO_PATH, "./tts_test.pcm");  //识别完成后在本地保存一个音频文件
        mIat.setParameter(SpeechConstant.AUDIO_SOURCE,"1");  //如果不写默认是“1”，“1”是从麦克风读取声音，“-1”是从.pcm音频文件读取声音
//3.开始听写-
        Recognizer recognizer =new Recognizer();
        mIat.startListening (recognizer);
    }
}
