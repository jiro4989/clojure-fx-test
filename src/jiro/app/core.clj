(ns jiro.app.core
  (:import [javafx.application Application]
           [javafx.fxml FXMLLoader]
           [javafx.scene Scene]
           [javafx.stage Stage]
           [javafx.event ActionEvent EventHandler])
  (:require [clojure.java.io :as io])
  (:gen-class
    :extends javafx.application.Application
    :name jiro.app.MyApp))

;(defn fx-action [node action]
;  (.setOnAction node
;                (proxy [EventHandler] []
;                  (handle [^ActionEvent event] action))))

(defn ^:Private -start
  [this ^Stage stage]
  (let [root (-> "layout/root.fxml" io/resource FXMLLoader/load)
        list-view (.lookup root "#imageFilesListView")]
    (println list-view) ;なぜかここでnilになる...
    ;(.setOnAction list-view
    ;              (proxy [EventHandler][]
    ;                (handler [_] (println "foobar"))))
    (doto stage
      (.setTitle "FXML Welcome")
      (.setScene (Scene. root 300 275))
      .show)))

(defn -main
  "Program launcher."
  [& args]
  (Application/launch jiro.app.MyApp args))

