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
import android.graphics.Typeface;
import com.xoidre.validarportugal.util.Log;

import java.util.HashMap;


public class FontLoader {

    /* base path in assets folder where fonts are located */
    private static final String FONT_BASE_PATH = "fonts/";

    /* HashMap to store the typefaces at runtime */
    HashMap<String, Typeface> typefaceHashMap;

    public static FontLoader sInstance;

    protected FontLoader() {
        typefaceHashMap = new HashMap<>();
    }

    /**
     * Singleton
     *
     * @return
     */
    public static FontLoader getInstance() {
        if (sInstance == null) {
            sInstance = new FontLoader();
        }
        return sInstance;
    }

    /**
     * Returns the typeface object with the desired font.
     *
     * @param fontName The name of the font.
     * @param context
     * @return
     */
    public Typeface getFont(String fontName, Context context) {
        Typeface typeFace = typefaceHashMap.get(fontName);
        if (typeFace != null) {
            return typeFace;

        } else {
            // load from assets if null
            try {
                typeFace = Typeface.createFromAsset(context.getAssets(), FONT_BASE_PATH + fontName);
                typefaceHashMap.put(fontName, typeFace);

            } catch (Exception e) {
                Log.e(this, "Error trying to create Typeface for font: " + FONT_BASE_PATH + fontName, e);
            }
        }

        if (typeFace == null) {
            return Typeface.DEFAULT;
        }
        return typeFace;
    }
}
