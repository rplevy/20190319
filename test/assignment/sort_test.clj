(ns assignment.sort-test
  (:require [clojure.test :refer :all]
            [assignment.sort :as base]))

(def test-data
  [{:first-name "Freddie"
    :last-name "Wilson"
    :gender "male"
    :favorite-color "beige"
    :birthdate "5/2/1975"}
   {:first-name "Harry"
    :last-name "Moore"
    :gender "male"
    :favorite-color "pink"
    :birthdate "9/2/2015"}
   {:first-name "Emily"
    :last-name "Davis"
    :gender "female"
    :favorite-color "turquoise"
    :birthdate "9/8/2010"}])

(deftest sort-tests
  (is (= [{:first-name "Emily"
           :last-name "Davis"
           :gender "female"
           :favorite-color "turquoise"
           :birthdate "9/8/2010"}
          {:first-name "Harry"
           :last-name "Moore"
           :gender "male"
           :favorite-color "pink"
           :birthdate "9/2/2015"}
          {:first-name "Freddie"
           :last-name "Wilson"
           :gender "male"
           :favorite-color "beige"
           :birthdate "5/2/1975"}]
         (base/by {:gender :asc} test-data)))
  (is (= [{:first-name "Freddie"
           :last-name "Wilson"
           :gender "male"
           :favorite-color "beige"
           :birthdate "5/2/1975"}
          {:first-name "Emily"
           :last-name "Davis"
           :gender "female"
           :favorite-color "turquoise"
           :birthdate "9/8/2010"}
          {:first-name "Harry"
           :last-name "Moore"
           :gender "male"
           :favorite-color "pink"
           :birthdate "9/2/2015"}]
         (base/by {:birthdate :asc} test-data)))
  (is (= [{:first-name "Freddie"
           :last-name "Wilson"
           :gender "male"
           :favorite-color "beige"
           :birthdate "5/2/1975"}
          {:first-name "Harry"
           :last-name "Moore"
           :gender "male"
           :favorite-color "pink"
           :birthdate "9/2/2015"}
          {:first-name "Emily"
           :last-name "Davis"
           :gender "female"
           :favorite-color "turquoise"
           :birthdate "9/8/2010"}]
         (base/by {:birthdate :asc} test-data))))
