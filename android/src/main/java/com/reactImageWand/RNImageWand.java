/**
 * Copyright (c) 2015-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package com.reactImageWand;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df

import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.image.ImageLoadEvent;
import com.facebook.react.views.image.ImageResizeMode;

import javax.annotation.Nullable;

/**
 * Wrapper class around Fresco's GenericDraweeView, enabling persisting props across multiple view
 * update and consistent processing of both static and network images.
 */
public class RNImageWand extends GenericDraweeView {

    private static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
<<<<<<< HEAD
    private int mBlur;
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df

    /*
     * Implementation note re rounded corners:
     *
     * Fresco's built-in rounded corners only work for 'cover' resize mode -
     * this is a limitation in Android itself. Fresco has a workaround for this, but
     * it requires knowing the background color.
     *
     * So for the other modes, we use a postprocessor.
     * Because the postprocessor uses a modified bitmap, that would just get cropped in
     * 'cover' mode, so we fall back to Fresco's normal implementation.
     */
    private static final Matrix sMatrix = new Matrix();
    private static final Matrix sInverse = new Matrix();

<<<<<<< HEAD
    private class redMeshPostprocessor extends BasePostprocessor {
        @Override
        public String getName() {
            return "redMeshPostprocessor";
        }

        @Override
        public void process(Bitmap bmp) {
            int radius = mBlur;
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            int[] pix = new int[w * h];
            bmp.getPixels(pix, 0, w, 0, 0, w, h);

            for(int r = radius; r >= 1; r /= 2) {
                for(int i = r; i < h - r; i++) {
                    for(int j = r; j < w - r; j++) {
                        int tl = pix[(i - r) * w + j - r];
                        int tr = pix[(i - r) * w + j + r];
                        int tc = pix[(i - r) * w + j];
                        int bl = pix[(i + r) * w + j - r];
                        int br = pix[(i + r) * w + j + r];
                        int bc = pix[(i + r) * w + j];
                        int cl = pix[i * w + j - r];
                        int cr = pix[i * w + j + r];

                        pix[(i * w) + j] = 0xFF000000 |
                                (((tl & 0xFF) + (tr & 0xFF) + (tc & 0xFF) + (bl & 0xFF) + (br & 0xFF) + (bc & 0xFF) + (cl & 0xFF) + (cr & 0xFF)) >> 3) & 0xFF |
                                (((tl & 0xFF00) + (tr & 0xFF00) + (tc & 0xFF00) + (bl & 0xFF00) + (br & 0xFF00) + (bc & 0xFF00) + (cl & 0xFF00) + (cr & 0xFF00)) >> 3) & 0xFF00 |
                                (((tl & 0xFF0000) + (tr & 0xFF0000) + (tc & 0xFF0000) + (bl & 0xFF0000) + (br & 0xFF0000) + (bc & 0xFF0000) + (cl & 0xFF0000) + (cr & 0xFF0000)) >> 3) & 0xFF0000;
                    }
                }
            }
            bmp.setPixels(pix, 0, w, 0, 0, w, h);
        }
    }


=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
    private class RoundedCornerPostprocessor extends BasePostprocessor {

        float getRadius(Bitmap source) {
            ScalingUtils.getTransform(
                    sMatrix,
                    new Rect(0, 0, source.getWidth(), source.getHeight()),
                    source.getWidth(),
                    source.getHeight(),
                    0.0f,
                    0.0f,
                    mScaleType);
            sMatrix.invert(sInverse);
            return sInverse.mapRadius(mBorderRadius);
        }

        @Override
        public void process(Bitmap output, Bitmap source) {
            output.setHasAlpha(true);
            if (mBorderRadius < 0.01f) {
                super.process(output, source);
                return;
            }
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
            Canvas canvas = new Canvas(output);
            float radius = getRadius(source);
            canvas.drawRoundRect(
                    new RectF(0, 0, source.getWidth(), source.getHeight()),
                    radius,
                    radius,
                    paint);
        }
    }

    private @Nullable Uri mUri;
    private @Nullable Drawable mLoadingImageDrawable;
    private int mBorderColor;
    private float mBorderWidth;
    private float mBorderRadius;
    private ScalingUtils.ScaleType mScaleType;
    private boolean mIsDirty;
    private boolean mIsLocalImage;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private final RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    private @Nullable ControllerListener mControllerListener;
    private @Nullable ControllerListener mControllerForTesting;
    private final @Nullable Object mCallerContext;
    private int mFadeDurationMs = -1;
    private boolean mProgressiveRenderingEnabled;

