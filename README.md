# Android GlowTextView Widget

[ ![Download](https://api.bintray.com/packages/riningan/AndroidMaven/GlowTextView/images/download.svg) ](https://bintray.com/riningan/AndroidMaven/GlowTextView/_latestVersion)

Library add glow effect to TextView.

DEMO
---

![demo_preview](./preview.gif)

USAGE
---

Using GlowTextView in your application
Add dependencies in build.gradle of your module

```groovy
	dependencies {
		compile 'com.riningan.widget:glowtextview:1.0'
	}
```

XML
-----

```xml
<com.riningan.glowtextview.GlowTextView
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:padding="32dp"
        app:glowColor="@color/colorAccent"
        app:glowRadius="20dp"
        android:text="Hello world!"
        android:textColor="@android:color/black"
        android:textSize="24sp" />
```
Properties:

* `app:glowRadius`              (dimension)    -> default 60f
* `app:glowColor`               (color)        -> default WHITE


JAVA
-----

```java
GlowTextView glowTextView = (GlowTextView) findViewById(R.id.glowTextView);
glowTextView.setGlowRadius(float glowRadius);
glowTextView.setGlowColor(@ColorInt int glowColor);
```

LICENCE
-----

  	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	   http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
