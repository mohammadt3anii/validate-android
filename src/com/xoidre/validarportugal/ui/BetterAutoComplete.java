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

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;
import com.xoidre.validarportugal.R;

/**
 * This class allows you to setup the font style of a {@link android.widget.AutoCompleteTextView}
 *
 * @date 30-03-2015
 */
public class BetterAutoComplete extends AutoCompleteTextView {

    public BetterAutoComplete(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        setStyles(context, attributeSet);
    }

    public BetterAutoComplete(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setStyles(context, attributeSet);
    }

    public BetterAutoComplete(Context context) {
        super(context);
    }


    /**
     * Processing of custom styling attributes
     *
     * @param context
     * @param attributeSet
     */
    private void setStyles(Context context, AttributeSet attributeSet) {
        if (!isInEditMode()) {
            TypedArray attributes = context.obtainStyledAttributes(attributeSet, R.styleable.BetterEditText);
            final int count = attributes.getIndexCount();
            for (int i = 0; i < count; ++i) {
                int attribute = attributes.getIndex(i);
                if (attribute == R.styleable.BetterAutoComplete_fontFace) {
                    String fontFace = attributes.getString(attribute);
                    if (!fontFace.isEmpty()) {
                        setTypeface(FontLoader.getInstance().getFont(fontFace, context));
                    }
                }
            }
            attributes.recycle();
        }
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
    }
}
