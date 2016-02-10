# react-native-imagewand
React native image wand view for android. Enabling you to do instagram like effects to images

### Installation

```bash
npm i --save react-native-imagewand
```

### Add it to your android project

* In `android/settings.gradle`

```gradle
...
include ':react-native-imagewand'
project(':react-native-imagewand').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-imagewand/android')
```

* In `android/app/build.gradle`

```
dependencies {
  ...
  ...
  compile project(':react-native-imagewand')
}
```

* register module (in MainActivity.java)

```java
import com.reactImageWand.*;  // <--- import

public class MainActivity extends ReactActivity {
  ......
  @Override
  protected List<ReactPackage> getPackages() {
    return Arrays.<ReactPackage>asList(
      new MainReactPackage(),
      new RNImageWandPackage()     // <------ add here
    );
  }
  ......
}
```

## Example
```javascript
var ImageWand = require('react-native-imagewand');

var Example = React.createClass({

  render: function() {
    return (
      <ImageWand
        src={'...'}
        blur={4}
      />
    );
  },

});
```
