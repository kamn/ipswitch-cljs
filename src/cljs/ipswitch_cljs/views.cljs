(ns ipswitch-cljs.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com :refer [v-box h-box title label button single-dropdown input-text]]))

(defn slide-title []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [title
       :label (str "Clojurescript For Fun and Profit")
       :level :level1])))

(defn slide-opening []
  (fn []
    [v-box
     :children [[slide-title]]]))

(defn slide-goals []
  (fn []
    [v-box
     :children [
                [title
                 :label "Goals"
                 :level :level1]
                [title
                 ;;:label "* Timesaving example of Clojurescript"
                 :label "* Profitable example of Clojurescript"
                 :level :level1]
                [title
                 :label "* What is Clojurescript"
                 :level :level1]
                [title
                 :label "* What benefits does it offer"
                 :level :level1]]]))

(defn slide-end []
  (fn []
    [v-box
     :children [
                [title
                 :label "Fin"
                 :level :level1]]]))


(defn example-action-section []
  (fn []
    [v-box
     :gap "10px"
     :children [
                 [h-box
                  :gap "10px"
                  :children [[label :label "Select an Action"]
                             [single-dropdown 
                              :on-change #(js/console.log %)
                              :model 1
                              :width "150px"
                              :choices [{:id 1 :label "Add Monitor"}
                                        {:id 2 :label "Add Report"}
                                        {:id 3 :label "Add Dashboard"}
                                        {:id 4 :label "Add Credential"}]]]]
                 [h-box
                  :gap "10px"
                  :children [[button :label "Cancel"]
                             [button :label "Next"
                                     :on-click #(re-frame/dispatch [:example-step-forward])]]]]]))
    

(defn example-cred-section []
  (fn []
    [v-box
     :gap "10px"
     :children [
                 [h-box
                  :gap "10px"
                  :children [[label :label "Select a Credential Type"]
                             [single-dropdown 
                              :on-change #(js/console.log %)
                              :model 1
                              :width "150px"
                              :choices [{:id 1 :label "SNMP v1"}
                                        {:id 2 :label "SNMP v2"}
                                        {:id 3 :label "SNMP v3"}
                                        {:id 4 :label "SSH"}
                                        {:id 5 :label "VMware"}]]]]
                 [h-box
                  :gap "10px"
                  :children [[button :label "Cancel"
                                     :on-click #(re-frame/dispatch [:example-step-back])]
                             [button :label "Next"
                                     :on-click #(re-frame/dispatch [:example-step-forward])]]]]]))

;; Name
;; Desc
;; Read Community
;; Write Community
(defn example-snmp-section []
  (fn []
    [v-box
     :gap "10px"
     :children [
                 [v-box
                  :gap "10px"
                  :children [[input-text :model "" :on-change #() :placeholder "Name"]
                             [input-text :model "" :on-change #() :placeholder "Write Community"]
                             [input-text :model "" :on-change #() :placeholder "Read Community"]
                             [input-text :model "" :on-change #() :placeholder "Timeout"]]]
                 [h-box
                  :gap "10px"
                  :children [[button :label "Cancel"
                                     :on-click #(re-frame/dispatch [:example-step-back])]
                             [button :label "Next"]]]]]))


(defn slide-first-example []
  (let [state (re-frame/subscribe [:example-1-state])]
    (fn []
      (cond 
        (= 0 (:step @state)) [example-action-section]
        (= 1 (:step @state)) [example-cred-section]
        (= 2 (:step @state)) [example-snmp-section]))))


;; Takes ~9 seconds
;; Vs <1 second to change code and look over
(defn slide-reload-time [] 
  (fn []
    [v-box
     :children [
                [title
                 :label "Timesaver: Reload Time"
                 :level :level1]
                [title
                 :label "* To reload and return to previous state is ~9 seconds"
                 :level :level1]
                [title
                 :label "* To change code and see change it is < 1 seconds"
                 :level :level1]
                [title
                 :label "* Web Devs reload browsers a few dozen of times per hour"
                 :level :level1]]]))

(defn slide-feedback []
    (fn []
      [v-box
       :children [
                  [title
                   :label "Timesaver: Reload Time"
                   :level :level1]
                  [title
                   :label "* To reload and return to previous state is ~9 seconds"
                   :level :level1]
                  [title
                   :label "* To change code and see change it is < 1 seconds"
                   :level :level1]
                  [title
                   :label "* Web Devs reload browsers a few dozen of times per hour"
                   :level :level1]]]))
(defn slide-how-demo []
    (fn []
      [v-box
       :children [
                  [title
                   :label "How was the Demo relate to Clojurescript?"
                   :level :level1]
                  [title
                   :label "Written in Clojurescript..."
                   :level :level1]
                  [title
                   :label "using Reagent(a wrapper for React)..."
                   :level :level1]
                  [title
                   :label "being reload with a tool called Figwheel."
                   :level :level1]
                  [title
                   :label "This is only able to happen because of the nature of Clojurescript"
                   :level :level1]]]))
(defn slide-intro-cljs []
    (fn []
      [v-box
       :children [
                  [title
                   :label "How was the Demo relate to Clojurescript"
                   :level :level1]
                  [title
                   :label "* To reload and return to previous state is ~9 seconds"
                   :level :level1]
                  [title
                   :label "* To change code and see change it is < 1 seconds"
                   :level :level1]
                  [title
                   :label "* Web Devs reload browsers a few dozen of times per hour"
                   :level :level1]]]))

(defn select-slide [val]
  (condp = val
    0 slide-opening
    1 slide-goals
    2 slide-first-example
    3 slide-reload-time
    4 slide-feedback
    5 slide-how-demo
    6 slide-intro-cljs
    slide-end))


(defn main-panel []
  (let [slide-num (re-frame/subscribe [:slide])]
    (fn []
      [v-box
       :align :center
       :justify :center
       :height "100%"
       :class "main-view"
       :children [[(select-slide @slide-num)]]
       :attr {:tabIndex 0
              :on-key-down (fn [e] 
                            (cond
                             ;; right arrow
                             (= e.keyCode 39) (re-frame/dispatch [:slide-forward])
                             ;; left arror
                             (= e.keyCode 37) (re-frame/dispatch [:slide-back])))}])))
                          
            
