package com.pronunciation_match.pronunciationmatch;

public class Phoneme {
    private String mText;
    private String mDisplayText;
    private Tone mTone;
    private String mPath;

    public Phoneme(String text, Tone tone, String path) {
        mTone = tone;
        setText(text);
        mPath = path;
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

    public String getPath() { return mPath; }

    public String toString() {
        return getDisplayText();
    }
}
