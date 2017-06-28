package com.xfyun;
import com.iflytek.cloud.speech.*;
/**
 * Created by Mshu on 2017/6/28.
 *语法识别
 主要指基于命令词的识别，识别指定关键词组合的词汇，或者固定说法的短句。
 语法识别采用 ABNF 语法格式，参考《MSC Reference Manual》中对 SpeechConstant 类的 CLOUD_GRAMMAR 成员的说明。
 */
public class Grammar {
    private static final String APPID = "59522b3c";
    private static String GrammarId = null;
    private static String  mCloudGrammar = "#ABNF 1.0 UTF-8; languagezh-CN;  mode voice; root $main; $main = $place1 到$place2 ; $place1 = 北京 | 武汉 | 南京 | 天津 | 天京 | 东京; $place2 = 上海 | 合肥; ";
    public static void mai(String[] args) {
        SpeechUtility.createUtility("appid=" + APPID);
        //1.创建SpeechRecognizer对象
        SpeechRecognizer mAsr = SpeechRecognizer.createRecognizer(); // ABNF语法示例，可以说”北京到上海”
        //2.构建语法文件
        mAsr.setParameter(SpeechConstant.TEXT_ENCODING, "utf-8");
        int ret = mAsr.buildGrammar("abnf", mCloudGrammar, grammarListener);
        if (ret != ErrorCode.SUCCESS) {
            System.out.println("语法构建失败,错误码：" + ret);
        } else {
            System.out.println("语法构建成功");
        }
        //3.开始识别,设置引擎类型为云端
        mAsr.setParameter(SpeechConstant.ENGINE_TYPE, "cloud");
        // 设置GrammarId
        System.out.println("|"+GrammarId);
        mAsr.setParameter(SpeechConstant.CLOUD_GRAMMAR, GrammarId);
        mAsr.startListening(recognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            System.out.println("识别失败,错误码: " + ret);
        }
    }
    /**
     * 构建语法监听器
     */
    private static GrammarListener grammarListener = new GrammarListener() {
        @Override
        public void onBuildFinish(String grammarId, SpeechError error) {
            if (error == null) {
                GrammarId = grammarId;
                System.out.println("语法构建成功,GrammarId：" + GrammarId);
            }else {
                System.out.println("语法构建失败,错误码：" + error.getErrorCode());
            }
        }
    };
    //听写监听器
    private static RecognizerListener recognizerListener = new RecognizerListener() {
        /**
         * 获取识别结果. 获取RecognizerResult类型的识别结果，
         */
        @Override
        public void onResult(RecognizerResult results, boolean islast) {
            // 结果返回为默认json格式,用JsonParser工具类解析
            System.out.println("results："+results.getResultString());
        }

        @Override
        public void onVolumeChanged(int volume) {
        }

        @Override
        public void onError(SpeechError error) {
            if (null != error) {
                System.out.println("onError Code：" + error.getErrorCode());
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int agr2, String msg) {

        }

        @Override
        public void onBeginOfSpeech() {
            System.out.println("开始录音");
        }

        @Override
        public void onEndOfSpeech() {
            System.out.println("录音结束");
        }
    };
}

