package com.example.dungeonexplorer;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DungeonView extends View implements Runnable {

    private Handler moveHandler = new Handler();
    private boolean moving;
    private int currentFrame = 0;
    private Character chibi;
    private String direction;
    private int viewWidth;
    private int viewHeight;

    public DungeonView(Context context) {
        super(context);
        init();
    }

    public DungeonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DungeonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int CHARACTER_SIZE = 168;

        chibi = new Character(CHARACTER_SIZE,
                BitmapFactory.decodeResource(getResources(), R.drawable.chibi_sprite),
                new Rect(0, 2 * CHARACTER_SIZE, CHARACTER_SIZE, 3 * CHARACTER_SIZE),
                new Rect(100, 100, 2 * CHARACTER_SIZE, 2 * CHARACTER_SIZE),
                null,
                8);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
        chibi.drawCharacter(canvas);
    }

    @Override
    public void run() {
        chibi.move(direction, currentFrame, viewWidth, viewHeight);
        invalidate();
        if (moving) {
            if (currentFrame >= chibi.getMovingFramesCount()) {
                currentFrame = 0;
            } else {
                currentFrame++;
            }
            moveHandler.postDelayed(this, 100);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewHeight = getHeight();
        viewWidth = getWidth();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            moving = true;

            if (event.getX() < getWidth() / 3) {
                direction = "left";
                moveHandler.post(this);
            } else if (event.getX() > getWidth() * 2 / 3) {
                direction = "right";
                moveHandler.post(this);
            } else if (event.getY() < getHeight() / 2) {
                direction = "top";
                moveHandler.post(this);
            } else if (event.getY() >= getHeight() / 2) {
                direction = "bottom";
                moveHandler.post(this);
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            moving = false;
            currentFrame = 0;
        }
        return true;
    }

}
