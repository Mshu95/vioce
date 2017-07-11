package com.xfyun;

import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SynthesizerListener;

/**
 * Created by Asus on 2017/6/29.
 * 语音合成回掉
 */
public class Synthesizer implements SynthesizerListener {
    //会话结束回调接口，没有错误时，error为null
    public void onCompleted(SpeechError error) {
    }

    @Override
    public void onEvent(int i, int i1, int i2, int i3, Object o, Object o1) {
    }

    //缓冲进度回调
    //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
    public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
    }

    //开始播放
    public void onSpeakBegin() {
    }

    //暂停播放
    public void onSpeakPaused() {
    }

    //播放进度回调
    //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
    public void onSpeakProgress(int percent, int beginPos, int endPos) {
    }

    //恢复播放回调接口
    public void onSpeakResumed() {
    }
}
