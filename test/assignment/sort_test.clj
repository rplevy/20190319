(ns assignment.sort-test
  (:require [clojure.test :refer :all]
            [assignment.sort :as base]
            [assignment.util :as util]))

(def test-data
  [{:first-name "Freddie"
    :last-name "Wilson"
    :gender "male"
    :favorite-color "beige"
    :birthdate (util/parse-date "5/2/1975")}
   {:first-name "Harry"
    :last-name "Moore"
    :gender "male"
    :favorite-color "pink"
    :birthdate (util/parse-date "9/2/2015")}
   {:first-name "Emily"
    :last-name "Davis"
    :gender "female"
    :favorite-color "turquoise"
    :birthdate (util/parse-date "9/8/2010")}])

(deftest sort-tests
  (is (= [{:first-name "Emily"
           :last-name "Davis"
           :gender "female"
           :favorite-color "turquoise"
           :birthdate (util/parse-date "9/8/2010")}
          {:first-name "Harry"
           :last-name "Moore"
           :gender "male"
           :favorite-color "pink"
           :birthdate (util/parse-date "9/2/2015")}
          {:first-name "Freddie"
           :last-name "Wilson"
           :gender "male"
           :favorite-color "beige"
           :birthdate (util/parse-date "5/2/1975")}]
         (base/by {:sort-field :gender
                   :sort-dir :asc} test-data)))
  (is (= [{:first-name "Freddie"
           :last-name "Wilson"
           :gender "male"
           :favorite-color "beige"
           :birthdate (util/parse-date "5/2/1975")}
          {:first-name "Emily"
           :last-name "Davis"
           :gender "female"
           :favorite-color "turquoise"
           :birthdate (util/parse-date "9/8/2010")}
          {:first-name "Harry"
           :last-name "Moore"
           :gender "male"
           :favorite-color "pink"
           :birthdate (util/parse-date "9/2/2015")}]
         (base/by {:sort-field :birthdate
                   :sort-dir :asc} test-data)))
  (is (= [{:first-name "Freddie"
           :last-name "Wilson"
           :gender "male"
           :favorite-color "beige"
           :birthdate (util/parse-date "5/2/1975")}
          {:first-name "Harry"
           :last-name "Moore"
           :gender "male"
           :favorite-color "pink"
           :birthdate (util/parse-date "9/2/2015")}
          {:first-name "Emily"
           :last-name "Davis"
           :gender "female"
           :favorite-color "turquoise"
           :birthdate (util/parse-date "9/8/2010")}]
         (base/by {:sort-field :last-name
                   :sort-dir :desc} test-data))))
