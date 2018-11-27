package com.pronunciation_match.pronunciationmatch;

public class Phoneme {
    private String mText;
    private String mDisplayText;
    private Tone mTone;
    private int mResourceId;

    public Phoneme(String text, Tone tone, int resId) {
        mTone = tone;
        setText(text);
        mResourceId = resId;
    }

    private void setText(String text) {
        mText = text;
        String[] prefixAndSuffix = StringUtils.parsePrefixAndSuffix(text);
        mDisplayText = prefixAndSuffix[0] + StringUtils.getSuffixWithTone(prefixAndSuffix[1], mTone);
    }

    public String getText() {
        return mText;
    }

    public String getDisplayText() {
        return mDisplayText;
    }

    public Tone getTone() {
        return mTone;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public String toString() {
        return getDisplayText();
    }
}
