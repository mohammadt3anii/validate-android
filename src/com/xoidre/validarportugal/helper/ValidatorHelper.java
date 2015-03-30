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

package com.xoidre.validarportugal.helper;


import android.text.TextUtils;
import android.util.Log;

public final class ValidatorHelper {

    private static final String TAG = ValidatorHelper.class.getSimpleName();

    private ValidatorHelper() {
    }

    /**
     * Required document number length: 12
     *
     * @param docNumber
     * @return
     */
    public static boolean isCcValid(String docNumber) {
        if (docNumber.length() != 12) {
            Log.e(TAG, "Tamanho inválido para número de documento.");
            return false;
        }

        String num = docNumber.toUpperCase();
        int sum = 0;
        boolean secondDigit = false;
        for (int i = num.length() - 1; i >= 0; --i) {
            Integer number = Functions.getNumberFromChar(num.charAt(i));
            if (number == null) {
                Log.e(TAG, "Valor inválido no número de documento.");
                return false;
            }
            if (secondDigit) {
                number *= 2;
                if (number > 9) {
                    number -= 9;
                }
            }
            sum += number;
            secondDigit = !secondDigit;
        }
        return (sum % 10) == 0;
    }


    /**
     * Required document number length: 9 max.
     * <p/>
     * Check-digit of Portuguese Documents: BI and NIF.
     * http://www.portugal-a-programar.pt/topic/9968-php-validar-bi-e-nif/page__st__20#entry356740
     * <p/>
     * 1) um BI pode ter menos de 7 caracteres. deve ser sempre feito o pad de 0 até o tamanho do BI ser 8
     * 2) o checkdigit nao pode ser incluido na fórmula de cálculo “bi[i] * (9-i)”
     * 3) o valor do cálculo (checkdigit) se for maior ou igual a 10 deve ser reposto a 0
     * 4) um bi é válido se o checkdigit for igual ao valor calculado
     *
     * @param docNumber
     * @return
     * @seealso http://maracujah.net/software/nif
     */
    public static boolean isNifOrBiValid(String docNumber) {
        if (docNumber.length() > 9 || !TextUtils.isDigitsOnly(docNumber)) {
            return false;
        }
        int number;
        int sum = 0;
        int remainder;
        int check;
        int nifCheck = 0;

        try {
            for (int i = 0; i < 8; i++) {
                number = Integer.valueOf(docNumber.substring(i, i + 1));
                sum += number * (9 - i);
            }
            nifCheck = Character.getNumericValue(docNumber.charAt(8));

        } catch (NumberFormatException e) {
            Log.e(TAG, "NumberFormatException", e);

        } catch (Exception e) {
            Log.e(TAG, "Exception", e);
        }

        remainder = sum % 11;

        // calc check digit
        if (remainder == 0 || remainder == 1) {
            check = 0;
        } else {
            check = 11 - remainder;
        }
        // return result
        return check == nifCheck;
    }

    /**
     * Required document number length: 11 max.
     * <p/>
     * Check-digit of Portuguese Document: NISS
     * source: http://maracujah.net/files/software/NISS.pdf
     *
     * @param docNumber
     * @return
     */
    public static boolean isNissValid(String docNumber) {
        if (docNumber.length() > 11 || !TextUtils.isDigitsOnly(docNumber)) {
            return false;
        }
        int[] factors = {29, 23, 19, 17, 13, 11, 7, 5, 3, 2};
        int digit;
        int sum = 0;
        try {
            for (int i = 0; i < docNumber.length(); i++) {
                digit = Integer.valueOf(docNumber.substring(i, i + 1));
                sum += (digit * factors[i]);

                if (i == (docNumber.length() - 2)) {
                    int checkDigit = Integer.valueOf(docNumber.substring(i + 1, i + 2));
                    int rest = sum % (i + 1);
                    int checkDigitCalc = 9 - rest;
                    return checkDigit == checkDigitCalc;
                }
            }
            Log.e(TAG, "[NISS] reach the end of the string");
            return false;

        } catch (NumberFormatException e) {
            Log.e(TAG, "[NISS] NumberFormatException", e);
            return false;
        }
    }
}
