package laoathsolutions.noyogurt.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import laoathsolutions.noyogurt.R;
import laoathsolutions.noyogurt.api.User;

/**
 * view to draw the legend
 */
public class LegendView extends View {
    private List<User> mUsers;
    public void setUsers(List<User> users) {
        mUsers = users;
    }

    public LegendView(Context context) {
        super(context);
    }

    public LegendView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LegendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), 200);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int eachItemWidth = width/mUsers.size();
        int position = 0;
        for(User user : mUsers) {
            canvas.save();
            Rect dst = new Rect(eachItemWidth * position, 0,eachItemWidth * (position + 1), eachItemWidth);
            Bitmap bitmapForPostion = PlateImageHelper.getBitmapForPostion(getContext(), position);
            Rect src = new Rect(0, 0,bitmapForPostion.getWidth(),bitmapForPostion.getHeight());
            canvas.drawBitmap(bitmapForPostion,src,dst,new Paint());
            canvas.restore();
            position++;
        }
    }
}
