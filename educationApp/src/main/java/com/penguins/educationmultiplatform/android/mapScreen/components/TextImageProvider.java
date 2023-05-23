package com.penguins.educationmultiplatform.android.mapScreen.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.yandex.runtime.image.ImageProvider;

public class TextImageProvider extends ImageProvider {

    private Context context;

    public TextImageProvider(Context context, String text){
        this.context = context;
        this.text = text;
    }
    @Override
    public String getId() {
        return "text_" + text;
    }

    private final String text;
    @Override
    public Bitmap getImage() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);

        Paint textPaint = new Paint();
        textPaint.setTextSize(15 * metrics.density);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);

        float widthF = textPaint.measureText(text);
        Paint.FontMetrics textMetrics = textPaint.getFontMetrics();
        float heightF = Math.abs(textMetrics.bottom) + Math.abs(textMetrics.top);
        float textRadius = (float)Math.sqrt(widthF * widthF + heightF * heightF) / 2;
        float internalRadius = textRadius + 3 * metrics.density;
        float externalRadius = internalRadius + 3 * metrics.density;

        int width = (int) (2 * externalRadius + 0.5);

        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(0xFF3B4560);
        canvas.drawCircle(width / 2, width / 2, externalRadius, backgroundPaint);

        backgroundPaint.setColor(Color.WHITE);
        canvas.drawCircle(width / 2, width / 2, internalRadius, backgroundPaint);

        canvas.drawText(
                text,
                width / 2,
                width / 2 - (textMetrics.ascent + textMetrics.descent) / 2,
                textPaint);

        return bitmap;
    }

    public TextImageProvider(String text) {
        this.text = text;
    }
}