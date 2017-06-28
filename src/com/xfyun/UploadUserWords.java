package com.xfyun;

import com.iflytek.cloud.speech.*;

/**
 * Created by Mshu on 2017/6/28.
 * 上传词汇表：
 *      上传用户词表可以提高词表内词汇的识别率，也可以提高语义的效果，
 * 每个用户终端设备对应一个词表，用户词表的格式及构造方法详见《MSC Reference Manual》UserWords 类。
 *      对付多音字比较奏效，比如 Fa Yan的读音，对应的汉子有 “发言” ， “发炎” ；
 * 如果将 ”发炎“ 的词汇加入词汇表，它会优先辨识为发炎，哪怕是 “发盐” 也行
 */
public class UploadUserWords {
    private static final String APPID = "59522b3c";
    private static final String USER_WORDS =
            "{\"userword\":[{\"name\":\"计算机词汇\",\"words\":[\"随机存储器\",\"只读存储器\",\"扩充数据输出\",\"局部总线\",\"压缩光盘\",\"十七寸显示器\"]}," +
                           "{\"name\":\"我的词汇\",\"words\":[\"槐花树老街\",\"王小恶\",\"发盐\",\"公事\"]}]}";

    public static void main(String[] args) {
        uploadUserWords();
    }
    private static void uploadUserWords() {
        SpeechUtility.createUtility("appid=" + APPID);
        SpeechRecognizer recognizer = SpeechRecognizer.getRecognizer();
        if ( recognizer == null) {
            recognizer = SpeechRecognizer.createRecognizer();

            if( null == recognizer ){
                System.out.println("获取识别实例实败！");
                return;
            }
        }

        UserWords userwords = new UserWords(USER_WORDS);
        recognizer.setParameter( SpeechConstant.DATA_TYPE, "userword" );
        recognizer.updateLexicon("userwords",
                userwords.toString(),
                lexiconListener);
    }

    /**
     * 词表上传监听器
     */
    static LexiconListener lexiconListener = new LexiconListener() {
        @Override
        public void onLexiconUpdated(String lexiconId, SpeechError error) {
            if (error == null){
                System.out.println("*************上传成功*************");
            }
            else{
                System.out.println("*************" + error.getErrorCode()
                        + "*************");
            }
        }

    };
}

