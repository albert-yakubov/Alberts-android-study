package com.stepasha.sharedprefsfordao

import android.app.Application
import timber.log.Timber


// TODO: 3. Extend Timber to include class, method, line numbers!

class MyDebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String? {

        return String.format(

            "[C:%s] [M:%s] [L:%s]",

            super.createStackElementTag(element),

            element.methodName,

            element.lineNumber

        )

    }

}



class App : Application() {



    // TODO: 4. Provide an Application-wide Shared Preferences

    companion object {

        var prefs: Prefs? = null

    }



    override fun onCreate() {

        super.onCreate()



        prefs = Prefs(applicationContext)



        // TODO: 2. Configure Timber logging

        // "Timber" Library

        if (BuildConfig.DEBUG) {

            Timber.plant(MyDebugTree())

        }

    }

}