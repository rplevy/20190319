(ns assignment.sort
  "Functions for sorting data")

(def direction-string->comparator
  {:asc compare
   :desc (complement compare)})

(defn sort-by-gender
  "this is a special case in order to keep things simple, because
  supporting composite sort-by fields in the general case gets
  complicated and we only have one view in the requirements that
  necessitates it, the gender + last-name view.  So gender is simply
  always sorted with secondary sorting by last-name."
  [comparator data]
  (sort-by (fn [item] (str (:gender item) (:last-name item)))
           comparator
           data))

(defn by
  [{:keys [sort-field sort-dir]} data]
  (let [comparator (direction-string->comparator sort-dir)]
    (if (= sort-field :gender)
      (sort-by-gender comparator data)
      (sort-by sort-field comparator data))))
