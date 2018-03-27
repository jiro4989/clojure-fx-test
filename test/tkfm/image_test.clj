(ns tkfm.image-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [clojure.java.io :as io]
            [jiro.app.image :refer :all])
  (:import [javax.imageio ImageIO]))

(deftest trim-test
  (testing "画像のトリミングのテスト"
    (-> (ImageIO/read (io/file  "./data/in/test01.png"))
        (trim {:x 0 :y 0 :width 144 :height 144})
        (ImageIO/write "PNG" (io/file "./data/out/trim-test-result.png")))))

(deftest scale-test
  (testing "画像の縮小のテスト"
    (-> (ImageIO/read (io/file  "./data/in/test01.png"))
        (scale 0.5)
        (ImageIO/write "PNG" (io/file "./data/out/scale-up-test-result.png")))
    (-> (ImageIO/read "./data/in/test01.png")
        (scale 1.5)
        (ImageIO/write "PNG" (io/file "./data/out/scale-down-test-result.png")))))

(deftest flip-test
  (testing "画像の左右反転のテスト"
    (-> (ImageIO/read (io/file  "./data/in/test01.png"))
        flip
        (ImageIO/write "PNG" (io/file "./data/out/flip-test-result.png")))))
