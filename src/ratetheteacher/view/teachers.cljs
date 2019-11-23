(ns ^:figwheel-always ratetheteacher.view.teachers
  (:require [reagent.core :as reagent]
            [clojure.string :as str]
            [ratetheteacher.model :as model]))

(defn star[style]
   [:i  {:class "fas fa-star" :style style }])

(defn stars 
  ([number]
   (stars number {}))
  ([number style]
   [:div (for [key (range number)]
           ^{:key key} [star style])]))


(defn filter-search [app-state]
  (filter (fn [[id info]]
            (str/includes? (:name info) (:search-term @app-state)))
          (:teachers @app-state)))

(defn search-box []
  [:div.mui-textfield {:on-change
                       (fn done-search [e]
                         (swap! model/app-state assoc :search-term (.-target.value e)))}
   [:input {:type "text" :placeholder "Live search"}]])

(defn teacher-list [app-state]
  [:div
   [search-box]
   [:table.mui-table
    (into [:thead]
          (for [teacher (filter-search app-state)]
            (let [teacher-id (first teacher)
                  info (second teacher)]
              [:tr
               [:th (stars 5 {:color "yellow"
                               :font-size 9
                               :text-shadow "0 0 2px black"})]
               [:th (:name info)]
               [:th [:a.mui-btn.mui-btn--primary
                     {:href (str "#/info/" teacher-id)} 
                     "info"]]])))]])

(defn view-info [app-state route-params]
    (let [{:keys [name, rating, subject]} 
          (get-in @app-state 
                  [:teachers (js/parseInt (:teacher-id route-params))])]
      [:div
       [:div.info-card
       [:div
        [:img {:src "http://icons.iconarchive.com/icons/treetog/junior/96/folder-blue-pictures-icon.png"}]]
       [:div
        [:h2 name]
        [:p subject]
        [stars 5]
        [:a.mui-btn.mui-btn--raised.mui-btn--primary
         {:href
          (str "#/rate/" (:teacher-id route-params))} "Review"]]]
      [:div.mui--divider-bottom 
       [:h2 "Reviews"]]]))


(defn rate [app-state route-params]
  [:div.mui-container-fluid
   [:h2 "Add Review"]
   [:div.mui-textfield
    [:input {:type "text" :placeholder "your name"}]]
   [:h4 "Your Rate"]
   (stars  5 {:color "yellow"
               :font-size 20
               :margin 3
               :text-shadow "0 0 2px black"})
   [:div.mui-textfield 
    [:textarea {:placeholder "your comment"}]]
   [:button.mui-btn.mui-btn--raised {:type "submit"} "Submit Review"]])