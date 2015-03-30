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

package test;

import android.test.ActivityInstrumentationTestCase2;
import com.xoidre.validarportugal.MainActivity;
import com.xoidre.validarportugal.R;
import com.xoidre.validarportugal.ui.BetterButton;
import com.xoidre.validarportugal.ui.BetterEditText;

public class TestActivity extends ActivityInstrumentationTestCase2<MainActivity> {

    private BetterButton mBtnCc;
    private BetterButton mBtnBi;
    private BetterButton mBtnNif;
    private BetterButton mBtnNiss;
    private BetterEditText mEditText;

    public TestActivity(Class<MainActivity> activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MainActivity activity = this.getActivity();
        mEditText = (BetterEditText) activity.findViewById(R.id.etDocNumber);
        mBtnCc = (BetterButton) activity.findViewById(R.id.btnValidateCc);
        mBtnBi = (BetterButton) activity.findViewById(R.id.btnValidateBi);
        mBtnNif = (BetterButton) activity.findViewById(R.id.btnValidateNif);
        mBtnNiss = (BetterButton) activity.findViewById(R.id.btnValidateNiss);
    }

    public void testPreconditions() {
        assertNotNull(mEditText);
        assertNotNull(mBtnCc);
        assertNotNull(mBtnBi);
        assertNotNull(mBtnNif);
        assertNotNull(mBtnNiss);
    }
}