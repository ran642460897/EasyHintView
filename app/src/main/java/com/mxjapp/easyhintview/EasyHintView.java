package com.mxjapp.easyhintview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * user: Jason Ran
 * date: 2018/4/4.
 */

public class EasyHintView extends LinearLayout {
    private TextView leftTV,rightTV;
    private boolean autoHide;
    private static final int LABEL_GRAVITY_START=Gravity.START;
    private static final int LABEL_GRAVITY_END=Gravity.END;
    public EasyHintView(Context context) {
        this(context, null);
    }

    public EasyHintView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyHintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
    }

    public EasyHintView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }
    private void initView(AttributeSet attrs){
        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if(inflater!=null) inflater.inflate(R.layout.easy_hint_view, this, true);
        leftTV=findViewById(R.id.left);
        rightTV=findViewById(R.id.right);

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasyHintView);
        CharSequence label=typedArray.getString(R.styleable.EasyHintView_leftHint);
        CharSequence text=typedArray.getString(R.styleable.EasyHintView_text);
        CharSequence textHint=typedArray.getString(R.styleable.EasyHintView_textHint);
        int ems=typedArray.getInteger(R.styleable.EasyHintView_leftEms,0);
        int gravity=typedArray.getInteger(R.styleable.EasyHintView_leftGravity,0)==0?LABEL_GRAVITY_START:LABEL_GRAVITY_END;
        boolean singleLine=typedArray.getBoolean(R.styleable.EasyHintView_singleLine,false);
        autoHide=typedArray.getBoolean(R.styleable.EasyHintView_autoHide,true);
        typedArray.recycle();

        leftTV.setText(label);
        if(ems>0) leftTV.setEms(ems);
        leftTV.setGravity(gravity);
        rightTV.setHint(textHint);
        rightTV.setText(text);
        rightTV.setSingleLine(singleLine);
        if(autoHide) this.setVisibility(TextUtils.isEmpty(text)?GONE:VISIBLE);
    }

    public CharSequence getLeftHint() {
        return leftTV.getText();
    }

    public void setLeftHint(CharSequence label) {
        leftTV.setText(label);
    }

    public CharSequence getText() {return rightTV.getText();}

    public void setText(CharSequence text) {
        rightTV.setText(text);
        if(autoHide) this.setVisibility(TextUtils.isEmpty(text)?GONE:VISIBLE);
    }
}
