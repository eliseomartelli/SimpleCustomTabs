# SimpleCustomTabs

A library made to use custom tabs without going crazy!

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleCustomTabs-orange.svg?style=flat)](http://android-arsenal.com/details/1/2950)

To use this library in your project add the dependency to your build.gradle file.

```gradle
dependencies {
    compile 'io.github.eliseomartelli:simple-custom-tabs:1.4.1'
}
```

You can see an **example** use of this library opening [*"example"*](https://github.com/eliseomartelli/SimpleCustomTabs/tree/master/example) module.

## Bare Minimum

```java

private CustomTabs.Warmer warmer;


@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Warm up section
    warmer = CustomTabs.with(getApplicationContext()).warm();


    //Load section
    CustomTabs.with(getApplicationContext()).openUrl("http://google.com", this);
}

@Override
protected void onDestroy() {
    super.onDestroy();
    warmer.unwarm();
}

```

## Styling

Choose what to style!

```java
CustomTabs.Style style = new CustomTabs.Style(getApplicationContext());
style.setActionButton(icon, description, pendingIntent, tint);
style.setCloseButton(closeIcon);
style.setShowTitle(showTitle);
style.setStartAnimation(android.R.anim.fade_in, android.R.anim.fade_out);
style.setExitAnimation(android.R.anim.fade_in, android.R.anim.fade_out);
style.setToolbarColor(R.color.colorPrimary);
style.setToolbarColorInt(color);
style.addMenuItem(itemDescription, itemPendingIntent);



CustomTabs
    .with(getApplicationContext())
    .setStyle(style)
    .openUrl("http://google.com", this);
```
Do you want to **write less**? No Problem!

```java
CustomTabs.with(getApplicationContext())
    .setStyle(new CustomTabs.Style(getApplicationContext())
            .setShowTitle(true)
            .setStartAnimation(R.anim.animation, R.anim.animation)
            .setExitAnimation(R.anim.animation, R.anim.animation)
            .setToolbarColor(R.color.toolbarColor))
    .openUrl("http://google.com", this);
```

## Fallback

Your user doesn't have Chrome Installed? No Problem!
The url will be opened in an installed browser without any edit to the code.

If you want a customized experience you can set a fallback activity by using:

```java
CustomTabs.with(getApplicationContext())
    .setFallBackActivity(ActivityToOpen.class)
    .openUrl("http://google.com", this);
```

The URI of the site will be placed in Intent's data.
To get the URI from the newly fired you have to get the data from Activity's intent.
To get the data from the Activity intent you can do like this:

```java
Uri uri = getIntent().getData();
```

You can see an example [here](https://github.com/eliseomartelli/SimpleCustomTabs/blob/master/example/src/main/java/io/github/eliseomartelli/myapplication/FallbackActivity.java).

## Screenshots

<img width="40%" src="https://github.com/eliseomartelli/SimpleCustomTabs/raw/master/assets/Screenshot2.jpg"/>
<img width="40%" src="https://github.com/eliseomartelli/SimpleCustomTabs/raw/master/assets/Screenshot1.jpg"/>

***

Hope you enjoy!
