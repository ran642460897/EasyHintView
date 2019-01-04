package com.mxjapp.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * user: Jason Ran
 * date: 2018/4/4.
 */

public class EasyHintView extends LinearLayout {
    private TextView hintTV,textTV;
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
//        if(inflater!=null) inflater.inflate(R.layout.easy_hint_view, this, true);
        hintTV=new TextView(getContext());
        textTV=new TextView(getContext());
        //hint text view attr
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EasyHintView);
        CharSequence hintHint=typedArray.getString(R.styleable.EasyHintView_hint);
        int hintEms=typedArray.getInteger(R.styleable.EasyHintView_hintEms,0);
        int hintGravity=typedArray.getInteger(R.styleable.EasyHintView_hintGravity,0)==0?Gravity.START:Gravity.END;
        int hintSize=typedArray.getDimensionPixelSize(R.styleable.EasyHintView_hintSize,0);
        int hintDrawableId=typedArray.getResourceId(R.styleable.EasyHintView_hintDrawable,0);
        int hintDrawablePadding=typedArray.getDimensionPixelOffset(R.styleable.EasyHintView_hintDrawablePadding,0);
        ColorStateList hintColor=typedArray.getColorStateList(R.styleable.EasyHintView_hintColor);
        //text text view attr
        CharSequence text=typedArray.getString(R.styleable.EasyHintView_text);
        CharSequence textHint=typedArray.getString(R.styleable.EasyHintView_textHint);
        ColorStateList textColor=typedArray.getColorStateList(R.styleable.EasyHintView_textColor);
        ColorStateList textHintColor=typedArray.getColorStateList(R.styleable.EasyHintView_textHintColor);
        int textSize=typedArray.getDimensionPixelSize(R.styleable.EasyHintView_textSize,0);
        int textGravity=typedArray.getInteger(R.styleable.EasyHintView_gravity,0)==0?Gravity.START:Gravity.END;
        int textDrawableId=typedArray.getResourceId(R.styleable.EasyHintView_textDrawable,0);
        int textDrawablePadding=typedArray.getDimensionPixelOffset(R.styleable.EasyHintView_textDrawablePadding,0);
        int textViewWidth=typedArray.getLayoutDimension(R.styleable.EasyHintView_textViewWidth,LayoutParams.WRAP_CONTENT);
        int textMaxLines=typedArray.getInteger(R.styleable.EasyHintView_textMaxLines,0);
        float textLineSpacingExtra=typedArray.getDimension(R.styleable.EasyHintView_textLineSpacingExtra,0);
        boolean singleLine=typedArray.getBoolean(R.styleable.EasyHintView_singleLine,false);
        autoHide=typedArray.getBoolean(R.styleable.EasyHintView_autoHide,true);
        //container attr
        int space=typedArray.getDimensionPixelSize(R.styleable.EasyHintView_space,0);

        typedArray.recycle();
        //set attr
        if(hintEms!=0) hintTV.setEms(hintEms);
        if(hintColor!=null) hintTV.setTextColor(hintColor);
        else hintTV.setTextColor(Color.parseColor("#999999"));
        if(hintSize!=0) hintTV.setTextSize(TypedValue.COMPLEX_UNIT_PX,hintSize);
        hintTV.setGravity(hintGravity);
        if(hintDrawableId!=0) {
            Drawable hintDrawable= ContextCompat.getDrawable(getContext(),hintDrawableId);
            if(hintDrawable!=null) hintDrawable.setBounds(0,0,hintDrawable.getMinimumWidth(),hintDrawable.getMinimumHeight());
            hintTV.setCompoundDrawables(hintDrawable,null,null,null);
            if(hintDrawablePadding>0) hintTV.setCompoundDrawablePadding(hintDrawablePadding);
        }
        hintTV.setText(hintHint);

        if(textColor!=null) textTV.setTextColor(textColor);
        if(textHintColor!=null) textTV.setHintTextColor(textHintColor);
        if(textSize!=0) textTV.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        if(textLineSpacingExtra>0) textTV.setLineSpacing(textLineSpacingExtra,textTV.getLineSpacingMultiplier());
        if(textDrawableId!=0) {
            Drawable textDrawable= ContextCompat.getDrawable(getContext(),textDrawableId);
            if(textDrawable!=null) textDrawable.setBounds(0,0,textDrawable.getMinimumWidth(),textDrawable.getMinimumHeight());
            textTV.setCompoundDrawables(null,null,textDrawable,null);
            if(textDrawablePadding>0) textTV.setCompoundDrawablePadding(textDrawablePadding);
        }
        textTV.setGravity(textGravity);
        textTV.setHint(textHint);
        textTV.setText(text);
        textTV.setSingleLine(singleLine);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(textViewWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        textTV.setLayoutParams(params);
        if(autoHide) this.setVisibility(TextUtils.isEmpty(text)?GONE:VISIBLE);

        if(getOrientation()==HORIZONTAL) textTV.setPadding(space,0,0,0);
        else textTV.setPadding(0,space,0,0);

        if(textMaxLines>0) {
            textTV.setMaxLines(textMaxLines);
            textTV.setEllipsize(TextUtils.TruncateAt.END);
        }

        addView(hintTV);
        addView(textTV);
    }

    public CharSequence getHint() {
        return hintTV.getText();
    }

    public void setHint(CharSequence label) {
        hintTV.setText(label);
    }

    public CharSequence getText() {return textTV.getText();}

    public void setText(CharSequence text) {
        textTV.setText(text);
        if(autoHide) this.setVisibility(TextUtils.isEmpty(text)?GONE:VISIBLE);
    }
}
