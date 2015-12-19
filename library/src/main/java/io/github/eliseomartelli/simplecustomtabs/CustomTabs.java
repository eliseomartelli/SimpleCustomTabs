package io.github.eliseomartelli.simplecustomtabs;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;

import io.github.eliseomartelli.simplecustomtabs.helpers.ChromePackageHelper;

/**
 * Created by eliseomartelli on 19/12/15.
 */
public class CustomTabs {

    static CustomTabsClient mCustomTabsClient;
    static CustomTabsSession mCustomTabsSession;

    static String packageNameToUse;

    /**
     * Method used to set the context
     * @param context Context of an activity
     */
    public static Operable with(Context context){
        return new Operable(context);
    }

    public static class Operable {
        Style style;
        Context context;

        private Operable(Context context){
            this.context = context;
        }

        /**
         * Method used to warm the custom tabs
         */
        public Warmer warm(){
            return new Warmer(context).warm();
        }

        /**
         * Method used to set the style of the custom tabs
         * @param style The style you want to set
         */
        public Operable setStyle(Style style){
            this.style = style;
            return this;
        }

        /**
         * Method used to open an Uri
         * @param uri The Uri you want to open
         * @param warmer The Warmer you created first
         * @param activity The current activity
         */
        public Operable openUrl(Uri uri, Warmer warmer, Activity activity){
            Context context = warmer.context;

            if (packageNameToUse != null) {

                if (style == null) style = new Style();

                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder(mCustomTabsSession);

                if (style.toolbarColor != 0) {
                    builder.setToolbarColor(ContextCompat.getColor(context, style.toolbarColor));
                }

                builder.setShowTitle(style.showTitle);

                if (style.startEnterAnimation != 0 && style.startCloseAnimation != 0) {
                    builder.setStartAnimations
                            (context, style.startEnterAnimation, style.startCloseAnimation);
                }

                if (style.exitEnterAnimation != 0 && style.exitCloseAnimation != 0) {
                    builder.setExitAnimations
                            (context, style.exitEnterAnimation, style.exitCloseAnimation);
                }
                builder.build().launchUrl(activity, uri);
            } else {
               context.startActivity(new Intent(Intent.ACTION_VIEW).setData(uri));

            }
            return this;
        }

        /**
         * Method used to open an Uri
         * @param url The Url you want to open
         * @param warmer The Warmer you created first
         * @param activity The current activity
         */
        public Operable openUrl(String url, Warmer warmer, Activity activity){
            return openUrl(Uri.parse(url), warmer, activity);
        }

    }

    /**
     * This class defines the Style you want to apply to the Custom Tab
     */
    public static class Style {
        private int toolbarColor;
        private int startEnterAnimation;
        private int exitEnterAnimation;
        private int startCloseAnimation;
        private int exitCloseAnimation;
        private boolean showTitle = false;

        public Style(){

        }

        /**
         * Method used to show (or not) the title
         * @param showTitle True if you want to show, false if not
         */
        public Style setShowTitle(boolean showTitle) {
            this.showTitle = showTitle;
            return this;
        }

        /**
         * Method used to set the Toolbar color of the custom tab
         * @param toolbarColor Color res id you want to set
         */
        public Style setToolbarColor(@ColorRes int toolbarColor) {
            this.toolbarColor = toolbarColor;
            return this;
        }

        /**
         * Method used to set the Animations when the Custom Tab opens
         * @param startEnterAnimation The Enter Animation of the custom tab
         * @param startCloseAnimation The Close Animation of the new activity
         */
        public Style setStartAnimation(@AnimRes int startEnterAnimation, @AnimRes int
                startCloseAnimation){
            this.startEnterAnimation = startEnterAnimation;
            this.startCloseAnimation = startCloseAnimation;
            return this;
        }

        /**
         * Method used to set the Animations when the Custom Tab opens
         * @param exitEnterAnimation The Enter Animation of the new activity
         * @param exitCloseAnimation The Close Animation of the custom tab
         */
        public Style setExitAnimation(@AnimRes int exitEnterAnimation, @AnimRes int
                exitCloseAnimation){
            this.exitEnterAnimation = exitEnterAnimation;
            this.exitCloseAnimation = exitCloseAnimation;
            return this;
        }

    }

    public static class Warmer {
        Context context;
        public Warmer(Context context) {
            this.context = context;
        }

        private Warmer warm(){
            CustomTabsServiceConnection mCustomTabServiceConnection = new CustomTabsServiceConnection() {
                @Override
                public void onCustomTabsServiceConnected(ComponentName componentName,
                                                         CustomTabsClient customTabsClient) {
                    mCustomTabsClient = customTabsClient;
                    mCustomTabsClient.warmup(0);
                    mCustomTabsSession = mCustomTabsClient.newSession(null);
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    mCustomTabsClient = null;
                }
            };

            packageNameToUse = ChromePackageHelper.getPackageNameToUse(context);

            if (packageNameToUse != null){
                CustomTabsClient.bindCustomTabsService(context, packageNameToUse,
                        mCustomTabServiceConnection);
            }
            return this;
        }
    }
}
