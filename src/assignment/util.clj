(ns assignment.util
  (:require [clojure.string :as str]
            [clj-time.core :as tc]
            [clj-time.format :as tf]))

(defn parse-date [date-str]
  (let [[month day year] (str/split date-str #"/")]
    (tc/date-time (Integer/parseInt year)
                  (Integer/parseInt month)
                  (Integer/parseInt day)))
  ;; having trouble with tf/parse today for some reason.
  ;; working around it with the above hack.
  #_(tf/parse (tf/formatter "MM/dd/yyyy")))

(defn unparse-dates [rows]
  (map (fn [item]
         (update item :birthdate
                 #(tf/unparse (tf/formatter "MM/dd/yyyy") %)))
       rows))
