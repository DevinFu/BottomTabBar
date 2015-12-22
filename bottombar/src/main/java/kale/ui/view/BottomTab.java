package kale.ui.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import kale.bottombar.R;
import kale.kale.bottomtab.BottomTabImpl;

public class BottomTab extends LinearLayout implements BottomTabImpl {

    private final static int DEFAULT_TITLE_TEXT_SIZE = 36;

    private CharSequence mTabTitle;
    private int mTabTitleSize;
    private ColorStateList mTabTextColors;
    private Drawable mTabDrawable;

    private TextView mTvTabHint;
    private RadioImageView mRivTabIcon;
    private RadioTextView mRtvTabTitle;

    private boolean mChecked = false;

    private OnCheckedChangeListener mOnCheckedChangeWidgetListener;

    public BottomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs == null) {
            // Preserve behavior prior to removal of this API.
            throw new NullPointerException();
        }

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomTab);
        mTabTextColors = a.getColorStateList(R.styleable.BottomTab_android_textColor);
        mTabTitle = a.getText(R.styleable.BottomTab_android_text);
        mTabTitleSize = a.getDimensionPixelSize(R.styleable.BottomTab_android_textSize, DEFAULT_TITLE_TEXT_SIZE);
        mTabDrawable = a.getDrawable(R.styleable.BottomTab_android_drawableTop);
        a.recycle();

        this.initViews();
    }

    private void initViews() {
        super.setOrientation(VERTICAL);
        inflate(getContext(), getLayoutRes(), this);

        mTvTabHint = (TextView) findViewById(R.id.tab_hint);
        mRivTabIcon = (RadioImageView) findViewById(R.id.tab_icon);
        mRtvTabTitle = (RadioTextView) findViewById(R.id.tab_title);

        mRivTabIcon.setImageDrawable(mTabDrawable);
        mRtvTabTitle.setText(mTabTitle);
        mRtvTabTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTabTitleSize);
        if (mTabTextColors != null) {
            mRtvTabTitle.setTextColor(mTabTextColors);
        }

        mRivTabIcon.setClickable(false);
        mRtvTabTitle.setClickable(false);
        this.setClickable(true);
    }

    @Override
    public boolean performClick() {
        if (!mChecked) {
            setChecked(true);
            return true;
        }
        return super.performClick();
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
        mRtvTabTitle.setText(text);
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
     * changes. This callback is used for internal purpose only.
     *
     * @param listener the callback to call on checked state change
     * @deprecated 请勿使用测方法，此方法本身为内部方法
     */
    @Override
    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeWidgetListener = listener;
    }

    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            mRivTabIcon.setChecked(mChecked);
            mRtvTabTitle.setChecked(mChecked);
            mTvTabHint.refreshDrawableState();
            mOnCheckedChangeWidgetListener.onCheckedChanged(this, checked);
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
//        setChecked(!mChecked);
    }
}
