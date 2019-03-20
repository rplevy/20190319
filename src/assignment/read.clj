(ns assignment.read
  "Read input data in three supported formats")

(defmulti using-format (fn [input-format line] input-format))

(defmethod using-format :pipe-delimited [_ line]
  )

(defmethod using-format :comma-delimited [_ line]
  )

(defmethod using-format :space-delimited [_ line]
  )
