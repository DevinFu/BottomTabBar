# BottomTabBar

说明：此控件是在Jack Tony: <developer_kale@.com>的BottomTabBar项目基础上进行的改造，原项目地址：<https://github.com/tianzhijiexian/BottomTabBar>。在此表示感谢。
 主要是由于原项目中，tab采用drawable top 和 radio button形式，无法灵活控制drawable的大小及drawable与文字之间的间距，现采用自定义的RadioImageView实现tab上的drawable，RadioTextView实现tab的标题。 

## 效果图

底部tab切换栏。完全模仿了RadioGroup的机制，提供了可以扩展的接口  
![image](./demoPic/demo01.png)   
`BottomTabGroup`完全模仿了RadioGroup的机制，你可以理解为它是`RadioGroup`的增强版本，它内部的view不再仅限于`RadioButton`了，而是实现了`BottomTabImpl`这个接口的的任何view。这样我们可以很容易的用自定义控件来做类似RadioButton的效果了，自然而然就能有更多的扩展性。  

## 如何使用  
--   

### 引入library

    ```  
	dependencies {
	        compile 'com.frozy:bottombar:1.0'
	}
    ```  

#### 1.首先在布局中像放RadioGroup时放一个BottomTabGroup:  

```xml
 	<kale.bottomtab.view.BottomTabGroup
        android:id="@+id/bottom_bar_root"
        android:layout_width="match_parent"
        android:layout_height="54dp"
        android:layout_alignParentBottom="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true"
        android:background="@drawable/list_vertical_root_bg"
        android:orientation="horizontal"
        android:paddingTop="5dp"
        >

        <kale.bottomtab.view.BottomTab
            android:id="@+id/tab_01"
            style="@style/bottomTab"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:drawableTop="@drawable/main_bottombar_icon_home_selector"
            app:tabText="tab01"/>

        <kale.bottomtab.view.BottomTab
            android:id="@+id/tab_02"
            style="@style/bottomTab"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:hintEnable="false"
            app:drawableTop="@drawable/main_bottombar_icon_home_selector"
            app:tabText="tab02"/>

        <kale.bottomtab.view.BottomTab
            android:id="@+id/tab_03"
            style="@style/bottomTab"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:drawableTop="@drawable/main_bottombar_icon_home_selector"
            app:tabText="tab03"/>

        <kale.bottomtab.view.BottomTab
            android:id="@+id/tab_04"
            style="@style/bottomTab"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:drawableTop="@drawable/main_bottombar_icon_home_selector"
            app:tabText="tab04"/>

    </kale.bottomtab.view.BottomTabGroup>
    ```

#### 2.style/bottomTab定义
    
```xml
    <style name="bottomTab">
        <item name="tabTextColor">@color/main_bottombar_text_selector</item>
        <item name="tabTextSize">12sp</item>
        <item name="hintTextColor">@android:color/white</item>
        <item name="hintTextSize">3sp</item>
        <item name="hintTextBackground">@drawable/red_hint</item>
        <item name="hintEnable">true</item>
        <item name="verticalSpace">6dp</item>
    </style>
    ```

#### 这样就搞定啦~  
![image](./demoPic/demo02.png)   


### 设置属性    
--  
如果你用的是包中提供的BottomTab的话，那么参考以上使用示例, 有下面这几个属性可以设置：  

`app:tabText`：设置按钮下方的文字  
`app:tabTextColor`：设置按钮的颜色  
`app:drawableTop`：设置按钮中的图片    
`app:tabTextSize`: 设置按钮文字大小
`app:hintText`:设置角标文字
`app:hintTextColor`:设置角标文字颜色
`app:hintTextSize`:设置角标文字大小
`app:hintTextBackground`:设置角标背景
`app:hintEnable`:设置是否显示角标
`app:verticalSpace`:设置按钮文字与图片的垂直间距, 图片会自适应大小

### 扩展    
--  
前面说到了BottomTab就是一个具体的实现类，我们完全可以用自定义View的方式来做出自己的按钮和红点来，下面推荐两种实现方式，可以按需求来做。  
1.继承BottomTab**（简单）**   
继承BottomTab这个类，然后复写`getLayoutRes()`这个方法，传入你自定义的一个layout的id:  

    ```java  
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
    ```  

在这个layout中你必须要放入一个id为：tab_btn和tab_hint的两个View，其中一个是RadioButton，一个是TextView。这样id为tab_hint的TextView就可以做红点提示，而id为tab_btn的RadioButton就可以做实体的按钮了。需要注意的是BottomTab这个view是继承自RelativeLayout的，所以在做布局的时候需要注意下控件摆放的位置。为了减少布局的层次，你还可以用merge标签。就像下面的写法：  

```xml  
<merge xmlns:android="http://schemas.android.com/apk/res/android"
    >

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        >

        <kale.bottomtab.view.RadioImageView
            android:id="@id/tab_icon"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_centerInParent="true"
            />

        <TextView
            android:id="@id/tab_hint"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerHorizontal="true"
            android:layout_marginLeft="-5dp"
            android:layout_toRightOf="@id/tab_icon"
            android:paddingLeft="4dp"
            android:paddingRight="4dp"
            android:singleLine="true"
            android:textSize="11sp"
            />
    </RelativeLayout>

    <kale.bottomtab.view.RadioTextView
        android:id="@id/tab_title"
        android:gravity="center"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:singleLine="true"/>

</merge>
```
  
2.实现BottomTabImpl**（扩展性强）**    
自定义一个view实现BottomTabImpl这个接口，然后请模仿BottomTab的写法进行编写。这样的方式是需要重新编写一些按钮点击事件，但是扩展性是最强的。


### License

    Copyright 2015 Jack Tony

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 
