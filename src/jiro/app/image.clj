(ns jiro.app.image
  (:import [java.awt.image BufferedImage AffineTransformOp]
           [java.awt RenderingHints]
           [java.awt.geom AffineTransform]))

;; trim - 画像を指定の座標から指定の幅だけ切り出した新しい画像として返却する
(defn trim [^BufferedImage image
            v]
  (.. image
      (getSubImage v[:x] v[:y] v[:width] v[:height])))

;; scale - 画像を指定の拡大率で拡縮した新しい画像を返却する
(defn scale [^BufferedImage image
             double rate]
  (let [w (-> image .getWidth int)
        h (-> image .getHeight int)]
    (let [ni (BufferedImage. w h BufferedImage/TYPE_INT_ARGB)]
      (doto (.createGraphics ni)
        (.setRenderingHint RenderingHints/KEY_INTERPOLATION RenderingHints/VALUE_INTERPOLATION_BICUBIC)
        (.scale rate rate)
        (.drawImage image 0 0 nil)
        .dispose)
      ni)))

;; flip - 画像を左右反転した新しい画像を返却する
(defn flip [^BufferedImage image]
  (.. (doto (AffineTransform/getScaleInstance -1 1)
        (.translate (.getWidth image) 0)
        (AffineTransformOp. AffineTransformOp/TYPE_BICUBIC))
      (filter image nil)))
