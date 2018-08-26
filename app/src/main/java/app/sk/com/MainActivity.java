package app.sk.com;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int totalItem = 100;
    ImageView imageView;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    float i = 10;
    boolean isFrstTime = true;
    TranslateAnimation animation;
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = (RelativeLayout) findViewById(R.id.parent_view);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        imageView = (ImageView) findViewById(R.id.image);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ListPageAdapter(createList(), this, R.layout.single_row));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (!isFrstTime)
                        startrightToAnimation(); // Scrolling up
                } else {
                    if (!isFrstTime)
                        startLeftTorightAnimation(); // Scrolling down
                }
                isFrstTime = false;
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                    //  startAnimation();
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                    // startAnimation();
                } else {
                    // Do something
                }
            }
        });
    }

    public void startrightToAnimation() {
        //TranslateAnimation animation;
        float centreX = relativeLayout.getX() + relativeLayout.getWidth() / 2;
        float centreY = relativeLayout.getY() + relativeLayout.getHeight() / 2;
        float j = imageView.getX() + i + 25;
        Log.e("TAG", width + "width," + j + "");

        if (i == 0) {
            animation = new TranslateAnimation(imageView.getPivotX(), i, 0, 0);
        } else {
            if (width > j) {
                Log.e("TAG", width + "width," + j + "test");

                animation = new TranslateAnimation(imageView.getPivotX() + i, i, 0, 0);
            }
        }
        animation.setDuration(20);
        animation.setFillAfter(true);
        animation.setAnimationListener(new MyAnimationListener());
        if (width > j) {
            imageView.startAnimation(animation);
            i += 3;
        }

    }


    public void startLeftTorightAnimation() {
      /*  float centreX = relativeLayout.getX() + relativeLayout.getWidth() / 2;
        float centreY = relativeLayout.getY() + relativeLayout.getHeight() / 2;
        //  Log.e("TAG", centreX + "tx");
        Log.e("TAG", i + "ty");*/

        animation = new TranslateAnimation(i, i - 10, 0, 0);

        animation.setFillAfter(true);
        float x = imageView.getPivotX() + i;
        Log.e("TAG", x + "xxx");
        imageView.startAnimation(animation);
        i -= 3;

    }

    public List<String> createList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i <= totalItem; i++) {
            list.add("Item=" + i);
        }
        return list;
    }

    class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationEnd(Animation animation) {

        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }
}
