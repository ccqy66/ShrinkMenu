package com.yoghourt.wolfcoder.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yoghourt.wolfcoder.shrinkmenu.ShrinkMenu;
import com.yoghourt.wolfcoder.shrinkmenu.utils.ViewUtils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                }else if(view.getTag().equals(ViewUtils.getViewTag(1))) {
                    Toast.makeText(MainActivity.this,"you click the second menuIcon",Toast.LENGTH_SHORT)
                            .show();
                }else if(view.getTag().equals(ViewUtils.getViewTag(2))) {
                    Toast.makeText(MainActivity.this,"you click the third menuIcon",Toast.LENGTH_SHORT)
                            .show();
                }else if(view.getTag().equals(ViewUtils.getViewTag(3))) {
                    Toast.makeText(MainActivity.this,"you click the forth menuIcon",Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });
    }

}
