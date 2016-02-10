// ImageView.js
var React = require('react-native');
var { requireNativeComponent, PropTypes } = React;

var ImageWand = React.createClass({
    propTypes: {
        src: PropTypes.string,
        blur: PropTypes.number,
        onImageInfo: PropTypes.func,
        borderRadius: PropTypes.number,
        resizeMode: PropTypes.oneOf(['cover', 'contain', 'stretch']),
        renderToHardwareTextureAndroid: PropTypes.bool,
        onLayout: PropTypes.bool,
        accessibilityLiveRegion: PropTypes.bool,
        accessibilityComponentType: PropTypes.string,
        shouldNotifyLoadEvents: PropTypes.bool,
        loadingIndicatorSrc: PropTypes.string,
        fadeDuration: PropTypes.number,
        progressiveRenderingEnabled: PropTypes.bool,
        importantForAccessibility: PropTypes.bool,
        accessibilityLabel: PropTypes.string,
        testID: PropTypes.string,
    },
    _onChange: function(event) {
        if (!this.props.onImageInfo) {
            return;
        }
        this.props.onImageInfo(event.nativeEvent);
    },
    render: function() {
        console.log('rendering');
        return <RCTImageWand {...this.props} onChange={this._onChange} />;
    }
})

var opts = {
    nativeOnly: {
        onChange: true
    }
}

var RCTImageWand = requireNativeComponent('RCTImageWand', ImageWand, opts);

module.exports = ImageWand;