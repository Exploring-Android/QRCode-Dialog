package com.wtrwx.qrdialog;
import android.content.Context;
import android.app.Dialog;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.view.View;
import android.graphics.Bitmap;

import java.util.Map;
import java.util.HashMap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import android.view.WindowManager;

public class QRDialog {
    public static void show(Context context,String url){
		final Dialog qrDialog = new Dialog(context, 0);
		qrDialog.setTitle("Scan QRCode");
		qrDialog.setContentView(R.layout.dialog_qrcode);
		ImageView imageView = qrDialog.findViewById(R.id.img_qrcode);
		imageView.setImageBitmap(createQRCode(url,350,350));
		qrDialog.setCanceledOnTouchOutside(true); 
		qrDialog.show();
	}
	public static Bitmap createQRCode(String content, int widthPix, int heightPix) {
        try {
            if (content == null || "".equals(content)) {
                return null;
            }
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix,
															heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.RGB_565);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
