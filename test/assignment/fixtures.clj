(ns assignment.fixtures
  (:require [assignment.db :as db]
            [assignment.util :as util]))

(defn reset-db [f]
  (reset! db/db nil)
  (f))
