(ns assignment.db
  "Non-persistant storage for application state"
  (:require [clojure.tools.logging :as log]))

(defonce db (atom []))

(defn insert-record [rec]
  (swap! db conj rec))
