(ns ratetheteacher.view.navbar
  (:require [reagent.core :as reagent]))

(defn user-widget [app-state]
  (if-let [username (:username @app-state)]
    [:a {:href "#/settings"} username]
    [:a {:href "#/login"} "Login"]))

(defn navbar[app-state]
  [:div.mui-appbar
   [:table    
    {:width "100%"}
    [:tbody
     [:tr
      {:style {:vertical-aligment "middle"}}
      [:td.mui--appbar-height 
       [:h1 [:a {:href "#/" :style {:color "white"}} "Rate Teacher"]]]
      [:td.mui--appbar-height {:align "right"}
       [:ul.nav-list
        [:li [:a {:href "#/"} "About"]]
        [:li [user-widget app-state]]]]]]]])





