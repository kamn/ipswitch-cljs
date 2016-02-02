(ns ipswitch-cljs.views
    (:require [re-frame.core :as re-frame]
              [re-com.core :as re-com :refer [v-box h-box title label button single-dropdown input-text hyperlink-href]]))

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
                 :level :level1]
                [title
                 :label "* Concerns"
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
                 :level :level1]
                [title
                 :label "* Quicker Feedback leads to quicker development time"
                 :level :level1]]]))

(defn slide-feedback []
    (fn []
      [v-box
       :children [
                  [title
                   :label "Timesaver: Visual Feedback"
                   :level :level1]
                  [title
                   :label "* Quicker Feedback leads to quicker development time"
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
                   :label "and the re-frame framework..."
                   :level :level1]
                  [title
                   :label "being reload with a tool called Figwheel."
                   :level :level1]
                  [title
                   :label "This is only able to happen because of the nature of Clojurescript"
                   :level :level1]]]))

(def dyn-dev-text (str "Clojurescript is a dynamic environment you can interact with. "
                       "Almost all of the language constructs are reified, and "
                       "thus can be examined and changed. You can grow your "
                       "program, with data loaded, adding features, fixing "
                       "bugs, testing, in an unbroken stream."))

(def fp-text (str "Clojurescript provides the tools to avoid mutable state, provides "
                  "functions as first-class objects, and emphasizes recursive"
                  " iteration instead of side-effect based looping. Clojurescript"
                  " is impure, yet stands behind the philosophy that programs "
                  "that are more functional are more robust."))

(def lisp-text (str "Clojurescript is a member of the Lisp family of languages. "
                  "Many of the features of Lisp have made it into other "
                  "languages, but Lisp's approach to code-as-data and its macro "
                  "system still set it apart. Additionally, Clojurescript’s maps, "
                  "sets, and vectors are as first class in Clojurescript as lists are in Lisp."))

(def r-poly-text (str "Systems that utilize runtime polymorphism are easier to "
                      "change and extend. Clojurescript offers simple, powerful and "
                      "flexible mechanisms for runtime polymorphism. Clojurescript’s "
                      "protocols and datatypes features add mechanisms for "
                      "abstraction and data structure definition with no "
                      "compromises vs the facilities of the host platform."))

(defn slide-intro-cljs []
    (fn []
      [v-box
       :children [
                  [title
                   :label "What is Clojurescript?"
                   :level :level1]
                  [title
                   :label "Dynamic Development"
                   :level :level1]
                  [title
                   :label dyn-dev-text
                   :level :level2]
                  [title
                   :label "Functional Programming"
                   :level :level1]
                  [title
                   :label fp-text
                   :level :level2]
                  [title
                   :label "Lisp"
                   :level :level1]
                  [title
                   :label lisp-text
                   :level :level2]
                  [title
                   :label "Runtime Polymorphism"
                   :level :level1]
                  [title
                   :label r-poly-text
                   :level :level2]]]))

(defn slide-intro-cljs-personal []
    (fn []
      [v-box
       :children [
                  [title
                   :label "What is Clojurescript to me?"
                   :level :level1]
                  [title
                   :label "* Dynamic Development"
                   :level :level1]
                  [title
                   :label "* Functions are key unit of composition"
                   :level :level1]
                  [title
                   :label "* Expressions over statements"
                   :level :level1]
                  [title
                   :label "* Stateless over Stateful"
                   :level :level1]
                  [title
                   :label "* Immutability over mutability"
                   :level :level1]]]))

(defn slide-example-code []
    (fn []
      [v-box
       :children [
                  [title
                   :label "Some Example Code"
                   :level :level1]
                  [hyperlink-href
                           :label "Himera"
                           :target "_blank"
                           :href "https://himera.herokuapp.com/synonym.html"]]]))

(defn slide-expr-over-statement []
    (fn []
      ;;http://fsharpforfunandprofit.com/posts/expressions-vs-statements/
      ;;http://lambda-the-ultimate.org/node/1044
      ;;https://visualstudiomagazine.com/Articles/2011/04/01/pcnet_Using-LINQ.aspx
      [v-box
       :children [
                  [title
                   :label "Expressions Over Statements"
                   :level :level1]
                  [title
                   :label "* Purity"
                   :level :level1]
                  [title
                   :label "* LINQ in C#"
                   :level :level1]]]))

(defn slide-stateless-over-state []
    (fn []
      ;;http://stackoverflow.com/questions/844536/advantages-of-stateless-programming
      [v-box
       :children [
                  [title
                   :label "Stateless over Stateful"
                   :level :level1]
                  [title
                   :label "* Purity"
                   :level :level1]]]))

(defn slide-immut-over-mut []
    (fn []
      ;;https://facebook.github.io/react/docs/advanced-performance.html
      ;;
      [v-box
       :children [
                  [title
                   :label "Immutability over Mutability"
                   :level :level1]
                  [title
                   :label "\"It is expensive\""
                   :level :level1]
                  [hyperlink-href
                           :label "React Adv Performance"
                           :target "_blank"
                           :href "https://facebook.github.io/react/docs/advanced-performance.html#immutable-js-to-the-rescue"]
                  [title
                   :label "\"So, Immutable data structures provides you a cheap and less verbose way to track changes... Therefore, if we [use immutable data we will] get a nice boost in perf.\""
                   :level :level2]]]))

(defn slide-react []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "React's place"
                   :level :level1]
                  [title
                   :label "* Transforms values into view (like a function)"
                   :level :level1]
                  [title
                   :label "* Functionally stateless"
                   :level :level1]
                  [title
                   :label "* Major improvments because of immutability "
                   :level :level1]]]))

(defn slide-reagent []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "Reagent"
                   :level :level1]]]))

(defn slide-re-frame []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "Reframe"
                   :level :level1]
                  [title
                   :label "({events} ==>) app-db  -->  components -->  Reagent  -->  React  -->  DOM ==> {events}"
                   :level :level2]]]))

(defn slide-figwheel []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "Figwheel"
                   :level :level1]]]))

(defn slide-conerns []
    (fn []
      ;;TODO - 
      [v-box
       :children [
                  [title
                   :label "Concerns"
                   :level :level1]]]))

(defn select-slide [val]
  (condp = val
    0 slide-opening
    1 slide-goals
    2 slide-first-example
    3 slide-reload-time
    4 slide-how-demo
    5 slide-intro-cljs
    6 slide-example-code
    7 slide-intro-cljs-personal
    8 slide-expr-over-statement
    9 slide-stateless-over-state
    10 slide-immut-over-mut
    11 slide-react
    12 slide-reagent
    13 slide-re-frame
    14 slide-figwheel
    15 slide-conerns
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
                          
            
