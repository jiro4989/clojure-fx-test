(ns jiro.app.image
  (:import [java.awt.image BufferedImage AffineTransformOp]
           [java.awt RenderingHints]
           [java.awt.geom AffineTransform]))

;; 元の画像と同じ幅の空の画像を返す
(defn new-image [^BufferedImage image]
  (BufferedImage. 
    (.getWidth image) 
    (.getHeight image) 
    BufferedImage/TYPE_INT_ARGB))

;; trim - 画像を指定の座標から指定の幅だけ切り出した新しい画像として返却する
(defn trim [^BufferedImage image
            v]
  (.getSubImage image (v :x) (v :y) (v :width) (v :height)))

;; scale - 画像を指定の拡大率で拡縮した新しい画像を返却する
(defn scale [^BufferedImage image
             double rate]
  (let [ni (new-image image)]
    (doto (.createGraphics ni)
      (.setRenderingHint RenderingHints/KEY_INTERPOLATION RenderingHints/VALUE_INTERPOLATION_BICUBIC)
      (.scale rate rate)
      (.drawImage image 0 0 nil)
      .dispose)
    ni))

;; flip - 画像を左右反転した新しい画像を返却する
(defn flip [^BufferedImage image]
  (let [ni (new-image image)]
    (-> (doto (AffineTransform/getScaleInstance -1 1)
          (.translate (.getWidth image) 0))
        (AffineTransformOp. AffineTransformOp/TYPE_BICUBIC)
        (.filter image ni))
    ni))
