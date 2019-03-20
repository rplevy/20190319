(ns assignment.read-test
  (:require [clojure.test :refer :all]
            [assignment.read :as base]))

(deftest read-tests
  (is (= {:first-name "Freddie"
          :last-name "Wilson"
          :gender "male"
          :favorite-color "beige"
          :birthdate "5/2/1975"}
         (base/using-format :pipe-delimited
                            "Wilson | Freddie | male | beige | 5/2/1975")))
  (is (= {:first-name "Harry"
          :last-name "Moore"
          :gender "male"
          :favorite-color "pink"
          :birthdate "9/2/2015"}
         (base/using-format :comma-delimited
                            "Moore, Harry, male, pink, 9/2/2015")))
  (is (= {:first-name "Emily"
          :last-name "Davis"
          :gender "female"
          :favorite-color "turquoise"
          :birthdate "9/8/2010"}
         (base/using-format :space-delimited
                            "Davis Emily female turquoise 9/8/2010"))))
