(ns assignment.cli.print)

(defmulti using-format (fn [output-format row] output-format))

(defmethod using-format :pipe-delimited
  [_ {:keys [last-name first-name gender favorite-color birthdate] :as row}]
  (printf "%s | %s | %s | %s | %s\n"
          last-name first-name gender favorite-color birthdate))

(defmethod using-format :space-delimited
  [_ {:keys [last-name first-name gender favorite-color birthdate] :as row}]
  (printf "%s %s %s %s %s\n"
          last-name first-name gender favorite-color birthdate))

(defmethod using-format :comma-delimited
  [_ {:keys [last-name first-name gender favorite-color birthdate] :as row}]
  (printf "%s, %s, %s, %s, %s\n"
          last-name first-name gender favorite-color birthdate))