    // We can't specify rounding in XML, so have to do so here
    private static GenericDraweeHierarchy buildHierarchy(Context context) {
        return new GenericDraweeHierarchyBuilder(context.getResources())
                .setRoundingParams(RoundingParams.fromCornersRadius(0))
                .build();
    }

    public RNImageWand(
            Context context,
            AbstractDraweeControllerBuilder draweeControllerBuilder,
            @Nullable Object callerContext) {
        super(context, buildHierarchy(context));
        mScaleType = ImageResizeMode.defaultValue();
        mDraweeControllerBuilder = draweeControllerBuilder;
        mRoundedCornerPostprocessor = new RoundedCornerPostprocessor();
        mCallerContext = callerContext;
    }

    public void setShouldNotifyLoadEvents(boolean shouldNotify) {
        if (!shouldNotify) {
            mControllerListener = null;
        } else {
            final EventDispatcher mEventDispatcher = ((ReactContext) getContext()).
                    getNativeModule(UIManagerModule.class).getEventDispatcher();

            mControllerListener = new BaseControllerListener<ImageInfo>() {
                @Override
                public void onSubmit(String id, Object callerContext) {
                    mEventDispatcher.dispatchEvent(
                            new ImageLoadEvent(getId(), SystemClock.uptimeMillis(), ImageLoadEvent.ON_LOAD_START)
                    );
                }

                @Override
                public void onFinalImageSet(
                        String id,
                        @Nullable final ImageInfo imageInfo,
                        @Nullable Animatable animatable) {
                    if (imageInfo != null) {
<<<<<<< HEAD
                        Log.d("abc",String.valueOf(imageInfo.getWidth()));
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
                        mEventDispatcher.dispatchEvent(
                                new ImageLoadEvent(getId(), SystemClock.uptimeMillis(), ImageLoadEvent.ON_LOAD_END)
                        );
                        mEventDispatcher.dispatchEvent(
                                new ImageLoadEvent(getId(), SystemClock.uptimeMillis(), ImageLoadEvent.ON_LOAD)
                        );
                    }
                }

                @Override
                public void onFailure(String id, Throwable throwable) {
                    mEventDispatcher.dispatchEvent(
                            new ImageLoadEvent(getId(), SystemClock.uptimeMillis(), ImageLoadEvent.ON_LOAD_END)
                    );
                }
            };
        }

        mIsDirty = true;
    }

    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        mIsDirty = true;
    }

    public void setBorderWidth(float borderWidth) {
        mBorderWidth = PixelUtil.toPixelFromDIP(borderWidth);
        mIsDirty = true;
    }

    public void setBorderRadius(float borderRadius) {
        mBorderRadius = PixelUtil.toPixelFromDIP(borderRadius);
        mIsDirty = true;
    }

    public void setScaleType(ScalingUtils.ScaleType scaleType) {
        mScaleType = scaleType;
        mIsDirty = true;
    }

    public void setSource(@Nullable String source) {
        mUri = null;
        if (source != null) {
            try {
                mUri = Uri.parse(source);
                // Verify scheme is set, so that relative uri (used by static resources) are not handled.
                if (mUri.getScheme() == null) {
                    mUri = null;
                }
            } catch (Exception e) {
                // ignore malformed uri, then attempt to extract resource ID.
            }
            if (mUri == null) {
                mUri = getResourceDrawableUri(getContext(), source);
                mIsLocalImage = true;
            } else {
                mIsLocalImage = false;
            }
        }
        mIsDirty = true;
    }

<<<<<<< HEAD
    public void setBlur(@Nullable Integer blur) {
        if(blur != null) {
            mBlur = blur;
        }
    }

