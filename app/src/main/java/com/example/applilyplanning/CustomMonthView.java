package com.example.applilyplanning;
import

/**
 * CustomMonthView with canvas
 */
public class CustomMonthView extends MonthView {

    /**
     * draw select calendar
     *
     * @param canvas    canvas
     * @param calendar  select calendar
     * @param x         calendar item x start point
     * @param y         calendar item y start point
     * @param hasScheme is calendar has scheme?
     * @return if return true will call onDrawScheme again
     */
    protected boolean onDrawSelected(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme) {
        canvas.drawRect(x , y , x + mItemWidth , y + mItemHeight, mSelectedPaint);
        return true;
    }

    /**
     * draw scheme if calendar has scheme
     *
     * @param canvas   canvas
     * @param calendar calendar has scheme
     * @param x        calendar item x start point
     * @param y        calendar item y start point
     */
    protected void onDrawScheme(Canvas canvas, Calendar calendar, int x, int y) {
        canvas.drawCircle(x + mItemWidth / 2, y + mItemHeight - 3 * mPadding, mPointRadius, mPointPaint);
    }

    /**
     * draw text
     *
     * @param canvas     canvas
     * @param calendar   calendar
     * @param x          calendar item x start point
     * @param y          calendar item y start point
     * @param hasScheme  is calendar has scheme?
     * @param isSelected is calendar selected?
     */
    protected void onDrawText(Canvas canvas, Calendar calendar, int x, int y, boolean hasScheme, boolean isSelected) {
        float baselineY = mTextBaseLine + y;
        int cx = x + mItemWidth / 2;
        canvas.drawText(String.valueOf(calendar.getDay()),
                cx,
                baselineY,
                calendar.isCurrentDay() ? mCurDayTextPaint :
                        calendar.isCurrentMonth() ? mSchemeTextPaint : mOtherMonthTextPaint);
    }
}
