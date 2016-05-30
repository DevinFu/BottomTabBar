package ui.kale.bottomtabbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import kale.bottomtab.view.BottomTab;
import kale.bottomtab.view.BottomTabGroup;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		BottomTabGroup root = (BottomTabGroup) findViewById(R.id.bottom_bar_root);

		BottomTab tab02 = (BottomTab) root.getChildAt(1);
		BottomTab tab03 = (BottomTab) root.getChildAt(2);


		BottomTab tab01 = (BottomTab) root.getChildAt(0);
		tab01.setHintBackgroundResource(R.drawable.red_hint); // 设置提示红点的背景
		tab01.setHint("99+"); // 设置提示红点上的文字
		tab01.setHintTextColor(0xffffffff); // 设置提示红点上文字的颜色

		tab03.setHint(1314520+"");

		BottomTab tab04 = (BottomTab) root.getChildAt(3);
		tab04.setTabDrawable(R.drawable.abc_btn_check_material) // 设置按钮的图片
				.setTabTitle("自定义") // 设置按钮下面的文字
				.setHint(520+""); // 设置提示红点部分的文字

		tab01.setChecked(true);

		root.setOnCheckedChangeListener(new BottomTabGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(BottomTabGroup root, int checkedId) {

				switch (checkedId) {
					case R.id.tab_01:
						Toast.makeText(getBaseContext(), "第1个", Toast.LENGTH_SHORT).show();
						break;
					case R.id.tab_02:
						Toast.makeText(getBaseContext(), "第2个", Toast.LENGTH_SHORT).show();
						break;
					case R.id.tab_03:
						Toast.makeText(getBaseContext(), "第3个", Toast.LENGTH_SHORT).show();
						break;
					case R.id.tab_04:
						Toast.makeText(getBaseContext(), "第4个", Toast.LENGTH_SHORT).show();
						break;
				}
			}
		});
	}

}
