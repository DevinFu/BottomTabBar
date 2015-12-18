package kale.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kale.kale.bottomtab.BottomTabImpl;
import kale.bottombar.R;

/**
 * @author Jack Tony
 * @date 2015/6/7
 */
public class BottomTab extends LinearLayout implements BottomTabImpl {

    public BottomTab(Context context) {
        this(context, null);
    }

    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs == null) {
            // Preserve behavior prior to removal of this API.
            throw new NullPointerException();
        }
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomTab);
        tabTextColors = a.getColorStateList(R.styleable.BottomTab_android_textColor);
        tabText = a.getText(R.styleable.BottomTab_android_text);
        tabTextSize = a.getDimensionPixelSize(R.styleable.BottomTab_android_textSize, 16);
        tabDrawable = a.getDrawable(R.styleable.BottomTab_android_drawableTop);
        a.recycle();
        initViews();
    }

    CharSequence tabText;
    int tabTextSize;

    ColorStateList tabTextColors;

    Drawable tabDrawable;

    private TextView mTvTabHint;
    private RadioImageView mRivTabIcon;
    private RadioTextView mTvTabTitle;

    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

    private OnCheckedChangeListener mOnCheckedChangeListener;

    private void initViews() {
        this.setOrientation(VERTICAL);
        inflate(getContext(), getLayoutRes(), this);
        mTvTabHint = (TextView) findViewById(R.id.tab_hint);
        mRivTabIcon = (RadioImageView) findViewById(R.id.tab_icon);
        mTvTabTitle = (RadioTextView) findViewById(R.id.tab_title);

        mRivTabIcon.setImageDrawable(tabDrawable);
        mTvTabTitle.setText(tabText);
        mTvTabTitle.setTextSize(tabTextSize);
        if (tabTextColors != null) {
            mTvTabTitle.setTextColor(tabTextColors);
        }

        mRivTabIcon.setOnCheckedChangeListener(new RadioImageView.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioImageView pView, boolean isChecked) {
                mChecked = isChecked;
                mTvTabHint.refreshDrawableState();
                mTvTabTitle.setChecked(isChecked);
                mOnCheckedChangeWidgetListener.onCheckedChanged(BottomTab.this, isChecked);
                if (mOnCheckedChangeListener != null) {
                    mOnCheckedChangeListener.onCheckedChanged(BottomTab.this, isChecked);
                }
            }
        });
    }

    
    public int getLayoutRes() {
        return R.layout.bottom_tab_main;
    }

    public TextView getHintTextView() {
        return mTvTabHint;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public BottomTab setTabTitle(String text) {
        mTvTabTitle.setText(text);
        return this;
    }

    public BottomTab setTabDrawable(int drawableResId) {
        mRivTabIcon.setImageDrawable(getResources().getDrawable(drawableResId));
        return this;
    }

    public BottomTab setTabDrawable(Drawable drawable) {
        mRivTabIcon.setImageDrawable(drawable);
        return this;
    }

    public BottomTab setHint(String msg) {
        mTvTabHint.setText(msg);
        return this;
    }

    public BottomTab setHint(int msg) {
        mTvTabHint.setText(String.valueOf(msg));
        return this;
    }

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes. This callback is used for internal purpose only.
     *
     * @param listener the callback to call on checked state change
     * @hide
     * @deprecated 请勿使用测方法，此方法本身为内部方法
     */
    @Override
    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeWidgetListener = listener;
    }

    private boolean mChecked = false;

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            mRivTabIcon.setChecked(mChecked);
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!mChecked);
    }
}
