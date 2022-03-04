package com.midsummer.currencylistdemo.internal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.midsummer.currencylistdemo.BuildConfig

/**
 * Created by nienle on 04,March,2022
 * Midsummer.
 * Ping me at nienbkict@gmail.com
 * Happy coding ^_^
 */
class LogHelper {
    companion object {
        /*
     * Used to identify the source of a log message. It usually identifies the
     * class or activity where the log call occurs
     */
        var LOGTAG = "MIDSUMMERTAG"

        /*
         * Is sent a VERBOSE log message. Set true if you want to show log to debug
         * application, else set true
         */
        var LOGV = true

        /*
         * Is sent a DEBUG log message. Set true if you want to show log to debug
         * application, else set true
         */
        var LogD = true

        /*
         * Is sent a ERROR log message. Set true if you want to show log to debug
         * application, else set true
         */
        var LogE = true

        /*
         * Is sent a INFO log message. Set true if you want to show log to debug
         * application, else set true
         */
        var LOGI = true

        /*
         * Is sent a WARN log message. Set true if you want to show log to debug
         * application, else set true
         */
        var LOGW = true


        /************
         * WTHLOG
         */
        val WTHLOG = "ohgodplease"
        val WTH = true

        /**
         * Send a VERBOSE log message.
         *
         * @param log : The message you would like logged.
         */
        fun v(log: String) {
            if (BuildConfig.DEBUG && LOGV) {
                Log.v(LOGTAG, log)
            }
        }

        /**
         * Send a VERBOSE log message and log the exception.
         *
         * @param log : The message you would like logged.
         * @param tr  : An exception to log
         */
        fun v(log: String, tr: Throwable) {
            if (BuildConfig.DEBUG && LOGV) {
                Log.v(LOGTAG, log, tr)
            }
        }

        /**
         * Send a DEBUG log message.
         *
         * @param log The message you would like logged.
         */
        fun d(log: String) {
            if (BuildConfig.DEBUG && LogD) {
                Log.d(LOGTAG, log)
            }
        }

        /**
         * Send a DEBUG log message and log the exception.
         *
         * @param log       The message you would like logged.
         * @param throwable An exception to log
         */
        fun d(log: String, throwable: Throwable) {
            if (BuildConfig.DEBUG && LogD) {
                Log.d(LOGTAG, log, throwable)
            }
        }

        /**
         * Send an ERROR log message.
         *
         * @param log The message you would like logged.
         */
        fun e(log: String) {
            if (BuildConfig.DEBUG && LogE) {
                Log.e(LOGTAG, log)
            }
        }

        fun e(e: Throwable?) {
            if (BuildConfig.DEBUG && LogE && e != null) {
                Log.e(LOGTAG, Log.getStackTraceString(e))
                e.printStackTrace()
            }
        }

        /**
         * Send a ERROR log message and log the exception.
         *
         * @param log The message you would like logged.
         * @param tr  An exception to log
         */
        fun e(log: String, tr: Throwable) {
            if (BuildConfig.DEBUG && LogE) {
                Log.e(LOGTAG, log, tr)
            }
        }

        /**
         * Send an INFO log message.
         *
         * @param log The message you would like logged.
         */
        fun i(log: String) {
            if (BuildConfig.DEBUG && LOGI) {
                Log.i(LOGTAG, log)
            }
        }

        /**
         * Send a INFO log message and log the exception.
         *
         * @param log The message you would like logged.
         * @param tr  An exception to log
         */
        fun i(log: String, tr: Throwable) {
            if (BuildConfig.DEBUG && LOGI) {
                Log.i(LOGTAG, log, tr)
            }
        }

        /**
         * Send a WARN log message
         *
         * @param log The message you would like logged.
         */
        fun w(log: String) {
            if (BuildConfig.DEBUG && LOGW) {
                Log.w(LOGTAG, log)
            }
        }

        /**
         * Send a WARN log message and log the exception.
         *
         * @param log The message you would like logged.
         * @param tr  An exception to log
         */
        fun w(log: String, tr: Throwable) {
            if (BuildConfig.DEBUG && LOGW) {
                Log.w(LOGTAG, log, tr)
            }
        }

        fun wth(log: String) {
            if (BuildConfig.DEBUG && WTH) {
                Log.d(WTHLOG, log)
            }
        }

        @JvmOverloads
        fun dumpIntent(i: Intent, className: String = "UNKNOWN") {
            val bundle = i.extras
            if (bundle != null) {
                val keys = bundle.keySet()

                val stringBuilder = StringBuilder()
                stringBuilder.append("IntentDump: $className \n\r")
                stringBuilder.append("-------------------------------------------------------------\n\r")

                for (key in keys) {
                    stringBuilder.append(key).append("=").append(bundle.get(key)).append("\n\r")
                }

                stringBuilder.append("-------------------------------------------------------------\n\r")
                d(stringBuilder.toString())
            }
        }

        @JvmOverloads
        fun dumpIntent(bundle: Bundle?, className: String = "UNKNOWN") {

            if (bundle != null) {
                val keys = bundle.keySet()

                val stringBuilder = StringBuilder()
                stringBuilder.append("IntentDump: $className \n\r")
                stringBuilder.append("-------------------------------------------------------------\n\r")

                for (key in keys) {
                    stringBuilder.append(key).append("=").append(bundle.get(key)).append("\n\r")
                }

                stringBuilder.append("-------------------------------------------------------------\n\r")
                d(stringBuilder.toString())
            }
        }
    }
}