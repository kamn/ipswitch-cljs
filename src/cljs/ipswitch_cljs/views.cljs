(ns ipswitch-cljs.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com]))

(defn title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [re-com/title
       :label (str "Hello from " @name)
       :level :level1])))

(defn slide-opening []
  (fn []
    [re-com/v-box
     :children [[title]]]))

(defn slide-goals []
  (fn []
    [re-com/v-box
     :children [
                [re-com/title
                 :label "test"]]]))


(defn main-panel []
  (fn []
    [re-com/v-box
     :align :center
     :justify :center
     :height "100%"

     :children [[slide-opening]]
                
     :attr {:tabIndex 0
            :on-key-down (fn [e] (js/console.log "click") (re-frame/dispatch [:slide-forward]))}]))
            
