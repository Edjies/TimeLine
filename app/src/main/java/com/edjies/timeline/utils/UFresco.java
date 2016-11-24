package com.edjies.timeline.utils;

import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 图片加载库Fresco辅助类
 * @author hubble
 */

public class UFresco {
    /** 加载缩略图 */
    public static void loadThumbImg(SimpleDraweeView view, Uri uri, int width, int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height)).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(view.getController())
                .setControllerListener(new BaseControllerListener<ImageInfo>())
                .build();
        view.setController(controller);
        view.setOnClickListener(null);
    }

    /** 加载图片*/
    public static void loadImg(SimpleDraweeView view, Uri uri) {
        view.setImageURI(uri);
    }
}
