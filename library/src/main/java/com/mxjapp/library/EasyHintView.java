package com.mxjapp.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
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
    public EasyHintView(Context context) {
        this(context, null);
    }

    public EasyHintView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EasyHintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public EasyHintView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr,defStyleRes);
        initView(attrs);
    }
    private void initView(AttributeSet attrs){
        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        if(inflater!=null) inflater.inflate(R.layout.easy_hint_view, this, true);
        leftTV=findViewById(R.id.left);
        rightTV=findViewById(R.id.right);
        //left text view attr
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasyHintView);
        CharSequence leftHint=typedArray.getString(R.styleable.EasyHintView_leftHint);
        int leftEms=typedArray.getInteger(R.styleable.EasyHintView_leftEms,0);
        int leftGravity=typedArray.getInteger(R.styleable.EasyHintView_leftGravity,0)==0?Gravity.START:Gravity.END;
        int leftSize=typedArray.getDimensionPixelSize(R.styleable.EasyHintView_leftSize,0);
        int leftDrawableId=typedArray.getResourceId(R.styleable.EasyHintView_leftDrawable,0);
        int leftDrawablePadding=typedArray.getDimensionPixelOffset(R.styleable.EasyHintView_leftDrawablePadding,0);
        ColorStateList leftColor=typedArray.getColorStateList(R.styleable.EasyHintView_leftColor);
        //right text view attr
        CharSequence text=typedArray.getString(R.styleable.EasyHintView_text);
        CharSequence textHint=typedArray.getString(R.styleable.EasyHintView_textHint);
        ColorStateList rightColor=typedArray.getColorStateList(R.styleable.EasyHintView_textColor);
        int rightSize=typedArray.getDimensionPixelSize(R.styleable.EasyHintView_textSize,0);
        int rightGravity=typedArray.getInteger(R.styleable.EasyHintView_gravity,0)==0?Gravity.START:Gravity.END;
        int rightDrawableId=typedArray.getResourceId(R.styleable.EasyHintView_rightDrawable,0);
        int rightDrawablePadding=typedArray.getDimensionPixelOffset(R.styleable.EasyHintView_rightDrawablePadding,0);
        boolean singleLine=typedArray.getBoolean(R.styleable.EasyHintView_singleLine,false);
        autoHide=typedArray.getBoolean(R.styleable.EasyHintView_autoHide,true);
        typedArray.recycle();
        //set attr
        if(leftEms!=0) leftTV.setEms(leftEms);
        if(leftColor!=null) leftTV.setTextColor(leftColor);
        if(leftSize!=0) leftTV.setTextSize(TypedValue.COMPLEX_UNIT_PX,leftSize);
        leftTV.setGravity(leftGravity);
        if(leftDrawableId!=0) {
            Drawable leftDrawable= ContextCompat.getDrawable(getContext(),leftDrawableId);
            if(leftDrawable!=null) leftDrawable.setBounds(0,0,leftDrawable.getMinimumWidth(),leftDrawable.getMinimumHeight());
            leftTV.setCompoundDrawables(leftDrawable,null,null,null);
            if(leftDrawablePadding>0) leftTV.setCompoundDrawablePadding(leftDrawablePadding);
        }
        leftTV.setText(leftHint);

        if(rightColor!=null) rightTV.setTextColor(rightColor);
        if(rightSize!=0) rightTV.setTextSize(TypedValue.COMPLEX_UNIT_PX,rightSize);
        if(rightDrawableId!=0) {
            Drawable rightDrawable= ContextCompat.getDrawable(getContext(),rightDrawableId);
            if(rightDrawable!=null) rightDrawable.setBounds(0,0,rightDrawable.getMinimumWidth(),rightDrawable.getMinimumHeight());
            rightTV.setCompoundDrawables(null,null,rightDrawable,null);
            if(rightDrawablePadding>0) rightTV.setCompoundDrawablePadding(rightDrawablePadding);
        }
        rightTV.setGravity(rightGravity);
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
