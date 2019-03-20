(ns assignment.cli.print
  (:require [assignment.util :as util]))

(defmulti using-format (fn [output-format row] output-format))

(defmethod using-format :pipe-delimited
  [_ {:keys [last-name first-name gender favorite-color birthdate] :as row}]
  (printf "%s | %s | %s | %s | %s\n"
          last-name first-name gender favorite-color
          (util/unparse-date birthdate)))

(defmethod using-format :space-delimited
  [_ {:keys [last-name first-name gender favorite-color birthdate] :as row}]
  (printf "%s %s %s %s %s\n"
          last-name first-name gender favorite-color
          (util/unparse-date birthdate)))

(defmethod using-format :comma-delimited
  [_ {:keys [last-name first-name gender favorite-color birthdate] :as row}]
  (printf "%s, %s, %s, %s, %s\n"
          last-name first-name gender favorite-color
          (util/unparse-date birthdate)))
