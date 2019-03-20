(ns assignment.cli
  (:require [clojure.string :as str]
            [clojure.tools.cli :refer [parse-opts]]
            [assignment.cli.print :as print]
            [assignment.read :as read]
            [assignment.sort :as sort]))

(defn read-file [input-format file-name]
  (map (partial read/using-format input-format)
       (str/split (slurp file-name) #"\n")))

(def file-ext->input-fmt
  {"psv" :pipe-delimited
   "ssv" :space-delimited
   "csv" :comma-delimited})

(defn file-ext [file-name]
  (last (str/split file-name #"\.")))

(defn -main [& args]
  (when args
    (let [{:keys [options errors arguments]}
          (parse-opts args
                      [["-s" "--sort-field SORT-FIELD" "sort field"
                        :parse-fn keyword
                        :validate [#{:gender :last-name :birthdate}
                                   "Must be gender, last-name, or birthdate"]]
                       ["-d" "--sort-dir SORT-DIR" "sort direction"
                        :parse-fn keyword
                        :default :asc
                        :validate [#{:asc :desc} "Must be asc or desc"]]
                       ["-o" "--output-format FORMAT" "output format"
                        :parse-fn keyword
                        :validate [#{:pipe-delimited
                                     :space-delimited
                                     :comma-delimited}
                                   "must be a valid format"]]
                       ["-h" "--help"]])
          file-name (first arguments)
          input-format (file-ext->input-fmt (file-ext file-name))]
      (cond errors (doseq [e errors] (println e))
            (not input-format) (println "unsupported file type")
            :else (doseq [row (sort/by options
                                       (read-file input-format file-name))]
                    (print/using-format (or (:output-format options)
                                            input-format)
                                        row))))))


#_(reduce-kv (fn [r k v]
             (assoc r k (keyword v)))
           {}
           options) ; keywordize
