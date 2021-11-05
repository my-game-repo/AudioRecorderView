package com.tougee.demo;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.LocaleList;

import java.util.Locale;

public class LanguageAwareContext extends ContextWrapper {
    public LanguageAwareContext(Context base) {
        super(base);
    }

    public static LanguageAwareContext newLanguageAwareContext(String targetLanguage, Context context) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        Locale newLocale = new Locale(targetLanguage);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            configuration.setLocale(newLocale);
            LocaleList.setDefault(new LocaleList(newLocale));

            context = context.createConfigurationContext(configuration);
        } else{
            configuration.setLocale(newLocale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }

        return new LanguageAwareContext(context);
    }
}
