/**********************************************************************************
 * Copyright (c) 2015 Xoidre-Team <me@joaquimley.com>                             *
 *                                                                                *
 * Permission is hereby granted, free of charge, to any person obtaining a copy   *
 * of this software and associated documentation files (the "Software"), to deal  *
 * in the Software without restriction, including without limitation the rights   *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell      *
 * copies of the Software, and to permit persons to whom the Software is          *
 * furnished to do so, subject to the following conditions:                       *
 *                                                                                *
 * The above copyright notice and this permission notice shall be included in all *
 * copies or substantial portions of the Software.                                *
 *                                                                                *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR     *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,       *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE    *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER         *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  *
 * SOFTWARE.                                                                      *
 **********************************************************************************/

package com.xoidre.validarportugal.ui;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * This class allows you to implement OnDrawableClickListener
 *
 * @date 30-03-2015
 */
public abstract class BetterDrawableOnTouchListener implements OnTouchListener {

    private static final int SAFETY_MARGIN = 10;
    private static final int POSITION_TOP_RIGHT = 4;
	private Drawable mDrawable;

	public BetterDrawableOnTouchListener(BetterEditText editText) {
		super();
		final Drawable[] drawables = editText.getCompoundDrawables();
		if (drawables != null && drawables.length == POSITION_TOP_RIGHT) {
            for (Drawable drawable : drawables) {
                if (drawable != null) {
                    mDrawable = drawable;
                    return;
                }
            }
		}
	}

    /**
     * Check if the drawable "area" was clicked.
     * Note: Assuming a drawable on the right of the view
     *
     * @param view
     * @param event
     * @return
     */
	@Override
	public boolean onTouch(final View view, final MotionEvent event) {
		if (event != null && mDrawable != null) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				final int x = (int) event.getX();
				final int y = (int) event.getY();
				final Rect bounds = mDrawable.getBounds();
				if (bounds != null) {
					if (x >= view.getRight() - view.getPaddingRight() - bounds.width() - SAFETY_MARGIN && x <= view.getRight() + SAFETY_MARGIN
							&& y >= -SAFETY_MARGIN && y <= view.getHeight() + SAFETY_MARGIN) {
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							return OnDrawableClickListener(event);
						}
					}
				}
			}
		}
		return false;
	}

	public abstract boolean OnDrawableClickListener(final MotionEvent event);
}