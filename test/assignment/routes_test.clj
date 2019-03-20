(ns assignment.routes-test
  (:require [clojure.test :refer :all]
            [assignment.fixtures :as fixtures]
            [assignment.routes :as base]
            [assignment.db :as db]
            [ring.mock.request :as mock]))

(use-fixtures :each fixtures/reset-db)

(deftest route-tests
  (is (= (base/app (mock/request :get "/records/gender"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[]}"}))
  (is (= (base/app (mock/request :get "/records/birthdate"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[]}"}))
  (is (= (base/app (mock/request :get "/records/name"))
         {:status 200
          :headers {"Content-Type" "application/json"}
          :body "{\"results\":[]}"}))
  (is (= (base/app (-> (mock/request :post "/records")
                       (mock/body "Doe | John | Male | Blue | 1/1/2001")))
         {:status 201
          :headers {}
          :body "inserted record for Doe\n"}))
  (with-redefs [db/db (atom [{:last-name "Barker" :first-name "Bob"
                              :gender "male" :favorite-color "blue"
                              :birthdate "1/1/2001"}
                             {:last-name "Smith" :first-name "Alice"
                              :gender "female" :favorite-color "green"
                              :birthdate "1/1/2002"}])]
    (is (= (base/app (mock/request :get "/records/gender"))
           {:status 200
            :headers {"Content-Type" "application/json"}
            :body "{\"results\":[{\"last-name\":\"Smith\",\"first-name\":\"Alice\",\"gender\":\"female\",\"favorite-color\":\"green\",\"birthdate\":\"1/1/2002\"},{\"last-name\":\"Barker\",\"first-name\":\"Bob\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"birthdate\":\"1/1/2001\"}]}"}))
    (is (= (base/app (mock/request :get "/records/birthdate"))
           {:status 200
            :headers {"Content-Type" "application/json"}
            :body "{\"results\":[{\"last-name\":\"Barker\",\"first-name\":\"Bob\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"birthdate\":\"1/1/2001\"},{\"last-name\":\"Smith\",\"first-name\":\"Alice\",\"gender\":\"female\",\"favorite-color\":\"green\",\"birthdate\":\"1/1/2002\"}]}"}))
    (is (= (base/app (mock/request :get "/records/name"))
           {:status 200
            :headers {"Content-Type" "application/json"}
            :body "{\"results\":[{\"last-name\":\"Barker\",\"first-name\":\"Bob\",\"gender\":\"male\",\"favorite-color\":\"blue\",\"birthdate\":\"1/1/2001\"},{\"last-name\":\"Smith\",\"first-name\":\"Alice\",\"gender\":\"female\",\"favorite-color\":\"green\",\"birthdate\":\"1/1/2002\"}]}"}))))
