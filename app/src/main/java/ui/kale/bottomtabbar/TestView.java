package ui.kale.bottomtabbar;

import android.content.Context;
import android.util.AttributeSet;

import kale.ui.view.BottomTab;

/**
 * @author Jack Tony
 * @date 2015/6/9
 */
public class TestView extends BottomTab{


    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.test_main;
    }
}
