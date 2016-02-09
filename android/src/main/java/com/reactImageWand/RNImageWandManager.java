package com.reactImageWand;

import android.graphics.Color;
<<<<<<< HEAD
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.common.MapBuilder;
<<<<<<< HEAD
<<<<<<< HEAD
import com.facebook.react.uimanager.annotations.ReactProp;
=======
import com.facebook.react.uimanager.ReactProp;
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
import com.facebook.react.uimanager.ReactProp;
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.views.image.ImageResizeMode;

import java.util.Map;

import javax.annotation.Nullable;

public class RNImageWandManager extends SimpleViewManager<RNImageWand> {

    public static final String REACT_CLASS = "RCTImageWand";
<<<<<<< HEAD
<<<<<<< HEAD
    private RNImageWand mImageWand;
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    private @Nullable
    AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private final @Nullable Object mCallerContext;

    public RNImageWandManager(
            AbstractDraweeControllerBuilder draweeControllerBuilder,
            Object callerContext) {
<<<<<<< HEAD
<<<<<<< HEAD
        Log.d("abc", "4567");
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
        mDraweeControllerBuilder = draweeControllerBuilder;
        mCallerContext = callerContext;
    }

    public RNImageWandManager() {
        // Lazily initialize as FrescoModule have not been initialized yet
<<<<<<< HEAD
<<<<<<< HEAD
        Log.d("abc", "456");
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
        mDraweeControllerBuilder = null;
        mCallerContext = null;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
<<<<<<< HEAD
<<<<<<< HEAD
        Log.d("abc", "45678");
        if (mDraweeControllerBuilder == null) {
            mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        Log.d("abc","drawee builder success");
=======
        if (mDraweeControllerBuilder == null) {
            mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
        if (mDraweeControllerBuilder == null) {
            mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
        return mDraweeControllerBuilder;
    }

    public Object getCallerContext() {
        return mCallerContext;
    }

    @Override
    public RNImageWand createViewInstance(ThemedReactContext context) {
<<<<<<< HEAD
<<<<<<< HEAD
        Log.d("abc","creating");
        mImageWand = new RNImageWand(
                context,
                getDraweeControllerBuilder(),
                getCallerContext());
//        mImageWand.setSource("http://facebook.github.io/react/img/logo_og.png");
        return mImageWand;
=======
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
        return new RNImageWand(
                context,
                getDraweeControllerBuilder(),
                getCallerContext());
<<<<<<< HEAD
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
    }

    // In JS this is Image.props.source.uri
    @ReactProp(name = "src")
    public void setSource(RNImageWand view, @Nullable String source) {
<<<<<<< HEAD
<<<<<<< HEAD
        Log.d("abc", source);
        view.setSource(source);
    }

    @ReactProp(name = "blur")
    public void setBlur(RNImageWand view, @Nullable Integer blur) {
        view.setBlur(blur);
    }

=======
        view.setSource(source);
    }

>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
=======
        view.setSource(source);
    }

>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
    // In JS this is Image.props.loadingIndicatorSource.uri
    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(RNImageWand view, @Nullable String source) {
        view.setLoadingIndicatorSource(source);
    }

    @ReactProp(name = "borderColor", customType = "Color")
    public void setBorderColor(RNImageWand view, @Nullable Integer borderColor) {
        if (borderColor == null) {
            view.setBorderColor(Color.TRANSPARENT);
        } else {
            view.setBorderColor(borderColor);
        }
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(RNImageWand view, float borderWidth) {
        view.setBorderWidth(borderWidth);
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(RNImageWand view, float borderRadius) {
        view.setBorderRadius(borderRadius);
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(RNImageWand view, @Nullable String resizeMode) {
        view.setScaleType(ImageResizeMode.toScaleType(resizeMode));
    }

    @ReactProp(name = "tintColor", customType = "Color")
    public void setTintColor(RNImageWand view, @Nullable Integer tintColor) {
        if (tintColor == null) {
            view.clearColorFilter();
        } else {
            view.setColorFilter(tintColor);
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(RNImageWand view, boolean enabled) {
        view.setProgressiveRenderingEnabled(enabled);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(RNImageWand view, int durationMs) {
        view.setFadeDuration(durationMs);
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(RNImageWand view, boolean shouldNotifyLoadEvents) {
        view.setShouldNotifyLoadEvents(shouldNotifyLoadEvents);
    }

    @Override
    public @Nullable Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                ImageLoadEvent.eventNameForType(ImageLoadEvent.ON_LOAD_START),
                MapBuilder.of("registrationName", "onLoadStart"),
                ImageLoadEvent.eventNameForType(ImageLoadEvent.ON_LOAD),
                MapBuilder.of("registrationName", "onLoad"),
                ImageLoadEvent.eventNameForType(ImageLoadEvent.ON_LOAD_END),
                MapBuilder.of("registrationName", "onLoadEnd")
        );
    }

    @Override
    protected void onAfterUpdateTransaction(RNImageWand view) {
        super.onAfterUpdateTransaction(view);
        view.maybeUpdateView();
    }


}
