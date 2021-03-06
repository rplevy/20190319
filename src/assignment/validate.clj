(ns assignment.validate
  "Validate input data in three supported formats")

(defn pipe-delimited? [line]
  (re-matches #".*\|.*\|.*\|.*\|.*" line))

(defn comma-delimited? [line]
  (re-matches #".*,.*,.*,.*,.*" line))

(defn space-delimited? [line]
  (re-matches #"[^\s]*\s[^\s]*\s[^\s]*\s[^\s]*\s[^\s]*" line))

(defn detect-format
  "either produce a keyword for the format or nil (invalid)"
  [line]
  (or (when (pipe-delimited? line)
        :pipe-delimited)
      (when (comma-delimited? line)
        :comma-delimited)
      (when (space-delimited? line)
        :space-delimited)))
