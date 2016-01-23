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

(defn select-slide [val]
  (condp = val
    0 slide-opening
    1 slide-goals
    slide-opening))


(defn main-panel []
  (let [slide-num (re-frame/subscribe [:slide])]
    (fn []
      [re-com/v-box
       :align :center
       :justify :center
       :height "100%"
       :children [[(select-slide @slide-num)]]
       :attr {:tabIndex 0
              :on-key-down (fn [e] 
                            (js/console.log e.keyCode)
                            (cond
                             ;; right arrow
                             (= e.keyCode 39) (re-frame/dispatch [:slide-forward])
                             ;; left arror
                             (= e.keyCode 37) (re-frame/dispatch [:slide-back])))}])))
                          
            
