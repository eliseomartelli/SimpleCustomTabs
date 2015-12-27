# SimpleCustomTabs
A library made to use custom tabs without going crazy!
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleCustomTabs-orange.svg?style=flat)](http://android-arsenal.com/details/1/2950)

To use this library in your project add the dependency to your build.gradle file.

```
dependencies {
    compile 'io.github.eliseomartelli:simple-custom-tabs:1.1.0'
}
```

You can see an example use of this library opening "example" module.

# Bare Minimum 

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Warm up section
        CustomTabs.with(getApplicationContext()).warm();
        
        
        //Load section
        CustomTabs.with(getApplicationContext()).openUrl("http://google.com", this);
    }
```

# Styling 

Choose what to style!

```java
    CustomTabs.Style style = new CustomTabs.Style(getApplicationContext());
    style.setActionButton(icon, description, pendingIntent, tint);
    style.setCloseButton(closeIcon);
    style.setShowTitle(showTitle);
    style.setStartAnimation(android.R.anim.fade_in, android.R.anim.fade_out);
    style.setExitAnimation(android.R.anim.fade_in, android.R.anim.fade_out);
    style.setToolbarColor(R.color.colorPrimary);
    style.addMenuItem(itemDescription, itemPendingIntent);

    CustomTabs
        .with(getApplicationContext())
        .setStyle(style)
        .openUrl("http://google.com", this);
```
Do you want to write less? No Problem!

```java
    CustomTabs.with(getApplicationContext())
        .setStyle(new CustomTabs.Style(getApplicationContext())
                .setShowTitle(true)
                .setStartAnimation(R.anim.animation, R.anim.animation)
                .setExitAnimation(R.anim.animation, R.anim.animation)
                .setToolbarColor(R.color.toolbarColor))
        .openUrl("http://google.com", this);
```

# One More Thing
No... This Library is not from Apple...

Your user doesn't have Chrome Installed? No Problem!
The Url will be opened in his browser!

Hope you enjoy!
