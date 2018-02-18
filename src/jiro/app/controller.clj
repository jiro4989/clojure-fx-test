(ns jiro.app.controller
  [:gen-class
   :name jiro.app.Controller
   :methods [[action [javafx.event.ActionEvent] void]]]
  [:import [javafx.fxml FXML]
   [java.io File]
   [javafx.stage
    Stage
    StageStyle
    FileChooser
    FileChooser$ExtensionFilter]])

(defn open-file []
  (println "foobar"))

;(defn open-file []
;  (let [fc (FileChooser.)]
;    (let [files (doto fc
;                  (-> .getExtensionFilters (.add (FileChooser$ExtensionFilter. "Image Files" ["*.png"])))
;                  (.setInitialDirectory (File. "."))
;                  (.showOpenMultipleDialog (Stage. StageStyle/UTILITY)))]
;      (doto image-files-list-view
;        (-> .getItems (.addAll [File(".")]))))))
