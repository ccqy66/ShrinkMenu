###ShrinkMenu<br>
---
ShrinkMenu是一个圆形菜单的自定义view的库，使用该库，你可以非常简单的生成一个绚丽的圆形菜单，在使用过程中，你可以完全根据你自己的想法来重新设计你想要的结果。因为你可以自定义显示的菜单个数、菜单的图标样式、菜单的布局大小...，而这些操作在该库的使用下仅仅只是一个配置项而已。总之。如果你想要一个圆形的菜单，那就尽情的使用它吧！
####如何使用?<br>
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
NOTICE:对于上述属性的解释。
layout_width:对应于整个menu菜单所占据的宽度，建议与高度相同;
layout_height:对应于整个menu菜单所占据的高度，建议与宽度相同;
layout_radius:对应与圆形menu菜单的半径，为了显示的效果，建议设置为与整个menu菜单的高度和宽度相同
circle_radius:对应于每个menu菜单的半径

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