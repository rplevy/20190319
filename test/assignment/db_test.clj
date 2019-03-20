(ns assignment.db-test
  (:require [clojure.test :refer :all]
            [assignment.db :as base]
            [assignment.fixtures :as fixtures]))

(use-fixtures :each fixtures/reset-db)

(deftest db-tests
  (is (base/insert-record {:last-name "Davis"
                           :first-name "Emily"
                           :gender "female"
                           :favorite-color "turquoise"
                           :birthdate "9/8/2010"})
      (= "turquoise" (:favorite-color (first @base/db)))))
