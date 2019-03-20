(ns assignment.validate-test
  (:require [clojure.test :refer :all]
            [assignment.validate :as base]))

(deftest validation-tests
  (is (base/pipe-delimited? "Wilson | Freddie | male | beige | 5/2/1975"))
  (is (base/comma-delimited? "Moore, Harry, male, pink, 9/2/2015"))
  (is (base/space-delimited? "Davis Emily female turquoise 9/8/2010"))
  (is (= :space-delimited
         (base/detect-format "Davis Emily female turquoise 9/8/2010")))
  (is (= :comma-delimited
         (base/detect-format "Moore, Harry, male, pink, 9/2/2015")))
  (is (= :pipe-delimited
         (base/detect-format "Wilson | Freddie | male | beige | 5/2/1975"))))
