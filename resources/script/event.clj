(import [javafx.stage
         Stage
         StageStyle
         FileChooser
         FileChooser$ExtensionFilter]
        [java.io File]
        )

(defn open-file []
  (let [fc (FileChooser.)]
    (let [files (doto fc
                  (-> .getExtensionFilters (.add (FileChooser$ExtensionFilter. "Image Files" ["*.png"])))
                  (.setInitialDirectory (File. "."))
                  (.showOpenMultipleDialog (Stage. StageStyle/UTILITY)))]
      (doto imageFilesListView
        (-> .getItems (.addAll [File(".")]))))))
