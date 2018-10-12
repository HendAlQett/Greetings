package com.hendalqett.greetings;

import android.content.Context;
import android.support.annotation.IntDef;
import android.text.TextUtils;

import java.lang.annotation.Retention;
import java.util.Calendar;

import static java.lang.annotation.RetentionPolicy.SOURCE;


/**
 * Created by hend on 9/15/18.
 */
public class Greeting {
    public static final int TEXT_NORMAL = 1;
    public static final int TEXT_CAMEL_CASE = 2;
    private Context context;
    private @TextMode
    int textState;
    private String userName;
    private boolean isContainsExclamation;
    private String value;

    private Greeting(Context context, @TextMode int textState, String userName, boolean isContainsExclamation) {
        this.context = context;
        this.textState = textState;
        this.userName = userName;
        this.isContainsExclamation = isContainsExclamation;
    }

    public String getCurrentGreeting() {

        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        StringBuilder greetingExpression = new StringBuilder();
        String greeting = "";

        if (hour >= 5 && hour <= 11) {
            greeting = context.getString(R.string.good_morning);

        } else if (hour >= 12 && hour <= 16) {
            greeting = context.getString(R.string.good_afternoon);

        } else if (hour >= 17 && hour <= 23) {
            greeting = context.getString(R.string.good_evening);

        } else if (hour >= 0 && hour <= 4) {
            greeting = context.getString(R.string.good_evening);
        }

        if (textState == TEXT_CAMEL_CASE) //Camel Case
        {
            greetingExpression.append(toTitleCase(greeting));
        } else {
            greetingExpression.append(greeting);
        }

        if (!TextUtils.isEmpty(userName)) {
            //TODO: support localization here
            greetingExpression.append(" ,").append(userName);
        }

        if (isContainsExclamation) {
            greetingExpression.append("!");
        }

        return greetingExpression.toString();
    }

    private String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : input.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }

        return titleCase.toString();
    }

    @Retention(SOURCE)
    @IntDef({TEXT_NORMAL, TEXT_CAMEL_CASE})
    private @interface TextMode {
    }

    public static class Builder {

        private Context context;
        private @TextMode
        int textState = TEXT_NORMAL; // normal or camel case
        private String userName = "";
        private boolean isContainsExclamation = false;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTextState(@TextMode int textState) {
            this.textState = textState;
            return this;
        }


        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setContainsExclamation(boolean containsExclamation) {
            isContainsExclamation = containsExclamation;
            return this;
        }

        public Greeting build() {
            return new Greeting(context, textState, userName, isContainsExclamation);
        }

    }


}
