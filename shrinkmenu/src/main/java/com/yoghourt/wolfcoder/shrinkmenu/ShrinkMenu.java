package com.yoghourt.wolfcoder.shrinkmenu;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.ImageView;

import com.yoghourt.wolfcoder.shrinkmenu.entity.ImageLocationEntity;
import com.yoghourt.wolfcoder.shrinkmenu.utils.ViewUtils;

import java.util.ArrayList;

/**
 * Description:
 * User: Dream_Coder(chenchen_839@126.com)
 * Date: 2015-11-02
 * Time: 22:28
 */
public class ShrinkMenu extends ViewGroup implements View.OnClickListener{
    private Context mContext;

    private int layout_radius;
    private int circle_radius;
    private int menuIcon ;
    private int[] imageResource;

    private long lastClickTime;
    private long duration;
    private boolean flag = false;

    private ArrayList<ImageLocationEntity> locationEntities;
    private ShrinkMenuOnClickListener onClickListener = null;
    private static final String CENTER_TAG = "center";
    public interface ShrinkMenuOnClickListener{
        void onMenuClick(View view);
    }
    public ShrinkMenu(Context context) {
        super(context);
        mContext = context;
    }
    public ShrinkMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initParams(attrs);
    }
    private void initParams(AttributeSet attrs){
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.shrinkMenu);
        layout_radius = (int) typedArray.getDimension(R.styleable.shrinkMenu_layout_radius, 0);
        circle_radius = (int) typedArray.getDimension(R.styleable.shrinkMenu_circle_radius, 0);

    }
    public void setup() {
        if(imageResource == null) {
            throw new RuntimeException("you must specify at least one image as menu");
        }
        resource2Entity();
        ImageView imageView = new ImageView(mContext);
        if(menuIcon == 0) {
            imageView.setImageResource(R.drawable.plus);
        }else {
            imageView.setImageResource(menuIcon);
        }
        if(duration == 0) {
            duration = 400;
        }
        imageView.setTag(CENTER_TAG);
        imageView.setLayoutParams(new LayoutParams(circle_radius, circle_radius));
        imageView.setOnClickListener(this);
        addView(imageView);
        setBackgroundColor(getResources().getColor(R.color.layoutBackground));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //首先先将所有的图标固定在圆心位置，除了选择按钮，其他设置为不可见
        for (int i = 0 ; i < count ; i++) {
            View imageView = getChildAt(i);
            imageView.layout(0, layout_radius-circle_radius, circle_radius, layout_radius);
        }
    }
    @Override
    public void onClick(View v) {
        String viewTag = (String) v.getTag();
        if(viewTag.equals("center")) {
            if(!timeRecoder()) return;
            if(!flag) {
                open();
            }else {
                close();
            }
        }else  {
            onClickListener.onMenuClick(v);
        }


    }

    /**
     * 关闭菜单
     */
    private void close() {
        int viewCount = getChildCount();
        View centerView = getChildAt(viewCount - 1);
        ObjectAnimator center_rotate = ObjectAnimator.ofFloat(centerView,"rotation",0f);
        center_rotate.setDuration(duration);
        center_rotate.start();
        for (int i= viewCount-2; i>=0; i--) {
            final ImageLocationEntity entity = locationEntities.get(i);
            AnimatorSet set = new AnimatorSet();

            ObjectAnimator animatorX = ObjectAnimator.ofFloat(entity.getImageView(),"translationX",0);
            animatorX.setStartDelay(100 * (viewCount-i-1));
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(entity.getImageView(),"translationY",0);
            animatorY.setStartDelay(100 * (viewCount-i-1));
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(entity.getImageView(), "scaleX", 1f, 0);
            scaleX.setStartDelay(100 * (viewCount-i-1));
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(entity.getImageView(), "scaleY", 1f, 0);
            scaleY.setStartDelay(100 * (viewCount-i-1));

            set.playTogether(animatorX, animatorY, scaleX, scaleY);
            set.setDuration(duration);
            set.setInterpolator(new AnticipateOvershootInterpolator());
            set.start();

        }
        changeState();
    }

    /**
     * 打开菜单
     */
    private void open() {
        int viewCount = getChildCount();
        View centerView = getChildAt(viewCount - 1);
        ObjectAnimator center_rotate = ObjectAnimator.ofFloat(centerView,"rotation",0f,-90f);
        center_rotate.setDuration(duration);
        center_rotate.start();
        for (int i=0; i<viewCount-1 ; i++) {
            AnimatorSet set = new AnimatorSet();
            ImageLocationEntity entity = locationEntities.get(i);
            entity.getImageView().setVisibility(VISIBLE);

            ObjectAnimator animatorX = ObjectAnimator.ofFloat(entity.getImageView(),"translationX",0,entity.getLeft());
            ObjectAnimator animatorY = ObjectAnimator.ofFloat(entity.getImageView(),"translationY",0,-entity.getTop());
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(entity.getImageView(), "scaleX", 0, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(entity.getImageView(), "scaleY", 0, 1f);

            set.playTogether(animatorX, animatorY, scaleX, scaleY);
            set.setDuration(duration);
            set.setInterpolator(new AnticipateOvershootInterpolator());
            set.start();
        }
        changeState();
    }
    /**
     * 添加菜单图片
     * @param resource
     */
    public void addMenuImage(int[] resource) {
        this.imageResource = resource;
    }

    /**
     * 计算菜单图片的位置
     */
    private void resource2Entity() {
        locationEntities = new ArrayList<ImageLocationEntity>();
        int imageCount = imageResource.length;
        double peerAngle = (Math.PI/(2*(imageCount-1)));
        for (int i = 0; i < imageCount; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(imageResource[i]);
            imageView.setLayoutParams(new LayoutParams(circle_radius, circle_radius));
            imageView.setVisibility(INVISIBLE);
            ViewUtils.generateViewTag(imageView, i);
            imageView.setOnClickListener(this);
            addView(imageView);
            ImageLocationEntity entity = new ImageLocationEntity(imageView);
            double currentAngle = peerAngle*(imageCount-1-i);
            int left = (int)((layout_radius-circle_radius)*Math.cos(currentAngle));
            int top = (int)((layout_radius-circle_radius) * Math.sin(currentAngle));
            entity.setLeft(left);
            entity.setTop(top);
            entity.setRight(left+circle_radius);
            entity.setBottom(top+circle_radius);
            locationEntities.add(entity);
        }
    }
    public void setOnClickListener(ShrinkMenuOnClickListener listener) {
        this.onClickListener = listener;
    }

    /**
     * 记录两次点击的时间，防止用户点击过快，造成一次打开或者关闭动画未播放结束就点击的问题
     * @return
     */
    private boolean timeRecoder() {
        long currentClickTime = System.currentTimeMillis();
        if(currentClickTime - lastClickTime > duration*(imageResource.length-2)) {
            lastClickTime = currentClickTime;
            return true;
        }
        return false;
    }

    /**
     * 改变点击的状态
     */
    private void changeState() {
        flag = !flag;
    }

    /**
     * 设置菜单选项按钮
     * @param menuIcon
     */
    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    /**
     * 设置动画的间隔
     * @param duration
     */
    public ShrinkMenu setDuration(long duration) {
        this.duration = duration;
        return this;
    }
}
