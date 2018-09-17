package com.hendalqett.greetingssample;

import android.content.Context;
import android.text.TextUtils;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;


/**
 * Created by hend on 9/15/18.
 */
public class Greeting {
    private Context context;
    private int textState;
    private int length;
    private String userName;
    private boolean isContainsExclamation;
    private String value;

    private Greeting(Context context, int textState, int length, @NotNull String userName, boolean isContainsExclamation) {
        this.context = context;
        this.textState = textState;
        this.length = length;
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

        if (textState == 2) //Camel Case
        {
            greetingExpression.append(toTitleCase(greeting));
        } else {
            greetingExpression.append(greeting);
        }

        if (!TextUtils.isEmpty(userName)) {

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

    public static class Builder {

        private Context context;
        private int textState = 1; // normal or camel case
        private int length = 1; // Short, long , medium
        private String userName = "";
        private boolean isContainsExclamation = false;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTextState(int textState) {
            this.textState = textState;
            return this;
        }

        public Builder setLength(int length) {
            this.length = length;
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
            return new Greeting(context, textState, length, userName, isContainsExclamation);
        }

    }


}