=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
    public void setLoadingIndicatorSource(@Nullable String name) {
        Drawable drawable = getResourceDrawable(getContext(), name);
        mLoadingImageDrawable =
                drawable != null ? (Drawable) new AutoRotateDrawable(drawable, 1000) : null;
        mIsDirty = true;
    }

    public void setProgressiveRenderingEnabled(boolean enabled) {
        mProgressiveRenderingEnabled = enabled;
        // no worth marking as dirty if it already rendered..
    }

    public void setFadeDuration(int durationMs) {
        mFadeDurationMs = durationMs;
        // no worth marking as dirty if it already rendered..
    }

    public void maybeUpdateView() {
        if (!mIsDirty) {
            return;
        }
<<<<<<< HEAD
        Log.d("abc","updating view");
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df

        boolean doResize = shouldResize(mUri);
        if (doResize && (getWidth() <= 0 || getHeight() <=0)) {
            // If need a resize and the size is not yet set, wait until the layout pass provides one
            return;
        }

        GenericDraweeHierarchy hierarchy = getHierarchy();
        hierarchy.setActualImageScaleType(mScaleType);

        if (mLoadingImageDrawable != null) {
//            hierarchy.setPlaceholderImage(mLoadingImageDrawable, ScalingUtils.ScaleType.CENTER);
        }

        boolean usePostprocessorScaling =
                mScaleType != ScalingUtils.ScaleType.CENTER_CROP &&
                        mScaleType != ScalingUtils.ScaleType.FOCUS_CROP;
        float hierarchyRadius = usePostprocessorScaling ? 0 : mBorderRadius;

        RoundingParams roundingParams = hierarchy.getRoundingParams();
        roundingParams.setCornersRadius(hierarchyRadius);
        roundingParams.setBorder(mBorderColor, mBorderWidth);
        hierarchy.setRoundingParams(roundingParams);
        hierarchy.setFadeDuration(
                mFadeDurationMs >= 0
                        ? mFadeDurationMs
                        : mIsLocalImage ? 0 : REMOTE_IMAGE_FADE_DURATION_MS);

        Postprocessor postprocessor = usePostprocessorScaling ? mRoundedCornerPostprocessor : null;

<<<<<<< HEAD
        Postprocessor redMeshPostprocessor = new redMeshPostprocessor();

        ResizeOptions resizeOptions = doResize ? new ResizeOptions(getWidth(), getHeight()) : null;

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(mUri)
                .setPostprocessor(redMeshPostprocessor)
=======
        ResizeOptions resizeOptions = doResize ? new ResizeOptions(getWidth(), getHeight()) : null;

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(mUri)
                .setPostprocessor(postprocessor)
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
                .setResizeOptions(resizeOptions)
                .setAutoRotateEnabled(true)
                .setProgressiveRenderingEnabled(mProgressiveRenderingEnabled)
                .build();

        // This builder is reused
        mDraweeControllerBuilder.reset();

        mDraweeControllerBuilder
                .setAutoPlayAnimations(true)
                .setCallerContext(mCallerContext)
                .setOldController(getController())
                .setImageRequest(imageRequest);

        if (mControllerListener != null && mControllerForTesting != null) {
            ForwardingControllerListener combinedListener = new ForwardingControllerListener();
            combinedListener.addListener(mControllerListener);
            combinedListener.addListener(mControllerForTesting);
            mDraweeControllerBuilder.setControllerListener(combinedListener);
        } else if (mControllerForTesting != null) {
            mDraweeControllerBuilder.setControllerListener(mControllerForTesting);
        } else if (mControllerListener != null) {
            mDraweeControllerBuilder.setControllerListener(mControllerListener);
        }

        setController(mDraweeControllerBuilder.build());
        mIsDirty = false;
    }

    // VisibleForTesting
    public void setControllerListener(ControllerListener controllerListener) {
        mControllerForTesting = controllerListener;
        mIsDirty = true;
        maybeUpdateView();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0) {
            maybeUpdateView();
        }
    }

    /**
     * ReactImageViews only render a single image.
     */
    @Override
    public boolean hasOverlappingRendering() {
        return false;
    }

    private static boolean shouldResize(@Nullable Uri uri) {
        // Resizing is inferior to scaling. See http://frescolib.org/docs/resizing-rotating.html#_
        // We resize here only for images likely to be from the device's camera, where the app developer
        // has no control over the original size
        return uri != null && (UriUtil.isLocalContentUri(uri) || UriUtil.isLocalFileUri(uri));
    }

    private static int getResourceDrawableId(Context context, @Nullable String name) {
        if (name == null || name.isEmpty()) {
            return 0;
        }
        return context.getResources().getIdentifier(
                name.toLowerCase().replace("-", "_"),
                "drawable",
                context.getPackageName());
    }

    private static @Nullable Drawable getResourceDrawable(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? context.getResources().getDrawable(resId) : null;
    }

    private static Uri getResourceDrawableUri(Context context, @Nullable String name) {
        int resId = getResourceDrawableId(context, name);
        return resId > 0 ? new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(resId))
                .build() : Uri.EMPTY;
    }
<<<<<<< HEAD
    public Bitmap fastblur(Bitmap sentBitmap, float scale, int radius) {

        int width = Math.round(sentBitmap.getWidth() * scale);
        int height = Math.round(sentBitmap.getHeight() * scale);
        sentBitmap = Bitmap.createScaledBitmap(sentBitmap, width, height, false);

        Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = ( 0xff000000 & pix[yi] ) | ( dv[rsum] << 16 ) | ( dv[gsum] << 8 ) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        Log.e("pix", w + " " + h + " " + pix.length);
        bitmap.setPixels(pix, 0, w, 0, 0, w, h);
        return (bitmap);
    }
=======
>>>>>>> 2d69a864c39e46b74b4827f4bd8675cd720cd7df
}
