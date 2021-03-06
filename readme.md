###ShrinkMenu<br>
---
ShrinkMenu是一个圆形菜单的自定义view的库，使用该库，你可以非常简单的生成一个绚丽的圆形菜单，在使用过程中，你可以完全根据你自己的想法来重新设计你想要的结果。因为你可以自定义显示的菜单个数、菜单的图标样式、菜单的布局大小...，而这些操作在该库的使用下仅仅只是一个配置项而已。总之。如果你想要一个圆形的菜单，那就尽情的使用它吧！
![image](https://raw.githubusercontent.com/ccqy66/ShrinkMenu/master/Art/demo.gif)
---

 - 使用之前，首先将该库导入到你的项目根目录下，然后在`build.gradle`中加入以下代码
 

> compile project(':shrinkmenu')

 - 然后再在你的布局文件中加入我们的自定义ViewGroup

> ```java
<com.yoghourt.wolfcoder.shrinkmenu.ShrinkMenu
        android:id="@+id/menu"
        android:layout_width="150dp"
        android:layout_height="150dp"
        myview:layout_radius="150dp"
        myview:circle_radius="50dp"
        android:layout_alignParentBottom="true"
</com.yoghourt.wolfcoder.shrinkmenu.ShrinkMenu>
```
NOTICE:对于上述属性的解释。<br>
layout_width:对应于整个menu菜单所占据的宽度，建议与高度相同;<br>
layout_height:对应于整个menu菜单所占据的高度，建议与宽度相同;<br>
layout_radius:对应与圆形menu菜单的半径，为了显示的效果，建议设置为与整个menu菜单的高度和宽度相同<br>
circle_radius:对应于每个menu菜单的半径<br>

 - 之后在我们的Activity文件中使用如下方法加入我们的menu

> ```java
ShrinkMenu menu = (ShrinkMenu) findViewById(R.id.menu);
        menu.addMenuImage(new int[]{R.drawable.person,
                R.drawable.connection, R.drawable.message,
                R.drawable.home});
        menu.setMenuIcon(R.drawable.plus);
        menu.setup();
        menu.setOnClickListener(new ShrinkMenu.ShrinkMenuOnClickListener() {
            @Override
            public void onMenuClick(View view) {
                if(view.getTag().equals(ViewUtils.getViewTag(0))) {
                    Toast.makeText(MainActivity.this,"you click the first menuIcon",Toast.LENGTH_SHORT)
                            .show();
                });
```
对于上述代码的说明：<br>
当获得了ShrinkMenu对象之后，你首先要的事就是添加你的每个menu所对应的Icon图标，以便来标示你的菜单。你至少要添加一个，因为如果一个都没有这是没有意义的。随意你需要调用`addMenuImage(int[] iamgeList)`方法。传入图片资源id。<br>
当然你如果对默认的十字不满意，可以通过调用`setMenuIcon(int imageId)`方法来自定义你自己的中心选择按钮。

 - 对于点击事件处理：<br>
 

	> 该View对时间的处理需要设定监听器`setOnClickListener( ShrinkMenu.ShrinkMenuOnClickListener())`并重写`onMenuClick(View view)`方法，对于该时间的处理与我们平时使用的View.OnClickListener 处理方法有略微的不同，我们是通过view的tag来标示一个view的，你可以使用`ViewUtils.getViewTag(int index)`方法来获取指定位置的view，index从0开始。例如：如果我们点击第3个view时，弹出一个toast，我们可以使用如下的方式：<br>
```java
if(view.getTag().equals(ViewUtils.getViewTag(2))) {
                    Toast.makeText(MainActivity.this,"you click the third menuIcon",Toast.LENGTH_SHORT)
                            .show();
                });
```

现在，去使用它吧!