/*
 * This is the source code of Telegram for Android v. 1.3.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013.
 */

package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.drawable.Drawable;
<<<<<<< HEAD
=======
import android.util.AttributeSet;
>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
import android.view.View;

public class BaseCell extends View {
    public BaseCell(Context context) {
        super(context);
    }

<<<<<<< HEAD
=======
    public BaseCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

>>>>>>> 5669c0dc333845448cc7ec627e73a6ff38979af2
    protected void setDrawableBounds(Drawable drawable, int x, int y) {
        setDrawableBounds(drawable, x, y, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
    }

    protected void setDrawableBounds(Drawable drawable, int x, int y, int w, int h) {
        drawable.setBounds(x, y, x + w, y + h);
    }
}
