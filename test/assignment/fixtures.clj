(ns assignment.fixtures
  (:require [assignment.db :as db]))

(defn reset-db [f]
  (reset! db/db nil)
  (f))
