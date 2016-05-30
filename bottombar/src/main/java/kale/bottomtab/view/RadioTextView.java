package kale.bottomtab.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.TextView;

public class RadioTextView extends TextView implements Checkable {

    private boolean mChecked = false;

    private boolean mBroadcasting;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(RadioTextView pView, boolean isChecked);
    }

    /**
     * android.R.attr.state_checked：指示控件已经选中时的状态，如果给控件指定这个属性，就代表控件就已经选中，
     * 会调用selector里的android:state_checked="true"来加载它的显示图片。
     */
    int[] CHECKED_STATE_SET = {android.R.attr.state_checked};

    public RadioTextView(Context context) {
        this(context, null);
    }

    public RadioTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RadioTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.init();
    }

    private void init() {
        this.setClickable(true);
    }


    @Override
    public void setChecked(boolean checked) {
        if (mChecked != checked) {
            mChecked = checked;
            refreshDrawableState();
        }

        if (mBroadcasting)
            return;

        mBroadcasting = true;
        if (mOnCheckedChangeListener != null) {
            mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
        }
        mBroadcasting = false;
    }

    public void setChecked(boolean checked, boolean broadcast) {
        if (broadcast) this.setChecked(checked);
        else {
            if (mChecked != checked) {
                mChecked = checked;
                refreshDrawableState();
            }
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        this.setChecked(!mChecked);
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        this.mOnCheckedChangeListener = listener;
    }

    /**
     * @param extraSpace 代表额外的空间，即如果为0，即代表用户不再另外指定属性，直接返回本身所具有的控件状态集。如果不为0，
     *                   多余的位置就可以用来放置自定义的状态,使该Drawable具有我们添加的属性。
     * @return 如果extraSpace为0，直接返回控件本身所具有的状态集。
     * 如果extraSpace不为0，返回的值除了具有控件本身所具有的状态集，会额外分配指定的空间以便用户自主指定状态。
     */
    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }


}
