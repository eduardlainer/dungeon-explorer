package com.example.dungeonexplorer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

class Character {

    private final int CHARACTER_SIZE;

    private Bitmap chibiSprite;
    private Rect sourceRect;
    private Rect destinationRect;
    private Paint emptyPaint;

    private int movingFramesCount;
    private int dx, dy;

    Character(int CHARACTER_SIZE, Bitmap chibiSprite, Rect sourceRect, Rect destinationRect, Paint emptyPaint, int movingFramesCount) {
        this.CHARACTER_SIZE = CHARACTER_SIZE;
        this.chibiSprite = chibiSprite;
        this.sourceRect = sourceRect;
        this.destinationRect = destinationRect;
        this.emptyPaint = emptyPaint;
        this.movingFramesCount = movingFramesCount;
    }

    void drawCharacter(Canvas canvas) {
        canvas.drawBitmap(chibiSprite,
                sourceRect,
                destinationRect,
                emptyPaint
        );
    }

    private void characterMoveLeft(int currentFrame) {
        sourceRect = new Rect(currentFrame * CHARACTER_SIZE,
                9 * CHARACTER_SIZE,
                (currentFrame + 1) * CHARACTER_SIZE,
                10 * CHARACTER_SIZE);
    }

    private void characterMoveRight(int currentFrame) {
        sourceRect = new Rect(currentFrame * CHARACTER_SIZE,
                11 * CHARACTER_SIZE,
                (currentFrame + 1) * CHARACTER_SIZE,
                12 * CHARACTER_SIZE);
    }


    private void characterMoveBottom(int currentFrame) {
        sourceRect = new Rect(currentFrame * CHARACTER_SIZE,
                10 * CHARACTER_SIZE,
                (currentFrame + 1) * CHARACTER_SIZE,
                11 * CHARACTER_SIZE);
    }

    private void characterMoveTop(int currentFrame) {
        sourceRect = new Rect(currentFrame * CHARACTER_SIZE,
                8 * CHARACTER_SIZE,
                (currentFrame + 1) * CHARACTER_SIZE,
                9 * CHARACTER_SIZE);
    }

    void move(String direction, int currentFrame, int viewWidth, int viewHeight) {

        int MOVE_RATIO = 10;

        switch (direction) {
            case "right":
                characterMoveRight(currentFrame);
                dx = destinationRect.right < viewWidth ? MOVE_RATIO : 0;
                dy = 0;
                break;
            case "left":
                characterMoveLeft(currentFrame);
                dx = destinationRect.left > 0 ? -MOVE_RATIO : 0;
                dy = 0;
                break;
            case "top":
                characterMoveTop(currentFrame);
                dx = 0;
                dy = destinationRect.top > 0 ? -MOVE_RATIO : 0;
                break;
            case "bottom":
                characterMoveBottom(currentFrame);
                dx = 0;
                dy = destinationRect.bottom < viewHeight ? MOVE_RATIO : 0;
                break;
        }

        destinationRect.left += dx;
        destinationRect.right += dx;
        destinationRect.top += dy;
        destinationRect.bottom += dy;
    }

    int getMovingFramesCount() {
        return movingFramesCount;
    }


}
