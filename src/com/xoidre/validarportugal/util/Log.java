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

package com.xoidre.validarportugal.util;

import com.xoidre.validarportugal.BuildConfig;

public final class Log {

	private Log() {
	}

	public static final boolean DEBUG = BuildConfig.DEBUG;

	/**
	 * Automatically detects method name
	 */
	public static void e(Object object, String text) {
		if (DEBUG) {
			StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
			android.util.Log.e(object.getClass().getSimpleName(), "▒" + trace[1].getMethodName() + "▒ " + text);
		}
	}

	public static void e(Object object, String text, Throwable e) {
		StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
		android.util.Log.e(object.getClass().getSimpleName(), "▒" + trace[1].getMethodName() + "▒ " + text);
		e.printStackTrace();
	}

	public static void e(String tag, String text) {
		if (DEBUG) {
			android.util.Log.e(tag, text);
		}
	}

	public static void e(String tag, Throwable e) {
		if (DEBUG) {
			android.util.Log.e(tag, e.toString());
			e.printStackTrace();
		}
	}

	public static void e(String tag, String text, Throwable e) {
		if (DEBUG) {
			android.util.Log.e(tag, text, e);
			e.printStackTrace();
		}
	}

	public static void w(Object object, String text) {
		if (DEBUG) {
			StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
			android.util.Log.w(object.getClass().getSimpleName(), "▒" + trace[1].getMethodName() + "▒ " + text);
		}
	}

	public static void w(String tag, String text) {
		if (DEBUG) {
			android.util.Log.w(tag, text);
		}
	}

	public static void w(String tag, String msg, Throwable throwable) {
		if (DEBUG) {
			android.util.Log.w(tag, msg, throwable);
		}
	}

	public static void d(Object object, String text) {
		if (DEBUG) {
			Throwable stack = new Throwable().fillInStackTrace();
			StackTraceElement[] trace = stack.getStackTrace();
			android.util.Log.d(object.getClass().getSimpleName(), "▒" + trace[1].getMethodName() + "▒ " + text);
		}
	}

	public static void d(String tag, String text) {
		if (DEBUG) {
			android.util.Log.d(tag, text);
		}
	}

    public static void methodEntry(Object object) {
        if (DEBUG) {
            StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
            android.util.Log.i(object.getClass().getSimpleName(),trace[1].getMethodName() + " - called");
        }
    }

	public static void i(Object object, String text) {
		if (DEBUG) {
			StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
			android.util.Log.i(object.getClass().getSimpleName(), "▒" + trace[1].getMethodName() + "▒ " + text);
		}
	}

	public static void i(String tag, String text) {
		if (DEBUG) {
			android.util.Log.i(tag, text);
		}
	}

	public static void v(Object object, String text) {
		if (DEBUG) {
			StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
			android.util.Log.v(object.getClass().getSimpleName(), "▒" + trace[1].getMethodName() + "▒ " + text);
		}
	}

	public static void v(String tag, String text) {
		if (DEBUG) {
			android.util.Log.v(tag, text);
		}
	}

	public static void printStackTrace(String tag, Throwable t) {
		if (DEBUG) {
			android.util.Log.e(tag, "---------------------------");
			t.printStackTrace();
		}
	}
}
