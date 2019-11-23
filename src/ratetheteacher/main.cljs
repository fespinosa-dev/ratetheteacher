(ns ratetheteacher.core
  (:require [reagent.core :as reagent]
            [goog.history.EventType :as EventType]
            [goog.events :as events]
            [bidi.bidi :as bidi]
            [ratetheteacher.model :as model]
            [ratetheteacher.view.navbar :as navbar]
            [ratetheteacher.view.teachers :as teachers])
  (:import
   [goog History]))

(enable-console-print!)

(def routes ["/" [["list" teachers/teacher-list]
                  [["info/" :teacher-id] teachers/view-info]
                  [["rate/" :teacher-id] teachers/rate]]])

(bidi/match-route routes "/list")

(defn rt-content [app-state]
  (let [route (:route @app-state)
        {:keys [:handler :route-params]} (bidi/match-route routes route)]
    [:div 
     [(or handler teachers/teacher-list) app-state route-params]]))

(defn rt-main [app-state]
  [:div
   [:header
    [navbar/navbar app-state]]
   [:section
    [rt-content app-state]]
   [:footer
    {:style {:font-family "fantasy"}}
    [:center "Rate Teacher @ 2019"]]])

(when-let [app (. js/document (getElementById "app"))]
  (doto (History.)
    (events/listen EventType/NAVIGATE model/navigation)
    (.setEnabled true))
  (reagent/render-component [rt-main model/app-state] app))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )
