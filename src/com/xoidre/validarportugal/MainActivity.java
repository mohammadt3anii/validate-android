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

package com.xoidre.validarportugal;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import com.devspark.appmsg.AppMsg;
import com.xoidre.validarportugal.helper.CancelAppMsg;
import com.xoidre.validarportugal.helper.DocumentType;
import com.xoidre.validarportugal.helper.Functions;
import com.xoidre.validarportugal.helper.ValidatorHelper;
import com.xoidre.validarportugal.ui.BetterButton;
import com.xoidre.validarportugal.ui.BetterDrawableOnTouchListener;
import com.xoidre.validarportugal.ui.BetterEditText;
import com.xoidre.validarportugal.util.*;

public class MainActivity extends Activity implements View.OnClickListener, TextWatcher {

    private static final String STATE_NUMBER = "state_number";
    private boolean mItemFocusedValid = false;
    private BetterEditText mEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_NUMBER)) {
            // Restore value of members from saved state
            mEditText.setText(savedInstanceState.getString(STATE_NUMBER));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        String number = Functions.getCleanText(mEditText.getText().toString());
        if (Functions.isDocumentNumberReady(number)) {
            savedInstanceState.putString(STATE_NUMBER, number);
        }
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private void init() {
        mEditText = (BetterEditText) findViewById(R.id.etDocNumber);
        findViewById(R.id.btnValidateCc).setOnClickListener(this);
        findViewById(R.id.btnValidateBi).setOnClickListener(this);
        findViewById(R.id.btnValidateNif).setOnClickListener(this);
        findViewById(R.id.btnValidateNiss).setOnClickListener(this);
        mEditText.addTextChangedListener(this);
        mEditText.setOnTouchListener(new BetterDrawableOnTouchListener(mEditText) {
            @Override
            public boolean OnDrawableClickListener(final MotionEvent event) {
                Functions.setKeyboardClose(MainActivity.this, mEditText);
                mEditText.setText("");
                clearAllElements();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        Functions.setKeyboardClose(MainActivity.this, view);
        switch (view.getId()) {

            case R.id.btnValidateBi:
                validateNumber(view, DocumentType.BI);
                break;

            case R.id.btnValidateCc:
                validateNumber(view, DocumentType.CC);
                break;

            case R.id.btnValidateNiss:
                validateNumber(view, DocumentType.NISS);
                break;

            case R.id.btnValidateNif:
                validateNumber(view, DocumentType.NIF);
                break;

            default:
        }
    }

    private void validateNumber(View view, DocumentType documentType) {
        String number = Functions.getCleanText(mEditText.getText().toString());
        if (!Functions.isDocumentNumberReady(number)) {
            setErrorMessage(getString(R.string.error_doc_invalid));
            return;
        }

        AppMsg appMsg = null;
        AppMsg.cancelAll(this);
        AppMsg.Style style = new AppMsg.Style(AppMsg.LENGTH_SHORT, R.color.green_stuff);
        String errorMessage = null;
        switch (documentType) {
            case BI:
                if (ValidatorHelper.isNifOrBiValid(number)) {
                    appMsg = AppMsg.makeText(MainActivity.this, getString(R.string.valid_bi), style);
                } else {
                    errorMessage = getString(R.string.error_bi);
                }
                break;
            case NIF:
                if (ValidatorHelper.isNifOrBiValid(number)) {
                    appMsg = AppMsg.makeText(MainActivity.this, getString(R.string.valid_nif), style);
                } else {
                    errorMessage = getString(R.string.error_nif);
                }
                break;
            case CC:
                if (ValidatorHelper.isCcValid(number)) {
                    appMsg = AppMsg.makeText(MainActivity.this, getString(R.string.valid_cc), style);
                } else {
                    errorMessage = getString(R.string.error_cc);
                }
                break;
            case NISS:
                if (ValidatorHelper.isNissValid(number)) {
                    appMsg = AppMsg.makeText(MainActivity.this, getString(R.string.valid_niss), style);
                } else {
                    errorMessage = getString(R.string.error_niss);
                }
                break;
            default:
        }

        if (appMsg != null) {
            appMsg.getView().setOnClickListener(new CancelAppMsg(appMsg));
            appMsg.setLayoutGravity(Gravity.BOTTOM);
            appMsg.show();
        }

        invalidateViews((BetterButton) view, errorMessage);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if ((mEditText != null && mEditText.getError() != null) || mItemFocusedValid) {
            //if error OR green box
            clearAllElements();
            mItemFocusedValid = false;
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void invalidateViews(BetterButton button, String errorMessage) {
        if (errorMessage == null) {
            Log.d(this, "[VALID] " + button.getText().toString());
            changeEditTextBackground(R.drawable.edit_text_green);
            mItemFocusedValid = true;
            mEditText.setError(null);

        } else {
            Log.e(this, "[ERROR] " + button.getText().toString());
            Log.e(this, "[ERROR] " + errorMessage);
            mItemFocusedValid = false;
            button.setTextColor(getResources().getColor(android.R.color.white));
            setErrorMessage(errorMessage);
        }
    }

    private void setErrorMessage(String message) {
        Drawable icClearError = getResources().getDrawable(R.drawable.ic_clear_error);
        icClearError.setBounds(0, 0, icClearError.getIntrinsicWidth(), icClearError.getIntrinsicHeight());
        mEditText.setError(message, icClearError);
        mEditText.requestFocus();
        changeEditTextBackground(R.drawable.edit_text_red);
    }

    private void clearAllElements() {
        AppMsg.cancelAll(MainActivity.this);
        mEditText.setError(null);
        ((BetterButton) findViewById(R.id.btnValidateCc)).setTextColor(getResources().getColor(R.color.button_text_color_selector));
        ((BetterButton) findViewById(R.id.btnValidateBi)).setTextColor(getResources().getColor(R.color.button_text_color_selector));
        ((BetterButton) findViewById(R.id.btnValidateNif)).setTextColor(getResources().getColor(R.color.button_text_color_selector));
        ((BetterButton) findViewById(R.id.btnValidateNiss)).setTextColor(getResources().getColor(R.color.button_text_color_selector));
        changeEditTextBackground(R.drawable.edit_text_normal);
    }

    /**
     * Changes view's backgroundResource while maintaining the original padding
     *
     * @param backgroundResourceId
     */
    private void changeEditTextBackground(int backgroundResourceId) {
        final int paddingTop = mEditText.getPaddingTop();
        final int paddingBottom = mEditText.getPaddingBottom();
        final int paddingLeft = mEditText.getPaddingLeft();
        final int paddingRight = mEditText.getPaddingRight();
        mEditText.setBackgroundResource(backgroundResourceId);
        mEditText.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
