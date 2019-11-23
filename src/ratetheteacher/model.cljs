(ns ratetheteacher.model
  (:require [reagent.core :as reagent :refer [atom]]))

(defonce app-state (atom {:teachers {1 {:name "Luis Roberto Nova"
                                        :rating 2
                                        :subject "Informatica"
                                         :reviews {
                                                  1 {:reviewer "Fernando"
                                                     :rating 3
                                                     :review "My words about the teacher goes here!"}}}
                                     2 {:name "Carlos Torres"
                                        :rating 2
                                        :subject "Matematica 3"
                                        :reviews {2 {:reviewer "Marco"
                                                     :rating 1
                                                     :review "My words about the teacher goes here!"}}}
                                     3 {:name "Dani de Jesus"
                                        :rating 2
                                        :subject "Matematica Binaria"
                                        :reviews {3 {:reviewer "Fernando"
                                                     :rating 5
                                                     :review "My words about the teacher goes here!"}}
                                        }}
                          :search-term ""}))

(get-in @app-state [:teachers 1 :name])


(defn navigation [event]
  (swap! app-state assoc :route (.-token event)))