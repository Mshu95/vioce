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
        mIat.startListening (mRecoListener);
    }
    //听写监听器
    private static RecognizerListener mRecoListener = new RecognizerListener(){
        //开始录音
        public void onBeginOfSpeech() {
            System.out.println(".。开始录音。.");
        }
        //音量值0~30
        public void onVolumeChanged(int volume){
            /*System.out.println("当前音量"+volume);*/
        }
        //结束录音
        public void onEndOfSpeech() {
            System.out.println("录音结束");
        }
        //扩展用接口
        public void onEvent(int eventType,int arg1,int arg2,String msg) {}
        //听写结果回调接口(返回Json格式结果，用户可参见附录)；
        //一般情况下会通过onResults接口多次返回结果，完整的识别内容是多次结果的累加；
        //关于解析Json的代码可参见MscDemo中JsonParser类；
        //isLast等于true时会话结束。
        public void onResult(RecognizerResult results, boolean isLast){
            System.out.println("Result:"+results.getResultString ());
            //结果实例
            //Result:{"sn":1,"ls":false,"bg":0,"ed":0,"ws":[{"bg":0,"cw":[{"sc":0.00,"w":"你好"}]}]}
            //sn:第几句
            //ls:是否是第一句
            //bg：开始
            //ed:结束
            //ws:词语
            //cw:中文分词
            //w:单词
            //sc:分数
        }
        //会话发生错误回调接口
        public void onError(SpeechError error) {
           // error.getPlainDescription(true); //获取错误码描述
            System.out.println(error.getErrorDesc());
        }

    };
}
