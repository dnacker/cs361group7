package com.pronunciation_match.pronunciationmatch;

public class Phoneme {
    private String mText;
    private Tone mTone;
    private int mResourceId;

    public Phoneme(String text, Tone tone, int resId) {
        mTone = tone;
        setText(text);
        mResourceId = resId;
    }

    private void setText(String text) {
        String[] prefixAndSuffix = StringUtils.parsePrefixAndSuffix(text);
        mText = prefixAndSuffix[0] + StringUtils.getSuffixWithTone(prefixAndSuffix[1], mTone);
    }

    public String getText() {
        return mText;
    }

    public Tone getTone() {
        return mTone;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public String toString() {
        return getText();
    }
}
