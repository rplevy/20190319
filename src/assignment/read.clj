(ns assignment.read
  "Read input data in three supported formats"
  (:require [clojure.string :as str]
            [assignment.util :as util]
            [clj-time.format :as tf]))

(defmulti using-format (fn [input-format line] input-format))

(defn items->map [[last-name first-name gender favorite-color birthdate]]
  {:last-name last-name
   :first-name first-name
   :gender gender
   :favorite-color favorite-color
   :birthdate (util/parse-date birthdate)})

(defmethod using-format :pipe-delimited [_ line]
  (items->map (str/split line #"\s*\|\s*")))

(defmethod using-format :comma-delimited [_ line]
  (items->map (str/split line #"\s*\,\s*")))

(defmethod using-format :space-delimited [_ line]
  (items->map (str/split line #"\s+")))
