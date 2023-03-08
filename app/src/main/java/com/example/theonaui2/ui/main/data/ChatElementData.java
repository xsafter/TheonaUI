package com.example.theonaui2.ui.main.data;

import static android.graphics.Color.blue;
import static android.graphics.Color.green;
import static android.graphics.Color.red;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.example.theonaui2.R;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.function.DoubleUnaryOperator;

public class ChatElementData {
    private String chatId;
    private String chatName;
    private Bitmap chatImage;
    private ArrayList<Message> cachedMessages;
    private int unreadMessagesCount;

    private String lastMessageText;
    private long lastMessageTimestamp;
    private String lastMessageSender;

    public ChatElementData(String chatName, Bitmap chatImage, ArrayList<Message> cachedMessages, int unreadMessagesCount) {
        this.chatId = UUID.randomUUID().toString();
        this.chatName = chatName;
        this.chatImage = chatImage;
        this.cachedMessages = cachedMessages;
        this.unreadMessagesCount = unreadMessagesCount;
        if (cachedMessages.get(cachedMessages.size() - 1).getMessageImage() != null) {
            lastMessageText = "📷Photo";
        } else {
            lastMessageText = cachedMessages.get(cachedMessages.size() - 1).getMessageText();
        }
        this.lastMessageTimestamp = cachedMessages.get(cachedMessages.size() - 1).getTimestamp();
        this.lastMessageSender = cachedMessages.get(cachedMessages.size() - 1).getMessageSender();
    }

    public ChatElementData( ) {}
    public String getChatId() {
        return chatId;
    }

    public String getChatName() {
        return chatName;
    }

    public Bitmap getChatImage() {
        return chatImage;
    }

    public ArrayList<Message> getCachedMessages() {
        return cachedMessages;
    }

    public int getUnreadMessagesCount() {
        return unreadMessagesCount;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }

    public long getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public String getLastMessageSender() {
        return lastMessageSender;
    }


    /*public ChatElementData createTestData(Context context) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());


        Faker faker = new Faker();
        ArrayList<Message> cachedMessages = new ArrayList<>();

        Message msg = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            msg = new Message(UUID.randomUUID().toString(),
                    faker.date().past(7, TimeUnit.DAYS).getTime(),
                    faker.lorem().sentence(5),
                    faker.name().fullName());
        }
        cachedMessages.add(msg);

        String chatName = faker.name().fullName();
        int unreadMessagesCount = new Random().nextInt(10);

            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.account);
            Log.d("ChatElementData", "drawable: " + drawable.toString());
            //change color of bitmap
            Color color = getRandomColor(UUID.randomUUID());
            Log.d("color", String.valueOf(color));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DrawableCompat.setTint(drawable, color.getComponentCount());
            }
            Log.d("color", String.valueOf(color));
        return new ChatElementData(chatName, chatImage, cachedMessages, unreadMessagesCount);
    }*/

    public ChatElementData createTestData(Context context) {
        ArrayList<Message> cachedMessages = new ArrayList<Message>();
        cachedMessages.add(new Message(UUID.randomUUID().toString(),
                System.currentTimeMillis(),
                "📷Photo",
                "Alex")
        );
        return new ChatElementData("test",
                getBitmap(context, R.drawable.account),
                cachedMessages,
                new Random().nextInt(10)
        );
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private static Bitmap getBitmap(VectorDrawable vectorDrawable) {
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return bitmap;
    }

    private static Bitmap getBitmap(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (drawable instanceof BitmapDrawable) {
            return BitmapFactory.decodeResource(context.getResources(), drawableId);
        } else if (drawable instanceof VectorDrawable) {
            return getBitmap((VectorDrawable) drawable);
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    private Bitmap changeColor(Bitmap bitmap, Color color) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        paint.setColorFilter(colorMatrixColorFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return output;
    }

    public static Color getRandomColor(UUID id) {
        byte[] bytes = UUID2Bytes(id);
        int r = Math.abs(bytes[0]);
        int g = Math.abs(bytes[1]);
        int b = Math.abs(bytes[2]);
        Color randomColor = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            randomColor = Color.valueOf(Color.rgb(r, g, b));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            while (luminance(randomColor.getComponentCount()) < 0.5) {
                id = UUID.randomUUID();
                bytes = UUID2Bytes(id);
                r = Math.abs(bytes[0]);
                g = Math.abs(bytes[1]);
                b = Math.abs(bytes[2]);
                randomColor = Color.valueOf(Color.rgb(r, g, b));
            }
        }
        return randomColor;
    }

    public static byte[] UUID2Bytes(UUID uuid) {
        long hi = uuid.getMostSignificantBits();
        long lo = uuid.getLeastSignificantBits();
        return ByteBuffer.allocate(16).putLong(hi).putLong(lo).array();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static float luminance(@ColorInt int color) {
        ColorSpace.Rgb cs = null;
            cs = (ColorSpace.Rgb) ColorSpace.get(ColorSpace.Named.SRGB);
        DoubleUnaryOperator eotf = null;
        eotf = cs.getEotf();


        double r = eotf.applyAsDouble(red(color) / 255.0);
        double g = eotf.applyAsDouble(green(color) / 255.0);
        double b = eotf.applyAsDouble(blue(color) / 255.0);

        return (float) ((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
    }

    public void setChatImage(Bitmap chatImage) {
        this.chatImage = chatImage;
    }

    @Override
    public String toString() {
        return "ChatElementData{" +
                "chatId='" + chatId + '\'' +
                ", chatName='" + chatName + '\'' +
                ", chatImage=" + chatImage +
                ", cachedMessages=" + cachedMessages +
                ", unreadMessagesCount=" + unreadMessagesCount +
                ", lastMessageText='" + lastMessageText + '\'' +
                ", lastMessageTimestamp=" + lastMessageTimestamp +
                ", lastMessageSender='" + lastMessageSender + '\'' +
                '}';
    }
}
