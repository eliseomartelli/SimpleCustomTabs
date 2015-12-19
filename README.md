# SimpleCustomTabs
Simple Custom Tabs is an open source Android Library made to use custom tabs without going crazy!

To use this library in your project add the dependency to your build.gradle file.

```
dependencies {
    compile 'io.github.eliseomartelli:simple-custom-tabs:1.0.0'
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

```java
    CustomTabs.with(getApplicationContext()).setStyle(new CustomTabs.Style()
                .setToolbarColor(R.color.color_id)
                .setShowTitle(true)
                .setStartAnimation(R.anim.anim_id, R.anim.anim_id)
                .setExitAnimation(R.anim.anim_id, R.anim.anim_id))
        .openUrl("http://google.com", this);
    
```

Hope you enjoy!
