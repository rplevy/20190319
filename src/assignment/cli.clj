(ns assignment.cli
  (:require [clojure.string :as str]
            [clojure.tools.cli :refer [parse-opts]]
            [assignment.read :as read]
            [assignment.sort :as sort]))

(defn read-file [file-name input-format]
  (map (partial read/using-format input-format)
       (str/split (slurp file-name) #"\n")))

(def file-ext->input-fmt
  {"psv" :pipe-delimited
   "ssv" :space-delimited
   "csv" :comma-delimited})

(defn file-ext [file-name]
  (last (str/split file-name #"\.")))

(defn -main [& args]
  (let [{:keys [options errors arguments]}
        (parse-opts args
                    [["-g" "--gender SORT-DIR" "gender sort direction"
                      :validate [#{"asc" "desc"} "Must be asc or desc"]]
                     ["-b" "--birthdate SORT-DIR" "birthdate sort direction"
                      :validate [#{"asc" "desc"} "Must be asc or desc"]]
                     ["-n" "--name SORT-DIR" "last name sort direction"
                      :validate [#{"asc" "desc"} "Must be asc or desc"]]
                     ["-h" "--help"]])
        file-name (first arguments)
        input-format (file-ext->input-fmt (file-ext file-name))]
    (cond errors (doseq [e errors] (println e))
          (not input-format) (println "unsupported file type")
          :else (sort/by options (read-file file-name input-format)))))
