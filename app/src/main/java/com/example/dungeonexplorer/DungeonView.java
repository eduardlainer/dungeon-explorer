package com.example.dungeonexplorer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

public class DungeonView extends View {

    public DungeonView(Context context) {
        super(context);
    }

    public DungeonView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DungeonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GRAY);
    }

}
