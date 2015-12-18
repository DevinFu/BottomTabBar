package kale.kale.bottomtab;

import android.widget.Checkable;

/**
 * @author Jack Tony
 * @date 2015/6/7
 */
public interface BottomTabImpl extends Checkable{

    /**
     * Interface definition for a callback to be invoked when the checked state
     * of a compound button changed.
     */
    public static interface OnCheckedChangeListener {
        /**
         * Called when the checked state of a compound button has changed.
         *
         * @param buttonView The compound button view whose state has changed.
         * @param isChecked  The new checked state of buttonView.
         */
        void onCheckedChanged(BottomTabImpl buttonView, boolean isChecked);
    }

    public int getId();

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes.
     *
     * @param listener the callback to call on checked state change
     */
   // public void setOnCheckedChangeListener(OnCheckedChangeListener listener);

    /**
     * Register a callback to be invoked when the checked state of this button
     * changes. This callback is used for internal purpose only.
     *
     * @param listener the callback to call on checked state change
     * @hide
     */
    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener listener);
}
