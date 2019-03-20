(ns assignment.cli-test
  (:require [clojure.test :refer :all]
            [assignment.cli :as base]))

(deftest cli-tests
  (is (not (base/-main)))
  (is (= :pipe-delimited (base/file-ext->input-fmt "psv")))
  (is (= :space-delimited (base/file-ext->input-fmt "ssv")))
  (is (= :comma-delimited (base/file-ext->input-fmt "csv")))
  (is (not (base/file-ext->input-fmt "tsv")))
  (is (= 50000 (count (base/read-file :pipe-delimited "resources/data.psv")))))
