package opencv_java;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        // 入力画像の取得
        Mat im = Imgcodecs.imread("input.png");
        // カスケード分類器で顔探索
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_alt_tree.xml");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(im, faceDetections);
        // 見つかった顔を矩形で囲む
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(im, new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 5);
        }
        // 結果を保存
        Imgcodecs.imwrite("ouput.png", im);
    }
}